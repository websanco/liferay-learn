# Learning to Rank

> **Liferay Enterprise Search（LES）サブスクライバー**

Elasticsearchのような検索エンジンには、一般的な検索目的に適した、適切に調整された関連性アルゴリズムがあります。

LES Learning to Rankは、機械学習を利用して検索結果のランキングを向上させます。 データサイエンティストの専門知識と機械学習を組み合わせて、検索クエリに適用されるよりスマートなスコアリング関数を生成します。

LES Learning to Rankには、Liferay Enterprise Searchのサブスクリプションが必要です。 [Elasticsearch Learning to Rankのプラグイン](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/index.html)はElasticによって作成されたものではなく、LiferayでサポートされているすべてのElasticsearchバージョンに対応したビルド済みのプラグインはないことを理解することが重要です。 詳細については、[LES互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-Enterprise-Search)を参照してください。

## 検索ページでのLearning to Rankを無効にする

Learning to Rankは、[ソートウィジェット](../search-pages-and-widgets/search-results/sorting-search-results.md)では機能しません。

LES Learning to Rankがデプロイされているが、（おそらくはソートウィジェットを使用するために）特定の検索ページで無効にする必要がある場合、

1.  [低レベル検索オプション](../search-pages-and-widgets/search-results/understanding-low-level-search-options.md)ウィジェットを検索ページに追加します。

2.  クリックしてウィジェットの設定画面を開きます

    *このページでは、低レベル検索オプションを設定します。*

3.  [除外する貢献者]フィールドに、次のように入力します

    `com.liferay.portal.search.learning.to.rank`

これで、ページの検索バーに入力されたクエリに対し、Learning to Rankの再スコアリングプロセスがスキップされます。 その結果は並べ替え可能で、デフォルトの関連性アルゴリズムを使用して返されます。

## 前提条件

Learning to Rankを使用して、Elasticsearchに送信されたLiferayクエリを再スコアリングするには、いくつかの前提条件があります。

  - Learning to Rankには、[Liferay Enterprise Search](https://www.liferay.com/products/dxp/enterprise-search)（LES）サブスクリプションが必要です。 サブスクリプションを取得したら、[Liferay Enterprise Search Learning to RankのLPKGファイルをダウンロード](https://customer.liferay.com/en/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191013&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191060)して[インストール](../../system-administration/installing-and-managing-apps/installing-apps.md)します。

  - データがインデックス化されているリモートのElasticsearchサーバー。

  - [Elasticsearch Learning to Rank](https://github.com/o19s/elasticsearch-learning-to-rank)プラグインの対応するバージョンがElasticsearchにインストールされている。

  - [トレーニング済みモデル](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/training-models.html)がLearning to Rankプラグインにアップロードされている。

## 技術概要

通常の検索では、ユーザーはLiferay DXPの[検索バー](../getting-started/searching-for-content.md)を介して検索エンジンにクエリを送信します。 返される結果の順序は、検索エンジンの[関連性スコアリングアルゴリズム](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/index-modules-similarity.html#bm25)によって決定されます。

ここで、Learning to Rankが介入し、そのプロセスが変わります。

1.  ユーザーが検索バーにクエリを入力します。

2.  LiferayはクエリをElasticsearchに送信し、検索エンジンの関連性アルゴリズムを使用して、通常どおり最初の1000件の結果を取得します。

3.  上位1000件の結果は検索ヒットとして返されませんが、Elasticsearchはそれらの結果を使用して、[再スコアリング機能](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/searching-with-your-model.html#rescore-top-n-with-sltr)を介して[再スコアリング](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/search-request-body.html#request-body-search-rescore)を行います。

4.  結果は、再スコアリングに使用するキーワードとトレーニング済みモデルを含む[SLTRクエリ](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/searching-with-your-model.html)によって再スコアリングされます。

5.  トレーニング済みのモデルが結果を再ランク付けすると、Liferayの[検索結果](../search-pages-and-widgets/search-results/search-results.md)に新しい順序で返されます。

これは上に挙げたソート済みリストの中の小さな点にすぎませんが、このパラダイムでの作業の多くは、トレーニングされたモデルを作成して磨きをかけることです。 それは本セクションの範囲外ですが、Liferayのクエリで機械学習の魅力を調和させるために、すべての要素を適切に整えるのに役立つ情報を以下に示します。 以下は、*モデルのトレーニング*を構成する内容の概要です。

## モデルトレーニング

優れた判断リストと優れた機能セットがLearning to Rankアルゴリズムに供給されると、有用なトレーニング済みモデルが生成されます（これはパズルの機械学習の部分です）。 したがって、以下のものを組み立てる必要があります。

  - トレーニングモデルの作成に使用するLearning to Rankアルゴリズム。 このデモンストレーションでは、[RankLib](https://sourceforge.net/p/lemur/wiki/RankLib/)を使用します。

  - 検索結果の等級付けされたリストを含む*判断リスト*。 アルゴリズムは、判断リストの順序に従ったモデルを生成します。

  - Learning to Rankアルゴリズムに渡すすべての*機能*を含む機能セット。判断リストと組み合わせて使用し、信頼できるモデルを作成します。 この例では、Liferayの機能セットの例を示します。

[判断リスト](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/core-concepts.html#judgments-expression-of-the-ideal-ordering)は、等級付けされた検索結果のリストです。

[機能](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/core-concepts.html#features-the-raw-material-of-relevance)は、アルゴリズムが結果をよりスマートな方法でスコアリングできる関数を作成するために使用する変数です。 関連する機能を十分に、あるいは正しく与えなければ、モデルは結果を改善するのに十分な「賢さ」を持ちません。

始める前に、Liferayと通信するリモート[Elasticsearch](../installing-and-upgrading-a-search-engine/elasticsearch.html)クラスターが必要です。 詳細については、[検索エンジンの互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360016511651)を参照してください。

```{tip}
Use [Suggestions](../search-pages-and-widgets/search-results/enabling-search-suggestions.md)_ to discover the most common queries (this can be one way to decide which queries to create Learning to Rank models for).
```

## ステップ1：ElasticsearchにLearning to Rankプラグインをインストールする

Learning to Rankプラグインのインストールについては、[Elasticsearch Learning to Rankプラグインのドキュメント](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/#installing)を参照してください。

インストールするプラグインのバージョンに応じて、次のようなコマンドを実行します。

``` bash
./bin/elasticsearch-plugin install http://es-learn-to-rank.labs.o19s.com/ltr-plugin-v1.5.3-es7.9.3.zip
```

[ElasticsearchクラスターでX-Pack Security](../installing-and-upgrading-a-search-engine/elasticsearch/securing-elasticsearch.md)を使用している場合は、[追加の手順が必要になる場合があります。](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/x-pack.html)

## ステップ2：モデルのトレーニングとアップロード

モデルのトレーニングに関する詳細な手順は、このガイドの範囲外です。 トレーニングには、適切なツールとモデルを推奨できるデータサイエンティストの介入が必要です。 自分に合ったものを使用してください。 そうすることで、選択したトレーニングツールで使用できる[判断リスト](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/core-concepts.html#judgments-expression-of-the-ideal-ordering)と[機能セット](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/building-features.html)をコンパイルして、ほぼ確実に適切な検索結果を生成するモデルを生成できます。 モデルを作成したら、それをLearning to Rankプラグインにアップロードします。

## ステップ3：モデルをLearning to Rankプラグインにアップロードする

`POST`リクエストを使用してモデルをアップロードしますが、最初に`_ltr`インデックスと機能セットがLearning to Rankプラグインにアップロードされていることを確認する必要があります。 （[LESモニタリングウィジェット](./monitoring-elasticsearch.md)から）Kibanaを使用して、これらのタスクを簡単にします。

1.  `_ltr`インデックスがまだない場合は、作成してください。

    ``` json
    PUT _ltr
    ```

2.  `_ltr`インデックスに機能セットを追加します。 この例では、セットは`liferay`と呼ばれています。

    ``` json
    POST _ltr/_featureset/liferay
    {
      "featureset": {
        "name": "liferay",
        "features": [
          {
            "name": "title",
            "params": [
              "keywords"
            ],
            "template": {
              "match": {
                "title_en_US": "{{keywords}}"
              }
            }
          },
          {
            "name": "content",
            "params": [
              "keywords"
            ],
            "template": {
              "match": {
                "content_en_US": "{{keywords}}"
              }
            }
          },
          {
            "name": "asset tags",
            "params": [
              "keywords"
            ],
            "template": {
              "match": {
                "assetTagNames": "{{keywords}}"
              }
            }
          }
        ]
      }
    }
    ```

    ここで使われている構文は必須ですので、注意してください。

3.  トレーニング済みモデルを機能セットに追加します。

    ``` json
    POST _ltr/_featureset/liferay/_createmodel
    {
      "model": {
        "name": "linearregression",
        "model": {
          "type": "model/ranklib",
          "definition": """
    ## Linear Regression
    ## Lambda = 1.0E-10
    0:-0.717621803830712 1:-0.717621803830712 2:-2.235841905601106 3:19.546816765721594
    """
        }
      }
    }
    ```

Liferay自体で行うことはあまりないため、この一連の指示は非常に高レベルです。 必要なものについての詳細は、[Learning to Rankプラグインのドキュメント](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/index.html)を参照してください。

```{tip}
Keep reworking those judgment lists!
```

## ステップ4：Learning to Rankを有効にする

[コントロールパネル] → [設定] → [システム設定] →[検索機能] → [Learning to Rank]からLearning to Rankを有効にします。 単純なオン/オフ構成と、検索クエリに適用するためにトレーニング済みのモデルの名前を入力する必要があるテキストフィールドがあります。

前のステップでモデルの名前は`linearregression`だったので、それを入力します。

![システム設定エントリからLiferayでLearning to Rankを有効にします。](./learning-to-rank/images/01.png)

トレーニング済みモデル、機能セット、Liferayからの検索クエリを使用してElasticsearch Learning to Rankプラグインを取得するために必要な構成はこれですべてです。
