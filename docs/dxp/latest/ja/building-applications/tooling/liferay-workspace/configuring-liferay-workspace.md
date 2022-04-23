# Liferay Workspaceの設定

Liferay Workspaceは使い方が簡単で、[最初から](./what-is-liferay-workspace.md)基本を学ぶことができます。 [プロジェクトを作成](./creating-code-with-liferay-workspace.md)したり、 [コードをデプロイ](./creating-code-with-liferay-workspace.md#deploying-code-via-liferay-workspace) したり、[Dockerコンテナを使用](./configuring-a-liferay-docker-container.md)したりしている場合は、ここにある情報は必要ないかもしれません。 ただし、もっと掘り下げて、ワークスペースで実行できるすべてのことについて学びたい場合は、このサイトが有益となるでしょう。

取り上げるトピックは次のとおりです。

- Liferay Workspaceとバンドルされたプラグインの更新
- 開発、UAT、および本番環境の使用
- ターゲットプラットフォームの管理

## Liferay Workspaceとバンドルされたプラグインの更新

Liferay Workspaceは、開発者の生産性を高めるために常に更新されており、ワークスペースを最新の状態にするのは簡単なプロセスです。

1. Liferayのリポジトリにある [ワークスペースのリリースに移動](https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.gradle.plugins.workspace) します。 バージョンがリストに表示されます。必要なバージョン番号をメモしてください。
1. ワークスペースのルートフォルダにある`settings.gradle`ファイルを開きます。
1. `依存関係`ブロックで、リポジトリで見つけたバージョンでバージョンを更新します。 最新のリリースを維持したい場合は、バージョン番号の代わりにテキスト`latest.release`を指定してください。

   ```groovy
    dependencies {
        classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "[WORKSPACE_VERSION]"
    }
    ```

1. ファイルを保存して閉じます。 アップグレードを実行するには、`tasks`のような任意のGradleコマンドを実行します。

   ```bash
   ./gradlew tasks
   ```

　 これで、ワークスペースがアップグレードされました。

## Liferayバージョンの更新

ワークスペースがコンパイルするLiferayのバージョンを更新することをお勧めします。 これは、単一のプロパティによって処理されます。

```properties
liferay.workspace.product=[$LIFERAY_LEARN_PORTAL_WORKSPACE$]
```

プロパティの値を、コードを開発しているLiferayのバージョンに更新します。 その後、ワークスペースの依存関係が自動的に新しいバージョンに更新されます。

## JDK11の使用

```{note}
JDK 11でコンパイルする場合は、JDK11で実行する必要があります。 ワークスペースに変更を加える前に、アプリサーバーでJDK11が実行されていることを確認してください。 LiferayのDockerイメージはデフォルトでJDK8を使用することに注意してください。 これをオーバーライドするには、`-e JAVA_VERSION=zulu11`環境変数を使用してDockerイメージを作成します。
```

**前提条件：** 古いバージョンのワークスペースを使用している場合は、次の2つをアップグレードする必要があります。

1. Gradleをバージョン6.6.1以上に更新します
1. ワークスペースのバージョンを3.4.2以上に更新します（アップグレード手順については上記を参照）。

Gradleをアップグレードするには、ワークスペースの`gradle/wrapper/gradle-wrapper.properties`ファイルを編集します。

```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-6.6.1-all.zip
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
zipStorePath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
```

ワークスペースもアップグレードした場合は、`liferay.workspace.product` プロパティが設定されていることを確認してください。 `blade init -l`と入力すると、Blade CLIを使用してこのプロパティの現在のリストをいつでも取得できることを忘れないでください。

古いワークスペースをアップグレードした場合は、Liferay CDNがワークスペースの`settings.gradle`ファイルで宣言されていることを確認してください。

```groovy
maven {
    url "http://repository.liferay.com/nexus/content/groups/public"
}
```
これで、LiferayプロジェクトでJDK11を使用する準備が整いました。 既存のプロジェクトがある場合は、追加で必要な手順があります。

### サービスビルダープロジェクト

古いワークスペースをサービスビルダープロジェクトでアップグレードした場合は、この構成をサービスビルダーの`-service`モジュールの`build.gradle`ファイルに追加します。

```groovy
tasks.withType(JavaCompile) {

    // Generated classes using Jodd library are unable to be read when compiled against JDK 11

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
```
### JAX-WSプロジェクト

JAX-WSプロジェクトがある場合は、JDK 11から削除された`javax.xml.soap`のクラスが必要です。 次に、それらを依存関係として手動で指定する必要があります。


```groovy
compile 'com.sun.xml.ws:jaxws-ri:2.3.2'
```

## デプロイメント環境の構築

コードを誰かと共有するときが来ます。 そのためには、環境を構築する必要があります。 企業の世界では、通常3つの環境があります。

* 開発者：コードを迅速に修正および再デプロイできる、早期テスト用の環境。 パワーユーザーはここでテストします。
* ユーザー受け入れテスト（UAT）：本番環境の構成をより厳密に反映する環境。 アプリケーションがほぼ完成したら、通常、より多くのユーザーをここでテストするように招待します。
* 本番環境：本番環境のサイトが存在する環境。 デプロイメントは厳密に制御され、他の2つの環境でテストされたコードのみがデプロイされます。

Liferay Workspaceを使用すると、コンテナーベースであろうと従来型であろうと、デプロイメント環境を簡単に生成できます。 構成を提供し、Gradleタスクを実行すると、配布可能なDockerコンテナまたはサーバーアーカイブを生成して、インストールすることができます。 ワークスペースには、アクションが発生するオプションの`configs`フォルダが含まれています。

[Blade CLI](../blade-cli/generating-projects-with-blade-cli.md)を使用してワークスペースを作成した場合、`configs`フォルダは既に存在しています。 ワークスペースを[手動で](./creating-a-liferay-workspace.md)作成した場合は、ワークスペースディレクトリに次のフォルダ構造を作成します。

   ```
   ├── common
   │   └── portal-setup-wizard.properties
   ├── dev
   │   └── portal-ext.properties
   ├── docker
   ├── local
   ├── prod
   └── uat
       └── portal-ext.properties
   ```

まだファイルには何も入れないでください。

### デプロイメント環境の仕組み

`configs`フォルダは、特定のシナリオを定義します。

`common`：すべての環境に適用される構成が含まれています。

`dev`：開発環境の構成が含まれています。

`docker`：Docker構成が含まれています。

`local`：ワークスペースが存在するローカル環境の構成が含まれます。

`prod`：本番環境の構成が含まれています。

`uat`：ユーザー受け入れテスト環境の構成が含まれています。

構成ファイルを特定のフォルダに配置すると、その環境の構成が定義されます。 `common`の場合、その構成は、環境が構築されるときに他の構成とマージされます。 これで、いくつかの環境を構築する準備が整いました。

### デプロイメント環境の構築

ご使用の環境で次のシナリオを想定します。

- 開発者環境は開発者プロパティを使用する必要があります
- ローカル環境はローカルデータベースを指します
- 開発者およびUAT環境は、独自のデータベースを指します
- すべての環境はセットアップウィザードをスキップします

そのシナリオを構成する方法は次のとおりです。

1. `common`フォルダで、次のプロパティを`portal-setup-wizard.properties`に追加します

   ```properties
   setup.wizard.enabled=false
   ```
1. `local`フォルダで、`portal-ext.properties`にローカルデータベースを構成します。

   ```properties
   #
   # MySQL
   #
   jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
   jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
   jdbc.default.username=root
   jdbc.default.password=password
   ```

1. `dev`フォルダで、開発者プロパティを有効にし、`portal-ext.properties`の開発サーバーでデータベースを構成します。

   ```properties
   include-and-override=portal-developer.properties

   #
   # MySQL
   #
   jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
   jdbc.default.url=jdbc:mysql://devel.server/lportaldev?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
   jdbc.default.username=root
   jdbc.default.password=password
   ```

1. `uat`フォルダで、`portal-ext.properties`のUAT環境でデータベースを構成します。

   ```properties
   #
   # MySQL
   #
   jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
   jdbc.default.url=jdbc:mysql://uat.server/lportaluat?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
   jdbc.default.username=root
   jdbc.default.password=password
   ```

これで、環境を生成して配布する準備が整いました。

### デプロイメント環境の生成

これで、ローカルで実行したり、サーバーに配布したりする環境を生成できます。 これは、`initBundle`または`distBundle` Gradleタスクのいずれかを使用して実行されます。

1. 最初に環境をテストする必要があります。 ローカルでビルドするには、`initBundle`コマンドを使用します。 たとえば、`dev`環境を構築するには、次のように実行します。

   ```bash
   ./gradlew initBundle -Pliferay.workspace.environment=dev
   ```
   これにより、ワークスペース内のすべてのプロジェクトがコンパイルされ、`bundles`フォルダ内のランタイムにデプロイされます。 また、指定した構成も提供されます（この例では、`dev`環境の構成）。 `bundles`フォルダ内の`portal-ext.properties`ファイルを調べると、`dev`構成に提供したものと一致していることがわかります。

1. 環境が整いテストが良好な場合は、すべてのアプリケーションと構成を含む配布可能なバンドルを構築できます。 次のコマンドを実行します。

   ```bash
   ./gradlew distBundleTar -Pliferay.workspace.environment=dev
   ```

   これにより、gzip圧縮された`tar`ファイルが`build`フォルダにビルドされます。 このアーカイブを取得して、他のLiferayバンドルと同じように開発サーバーにインストールできます。ただし、このバンドルは希望どおりに完全に構成されており、すべてのアプリケーションが既にインストールされている点が異なります。

```{note}
.zipアーカイブが必要な場合は、`distBundleZip`コマンドを使用できます。
```

上記の手順に従って、各環境をテストおよび構築します。

## ターゲットプラットフォームの管理

通常、Gradleの依存関係を定義するときは、次のようにそれらの依存関係のバージョンを提供する必要があります。

```groovy
dependencies {
   compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.1"
   compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "4.0.1"
}
```

Liferayのプラットフォームで作成されたアプリケーションはすべてそのプラットフォームを対象としているため、Liferayでは、Liferayのバージョンを宣言し、Liferayから他の依存関係を継承することで、すべての依存関係を1回で簡単に指定できます。 そうすれば、上記のような混乱はありません。

ターゲットプラットフォームはデフォルトで有効になっています。使用するために特別なことをする必要はありません。 現在、ほとんどの依存関係は次のようになっています。

```groovy
dependencies {
    compileOnly group: "com.liferay.portal", name: "release.portal.api"
}
```

これにより、Liferayに付属するすべての依存関係がもたらされます。 何らかの理由で特定の依存関係を指定する必要がある場合でも、引き続き可能です。

```groovy
dependencies {
        compileOnly group: "com.liferay.portal", name: "release.portal.api"
        cssBuilder group: "com.liferay", name: "com.liferay.css.builder", version: "3.0.2"
}
```
