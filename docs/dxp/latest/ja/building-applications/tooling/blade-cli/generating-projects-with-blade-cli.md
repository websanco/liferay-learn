# Blade CLIを使用したプロジェクトの生成

Blade CLIは、Liferay WorkspaceでLiferayプロジェクトを作成、ビルド、およびデプロイするために存在します。 作成したプロジェクトは、IDEにインポートすることも、直接作業することもできます。 ここでは、Liferayプロジェクトを作成および管理するさまざまな方法を学びます。

## Liferay Workspaceの作成

Liferay Workspaceは、プロジェクトとDevOps構成を保存するマシン上の一連のフォルダーです。 Liferay WorkspaceはGradleプロジェクトであるため、任意のIDEにインポートできます。 詳しくは、[Liferay Workspace](../liferay-workspace/what-is-liferay-workspace.md)を参照してください。 Liferay Workspaceを作成するには、次の手順に従います。

1. CLIで、Liferay Workspaceを作成するフォルダーに移動します。

1. 次のコマンドを実行します。

   ```bash
   blade init -v 7.3 [workspace name]
   ```

## プロジェクトの作成

プロジェクトはLiferay Workspaceに存在します。 覚えておくべき重要なオプションは次のとおりです。

**-t：** 使用するプロジェクトテンプレートを指定します。 これらのリストを取得するには、 `blade create -l`と入力します。

**-p：** クラスのパッケージ名を指定します。

**-c：** クラス名を指定します。

**-v：** Liferayのバージョンを指定します。例えば、 `7.1`、 `7.2`、`7.3`または`7.4`。 注：このオプションは省略でき、Bladeのデフォルトはワークスペースで構成されたバージョンになります。

これらをまとめて、「guestbook」というLiferay MVCポートレットを作成する場合は、次のコマンドを使用します。

```bash
blade create -t mvc-portlet -p com.acme.z3x1.portlet -c GuestbookPortlet guestbook
```

これにより、MVCポートレットテンプレート、`com.acme.z3x1.portlet`と呼ばれるデフォルトのパッケージ、`GuestbookPortlet`と呼ばれるポートレットクラス（Liferay 7.4では*ゲストブック*と呼ばれる）を使用してプロジェクトが作成されます。 これで、ワークスペースをIDEにインポートできます。 Liferay IntelliJプラグインまたはLiferay Developer StudioのEclipseプラグインは、拡張サポートを提供します。 Bladeを使用してプロジェクトに新しいワークスペースを作成する場合は、IDEでGradleプロジェクトを更新する必要があることに注意してください。

## サンプルプロジェクトの作成

Liferayは、 [サンプルプロジェクト](https://github.com/liferay/liferay-blade-samples/tree/7.4)のGitHubリポジトリを維持しています。 これらは、プロジェクトの開始点として使用できるさまざまなLiferayテクノロジーの完全に実装されたサンプルです。 ただし、リポジトリにクローンを作成してアクセスするのではなく、Blade CLIを使用してローカルに作成できます。

1. 必要なサンプルプロジェクトを見つけます。

   ```bash
   blade samples
   ```

1. [モデルリスナー](../../../liferay-internals/extending-liferay/creating-a-model-listener.md)の実用的な例が必要だとします。 次のコマンドを入力します。

   ```bash
   blade samples model-listener
   ```

1. サンプルの特定のバージョンが必要な場合は、バージョンを渡すことができます。

   ```bash
   blade samples -v 7.1 model-listener
   ```

## レガシープラグインSDKプロジェクトの変換

バージョン7.0より前のLiferayプロジェクトがある場合、それらはPlugins SDKに含まれています。 これらを6.2以降のLiferayのバージョンで使用するには、プラグインSDKからLiferay Workspaceに移行する必要があります。

1. まだ作成していない場合は、 [Liferay Workspace](#creating-a-liferay-workspace) 作成します。

1. Liferay Workspace内から、次のコマンドを実行します。

   ```bash
   blade convert -s [path to old Plugins SDK] -a
   ```

   これにより、プラグインSDKのすべてのプロジェクトがワークスペースプロジェクトに変換されます。

1. 単一のプロジェクトのみを変換する場合は、代わりに次のコマンドを使用します。

   ```bash
   blade convert -s [path to old Plugins SDK] [name of Plugins SDK project to convert]
   ```

- Service Builderサービスを含むプロジェクトを変換すると、Blade CLIは個別のAPIおよびサービスOSGiモジュールを作成します。 ポートレットはWARのままで、`wars` フォルダに移動します。
- テーマは、Liferay 7.xテーマのようにNodeJSを活用するように変換されます。 Javaベースのテーマを変換するには、代わりにTheme Builder Gradleプラグインを使用する `-t` オプションを追加します。

## 関連トピック

[Liferay Workspace](../liferay-workspace/what-is-liferay-workspace.md)
