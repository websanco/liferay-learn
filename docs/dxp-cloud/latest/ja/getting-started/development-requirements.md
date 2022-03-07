# 開発要件

Liferay DXP Cloudでのデプロイを開始する前に、DXP Cloudのモジュールを作成することを計画している開発者は、必要なツールをインストールする必要があります。 開発者がカスタマイズを作成するために使用できるさまざまな開発ツールとフレームワークがありますが、DXP Cloudへのデプロイは、プロビジョニングプロセス中に作成されたGitHubプロジェクトから始まります。

DXP Cloudで開発するためのローカル環境をセットアップするには、以下が必要です:

* [JDK 1.8またはJDK 11](http://www.oracle.com/technetwork/java/javase/downloads/index.html) ：Liferay DXPをローカルで実行するために必要

    ```{important}
       JDK 9 と JDK 10 はサポートされていません。 詳しくは最新の [互換性マトリックス](https://help.liferay.com/hc/ja/articles/360049238151) を参照してください。
    ```

* [Gradle 4+](http://www.gradle.org/downloads) ：Liferay DXPおよびDXP Cloudのさまざまなビルドコマンドを実行するために使用されます

* [Git](https://git-scm.com/) ：DXP Cloudにデプロイする変更を追加するために必要

* リポジトリホスティングサービスアカウント：コードの変更をリモートリポジトリにプッシュしたり、DXP Cloudのビルド用に変更を送信するために必要です。 以下の利用可能なオプションのいずれかにアカウントを使用することができます：

  * [GitHub](https://github.com/)
  * [Bitbucket](https://bitbucket.org/)
  * [GitLab](https://gitlab.com/)

```{note}
    Liferay DXP Cloudは、 [Liferay Workspace](https://help.liferay.com/hc/ja/articles/360029147471-Liferay-Workspace) と同様の前提条件を持っています。 [Liferay Dev Studio](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191007&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191038) , [Maven](https://help.liferay.com/hc/ja/articles/360017885592-Maven-Workspace) , [Liferay IntelliJ Plugin](https://plugins.jetbrains.com/plugin/10739-liferay-intellij-plugin) を使ってLiferayワークスペースを作成することに慣れている開発者は、必要なツールのほとんどをすでに設定しています。
```

<a name="additional-information" />

## 追加情報

* [Githubリポジトリの構成](../getting-started/configuring-your-github-repository.md)
* [Bitbucketリポジトリの設定](./configuring-your-bitbucket-repository.md)
* [GitHubリポジトリの設定](./configuring-your-gitlab-repository.md)
* [DXP Cloud導入ワークフローの概要](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md)
* [Liferay DXPインスタンスへのログイン](../getting-started/logging-into-your-dxp-cloud-services.md)
