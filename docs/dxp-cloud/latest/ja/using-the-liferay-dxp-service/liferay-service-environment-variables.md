# Liferayサービス環境変数

Liferayサービスには [の範囲の環境変数](#environoment-variables-reference) があり、サービスや他のサービスへの接続、DXPのインストール自体を設定するために使用されます。 DXP Cloud固有の環境変数を使用したり、DXPの [ポータルプロパティ](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html)をオーバーライドする変数を定義したりすることができます。

## ポータルプロパティのオーバーライド

Liferayサービスで環境変数を使用すると、ポータルプロパティファイルで通常定義されている設定を上書きすることができます。

[ポータルプロパティのドキュメント](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html) をチェックして、対応する各ポータルプロパティの環境変数名("Env")を確認してください。 例えば、ポータルプロパティ `company.default.time.zone` を環境変数 `LIFERAY_COMPANY_PERIOD_DEFAULT_PERIOD_TIME_PERIOD_ZONE`でオーバーライドすることができます。

Liferayサービスへの追加については、 [環境変数の定義](../reference/defining-environment-variables.md) を参照してください。

### ポータルプロパティ名を環境変数に変換する

もし、 [ポータルプロパティ・ドキュメント](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html) にポータルプロパティの直訳がない場合は、自分で環境変数を上書きするように変換することもできます。

ポータルプロパティを上書きする環境変数

  - 数字で始まってはいけません。

  - 前方に `LIFERAY_` という接頭語を付けなければならない。

  - 大文字、数字、アンダースコア（`_`）のみで設定　されています。 この制約に当てはまらない文字は、対応する [`CharPool`](https://docs.liferay.com/dxp/portal/7.3-latest/javadocs/modules/core/petra/com.liferay.petra.string/) または [Unicode](https://unicode-table.com/en/) 終端に変換しなければなりません（10進数に変換）。

これらの要件を満たすためには、すべてのポータルプロパティをこのフォーマットに変換する必要があります。 これにより、DXP Cloudはフルネームを適切に認識し、対応するポータルプロパティと一致させることができます。

以下の手順で、ポータルプロパティ名を環境変数名に変換します。

1.  名前に含まれる文字のうち、アルファベット、数字、アンダースコア（ピリオドを含む）ではないものを、対応する [`CharPool`](https://docs.liferay.com/dxp/portal/7.3-latest/javadocs/modules/core/petra/com.liferay.petra.string/) またはUnicodeのエンドポイントに変換し、アンダースコアで囲みます。

    たとえば、ピリオド文字（`.`）を `_PERIOD_`、または `_46_` （Unicodeを使用する場合）に変換します。

2.  変数名の先頭にプレフィックス `LIFERAY_` を付けます。

3.  任意の文字を大文字に変換します。

例えば、ポータルプロパティ名 `setup.wizard.enabled`を例にとると、 `CharPool` というエンドポイントを使って、環境変数の名前に変換することができます。 `LIFERAY_SETUP_PERIOD_WIZARD_PERIOD_ENABLED`となります。

## 環境変数リファレンス

以下の環境変数は、環境変数UIまたはLiferayサービスの `LCP.json` ファイルで設定できます。

| 名前                                     | デフォルト値 | 説明                                                                                             |
| -------------------------------------- | ------ | ---------------------------------------------------------------------------------------------- |
| `LCP_DATABASE_PORT`                    | `3306` | readOnlyユーザーが使用するデータベースポートの設定　を設定します。 `DATABASE_SERVICE_PORT` infra 環境変数が定義されている場合は、それを上書きします。 |
| `LCP_LIFERAY_JDBC_CONNECTION_URL`      |        | データベースへの接続に使用されるURLです。 これは、データベース名とホストを直接設定するために使用することができます。 値は、 `jdbc:mysql://`で始まるものとします。    |
| `LCP_LIFERAY_JDBC_DRIVER`              |        | Liferayサービスが使用するMySQLドライバを指定することができます。 DXPバージョン7.0および7.1では、これはクラスター設定　にも使用されます。                |
| `LCP_PROJECT_LIFERAY_CLUSTER_ENABLED`  | `true` | クラスタリングやノード間の通信を有効にするかどうか。                                                                     |
| `LCP_PROJECT_MONITOR_DYNATRACE_TENANT` |        | 8文字の文字列です。 お客様のDynatrace SaaSアカウントのURL（プレフィックス）の一部です。                                          |
| `LCP_PROJECT_MONITOR_DYNATRACE_TOKEN`  |        | *Dynatraceのデプロイ* → *インストールの開始* → *PaaS監視の設定* → *インストーラのダウンロード*.                                |
| `LIFERAY_JAVA_OPTS`                    |        | `CATALINA_OPTS` に付加されて、デフォルトの推奨オプションをオーバーライドするJVMオプションです。                                      |

これらの変数は、代わりにLiferayサービスのSecrets</a> として

を定義する必要があります。</p> 

| 名前                                           | デフォルト値 | 説明                                                        |
| -------------------------------------------- | ------ | --------------------------------------------------------- |
| `LCP_SECRET_DATABASE_NAME`                   |        | データベース接続（jdbc、jdbc ping、および読み取り専用のユーザー接続）に使用されるデータベース名です。 |
| `LCP_SECRET_DATABASE_PASSWORD`               |        | jdbc（およびjdbc ping）の設定にのみ使用されるデータベースのパスワードです。              |
| `LCP_SECRET_DATABASE_READONLY_USER`          |        | 読み取り専用のユーザー名です。                                           |
| `LCP_SECRET_DATABASE_READONLY_USER_PASSWORD` |        | 読み取り専用のユーザーのパスワードです。                                      |
| `LCP_SECRET_DATABASE_USER`                   |        | プライマリデータベースユーザーのユーザー名です。 jdbcおよびjdbc pingの接続に使用されます。      |
