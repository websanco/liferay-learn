# 開発要件

Liferay DXP Cloudでの展開を開始する前に、DXP Cloudのモジュールを作成することを計画している開発者は、必要なツールをインストールする必要があります。 開発者がカスタマイズを作成するために使用できるさまざまな開発ツールとフレームワークがありますが、DXP Cloudへのデプロイは、プロビジョニングプロセス中に作成されたGitHubプロジェクトから始まります。

DXP Cloudで開発するためのローカル環境をセットアップするには、以下が必要です。

  - [JDK 1.8またはJDK 11](http://www.oracle.com/technetwork/java/javase/downloads/index.html)：Liferay DXPをローカルで実行するために必要

    ```{important}
    JDK 9 と JDK 10 はサポートされていません。 詳しくは最新の [互換性マトリックス](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.2+Compatibility+Matrix/b6e0f064-db31-49b4-8317-a29d1d76abf7?) を参照してください。
    ```

  - [Gradle 4+](http://www.gradle.org/downloads)：Liferay DXPおよびDXP Cloudのさまざまなビルドコマンドを実行するために使用されます

  - [Git](https://git-scm.com/)：DXP Cloudにデプロイする変更を追加するために必要

  - [GitHubアカウント](https://github.com/)：コード変更をGitHubにプッシュしてプルリクエストを送信するために必要

    ```{note}
    Liferay DXP Cloudは、 [Liferay Workspace](https://help.liferay.com/hc/en-us/articles/360029147471-Liferay-Workspace)[と同様の前提条件を持っています。 Liferay Dev Studio](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191007&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191038), [Maven](https://help.liferay.com/hc/en-us/articles/360017885592-Maven-Workspace), [Liferay IntelliJ Plugin](https://plugins.jetbrains.com/plugin/10739-liferay-intellij-plugin) を使ってLiferayワークスペースを作成することに慣れている開発者は、必要なツールのほとんどをすでに設定しています。
    ```

## 追加情報

  - [Githubリポジトリの設定](../getting-started/configuring-your-github-repository.md)
  - [DXP Cloud導入ワークフローの概要](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md)
  - [Liferay DXPインスタンスへのログイン](../getting-started/logging-into-your-dxp-cloud-services.md)
