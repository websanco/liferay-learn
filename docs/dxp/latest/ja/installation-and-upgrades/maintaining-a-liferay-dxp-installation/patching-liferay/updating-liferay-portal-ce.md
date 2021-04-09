# LiferayポータルCEの更新

LiferayポータルCEの修正は、新しいポータルCE GAリリースとして提供されます。 たとえば、CE 7.2 GA2はCE 7.2 GA1に存在するバグを修正します。 Liferay Portal CE用のパッチツールはありません。 次に、Portal CEインストールを新しいGAリリースに更新する手順を示します。

1.  [元に戻す場合に備えて、DXPシステム](../backing-up.md) バックアップします。

2.  アプリケーションサーバーをシャットダウンします。

3.  新しい場所またはソース管理ブランチに新しいPortal CE GAリリースをインストールします。

4.  アプリ、プラグイン、モジュール、作成したファイルやカスタマイズしたファイル(例えば、`portal-ext.properties`、`web.xml`、OSGi `.config`ファイルなど)を新しい場所/ブランチに移行します。

5.  DXPキャッシュをクリーンアップします。

    `[Liferay Home]/osgi/state` フォルダーを削除します。

    ``` bash
    rm -rf [Liferay Home]/osgi/state
    ```

    `[Liferay Home]/work` フォルダーを空にします。

    ``` bash
    rm -rf work/*
    ```

    アプリケーションサーバーのキャッシュを削除します。 キャッシュの場所については、アプリケーションベンダーのドキュメントを参照してください。

    ``` warning::
       **Do not delete these two files:** ``patching-backup-deps.zip`` and ``patching-backup.zip``. The Patching Tool creates them in the DXP application's ``WEB-INF`` folder. The Patching Tool examines them to determine previous Fix Pack files to revert before installing a new Fix Pack.
    ```

    ``` note::
       If a module's changes are only internal, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next DXP startup ensures that such modules reinstall with the appropriate state.
    ```

6.  アプリケーションサーバーを再起動します。

7.  `upgrade：check` [Gogo Shellコマンド](https://help.liferay.com/hc/en-us/articles/360029070351-Using-the-Felix-Gogo-Shell) を実行して、データがアップグレードされていないすべてのモジュールをリストします。

8.  これらのモジュールのデータをアップグレードするには、 [Gogo Shellコマンド](../../upgrading-liferay-dxp/upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md) を使用します。

Portal CEインストールを新しいGAリリースに更新しました\！
