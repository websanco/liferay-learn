# ローカル現行環境ステージングの設定

ローカル現行環境ステージングでは、ステージング環境と本番環境の両方が同じLiferayサーバーでホストされます。 有効にすると、Liferay DXPは、ステージング環境として機能するサイトのローカルクローンを作成し、元のインスタンスが本番環境になります。

両方の環境は、同じJVM、データベース、選択されたアプリケーションデータ、および構成（`portal-ext.properties`で設定されたプロパティなど）を共有します。

  - [ローカル現行環境ステージングの設定](#setting-up-local-live-staging)
  - [ローカル現行環境ステージングの無効化](#disabling-local-live-staging)

## ローカル現行環境ステージングの設定

DXPインスタンスのローカル現行環境ステージングを設定するには、次の手順に従います。

1.  *[プロダクトメニュー]* → *[公開設定]* → *[ステージング]* に移動します。

    ![プロダクトメニューの[ステージング]に移動します。](./configuring-local-live-staging/images/01.jpg)

2.  *[ローカル現行環境]* を選択すると、*[ページバージョニング]* と*[ステージコンテンツ]* の追加フィールドが表示されます。

    ![[ローカル現行環境]を選択します。](./configuring-local-live-staging/images/02.png)

3.  *[ページバージョニング]* をパブリックページセットまたはプライベートページセット、あるいはその両方で有効にするかどうかを選択します。

    ![ページバージョニングをプライベートページセットとパブリックページセットで有効にします。](./configuring-local-live-staging/images/03.png)

4.  ステージングする*データ*と*コンテンツ*のタイプを選択します。

    ![ステージングするデータとコンテンツのタイプを選択します。](./configuring-local-live-staging/images/04.png)

    ```{warning}
    When applications are checked, their data is copied to Staging, and it may not be possible to edit them directly in Live. When unchecking an application, first make sure that any changes in Staging are published, since they may be lost. See [Managing Data and Content Types in Staging](./managing-data-and-content-types-in-staging.md) for more information.
    ```

5.  *[保存]* をクリックして、クローン作成プロセスを開始します。 このプロセスの期間は、サイトのサイズによって異なります。

    ```{tip}
    Stage your Site early on to reduce cloning time and record a more complete history of your Site's update history, since updates are only recorded once you enable Page Versioning.
    ```

プロセスが完了すると、ローカル現行環境ステージングを使用する準備が整います。 ステージング環境の公開機能のナビゲートについては、[Staging UI Reference](./staging-ui-reference.md)を参照してください。

## ローカル現行環境ステージングの無効化

何らかの理由でサイトのステージングを無効にする必要がある場合は、ステージング環境から無効にすることができます。 ただし、ローカル現行環境ステージングを無効にすると、すべての未公開コンテンツとともにステージング環境が削除されることに注意してください。 このため、ステージングを無効にする前に、必要なすべての情報が他の場所で公開または保存されていることを確認してください。 ステージング環境のサイズによっては、このプロセスに時間がかかる場合があります。 DXPインスタンスが処理中の場合は、ステージングを無効にしないことをお勧めします。

ローカル現行環境ステージングを無効にするには、次の手順に従います。

1.  ステージング環境でプロダクトメニューを開き、*[公開設定]* → *[ステージング]* に移動します。

2.  *[アプリケーション]* バーの*アクション*ボタン（![Actions button](../../../images/icon-actions.png)）をクリックし、*[ステージング設定]* を選択します。

3.  ステージング設定で*[None]* を選択し、*[保存]* をクリックします。

## 追加情報

  - [Staging Overview](../staging.md)
  - [Staging UI Reference](./staging-ui-reference.md)
  - [ステージングでのデータとコンテンツタイプの管理](./managing-data-and-content-types-in-staging.md)
  - [ステージング権限の管理](./managing-staging-permissions.md)
