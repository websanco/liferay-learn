# DocuSignデジタル署名の有効化

> 利用可能：Liferayポータル7.4 GA3以降

[DocuSign](https://www.docusign.com/)デジタル署名をLiferayドキュメントに統合できるようになりました。 DocuSignは、電子的に署名されるドキュメントを管理するサービスです。 この統合により、ドキュメントの署名を管理および収集できます。

Liferayでデジタル署名を有効にする前に、ユーザーIDキー、APIアカウントキー、アカウントベースURI、統合キー、およびRSA秘密キーを生成して取得していることを確認してください。 この手順は、[DocuSignのWebサイト](https://support.docusign.com/en/guides/ndse-admin-guide-api-and-keys)に記載されています。

## デジタル署名の有効化

1. 右上隅にあるグローバルメニューを開きます。 ![グローバルメニュー](../../../images/icon-applications-menu.png)

1. *［コントロールパネル］* &rarr; *［Instance Settings］* &rarr; *［Digital Signature］*の順にクリックします。

    ![デジタル署名のリンクは、［Instance Settings］または［サイト設定］のいずれかに表示されます。](./enabling-docusign-digital-signatures/images/01.png)

1. トグルを *［Enabled］*に切り替えます。

    ![トグルを切り替えます。](./enabling-docusign-digital-signatures/images/02.png)

1. *［Site Settings Strategy］*を選択します（以下を参照）。

    ![サイト設定戦略は、デジタル署名のスコープを定義します。](./enabling-docusign-digital-signatures/images/03.png)

1. *［保存］*をクリックします。

サイト設定戦略には3つのオプションがあります。

**Always Inherit：**すべてのサイトがこれらの設定にリンクされます。

**Always Override：** サイトごとに独自の構成を行う必要があります。

**Inherit or Override：**インスタンス設定とサイト設定の両方で定義できます。 両方で定義されている場合、サイト設定はインスタンス設定よりも優先されます。

ここで、Liferayの適切なスコープでDocuSignからデジタルキーを追加する必要があります。

## デジタルキーの追加

サイト設定戦略で選択した内容に応じて、[適切なスコープ](../../../system-administration/configuring-liferay/understanding-configuration-scope.md)でデジタルキーを追加します。

- ［Always Inherit］を選択した場合は、［Instance Settings］でキーを追加します。
- ［Always Override］を選択した場合は、［サイト設定］でキーを追加します。
- ［Inherit or Override］を選択した場合は、いずれかの場所にキーを追加します。

1. _［コントロールパネル］_ &rarr; _［Instance Settings］_ &rarr; _［Digital Signature］_に移動するか、サイト設定の場合は、_［サイトメニュー］_ &rarr; _［設定］_ &rarr; _［サイト設定］_ &rarr; _［Digital Signature］_に移動します。

1. まだ切り替わっていない場合は、トグルを_［Enabled］_に切り替えます。

1. 以前にDocuSignのWebサイトから取得したユーザーID、APIアカウントID、アカウントのベースURI、統合キー、およびRSA秘密キーを入力します。

1. *［保存］*をクリックして、デジタル署名を有効にします。

## ドキュメントとメディアでのドキュメント署名の収集

1. デジタル署名を収集するドキュメントを見つけて、*［アクション］* &rarr; *［Collect Digital Signature］*をクリックします。

    ![アクションボタンと［Collect Digital Signatures］ボタンが表示されます。](./enabling-docusign-digital-signatures/images/04.png)

1. 複数のドキュメントの場合は、署名を収集するドキュメントを選択し、*［Collect Digital Signature］*（![Collect Digital Signature](../../../images/icon-digital-signature.png)）をクリックします。

   ![選択した複数のファイルと［Collect Digital Signature］ボタンが表示されます。](./enabling-docusign-digital-signatures/images/05.png)

1. *エンベロープ*の情報を入力し、*［Send］*をクリックします。

    ![エンベロープの情報を入力します。](./enabling-docusign-digital-signatures/images/06.png)

1. エンベロープが送信されると、受信者は[DocuSignのプロセス](https://www.docusign.com/products/electronic-signature/how-docusign-works)を経てドキュメントに署名する必要があります。

## エンベロープのステータスの追跡

DocuSignは、_エンベロープ_という用語を使用して、署名するドキュメントまたはドキュメントのコレクションを示します。 送信すると、Liferay内からエンベロープのステータスを追跡できます。

```{tip}
[DocuSignのWebサイト](https://support.docusign.com/en/guides/ndse-user-guide-document-status)でさまざまなステータスラベルを確認できます。 
```

1. *サイトメニュー*（![Site Menu](../../../images/icon-menu.png)）&rarr; _［Content & Data］_ &rarr; _［Digital Signature］_を開き、作成されたエンベロープの一覧を表示します。

1. ![Add Button](../../../images/icon-add.png)を使用して、この画面から直接エンベロープを作成することもできます。 エンベロープの情報を入力するための画面が表示されます。

1. *［フィルターと並び替え］*を使用するか、検索バーにキーワードを入力して、使用可能なエンベロープの一覧をフィルタリングしてソートすることができます。

    ![フィルターと並び替え、または検索バーを使用してドキュメントを整理します。](./enabling-docusign-digital-signatures/images/07.png)

## ドキュメントのダウンロード

1. エンベロープの名前をクリックすると、詳細が表示されます。 *［Download］*ボタンをクリックするか、［Digital Signature］画面の*アクションボタン*をクリックして、 ![アクションボタン](../../../images/icon-actions.png) ドキュメントをダウンロードできます。

   ```{important}
   Liferayは署名されたドキュメントを保存しないため、［Download ］をクリックすると、LiferayからではなくDocuSignからドキュメントが取得されます。 ドキュメントの署名が部分的か完全かどうかに関係なく、ダウンロードボタンは最新の状態を取得します。
   ```

   ![Liferayの内部からエンベロープの詳細を表示できます。](./enabling-docusign-digital-signatures/images/08.png)

```{warning}
デジタル署名設定を無効にすると、コントロールパネルのデジタル署名モジュールを含むDocuSignへのすべての参照が削除されます。 Liferayでこの機能を無効にしても、すべてのエンベロープはDocuSignに引き続き表示されます。 デジタル署名を再度有効にすると、一覧が再び表示されます。
```

## 追加情報

- [構成スコープについて](../../../system-administration/configuring-liferay/understanding-configuration-scope.md)
