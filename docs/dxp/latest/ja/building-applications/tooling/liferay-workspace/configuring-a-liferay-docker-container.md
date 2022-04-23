# LiferayのDockerコンテナの設定

コンテナの作成とデプロイは、最新の開発者ワークフローの重要な部分です。 Liferay Workspaceでは、Dockerコンテナを開発用に使用することも、完全な作業構成を作成するために使用することも簡単にできます。

コンテナの作成は、次の3つの主要なステップで行われます。

1. コンテナに必要なDockerイメージを選択します。
1. そのコンテナをワークスペースにダウンロードします。
1. 設定とアプリケーションを使用してコンテナを構成します。

以下の手順を実行する前に、Dockerをインストールして実行する必要があります。

## Liferay Dockerイメージの選択

イメージのバージョンはワークスペースのバージョンから継承されるため、ほとんどの場合、Liferay Dockerイメージを選択する必要はありません。 ただし、ワークスペースで指定されているバージョン以外のイメージバージョンをインストールする必要がある場合は、バージョンを明示的に設定できます。 これを行う必要がない場合は、次のセクションにスキップしてください。

LiferayのDockerイメージは [Docker Hub](https://hub.docker.com/r/liferay/portal/tags) にあります。 それらは2つのカテゴリーに分類されます。

- [Liferay/Portal](https://hub.docker.com/r/liferay/portal)
- [Liferay/DXP](https://hub.docker.com/r/liferay/dxp)

1. 上記のリンクを使用して、必要なLiferayコンテナのバージョンを見つけます。
1. コンテナの完全なタグ名を一覧表示する`docker pull`コマンドに注目してください。 タグ名は、`liferay/dxp:7.3.10-ga1`または`liferay/portal:7.3.6-ga7`のようになります。
1. Liferay Workspaceで`gradle.properties`を開きます。
1. プロパティ`liferay.workspace.docker.image.liferay`を使用してDockerタグを指定します。 次にいくつかの例を示します。

   ```properties
   liferay.workspace.docker.image.liferay=liferay/dxp:7.3.10-ga1
   ```

   ```properties
   liferay.workspace.docker.image.liferay=liferay/portal:7.3.6-ga7
   ```

1. ファイルを保存します。 これで、Dockerイメージを作成する準備が整いました。

## Liferay Dockerイメージを作成する

ワークスペースのルートフォルダから次のコマンドを実行します。

   ```bash
   ./gradlew createDockerContainer
   ```

ワークスペースの名前に`-liferay`が追加されたDockerイメージが作成されます。 たとえば、ワークスペースフォルダが`my-project`の場合、Dockerコンテナは`my-project-liferay`と呼ばれます。 他のDockerイメージと同じようにコンテナを開始/停止できますが、最初にコンテナを構成することをお勧めします。

## Dockerイメージの構成

コンテナを構成するための通常のDockerツールに加えて、Gradleタスクからコンテナを構築すると、 [Liferay Home](../../../installation-and-upgrades/reference/liferay-home.md) を指すフォルダがセットアップされるため、必要なLiferay構成を作成できます。 このフォルダは`configs/docker`にあります。

たとえば、コンテナのGogoシェルへのtelnetアクセスを有効にすることができます。 その方法は以下の通りです。

1. `configs/docker`フォルダに`portal-ext.properties`ファイルを作成し、次のプロパティを追加します。

   ```properties
   module.framework.properties.osgi.console=0.0.0.0:11311
   ```

1. ファイルを保存します。

1. コンテナを起動します。 作成した`portal-ext.properties`ファイルに含まれているプロパティは、実行中のインスタンスに適用され、次のコマンドを使用してGogoシェルにtelnetできるようになります。

```bash
telnet localhost 11311
```

Docker構成は、複数の環境を処理できるより大きな[ワークスペース構成](configuring-liferay-workspace.md)の一部です。 これを使用している場合、`configs/common`に格納されている構成は、`configs/docker`フォルダに配置されている構成とマージされます。 
