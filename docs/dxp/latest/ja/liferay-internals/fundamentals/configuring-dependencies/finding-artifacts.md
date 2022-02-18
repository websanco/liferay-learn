# アーティファクトを見つける

プロジェクトで外部アーティファクトを使用するには、`build.gradle` [Gradle](https://gradle.org/)スクリプトでそれらの依存関係を構成する必要があります。

アーティファクトを依存関係として指定する前に、まずその属性を見つける必要があります。 アーティファクトには次の属性があります。

* *グループID*：オーサリング組織
* *アーティファクトID*：名前/識別子
* *バージョン*：リリース番号

ここでは、アーティファクト属性を見つけて依存関係を指定する方法を学習します。

## コアアーティファクト属性の検索

各Liferayアーティファクトは、アーティファクトのOSGiバンドルメタデータ属性を含む`META-INF/MANIFEST.MF`ファイルが含まれるJARファイルです。 たとえば、次の2つのOSGiヘッダーは、アーティファクトIDとバージョンを指定します。

```
Bundle-SymbolicName:  [artifact ID]
Bundle-Version: [version]
```

```{important}
Liferay DXPフィックスパックのアーティファクトは、Liferay DXPインストールアーティファクトをオーバーライドします。 フィックスパックの `binaries`フォルダには、サブフォルダのアーティファクトが含まれています。 プロジェクトでフィックスパックによって提供されるアーティファクトが必要な場合は、依存関係を宣言するときにフィックスパックからバージョンを宣言してください。
```

Liferayのコアアーティファクトを見つける場所は次のとおりです。

| ファイル                    | グループID               | アーティファクトID                  | 版                       | 原点                                                   |
|:----------------------- |:-------------------- |:--------------------------- |:----------------------- |:---------------------------------------------------- |
| `portal-kernel.jar`     | `com.liferay.portal` | `com.liferay.portal.kernel` | （JARの `MANIFEST.MF`を参照） | フィックスパックZIP、Liferayインストール、またはLiferay依存関係ZIP          |
| `portal-impl.jar`       | `com.liferay.portal` | `com.liferay.portal.impl`   | （JARの `MANIFEST.MF`を参照） | フィックスパックZIPまたはLiferay `.war`                         |
| `util-bridges.jar`      | `com.liferay.portal` | `com.liferay.util.bridges`  | （JARの `MANIFEST.MF`を参照） | フィックスパックZIPまたはLiferay `.war`                         |
| `util-java.jar`         | `com.liferay.portal` | `com.liferay.util.java`     | （JARの `MANIFEST.MF`を参照） | フィックスパックZIPまたはLiferay `.war`                         |
| `util-slf4j.jar`        | `com.liferay.portal` | `com.liferay.util.slf4j`    | （JARの `MANIFEST.MF`を参照） | フィックスパックZIPまたはLiferay `.war`                         |
| `util-taglibs.jar`      | `com.liferay.portal` | `com.liferay.util.taglib`   | （JARの `MANIFEST.MF`を参照） | フィックスパックZIPまたはLiferay `.war`                         |
| `com.liferay.*` JARファイル | `com.liferay`        | （JARの `MANIFEST.MF`を参照）     | （JARの `MANIFEST.MF`を参照） | フィックスパックZIP、Liferayインストール、Liferay依存関係ZIP、またはOSGi ZIP |

次に、Liferayアプリと独立したモジュールアーティファクトの属性を見つける方法を学びます。

## Liferayアプリと独立したアーティファクトを見つける

コア外のモジュールの場合でも、必要なモジュールへの依存関係を指定する必要があります。 必要な依存関係を見つけるには、次の3つの方法があります。

| 資源                           | アーティファクトタイプ                                   |
|:---------------------------- |:--------------------------------------------- |
| [アプリマネージャー](#app-manager)    | デプロイされたモジュール                                  |
| [参照ドキュメント](#reference-docs)  | Liferayモジュール（リリースごと）                          |
| [メイヴン・セントラル](#maven-central) | すべてのアーティファクトのタイプ：Liferayとサードパーティ、モジュールと非モジュール |

```{important}
`com.liferay`は、Liferayのすべてのアプリと独立したモジュールのグループIDです。
```

アプリケーションマネージャは、デプロイされたモジュールに関する最良の情報を提供します。

### アプリマネージャー

[アプリケーションマネージャ](../../../system-administration/installing-and-managing-apps/managing-apps/using-the-app-manager.md)は、Liferayインスタンスにデプロイされているものを表示します。

1. Liferayで、*グローバルメニュー* （![Global Menu icon](./finding-artifacts/images/01.png)）をクリックし、*［コントロールパネル］*タブを選択します。

1. ［System］カテゴリで、*［アプリケーションマネージャ］*を選択します。

1. 表示名、シンボル名、または関連するキーワードでモジュールを検索します。 アプリの一覧からモジュールを閲覧することもできます。 閲覧するか検索するかにかかわらず、アプリマネージャーは各モジュールのアーティファクトIDとバージョン番号を表示します。

![アプリケーションマネージャは、デプロイされた各モジュールのアーティファクトIDとバージョン番号を表示します。](./finding-artifacts/images/02.png)

アプリケーションマネージャは、独立モジュールを独立モジュールカテゴリに集約します。

![アプリケーションマネージャは、独立したモジュールを集約します。](./finding-artifacts/images/03.png)

独立したモジュールのグループIDがわからない場合は、[Felix Gogo シェル](../using-the-gogo-shell.md)を使用して検索します。

1. コントロールパネルの［System］カテゴリで、*［Gogo シェル］*を選択します。 Gogo シェルコマンドプロンプトにコマンドを入力します。

1. 表示名（`Apache Aries CDI`など）またはキーワードでモジュールを検索します。 結果で、モジュールの番号をメモします。 次のステップで使用できます。 以下の例は、Liferay Announcements APIモジュール番号`47`を示しています。

    ![このGogoコマンドの結果は、モジュールの番号が<code>47</code>であることを示しています。](./finding-artifacts/images/04.png)

1. モジュール番号を指定して`headers`コマンドを使用して、マニフェストヘッダーを表示します。 `Bundle-Vendor`または`Implementation-Vendor`の値に注目してください。 後のステップでアーティファクトグループと照合します。

    ![<code>headers</code>コマンドを実行した結果は、モジュールのベンダー名とバンドルバージョンを示しています。](./finding-artifacts/images/05.png)

1. [Maven Central](https://search.maven.org/)または[MVNRepository](https://mvnrepository.com)で、アーティファクトIDでモジュールを検索します。

1. 手順3の`Bundle-Vendor`または`Implementation-Vendor`の名前を、アーティファクトを提供するグループと照合して、グループIDを決定します。

Liferayリファレンスドキュメントには、Liferayアプリのアーティファクト属性も記載されています。

### 参照ドキュメント

LiferayのアプリJavadocには、各アプリモジュールのアーティファクトID、バージョン番号、表示名が一覧表示されます。 これは、LiferayインスタンスにまだデプロイされていないLiferayアプリモジュールを検索するのに最適な場所です。

```{note}
Liferayのコアアーティファクトのアーティファクト情報を見つけるには、前のセクション*Liferayのコアアーティファクト属性の検索*を参照してください。
```

JavadocでLiferayアプリモジュールの属性を見つけるには、次の手順に従います。

1. アプリモジュールクラスのJavadocに移動します。 クラスのJavadocへのリンクがない場合は、LiferayエディションのアプリJavadocを閲覧してリンクを検索します。

    **Liferay DXP**: <https://docs.liferay.com/dxp/apps>

    **Liferay Portal**: <https://docs.liferay.com/ce/apps>

1. クラスのパッケージ名をコピーします。

1. *［概要］*ページに移動します。

1. *［概要］*ページで、手順2でコピーしたパッケージ名を検索します。

パッケージ名の上の見出しには、モジュールのアーティファクトID、バージョン番号、表示名が表示されます。 すべてのアプリモジュールのグループIDは`com.liferay`であることを思い出してください。

![LiferayのアプリJavadocには、表示名に続いて、グループID、アーティファクトID、およびバージョン番号がGradleアーティファクト構文で表示されています。](./finding-artifacts/images/06.png)

```{note}
現在、モジュールのバージョン番号はどのタグライブラリのリファレンスドキュメントにも含まれていません。
```

次に、MVNRepositoryおよびMaven Centralでアーティファクトを検索する方法を学習します。

### メイヴン・セントラル

タイプや出所に関係なく、ほとんどのアーティファクトは[MVNRepository](https://mvnrepository.com/)と[MavenCentral](https://search.maven.org/)にあります。 これらのサイトは、クラスパッケージに基づいて成果物を見つけるのに役立ちます。 アーティファクトのパッケージ名の先頭にアーティファクトのIDを含めるのが一般的です。 たとえば、クラス`org.osgi.service.component.annotations.Component`に依存している場合は、Mavenサイトの1つでパッケージ名`org.osgi.service.component.annotations`を検索します。

```{note}
上記の手順に従って、必要なLiferayアーティファクトのバージョンを確認してください。
```

## 次のステップ

アーティファクトの属性がわかったので、アーティファクトへの依存関係を構成できます。 詳細については、[Specifying Dependencies](./specifying-dependencies.md)を参照してください。

## 追加情報

* [パッケージのインポート](../importing-packages.md)
* [パッケージのエクスポート](../exporting-packages.md)
* [サードパーティライブラリパッケージの依存関係の解決](./resolving-third-party-library-package-dependencies.md)
* [Deploying WARs \(WAB Generator\)](../../../developing-applications/reference/deploying-wars-wab-generator.md)