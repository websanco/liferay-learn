# Building a Time Off Requester

Liferay Objectsは、コードを書いたりモジュールをデプロイすることなく、アプリケーション全体を構築することができます。 ここでは、Liferayの [オブジェクト](../../objects.md) と [選択リスト](../using-picklists.md)を使用して、Time Off Requestアプリケーションを作成します。

まず、部門の値を定義するための [選択リストを追加](#adding-a-department-picklist) します。 そして、 [Time Off Requests](#adding-a-time-off-request-object) 用と [Employees](#adding-an-employee-object) 用の2つのオブジェクトを作成します。 このプロセスでは、これらのオブジェクトを相互に関連付け、Time Off RequestオブジェクトにWebhookを追加します。 最後に、リレーションシップフィールドを表示するための [カスタムレイアウトを設計します](#designing-custom-object-layouts) 。

```{note}
システムオブジェクトの拡張には制限があるため、リクエストとユーザープロファイルを関連付けるには、Employeeオブジェクトを作成する必要があります。 
```

![Build a Time Off Request application.](./building-a-time-off-requester/images/01.png)

```{include} /_snippets/run-liferay-dxp.md
```

## 部門選択リストの追加

ピックリストは会社の部門を表しています。

1. ［**グローバルメニュー**］(![Global Menu](../../../images/icon-applications-menu.png))を開き、 ［**コントロールパネル**］ タブをクリックし、 ［**Picklists**］ に進みます。

1. ［**Add**］ ボタン（![Add Button](../../../images/icon-add.png)）をクリックし、名前に ［**Department**］ を入力し、 ［**保存**］ をクリックします。

1. 新しい ［**Picklist**］ を選択し、 ［**Add**］ ボタン(![Add Button](../../../images/icon-add.png))を使用して、アイテムを追加します（例：マーケティング、人事、営業）。

   ![Add items to the Department Picklist.](./building-a-time-off-requester/images/02.png)

これで、Time Off Requestオブジェクトのフィールドとして選択リストを使用できるようになりました。

## Time Off Requestオブジェクトの追加

1. ［**グローバルメニュー**］（![Global Menu](../../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ タブをクリックして、 ［**オブジェクト**］ に進みます。

1. ［**Add**］ ボタン（ ![Add Button](../../../images/icon-actions.png) ）をクリックし、以下の値を入力します。

   | フィールド   | 値                 |
   | ------- | ----------------- |
   | ラベル     | Time Off Request  |
   | 複数形のラベル | Time Off Requests |
   | オブジェクト名 | TimeOffRequest    |

1. ［**オブジェクト**］ を選択し、 ［**Fields**］ タブをクリックして、以下の4つの **フィールド** を追加します。

   | ラベル  | 項目名          | タイプ   | 選択リスト | 必須       |
   | ---- | ------------ | ----- | ----- | -------- |
   | 開始日  | `startDate`  | 日付    | n/a   | &#10004; |
   | 終了日  | `endDate`    | 日付    | n/a   |          |
   | 部門   | `department` | 選択リスト | 部門    | &#10004; |
   | コメント | `コメント`       | 文字列   | n/a   |          |

   ![Add the Start Date, End Date, Department, and Comments fields to the Object.](./building-a-time-off-requester/images/03.png)

1. ［**Actions**］ タブをクリックし、このアクションを追加します。

   | フィールド | 値                                                           |
   | ----- | ----------------------------------------------------------- |
   | 名前    | 作成の同期                                                       |
   | 日時    | 追加後                                                         |
   | 次を実行  | Webhook                                                     |
   | URL   | 自分のアプリやサイトからhttps://webhook.siteのようなテスト用のwebhook URLを入力します。 |

   このアクションは、Time Off Requestエントリーが追加されるたびに、外部システムにデータを送信します。 詳しくは、 [Defining Actions](../creating-and-managing-objects/defining-object-actions.md) をご覧ください。

   ![Create a webhook action that's triggered after requests are added.](./building-a-time-off-requester/images/04.png)

1. ［**Details**］ タブをクリックし、 次のエントリディスプレイとスコープ設定を設定します。

   | フィールド      | 値                    |
   | ---------- | -------------------- |
   | タイトルフィールド  | 開始日                  |
   | 範囲         | 会社                   |
   | パネルのカテゴリキー | アプリケーション > コミュニケーション |

1. ［**公開**］ をクリックします。 これにより、オブジェクトが起動します。

次に、Employeeオブジェクトを作成し、それをTime Off Requestオブジェクトに関連付けることで、リクエストエントリーを個々のユーザに関連付けることができます。

## Employeeオブジェクトの追加

1. ［**グローバルメニュー**］（![Global Menu](../../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ タブをクリックして、 ［**オブジェクト**］ に進みます。

1. ［**Add**］ ボタン（ ![Add Button](../../../images/icon-actions.png) ）をクリックし、以下の値を入力します。

   | フィールド   | 値         |
   | ------- | --------- |
   | ラベル     | Employee  |
   | 複数形のラベル | Employees |
   | オブジェクト名 | Employee  |

1. ［**オブジェクト**］ を選択し、 ［**Fields**］ タブをクリックして、これらの **フィールド** を追加します。

   | ラベル     | 項目名       | タイプ | 必須       |
   | ------- | --------- | --- | -------- |
   | 名前      | `名前`      | 文字列 | &#10004; |
   | メールアドレス | `メールアドレス` | 文字列 | &#10004; |

   ![Add the Name and Email fields to the Object.](./building-a-time-off-requester/images/05.png)

1. ［**Relationships**］ タブをクリックし、このリレーションシップを追加します。

   | フィールド   | 値              |
   | ------- | -------------- |
   | ラベル     | Requester      |
   | リレーション名 | requester      |
   | タイプ     | 1 対多           |
   | オブジェクト  | TimeOffRequest |

   ```{note}
   このリレーションシップを作成すると、Time Off Requestオブジェクトに外部キーフィールドが自動的に追加されます。
   ```

   ![Add a One to Many Relationship with the Time Off Request Object.](./building-a-time-off-requester/images/06.png)

1. ［**Details**］ タブをクリックし、 次のエントリディスプレイとスコープ設定を設定します。

   | フィールド      | 値                |
   | ---------- | ---------------- |
   | タイトルフィールド  | 名前               |
   | 範囲         | 会社               |
   | パネルのカテゴリキー | コントロールパネル > ユーザー |

1. ［**公開**］ をクリックします。

公開されると、Liferay UIやHeadless APIを使ってエントリーを作成することができます。 しかし、これらのエントリをリクエストエントリに関連付けるためには、まず [カスタムレイアウト](#designing-custom-object-layouts) を作成する必要があります。

## カスタムオブジェクトレイアウトの設計

オブジェクトのレイアウトは、オブジェクトエントリを作成や編集をする際にフィールドの構成を決定します。 リレーションシップはデフォルトのオブジェクトレイアウトには表示されないので、リレーションシップフィールドを表示するには、両方のオブジェクトにカスタムレイアウトを設計する必要があります。

### Time Off Requestsの場合

1. ［**オブジェクト**］ アプリケーションに移動し、 ［**Time Off Request**］ オブジェクトを選択します。

1. ［**レイアウト**］ タブをクリックし、 ［**Add**］ ボタン（ ![Add Button](../../../images/icon-actions.png) ）をクリックし、Nameに ［**Request**］ を入力し、 ［**Save**］ をクリックします。

1. 新しいレイアウトを選択し、 ［**Mark as Default**］ のボックスをチェックします。

   ![Add a new Layout and mark it as the Object's default layout.](./building-a-time-off-requester/images/07.png)

1. ［**Layout**］ タブをクリックし、 ［**tab**］ に2つの ［**block**］ を追加します。

   | 要素   | ラベル     | タイプ   |
   | ---- | ------- | ----- |
   | タブ   | リクエスト情報 | フィールド |
   | ブロック | 日付      | n/a   |
   | ブロック | 詳細      | n/a   |

1. ブロックにフィールドを追加します。

   | ブロック | 項目               |
   | ---- | ---------------- |
   | 日付   | 開始日、終了日          |
   | 詳細   | 部門、Employee、コメント |

   ![Add the Start Date, End Date, Department, Employee, and Comments fields to the layout blocks.](./building-a-time-off-requester/images/08.png)

1. ［**保存**］ をクリックします。

保存されると、新しいレイアウトは、LiferayのUIでTime Off Requestのエントリーを作成や編集をする際に使用されます。 employeeエントリーが存在する場合、それを個々のリクエストに関連付けることができます。

![The layout is used for creating and editing Time Off Request entries.](./building-a-time-off-requester/images/09.png)

### Employeesの場合

1. ［**オブジェクト**］ アプリケーションに移動し、 ［**Employee**］ オブジェクトを選択します。

1. ［**レイアウト**］ タブをクリックし、 ［**Add**］ ボタン（ ![Add Button](../../../images/icon-actions.png) ）をクリックし、Nameに ［**Employee Info**］ を入力し、 ［**Save**］ をクリックします。

1. 新しいレイアウトを選択し、 ［**Mark as Default**］ のボックスをチェックします。

1. ［**Layout**］ タブをクリックし、1つのブロックを持つ［Fields］タブを追加します。

   | 要素   | ラベル   | タイプ   |
   | ---- | ----- | ----- |
   | タブ   | 基本情報  | フィールド |
   | ブロック | 従業員情報 | n/a   |

1. ブロックに ［**Name**］ と ［**Email**］ フィールドを追加します。

1. Employeeオブジェクトの Requesterリレーションシップの［リレーションシップ］タブを追加します。

   | 要素 | ラベル               | 種類        | リレーションシップ |
   | -- | ----------------- | --------- | --------- |
   | タブ | Time Off Requests | リレーションシップ | Requester |

   ![Add a Relationships tab for the Object's Requester relationship.](./building-a-time-off-requester/images/10.png)

1. ［**保存**］ をクリックします。

保存されると、新しいレイアウトは、LiferayのUIでemployeeのエントリーを作成や編集をする際に使用されます。 リクエストエントリーが存在する場合は、 ［**Time Off Requests**］ タブでemployeeエントリーと関連付けることができます。 ここでは、選択したemployeeエントリーに関連するすべてのリクエストを表示することもできます。

![The layout is used to create and edit employee entries.](./building-a-time-off-requester/images/11.png)

## オブジェクトエントリーの追加

これでアプリが完成したので、Liferay UIとHeadless APIを使って、Time Off RequestとEmployeeオブジェクトにデータを追加することができます。

### Employeesの追加

以下の手順で、Headless APIを使ってemployeeエントリーを追加します。

1. Liferayの **API Explorer**（つまり、 `localhost:8080/o/api`）を開き、 **REST Services** ドロップダウンメニューをクリックし、 **c/employees** を選択します。

1. このスクリプトを、バッチ`POST` APIのリクエストボディに入力します。

   ```json
   [
     {
       "email": "foo@liferay.com",
       "name": "Able"
     },
     {
       "email": "bar@liferay.com",
       "name": "Baker"
     },
     {
       "email": "goo@liferay.com",
       "name": "Charlie"
     }
   ]
   ```

1. ［**Execute**］ をクリックします。

これにより、Employeeオブジェクトに 3 つのエントリが追加されます。

### Time Off Requestの追加

以下の手順で、Liferay UIを使ってリクエストエントリを追加します。

1. **グローバルメニュー**（![Global Menu](../../../images/icon-applications-menu.png)）を開き、 ［**アプリケーション**］ タブをクリックして、 ［**Time Off Requests**］ を選択します。

1. ［**Add**］ ボタン(![Add Button](../../../images/icon-actions.png))をクリックして、フォームに入力してください。

1. ［**保存**］ をクリックします。

エントリーを保存すると、そのエントリーの編集画面が表示されます。 さらにエントリーを追加するには、Time Off Requestsのメインページに戻ります。 時間のリクエストが追加されると、ObjectのWebhookがトリガーされ、設定されたURLにエントリーのデータが投稿されます。

リクエストが追加され、employeeエントリーに関連られると、Employeesアプリケーションでそれらを表示することができます。 ［**グローバルメニュー**］（![Global Menu](../../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ タブをクリックして、 ［**Employees**］ を選択します。 関連する **エントリ** を選択し、 ［**Time Off Requests**］ タブをクリックします。

![View an employee's related requests in the Time Off Requests tab.](./building-a-time-off-requester/images/12.png)

## 追加情報

* [Objects Overview](../../objects.md)
* [オブジェクトの作成](../creating-and-managing-objects/creating-objects.md)
* [選択リストの使用](../using-picklists.md)
