# LDAPディレクトリへの接続

Lightweight Directory Access Protocol （LDAP）サーバーは、Liferay DXPの一般的なユーザーストアです。 LDAPは、システム設定のシステムスコープまたはインスタンス設定のインスタンススコープで設定できます。 ユーザーはLDAPからインポートしたりLDAPにエクスポートしたりできます。

<a name="adding-a-new-ldap-server-connection" />

## 新しいLDAPサーバー接続の追加

インスタンスレベルでLDAP構成設定にアクセスするには、

1. **コントロールパネル&rarr;、設定** &rarr;、 **インスタンス設定** へ行きます。

    ![LDAPは、インスタンスレベルとシステムレベルで設定できます。](./connecting-to-an-ldap-directory/images/01.png)

1. **LDAP** &rarr; **サーバー** をクリックします。
1. ［**追加**］ ボタンをクリックして、LDAPサーバー接続を追加します。
1. LDAPサーバーの構成値を入力します。 詳細は、 [構成リファレンス](#ldap-server-configuration-reference) を参照してください。

ただし、デフォルト値を修正するには、場合によって［最適値］を表す残りの設定をカスタマイズする必要があります。 通常、デフォルトの属性マッピングは、ユーザがログインしようとしたときにLiferayデータベースに再同期するのに十分なデータを提供します。 LDAPサーバーへの接続をテストするには、 ［**Test LDAP Connection**］ ボタンをクリックします。

LDAPサーバーが複数ある場合は、上/下矢印を使用して、優先順位に従ってサーバーを配置できます。 追加するLDAPサーバーの数に関係なく、各サーバーには同じ設定オプションがあります。

### システム設定スコープの使用

または、［System Settings］メニューから、またはOSGi `.config`ファイルを使用して、システム設定スコープでLDAPサーバー接続を定義できます。

```{tip}
［*Instance Settings*］のLDAPサーバー設定画面には、LDAP接続の設定を支援するユーティリティがあります。 このユーティリティを使用して、システム設定スコープで入力する前に、まず設定を検証できます。
```

`.config`ファイルを使用する最も簡単な方法は、GUIを使用して構成をエクスポートすることです。 その後、結果の`.config`ファイルを必要な場所（クラスター内の他のノードなど）で使用できます。

```{note}
LDAPサーバー設定に［config］ファイルを使用するには、サーバーがシステムスコープではなくインスタンススコープで定義されているため、エクスポートされた設定ファイルで仮想インスタンスID（ソースでは、変数名は［companyId］）を指定する必要があります。 これを行うには、次のようにファイルのどこかに仮想インスタンスIDを指定します。

    companyId=1234

バーチャル・インスタンスIDは、*コントロールパネル* -> *設定* -> *仮想インスタンス* で確認できます。
```

<a name="ldap-server-configuration-reference" />

## LDAPサーバー設定リファレンス

**Server Name：** LDAPサーバーの名前を入力します。

**Default Values：** いくつかの一般的なディレクトリサーバーがここに表示されます。 これらのいずれかを使用する場合は、それを選択すると、フォームの残りの部分にそのディレクトリのデフォルト値が自動入力されます。

これらの設定は、LDAPへの接続を対象としています。

**Base Provider URL：** LDAPサーバーへのリンク。 LiferayサーバーがLDAPサーバーと通信できることを確認してください。 2つのシステムの間にファイアウォールがある場合は、適切なポートが開いていることを確認してください。

**Base DN：** LDAPディレクトリのベース識別名。通常は組織をモデルにしています。 次のようになります：`dc=companynamehere,dc=com`

**Principal：** デフォルトのLDAP管理者ユーザーIDがここに入力されます。 管理者IDが異なる場合は、代わりにその認証情報を使用してください。 LiferayはこのIDを使用してユーザーアカウントをLDAPとの間で同期するため、管理資格情報が必要です。

**Credentials：** LDAP管理ユーザーのパスワードを入力します。

<a name="checkpoint" />

## チェックポイント

LiferayのLDAP接続の微調整に進む前に、次の手順が実行されていることを確認してください。

1. LDAP接続が有効になっている。 必要に応じて、バインドされたユーザーのみがログインできるように、LDAP認証が必要になる場合があります。

1. **エクスポート/インポート** ：クラスター環境のユーザーの場合は、起動時に各ノードに大量のインポートが発生しないように、［Enable Import/Export on Startup］を無効にする必要があります。

1. LDAPサーバーを追加するとき、 ［**サーバー名**］ 、 ［**Default Values**］ 、 ［**つながり**］ の値が正しい。 保存する前に、 ［**Test LDAP Connection**］ をクリックすることをお勧めします。

<a name="using-ssl-to-connect-to-an-ldap-server" />

## SSLを使用してLDAPサーバーに接続する

LDAPディレクトリをSSLモードで実行してネットワーク上の資格情報を暗号化する場合は、2つのシステム間で暗号化キーと証明書を共有するための追加の手順を実行する必要があります。

たとえば、LDAPディレクトリがWindows Server 2003上のMicrosoft Active Directoryの場合、次のように証明書を共有します。

1. **スタート** &rarr; **管理ツール** &rarr; **認証局** をクリックします。

1. 証明機関であるマシンを強調表示し、それを右クリックして、 ［**Properties**］ をクリックします。

1. ［General ］メニューの ［**View Certificate**］ をクリックします。

1. 詳細ビューを選択し、 ［**Copy To File**］ をクリックします。 表示されるウィザードを使用して、証明書をファイルとして保存します。

1. 次のように、証明書を **cacerts keystore** にインポートします。

    ```bash
    keytool -import -trustcacerts -keystore /some/path/java-8-jdk/jre/lib/security/cacerts -storepass changeit -noprompt -alias MyRootCA -file /some/path/MyRootCA.cer
    ```

    `keytool`ユーティリティは、Java SDKの一部として付属しています。

1. コントロールパネルのLDAPページに戻ります。

1. 次のようにプロトコルを`ldaps`に変更し、ポートを`636`に変更して、ベースDNフィールドのLDAP URLを安全なバージョンに変更します。

    ```
    ldaps://myLdapServerHostname:636
    ```

変更を保存します。 LDAPへの通信が暗号化されました。

Liferay DXPが同期のためにLDAPのユーザーを照合する方法を調整または設定するには、[configuring import and export](./configuring-user-import-and-export.md)を参照してください。
