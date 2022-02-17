# オブジェクトアプリケーションの権限

他のLiferayアプリケーションと同様に、オブジェクトポートレットは権限フレームワークと統合されています。 これは、 [アプリケーション](#application-permissions) と [リソース](#resource-permissions) の権限をユーザーロールに割り当てて、オブジェクトポートレットとそのデータにアクセスできるユーザーを決定できることを意味します。

![ロール権限を定義するときにオブジェクト権限を割り当てます。](./objects-application-permissions/images/01.png)

ユーザーロールへの権限の割り当ての詳細については、[Defining Role Permissions](../../../users-and-permissions/roles-and-permissions/defining-role-permissions.md)を参照してください。

<a name="application-permissions" />

## アプリケーションの権限

アプリケーションの権限は、一般的なアプリケーション関連の操作を実行する機能を付与し、 [リソース関連の権限](#object-resource-permissions) は含まれません。

オブジェクトポートレットには、次のアプリケーション権限があります

| 権限              | 説明                               |
| --------------- | -------------------------------- |
| コントロールパネルへのアクセス | グローバルメニューのオブジェクトにアクセスする機能        |
| 設定              | オブジェクトポートレットの構成オプションを表示および変更する機能 |
| Permissions     | オブジェクトの権限を表示および変更する機能            |
| Preferences     | オブジェクトポートレットの設定を表示および変更する機能      |
| View            | オブジェクトポートレットを表示する機能              |

<a name="resource-permissions" />

## リソース権限

リソース権限は、アプリケーションリソースに関連する特定の機能を付与します。 これらの権限の中には、 [データベースのエンティティ（＝モデルリソース）に対して操作](#object-definition-actions) を実行する機能を付与するものもあります。 また、アプリケーションコンテキストで [リソース関連の操作](#object-related-actions) を行う機能を付与するものもあります（例えば、新しいリソースエンティティを作成する機能など）。

オブジェクトのポートレットには、次のリソース権限があります。

### オブジェクト関連のアクション

| 権限              | 説明                                  |
| --------------- | ----------------------------------- |
| オブジェクト定義を追加     | オブジェクトのドラフトを作成する機能                  |
| システムオブジェクト定義を拡張 | フィールド、関係、およびレイアウトをシステムオブジェクトに追加する機能 |
| Permissions     | オブジェクトに関連する権限を表示および管理する機能           |
| オブジェクト定義を公開     | オブジェクトのドラフトを公開する機能                  |

### オブジェクト定義アクション

| 権限          | 説明                    |
| ----------- | --------------------- |
| Delete      | オブジェクトのドラフトを削除する機能    |
| Permissions | オブジェクトの権限を表示および変更する機能 |
| Update      | オブジェクトを更新する機能         |
| View        | オブジェクトを表示する機能         |

<a name="additional-information" />

## 追加情報

* [Introduction to Objects](./introduction-to-objects.md)
* [Creating Objects](./creating-and-managing-objects/creating-objects.md)
* [Managing Objects](./creating-and-managing-objects/managing-objects.md)
