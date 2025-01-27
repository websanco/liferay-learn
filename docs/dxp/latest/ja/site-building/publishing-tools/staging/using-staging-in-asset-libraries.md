# アセットライブラリでのステージングの使用

> Liferay DXP 7.4および7.3.10 FP1から、アセットライブラリでステージングがサポートされています。

Liferayのステージングアプリケーションは、DXPでの変更を管理するための公開設定ツールです。 アセットライブラリの場合、ステージングは、変更を公開する前に、Webコンテンツまたはドキュメントとメディアアプリケーションでアセットを追加、削除、および編集するための作業環境を提供します。 メニューページからアセットライブラリのステージングアプリケーションにアクセスできます。

![アセットライブラリのメニューページからステージングにアクセスします。](./using-staging-in-asset-libraries/images/01.png)

ここから、サイトの場合と同じプロセスに従って、個々のアセットライブラリの[ローカル現行環境](./configuring-local-live-staging.md)ステージングまたは[リモート本番環境](./configuring-remote-live-staging.md)ステージングを有効にできます。 唯一の違いは、ステージングされたコンテンツオプションが、アセットライブラリで有効になっているアプリケーションに限定されることです。

```{important}
ステージングが有効になっているアセットライブラリは、ステージングが有効になっているサイトとのみリンクできます。 アセットライブラリでステージングが有効になっている場合、サイトでもステージングが有効になっていないと、既存のサイト接続が自動的に削除されます。 
```

アセットライブラリのステージングを有効にすると、ページ上部のステージングバーを使用して本番環境とステージング環境を切り替えることができます。 ステージング環境では、 ［**Publish to Live**］ にアクセスして、新しい公開設定プロセスを開始することもできます。

![アセットライブラリに移動して、ステージングバーにアクセスします。](./using-staging-in-asset-libraries/images/02.png)

多くの編集オプションは、ステージング環境でのみアクセスできます。 これは、明確な開発プロセスを維持し、本番環境での編集によるデータの競合を回避するためです。

ステージング環境で変更が行われると、それらは一度にすべて、または一度に1つずつ公開できます。 [サイト](./site-staging-ui-reference.md#publish-to-live) と同様に、 [シンプル](./site-staging-ui-reference.md#simple-publishing) または [高度な](./site-staging-ui-reference.md#advanced-publishing) 公開設定オプションを使用できます。

<a name="additional-information" />

## 追加情報

* [ステージングの概要](../staging.md)
* [アセットライブラリの概要](../../../content-authoring-and-management/asset-libraries/asset-libraries-overview.md)
* [ローカル現行環境ステージングの設定](./configuring-local-live-staging.md)
* [リモート本番環境ステージングの設定](./configuring-remote-live-staging.md)
