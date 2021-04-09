# 通知テンプレート変数リファレンスガイド

電子メール通知テンプレートを作成するときに、電子メールコンテンツの *電子メール設定* および *ボディ* フィールドにキー値の代わりとして変数を挿入できます。 キーの値には、顧客の名前、注文ID、配送先と請求先の住所、注文のアイテムのリストが含まれます。

![これらの変数を「Eメール本文」フィールドで使用します。](./notification-template-variables-reference-guide/images/02.png)

使用可能な変数を表示するには、最初に通知テンプレートタイプを選択して有効にします。

![最初に通知テンプレートのタイプを選択します。](./notification-template-variables-reference-guide/images/01.png)

テンプレートタイプを選択したら、[ *用語の定義* ]ドロップダウンメニューを展開します。

## メール設定

![これらの変数を「Eメール設定」フィールドで使用します。](./notification-template-variables-reference-guide/images/03.png)

次の変数を使用して、送信者と受信者の電子メール設定フィールドに入力できます。

| 変数                                          | 説明              |
| ------------------------------------------- | --------------- |
| \[%ACCOUNT\_ROLE\_ORDER\_MANAGER%\] | アカウントオーダーマネージャー |
| \ [%ORDER \ _CREATOR％\]                  | 注文を作成したユーザー     |
| \[%ACCOUNT\_ROLE\_ADMINISTRATOR%\]    | アカウント管理者        |
| \[%USER\_GROUP\_NAME%\]               | ユーザーグループ名       |

## [Orders]

![これらの変数Orders emailsを使用します。](./notification-template-variables-reference-guide/images/05.png)

次の変数は、注文タイプの電子メール通知テンプレートで使用できます。

| 変数                                        | 説明                |
| ----------------------------------------- | ----------------- |
| \[%ORDER\_ITEMS%\]                    | 注文に含まれるすべてのアイテムの表 |
| \ [%ORDER \ _SHIPPING \ _ADDRESS％\] | 注文の配送先住所          |
| \ [%ORDER \ _BILLING \ _ADDRESS％\]  | 注文の請求先住所          |
| \ [%ORDER \ _ID％\]                     | 注文ID              |

## サブスクリプション

![この変数をサブスクリプションに使用します。](./notification-template-variables-reference-guide/images/04.png)

次の変数は、サブスクリプションタイプの電子メール通知テンプレートで使用できます。

| 変数                        | 説明  |
| ------------------------- | --- |
| \ [%PRODUCT \ _NAME％\] | 商品名 |

## 追加情報

  - [通知テンプレートの使用](./using-notification-templates.md)
