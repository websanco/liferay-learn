# テーマのアップグレード

以前のバージョンのLiferay DXP用に開発されたテーマは、新しいバージョンにデプロイするためにアップグレードする必要があります。 `gulp upgrade`タスクを実行すると、Liferayバージョンの参照とテーマの依存関係が更新されます。 これにより、テーマを新しいバージョンのLiferayにデプロイできます。

<a name="prerequisites" />

## 前提条件

Liferayテーマジェネレーターは、アップグレードタスクを実行するために使用されます。 インストールしていない場合は、次のコマンドを使用してインストールしてください。

```bash
npm install -g generator-liferay-theme@10.x.x
```

次のコマンドを使用して、YeomanとGulpの依存関係をインストールします。

```bash
npm install -g yo gulp
```

<a name="running-the-upgrade-task" />

## アップグレードタスクの実行

アップグレードタスクを使用して、テーマのバージョン依存関係を更新します。

```{important}
アップグレードタスクにより、テーマ内のファイルが変更または上書きされる場合があります。 タスクを実行する前に、テーマのファイルをバックアップしてください。 
```

テーマのフォルダ内から次のコマンドを実行します。

```bash
gulp upgrade
```

まだファイルをバックアップしていない場合は、バックアップするように通知する警告が表示されます。 準備ができたら、「y」（または「yes」）と入力してプロンプトを続行します。

```
? We recommend creating a backup of your theme files before proceeding. Are you 
sure you wish to start the upgrade process?
```

コンソールのメッセージで、アップグレードタスクが完了したことを確認します。

```
Finished 'upgrade' after 36 s
```

テーマのバージョン番号が更新され、Liferay DXPの新しいバージョンにデプロイできるようになりました。

<!-- Add Next Steps section when information is available -->