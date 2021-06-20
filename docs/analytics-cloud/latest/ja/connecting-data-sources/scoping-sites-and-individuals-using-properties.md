# プロパティを使用したサイトと個人の追跡

プロパティでは、ラベルを定義し、単一または複数のサイトに関連付けることができます。 これにより、プロパティに関連するすべてのサイトの分析データを単一のダッシュボードに集約することができます。 特にDXPインスタンス内に複数のサイトがあり、それらを1つのサイトとして分析したい場合に便利です。

例えば、各部門ごとに複数のサブサイトを持つ企業イントラネットを想像してみてください。 各サブサイトは、 *イントラネット*というユーザー定義のプロパティに関連付けることができます。 そして、すべてのアナリティクスデータは、1つのサイトとしてAnalytics Cloudに集約されます。

プロパティを使用すると、その逆も可能です。 ユーザーは、Liferay DXPインスタンスでサイトごとに個別のプロパティを作成できるので、Analytics Cloudユーザーはサイトごとにスコープされた細かい分析データを作成することができます。 管理者は、異なるプロパティにスコープされたAnalytics Cloudユーザーに権限を割り当てることもできます。

## プロパティの作成

各Analytics Cloudワークスペースでは、複数のプロパティを作成して追跡することができます。 スタート時には、新しいDXPデータソースを接続すると、必ずデフォルトのプロパティが作成されます。 データソースの名前はDXPインスタンス名と同じになります。

新しいプロパティを作成するには、Analytics Cloud Workspaceに移動します。 次に、 *設定* -&gt; *プロパティ*に移動し、右上の *新規プロパティ* をクリックします。

![新規プロパティを追加しました。](scoping-sites-and-individuals-using-properties/images/01.png)

プロパティに名前を付けて、 *をクリックして*を保存します。 次に、先ほど作成したプロパティの設定画面が表示されます。 当初、プロパティに関連するサイトはありません。 次にこのプロパティにサイトを追加する方法をご紹介します。

![新しいプロパティができました。](scoping-sites-and-individuals-using-properties/images/02.png)

## サイトをプロパティに同期する

Analytics Cloudでサイトのデータを見るためには、サイトをプロパティに同期させる必要があります。 これを行うには、Liferay DXPインスタンスのコントロールパネルに移動する必要があります。

1.  Liferay DXPインスタンスで *Instance Settings* -&gt; *Analytics Cloud* を表示して、Liferay DXPインスタンスがAnalytics Cloudと同期していることを確認します。 `あなたのDXPインスタンスはAnalytics Cloudに接続されています` というメッセージは、Liferay DXPインスタンスがAnalytics Cloudに正しく接続されていることを表しています。

    ``` important::
      If you do not see the message ``Your DXP instance is connected to Analytics Cloud.``, please review `Connecting Liferay DXP Sites to Analytics Cloud <./connecting-liferay-dxp-to-analytics-cloud.md>`_.
    ```

2.  Analytics Cloud ワークスペースで、 *Synced Sites*をクリックします。 Analytics Cloudで作成した利用可能なプロパティのリストが表示されます。 追跡したいサイトのために、ここに新しいプロパティを作成します。

    ![サイトを追跡するための新しいプロパティを作成します。](scoping-sites-and-individuals-using-properties/images/03.png)

3.  作成したプロパティを選択します。 Liferay DXPインスタンス上のサイトのリストが表示されます。 このプロパティに同期するサイトを選択し、 *保存*をクリックします。 選択したプロパティを使用して、サイトがAnalytics Cloudに同期されるようになりました。

    ![プロパティと同期するサイトの選択](scoping-sites-and-individuals-using-properties/images/04.png)

    ``` important::
       You cannot sync a DXP site with multiple properties. If one of your sites is already synced to another property. It will be greyed out here, and not selectable.
    ```

## 連絡先をプロパティに同期する

連絡先をプロパティに同期するには

1.  Analytics Cloud Workplaceの設定で *Select Contacts*をクリックします。

    ![Analytics Cloudで連絡先として、すべてのユーザーを同期するか、選択したグループを同期するかを選択できます。](scoping-sites-and-individuals-using-properties/images/05.png)

    DXP内のすべてのユーザーを同期することも、ユーザーグループや組織のみを同期することもできます。

2.  同期するユーザーまたはグループを選択し、 *保存*をクリックします。

Liferay DXPからコンタクトデータを同期する についての詳細は[こちら](syncing-contact-data-from-dxp.md)を確認してください。

## プロパティの許可を管理する

管理者は、ワークスペース内のすべてのプロパティを表示する権限を持っています。 ワークスペースのメンバに、特定のプロパティのデータを表示する権限を割り当てることができます。

プロパティ権限を割り当てるには、次のようにします。

1.  Analytics Cloudのワークスペースで、 *設定* -&gt; *プロパティ*に移動し、パーミッションを管理したいプロパティを選択します。

    ``` note::
       By default, properties are accessible to all users of a workspace in Analytics Cloud. You can change that by clicking on the *Select Users* radio button.
    ```

2.  *Add User*をクリックして、プロパティにユーザーを追加します。

追加されたユーザーはプロパティにアクセスできるようになりました。

![プロパティにユーザーを追加します。](scoping-sites-and-individuals-using-properties/images/06.png)

## 異なるプロパティ間の移動

Analytics Cloud ワークスペースで異なるプロパティを交互に表示するには、ナビゲーション バーの左上隅にあるプロパティ名をクリックします。 閲覧許可を得ているプロパティの一覧がメニューに表示されます。 削除されたプロパティをクリックして切り替えます。

![プロパティごとに表示を変える。](scoping-sites-and-individuals-using-properties/images/07.png)

プロパティを見るときに **すべての** アナリティクス データはこのプロパティにスコープされます。 たとえば、

-   訪問者、セッション、ページビューのメトリクスは、各プロパティのみサイト内のセッションに基づいて計算されます。
-   個人では、各プロパティのサイトを訪れた訪問者が表示されます。 これは、DXPインスタンスに5000人の個人がいて、そのうち100人だけがあなたのサイトにセッションを持っている場合を意味します。 個人のリストには100個しか表示されません。 5000人分のデータは同期されていますが、追跡されているサイトを訪問した個人のみが表示されます。
-   作成されたセグメントは、各プロパティにスコープされます。
-   AB テストは各プロパティにのみ適用されます。
