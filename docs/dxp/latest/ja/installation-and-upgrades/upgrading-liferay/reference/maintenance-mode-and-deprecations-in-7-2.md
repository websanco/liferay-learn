# 7.2のメンテナンスモードと非推奨

新しいDXPのリリースごとに、機能の継続的な拡張が保証されない場合や、廃止される可能性があります。 機能の拡張を停止すると、その機能は **メンテナンスモード** に入ります。

**メンテナンスモード：** この機能はサポートされていますが、拡張は行われません。

廃止された機能は最終的に削除されるか、より新しい、より優れた機能に置き換えられます。 廃止された機能のサポートを終了する前に、Liferayはそれを **廃止予定** とマークします。

**廃止予定：** この機能は、次のマイナーバージョンのリリースでサポートされなくなります。 たとえば、7.2で廃止予定になった機能のサポートは、早ければ7.3で削除される可能性があります。

```{important}
   廃止予定の機能のサポートは、早ければ次のマイナーリリースで終了する可能性があるため、機能の使用を停止することを計画してください。
```

```{important}
   詳細は、 [Maintenance Mode and Deprecation Policies](https://help.liferay.com/hc/en-us/articles/360015767952-Maintenance-Mode-and-Deprecation) を参照してください。
```

<a name="deprecated-feature-availability" />

## 廃止予定の機能の利用可能性

廃止予定の機能には、さまざまな利用可能性があります。

**バンドル：** この機能はLiferay製品に含まれています。

**マーケットプレイス：** この機能はマーケットプレイスアプリの最終バージョンに含まれています。

**最終版：** このLiferayバージョン用にマーケットプレイスアプリの最終版がリリースされています。

**アーカイブ：** この機能は利用できませんが、そのコードはLiferayのリポジトリ（ <https://repository.liferay.com/nexus/index.html#welcome>）のソースアーティファクトで利用できます。

**削除：** この機能とそのコードは使用できません。

Liferay 7.0以前からアップグレードする場合は、該当するリリースで発生した廃止予定事項を調べてください。

* [ 7.1](https://help.liferay.com/hc/en-us/articles/360018403151-Digital-Experience-Platform-7-1-Deprecated-and-Removed-Items)
* [ 7.0](https://help.liferay.com/hc/en-us/articles/360018123832-Digital-Experience-Platform-7-0-Deprecated-and-Removed-Items)

<a name="features-deprecated-in-72" />

## 7.2で廃止予定の機能

7.2で廃止予定の機能は次のとおりです。


| 機能                                   | 利用可能性     | メモ                                                                                                                                                                                                                                                                   |
| ------------------------------------ | --------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| AlloyUI                              | バンドル      | [ClayUIタグ](https://clayui.com/) に相当するものとして公開されている [MetalJS](https://metaljs.com/) （仮）に置き換えられました。                                                                                                                                                                        |
| オーディエンスターゲティング                       | 削除        | [パーソナライゼーション](../../../site-building/personalizing-site-experience/personalizing-site-experience.html) に置き換えられました（ [Migrating from Audience Targeting](https://help.liferay.com/hc/en-us/articles/360028711992-Manually-Migrating-from-Audience-Targeting) を参照）。        |
| :--- | :--- | :--- |
| 中央認証サービス（CAS）                        | バンドル      | [ SAMLベースの認証](../../securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md)に移行。                                                                                                                                                       |
| CMISストア                              | 削除        | 別の[ファイルストアオプション](../../../system-administration/file-storage/configuring-file-storage.md)に移行。 [Liferay 7.2にアップグレード](../upgrade-basics/upgrade-overview.md)する前に、[ファイルストアデータを移行してください](../../../system-administration/file-storage/file-store-migration.md)。           |
| Google ログイン                          | マーケットプレース | [OpenID Connect](../../securing-liferay/configuring-sso/other-ssos/using-openid-connect.md)に置き換えられました。                                                                                                                                                               |
| JCRStore                             | 削除        | 別の[ファイルストアオプション](../../../system-administration/file-storage/configuring-file-storage.md)に移行。 [Liferay 7.2にアップグレード](../upgrade-basics/upgrade-overview.md)する前に、[ファイルストアデータを移行してください](../../../system-administration/file-storage/file-store-migration.md)。           |
| Liferay Mobile Device Detection Lite | 最終版       | 直接の置換はありません。                                                                                                                                                                                                                                                         |
| Liferayモバイルデバイス検出エンタープライズ            | 削除しました    | 最新の定義については、51Degreesにお問い合わせください                                                                                                                                                                                                                                      |
| ライブユーザー                              | バンドル      | [`live.users.enabled`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) [ポータルプロパティ](../../reference/portal-properties.md) を通じて有効化されます。                                                                                             |
| NTLM                                 | 削除しました    | NTLMにはセキュリティの脆弱性が残ります。 [Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md)に置き換えられました。 NTLMを使用してMicrosoft Windows™アカウントを認証している場合は、[Kerberos](../../securing-liferay/configuring-sso/authenticating-with-kerberos.md)を使用するように切り替えてください。 |
| OAuth 1.0a                           | マーケットプレース | バンドルに含まれているOAuth 2.0に置き換えられました。                                                                                                                                                                                                                                      |
| OpenAM/OpenSSO                       | バンドル      | [ SAMLベースの認証](../../installation-and-upgrades/securing-liferay/configuring-sso/authenticating-with-saml/single-sign-on-with-saml.md)に移行。                                                                                                                             |
| OpenID                               | マーケットプレース | [OpenID Connect](../../securing-liferay/configuring-sso/other-ssos/using-openid-connect.md)に置き換えられました。                                                                                                                                                               |
| リソースインポーター                           | バンドル      | 7.1で非推奨、直接の置き換えなし                                                                                                                                                                                                                                                    |
| RSSパブリッシャー                           | バンドル      | このウィジェットの有効化と使用に関する [記事](https://help.liferay.com/hc/en-us/articles/360028820672-The-RSS-Publisher-Widget) を参照してください。                                                                                                                                                  |
| **ツール** カテゴリの検索ウィジェット（クラシック）           | バンドル      | 7.1で廃止。 **検索** カテゴリの[検索ウィジェット](../../../using-search/search-pages-and-widgets/search-results/search-results.md)に置き換えられました。                                                                                                                                              |
| スプライトフレームワーク                         | バンドル      | Liferayの画像スプライトフレームワークは非推奨になり、`sprite.enabled` [ポータルプロパティ](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/reference/portal-properties.html) を介してデフォルトで無効になっています。 引き続き好きなフレームワークを使用して画像スプライトを作成し、プラグインにデプロイできます。                                     |
| テーマ：Fjord                            | 最終版       | 直接の置換はありません。                                                                                                                                                                                                                                                         |
| テーマ：Porygon                          | 最終版       | 直接の置換はありません。                                                                                                                                                                                                                                                         |
| テーマ：Westeros                         | 最終版       | 直接の置換はありません。                                                                                                                                                                                                                                                         |
| ユーザーグループページ（コピーモード）                  | バンドル      | 有効にする方法については、[レガシーユーザーグループサイトの動作](../../../users-and-permissions/user-groups/user-group-sites.md)の説明を参照してください。                                                                                                                                                       |
| Webフォーム                              | 削除しました    | 7.0用にリリースされた最終バージョン。                                                                                                                                                                                                                                                 |

<a name="features-moved-to-maintenance-mode-in-72" />

## 7.2でメンテナンスモードに移行された機能

7.2でメンテナンスモードに移行された機能は次のとおりです。

* Liferay Connected Services（LCS）
* Liferayモバイルエクスペリエンス（Liferay Screens、Liferay Mobile SDK、Liferay Push）
* Liferay Sync
* ステージング

<a name="additional-information" />

## 追加情報

* [破壊的変更](../../../liferay-internals/reference/7-2-breaking-changes.md)
* [7.2 Default Setting Changes](./default-setting-changes-in-7-2.md)