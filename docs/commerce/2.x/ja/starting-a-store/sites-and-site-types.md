# サイトとサイトのタイプ

## サイト

Liferay Commerceのデプロイは、サイト、ページ、ウィジェット、およびアカウントの階層で構成されます。 Liferay Commerceでサイトを作成および設定することは、ストアを立ち上げるための最初のステップの1つです。 [アクセラレータ](../starting-a-store/accelerators.md)を使用してすぐにサイトを開始するか、または空白のサイトを作成して必要なCommerceウィジェットとページを追加できます。

Liferay Commerceは、Liferay Digital Experience Platform上に構築されています。 Liferay DXPサイトの機能の詳細については、[Building a Site](https://help.liferay.com/hc/en-us/articles/360018171231-Building-a-Site)を参照してください。

ストアサイトの作成の詳細については、[Store Setup Overview](../starting-a-store/store-setup-overview.md)を参照してください。

## サイトのタイプ

Liferay Commerceは、Liferayのサイトをサイトのタイプと組み合わせて使用します。 サイトのタイプによって、ストアのビジネスモデルが指定され、ストアフロントが[アカウント](../account-management/introduction-to-accounts.md)とどのように連携するかが決まります。

以下のサイトのタイプを使用できます：

  - **B2B**：ビジネスアカウントを必要とする企業間取引サイト。 購入するには、個々のユーザーアカウントをビジネスアカウントに関連付ける*必要があります*。 *Minium*アクセラレータを使用すると、B2Bサイトタイプを使用したサイトがすぐに開始されます。

  - **B2C**：個人アカウントを必要とする企業-消費者間取引サイト。 認証されたユーザーは誰でも購入できます。 *Speedwell*アクセラレータ（近日公開予定）を使用すると、B2Cサイトタイプを使用したサイトがすぐに開始されます。

  - **B2X**：B2C-B2Bサイトは、個人アカウントおよびビジネスアカウントを認識します。 ユーザーはビジネスアカウントに関連付けることができますが、個別に購入することもできます。

## サイトのタイプの設定

サイトのタイプを作成したらすぐに設定し、今後変更しないようにすることをお勧めします。

サイトのタイプを設定するには、 *サイト管理* → *コマース* → *設定* に移動し、「サイトタイプ」タブを選択します。 ドロップダウンメニューからタイプを選択し、*[Save]*をクリックします 。

![サイトのタイプ](./sites-and-site-types/images/01.png)

サイトのタイプを変更すると、[Accounts]ウィジェットに表示されるアカウントも変更されます。 インスタンスにビジネスアカウントが含まれているが、サイトのタイプがB2Cに設定されている場合、それらのアカウントはデータベースに存在しますが、[Accounts]ウィジェットには表示されず、ユーザーはアクセスできません。

## 追加情報

  - [Store Setup Overview](../starting-a-store/store-setup-overview.md)
  - [Building a Site](https://help.liferay.com/hc/en-us/articles/360018171231-Building-a-Site)
  - [Introduction to Accounts](../account-management/introduction-to-accounts.md)
