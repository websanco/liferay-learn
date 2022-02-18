# 依存関係の指定

モジュールを正常にコンパイルおよびデプロイするには、すべての依存関係を満たす必要があります。 [依存関係のアーティファクトを見つけたら](./finding-artifacts.md)、Gradleビルドファイルに依存関係として追加します。 Liferayには、実行時にすでに多くのアーティファクトが含まれています。 他のアーティファクトに依存している場合は、それらを手動でデプロイするか、モジュールに含める必要があります。 ここでは、依存関係の構成手順と例を示します。

<a name="依存関係の構成" />

## 依存関係の構成

1. `build.Gradle`ファイルを開きます。

1. [`dependencies`セットがまだ存在しない場合は、それを宣言](https://docs.gradle.org/current/userguide/declaring_dependencies.html) します。

    ```groovy
    dependencies {
    }
    ```

1. モジュールにLiferay、OSGi、またはBndのパッケージが必要な場合は、Liferay Portal APIに`compileOnly`依存関係を追加します。

    ```groovy
    dependencies {
        compileOnly group: "com.liferay.portal", name: "release.portal.api"
    }
    ```

1. モジュールがコンパイルまたはデプロイされない場合は、必要なパッケージを提供するアーティファクトを見つけて、アーティファクトに`compileInclude`依存関係を追加します。

    ```groovy
    dependencies {
        compileOnly group: "com.liferay.portal", name: "release.portal.api",
        compileInclude group: 'com.google.guava', name: 'guava', version: '19.0'
    }
    ```

1. モジュールをデプロイし、[Gogo シェルコマンド](../using-the-gogo-shell.md)を使用するかログを参照して、満たされていないパッケージの依存関係がないかどうかを確認します。

1. 満たされていない依存関係がある場合は、次のように解決します。

    **モジュールの依存関係の場合は** 、必要なモジュールをデプロイします。 詳細は、[Installing and Managing Apps](../../../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md)を参照してください。

    **ライブラリの依存関係の場合は** 、[Resolving Third Party Library Dependencies](./resolving-third-party-library-package-dependencies.md)の手順に従ってください。

依存関係の指定は、信頼できるスキルです。

<a name="追加情報" />

## 追加情報

* [アーティファクトを見つける](./finding-artifacts.md)
* [パッケージのインポート](../importing-packages.md)
* [パッケージのエクスポート](../exporting-packages.md)
* [サードパーティライブラリパッケージの依存関係の解決](./resolving-third-party-library-package-dependencies.md)
* [Deploying WARs \(WAB Generator\)](../../../developing-applications/reference/deploying-wars-wab-generator.md)