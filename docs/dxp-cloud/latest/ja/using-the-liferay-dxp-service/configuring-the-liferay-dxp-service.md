# Liferay DXPサービスの設定

Liferay DXPを設定するには、[DXPシステム設定](https://learn.liferay.com/dxp/7.x/en/system-administration/system-settings/system-settings.html)を使用する方法と、[設定](https://learn.liferay.com/dxp/7.x/en/system-administration/system-settings/using-configuration-files.html)ファイルと[プロパティファイル](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html)を使用する方法があります。 DXP CloudのLiferay DXPインスタンスのDXPプロパティおよび設定ファイルは、リポジトリのLiferay DXPサービスディレクトリにある`config`フォルダのいずれかに配置することでデプロイされます。

    lcp
    └── liferay
      ├── LCP.json
      └── config
        ├── common
        ├── dev
        ├── local
        ├── prd
        └── uat

`common/`ディレクトリを除き、特定のサービスの環境フォルダ（`dev`、`uat`、`prod`など）に追加された変更は、対応する環境にデプロイするときに*のみ*伝播されます。 `common/`ディレクトリに追加された変更は、ターゲットのデプロイ環境に関係なく、*常に*デプロイされます。 これは、`lcp/liferay/`内の`config`、`deploy`、`license`、および`script`ディレクトリに適用されます。

## ポータルプロパティ

[ポータルプロパティ](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html)は、Liferay DXP環境を設定するために使用されるフォーム`portal-*.properties`のファイルです。

オンサイトのLiferay DXPインスタンスでは、これらのファイルは`$LIFERAY_HOME`内に属しています。 Liferay DXP Cloudを使用する場合、これらのファイルを適切な `config` フォルダに配置して、デプロイ時にLiferay DXPインスタンスの `$LIFERAY_HOME` にコピーします。

これらは、`config`フォルダのいずれかで使用できるポータルプロパティ形式のファイルです。

  - `portal-all.properties`：環境全体でLiferay DXPを変更するプロパティが含まれています

  - `portal-env.properties`：現在の環境にのみ影響するプロパティが含まれます（たとえば、環境ごとに異なる外部サービスの資格情報とURLエンドポイント）

  - `portal-clu.properties`：DXP CloudでLiferay DXPをクラスタリングするための事前設定されたプロパティが含まれています。 詳細は、[DXP Cloudでのクラスタリングのセットアップ](./setting-up-clustering-in-dxp-cloud.md)を参照してください。

  - `portal-ext.properties`: Liferay DXP 設定　の最終的な変更が含まれています。ほとんどのプロパティは `portal-all.properties` および `portal-env.properties`で設定　されているため、このファイルは空であることが多いです。

<!-- end list -->

``` note::
   ポータルプロパティは、環境変数として定義することもできます。 詳細は `環境変数リファレンス <./introduction-to-the-liferay-dxp-service.md#environment-variables-reference>`_ を参照してください。
```

## OSGiの設定

OSGi設定（`.cfg` または `.config` ファイル）は、Liferay DXPでOSGiコンポーネントを設定するために使用されます。

これらの設定ファイルは、 `$LIFERAY_HOME`内の `/osgi/configs` フォルダに属しています。 Liferay DXP Cloudを使用する場合、これらのファイルを適切な `config` フォルダに配置して、デプロイ時にLiferay DXPインスタンスの `/osgi/configs` にコピーします。

## 追加情報

  - [Introduction to the Liferay DXP Service](./introduction-to-the-liferay-dxp-service.md)
  - [Enabling Clustering in DXP Cloud](./setting-up-clustering-in-dxp-cloud.md)
  - [Portal Properties](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html)
