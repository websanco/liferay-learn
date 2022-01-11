# Commerceロールのリファレンス

ユーザーロールは、Liferayのアプリケーションやリソースにアクセスして使用するために必要な権限をグループ化します。 各ロールは、インスタンス、サイト、組織、アセットライブラリ、またはアカウントにスコープすることができます。 このスコープにより、どの権限をロールに割り当てることができるかが決まります。 また、各権限には、どのアプリケーションやリソースにアクセスして使用できるかを決定する独自のスコープがあります。 詳細は、 [ロールと権限について](https://learn.liferay.com/dxp/latest/ja/users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.html) を参照してください。

Commerceには [すぐに使えるロール](#out-of-the-box-roles) が４つ含まれています：アカウント管理者、アカウントメンバー、バイヤー、そしてオーダーマネージャーです。

これらのロールはお客様のニーズを満たすものではありますが、お客様の使用目的に合わせて追加のCommerceロールを作成することもできます。 いくつかの [common custom roles](#custom-roles) には カタログマネージャー、在庫マネージャー、出荷マネージャー、インスタンスオーダーマネージャー、および割引マネージャーが含まれています。

```{note}
Liferay 7.3.xおよびそれ以前のCommerceバージョンでは、 [Sales Agent](#sales-agent) もすぐに使用できるロールとして含まれています。
```

## 表示の権限

以下の手順で、Commerceロールに関連する権限を表示および管理します：

1. ［*グローバルメニュー*］(![Global Menu](../../images/icon-applications-menu.png))を開き、［*コントロールパネル*］タブをクリックして、［*ユーザー*］ &rarr; ［*ロール*］に移動します。

   ![コントロールパネルのロールをクリックします。](./commerce-roles-reference/images/01.png)

1. 目的のロールをクリックします。

   * *［アカウント管理者］* (アカウントの役割)
   * *［アカウントメンバー］* （アカウントロール）
   * *［バイヤー］* (サイトロール)
   * *［オーダーマネージャー］* （サイトロール）

   ```{note}
   Liferay 7.3およびそれ以前のバージョンでは、アカウント管理者はサイトロールであり、アカウントメンバーロールはデフォルトロールではありません。 
   ```

1. *[権限の定義]* タブをクリックします。

   ここから、ロールに割り当てられたすべてのアプリケーションおよびリソースの権限の概要を確認できます。

   権限を削除または追加することもできます。

   ![ [権限の定義]タブをクリックして、ロールの権限を表示および管理します。](./commerce-roles-reference/images/02.png)

## すぐに使えるロール

以下のロールは、Commerceの起動時にDXPインスタンスに自動的に追加されます。 これらは、DXPのデフォルトの役割と一緒に含まれており、必要に応じて変更することができます。 詳細は、 [ロール権限の定義](https://learn.liferay.com/dxp/latest/ja/users-and-permissions/roles-and-permissions/defining-role-permissions.html) を参照してください。

### アカウント管理者

> アカウントロール

アカウント管理者は、自分のアカウントを変更したり、ユーザーを招待したり追加したり、他のアカウントメンバーに役割を割り当てたりすることができます。 アカウント管理者は、アカウントを作成または削除することはできません。 この権限はインスタンス管理者にのみあります。

次のアカウント権限は、アカウント管理者がB2Bの環境においてアカウントを変更する権限に関連していますが、カートを開く権限は、管理者がストアタイプに関係なくチャネルオーダーを変更する権限に関連しています。

| 権限設定                                           | 説明                                        |
| ---------------------------------------------- | ----------------------------------------- |
| Accounts > Account: Add Account Entry          | アカウントエントリを追加する機能                          |
| Accounts > Account Entry: Manage Users         | アカウントユーザーを管理する機能                          |
| Accounts > Account Entry: Update               | アカウントエントリを編集する機能                          |
| Accounts > Account Entry: View                 | アカウントを表示する機能                              |
| Accounts > Account Entry: View Users           | アカウントユーザーを表示する機能                          |
| Accounts > Commerce Account: Manage Addresses  | アカウントに新しい請求先住所、配送先住所、または組み合わせた住所を追加する機能   |
| Accounts > Commerce Account: Manage Members    | ユーザーや組織をアカウントに招待する機能、およびその詳細を編集する機能       |
| Accounts > Commerce Account: Update            | アカウントを編集する機能                              |
| Accounts > Commerce Account: View              | アカウントを表示する機能                              |
| Accounts > Commerce Account: View Addresses    | アカウントに関連するすべてのアドレスを表示する機能                 |
| Accounts > Commerce Account: View Members      | アカウント内の全メンバーを表示する機能                       |
| カートを開く > Commerce Order: Add Order             | バイヤーのアカウントに注文を追加する機能                      |
| カートを開く > Commerce Order: Approve Open Orders   | ワークフローが有効な場合、注文を承認する機能                    |
| カートを開く > Commerce Order: Check Out Open Orders | 注文のチェックアウトプロセスを完了する機能                     |
| カートを開く > Commerce Order: Delete Orders         | 注文を削除する機能                                 |
| カートを開く > Commerce Order: Manage Orders         | 郵送先住所や請求先住所などの注文内容を変更する機能、および注文にメモを追加する機能 |
| カートを開く > Commerce Order: View Open Orders      | すべての未処理注文を表示する機能                          |
| カートを開く > Commerce Order: View Orders           | ステータスに関係なく注文を表示する機能                       |

### アカウント メンバー

> アカウントロール（Liferay 7.4に含まれる）

このロールは、アカウント内のすべてのユーザーに自動的に割り当てられ、基本的な表示権限が与えられます。

| 権限                             | 説明           |
| ------------------------------ | ------------ |
| Accounts > Account Entry: View | アカウントを表示する機能 |

### バイヤー

> サイトロール

このロールが割り当てられたユーザーは、注文を表示、作成、およびチェックアウトできます。

| パーミッション                                       | 説明                    |
| --------------------------------------------- | --------------------- |
| カートを開く > Commerce Order: Add Order            | バイヤーのアカウントに注文を追加する機能  |
| カートを開く > Commerce Order: Checkout Open Orders | 注文のチェックアウトプロセスを完了する機能 |
| カートを開く > Commerce Order: View Open Orders     | 未処理注文を表示する機能          |
| カートを開く > Commerce Order: View Orders          | ステータスに関係なく注文を表示する機能   |

### オーダーマネージャー

> サイトロール

このオーダーマネージャーロールは、ユーザーにバイヤーのすべての権限を付与し、さらにユーザーに特定のチャネルサイトの注文を管理、削除、承認する権限を与えます。

| 権限の設定                                          | 説明                                        |
| ---------------------------------------------- | ----------------------------------------- |
| カートを開く > Commerce Order: Add Order             | バイヤーのアカウントに注文を追加する機能                      |
| カートを開く > Commerce Order: Approve Open Orders   | ワークフローが有効な場合、注文を承認する機能                    |
| カートを開く > Commerce Order: Check Out Open Orders | 注文のチェックアウトプロセスを完了する機能                     |
| カートを開く > Commerce Order: Delete Orders         | 注文を削除する機能                                 |
| カートを開く > Commerce Order: Manage Orders         | 郵送先住所や請求先住所などの注文内容を変更する機能、および注文にメモを追加する機能 |
| カートを開く > Commerce Order: View Open Orders      | すべての未処理注文を表示する機能                          |
| カートを開く > Commerce Order: View Orders           | ステータスに関係なく注文を表示する機能                       |

## カスタムロール

以下のリストには、自身のCommerceインスタンス用に作成した方がいい一般的なカスタムロールが含まれています。 これらの役割は、他のDXPの役割と同じように作成できます。 詳細は、 [ロールの作成と管理](https://learn.liferay.com/dxp/latest/ja/users-and-permissions/roles-and-permissions/creating-and-managing-roles.html) と [ロール権限の定義](https://learn.liferay.com/dxp/latest/ja/users-and-permissions/roles-and-permissions/defining-role-permissions.html) を参照してください。

### カタログマネージャー

> 標準ロール

カタログマネージャーロールは、特定のカタログ内の商品にアクセス、作成、編集、および管理するための権限を付与します。 このロールは、異なるベンダーや会社のチームが別々のカタログで異なる商品を管理する時など、複数のカタログがある場合に役立ちます。 カタログマネージャーは在庫を管理できません。 この責任については、 [在庫マネージャー](#inventory-manager) を参照してください。

| パーミッション                                             | 説明                               |
| --------------------------------------------------- | -------------------------------- |
| Catalogs: Access in Control Panel                   | グローバルメニューからカタログアプリケーションにアクセスする機能 |
| Catalogs: View                                      | カタログアプリケーションを表示する機能              |
| Currencies > Commerce Currencies: Manage Currencies | 通貨を管理する機能                        |
| Portal: View Control Panel Menu                     | グローバルメニューにアクセスする機能               |
| Products: Access in Control Panel                   | グローバルメニューから商品アプリケーションにアクセスする機能   |
| Products: View                                      | 商品アプリケーションを表示する機能                |

上記の権限に加えて、ロールに管理させたい個々のカタログに対して、 *表示* および *アップデート* の権限を割り当てる必要があります。

これを行うには、［*グローバルメニュー*］(![Global Menu](../../images/icon-applications-menu.png))を開き、［*Commerce*］タブをクリックし、*［商品管理］* &rarr; *［カタログ］*に移動します。 次に、目的のカタログの *［Actions］* ボタン（![Actions Button](../../images/icon-actions.png)）をクリックし、 *［権限設定］*を選択します。 チェックボックスを使用して、ロール *表示* および *アップデート* 許可を割り当てます。

```{note}
カタログマネージャーに、カタログ自体を変更することなく、カタログ内の商品を管理させたい場合は、`Catalogs.Access in Control Panel`と`Catalogs: Access in Control Panel`を削除してください。
```

### 割引マネージャー

> 標準ロール

割引マネージャーロールは、Liferay Commerceでの割引の作成、更新、および削除の権限を付与します。 Commerceの割引は、注文の異なる部分を対象としたり、異なる対象ルールを使用したりすることができるため、さまざまな割引マネージャーの設定を行うことができます。 以下の設定は、すべての割引オプションへのアクセスを許可します。

| 権限の設定                                                            | 説明                              |
| ---------------------------------------------------------------- | ------------------------------- |
| Account Groups: View                                             | アカウントグループアプリケーションを表示する機能        |
| Account Groups > Commerce Accounts: Manage All Accounts          | アカウントグループアプリケーションにアクセスするために必要   |
| Account Groups > Commerce Accounts: View Commerce Account Groups | 既存のアカウントグループを表示する機能             |
| Catalogs: View                                                   | カタログアプリケーションを表示する機能             |
| Catalogs > Commerce Catalogs: View Commerce Catalogs             | カタログアプリケーションで関連リソースを表示する機能      |
| Channels: View                                                   | チャネルアプリケーションを表示する機能             |
| Channels > Commerce Channels: View Commerce Channels             | チャネルアプリケーションで関連リソースを表示する機能      |
| Discounts: Access in Control Panel                               | グローバルメニューから割引アプリケーションにアクセスする機能  |
| Discounts: Permissions                                           | 割引アプリケーションでリソース権限を表示および変更する機能   |
| Discounts: View                                                  | 割引アプリケーションを表示する機能               |
| Discounts > Commerce Discount: Delete                            | 割引エンティティを削除する機能                 |
| Discounts > Commerce Discount: Permissions                       | 割引エンティティの権限を表示および変更する機能         |
| Discounts > Commerce Discount: Update                            | 割引エンティティを変更する機能                 |
| Discounts > Commerce Discount: View                              | 割引エンティティを表示する機能                 |
| Discounts > Commerce Discounts: Add Discount                     | 割引アプリケーションで割引エンティティを作成する機能      |
| Discounts > Commerce Discounts: View Discounts                   | 割引アプリケーションで割引エンティティを表示する機能      |
| Currencies > Commerce Currencies: Manage Currencies              | 通貨エンティティにアクセスして変更する機能           |
| Portal: View Control Panel Menu                                  | グローバルメニューにアクセスする機能              |
| Price Lists: Access in Control Panel                             | グローバルメニューから価格表アプリケーションにアクセスする機能 |
| Product Groups: View                                             | 商品グループエンティティを表示する機能             |

### 在庫マネージャー

> 標準ロール

在庫マネージャーロールでは、ユーザーはすべての倉庫の在庫を表示し、管理することができます。 このロールを持つユーザーは、入荷の追加、在庫レベルの更新、倉庫間の移動、およびチェンジログを表示することができます。

| 権限の設定                                               | 説明                               |
| --------------------------------------------------- | -------------------------------- |
| Inventory: Access in Control Panel                  | コントロールパネルから在庫アプリケーションにアクセスする機能   |
| Inventory: View                                     | 在庫アプリケーションを表示する機能                |
| Warehouses > Commerce Inventories: Manage Inventory | 倉庫にリンクされている在庫エンティティにアクセスして変更する機能 |
| Portal: View Control Panel Menu                     | グローバルメニューにアクセスする機能               |

### インスタンス オーダーマネージャー

> 標準ロール

オーダーマネージャーロールは、複数のチャネルにまたがるオーダーを管理するために必要な権限をユーザーに付与するもので、1つのサイトに限定されるものではありません。

| 権限の設定                                                   | 説明                                        |
| ------------------------------------------------------- | ----------------------------------------- |
| カートを開く > Commerce Order: Add Order                      | バイヤーのアカウントに注文を追加する機能                      |
| カートを開く > Commerce Order: Approve Open Orders            | ワークフローが有効な場合、注文を承認する機能                    |
| カートを開く > Commerce Order: Check Out Open Orders          | 注文のチェックアウトプロセスを完了する機能                     |
| カートを開く > Commerce Order: Delete Orders                  | 注文を削除する機能                                 |
| カートを開く > Commerce Order: Manage Order Notes             | 一般的な注文メモを変更する機能                           |
| カートを開く > Commerce Order: Manage Order Restricted Notes  | 制限付き注文メモを変更する機能                           |
| カートを開く > Commerce Order: Manage Orders                  | 郵送先住所や請求先住所などの注文内容を変更する機能、および注文にメモを追加する機能 |
| カートを開く > Commerce Order: View Open Orders               | すべての未処理注文を表示する機能                          |
| カートを開く > Commerce Order: View Orders                    | ステータスに関係なく注文を表示する機能                       |
| Orders: Access in Control Panel                         | グローバルメニューから注文アプリケーションにアクセスする機能            |
| Orders: Permissions                                     | 注文アプリケーションで権限を表示および変更する機能                 |
| Orders: View                                            | 注文アプリケーションを表示する機能                         |
| Account Groups > Commerce Accounts: Manage All Accounts | アカウントグループアプリケーションにアクセスするために必要             |
| Portal: View Control Panel Menu                         | グローバルメニューにアクセスする機能                        |

### 出荷マネージャー

> 標準ロール

出荷マネージャーロールは、ユーザーが出荷を処理できるようにします。 これには、出荷の詳細の追加、出荷日の追加、出荷への商品の追加、出荷状況の更新などの機能が含まれます。 Commerce 3.0+およびLiferay 7.3+では、ユーザーは注文アプリケーションで出荷を作成します。 出荷マネージャーのみが出荷の記入および処理を行うようにしたい場合は、以下の権限で十分です。 そうでない場合は、 [オーダーマネージャー](#order-manager-regular-role) の権限を追加することができます。

| 権限の設定                                                   | 説明                             |
| ------------------------------------------------------- | ------------------------------ |
| カートを開く > Commerce Order: View Orders                    | ステータスに関係なく注文を表示する機能            |
| Account Groups > Commerce Accounts: Manage All Accounts | アカウントグループアプリケーションにアクセスするために必要  |
| Warehouses > Commerce Inventories: Manage Inventory     | 倉庫にリンクされた在庫エンティティにアクセスして変更する機能 |
| 出荷管理                                                    | 出荷エンティティにアクセスして変更する機能          |
| Portal: View Control Panel Menu                         | グローバルメニューにアクセスする機能             |
| Shipments: Access in Control Panel                      | グローバルメニューから出荷アプリケーションにアクセスする機能 |
| Shipments: View                                         | 出荷アプリケーションを表示する機能              |
| Shipments > Commerce Warehouse: View                    | 倉庫エンティティを表示する機能                |

## Liferay 7.3以前

### Sales Agent

> 標準ロール

Sales AgentはLiferay Commerceの通常のロールであり、割り当てユーザーは管理権限を付与することなく、ロールに割り当てられたアカウントを管理することができます。 これは標準ロールであるため、 [*標準ロール* ]タブにあります。

![ [権限の定義]タブでデフォルトのSales Agentの権限を表示します。](./commerce-roles-reference/images/06.png)

| 権限の設定         | 説明                             |
| ------------- | ------------------------------ |
| 組織を管理         | 組織を追加または削除する機能                 |
| 利用可能なアカウントを管理 | Sales Agentが加入しているアカウントを管理する機能 |

## 追加情報

* [ロールと権限について](https://learn.liferay.com/dxp/latest/ja/users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.html)
* [注文管理権限のリファレンス](./order-management-permissions.md)
* [在庫管理権限のリファレンス](./inventory-management-permissions.md)
* [価格設定権限のリファレンス](./pricing-permissions.md)
* [商品管理権限のリファレンス](./product-management-permissions.md)
* [ストア管理権限のリファレンス](./store-management-permissions.md)
* [設定権限のリファレンス](./settings-permissions.md)
