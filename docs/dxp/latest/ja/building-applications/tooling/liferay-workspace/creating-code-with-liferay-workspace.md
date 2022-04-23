# Liferay Workspaceでのコード作成

Liferay Workspaceは、Liferay開発プロジェクトを作成および管理するための開発者向けツールです。

## プロジェクトの作成

[Blade CLI](../blade-cli/installing-and-updating-blade-cli.md)を使用すると、事前に作成されたプロジェクトテンプレートを提供することで、Liferay Workspaceでプロジェクトを簡単に作成できます。 または、プロジェクトを手動で作成することもできます。

### Blade CLIを使用したプロジェクトの作成

1. ワークスペース内から、次のコマンドを使用して、実行したい内容に最も近いプロジェクトテンプレートを見つけます。

   ```bash
   blade create -l
   ```

1. 使用するテンプレートを特定したら、プロジェクトを生成します。 たとえば、MVCポートレットを作成するには、次のコマンドを使用します。

   ```bash
   blade create -t mvc-portlet -p com.acme.z3x1.portlet -c MyPortlet myproject-web
   ```

   これにより、MVCポートレットテンプレート（`-t mvc-portlet`）を使用してプロジェクトが作成され、指定したJavaパッケージにポートレットクラスが生成され（`-p com.acme.z3x1.portlet`）、ポートレットクラスの名前が指定され（`-c MyPortlet`）、プロジェクトに`myproject-web`という名前が付けられます。

### 手動でプロジェクトを作成する

プロジェクトの作成にBlade CLIは必要ありません。 必要に応じて、プロジェクトフォルダを手動で作成できます。

1. ワークスペース内から、プロジェクトを格納するフォルダを作成します。

   ```bash
   cd [my project name]
   ```

1. プロジェクトの`build.gradle`スクリプトを作成します。 Liferay Workspaceを使用しているため、ほとんどの場合、必要な依存関係は1つだけです。

   ```groovy
   dependencies {
      compileOnly group: "com.liferay.portal", name: "release.portal.api"
   }
   ```

1. プロジェクトのフォルダ構造を作成します。

Liferay DXPで実行するコードを作成するには、それをデプロイするLiferayランタイムが必要です。 したがって、Liferay Workspaceをインストールした後の最初のタスクは、Liferayバンドルをワークスペースに追加することです。

## Liferay WorkspaceでThemes Generatorを使用する

[Liferay Themes Generator](../other-tools/theme-generator.md)は、サイトのルックアンドフィールを変更するテーマを作成するためのnode.jsベースのツールです。 これらのプロジェクトは、Liferay Workspaceで管理することもできます。 テーマプロジェクトは、Bladeを使用するか手動で作成できます。

Bladeを使用してTheme Generatorプロジェクトを作成するには、次のコマンドを使用します。

```bash
blade create -t js-theme [project-name]
```

これにより、Node.JSと`yo`がダウンロードされ、`yo liferay-theme`が実行されます。

ワークスペースの外部で作成するのと同じ方法で、ワークスペースでテーマプロジェクトを作成することもできます。

1. Themes Generatorをインストールしたと仮定して、Liferay Workspaceに移動します。 他のプロジェクトと同様に、テーマプロジェクトはどこにでも配置できます。 次のコマンドを実行して、テーマプロジェクトを作成します。

   ```bash
   yo liferay-theme
   ```

1. 新しいテーマフォルダに移動して実行します

   ```bash
   ../gradlew build
   ```

   上記のコマンドは、ワークスペースのルートから1つ下のフォルダにいることを前提としています。プロジェクトを（おそらく`themes`フォルダで）ネストした場合は、それに応じて`gradlew`の呼び出しを調整します。 このコマンドは、LiferayのノードGradleプラグインを使用してフロントエンドテーマを構築します。

Workspaceがプロジェクトを認識していることを確認するには、次のコマンドを使用してすべてのプロジェクトを表示します。

```bash
./gradlew projects
```

## Liferayバンドルをワークスペースに追加する

ワークスペースは、コードのランタイムのダウンロードとセットアップを自動化します。 サーバーでコードを実行する準備ができたら、ほんの数ステップでダウンロードできます。

1. 対象となるLiferayリリースを見つけます。

   **DXP：** [［Liferay Workspace Target Versions］](https://help.liferay.com/hc/ja/articles/360041818312) ページに移動し、バンドルURLをクリップボードにコピーします。

   **CE：** [releases-cdn.liferay.com/portal](https://releases-cdn.liferay.com/portal) を閲覧し、必要なバンドルを見つけて、そのURLをクリップボードにコピーします。

1. ワークスペースのルートフォルダから`gradle.properties`を開き、バンドルURLプロパティを設定します。

   ```properties
   liferay.workspace.bundle.url=[paste the URL from your clipboard]
   ```

   Liferay DXPを使用している場合は、次のプロパティも設定します。

   ```properties
   liferay.workspace.bundle.token.download=true
   liferay.workspace.bundle.token.email.address=[enter the email address registered on liferay.com]
   liferay.workspace.bundle.token.password=[enter your liferay.com password]
   ```

   ファイルを保存して閉じます。

1. バンドルをダウンロードする準備はできましたか。 次のコマンドを実行します。

   ```bash
   ./gradlew initBundle
   ```

   または、Bladeの場合は、次のコマンドを実行します。

   ```bash
   blade server init
   ```

DXPの場合、セキュリティ上の理由から、バンドルのダウンロード後にプロパティファイルからパスワードを削除する必要があります。

## Liferay Workspaceを介したコードのデプロイ

上記の手順で追加したLiferayバンドル、またはLiferayを実行しているDockerコンテナにコードをデプロイできます。

### Liferayバンドルへのコードのデプロイ

Liferay Workspaceに追加されたバンドルにコードをデプロイするのは簡単です。 プロジェクトフォルダから、次のGradleタスクを実行します。

```bash
../gradlew deploy
```

これにより、WorkspaceルートフォルダのGradleラッパースクリプトが呼び出され、ワークスペースに追加されたLiferayバンドルにプロジェクトがデプロイされます。

### Liferay Dockerコンテナへのコードのデプロイ

すでに[Liferay Dockerコンテナを作成](./configuring-a-liferay-docker-container.md)している場合は、ローカルバンドルにデプロイするのと同じくらい簡単にデプロイできます。 同じGradleタスクを実行します。

```bash
../gradlew deploy
```

## 関連トピック

* [LiferayのDockerコンテナの設定](./configuring-a-liferay-docker-container.md)
* [Blade CLIを使用したプロジェクトの生成](../blade-cli/generating-projects-with-blade-cli.md)
