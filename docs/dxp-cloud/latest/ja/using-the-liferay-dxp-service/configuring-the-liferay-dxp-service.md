# Liferay DXPサービスの構成

Liferay DXPを設定するには、 [DXPシステム設定](https://learn.liferay.com/dxp/latest/ja/system-administration/configuring-liferay/system-settings.html) や、 [config](https://learn.liferay.com/dxp/latest/ja/system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.html) と [property file](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/reference/portal-properties.html) を使用するなど、いくつかの方法があります。  DXP CloudのLiferay DXPインスタンスのDXPプロパティおよび設定ファイルは、リポジトリのLiferay DXPサービスディレクトリにある`configs/`フォルダのいずれかに配置することでデプロイされます。

```
liferay
├── configs
│   ├── common
│   ├── dev
│   ├── local
│   ├── prd
│   └── uat
└── LCP.json
```

`common/`ディレクトリを除き、特定の環境フォルダ（`dev`、`uat`、`prod`など）に追加された変更は、対応する環境にデプロイするときに **のみ** 伝播されます。 `common/`ディレクトリに追加された変更は、ターゲットのデプロイ環境に関係なく、 **常に** デプロイされます。 これは、すべてのサービスにおいて、 `configs/` ディレクトリ内のすべてのサブフォルダに適用されます。

```{note}
   バージョン3.x.xのサービスを使用している場合、これらの設定ファイルは適切な `lcp/liferay/config/{ENV}/` フォルダに属しています。 バージョンの確認については、 [サービススタックのバージョンについて](../reference/understanding-service-stack-versions.md) を参照してください。
```

## ポータルプロパティ

[ポータルプロパティ](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/reference/portal-properties.html) は、 `portal-ext.properties` ファイルに保存されたプロパティです。 これらは、Liferay DXP環境の設定に使用されます。

オンプレミスのLiferay DXPインスタンスの場合、このファイルは `$LIFERAY_HOME`の中に属します。 Liferay DXP Cloudを使用する場合、ポータルプロパティファイルを適切な `configs/{ENV}` フォルダに配置して、デプロイ時にLiferay DXPインスタンスの `$LIFERAY_HOME` にコピーします。

例えば、dev環境のプロパティは、 `configs/common` ディレクトリのプロパティファイルと、 `configs/dev` ディレクトリのプロパティから構成されています。 同名のファイルがある場合は、環境固有のディレクトリにあるファイルが、 `共通の` ディレクトリにあるファイルを上書きします。

```{note}
   バージョン3.x.xのサービスを使用している場合、これらの設定ファイルは適切な `lcp/liferay/config/{ENV}/` フォルダに属しています。 バージョンの確認については、 [サービススタックのバージョンについて](../reference/understanding-service-stack-versions.md) を参照してください。
```

### ポータルプロパティを複数のファイルに分割する

デフォルトでは、Liferayは `portal-ext.properties` ファイルのみから直接プロパティを読み取りますが、環境固有のファイルなど、リポジトリ内の異なるファイルにプロパティを整理しておくと便利です。 ポータルのプロパティを上書きするために、追加のファイルを定義することができます。

* `portal-all.properties`：環境全体でLiferay DXPを変更するプロパティが含まれています

* `portal-env.properties`：現在の環境にのみ影響するプロパティが含まれます（たとえば、環境ごとに異なる外部サービスの資格情報とURLエンドポイント）

これらのファイルを使用するには、 `portal-ext.properties` ファイルが、 `portal-all.properties` および `portal-env.properties` を、 `include and override` プロパティを使用して、明示的にインポートする必要があります。

```
include-and-override=/opt/liferay/portal-all.properties
include-and-override=/opt/liferay/portal-env.properties
```

そして、以下のような構造でプロパティを整理することができます：

```
liferay
├── configs
│   ├── common
│   │   ├── portal-ext.properties
│   │   └── portal-all.properties
│   ├── dev
│   │   └── portal-env.properties
│   ├── local
│   │   └── portal-env.properties
│   ├── prd
│   │   └── portal-env.properties
│   └── uat
│       └── portal-env.properties
└── LCP.json
```

`portal-ext.properties` と `portal-all.properties` は、すべての環境で共有されます。 そこに共有のプロパティを追加し、それぞれの `portal-env.properties` ファイルに環境固有のプロパティを追加することができます。 詳細については、 [Portal Property Priority](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/reference/portal-properties.html#portal-property-priority) を参照してください。

```{note}
   ポータルプロパティは、環境変数として定義することもできます。 詳細は [環境変数リファレンス](./introduction-to-the-liferay-dxp-service.md#environment-variables-reference) を参照してください。
```

## OSGiの構成

OSGi構成（`.cfg` または `.config` ファイル）は、Liferay DXPでOSGiコンポーネントを構成するために使用されます。

これらの構成ファイルは、 `$LIFERAY_HOME`内の `osgi/configs/` フォルダに属しています。 Liferay DXP Cloudを使用する場合、これらのファイルを適切な `config` フォルダに配置して、デプロイ時にLiferay DXPインスタンスの `configs/{ENV}/osgi` にコピーします。

```{note}
   バージョン3.x.xのサービスを使用している場合、OSGiの設定ファイルはLiferayサービスディレクトリ内の適切な`config/{ENV}/`フォルダに属します。 バージョンの確認については、 [サービススタックのバージョンについて](../reference/understanding-service-stack-versions.md) を参照してください。
```

## Tomcatの設定

適切な環境の `liferay/configs/{ENV}` フォルダにファイルをデプロイして、LiferayサービスのTomcatサーバーを設定し、設定ファイルを上書きします。 例えば、Liferayコンテナのファイルシステムにある `{TOMCAT HOME}/conf/web.xml` ファイルを上書きするには、カスタマイズしたファイルをリポジトリの適切な `liferay/configs/{ENV}/tomcat/conf/` フォルダに配置して、変更をデプロイします。

```{note}
   DXPクラウドのLiferayコンテナには、一般的な「tomcat」フォルダと、バージョン管理された「tomcat-x.x.xの2つのtomcatフォルダが存在します。 「tomcat」フォルダは、バージョン管理された「tomcat-x.x.x」 フォルダへのシンボリックリンクを持っているので、一般的な 「tomcat」フォルダ内のファイルを上書きすると、新しいファイルが両方のフォルダに反映されます。
```

```{warning}
   デフォルトのTomcatの設定を上書きする際には、DXP CloudのLiferayサービスは、クラウドプラットフォーム上の閉じられたネットワークに存在することに留意してください。 オンプレミスのLiferayインストールで変更可能なネットワーク構成の中には、クラウド環境ではデフォルト値から変更できないものや、お客様の環境で問題が発生する可能性のあるものがあります。
```

## 追加情報

* [Liferay DXPサービスの紹介](./introduction-to-the-liferay-dxp-service.md)
* [DXP Cloudでのクラスタリングの有効化](./setting-up-clustering-in-dxp-cloud.md)
* [ポータルプロパティ](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/reference/portal-properties.html)
