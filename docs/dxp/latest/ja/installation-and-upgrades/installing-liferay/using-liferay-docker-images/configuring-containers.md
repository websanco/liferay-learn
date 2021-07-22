# DXPコンテナの構成

DXPインストールで構成可能なものはすべて、DXP Dockerコンテナで構成できます。

設定する最も一般的なものは次のとおりです。

  - [JVMオプション](#jvm-options)
  - [ポータルプロパティ](#portal-properties)
  - [画像定義の環境変数](#image-defined-environment-variables)
  - [システムプロパティ](#system-properties)
  - [System Settings](#system-settings)

<!-- end list -->

``` note::
   The configuration use cases that involve providing a file to the container are demonstrated here using `bind mounts <https://docs.docker.com/storage/bind-mounts/>`_. You can also use `volumes <https://docs.docker.com/storage/volumes/>`_ and in some cases, use a ``docker cp`` command. See `Providing File to the Container <./providing-files-to-the-container.md>`_ for more information.
```

## JVMオプション

TomcatのJVMオプションは、追加または置換できます。

### JVMオプションをCATALINA\_OPTSに追加する

JVM オプションは Tomcat の `CATALINA_OPTS` 変数に追加することができます。この変数は、コンテナを作成する際に `LIFERAY_JVM_OPTS` 環境変数に指定します。

``` bash
docker run -it --name [container] -p 8080:8080 -e LIFERAY_JVM_OPTS=[value\ with\ space] liferay/dxp:[tag]
```

``` warning::
   In the ``LIFERAY_JVM_OPTS`` value, use backslashes to escape space characters. Don't use quotes.
```

コンテナは Tomcat の `CATALINA_OPTS` に追加された `LIFERAY_JVM_OPTS` で実行されます。

### setenv.shファイルの置き換え

JVMオプションを設定する別の方法には、Tomcatの `setenv.sh` スクリプトを上書きすることが含まれます。 `setenv.sh` スクリプトを素早く作成する方法は、DXPコンテナからコピーすることです。 スクリプトのコピーを変更して新しいコンテナで使用する方法は次のとおりです。

1.  既存のDXPコンテナがある場合は、起動します。 それ以外の場合は、新しいものを実行します。

    ``` bash
    docker run -it --name tmp-dxp -p 8080:8080 liferay/dxp:[tag]
    ```

2.  `setenv.sh` ファイルをコンテナからコピーします。

    ``` bash
    docker cp tmp-dxp:/opt/liferay/tomcat/bin/setenv.sh .
    ```

3.  [コンテナを停止します](./docker-container-basics.md#stopping-a-container)。

4.  `setenv.sh` のコピーで JVM オプションを設定します。

5.  `files/tomcat/bin` パスを作成するために、ホストフォルダとサブフォルダを作成します。

    ``` bash
    mkdir -p [host folder]/files/tomcat/bin
    cp setenv.sh [host folder]/files/tomcat/bin
    ```

6.  バインドする `-v` オプションでコンテナをホストフォルダにマウントします。

    ``` bash
    docker run -it --name [container] -p 8080:8080 -v [host folder path]:/mnt/liferay liferay/dxp:[tag]
    ```

コンテナは `setenv.sh` スクリプトの JVM オプションを使用します。

``` note::
   Please see `Providing Files to the Container <./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay>`_ for more information on bind mounting to to the container's ``/mnt/liferay`` folder.
```

``` note::
   See `DXP Docker Container Basics <./docker-container-basics.md>`_ for details on starting and operating DXP containers.
```

## ポータルプロパティ

DXPコンテナ [ポータルプロパティ](../../reference/portal-properties.md) は、以下の2つの方法でオーバーライドできます：

  - [Liferay環境変数の使用](#using-liferay-env-variables)
  - [ポータルプロパティファイルの使用](#using-a-portal-properties-file)

### Liferay環境変数の使用

*ポータルプロパティ* ごとに [Env](../../reference/portal-properties.md)変数があります。 Envプロパティは、DXP Dockerコンテナのポータルプロパティをオーバーライドします。

1.  [ポータル プロパティ](https://docs.liferay.com/ce/portal/7.3-latest/propertiesdoc/portal.properties.html) のオンライン 説明で、オーバーライドするプロパティを見つけます。

2.  プロパティ説明のすぐ下に表示される `Env` 変数名をコピーします。 例えば、 [`jdbc.default.jndi.name`](https://docs.liferay.com/ce/portal/7.3-latest/propertiesdoc/portal.properties.html#JDBC) ポータルプロパティのEnv変数は次のとおりです。

    ``` properties
    Env: LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_JNDI_PERIOD_NAME
    ```

3.  コンテナを作成し、 `-e` オプションを使用して新しいポータルプロパティ値を渡します。
   
        docker run -it --name [container] -p 8080:8080 -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_JNDI_PERIOD_NAME=jdbc/MyPool liferay/dxp:[tag]

    ``` warning::
       In the ``Env`` variable value, use backslashes to escape space characters. Don't use quotes.
    ```

    ``` note::
       See `DXP Docker Container Basics <./docker-container-basics.md>`_ for details on starting and operating the containers.
    ```

プロパティは *構成* → *サーバー管理* → *プロパティ* → *ポータルプロパティ* で表示されます。

### ポータルプロパティファイルの使用

DXPコンテナのポータルプロパティは、 `portal-ext.properties` ファイルを使用して上書きできます。 この例では、 [bind mount](./providing-files-to-the-container.md) を使用します。

1.  ホストフォルダと `files` というサブフォルダを作成します。

    ``` bash
    mkdir -p [host folder]/files
    ```

2.  作成した `ファイル` サブフォルダ内の `portal-ext.properties` ファイルにプロパティの上書きを追加します。 例:

    ``` bash
    echo "jdbc.default.jndi.name=jdbc/MyPool" >> [host folder]/files/portal-ext.properties
    ```

3.  `portal-ext.properties` ファイルのフォルダをコンテナの `/mnt/liferay/files` フォルダにマッピングするバインドマウントを含むコンテナを作成します。 この例の `portal-ext.properties` は `ファイル`という名前のフォルダーにあるため、 [マウントをコンテナの `/mnt /liferay` フォルダーにバインドできます](./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay)。

    ``` bash
    docker run -it --name [container] -p 8080:8080 -v [host folder path]:/mnt/liferay liferay/dxp:[tag]
    ```

プロパティは *構成* → *サーバー管理* → *プロパティ* → *ポータルプロパティ* で表示されます。

## 画像定義の環境変数

Liferayイメージは、いくつかの環境変数を定義します。 いくつかの変数は、 [Liferay Home](../../reference/liferay-home.md) パスや、Tomcat をデバッグモードで起動するオプションなど、内部的な設定を行います。 その他の変数は [ポータルプロパティ](../../reference/portal-properties.md)を設定します。 以下は、ポータルプロパティを設定するイメージ定義の環境変数です。

``` properties
LIFERAY_MODULE_PERIOD_FRAMEWORK_PERIOD_PROPERTIES_PERIOD_OSGI_PERIOD_CONSOLE=0.0.0.0:11311
LIFERAY_SETUP_PERIOD_WIZARD_PERIOD_ADD_PERIOD_SAMPLE_PERIOD_DATA=false
LIFERAY_SETUP_PERIOD_WIZARD_PERIOD_ENABLED=false
LIFERAY_TERMS_PERIOD_OF_PERIOD_USE_PERIOD_REQUIRED=false
LIFERAY_USERS_PERIOD_REMINDER_PERIOD_QUERIES_PERIOD_ENABLED=false
```

ポータルプロパティに対応する環境変数は、ポータルプロパティファイルの設定よりも優先されます。

上記を含むすべてのDocker環境変数は不変です。 環境変数を設定する場合、またはLiferayのイメージ定義の環境変数に依存する場合は、必要な値があることを確認してください。

### 環境変数オプション

次に、イメージ定義の環境変数を操作するためのオプションを示します。

1.  画像で定義されたデフォルトを使用します。 自動的に設定されます。

2.  コンテナの実行時に環境変数を設定して、デフォルト値を上書きします。 例えば、 `docker run -e [variable]=[value] ...`です。

3.  割り当てなしで宣言することにより、環境変数を無効にします (すなわち、 `=` 文字)。 形式は次のとおりです `e [varable]`

    イメージ定義の Portal 環境変数を無効にすると、コンテナ起動時の [Portal プロパティ ファイル](#using-a-portal-properties-file) で必要な値を柔軟に指定できます。 例:

    ``` bash
    docker run -e [varable] -v [host folder path]:/mnt/liferay ...
    ```

### 例：画像定義のポータルプロパティ環境変数の操作

次の画像で定義されているポータルプロパティ環境変数は、ユーザーが使用条件に同意する必要がないことを宣言しています。

``` properties
LIFERAY_TERMS_PERIOD_OF_PERIOD_USE_PERIOD_REQUIRED=false
```

これを無効にし、ポータルプロパティファイルを使用して操作する方法は次のとおりです。

1.  環境変数を無効にし、ポータルプロパティファイルのバインドマウントを設定します。

    ``` bash
    docker run -e LIFERAY_TERMS_PERIOD_OF_PERIOD_USE_PERIOD_REQUIRED -v $(pwd):/mnt/liferay ...
    ```

    利用規約の要件は、ポータルプロパティに基づいています。 [デフォルトのポータルプロパティ設定](https://docs.liferay.com/portal/7.3-latest/propertiesdoc/portal.properties.html) （ `LIFERAY_TERMS_PERIOD_OF_PERIOD_USE_PERIOD_REQUIRED`を検索）には、利用規約が必要です。

    ``` properties
    terms.of.use.required=true
    ```

2.  バインドマウントパスにある `portal-ext.properties` ファイルに必要な設定を指定します。 [ポータルプロパティファイル](#using-a-portal-properties-file)を使用するを参照してください。

    ``` bash
    echo "terms.of.use.required=false" >> ./files/portal-ext.properties
    ```

3.  コンテナを再起動します。

コンテナはプロパティ設定を使用します。

## システムプロパティ

[システムプロパティ](https://docs.liferay.com/ce/portal/7.3-latest/propertiesdoc/system.properties.html) は `system-ext.properties` ファイルを使用して上書きできます。 この例では、 [bind mount](./providing-files-to-the-container.md) を使用します。

1.  `[host folder]/files/tomcat/webapps/ROOT/WEB-INF/classes` を作成します。

    ``` bash
    mkdir -p [host folder]/files/tomcat/webapps/ROOT/WEB-INF/classes
    ```

2.  `[host folder]/files/tomcat/webapps/ROOT/WEB-INF/classes` フォルダ内の `system-ext.properties` ファイルにプロパティの上書きを追加します。 例:

    ``` bash
    echo "net.sf.ehcache.skipUpdateCheck=false" >> [host folder]/files/tomcat/webapps/ROOT/WEB-INF/classes/system-ext.properties
    ```

3.  バインドする `-v` オプションでコンテナをホストフォルダにマウントします。

    ``` bash
    docker run -it --name [container] -p 8080:8080 -v [host folder path]:/mnt/liferay liferay/dxp:[tag]
    ```

    ``` note::
       Please see `Providing Files to the Container <./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay>`_ for more information on bind mounting to to the container's ``/mnt/liferay`` folder.
    ```

プロパティは、コントロールパネルの *構成* → *サーバー管理* → *プロパティ* → *システムプロパティ*で表示されます。

## System Settings

DXPシステム設定は、 [コントロールパネル](https://help.liferay.com/hc/en-us/articles/360029131591-System-Settings) または [構成ファイル](https://help.liferay.com/hc/en-us/articles/360029131651-Understanding-System-Configuration-Files) （`.config` ファイル）をコンテナに提供することで構成できます。 最初から `.config` ファイルを作成するか、UIからコンポーネント設定値をエクスポートすることができます。

次のいずれかの方法を使用して、システム設定を変更します。

  - [新しいコンテナへの構成の適用](#applying-configurations-to-a-new-container)
  - [実行時の構成ファイルの適用](#applying-configuration-files-at-run-time)
  - [コントロールパネルの使用](https://help.liferay.com/hc/en-us/articles/360029131591-System-Settings)

### 新しいコンテナへの構成の適用

コンテナをまだ作成していない場合は、次の手順に従って、 [バインドマウント](./providing-files-to-the-container.md)を使用して `.config` ファイルを新しいコンテナに提供します。

1.  `[host folder]/files/osgi/configs` を作成するために、ホストフォルダとサブフォルダを作成します。

    ``` bash
    mkdir -p [host folder]/files/osgi/configs
    ```

2.  `.config` ファイルをホストフォルダの `files/osgi/configs` サブフォルダにコピーします。 例:
   
        cp ~/*.config [host folder path]/files/osgi/configs

3.  バインドする `-v` オプションを使用してDXPコンテナを実行すると、ホストフォルダがマウントされます。

    ``` bash
    docker run -it --name [container] -p 8080:8080 -v [host folder path]:/mnt/liferay liferay/dxp:[tag]
    ```

    ``` note::
       Please see `Providing Files to the Container <./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay>`_ for more information on bind mounting to to the container's ``/mnt/liferay`` folder.
    ```

システムコンポーネントの構成は、そのコンポーネントの画面の *構成* → *システム設定*、DXPのコントロールパネルに表示されます。

### 実行時の構成ファイルの適用

すでにコンテナがある場合は、次のような `docker cp` コマンドを使用して、実行時に `.config` ファイルをコンテナにコピーできます。

``` bash
docker cp [config file] [container]:/opt/liferay/osgi/configs
```

## まとめ

DXPコンテナのJVMオプション、ポータルプロパティ、DXPイメージ環境変数、システムプロパティ、およびシステム設定を構成する方法をマスターしました。

## 追加情報

  - [DXP Dockerコンテナの基本](./docker-container-basics.md)
  - [DXPコンテナのライフサイクルとAPI](./container-lifecycle-and-api.md)
  - [コンテナへのファイルの提供](./providing-files-to-the-container.md)
  - [コンテナへのアプリやその他のアーティファクトのインストール](./installing-apps-and-other-artifacts-to-containers.md)
  - [DockerでDXPにパッチを適用する](./patching-dxp-in-docker.md)
