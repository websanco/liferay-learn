# 工場構成の使用

ほとんどのLiferay DXPシステム設定では、1つのエントリに1つの設定セットしか許可されていません。 構成を変更するには、単一の構成フォームを更新します。これは、影響を受ける構成 [全体に適用されます](./understanding-configuration-scope.md)。 あまり一般的ではあり *が、構成は同時に共存する複数のエントリをサポートします。これらは*ファクトリ構成*と呼ばれます。 工場構成の場合、各構成セットは固有の構成を提供します。 一般的な使用法は、サードパーティサーバー（LDAPやElasticsearchなど）へのシステムスコープの接続の構成です。 これらのサーバーへの接続ごとに固有の構成値が必要であるため、工場出荷時の構成を使用してそれを可能にします。</p>

``` tip::
    Factory Configuration Example: `Adding Organization types <../../users-and-permissions/organizations/adding-a-new-organization-type.md>`_ is supported, and is useful if you need to model real-life hierarchies or enforce hierarchical rules. In Liferay DXP, each Organization type is created via a factory configuration entry in System Settings.
```

## 工場構成の特定

サービスが出荷時の構成をサポートしている場合、そのシステム設定エントリには[追加]ボタンがあります。

![システム設定エントリに[追加]ボタンがある場合、工場出荷時の構成をサポートしています。](./using-configuration-files/images/01.png)

[追加]ボタン ![Add](../../images/icon-add.png) をクリックして、構成値の新しいセットを追加します。

## ファクトリー構成ファイル

単一インスタンス構成と同様に、システム設定インターフェイス（上記の[追加]ボタンを使用）または [構成ファイル](./using-configuration-files.md)介して工場構成を設定できます。 標準の単一インスタンス構成ファイルは、構成オブジェクトの完全修飾クラス名に `.config`追加したものを使用します。

``` bash
my.service.ServiceConfiguration.config
```

サービスが工場出荷時の構成をサポートしている場合は、構成の最初のインスタンス `-default.config`を呼び出す規則を使用します。 デフォルトの組織タイプの名前は次のようになっています。

``` bash
com.liferay.organizations.internal.configuration.OrganizationTypeConfiguration-default.config
```

次のインスタンスには、一意の *サブネーム* （ *以外のデフォルト*）が含まれています。 このインスタンスを使用する必要があるときにわかりやすい名前を使用することをお勧めします。 [新しい組織タイプの追加](../../users-and-permissions/organizations/adding-a-new-organization-type.md)の例に続いて、*リーグ*タイプを設定ファイル

``` bash
com.liferay.organizations.internal.configuration.OrganizationTypeConfiguration-league.config
```

``` warning::
   Providing a configuration file with a subname forces a factory configuration scenario, even if the service isn't designed to accept multiple configuration entries. Use the System Settings UI as described above to determine if using factory configurations is supported for a configuration entry. 
```

工場出荷時の構成をサポートする一部のシステム設定エントリには、デフォルトインスタンスの構成ファイルが付属していません（例：匿名ユーザーエントリ）。 工場構成ファイルをエクスポートして `.config` ファイルを取得する場合、 `-default.config` 命名規則は使用されません。 代わりに、それが最初の出現か追加の出現かに関係なく、そのサブネームには保証された一意の識別子が与えられます。

``` bash
com.liferay.user.associated.data.web.internal.configuration.AnonymousUserConfiguration-6befcd73-7c8b-4597-b396-a18f64f8c308.config
```

あなたが別のシステムに展開するための設定ファイルをエクスポートしている場合は、最初の後、エクスポートファイル名の一部の名前を変更することができます `-` より記述サブネームを使用します。 注意：ファイルの名前を変更して、エクスポート元と同じシステムにデプロイすると、新しいサブネームにより、完全に新しい構成としてマークされます。 この場合、名前が変更されたインスタンスだけでなく、追加の構成インスタンスが作成されます。

``` warning::
   For configuration entries supporting factory configurations, omitting the subname from a `.config` file's name causes System Settings to disallow adding new entries for the configuration entry targeted by this `.config` file. This is caused by a known bug. See `LPS-76352 <https://issues.liferay.com/browse/LPS-76352>`_ for more information. Once an improperly named configuration file is deployed, you can't add any entries for the configuration in question from its System Settings entry.

   Deploying an erroneous (lacking a subname) `.config` file doesn't disable anything permanently. Rename the file using the proper convention described above or remove it entirely and start over.
```
