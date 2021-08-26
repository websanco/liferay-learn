# DXP Cloudスタックのアップグレード

DXP Cloudバージョン4の新しい構造を使用するようにリポジトリをアップグレードすると、リポジトリ内でLiferayワークスペースを活用し、サービスを最新の状態に保ち、さまざまな新機能や更新された機能を使用できます。

新しいリポジトリ構造へのアップグレードには、 [アップグレードスクリプト](https://github.com/LiferayCloud/stack-upgrade/archive/release.zip)使用が含まれます。

## アップグレードの準備

DXP Cloudスタックのバージョン4.xにアップグレードするには、現在のサービスが3.xである必要があります。 現在のバージョンを確認するには、リポジトリでgradle.propertiesファイルを見つけます。 リポジトリは次の構造に従います。

    repository
    ├── gradle
    ├── lcp
    ├── liferay
    ├── modules
    ├── themes
    ├── build.gradle
    ├── gradle.properties
    ├── gradlew
    ├── gradlew.bat
    ├── Jenkinsfile
    ├── README.md
    ├── README-dxpcloud.md
    └── settings.gradle

`gradle.properties` ファイルを開き、Dockerイメージに指定されたバージョンを確認します。 たとえば、これはLiferayサービスのイメージバージョンです。

``` properties
liferay.workspace.lcp.liferay.image=liferaycloud/liferay-dxp:7.2.10-sp1-fp4-3.0.19
```

Dockerイメージ名の末尾の数字は、使用しているDXP Cloudスタックのバージョンを示します。 イメージ名が `3.xx` （この例では、liferay-dxp：7.2.10-sp1-fp4-**3.0.19**）で終わっていることを確認します。

次に、Gitリポジトリがクリーンなブランチ上にあることを確認します。 アップグレードスクリプトは、以前にコミットされていないファイルをコミットし、 `upgrade-workspace`と呼ばれる新しいブランチをチェックアウトします。

``` important::
   すでに ``upgrade-workspace`` ブランチがある場合、スクリプトはそのブランチの作成をスキップして、現在のブランチで動作します。
```

## アップグレードスクリプトの実行

アップグレードするには、次の手順を実行します。

1.  [アップグレードスクリプト](https://github.com/LiferayCloud/stack-upgrade/archive/release.zip)ダウンロードし、ローカルのDXP Cloudリポジトリのルートに抽出します。

    ``` bash
    curl -L https://github.com/LiferayCloud/stack-upgrade/archive/release.zip -O
    ```

    ``` bash
    unzip -j release.zip stack-upgrade-release/upgrade-workspace.sh -d path/to/project/folder
    ```

2.  アップグレードスクリプトを実行します。

    ``` bash
    cd /path/to/project/folder
    ```

    ``` bash
    ./upgrade-workspace.sh
    ```

3.  スクリプトの開始出力を読み、プロンプトでプロジェクトのIDを入力します。
   
        Enterを押して続行してください。
        プロジェクトIDを入力してください: lfrlearn

4.  プロンプトで、DXP Cloud Consoleに認証します。

5.  サービスに使用するLiferayバージョンに対応する番号を入力します。
   
        1) 7.0
        2) 7.1
        3) 7.2
        Liferay DXPバージョンを選択してください。これにより、liferay/LCP.jsonにあるLiferay CLOUDイメージセットとliferay/gradle.propertiesにあるLiferayイメージセットが決定されます。3

6.  プロンプトで、プロジェクトファイルに使用する環境のコンマ区切りのリストを入力します（ `common`は含まれません）。
   
        環境のリストをカンマ区切りで入力してください: dev,uat,prd

    ``` important::
       プロジェクトリポジトリにファイルを追加するLiferay環境の名前だけを入力してください。 インフラストラクチャ(`infra`)環境を追加しない。
    ```

7.  すべてのLiferay環境にElasticsearchプラグインをインストールする場合は、プロンプトでそれらの名前のコンマ区切りのリストを入力します。 それ以外の場合は、Enterキーを押します。
   
        elasticsearchプラグインのリストをカンマ区切りで入力してください。

    このスクリプトは、リポジトリに新しいブランチを作成してチェックアウトし、アップグレードスクリプトを `.gitignore` ファイルに追加します。

8.  サービスレベルのフォルダーがルートレベルにある状態で、リポジトリの新しい組織をチェックして、スクリプトが正常に実行されたことを確認します。
   
        repository
        ├── backup
        ├── build
        ├── ci
        ├── database
        ├── liferay
        ├── search
        ├── webserver
        └── upgrade-workspace.sh

リポジトリが再編成され、 `liferay` フォルダーがLiferayワークスペースになり、サービスが4.xxにアップグレードされます。

## CIサービスの環境変数を確認する

サービススタックのアップグレードを完了した後、プロジェクトの `ci/LCP.json` ファイル内の環境変数の動作が異なる場合があります。 アップグレードしたプロジェクトを続行する前に、 `ci` サービスの環境変数が正しい設定を反映していることを確認してください。

プロジェクトとバージョン管理との統合（この例では、GitHubを使用）に対して次のプロパティが正しく設定されていることを確認します。

``` json
{
    "LCP_CI_SCM_PROVIDER": "github",
    "LCP_CI_SCM_REPOSITORY_OWNER": "OWNER_NAME",
    "LCP_CI_SCM_REPOSITORY_NAME": "PROJECT_NAME",
    "LCP_CI_SCM_TOKEN": "AUTH_TOKEN",
}
```

デフォルトJenkinsfileがされているので [はもはや必要な](./dxp-cloud-project-changes-in-version-4.md#ci-service-changes) プロジェクトで、プロジェクトのルートにJenkinsfileもアップグレードした後に除去することはできません。 プロジェクトにデフォルトのJenkinsfileを使用する場合は、 `ci` サービス環境変数がこれを反映していることを確認してください。

``` json
{
    "LCP_CI_USE_DEFAULT_JENKINSFILE": "true",
    "LCP_CI_SCM_JENKINSFILE_HOOKS_DIR": "ci/",
}
```

``` note::
   デフォルトのJenkinsfileを拡張するフックを使いたい場合は ``LCP_CI_SCM_JENKINSFILE_HOOKS_DIR`` 変数を定義する必要があります。
```

`ci` サービスディレクトリ内に独自のJenkinsfileを定義してデフォルトを上書きする場合は、環境変数が次のようになっていることを確認してください。

``` json
{
    "LCP_CI_USE_DEFAULT_JENKINSFILE": "false",
    "LCP_CI_SCM_JENKINSFILE_PATH": "ci/Jenkinsfile",
}
```

## 次のステップ

ローカルリポジトリを変更後、 [のCLIツール](./command-line-tool.md)'の `lcp deploy` コマンドを使用して、 `ci` サービスを `infra` 環境にデプロイします。 これにより、 `ci` サービスへの変更が最初にデプロイされることが保証されます。

次に、新しいサービスバージョン</a> を開発環境に デプロイするか、DXP Cloudサービスの新しいバージョンの新機能のいくつかを探索することができます。 詳細は、[変更点の説明 ](./dxp-cloud-project-changes-in-version-4.md)ご覧ください。
