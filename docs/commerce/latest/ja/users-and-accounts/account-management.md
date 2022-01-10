# アカウント管理

```{toctree}
:maxdepth: 3

account-management/creating-a-new-account.md
account-management/inviting-users-to-an-account.md
account-management/adding-addresses-to-an-account.md
account-management/account-roles.md
account-management/creating-a-new-account-group.md
account-management/using-organizations-to-create-a-sales-network.md
account-management/adding-organizations-to-accounts.md
```

Liferay Commerceでは、アカウントは、請求先住所や配送先住所、注文の詳細、VAT番号などの顧客情報を保存するために使用されます。 一度作成したアカウントは、共通の基準（例：地理的地域）に基づいてグループに分類することができます。 これらのアカウントグループは、特定の価格表、プロモーション、および割引を対象とする顧客をターゲットするために使用できます。

## アカウント タイプ

コマースでは、ビジネスとパーソナルタイプの2種類のアカウントタイプを提供しています。 それぞれ異なる [サイトの種類](../starting-a-store/sites-and-site-types.md) （B2B、B2Cなど）に使用されます。

* **ビジネスアカウント**: ビジネスアカウントには、複数のユーザーを関連付けることができます。 これらのユーザーには、 [アカウントロール](./account-management/account-roles.md) を割り当てて、アカウントに代わって責任を委譲することができます。 B2Bサイトでは、顧客がビジネスアカウントに関連付けられていると購入できるようになります。

* **パーソナルアカウント**：個人アカウントは1人のユーザーにのみ関連付けることができます。 B2Cサイトでは、認証された顧客には自動的にパーソナルアカウントが割り当てられ、個別に購入することができます。

  ```{note}
  Liferayユーザーアカウントのプロフィール情報は、作成時にパーソナルアカウントにインポートされます。 Liferayユーザーアカウントプロフィールの追加更新は、パーソナルアカウントには自動的にプロパゲートされません。
  ```
<!-- TASK: Add the Guest Account type; also note that B2X Sites recognize both Business and Personal Accounts-->
## 一般的なアカウント管理アクション

一般的なアカウント管理アクションの詳細は、以下の記事を参照してください：

* [新規アカウントの作成](./account-management/creating-a-new-account.md)
* [アカウントへのユーザーの招待](./account-management/inviting-users-to-an-account.md)
* [アカウントへのアドレスの追加](./account-management/adding-addresses-to-an-account.md)
* [新規アカウントグループの作成](./account-management/creating-a-new-account-group.md)

## 追加情報

* [サイトとサイトのタイプ](../starting-a-store/sites-and-site-types.md)
* [アカウントロール](./account-management/account-roles.md)
