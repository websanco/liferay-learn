# Commerce 通知キューの設定

イベントによって通知がトリガーされると（注文など）、通知はチャネルの[ *通知キュー* ]タブに記録されます。 デフォルトでは、Liferay Commerceインスタンスは、通知が15分ごとに送信されたかどうかを確認し、43200分（30日）後に未送信の通知を削除します。

*チェック* と *削除* 間隔を変更するには：

1.  *[Control Panel]* → *[Configuration]* → *[System Settings]*に移動します。

2.  *[Orders]*をクリックしてから、*[Commerce Notification Queue]*をクリックします。 デフォルト値は分単位でリストされています。 必要に応じて、[Check Interval]と[Delete Interval]の値を変更します。

    ![間隔を変更する](./configuring-the-commerce-notification-queue/images/01.png)

3.  完了したら、*[Save]*をクリックします。

## 追加情報

  - [通知テンプレートの使用](./using-notification-templates.md)
