# DXPのパッチコードへのアクセス

Liferay DXPフィックスパックとホットフィックスのソースコードは、`liferay-dxp`と呼ばれるプライベートGitHubリポジトリで入手できます。 ソースコードは、プロジェクトやカスタムコードに影響を与える可能性のある変更を特定し、理解するのに役立ちます。 フィックスパックとホットフィックスには、GitHubでアクセスできるタグがあります。

![フィックスパックとホットフィックスのタグ。](./accessing-dxp-patch-code/images/01.png)

```{note}
   フィックスパックは、それぞれのDXPバージョンブランチ（つまり、`` 7.0.x``、`` 7.1.x``など）でタグ付けされています。 ホットフィックスには、ブランチヘッドでタグ付けされた独自のブランチがあります。 ホットフィックスブランチヘッドとホットフィックスタグのコードは同じであるため、ブランチまたはタグを取得すると同じコードにアクセスします。
```

このコードでできることは次のとおりです。

* 差分から変更されたファイルを調べる
* 異なるパッチのコードを比較する
* パッチの実装を表示する
* コードをマシンにダウンロードまたは複製する

次の図は、2つのホットフィックス間のファイルの差分を示しています。

![タグの比較。](./accessing-dxp-patch-code/images/02.png)

```{note}
   Liferay DXP 7.0と7.1のパッチソースコードのタグ付きブランチは、次のフィックスパックのベースラインから入手できます。

   * Liferay DXP 7.1フィックスパック19
   * Liferay DXP 7.0フィックスパック96
```

Liferay DXPソースコードのカスタマイズはサポートされていないため、ビルドファイルはソースコードから除外されます。 Liferay DXPの拡張ポイントは、サポートされている唯一のカスタマイズ方法です。

* 新しい拡張ポイントまたは新しい機能が必要な場合は、 [JIRA](https://issues.liferay.com) で機能リクエストを送信してください。
* Liferay DXPのカスタマイズや機能リクエストについてサポートが必要な場合は、アカウントエグゼクティブまたはカスタマーエクスペリエンスマネージャーにお問い合わせください。

```{note}
   以前のパッチツールのソースモードコマンド「diff」および「store」は、DXP 7.3以降では使用できません。 それらは置き換えられることなく削除されました。 以前のDXPバージョンでDXPソースコードにパッチを適用していて、DXP 7.3以降にアップグレードする場合は、新しいDXPソースコードを調べてそれに適応してください。
```

<a name="requesting-repository-access" />

## リポジトリアクセスのリクエスト

<https://github.com/liferay/liferay-dxp>のGitHubリポジトリにあるDXPソースコードは非公開であり、サブスクライバー専用です。 リポジトリへのアクセスをリクエストする方法は次のとおりです。

1. ヘルプセンターで、 [［Projects］](https://customer.liferay.com/project-details?_ga=2.57624622.528260345.1619731014-1356934316.1588162379) ページに移動します。
1. DXPプロジェクトを選択します。
1. ［**Source Code Access**］ タブを選択します。
1. 「+」ボタンをクリックします。 ソースコードへのアクセスフォームが表示されます。
1. 要求された情報を入力します。必ずその人の姓名を含めてください。
1. ［**送信**］ をクリックします 。

```{note}
   他のプロジェクトチームメンバーに代わってアクセスリクエストを送信できます。
```

リクエストが承認されると、リポジトリアクセスの招待メールが届きます。 招待状を紛失した場合は、GitHub（<https://github.com/liferay/liferay-dxp/invitations>）からアクセスしてください。

```{note}
   リクエストは、受信した順にできるだけ早く処理されます。 リクエスト数が多い場合、すぐに処理できない場合があります。
```

リポジトリアクセスが承認されると、 **読み取り** レベルの権限が与えられます。 実行できるアクションは次のとおりです。

* リポジトリからプル
* リポジトリをフォークする
* 公開されたリリースを表示する

アクセスは、Liferay DXPサブスクリプションの期間中維持されます。

```{warning}
   招待状は7日間有効であり、チームメンバーが［プロジェクトの詳細］ページに表示されなくなった場合でも受け入れることができます。 たとえば、誰かに属しているユーザーのGitHubユーザー名が間違って入力された場合、そのユーザーは一時的にリポジトリにアクセスできます。 これを防ぐために、システムはアクティブなLiferayプロジェクトに属していない新しいリポジトリコラボレーターを毎週チェックし、それらのアクセスを自動的に取り消します。
```

```{important}
   GitHubのユーザー名を変更すると、リポジトリへのアクセスが無効になります。 ユーザー名を変更する場合は、ユーザーのアクセスを削除してから<#removing-access>`_、新しいGitHubユーザー名を使用してアクセスをリクエストしてください。
```

<a name="removing-access" />

## アクセスの削除

GitHubユーザーのリポジトリアクセスを削除する方法は次のとおりです。

1. [ヘルプセンター](https://help.liferay.com/hc/ja/) で、
［Projects］ページに移動します。</li> 
   
   1 DXPプロジェクトを選択します。
1 ［**Source Code Access**］ タブを選択します。
1 ユーザーのGitHub情報を展開します。
1 ［Delete］</em> をクリックします。</ol> 

ユーザーのリポジトリアクセスが削除されます。



<a name="privacy" />

## プライバシー

承認されたすべてのサブスクライバーが同じリポジトリにアクセスしても、GitHubユーザーアカウントとリポジトリフォークはデフォルトで非公開のままです。 ただし、一部のアクションは他のリポジトリコラボレーターに表示されます。 次のアクションを回避し、代わりに推奨される代替チャネルを使用することで、プライバシーを維持できます。

| 回避する行動             | 代替チャネル |
|:------------------ |:------ |
| 自分のコメントの編集と削除      | (1)    |
| 問題のオープン            | (1)    |
| 開いた問題のクローズ         | (1)    |
| クローズした問題の再オープン     | (1)    |
| 問題の割り当て            | (1)    |
| フォークからプルリクエストを送信する | (2)    |
| プルリクエストレビューの送信     | (2)    |
| wikiの編集            | (3)    |


（**1）問題とコメントについて：** ソースコードリポジトリで発生した問題をクローズし、未回答のままにします。 Liferayソフトウェアに問題がある場合は、ヘルプセンターで [サポートチケットを作成](https://help.liferay.com/hc/ja/requests/new) してLiferayサポートチームにお知らせください。 

（**2）コード投稿について：** パブリックLiferay Portalリポジトリ（<https://github.com/liferay/liferay-portal>）でコードを投稿できます。 承認された投稿は、次のLiferay DXPリリースに含まれます。 詳細については、 [Contributing to Liferay Development](../../../liferay-internals/contributing_to_liferay_development.html) （近日公開）をご覧ください。

（**3）Wikiについて：** Liferay DXPの公式ドキュメンテーションは<https://learn.liferay.com>にあり、サポートナレッジベースは [ヘルプセンター](https://help.liferay.com/hc/ja/) にあります。 `liferay-dxp`リポジトリのwikiは使用していません。  記事の変更を提案したり、トピックに関するドキュメントをリクエストする場合は、ヘルプセンターで [サポートチケットを作成](https://help.liferay.com/hc/ja/requests/new) してLiferayサポートチームにお知らせください。



<a name="additional-information" />

## 追加情報

* [About Open Source at Liferay](https://liferay.dev/open-source)
* [Getting Started with GitHub and Git](https://help.github.com/en/github/getting-started-with-github)
* [Searching for Information on GitHub](https://help.github.com/en/github/searching-for-information-on-github)
* [Cloning a Repository on GitHub](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository-from-github)
* [Working with Forks](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/working-with-forks)
* [Forking Projects](https://guides.github.com/activities/forking/)