# フォーム通知の設定

フォームエントリーが送信されるたびに通知メールを送信するようにフォームアプリを設定できます。 [フォームにワークフローを有効](./advanced-forms-usage/using-forms-with-a-workflow.md)にしている場合は、通知を生成するようにフォームアプリを設定する必要がない場合があります。

フォームの電子メール通知を設定するには：

1.  *製品メニュー*（![Product Menu](../../../images/icon-product-menu.png)）を開き、*サイト管理*メニューのコンパスアイコン（![Compass](../../../images/icon-compass.png)）をクリックします。 フォームが関連付けられているサイトを選択します。

2.  *[Content & Data]* → *[Forms]* の順にクリックします。

3.  フォームを選択します（例：*Guest Survey Form* ）。

4.  *オプション*ボタン（![Options](../../../images/icon-options.png)）をクリックして*[Settings]* を選択し、フォームの*[Form Settings]* セクションを開きます。

    ![フォームの設定](./configuring-form-notifications/images/01.png)

5.  *[Email Notifications]* タブをクリックします。

6.  *[Send an Email Notification for Each Entry]* オプションを有効にします。

    ![フォームエントリーが送信されるたびに電子メール通知を送信するよう設定します。](./configuring-form-notifications/images/02.png)

7.  次のように入力します：

    **From Name：**送信者の名前。 これは、サイト名、フォーム名、または受信者に有益なその他の情報にできます。

    **From Address：**送信者のメールアドレス。 `noreply@example.com`を使用して、受信者が返信しないようにすることができます。

    **To Address：**受信者の電子メールアドレス（`test@example.com`など）。

    **Subject：**メールの件名。

8.  *[Done]* をクリックして、ダイアログウィンドウを閉じます。

## 追加情報

  - [Using Forms with a Workflow](./advanced-forms-usage/using-forms-with-a-workflow.md)
  - [Connecting to a Mail Server](../../../installation-and-upgrades/setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md)
