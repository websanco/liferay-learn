# Jenkinsステージの再起動

CIサービスでは、Jenkinsのスタッシュを保存するように設定することができます。 この隠し場所を使って、Jenkinsビルドの特定のステージを再開することができます。

## ジェンキンス・スタッシュの保存

以下のCIサービス変数を使用して、スタッシュとアーティファクトが保存される完了ビルドの数を決定します。

  - `LCP_CI_PRESERVE_STASHES_BUILD_COUNT`
  - `LCP_CI_ARTIFACT_NUM_TO_KEEP`

以下の例では、スタッシュは過去3回のビルド分、アーティファクトは過去6回のビルド分が保存されています。

![これらの変数を使って、どのアーティファクトが保存されているかを判断します。](./restarting-jenkins-stages/images/01.png)

デフォルトでは、 `LCP_CI_PRESERVE_STASHES_BUILD_COUNT` の値は `20`に設定されています。一方、 `LCP_CI_ARTIFACT_NUM_TO_KEEP` の値は `1`に設定されています。 ステージの再起動が重要でない場合は、それらの値を `0` に設定して、CIがstashやartifactを保持しないようにすることができます。

``` important::
   LCP_CI_PRESERVE_STASHES_BUILD_COUNT``変数に設定されているビルド数に関わらず、 ``LCP_CI_ARTIFACT_NUM_TO_KEEP`` 変数で許可されている以上のビルド数でスタッシュを保存することはできません。
```

## Jenkinsのステージを再起動する方法

以下の手順で、Jenkinsステージを再起動します。

1.  プロジェクトのCIサービスのページ（例： `ci-<project>-infra.lfr.cloud`）にアクセスし、 *Open Blue Ocean* プラグインをクリックして、プロジェクトのパイプラインを表示します。

    ``` note::
       パイプラインのステージは、Jenkinsの標準コンソールで再起動することができますが、Open Blue Oceanプラグインは、パイプラインのステージを表示または管理する際に、より明確で直感的なユーザーエクスペリエンスを提供します。
    ```

    ![Open Blue Ocean」プラグインをクリックすると、プロジェクトのパイプラインが表示されます。](./restarting-jenkins-stages/images/02.png)

2.  目的のパイプラインを選択し、目的のビルドをクリックします。

    ![再開したいステージのあるビルドをクリックします。](./restarting-jenkins-stages/images/03.png)

3.  目的のステージを選択し、 *再起動*をクリックします。

    ![目的のステージを選択し、「Restart」をクリックします。](./restarting-jenkins-stages/images/04.png)

    これにより、選択したステージから始まる新しいJenkinsランが開始され、ベースとなったランと同じPRとコミット情報を持つことになります。 新しい説明文は、その前に実行された内容と、再開されたステージの名前が自動的に生成されます。 ナビゲーションバーの左上にあるパンくずボタンを使って、前のランにすばやく戻ることができます。

    ![これにより、選択したビルドに基づいて新しいビルドが実行されます。](./restarting-jenkins-stages/images/05.png)

## 追加情報

  - [継続的インテグレーション](../platform-services/continuous-integration.md)
  - [DXP Cloud導入ワークフローの概要](./overview-of-the-dxp-cloud-deployment-workflow.md)
  - [CLIツールによる変更点のデプロイ](./deploying-changes-via-the-cli-tool.md)
