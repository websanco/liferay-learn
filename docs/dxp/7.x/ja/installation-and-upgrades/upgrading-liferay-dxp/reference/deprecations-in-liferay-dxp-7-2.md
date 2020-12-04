# Liferay DXP 7.2での非推奨項目

Liferay DXPは、新しいバージョンの機能の開発またはアーカイブを停止することがあります。 DXP 7.2では、Liferayはいくつかのアプリと機能を廃止しました。

Liferay DXPで非推奨になった機能は、コア製品または[マーケットプレイスのダウンロード](https://web.liferay.com/marketplace)のいずれかにまだ存在する可能性があります。 非推奨になった機能は、将来のリリースでアーカイブされる可能性があります。 Liferay DXPの新しいバージョンでは、アーカイブされたアプリのメンテナンスやリリースは行われません。

``` note::
   Liferayで非推奨となったすべてのアプリケーションは、積極的な開発段階にはありません。 したがって、これらのアプリケーションの使用を停止することを検討する必要があります。
```

7.1より前のバージョンからアップグレードしている場合は、場合によってLiferay DXPの以前のバージョンで非推奨となった機能も考慮する必要があります。お使いのアップグレード前のバージョンと7.2の間のバージョンについては、次の非推奨項目を参照してください。

  - [Liferay DXP 7.1](https://help.liferay.com/hc/en-us/articles/360018403151-Digital-Experience-Platform-7-1-Deprecated-and-Removed-Items)
  - [Liferay DXP 7.0](https://help.liferay.com/hc/en-us/articles/360018123832-Digital-Experience-Platform-7-0-Deprecated-and-Removed-Items)

DXP 7.2で非推奨となった機能は次のとおりです。

## Foundation

| 機能           | 利用可能性 | メモ                                                                                                                                                                                                                                                                                                           |
| ------------ | ----- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| AlloyUI      | バンドル  | [ClayUIタグ](https://help.liferay.com/hc/en-us/articles/360028832192-Front-End-Taglibs)に相当するものとして公開されている[MetalJS](https://metaljs.com/)（仮）に置き換えられました。                                                                                                                                                          |
| CMIS Store   | アーカイブ | 別の[ドキュメントリポジトリストアオプション](https://help.liferay.com/hc/en-us/articles/360028810112-Document-Repository-Configuration)に移行します。 [DXP 7.2にアップグレード](../upgrade-basics/upgrade-overview.md)する前に、[サーバー管理のデータ移行](https://help.liferay.com/hc/en-us/articles/360029131691-Server-Administration)を使用してドキュメントストアデータを移行します。 |
| JCRStore     | アーカイブ | 別の[ドキュメントリポジトリストアオプション](https://help.liferay.com/hc/en-us/articles/360028810112-Document-Repository-Configuration)に移行します。 [DXP 7.2にアップグレード](../upgrade-basics/upgrade-overview.md)する前に、[サーバー管理のデータ移行](https://help.liferay.com/hc/en-us/articles/360029131691-Server-Administration)を使用してドキュメントストアデータを移行します。 |
| レガシー検索ポートレット | バンドル  | 将来のリリースでアーカイブされます。 これは、[検索ウィジェット](https://help.liferay.com/hc/en-us/articles/360029133791-Introduction-to-Search)に置き換えられる予定です。                                                                                                                                                                               |
| スプライトフレームワーク | バンドル  | Liferayの画像スプライトフレームワークは非推奨になり、`sprite.enabled`[ポータルプロパティ](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html)を介してデフォルトで無効になっています。 引き続き好きなフレームワークを使用して画像スプライトを作成し、プラグインにデプロイできます。                                                                                |

## パーソナライゼーション

| 機能             | 利用可能性 | メモ                                                                                                                                                                                                                                                         |
| -------------- | ----- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| オーディエンスターゲティング | アーカイブ | [パーソナライゼーション](https://help.liferay.com/hc/en-us/articles/360028721372-Introduction-to-Segmentation-and-Personalization)に置き換えられました（[Migrating from Audience Targeting](./96-migrating-from-audience-targeting/01-migrating-from-audience-targeting.md)を参照）。 |

## Web Experience

| 機能                  | 利用可能性 | メモ                                                                                                                                                      |
| ------------------- | ----- | ------------------------------------------------------------------------------------------------------------------------------------------------------- |
| RSSパブリッシャー          | バンドル  | このウィジェットの有効化と使用に関する[記事](https://help.liferay.com/hc/en-us/articles/360028820672-The-RSS-Publisher-Widget)を参照してください。                                     |
| ユーザーグループページ（コピーモード） | バンドル  | 有効にする方法については、[レガシーユーザーグループサイトの動作](https://help.liferay.com/hc/en-us/articles/360028819172-User-Group-Sites#legacy-user-group-sites-behavior)の説明をご覧ください。 |
| リソースインポーター          | バンドル  | 7.1で非推奨、直接の置き換えなし                                                                                                                                       |

## フォーム

| 機能      | 利用可能性 | メモ                   |
| ------- | ----- | -------------------- |
| Webフォーム | アーカイブ | 7.0用にリリースされた最終バージョン。 |

## 検索

| 機能        | 利用可能性 | メモ                    |
| --------- | ----- | --------------------- |
| ポートレットを検索 | アーカイブ | 複数の検索ウィジェットに置き換えられました |

## セキュリティ

| 機能             | 利用可能性     | メモ                                                                                                                      |
| -------------- | --------- | ----------------------------------------------------------------------------------------------------------------------- |
| 中央認証サービス       | バンドル      | [ SAMLベースの認証](https://help.liferay.com/hc/en-us/articles/360028711032-Introduction-to-Authenticating-Using-SAML)に移行。    |
| Google ログイン    | マーケットプレース | [OpenID Connect](https://help.liferay.com/hc/en-us/articles/360028711312-Authenticating-with-OpenID-Connect)に置き換えられました。 |
| NTLM           | マーケットプレース | [Kerberos](https://help.liferay.com/hc/en-us/articles/360029031831-Authenticating-with-Kerberos)に置き換えられました。             |
| OAuth 1.0a     | マーケットプレース | バンドルに含まれているOAuth 2.0に置き換えられました。                                                                                         |
| OpenAM/OpenSSO | バンドル      | [ SAMLベースの認証](https://help.liferay.com/hc/en-us/articles/360028711032-Introduction-to-Authenticating-Using-SAML)に移行。    |
| OpenID         | マーケットプレース | [OpenID Connect](https://help.liferay.com/hc/en-us/articles/360028711312-Authenticating-with-OpenID-Connect)に置き換えられました。 |

### NTLMからKerberosへの切り替え

NTLMを使用してMicrosoft Windows™アカウントをLiferay DXPで認証している場合は、[Kerberos](https://help.liferay.com/hc/en-us/articles/360029031831-Authenticating-with-Kerberos)を使用するように切り替えます。 NTLMにはセキュリティの脆弱性が残ります。 NTLMは非推奨になり、バンドルから削除されましたが、引き続き[モジュールをビルドしてデプロイ](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-security-sso-ntlm)できます。

## ユーザーおよびシステム管理

| 機能      | 利用可能性                                                                                                                                                                    |
| ------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| ライブユーザー | [`live.users.enabled`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html)[ポータルプロパティ](../../reference/portal-properties.md)を通じて有効化されます。 |

## メンテナンスモードのアプリケーション

すぐに対処する必要はありませんが、DXP 7.2でメンテナンスモードになっているアプリケーションについても考慮することを推奨します。これらのアプリケーションは廃止されたりアーカイブされたりすることはありませんが、バグ修正以外のアップデートを受け取ることはありません。 詳細については、[Features in Maintenance Mode](./features-in-maintenance-mode.md)を参照してください。
