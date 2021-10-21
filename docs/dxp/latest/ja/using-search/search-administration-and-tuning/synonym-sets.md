# 同義語セット

> **サブスクライバー**

同義語セットは、管理者が作成した同じ意味を持つ単語またはフレーズのグループです。 ユーザーが特定のキーワードまたはフレーズを検索すると、セット内の同義語も検索されます。

たとえば、ユーザーが「US」という単語を検索するとします。 ほとんどの場合、ユーザーは*America*、*U.S.A*、*United States*などの同義語も含む検索結果を求めています。 同義語セットを作成することで、ユーザーが検索を最大限に活用できるようになります。

## 要件と制限事項

同義語セットは、Elasticsearchを検索エンジンとして使用している場合にのみサポートされます。 Elasticsearchのインストールについては、[Getting Started with Elasticsearch](../installing-and-upgrading-a-search-engine/elasticsearch/getting-started-with-elasticsearch.md)を参照してください。

同義語セットは現在、すぐに使用可能なロケール（英語またはスペイン語）のいずれかでインデックス付けされたフィールドで機能します。 これら2つの言語のいずれかでローカライズ可能なフィールドを持つLiferayアセットは、同義語セットで機能します。

Elasticsearchでサポートされている[`=>`形式](https://www.elastic.co/guide/en/elasticsearch/guide/current/synonyms.html)は同義語セットUIではサポートされていません。

## 同義語セットの作成と管理

必要な数の同義語キーワードをセットに追加して、同義語セットを作成します。 同義語セットが保存されると、同じ会社スコープ（同義語が設定された[仮想インスタンス](../../system-administration/configuring-liferay/virtual-instances/understanding-virtual-instances.md)の任意のサイト）での検索が有効になります。

新しい同義語セットを作成するには、

1.  グローバルメニュー（![Global Menu](../../images/icon-applications-menu.png)）から、 *[Applications]* → *[検索の調整]* → *[同義語]* に移動します。

    ![グローバルメニューの[同義語]セクションに移動します](synonym-sets/images/01.png)

2.  *追加* アイコン（![Click on the add icon](../../images/icon-add.png)）をクリックして、新規同義語セットを追加します。

3.  セット内の同義語のリストを入力します。 同義語の入力は、*Enter*をクリックするか、カンマを入力することで行います。

    ![異なる同義語をセットに入力します。](synonym-sets/images/02.png)

4.  同義語の横にある*[X]* をクリックすると、同義語を削除できます。 セットが終わったら、*[公開]* をクリックします。

5.  セットを編集または削除するには、*オプション*アイコン（![Click on the options icon.](../../images/icon-options.png)）をクリックし、*[Edit]* または*[Delete]* をクリックします。

    ![編集または削除をクリックして変更を加えます。](synonym-sets/images/03.png)

    同義語セットが公開されると、使用できるようになります。

## 同義語セットの使用

セットに保存した同義語キーワードの1つを検索することで、同義語セットをテストできます。 キーワードと同義語に一致する結果が検索結果ウィジェットに返されます。

![セットから同義語を検索してみてください。](synonym-sets/images/04.png)

上記の例では、月面車に関するこのブログ記事には「LRV」という単語は含まれていませんが、検索結果の一致として返されるようになりました。 同義語が強調表示されていることにも注目してください。
