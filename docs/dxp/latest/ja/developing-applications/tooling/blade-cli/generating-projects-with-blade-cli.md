# Blade CLIを使用したプロジェクトの生成
Blade CLIは、Liferayプロジェクトを作成、構築、展開するために存在します。 Liferayの「プロジェクト」とは何ですか？ Liferayワークスペースとどのように関連していますか？ --> . 作成したら、これらのプロジェクト（スタンドアロンでもLiferayワークスペースでも）をIDEにインポートするか、直接作業できます。 ここでは、Liferayプロジェクトを作成および管理するさまざまな方法を学びます。

## Liferayワークスペースの作成
Liferayワークスペースは、プロジェクトとDevOps構成を保存するマシン上の一連のフォルダーです。 詳細は、[Liferayワークスペース](../../tooling/liferay-workspace/what-is-liferay-workspace.md)を参照してください。 --> for further information. Liferayワークスペースを作成するには、次の手順に従います。

1.  CLIで、Liferayワークスペースを作成するフォルダーに移動します。

2.  次のコマンドを実行します。

    ``` bash
    blade init -v 7.3 [workspace name]
    ``` <!-- Let's ask the blade team to add some sort of success message to this command. Also - when I ran this to test (it was my first time) I thought that the brackets were required - but I ended up creating a workspace with brackets in the folder path. would it be clearer to say `blade init -v 7.3 your-workspace-name` ? --> ## プロジェクトを作成する
ほとんどの場合、プロジェクトは、 Liferayワークスペースに存在します。 ワークスペース内でもスタンドアロンでも、プロジェクトの作成は同じ方法で行われます。 覚えておくべき重要なオプションは次のとおりです。

**-t：** 使用するプロジェクトテンプレートを指定します。 これらのリストを取得するには、 `blade create -l`と入力します。

**-p：** クラスのパッケージ名を指定します。

**-c：** クラス名を指定します。

**-v：** Liferayのバージョンを指定します。例えば、 `7.1`、 `7.2`、又は `7.3`。

これらをまとめて、「guestbook」というLiferay MVCポートレットを作成する場合は、次のコマンドを使用します。

``` bash
blade create -t mvc-portlet -p com.liferay.docs.portlet -c GuestbookPortlet -v 7.3 guestbook
```

これにより、MVCポートレットプロジェクトが作成されます。 スタンドアロンプロジェクトは任意のIDEにインポートできます。 Liferayワークスペースは、Liferay IntelliJプラグインを使用してIntelliJに、またはLiferay Developer Studioを使用してEclipseにインポートできます。

``` tip::
If you run this command from inside a Liferay Workspace, your project is created in the Workspace, and can take advantage of all the infrastructure and automation that Workspace offers.
```

## サンプルプロジェクトの作成

Liferayは、 [サンプルプロジェクト](https://github.com/liferay/liferay-blade-samples/tree/7.3)GitHubリポジトリを維持しています。 これらは、プロジェクトの開始点として使用できるさまざまなLiferayテクノロジの完全に実装されたサンプルです。 ただし、リポジトリにクローンを作成してアクセスするのではなく、Blade CLIを使用してローカルに作成できます。

1.  必要なサンプルプロジェクトを見つけます。

    ``` bash
    blade samples
    ```

2.  [モデルリスナー](../../../liferay-internals/extending-liferay/creating-a-model-listener.md)実際の例が必要だとします。 次のコマンドを入力します。

    ``` bash
    blade samples model-listener
    ```

3.  サンプルの特定のバージョンが必要な場合は、バージョンを渡すことができます。

    ``` bash
    blade samples -v 7.1 model-listener
    ```

## レガシープラグインSDKプロジェクトの変換

バージョン7.0より前のLiferayプロジェクトがある場合、それらはPlugins SDKに含まれています。 これらを6.2以降のLiferayのバージョンで使用するには、プラグインSDKからLiferayワークスペースに移行する必要があります。

1.  まだ作成していない場合は、 [Liferayワークスペース](#creating-a-liferay-workspace) 作成します。

2.  Liferayワークスペース内から、次のコマンドを実行します。

    ``` bash
    blade convert -s [path to old Plugins SDK] -a
    ```

    これにより、プラグインSDKのすべてのプロジェクトがワークスペースプロジェクトに変換されます。

3.  単一のプロジェクトのみを変換する場合は、代わりに次のコマンドを使用します。

    ``` bash
    blade convert -s [path to old Plugins SDK] [name of Plugins SDK project to convert]
    ``` <!-- end list --> - Service Builderサービスを含むプロジェクトを変換すると、Blade CLIは個別のAPIおよびサービスOSGiモジュールを作成します。 ポートレットはWARのままで、 `wars` フォルダーに移動します。
  - テーマは、Liferay 7.xテーマのようにNodeJSを活用するように変換されます。 Javaベースのテーマを変換するには、代わりにTheme Builder Gradleプラグインを使用する `-t` オプションを追加します。

## 関連トピック
[Liferayワークスペース](../../tooling/liferay-workspace/what-is-liferay-workspace.md) <!-- Placeholder until Workspace articles come through. -->
