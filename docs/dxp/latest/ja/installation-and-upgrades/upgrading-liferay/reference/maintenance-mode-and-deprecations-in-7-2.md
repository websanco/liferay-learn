# 7.2のメンテナンスモードと非推奨

新しいDXPのリリースごとに、機能の継続的な拡張が保証されない場合や、廃止される可能性があります。 機能の拡張を停止すると、その機能は*メンテナンスモード*に入ります。 旧式の機能は*廃止予定*となります。

廃止予定のアプリには次の3種類があります。

1.  Liferayに残っているが、将来のリリースで削除される予定の廃止予定アプリ。 （利用可能性： *バンドル*）

2.  Liferayから削除されたが、[Liferay マーケットプレイス](https://web.liferay.com/marketplace)からダウンロードできる廃止予定アプリ（利用可能性： *マーケットプレイス*）

3.  Liferayから削除され、ダウンロードできない廃止予定アプリ。 （利用可能性： *削除*）

<!-- end list -->

```{note}
Liferayで廃止予定となったすべてのアプリケーションは、積極的な開発段階にはありません。 したがって、これらのアプリの使用を停止することを検討する必要があります。
```

Liferay 7.0以前からアップグレードする場合は、該当するリリースで発生した廃止予定事項を調べてください。

  - [ 7.1](https://help.liferay.com/hc/en-us/articles/360018403151-Digital-Experience-Platform-7-1-Deprecated-and-Removed-Items)
  - [ 7.0](https://help.liferay.com/hc/en-us/articles/360018123832-Digital-Experience-Platform-7-0-Deprecated-and-Removed-Items)

## 7.2で廃止予定の機能

7.2で廃止予定の機能は次のとおりです。

| 機能                        | 利用可能性     | メモ                                                                                                                                                                                                                                                                   |
| ------------------------- | --------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| AlloyUI                   | バンドル      | [ClayUIタグ](https://clayui.com/)に相当するものとして公開されている[MetalJS](https://metaljs.com/)（仮）に置き換えられました。                                                                                                                                                                        |
| オーディエンスターゲティング            | 削除        | [パーソナライゼーション](../../../site-building/personalizing-site-experience/personalizing-site-experience.html)に置き換えられました（[Migrating from Audience Targeting](https://help.liferay.com/hc/en-us/articles/360028711992-Manually-Migrating-from-Audience-Targeting)を参照）。        |
| 中央認証サービス（CAS）             | バンドル      | [ SAMLベースの認証](../../securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md)に移行。                                                                                                                                                       |
| CMISストア                   | 削除        | 別の[ファイルストアオプション](../../../system-administration/file-storage.md)に移行。 [Liferay 7.2にアップグレード](../upgrade-basics.md)する前に、[ファイルストアデータを移行してください](../../../system-administration/file-storage/file-store-migration.md)。           |
| Google ログイン               | マーケットプレース | [OpenID Connect](../../securing-liferay/configuring-sso/using-openid-connect.md)に置き換えられました。                                                                                                                                                               |
| JCRStore                  | 削除        | 別の[ファイルストアオプション](../../../system-administration/file-storage.md)に移行。 [Liferay 7.2にアップグレード](../upgrade-basics.md)する前に、[ファイルストアデータを移行してください](../../../system-administration/file-storage/file-store-migration.md)。           |
| Liferayモバイルデバイス検出エンタープライズ | 削除        | 最新の定義については、51Degreesにお問い合わせください                                                                                                                                                                                                                                      |
| ライブユーザー                   | バンドル      | [`live.users.enabled`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html)[ポータルプロパティ](../../reference/portal-properties.md)を通じて有効化されます。                                                                                             |
| NTLM                      | 削除        | NTLMにはセキュリティの脆弱性が残ります。 [Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md)に置き換えられました。 NTLMを使用してMicrosoft Windows™アカウントを認証している場合は、[Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md)を使用するように切り替えてください。 |
| OAuth 1.0a                | マーケットプレース | バンドルに含まれているOAuth 2.0に置き換えられました。                                                                                                                                                                                                                                      |
| OpenAM/OpenSSO            | バンドル      | [ SAMLベースの認証](../../securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md)に移行。                                                                                                                             |
| OpenID                    | マーケットプレース | [OpenID Connect](../../securing-liferay/configuring-sso/using-openid-connect.md)に置き換えられました。                                                                                                                                                               |
| リソースインポーター                | バンドル      | 7.1で廃止、直接の置き換えなし                                                                                                                                                                                                                                                     |
| RSSパブリッシャー                | バンドル      | このウィジェットの有効化と使用に関する[記事](https://help.liferay.com/hc/en-us/articles/360028820672-The-RSS-Publisher-Widget)を参照してください。                                                                                                                                                  |
| *ツール*カテゴリの検索機能ウィジェット（標準）  | バンドル      | 7.1で廃止。 *検索機能*カテゴリの[検索機能ウィジェット](../../../using-search/search-pages-and-widgets/search-results/search-results.md)に置き換えられました。                                                                                                                                          |
| スプライトフレームワーク              | バンドル      | Liferayの画像スプライトフレームワークは廃止され、`sprite.enabled`[ポータルプロパティ](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html)を介してデフォルトで無効になっています。 引き続き好きなフレームワークを使用して画像スプライトを作成し、プラグインにデプロイできます。                                          |
| ユーザーグループページ（コピーモード）       | バンドル      | 有効にする方法については、[レガシーユーザーグループサイトの動作](../../../users-and-permissions/user-groups/user-group-sites.md)の説明を参照してください。                                                                                                                                                       |
| Webフォーム                   | 削除        | 7.0用にリリースされた最終バージョン。                                                                                                                                                                                                                                                 |

## 7.2でメンテナンスモードに移行された機能

7.2でメンテナンスモードに移行された機能は次のとおりです。

  - Liferay Connected Services（LCS）
  - Liferayモバイルエクスペリエンス（Liferay Screens、Liferay Mobile SDK、Liferay Push）
  - Liferay Sync
  - ステージング

## 追加情報

  - [7.2 Breaking Changes](../../../liferay-internals/reference/7-2-breaking-changes.md)
  - [7.2 Default Setting Changes](./default-setting-changes-in-7-2.md)
