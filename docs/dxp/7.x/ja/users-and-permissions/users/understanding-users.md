# ユーザーについて

Liferayサイトにアクセスするすべての人は、ユーザーと見なされます。 認証されていないユーザーは、*ゲスト*ユーザーと見なされます。 Liferayには、システムを完全に制御し、システムにアクセスできるデフォルトの管理者ユーザーが付属しています。 [ロールと権限](../roles-and-permissions/README.md)は、ユーザーがサイトで表示および実行できる内容を管理するものです。

ユーザーは3つの一般的なカテゴリに分類されます。

| ユーザー                                                              | デフォルトのアクセス                                                            | メモ                                                            |
| ----------------------------------------------------------------- | --------------------------------------------------------------------- | ------------------------------------------------------------- |
| [管理者](../../getting-started/introduction-to-the-admin-account.md) | 完全なシステムアクセス権を持つLiferay管理者                                             | インストール後、このアカウントのパスワードをすぐに変更することを*強く*お勧めします。                   |
| ゲスト                                                               | パブリックページとサイトへのアクセスを表示します。 ほとんどの場合、明示的に許可されていない限り、コンテンツの作成または追加はできません。 | デフォルトでは、ゲストユーザーはLiferayサイトでアカウントを作成して、*パブリック*サイトを表示および操作できます。 |
| ユーザー                                                              | メンバーとなっているページとサイトへのアクセスを表示します。 コンテンツを作成できます。                          | ユーザーは定義されたデフォルトの権限を取得し、組織またはサイト管理者に昇格できます。                    |

## ユーザーの管理

ユーザーは、コントロールパネルの*[Users]* セクションで管理されます。

![コントロールパネルの[Users and Organizations]セクションでユーザーを管理する。](./understanding-users/images/01.png)

ここでは、ユーザーのメタデータ（名前、部門など）、権限、およびアクティベーションステータスを管理できます。

### ユーザーの追加

管理者は、コントロールパネルの[Users and Organizations]セクションまたは[APIを使用](../developer-guide/README.md)してユーザーを追加できます。 新規ユーザーは管理者が作成したり、オープン登録が設定されている場合は自分で登録したり、LDAPサーバーから同期したりできます。 詳細については、[Adding and Managing Users](./adding-and-managing-users.md)を参照してください。

#### オープン登録

デフォルトでは、ゲストユーザーはユーザーアカウントを作成できます。

ユーザーの登録と認証の設定について詳しくは、[Configuring Authentication](../../installation-and-upgrades/securing-liferay/authentication-basics.md)を参照してください。

#### ユーザーディレクトリの設定

LDAPでユーザーを管理する組織は、LDAPサーバーをLiferayと同期してユーザーをインポートできます。 詳細については、[こちらの記事](../devops/connecting-to-a-user-directory/connecting-to-an-ldap-directory.md)を参照してください。


<!-- #### Other Methods

Are there other methods of adding users? -->

## ユーザーアクセスの管理

ユーザーアクセスは、ロールと権限によって制御されます。 権限は、ユーザーが実行できることと実行できないことを定義します。 ロールは、権限をグループ化したものです。 権限は、ロールの権限を定義し、事前設定されたロールにユーザーを割り当てることによって設定されます。 ユーザーのアクセス管理について詳しくは、[Roles and Permissions](../roles-and-permissions/README.md)を参照してください。

## ユーザーの整理

Liferayには、ユーザーの整理と管理に役立つツールがいくつかあります。

[組織](../organizations/understanding-organizations.md)はLiferayのエンティティであり、分散階層でユーザーをグループ化できます。 これは実際には、大規模な組織がユーザーに組織を管理する権限を与え、委任できることを意味します。

![Liferayで組織階層を作成する方法の一例。](./understanding-users/images/02.png)

[ユーザーグループ](../user-groups/creating-and-managing-user-groups.md)は、ユーザーをグループ化して管理とロールの割り当てを簡略化するもう1つの方法です。 ユーザーグループは、ロールを割り当てることができるユーザーの単純なリストです。

## 関連情報

  - [Adding and Managing Users](./adding-and-managing-users.md)
  - [Organizations](../organizations/understanding-organizations.md)
  - [User Groups](../user-groups/creating-and-managing-user-groups.md)
