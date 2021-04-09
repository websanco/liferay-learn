# ユーザーのインポートとエクスポートの設定

インポート/エクスポートの設定では、LDAPとLiferay間のマッピングを構成して、2つのシステム間のユーザーを一致させます。

## LDAPディレクトリでユーザーを検索する

**認証検索フィルター：**この検索フィルターボックスを使用して、ユーザーログインの検索条件を決定します。 デフォルトでは、Liferayはログイン名にユーザーのメールアドレスを使用します。 ここでの値は、使用する[認証タイプ](../../../installation-and-upgrades/securing-liferay/authentication-basics.md#authentication-types)を使用する必要があります。 たとえば、Liferayの認証方法を変更して、電子メールアドレスの代わりに画面名を使用する場合は、入力したログイン名と一致するように検索フィルターを変更します。

    (cn=@screen_name@)

**インポート検索フィルター：** LDAPスキーマに応じて、ユーザーを識別するさまざまな方法があります。 通常は、デフォルト設定で問題ありません。

    (objectClass=inetOrgPerson)

ユーザーのサブセットまたは異なるLDAPオブジェクトクラスを持つユーザーのみを検索する場合は、これを変更できます。

## LDAPユーザー属性のLiferayフィールドへのマッピング

次に、LDAP属性からLiferayフィールドへのマッピングを定義できます。 LDAPユーザー属性はLDAPサーバーごとに異なる場合がありますが、ユーザーを認識するためにLiferayでマップする必要があるフィールドは5つあります。

  - *Screen Name*（例：`uid`または`cn`）
  - *Password*（例：`userPassword`）
  - *Email Address*（例：`mail`または`email`）
  - *First Name*（例：`name`または`givenName`）
  - *Last Name*（例：`sn`）

``` note::
   メールアドレスのないユーザーを作成またはインポートする場合は、 ::portal-ext.properties::に::users.email.address.required=false::を設定する必要があります。 このセットを使用すると、Liferayは、ユーザーIDとプロパティ::users.email.address.auto.suffix=::で定義されたサフィックスを組み合わせた電子メールアドレスを自動生成します。 最後に、LiferayおよびLDAP認証を電子メールアドレス以外のものに設定してください。
```

LDAPグループをLiferayユーザーグループとしてインポートする場合は、メンバーシップ情報が保持されるように、Liferayグループフィールドのマッピングを定義してください。

  - *Group*（例：*member*）

その他のLDAPユーザーマッピングフィールドはオプションです。

コントロールパネルには、よく使用されるLDAP属性のデフォルトのマッピングが用意されています。 独自のマッピングを追加することもできます。

**Test LDAP Users：**属性マッピングを設定した後（上記を参照）、*[Test LDAP Users]* ボタンをクリックすると、LiferayはLDAPユーザーをプルし、プレビューとしてマッピングと照合します。

![[Test LDAP Users]ボタンをクリックすると、ユーザーのリストが表示されます。](./configuring-user-import-and-export/images/01.png)

## LDAPグループのLiferayユーザーグループへのマッピング

このセクションには、LDAPグループをLiferayユーザーグループにマッピングするための設定が含まれています。

**インポート検索フィルター：**これは、LDAPグループをLiferayユーザーグループにマッピングするためのフィルターです。 例,

    (objectClass=groupOfNames)

このマッピングで取得するLDAPグループ属性を入力します。 以下の属性をマップできます。 *[Group Name]* および*[User]* フィールドは必須です。*[Description]* はオプションです。

  - *Group Name*（例`cn`または`o`）
  - *Description*（例：`description`）
  - *User*（例`member`）

**Test LDAP Groups：***[Test LDAP Groups]* ボタンをクリックすると、検索フィルターによって返されたグループのリストが表示されます。

## エクスポート

このセクションには、LiferayユーザーデータをLDAPにエクスポートするための設定が含まれています。

**Users DN：**ユーザーが保存されているLDAPツリーの場所を入力します。 Liferayはユーザーをこの場所にエクスポートします。

**User Default Object Classes：**ユーザーは、リストされているデフォルトのオブジェクトクラスでエクスポートされます。 デフォルトのオブジェクトクラスを確認するには、Apache Directory StudioなどのLDAPブラウザーツールを使用してユーザーを検索し、LDAPに保存されているそのユーザーのオブジェクトクラス属性を表示します。

**Groups DN：**グループが保存されているLDAPツリーの場所を入力します。 Liferayがエクスポートを実行すると、そのグループがこの場所にエクスポートされます。

**Group Default Object Classes：**グループがエクスポートされると、そのグループはリストされたデフォルトのオブジェクトクラスで作成されます。 デフォルトのオブジェクトクラスを確認するには、[Apache Directory Studio](https://directory.apache.org/studio)などのLDAPブラウザーツールを使用してグループを検索し、LDAPに保存されているそのグループのオブジェクトクラス属性を表示します。

すべてのオプションを設定して接続をテストしたら、*[保存]* をクリックします。

``` note::
   ユーザーがLiferayのパスワードのような値を変更した場合、Liferayが変更を行うのに十分なスキーマアクセスを持っている限り、その変更はLDAPサーバーに渡されます。
```

LDAPサーバーをLiferayに接続する方法と、ユーザーのインポート動作、エクスポート動作、およびその他のLDAP設定を構成する方法を理解しました。 他にも設定可能なオプションがあります。 それらについては[Configuring LDAP](./ldap-configuration-reference.md)で説明しています。
