# Liferay Workspaceの作成

Liferay Workspaceは手動または[Blade CLI](../blade-cli/installing-and-updating-blade-cli.md)を使用して作成できます。

## Liferay Workspaceを手動で作成する

Liferay Workspaceを手動で作成するには、[Gradle](https://gradle.org)がインストールされている必要があります。 また、ターゲットとするLiferay製品（DXP、ポータルローリングリリース、またはコマース）のIDを知っているか、`blade init -l` コマンドを使用してBlade CLIで検索できる必要があります。

1.  ワークスペースを保存するフォルダをシステム上に作成します。

1.  このフォルダ内に、次のコンテンツを含む`settings.gradle`というファイルを作成します。

    ```groovy
    buildscript {
            dependencies {
                    classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "latest.release"
            }

            repositories {
                    mavenLocal()

                    maven {
                            url "https://repository-cdn.liferay.com/nexus/content/groups/public"
                    }
            }
    }

    apply plugin: "com.liferay.workspace"
    ```

1.  次に、次のコマンドを使用して、Gradleラッパーをプロジェクトに追加します。

    ```bash
    gradle wrapper --gradle-version 6.6.1
    ```

1.  以下を含む`gradle.properties`というファイルを作成します。

    ```properties
    liferay.workspace.product=[$LIFERAY_LEARN_PORTAL_WORKSPACE$]
    ```

    これは、Liferay Portalの最新のGAを定義します。 `blade init -l`と入力すると、Blade CLIを使用して常に現在のリストを取得できます。

## Blade CLIを使用してLiferay Workspaceを作成する

1. コマンドラインインターフェイスで、ワークスペースを生成するフォルダに移動します。

1. 利用可能なバージョンを一覧表示して、ターゲットにするLiferayのバージョンを見つけます。

   ```bash
   blade init -l
   ```

1. これで、ワークスペースを作成する準備が整いました。 次のコマンドを実行します。

   ```bash
   blade init -v [Liferay version] [workspace name]
   ```

   例:

   ```bash
   blade init -v portal-7.4-ga1 my-workspace
   ```

ワークスペースが作成されます。 なお、Liferayの7.xバージョンであれば、任意のバージョンをターゲットにすることができます。

```bash
blade init -v portal-7.0-ga7 [workspace name]
```

ワークスペースのバージョンは、`liferay.version.default`プロパティを使用して、ワークスペースのルートフォルダにある非表示の`.blade.properties`ファイルに保存されます。 テンプレートに基づいてプロジェクトを作成する場合、ここに保存されているバージョンによって、使用されるテンプレートのバージョンが決まります。

## プロキシの構成

プロキシサーバーの背後にいる場合は、それを設定できます。

1.  `~/.gradle/gradle.properties`ファイルを開きます。 このファイルが存在しない場合は作成してください。

1.  次のプロパティをファイルに追加します。

    ```properties
    systemProp.http.proxyHost=www.somehost.com
    systemProp.http.proxyPort=1080
    systemProp.https.proxyHost=www.somehost.com
    systemProp.https.proxyPort=1080
    ```

    プロキシのホストとポートの値を自分のものに置き換えてください。

3.  プロキシサーバーで認証が必要な場合は、次のプロパティも追加します。

    ```properties
    systemProp.http.proxyUser=userId
    systemProp.http.proxyPassword=yourPassword
    systemProp.https.proxyUser=userId
    systemProp.https.proxyPassword=yourPassword
    ```

プロキシ設定がLiferayワークスペースで設定されます。 

