# ポータル開発者プロパティ

開発を促進する[ポータルプロパティ](../../installation-and-upgrades/reference/portal-properties.md)があります。 Liferayの[`portal-developer.properties`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-impl/src/portal-developer.properties)ファイルには、それらすべてが含まれています。 `portal-developer.properties`ファイルは、この設定を`portal-developer.properties`ファイルの先頭に追加することで有効になります。

``` properties
include-and-override=portal-developer.properties
```

## 開発者設定

| 開発者設定                                                         | 説明                                                                                                                                                                    |
|:------------------------------------------------------------- |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| schema.module.build.auto.upgrade=true                         | 前回のデプロイメントからモジュールのビルド番号が増加した場合、自動的にデータベースをアップグレードします。                                                                                                                 |
| upgrade.database.auto.run=true                                | ポータルが起動し、モジュールが有効になった時点でアップグレード処理を実行します。                                                                                                                              |
| theme.css.fast.load=false                                     | デバッグを容易にするために、テーマのCSSファイルのマージを無効にします。                                                                                                                                 |
| theme.images.fast.load=false                                  | デバッグを容易にするために、テーマの画像ファイルのマージを無効にします。                                                                                                                                  |
| javascript.fast.load=true                                     | OSGiバンドルのマニフェストファイルのプロパティ`Liferay-JS-Resources-Top-Head`および/または`Liferay-JS-Resources-Top-Head-Authenticated`にリストされているパックされたバージョンのファイルの読み込みを無効にします。                    |
| javascript.log.enabled=false                                  | JavaScriptのログの表示を無効にします。                                                                                                                                              |
| layout.template.cache.enabled=false                           | レイアウトテンプレートのコンテンツのキャッシュを無効にします。                                                                                                                                       |
| combo.check.timestamp=true                                    | コンボサーブレットを無効にすることで、デバッグを容易にします。 詳細は、[`combo.check.timestamp`](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Combo)の定義を参照してください。 |
| json.service.serialize.throwable=true                         | JSON レスポンスに含まれるサーバーエラーの情報を返します。                                                                                                                                       |
| minifier.enabled=false                                        | CSSとJavaScriptのリソースの最小化を有効にします。                                                                                                                                       |
| module.framework.properties.initial.system.check.enabled=true | サーバ起動時にモジュールをチェックします。                                                                                                                                                 |
| module.framework.properties.osgi.console=localhost:11311      | モジュールのデバッグ用のコンソールアクセスを有効にします。                                                                                                                                         |
| work.dir.override.enabled=true                                | Liferayのワークディレクトリを使用して、デプロイされたOSGiバンドル内のJSPファイルを上書きできるようになります。                                                                                                        |

## 追加情報

  - [Portal Properties](../../installation-and-upgrades/reference/portal-properties.md)
