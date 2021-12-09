# ステージングの概要

```{toctree}
:maxdepth: 3

staging/understanding-the-publishing-process.md
staging/configuring-local-live-staging.md
staging/configuring-remote-live-staging.md
staging/managing-data-and-content-types-in-staging.md
staging/page-versioning.md
staging/publishing-single-assets-and-widgets.md
staging/managing-staging-permissions.md
staging/using-staging-in-asset-libraries.md
staging/site-staging-ui-reference.md
```

Liferay DXPの*ステージング*ツールを使用して、*ステージング環境*の背後でサイトに変更を加えてから、ローカルまたはリモートの*本番環境*に変更を公開し、本番環境でユーザーの着信トラフィックを処理できます。

ステージング設定の一部として、環境を同じサーバーでホストするか、ネットワークで接続された別々のサーバーでホストするかを決定します。 また、ページバージョニングを有効にして、ステージングするサイトコンテンツとアプリケーションデータをカスタマイズすることもできます。

## 設定オプション

サイトでステージングを設定するには、 *[ローカル現行環境ステージング]* と*[リモート本番環境ステージング]* の2つのオプションがあります。

**ローカル現行環境ステージング**：ステージング環境と本番環境の両方を同じLiferayサーバーでホストできます。 有効にすると、Liferay DXPは、ステージング環境として機能するサイトのローカルクローンを作成し、元のDXPインスタンスが本番環境になります。

**リモート本番環境ステージング**：ステージング環境と本番環境を別々のLiferayサーバーでホストできます。 有効にすると、ステージングの構成に使用されるサイトがステージング環境になり、構成されたリモートサーバーが本番環境になります。

どちらのオプションも、ステージングされたページ、アプリケーション、およびコンテンツの管理と公開に同じインターフェイスを使用しますが、セットアップが異なります。 ニーズに最適な設定オプションを選択したら、[Configuring Local Live Staging](./staging/configuring-local-live-staging.md)または [Configuring Remote Live Staging](./staging/configuring-remote-live-staging.md)を参照してセットアップ手順を確認してください。

### ページバージョニング

ステージングの一部として、*ページバージョニング*機能を有効にできます。 この機能を使用すると、あなたとあなたのチームは、プライベートページとパブリックページの両方のバリエーションを同時に開発して作業することができます。 ページバージョンの完全な履歴を確認し、必要に応じてページを以前のバージョンに戻すことができます。 バリエーションの作成と管理の方法については[Page Versioning](./staging/page-versioning.md)を、各バリエーションの権限を管理する方法については[ステージング権限の管理](./staging/managing-staging-permissions.md)を参照してください。

```{note}
Page versioning is only supported for Widget Pages.
```

### ステージングされたデータとコンテンツタイプ

ステージング設定の一部として、ステージングするコンテンツグループとアプリケーションデータを選択できます。 ステージングされると、選択されたデータとコンテンツはステージング環境によって管理されます。 また、アプリケーションまたはコンテンツグループを選択すると、含まれているすべてのエンティティ（アプリケーションフォルダなど）もステージングされます。 詳細は、[ステージングでのデータとコンテンツタイプの管理](./staging/managing-data-and-content-types-in-staging.md)を参照してください。

## 公開プロセスを理解する

下位の観点からは、ステージングは、エンティティが別の場所にミラーリングされる同値関係です。 高度な観点からは、ステージングプロセスは、エクスポート、検証、およびインポートの3つの連続したフェーズで実行されます。

このプロセスを理解すると、効率が向上し、シームレスな公開エクスペリエンスを実現するための事前の計画に役立ちます。 詳細は、[公開プロセスを理解する](./staging/understanding-the-publishing-process.md)を参照してください。

```{raw} html
:file: ../../../landingpage_template.html
```

```{raw} html
:file: staging/landing.html
```
