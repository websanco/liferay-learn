# パッチツールの構成

> サブスクライバー

パッチツールは、どのDXPインストールにも適応できます。 ツールの自動検出オプションは、パッチツールを構成する最も簡単な方法です。 DXPをアプリケーションサーバーにインストールした場合、またはパッチツールのリクエストをプロキシする必要がある場合は、パッチツールを手動で設定できます。

**概要：**

* [`自動検出を使用した自動構成`](#automatic-configuration-using-auto-discovery)
* [構成のテスト](#testing-the-configuration)
* [手動設定](#manual-configuration)
* [パッチプロファイルの使用](#using-patching-profiles)

<a name="自動検出を使用した自動構成" />

## `自動検出を使用した自動構成`

Patching Toolの `auto-discovery` コマンドは、DXPファイルをスキャンし、パッチに使用するプロパティファイルにそれらのパスを書き込みます。

パッチ適用ツールを自動的に構成するには、次の手順に従います。

1. コマンドラインを開き、 `patching-tool` フォルダーに移動します。

    ```bash
    cd patching-tool
    ```

1. auto-discoveryコマンドを実行します。

    [Liferay Home](../../reference/liferay-home.md) がパッチツールの親フォルダである場合は、次のコマンドを実行します。

    ```bash
    ./patching-tool.sh auto-discovery
    ```

    [Liferay Home](../../reference/liferay-home.md) が別の場所にある場合は、コマンドにLiferay Homeパスを指定します。

    ```bash
    ./patching-tool.sh auto-discovery [path to Liferay Home]
    ```

ツールは、構成をファイル `default.properties`書き込みます。

Liferay Homeへの間違ったパスを指定した場合、またはLiferay Homeが親フォルダにない場合、パッチツールは次のようなエラーを報告します。

```
The .liferay-home has not been detected in the given directory tree.

Configuration:
patching.mode=binary
war.path=../tomcat-9.0.17/webapps/ROOT/
global.lib.path=../tomcat-9.0.17/lib/ext/
liferay.home=**[please enter manually]**

The configuration hasn't been saved. Please save this to the default.properties file.
```

次のいずれかの方法を使用して問題を解決します。

* Liferayホームがパッチツールのツリーにある場合は、Liferayホームフォルダーに `.liferay-home` ファイルを作成し、 `auto-discovery` コマンドを再実行します。
* プロパティファイルの `liferay.home` プロパティでLiferayホームパスを指定します（例： `default.properties`）。

<a name="構成のテスト" />

## 構成のテスト

パッチツールが設定されている場合、 `info` コマンドを実行すると、次のようなすべての製品情報とパッチ情報が報告されます。

```
/patching-tool>./patching-tool.sh info
Loading product and patch information...
Product information:
  * build number: 7310
  * service pack version:
    - available SP version: 1
    - installable SP version: 1
  * patching-tool version: 3.0.5
  * time: 2020-09-01 14:02Z
  * host: 91WRQ72 (8 cores)
  ...
```

情報が正しくない場合は、構成を手動で編集します。

<a name="手動設定" />

## 手動設定

パッチツールプロパティファイルを編集します。 `auto-discovery` コマンドは、デフォルトで `default.properties` ファイルを作成するか、指定したファイル（例： [Patching Profile](#using-patching-profiles) ）を作成します。

### 一般的なプロパティ

| プロパティ                               | 説明                                                                                                                                                                     |
|:----------------------------------- |:---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `global.lib.path`                   | グローバルクラスパス上の `.jar` ファイルを保存する場所を指定します。 ヒント：`portal-kernel.jar`はグローバルクラスパス上にあります。<br><br>DXP 7.2では、`global.lib.path`は、`patching.mode`が`binary`の場合にのみ使用されます。 |
| `liferay.home`                      | [Liferay Home](../../reference/liferay-home.md) フォルダーを指定します。これは通常、DXPの `osgi` および `tools` フォルダーの親フォルダーです。                                                              |
| `module.framework.core.path`        | DXPの `osgi/core` フォルダーへのパス。                                                                                                                                            |
| `module.framework.marketplace.path` | DXPの `osgi/marketplace` フォルダーへのパス。                                                                                                                                     |
| `module.framework.modules.path`     | DXPの `osgi/modules` フォルダーへのパス。                                                                                                                                         |
| `module.framework.portal.path`      | DXPの `osgi/portal` フォルダーへのパス。                                                                                                                                          |
| `module.framework.static.path`      | DXPの `osgi/static` フォルダーへのパス。                                                                                                                                          |
| `patches.folder`                    | パッチを保存する場所を指定します。 デフォルトの場所は `./patches`です。                                                                                                                             |
| `patching.mode` （**DXP 2.0のみ**） | パッチには、更新されたバイナリファイルとソースファイルが含まれています。 モードは、適用するファイルタイプを決定します。<br><br> **`binary`（デフォルト）：DXPインストールの更新用。<br>** `source`：DXPを拡張するソースツリーの更新用。               |
| `source.path` （**DXP 2.0のみ**） | DXPソースツリーの場所を指定します。 `source.path`は、`patching-mod`eが`source`の場合にのみ使用されます。                                                                                               |
| `war.path`                          | DXP Webアプリケーションへのパス（展開されたフォルダー構造または `.war` ファイルへのパス）を指定します。                                                                                                            |

### プロキシ設定

Service Packの検出は、プロキシサーバーの背後で利用できます。 プロキシを構成するには、次の設定グループのいずれかを使用し、 `［PROXY_IP_ADDRESS］`を含むすべての値を置き換えます。

```properties
### Proxy settings

# HTTP Proxy

#proxy.http.host=[PROXY_IP_ADDRESS]
#proxy.http.port=80
#proxy.http.user=user
#proxy.http.password=password

# HTTPS Proxy

proxy.https.host=[PROXY_IP_ADDRESS]
proxy.https.port=80
proxy.https.user=user
proxy.https.password=password

# SOCKS Proxy

#proxy.socks.host=[PROXY_IP_ADDRESS]
#proxy.socks.port=1080
#proxy.socks.user=user
#proxy.socks.password=password
```

<a name="パッチプロファイルの使用" />

## パッチプロファイルの使用

自動検出を実行するか、手動でプロファイルを作成することにより、複数のランタイムのプロファイルを作成できます。 DXPランタイムを自動検出するには、次のようなパラメータを使用してパッチツールを実行します。

```bash
./patching-tool.sh [name of profile] auto-discovery [path/to/Liferay Home]
```

これにより、同じ検出プロセスが実行され、プロファイル情報が `［プロファイル名］ .properties`というファイルに書き込まれます。 または、 `patching-tool` フォルダーでプロファイルプロパティファイルを作成および編集できます。

プロファイルを作成したら、パッチツールのコマンドで使用できます。 たとえば、次のコマンドは `test-server.properties`というプロファイルファイルを使用してパッチをインストールします。

```bash
./patching-tool.sh test-server install
```

<a name="追加情報" />

## 追加情報

* [Installing Patches](./installing-patches.md)
* [パッチツールのインストール](./installing-the-patching-tool.md)
