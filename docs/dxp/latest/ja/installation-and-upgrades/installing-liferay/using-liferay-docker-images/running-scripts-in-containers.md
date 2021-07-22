# コンテナでのスクリプトの実行

TomcatおよびDXPファイルの設定、アーティファクトの展開、およびパッチの適用以外に、DXPコンテナで実行したいことが他にもある場合は、スクリプトを使用できます。 コンテナは、 [ライフサイクル](./container-lifecycle-and-api.md)いくつかの時点で特定のフォルダー内のスクリプトをスキャンします。 次の表に、スクリプトの挿入ポイントを示します。

| ライフサイクルフェーズ | 説明                                 | ターゲットコンテナフォルダー                             |
|:----------- |:---------------------------------- |:------------------------------------------ |
| 事前構成        | 構成フェーズの前にスクリプトを実行する                | `/usr/local/liferay/scripts/pre-configure` |
| 構成、設定       | ファイルを `[Liferay Home]`にコピーしてから実行する | `/mnt/liferay/scripts`                     |
| スタートアップ前    | Tomcatを起動する前にスクリプトを実行します           | `/usr/local/liferay/scripts/pre-startup`   |
| シャットダウン後    | Tomcatをシャットダウンした後にスクリプトを実行する       | `/usr/local/liferay/scripts/post-shutdown` |

次のセクションでは、上記のフェーズでのスクリプトの作成と実行について説明します。

## 構成フェーズでのスクリプトの実行

TomcatとDXPを構成する方法が、構成フェーズが提供する方法以外にもある場合は、それらを構成フェーズスクリプトに実装します。 構成フェーズの詳細については、 [DXP Container Lifecycle and API](./container-lifecycle-and-api.md#lifecycle) を参照してください。

ここでは、バインドマウントを使用して構成フェーズスクリプトを設定する手順を示します。

1.  ローカル構成スクリプト用の任意のフォルダーを作成します。

    ``` bash
    mkdir scripts
    ```

    ``` tip::
       If you plan to `mount the container's /mnt/liferay folder <./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay>`_ already, you can put your Configure Phase scripts into a folder called ``scripts`` in your local mount folder to include in the Configure Phase. The ``[local-folder]/scripts`` folder would map to the container's ``/mnt/liferay/scripts`` folder.
    ```

2.  事前設定アクションを実行するためのスクリプトを作成します。

    ``` bash
    echo "inside some-pre-configure.sh" > scripts/configure-phase-script.sh
    ```

3.  スクリプトのフォルダーをコンテナの `/mnt/liferay/scripts` フォルダーにバインドマウントするDockerコンテナを実行します。

    ``` bash
    docker run -v $(pwd)/scripts:/opt/liferay/scripts ...
    ```

エントリポイントは、ファイルを `/mnt/liferay/files` にコピーした後、構成フェーズでスクリプトを実行し、次のメッセージを出力します。

``` messages
[LIFERAY] Executing scripts in /mnt/liferay/scripts:

[LIFERAY] Executing configure-phase-script.sh.
in configure-phase-script.sh
```

## 他のフェーズでのスクリプトの実行

コンテナは、設定フェーズの外でもスクリプトを実行する方法を提供します。

| ライフサイクルフェーズ | 説明                           | ターゲットコンテナフォルダー                             |
|:----------- |:---------------------------- |:------------------------------------------ |
| 事前構成        | 構成フェーズの前にスクリプトを実行する          | `/usr/local/liferay/scripts/pre-configure` |
| スタートアップ前    | Tomcatを起動する前にスクリプトを実行します     | `/usr/local/liferay/scripts/pre-startup`   |
| シャットダウン後    | Tomcatをシャットダウンした後にスクリプトを実行する | `/usr/local/liferay/scripts/post-shutdown` |

コンテナの `/usr/local/liferay/scripts` フォルダーの構造は次のとおりです。

    /usr/local/liferay/scripts
    ├───pre-configure
    ├───pre-startup
    └───post-shutdown

同じ構造のホストフォルダーを作成し（以下を参照）、スクリプトを入力すると、ホストフォルダーを `/ usr/local/liferay/scripts` フォルダーにマッピングすることで、コンテナでスクリプトを使用できるようになります。

    [host folder]
    ├───pre-configure
    ├───pre-startup
    └───post-shutdown

上記のサブフォルダにスクリプトを作成する一般的な手順は次のとおりです。

1.  スクリプトフェーズフォルダーの任意の親フォルダーを作成します。

    ``` bash
    mkdir [host-folder]
    ```

2.  スクリプトフェーズフォルダーを作成します。

    ``` bash
    cd [host-folder]
    mkdir pre-configure pre-startup post-shutdown
    ```

3.  アクションをフェーズフォルダーの任意のスクリプトに実装します。

    ``` bash
    echo "inside pre-configure-script.sh" > pre-configure/some-pre-configure-script.sh
    ```

    ``` bash
    echo "inside pre-startup-script.sh" > pre-startup/some-pre-startup-script.sh
    ```

    ``` bash
    echo "inside some-post-shutdown-script.sh" > post-shutdown/some-post-shutdown-script.sh
    ```

4.  ホストフォルダーをコンテナの `/usr/local/liferay/scripts /` フォルダーにバインドマウントするDockerコンテナを実行します。

    ``` bash
    docker run -v $(pwd)/[host-folder]:/usr/local/liferay/scripts ...
    ```

エントリポイントは、それぞれのフェーズ中にスクリプトを実行し、次のようなメッセージを出力します。

``` messages
[LIFERAY] Executing scripts in /usr/local/liferay/scripts/pre-configure:

[LIFERAY] Executing some-pre-configure-script.sh.
inside some-pre-configure-script.sh
```

## まとめ

これで、コンテナのライフサイクルのすべての部分でスクリプトを実行する方法がわかりました。

## 追加情報

  - [DXP Dockerコンテナの基本](./docker-container-basics.md)
  - [DXPコンテナのライフサイクルとAPI](./container-lifecycle-and-api.md)
  - [コンテナへのファイルの提供](./providing-files-to-the-container.md)
