# サイトの外観を変更する

サイトのロゴ、ファビコン、テーマの変更は、変更したい最初のルックアンドフィールの一部です。 テーマは、サイトの全体的なルックアンドフィールを設定するために使用されます。 サイトのページは、DXPインスタンスに展開されているテーマを使用するように構成できます。

DXP Dockerイメージには*クラシック*デフォルトで利用可能なテーマ。 この例では、新しいシンプルなテーマをデプロイしてから、テーマを切り替える方法を示します。

## サイトのテーマを変更する

### 新しいテーマをデプロイする

1.  Liferay DXP Dockerイメージを起動します。

    ``` bash
    docker run -it -p 8080:8080 liferay/portal:7.3.1-ga2
    ```

2.  [ Acmeサンプルブルーテーマ](./liferay-5b2v-theme.war)を含むWARをダウンロードする ：

    ``` bash
    curl https://learn.liferay.com/dxp/7.x/en/getting-started/changing-your-sites-appearance/liferay-5b2v-theme.war -O
    ```

3.  テーマを含むWARをデプロイします。
    
     <!-- ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq) -->

    ``` bash
    docker cp liferay-5b2v-theme.war docker-container:path-to-deploy-folder
    ```

これにより、DXPインスタンスにサンプルテーマが読み込まれます。 コンソールで次のメッセージを確認して、テーマが正常にデプロイされたことを確認できます。

    2020-03-11 17:06:35.601 INFO  [fileinstall-/opt/liferay/osgi/war][BundleStartStopLogger:39] STARTED liferay-5b2v-theme_1.0.0 [1112]

### サイトのテーマを変更する

デプロイされたテーマを使用するようにサイトのページを構成します。

1.  ブラウザで` https：// localhost：8080にアクセスし、` [管理者としてログイン](./introduction-to-the-admin-account.md) します。

2.  *[Site Administration]* → *[Site Builder]* → *[Pages]* に移動します。

3.  *公開ページ*の横にある歯車アイコンをクリックしますそれらを設定するには：

    ![ページ画面を開いて、公開ページを構成します。](./changing-your-sites-appearance/images/01.png)

4.  下にスクロールして、*現在のテーマを変更をクリックします*ボタン：

    ![[現在のテーマを変更]をクリックして、パブリックページの新しいテーマを選択します。](./changing-your-sites-appearance/images/02.png)

5.  デプロイされたサンプルテーマ* Acme Sample Blue Themeを選択します。 *

6.  サイトのホームページに戻り、テーマが変更されたことを確認します。 サイトの背景が青色になりました。

    ![テーマを変更すると、ホームページの背景色が変わります。](./changing-your-sites-appearance/images/03.png)

サイトのテーマが更新されました。

### テーマリソース

[ Liferay Marketplaceで利用可能な多くのテーマがあります](../advanced-installation-and-upgrades/01-installing-liferay-dxp/10-setting-up-marketplace.md)プロフェッショナルなルックアンドフィールをすばやく実現するために使用できます。

[独自のテーマを作成する方法を学ぶこともできます](../site-building/README.md) 。

## サイトのロゴを変更する

近日公開！

## サイトのファビコンを変更する

近日公開！
