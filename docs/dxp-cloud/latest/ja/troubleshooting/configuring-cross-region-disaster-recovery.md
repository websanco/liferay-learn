# クロスリージョンディザスタ リカバリの設定

DXP Cloudは、大規模なインシデントの場合にお客様が障害復旧（DR）手順を利用するための2つの方法を提供します：自動障害復旧と地域間障害復旧。 障害復旧シナリオに対するDXP Cloudのアプローチについては、 [障害復旧の概要](./disaster-recovery-overview.md)を参照してください。

ここでは、地域間の災害時にデータを手動で回復する方法をご紹介します。 これらの手順は、同じリージョン内の3つのゾーンすべてに同時に妥協がある場合にのみ必要です。

* [初期設定](#initial-setup)
* [インシデント中](#during-an-incident)
* [インシデント後の回復](#post-incident-recovery)

<a name="initial-setup" />

## 初期設定

Liferayは、地域間の災害を管理するための専用のDXP Cloud環境を提供します。 この例では、本番環境が **europe-west2** リージョンに格納されており、リージョンが危険にさらされていると想定しています。 本番環境でのダウンタイムとデータ損失を防ぐには、ディザスタリカバリ環境を、 **us-west1** などの運用領域外にシフトする必要があります。 したがって、この5番目の障害復旧（DRに短縮された）環境は、インシデント中に生成された新しいユーザーデータを格納するバックアップとして機能します。

障害復旧環境のセットアップを希望するDXP Cloudのお客様は、DR環境をプロビジョニングするために、営業担当者に連絡する必要があります。 この新しい環境は、他の利用可能な環境 (例えば、 `dev`、 `infra`、 `uat`、および `prd`) とともに表示されます。

![災害復旧環境を作成したら、他の環境と同じようにそれを選択できます。](./configuring-cross-region-disaster-recovery/images/01.png)

DXP Cloudシステム管理者は、DR環境と本番環境の両方に対する完全な管理権限を持っている必要があります。

<a name="verify-vpn-settings-in-the-dr-environment" />

### DR環境でのVPN設定の確認

本番環境でVPNが有効になっている場合は、DR環境のVPNも有効になっていることを確認してください。

2つの環境が接続されていることを確認するには：

1. 左側のメニューで、DR環境の[**設定**]タブをクリックします。

1. VPNセクションで、以下の情報を入力します：

    ***VPNタイプ** ：OpenVPN
    ***サーバーアドレス** ：サーバーアドレス。
    ***アカウント名** ：管理者のメールアドレスです。
    ***Password** ：管理者のパスワードです。
    ***証明書** : 証明書のコードです。
    ***転送IP** ：転送IPアドレス。
    ***転送ポート** ：転送ポート番号。
    ***ローカルホスト名** ：VPNのホスト名。
    ***ローカルポート** ：ローカルポート番号。

1. ［**Connect VPN**］をクリックします。

VPNへの接続の詳細は、 [VPN接続](../infrastructure-and-operations/networking/connecting-a-vpn-server-to-dxp-cloud.md)を参照してください。

<a name="deploy-the-latest-stable-build-from-production-to-the-dr-environment" />

### 最新の安定したビルドを本番環境からDR環境にデプロイする

次に、最新の安定したビルドを本番環境でDR環境にデプロイする必要があります。 そのためには、 [DXP Cloud展開ワークフローの概要](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md)で説明したのと同じ手順に従ってください。

<a name="set-up-automated-backup-restores-to-disaster-recovery" />

### ディザスタリカバリへの自動バックアップリストアの設定

ディザスタリカバリ環境のセットアップを完了するには、自動バックアップリストアを設定します。 これにより、インシデントが発生したときに備えて、DR環境に常に最新のバックアップが用意されます。

まず、本番環境のマスタートークンを取得します（これには、 `liferay` サービスシェルにアクセスするための管理者権限が必要です）：

1. DXP Cloudコンソールで、本番環境&rarr; `liferay`のサービスページに移動します。

1. **Shell** タブをクリックします。

1. 次のコマンドを実行して、環境のマスタートークンを取得します：

    ```bash
    env | grep LCP **PROJECT** MASTER_TOKEN
    ```

    マスタートークンは、結果の `=` の後の16進数のIDとなります。

本番環境のマスタートークンを取得したら、DR環境で [環境変数](../reference/defining-environment-variables.md)を設定します。

  ***LCP** EXTERNAL **PROJECT** MASTER **TOKEN** ：本番環境のマスタートークン

  ***LCP** EXTERNAL **PROJECT_ID** ：本番環境のプロジェクトID（たとえば、 `acme-prd`）

  ***LCP** BACKUP **RESTORE_SCHEDULE** : 自動バックアップの頻度を定義する [cronスケジュール](https://crontab.guru/) の値。 詳細については、 [Scheduling Automated Backups and Cleanups](../platform-services/backup-service/backup-service-overview.md#scheduling-automated-backups-and-cleanups) を参照してください。

これらの変数をDR環境に保存することで、自動復元が可能になります。

<a name="during-an-incident" />

## インシデント中

上記の例を続けて、 **europe-west2** リージョンでホストされている本番環境が、現地時間の午後2時に1時間ごとにバックアップされるようにスケジュールされていると想定します。 このシナリオでは、現地時間の午後2時30分に地域が危険にさらされます。 その間の30分間はバックアップが生成されていないため、データベースとドキュメントのバックアップを本番環境から障害復旧環境に復元する必要があります。 最後の安定した環境は、午後2時に作成されたバージョンです。

地域間でのインシデントの際には、以下の手順に従ってください：

1. [データベース復元スケジュールを無効にします。](#disable-the-database-restoration-schedule)
1. [最新の本番データをDR環境にコピーします。](#copy-latest-production-data-to-the-dr-environment)
1. [DR環境のVPNステータスの確認とインデックスの再構築を行います。](#verify-the-dr-environment-s-vpn-status-and-reindex)
1. [カスタムドメイントラフィックをDR環境に送信します。](#direct-custom-domain-traffic-to-the-dr-environment)

<a name="disable-the-database-restoration-schedule" />

### データベース復元スケジュールの無効化

インシデントが発生している間は、DR環境がユーザーがアクセスできるメインの環境となるため、通常のデータベース復元スケジュールでは、本番環境をDR環境に切り替えた後にデータが上書きされる可能性があります。

[`LCP_BACKUP_RESTORE_SCHEDULE` 環境変数](../platform-services/backup-service/backup-service-overview.md#environment-variables-reference) を使用してDR環境にデータを定期的に復元している場合は、変数を削除して復元スケジュールを一時的に無効にします。 これにより、インシデント中に作成されたデータが、スケジュールされたリストアによって上書きされることを防ぎます。

アクセス可能な状態で復元スケジュールを無効にするには、以下の手順に従ってください：

1. DXP Cloudコンソールで、DR環境 &rarr; Backup serviceページ &rarr; 環境変数に移動します。

1. 目のアイコンをクリックすると、 `LCP_BACKUP_RESTORE_SCHEDULE` の変数の値が表示されます：

    ![目のアイコンをクリックすると、スケジュール設定が表示されます。](./configuring-cross-region-disaster-recovery/images/02.png)

1. `LCP_BACKUP_RESTORE_SCHEDULE` の値をメモしておき、事故後にすぐに交換できるようにしておきましょう。

1. `LCP_BACKUP_RESTORE_SCHEDULE` 環境変数を削除し、変更を保存します。

<a name="copy-latest-production-data-to-the-dr-environment" />

### 最新の本番データをDR環境にコピーする

次に、本番環境のバックアップを復元して、DR環境に最新のアップデートがあることを確認します。

```{important}
   `LCP_BACKUP_RESTORE_SCHEDULE`環境変数を使用してDR環境に定期的に復元していた場合は、（設定した頻度に応じて）より最近の安定したバックアップがすでに復元され、準備ができているかもしれません。 最近自動的にバックアップが復元された場合は、バックアップの手動復元をスキップしてください。
```

次の手順に従って、本番環境の最新の安定したバックアップをDR環境に復元します：

1. DR環境で、[**バックアップ**]タブをクリックします。
1. 本番環境に対応するタブをクリックします。

    ```{note}
       バックアップ履歴では、バックアップが2つのタブに一覧表示されます。 1つはDR環境用、もう1つは本番環境用です。
    ```

1. [**アクション**]ボタン（![Actions](./configuring-cross-region-disaster-recovery/images/03.png)）をクリックして、本番環境の最新の安定したバックアップを取得し、[**復元**]を選択します。

    ![最新の安定したバックアップを本番環境からDR環境に復元します。](./configuring-cross-region-disaster-recovery/images/04.png)

<a name="verify-the-dr-environments-vpn-status-and-reindex" />

### DR環境のVPNステータスの確認とインデックスの再作成

次に、以下の手順でDR環境がトラフィックを受信できる状態にします：

1. DR環境の ［**設定**］ &rarr; ［**VPN**］ ページに移動して、VPNがDR環境に接続されていることを確認します。

    ![DR環境のVPNステータスをチェックして、正しく接続されていることを確認します。](./configuring-cross-region-disaster-recovery/images/05.png)

    適切なVPNが接続されていない場合は、接続を設定してください。 詳しくは [DXP CloudへのVPNサーバーの接続](../infrastructure-and-operations/networking/connecting-a-vpn-server-to-dxp-cloud.md) をご覧ください。

1. DXPインスタンスにログインします（カスタムドメインはまだDR環境を指していないため、IPアドレスを使用します）。

1. [**グローバルメニュー**](![Applications Menu icon](./configuring-cross-region-disaster-recovery/images/06.png) ) &rarr; [**コントロールパネル**] &rarr; [**検索**] に移動します。

1. ［**すべてインデックスを再構築**］ をクリックします。

インデックスの再構築が完了するまでしばらく待ちます。

<a name="direct-custom-domain-traffic-to-the-dr-environment" />

### カスタムドメイントラフィックをDR環境に送信する

DR環境でのWebサーバーサービスのカスタムドメインは、元の本番環境のものと一致する必要があります。 また、その設定を実稼働環境から削除する必要もあります：

1. DR環境では、左側のメニューで ［**Services**］ を選択します。
1. サービスのリストで ［**webserver**］ をクリックします。
1. [**Custom Domains**]タブをクリックし、本番環境のドメインと一致するようにカスタムドメインを設定します。
1. 本番環境で同じ設定に移動し、カスタムドメイン設定を削除します。
1. DNSレコードを更新し、カスタムドメインをDR環境に追加します。 詳細は、 [カスタムドメイン](../infrastructure-and-operations/networking/custom-domains.md)を参照してください。

これにより、すべてのトラフィックがDR環境に送られます。

![webserverサービスの場合、DR環境のカスタムドメインを構成して、本番環境のカスタムドメインと一致させます。](./configuring-cross-region-disaster-recovery/images/07.png)

<a name="post-incident-recovery" />

## インシデント後の回復

リージョンインシデントが終了したら、元のリージョンの本番環境（この例では **europe-west2**）に戻す必要があります。 次の手順を実行します：

1. [データ作成を凍結させます。](#put-a-freeze-on-data-creation)
1. [DR環境の手動バックアップを作成します。](#create-a-manual-backup-of-the-dr-environment)
1. [手動バックアップを本番環境に復元します。](#restore-the-manual-backup-to-production)
1. [VPNステータスの確認とインデックスの再作成を行います。](#verify-vpn-status-and-reindex)
1. [サーバーのカスタムトラフィックを本番環境に復元します。](#restore-server-custom-traffic-to-production)
1. [データベース復元スケジュールを復元します。](#restore-the-database-restoration-schedule)

<a name="put-a-freeze-on-data-creation" />

### データ作成の凍結

通常の本番環境に切り替えたときに、最近の変更によるデータ損失を防ぐために、DR環境でのコンテンツ作成をすべて凍結する必要があります。 本番環境に戻る準備ができたら、手動バックアップを行う前に、データベース管理者と協力してデータの凍結を手配します。

<a name="create-a-manual-backup-of-the-dr-environment" />

### DR環境の手動バックアップを作成する

インシデント中、DR環境は本番環境として機能するため、障害時に生成された新しいデータが含まれます。 このデータを保持するには、DR環境をバックアップする必要があります：

1. DR環境では、左側のメニューで[**Backups**]をクリックします。
1. ［**Backup Now**］ をクリックします。

![DR環境の手動バックアップを作成します。](./configuring-cross-region-disaster-recovery/images/08.png)

<a name="restore-the-manual-backup-to-production" />

### 本番環境への手動バックアップの復元

DR環境から通常の本番環境にデータを復元します。

1. DR環境では、左側のメニューで[**Backups**]をクリックします。
1. DR環境に対応するタブをクリックします。

    ```{note}
       バックアップ履歴では、バックアップが2つのタブに一覧表示されます。 1つはDR環境用、もう1つは本番環境用です。
    ```

1. 最新のバックアップ（作成したばかりのバックアップ）の場合は、[**アクション**]ボタン（![Actions](./configuring-cross-region-disaster-recovery/images/03.png)）をクリックし［**復元**]を選択します。
1. 本番環境を選択し、[**Deploy Build**]をクリックします。

![バックアップを本番環境にデプロイします。](./configuring-cross-region-disaster-recovery/images/09.png)

<a name="verify-vpn-status-and-reindex" />

### VPNステータスの確認とインデックスの再作成

以下の手順で、本番環境がトラフィックの受信の準備ができていることを確認してください：

1. 本番環境の ［**設定**］ &rarr; ［**VPN**］ ページに移動して、VPNが本番環境に接続されていることを確認します。

   適切なVPNが接続されていない場合は、接続を設定してください。 詳しくは [DXP CloudへのVPNサーバーの接続](../infrastructure-and-operations/networking/connecting-a-vpn-server-to-dxp-cloud.md) をご覧ください。

1. DXPインスタンスにログインします（カスタムドメインはDR環境を指しているため、IPアドレスを使用します）。

1. ［**グローバルメニュー**］ &rarr; ［**コントロールパネル**］ &rarr; ［**検索**］ に移動します。

1. ［**すべてインデックスを再構築**］ をクリックします。

インデックスの再構築が完了するまでしばらく待ちます。

<a name="restore-server-custom-traffic-to-production" />

### サーバーのカスタムトラフィックを本番環境に復元する

インシデント中にWebサーバーサービスがすべてのトラフィックをDR環境にリダイレクトしたため、すべてのトラフィックが元の本番環境にリダイレクトされるように、これらの設定を再度更新する必要があります。

1. 左メニューの ［**サービス**］ に移動します。
1. サービスのリストで ［**webserver**］ クリックします。
1. ［**Custom Domains**］ タブをクリックします。

   ![DR環境からカスタムドメインを削除します。](./configuring-cross-region-disaster-recovery/images/10.png)

1. DR環境からカスタムドメインを削除します。

    ```{warning}
       カスタムドメインを削除すると、本番環境が再びトラフィックを受信するまで、ダウンタイムが発生します。
    ```

1. DNSレコードを更新し、カスタムドメインを本番環境に戻します。 詳細は、 [カスタムドメイン](../infrastructure-and-operations/networking/custom-domains.md)を参照してください。
1. ［**カスタムドメインの更新**］ をクリックします。

これでトラフィックは元の本番環境に戻されます。 DR環境に自動的にスケジュールされたデータベースの復元を使用しない場合、災害復旧プロセスは完了します。

<a name="restore-the-database-restoration-schedule" />

### データベース復元スケジュールを復元する

`LCP_BACKUP_RESTORE_SCHEDULE` 環境変数を使用して、インシデントの前にDR環境に定期的に復元していた場合は、この変数を再度復元して、復元スケジュールを再開します：

1. DXPクラウドのコンソールで、[DR環境] &rarr; [バックアップサービスページ] &rarr; [環境変数]に移動します。

1. `LCP_BACKUP_RESTORE_SCHEDULE` 環境変数を追加し, [削除した際に](#disable-the-database-restoration-schedule) メモした値を復元します。

1. 変更を保存します。

これで、DXP Cloud環境は通常の操作を再開することができます。
