# スレッドとヒープのダンプの作成

`Liferay` サービスのパフォーマンスに問題が発生した場合は、スレッドまたはヒープダンプを使って、自身またはDXP Cloudサポートがインスタンスの最適化または問題のトラブルシューティングを行う必要があります。

次の手順に従って、トラブルシューティング用のスレッドまたはヒープダンプを生成します：

1. [ダンプを生成するスクリプトの選択](#choose-a-script-to-generate-the-dumps)

1. [Liferayサービスシェルからスクリプトを実行](#run-the-script-from-the-liferay-service-shell)

1. [バックアップ経由でダンプをダウンロード](#download-the-dumps-via-a-backup)

1. [終わったらダンプを削除する](#delete-the-dumps-when-you-are-done)

次のセクションでは、DXP Cloud環境のスレッドやヒープダンプを生成するために実行できるスクリプトの例を紹介します。

<a name="choose-a-script-to-generate-the-dumps" />

## ダンプを生成するスクリプトの選択

スレッドダンプとヒープダンプのどちらを生成する場合でも、 `liferay` サービスの [シェル](shell-access.md) を介してスクリプトを実行し、バックアップ経由でダウンロードできるようにすることができます。

<a name="thread-dump-creation-script" />

### スレッドダンプ作成スクリプト

スレッドダンプは、DXP Cloud環境でどのようなプロセスが行われているかを理解するのに役立ちます。 複数のスレッドダンプのセットがあると、Liferayインスタンスに問題のあるパターンが存在する可能性があるかどうかをよりわかりやすく表示できます。

以下のスクリプトを使用して、あらゆるDXP Cloud環境のLiferayインスタンスのスレッドダンプを生成することができます：

```
#!/bin/bash

TARGET_THREAD_DUMP_FOLDER=${TARGET_THREAD_DUMP_FOLDER:-/opt/liferay/data/thread_dumps}

mkdir -p "${TARGET_THREAD_DUMP_FOLDER}"

take_thread_dump() {
    mkdir -p "${TARGET_THREAD_DUMP_FOLDER}/${1}"

    local pid=$(jps | grep -v Jps | awk '{print $1}')

    echo "[DXP Cloud] jstack ${pid} > ${TARGET_THREAD_DUMP_FOLDER}/${1}/threaddump${2}.txt"

    jstack ${pid} > ${TARGET_THREAD_DUMP_FOLDER}/${1}/threaddump${2}.txt
}

take_thread_group() {
    local time=$(date +'%H%M%S')

    echo "[DXP Cloud] Taking thread dumps with timestamp ${time}"

    for num in 1 2 3 4 5 6
    do
        take_thread_dump $time $num
        sleep 10
    done
}

main() {
    for num in 1 2 3 4
    do
        take_thread_group
        sleep 60
    done

    echo "[DXP Cloud] Thread dumps generated"
}

echo "[DXP Cloud] Take thread dumps"
main
```

このスクリプトは、お客様の`liferay` サービスの `$LIFERAY_HOME/data/`フォルダ（デフォルトで`thread_dumps /`という名前）に新しいフォルダを作成し、それぞれがスレッドダンプのセットが含まれている複数のタイムスタンプ付きのサブフォルダを作成します。 デフォルトでは、約60秒間隔のタイムスタンプがある **4つのサブフォルダ** を作成し、各サブフォルダに約10秒間隔で **6つのスレッドダンプ** が含まれます。 スレッドダンプは、 [jstackユーティリティー](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr016.html) で作成されます。

複数の編集を行って、この動作を変更できます：

* 1行目のパスの最後にある `thread_dumps/` を変更することで、スレッドダンプが保存されるディレクトリの名前を変更することができます。 ただし、バックアップでアクセスできるようにするには、 **`data/` directoryの中にフォルダを置いておく必要があります。**

* `メイン` 関数のループで反復回数を変更することで、作成されるセットの数を変更することができます。 `take_thread_group` 関数で同じ変更を行って、セットごとのスレッドダンプの数を変更できます。

* `sleep` コマンドの周期（秒単位）を `メイン` 機能で変更することで、各セットが作成される頻度を変更することができます。 `take_thread_group` 関数で同じ変更を行って、各セット内のスレッドダンプの頻度を変更できます。

<a name="heap-dump-creation-script" />

### ヒープダンプ作成スクリプト

ヒープダンプは、LiferayインスタンスでどのデータがRAMを消費しているかを把握するのに役立ちます。 インスタンスのメモリ割り当てをトラブルシューティングする必要がある場合、問題があるかどうかを判断するために、異なる重要な時間に複数のヒープダンプを取得する必要があるかもしれません。

以下のスクリプトを使って、DXP Cloud環境のLiferayインスタンスのヒープダンプを生成することができます：

```
#!/bin/bash

TARGET_HEAP_DUMP_FOLDER=${TARGET_HEAP_DUMP_FOLDER:-/opt/liferay/data/heap_dumps}

mkdir -p "${TARGET_HEAP_DUMP_FOLDER}"

take_heap_dump() {
    mkdir -p "${TARGET_HEAP_DUMP_FOLDER}/${1}"

    echo "[DXP Cloud] Taking heap dump with timestamp ${1}"

    local pid=$(jps | grep -v Jps | awk '{print $1}')

    echo "[DXP Cloud] jmap -dump:format=b,file=heapdump.txt ${pid}"

    jmap -dump:format=b,file=heapdump.txt ${pid}

    mv heapdump.txt ${TARGET_HEAP_DUMP_FOLDER}/${1}/heapdump.txt
}

main() {
    local time=$(date +'%H%M%S')

    take_heap_dump $time

    echo "[DXP Cloud] Heap dump generated"
}

main
```

このスクリプトは、お客様の`liferay` サービスの `$LIFERAY_HOME/data/`フォルダ（デフォルトで`heap_dumps /`という名前）に新しいフォルダを作成し、単一の新しいヒープダンプが含まれているタイムスタンプ付きのサブフォルダを作成します。 ヒープダンプは、 [jmap ユーティリティー](https://docs.oracle.com/javase/7/docs/technotes/tools/share/jmap.html) で作成されます。

1行目のパスの最後にある `heap_dumps/` を変更することで、スレッドダンプが保存されるディレクトリの名前を変更することができます。 ただし、バックアップでアクセスできるようにするには、 **`data/` folderの中にフォルダを置いておく必要があります。**

<a name="run-the-script-from-the-liferay-service-shell" />

## Liferayサービスシェルからスクリプトを実行する

使用するスクリプトができたら、それをデプロイして、DXP Cloudコンソールの `liferay`サービスのシェルから実行する必要があります。

<a name="save-the-script-to-your-project-repository" />

### スクリプトをプロジェクトのリポジトリに保存する

まず、選んだスクリプトをLiferayインスタンスの `$LIFERAY_HOME` フォルダにデプロイできるディレクトリに保存します。

1. `liferay/configs/{ENV}/diagnostics/`のように、プロジェクトリポジトリのLiferay構成の中に、手動スクリプト用の新しいフォルダを作成します。  適切な `liferay/configs/{ENV}/` ディレクトリにフォルダを作成することで、デプロイされた際にLiferayインスタンスの `$LIFERAY_HOME` にフォルダのコンテンツが表示されるようになります。

    ```bash
    cd liferay/configs/common/
    ```

    ```bash
    mkdir diagnostics/
    ```

1. プロジェクトリポジトリのLiferay構成の中に、手動スクリプト用の新しいフォルダ（ `liferay/configs/{ENV}/diagnostics/`など）に、 `generate_thread_dumps.sh` または `generate_heap_dump.sh` ファイルを作成します。

    ```bash
    touch my **script** name.sh
    ```

1. [目的のスクリプトの内容](#choose-a-script-to-generate-the-dumps) を新しいファイルに保存します。

<a name="deploy-and-run-the-script" />

### スクリプトのデプロイと実行

スクリプトを `liferay/configs/{ENV}/`のサブフォルダに保存したら、スクリプトをデプロイし、DXP Cloudコンソールのシェルから実行する必要があります。

1. [Overview of DXP Cloud Deployment Workflow](../using-the-liferay-dxp-service/overview-of-the-dxp-cloud-deployment-workflow.md) で説明されている手順に従って、スクリプトを適切な環境にデプロイします。

1. DXP Cloudコンソールで、該当する環境の `liferay` サービスページに移動します。

1. ［**Shell**］ タブをクリックします。

    ![Liferayサービスシェルにアクセスして、スクリプトを実行します。](./creating-thread-and-heap-dumps/images/01.png)

    この環境にデプロイしたスクリプトは、このシェルからアクセスできるファイルシステムにあります。

1. シェルで、スクリプトを作成してデプロイしたフォルダに移動します。

    ```bash
    cd diagnostics/
    ```

1. デプロイしたスクリプトを実行する権限があることを確認してください。

    ```bash
    chmod +x ./generate **thread** dumps.sh
    ```

1. スクリプトを実行します。

    ```bash
    ./generate **thread** dumps.sh
    ```

1. スクリプトが完了するまで待って、適切な数のスレッドダンプがあることを確認します。 スクリプトがスレッドまたはヒープダンプの作成を終了すると、確認メッセージが表示されます。

    ```
    [DXP Cloud] Thread dumps generated
    ```

    ```{note}
    （このセクションで）提供しているヒープダンプスクリプト（#heap-dump-creation-script）では、1つのヒープダンプしか生成されません。 異なる時間帯のメモリ使用量を分析するために複数のヒープダンプが必要な場合は、その時間帯にスクリプトを再度実行する必要があります。
    ```

スクリプトが実行し終えたら、スレッドまたはヒープダンプ（複数可）は `$LIFERAY_HOME/データ`内で利用可能なタイムスタンプ付きのサブフォルダに保存されます。 次に、バックアップを経由してダンプをダウンロードし、ローカルに取り出す必要があります。

<a name="download-the-dumps-via-a-backup" />

## バックアップ経由でダンプをダウンロード

`データ` ボリュームのバックアップとともにスレッドまたはヒープダンプをダウンロードして取得します。 ダンプが生成された後、まずバックアップを作成する必要があります。

1. 画面左側のリンクから、同じ環境の ［**バックアップ**］ 画面に移動します。

1. バックアップページで、[**今すぐバックアップ**]をクリックします。

    ![［今すぐバックアップ］をクリックすると、選択した環境のバックアップが手動で作成されます。](./creating-thread-and-heap-dumps/images/02.png)

1. バックアップが作成されたら、新しいバックアップのアクションメニューをクリックし、 ［**ダウンロード**］ をクリックして、 `backup-lfr-<PROJECT_NAME>-<ENV>-<TIMESTAMP>.tgz` という名前のダウンロードリンクを選択して`データ` ボリュームをダウンロードできます。

    ![データボリュームをダウンロードするには、正しいリンクを選択してください。](./creating-thread-and-heap-dumps/images/03.png)

`データ` ボリュームのバックアップを含むダウンロードが開始されます。 ダンプは、スクリプトが指定したサブフォルダに含まれます（例： `thread_dumps/` または `heap_dumps/`）。

<a name="delete-the-dumps-when-you-are-done" />

## 終わった後のダンプの削除

生成されたスレッドやヒープダンプは、特にスクリプトを複数回実行した場合、Liferayサービスの `データ` ボリュームのかなりのスペースを占める可能性があります。 `データ` ボリュームのメモリを浪費しないように、ダンプが不要になったら、環境からダンプを削除するようにしてください。 DXP CloudコンソールでLiferayのサービスシェルを使い、ダンプを削除します。

例えば、DXP Cloudコンソールから以下のコマンドを実行して、スレッドダンプの１つのセットを削除します：

```bash
cd data/thread_dumps/
```

```bash
rm -r <TIMESTAMPED_FOLDER>/
```

[ここで提供されているスクリプト](#choose-a-script-to-generate-the-dumps) は、スレッドとヒープのダンプを、それらがいつ作成されたかを判別するために使用できるタイムスタンプを使用してサブフォルダーに保存します。 トラブルシューティングに不要になったタイムスタンプを持つサブフォルダーのみを削除することも可能です。

<a name="additional-information" />

## 追加情報

* [シェルアクセス](./shell-access.md)
