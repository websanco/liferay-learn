# 匿名ユーザーの設定

使用するシステムによって個人データがどのように処理されるかに関するインターネットユーザーの懸念は当然のことながら強まっています。 Liferayは、ユーザーデータ管理に対処する機能の必要性を認識しています。 *忘れられる権利*の要求を尊重する最も安全な方法は[ユーザーデータの削除](./sanitizing-user-data.md#the-personal-data-erasure-screen)です。

ユーザーデータを保存する必要がある場合は、データの自動匿名化を行うことが 適切です。 匿名化されるユーザーは、そのユーザーがアクセスしたコンテンツから、ユーザーの識別子（ユーザーIDやユーザー名など）が削除される必要があります。 ポータルコンテンツは通常、アプリケーションが正しく機能するためにこの情報を必要とするため、ユーザーの識別子を何かまたは誰かに置き換える必要があります。 その誰かが*Anonymous Anonymous*です。 この無効化されたユーザーは、匿名化されたコンテンツに識別子が割り当てられているユーザーになります。 このIDスワップは匿名化プロセスの重要なステップですが、真に匿名化を実現するには、追加の手動介入が必要になる場合があります。

![匿名化されたコンテンツは、ユーザーのAnonymous Anonymousの識別情報とともに表示されます。](./configuring-the-anonymous-user/images/01.png)

新規ユーザーを匿名ユーザーとして設定する最も簡単な方法は、既存の匿名ユーザー設定を編集して、別のユーザーIDを渡すことです。

既存の設定を編集するには、

1.  [コントロールパネル]→[設定]→[Instance Settings]→[ユーザー]→[匿名ユーザー]に移動します。

2.  既存の設定を編集し、別のユーザーIDを指定します。

    [コントロールパネル] → [ユーザー] → [ユーザーと組織]からユーザーIDを取得します。 ユーザーをクリックして、[ユーザーの編集]画面の[ユーザーディスプレイデータ]セクションでユーザーIDを見つけます。

3.  *[アップデート]* をクリックします。

まったく新しい匿名ユーザーを作成するには、最初にデータの匿名化に使用する[ユーザーを作成](../users/adding-and-managing-users.md)してから、上記と同じ手順に従います。

インスタンスごとに設定できる匿名ユーザーは1人だけです。

![コントロールパネルのインスタンス設定から独自の匿名ユーザーを割り当てます。](./configuring-the-anonymous-user/images/02.png)

## 設定ファイルの使用

すべてのシステム設定およびインスタンス設定と同様に、コントロールパネルのUIで作業する代わりに、`.config`[ファイル](../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)を`[Liferay Home]/osgi/configs/`にデプロイできます。 これを行うには、[ファクトリ設定](../../system-administration/configuring-liferay/configuration-files-and-factories/using-factory-configuration.md)ファイルを利用します。 パターンに従ったファイルを使用して、仮想インスタンスごとに匿名ユーザーを作成します。

``` bash
com.liferay.user.associated.data.web.internal.configuration.AnonymousUserConfiguration.scoped-[uniqueId].config
```

```{tip}
Using the Web ID or Instance ID (equivalent to the Company ID) for each Virtual Instance as its configuration file's `-subname` can help you differentiate between the files at a glance.
```

次のような内容を指定します。

``` properties
companyId=20098
userId=36059
```

[仮想インスタンス](../../system-administration/configuring-liferay/virtual_instances.rst)の`companyId`を見つけるには、[コントロールパネル] → [System] → [仮想インスタンス]に移動します。 表に表示されているインスタンスIDが、設定ファイルで使用する `companyId` です。

```{note}
`Why is there an extra field in the configuration file?`

When configuring the Anonymous User by configuration file, you must provide the Company ID of the instance. In Instance Settings the configuration is already associated to the instance where it's being configured, but the configuration file is deployed at the system scope, so it must contain the ID of the instance where it applies.
```
