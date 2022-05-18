# サイトの外観を変更する

サイトのロゴ、ファビコン、テーマの変更は、変更したい最初のルックアンドフィールの一部です。 テーマは、サイトの全体的なルックアンドフィールを設定するために使用されます。 サイトのページは、DXPインスタンスに展開されているテーマを使用するように構成できます。

DXP Dockerイメージには **クラシック** デフォルトで利用可能なテーマ。 この例では、新しいシンプルなテーマをデプロイしてから、テーマを切り替える方法を示します。

<a name="changing-your-sites-theme" />

## サイトのテーマを変更する

<a name="deploy-a-new-theme" />

### 新しいテーマをデプロイする

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1.  [ Acmeサンプルブルーテーマ](./liferay-5b2v-theme.war) を含むWARをダウンロードする ：

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/getting-started/changing-your-sites-appearance/liferay-5b2v-theme.war -O
    ```

1. テーマを含むWARをデプロイします。

    <!-- ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq) -->

    ```bash
    docker cp liferay-5b2v-theme.war docker-container:path-to-deploy-folder
    ```

これにより、DXPインスタンスにサンプルテーマが読み込まれます。 コンソールで次のメッセージを確認して、テーマが正常にデプロイされたことを確認できます。

```
2020-03-11 17:06:35.601 INFO  [fileinstall-/opt/liferay/osgi/war][BundleStartStopLogger:39] STARTED liferay-5b2v-theme_1.0.0 [1112]
```

<a name="change-your-sites-theme" />

### サイトのテーマを変更する

デプロイされたテーマを使用するようにサイトのページを構成します。

1. ブラウザで` https:// localhost:8080にアクセスし、` [管理者としてログイン](./introduction-to-the-admin-account.md) します。

2. [**Site Administration**] → [**Site Builder**] → [**Pages**] に移動します。

3. **公開ページ** の横にある歯車アイコンをクリックしますそれらを設定するには：

    ![ページ画面を開いて、公開ページを構成します。](./changing-your-sites-appearance/images/01.png)

4.  下にスクロールして、 **現在のテーマを変更をクリックします** ボタン：

    ![ [現在のテーマを変更]をクリックして、パブリックページの新しいテーマを選択します。](./changing-your-sites-appearance/images/02.png)

5.  デプロイされたサンプルテーマ **Acme Sample Blue Themeを選択します。**

1. サイトのホームページに戻り、テーマが変更されたことを確認します。 サイトのコンテンツセクションの背景が青色になりました。

    ![テーマを変更すると、ホームページの背景色が変わります。](./changing-your-sites-appearance/images/03.png)

サイトのテーマが更新されました。

<a name="whats-next" />

### テーマリソース

サイトの外観を管理する方法の全容については、[Managing Your Site's Appearance](../site-building/site-appearance/site-appearance.md)を参照してください。

[ Liferay Marketplaceで利用可能な多くのテーマがあります](../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md)プロフェッショナルなルックアンドフィールをすばやく実現するために使用できます。

[独自のテーマを作成する方法を学ぶこともできます](../site-building/site-appearance/themes/introduction-to-themes.md) 。

<!-- ## Changing Your Site's Logo

Coming soon!

## Changing Your Site's Favicon

Coming soon! -->
