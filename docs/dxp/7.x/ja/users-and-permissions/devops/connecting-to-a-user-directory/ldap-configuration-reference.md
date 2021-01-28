# LDAP設定リファレンス

LDAP構成設定にアクセスするには、*[Control Panel] → [Configuration]* → *[Instance Settings]* → *[Security]* → *[LDAP]* に移動します。 左側には、[Export]、[General]、[Import]、および[Servers]の4つのカテゴリがあります。

## [Export]

**Enable Export：**ユーザーアカウントをLDAPにエクスポートするには、このボックスをオンにします。 リスナーは、`User`オブジェクトに加えられた変更を追跡し、`User`オブジェクトが変更されるたびにLDAPサーバーに更新をプッシュします。 デフォルトでは、ログインごとに`lastLoginDate`などのフィールドが更新されることに注意してください。 エクスポートを有効にすると、ユーザーがログインするたびにユーザーエクスポートが発生します。 `portal-ext.properties`ファイルで次のプロパティを設定することにより、ユーザーの`lastLoginDate`フィールドを更新してもLDAPユーザーエクスポートがトリガーされないようにすることができます。

``` properties
users.update.last.login=false
```

**Enable Group Export：** グループをLDAPにエクスポートします。

![[Export]タブ。](./ldap-configuration-reference/images/01.png)

## [General]

**Enabled：**LDAP認証を有効にするには、このボックスをオンにします。

**Required：**LDAP認証が必要な場合は、このボックスをオンにします。 ユーザーは、LDAPディレクトリに正常にバインドできなければ、ログインできません。 Liferayアカウントを持っているがLDAPアカウントを持っていないユーザーがログインできるようにするには、このボックスをオフにします。

**Use LDAP Password Policy：**Liferayはデフォルトで独自のパスワードポリシーを使用します。 これは、コントロールパネルの[Password Policies]ページで設定できます。 LDAPディレクトリで定義されたパスワードポリシーを使用する場合は、*[Use LDAP Password Policy]* チェックボックスをオンにします。 これを有効にすると、[Password Policies]タブに、ローカルパスワードポリシーを使用していないことが示されます。 ここで、LDAPディレクトリのメカニズムを使用してパスワードポリシーを設定する必要があります。 Liferayはこれらのポリシーを適用できず、LDAPサーバーから返されたメッセージを渡すことしかできません。 これは、サーバーが返すLDAPコントロール内のメッセージを解析することによって行われます。 Liferayのデフォルト設定では、Fedora Directory Serverから返されたメッセージを解析します。 別のLDAPサーバーを使用する場合は、*[System Settings]* → *[Security]* → *[LDAP]* → *[Connection]* でメッセージをカスタマイズする必要があります。

**Method：***[Bind]*（デフォルト）または*[Password Compare]* を選択します。 [Bind]は標準のLDAPバインドを行います。[Password Compare]は、以下のフィールドで指定された暗号化アルゴリズムを使用して、LiferayとLDAPのパスワードを比較しようとします。 [Password Compare]はめったに使用されません。

**Password Encryption Algorithm：**LDAPサーバーがパスワードの暗号化に使用するパスワード暗号化アルゴリズムを選択して、[Password Compare]バインド方式を使用する場合にパスワードを比較できるようにします。 これはめったに使用されません。

![[General]設定タブ。](./ldap-configuration-reference/images/02.png)

## [Import]

次のオプションを使用して、LDAPディレクトリからユーザーデータをインポートできます。

**Enable Import：**このチェックボックスをオンにすると、LDAPディレクトリから一括インポートが行われます。 それ以外の場合、ユーザーはログイン時にインポートされます。

![ZiltoidとRexはログインしているためインポートされました。](./ldap-configuration-reference/images/03.png)

**Enable Import on Startup：**このボックスをオンにすると、Liferayの起動時に一括インポートを実行します。 注：このボックスは、上記の**[Enable Import]**をオンにした場合にのみ表示されます。 Liferayクラスターがある場合は、このチェックボックスをオフのままにしてください。そうしないと、すべてのノードが起動時に一括インポートを実行します。

**Import Interval：**ユーザーを一括インポートする場合に、X分ごとにユーザーをインポートします。

**Import Method：**[User]または[Group]を設定します。 これを[User]に設定すると、Liferayはサーバー接続で指定された場所からすべてのユーザーをインポートします。 [Group]に設定すると、Liferayはすべてのグループを検索し、各グループ内のユーザーをインポートします。 どのグループにも属していないユーザーがいる場合、それらはインポートされません。

**Lock Expiration Time：**LDAPユーザーインポートのアカウントロックの有効期限を設定します。 デフォルトは1日です。

**Import User Sync Strategy：**ユーザーアカウントの同期に使用する戦略を設定します。 オプションは、[Auth Type]（つまり、ユーザーを認証する方法（画面名を使用するなど））と[UUID]（LDAP内にUUID属性が必要）です。

**Enable User Password on Import：**2つのシステム間でユーザーを同期できるように、ユーザーをインポートするときにデフォルトのパスワード（以下を参照）を割り当てます。

**Autogenerate User Password on Import：**ユーザーのインポート時にランダムなパスワードを作成します。

**Default User Password：**ユーザーがLDAP経由で初めてログインするときに割り当てられるデフォルトのパスワードを入力します。

**Enable Group Cache on Import：**インポートされたグループをキャッシュして、データベースアクセスによってインポートが遅くならないようにします。

**Create Role per Group on Import：**LDAPグループごとに、対応するLiferayロールを作成します。

![[Import]画面。](./ldap-configuration-reference/images/04.png)

## [Servers]

**LDAP Servers：**Liferayは、複数のLDAPサーバーへの接続をサポートしています。 *[Add]* ボタンを使用して、LDAPサーバーを追加します。

**Server Name：**LDAPサーバーの名前を入力します。

**Default Values：**いくつかの一般的なディレクトリサーバーがここに表示されます。 これらのいずれかを使用する場合は、それを選択すると、フォームの残りの部分にそのディレクトリのデフォルト値が自動入力されます。

これらの設定は、LDAPへの接続を対象としています。

**Base Provider URL：**LDAPサーバーへのリンク。 LiferayサーバーがLDAPサーバーと通信できることを確認してください。 2つのシステムの間にファイアウォールがある場合は、適切なポートが開いていることを確認してください。

**Base DN：**LDAPディレクトリのベース識別名。通常は組織をモデルにしています。 次のようになります：`dc=companynamehere,dc=com`

**Principal：**デフォルトのLDAP管理者ユーザーIDがここに入力されます。 管理者IDが異なる場合は、代わりにその認証情報を使用してください。 LiferayはこのIDを使用してユーザーアカウントをLDAPとの間で同期するため、管理資格情報が必要です。

**Credentials：**LDAP管理ユーザーのパスワードを入力します。

![新しいLDAPサーバーを追加する。](./ldap-configuration-reference/images/05.png)

LDAPの設定が完了したら、*[保存]* ボタンをクリックします。

### システム設定で使用可能なLDAPオプション

ほとんどのLDAP設定はインスタンス設定から行うことができますが、システム設定でのみ使用できるいくつかのパラメーターがあります。 インスタンス設定から複製された設定もあります。 これらは、新しい仮想インスタンスの*デフォルト*設定を変更します（下記の注を参照）。

``` note::
   システム設定で変更を加えると、現在の仮想インスタンスに影響します。 設定を変更した後に新しい仮想インスタンスを作成した場合、その仮想インスタンスは作成元の仮想インスタンスの設定をデフォルトとして継承します。 たとえば、A、B、Cという名前の仮想インスタンスがあるとします。Aから、[*Error password history keywords*]を変更します。 この変更は、Aにのみ表示され、BまたはCには表示されません。次に、Aから仮想インスタンスDを作成します。DのデフォルトはAの設定に基づいているため（DはAから作成されたので）、[*Error password history keywords*]への変更はDに表示されます（BまたはCには表示されません）。
```

これらのオプションのいずれかを変更する必要がある場合は、*[Control Panel]* → *[Configuration]* → *[System Settings]* に移動します。 *[Security]* セクションに移動し、タイトルにLDAPが含まれるエントリを見つけます。 ここでの唯一の新しい設定は、*接続*エントリにあります。

*接続*エントリを使用して、LDAPサーバーから返される可能性のあるエラーメッセージからフレーズのリストを設定できる、*Error password age keywords*などのエラープロパティを管理します。 ユーザーがLDAPにバインドすると、サーバーは成功または失敗の応答を含む*コントロール*を返します。 これらのコントロールには、エラーを説明するメッセージ、または応答で返される情報が含まれています。 コントロールはLDAPサーバー全体で同じですが、メッセージは異なる場合があります。 ここで説明するプロパティには、これらのメッセージから取得した単語のスニペットが含まれており、Red HatのFedora Directory Serverで動作します。 そのサーバーを使用していない場合、スニペットという単語がLDAPサーバーで機能しない可能性があります。 機能しない場合は、これらのプロパティの値をサーバーのエラーメッセージのフレーズに置き換えることができます。 これにより、Liferayはそれらを認識できます。
