# DXP Cloudの概要

Liferay DXP Cloudは、安全で信頼性の高いエンタープライズクラウドプラットフォームであり、Liferay DXPで驚くべきアプリケーションを簡素化、安全化、提供するために必要なインフラストラクチャとツールを提供します。

次の機能を提供するプラットフォームを使用して、心配をせずにさらに構築できます。

  - [標準に準拠したセキュリティ、自動バックアップ、柔軟なガバナンス](#standards-compliant-security-automated-backups-and-flexible-governance)
  - [高可用性、スケーラビリティ、およびパフォーマンス](#high-availability-scalability-and-performance)
  - [リアルタイムアラート](#real-time-alerts)

これらすべては、Liferay DXPでビジネスクリティカルなソリューションを実行した10年以上の経験を持つLiferayプロフェッショナルの専任チームによって設計、構築、サポートされています。

DXP Cloudは、完全なDevOps CI/CD戦略、ツール、ワークフローを提供することにより、アプリケーションの開発と展開を加速します。

すべてのDXP Cloudプロジェクトには以下が付属しています。

  - [組み込みCI/CDによる開発の加速](#accelerated-development-with-built-in-ci-cd)
  - [アプリケーション開発ツール](#application-development-tools)
  - [洞察力のあるモニタリング](#insightful-monitoring)

## 標準に準拠したセキュリティ、自動バックアップ、柔軟なガバナンス

DXP Cloudはセキュリティを考慮して設計されています。 自信を持ってビルドおよびデプロイミッションクリティカルなサイトDXP雲があることを知っ **ISO 27001** 及び **AICPA SOC2** の認証を受けています。 詳細は、 [セキュリティポリシー](https://www.liferay.com/documents/10182/3292406/Liferay+DXP+Cloud+Data+Security+and+Protection.pdf/78ce7065-9787-1fb2-9c7b-6d7c13f4a3e6?t=1564674972483) を参照してください。

[自動バックアップ](../platform-services/backup-service.md) データの破損や障害が発生した場合に、データとドキュメントが保護され、復元の準備ができていることを確認します。 保存時の暗号化により、ディスクに保存された機密データが、有効なキーなしでユーザーやアプリケーションから読み取られることがなくなります。

![図3：DXP Cloudのバックアップサービスは、データを保護します。](./introduction-to-dxp-cloud/images/01.png)

すぐに使える [ロールとチーム管理](../manage-and-optimize/team-collaboration-and-access-control.md) により、管理者はチームに人を追加または削除し、プロジェクト環境ごとに権限を管理することにより、最大の生産性とセキュリティを提供できます。

![図4：権限を含め、プロジェクトのチームメンバーを管理します。](./introduction-to-dxp-cloud/images/02.png)

## 高可用性、スケーラビリティ、およびパフォーマンス

[クラスタリング](../using-the-liferay-dxp-service/setting-up-clustering-in-dxp-cloud.md) と [ロードバランシング](../infrastructure-and-operations/networking/load-balancer.md) 、可用性の高い、ダウンタイムのない展開を提供し、ミッションクリティカルなサイトがオンラインのままであることを保証します。 DXP Cloudは、マルチリージョンアーキテクチャを通じてデータを提供し、高可用性を保証します。

[オートスケーリング](../manage-and-optimize/auto-scaling.md) は、予期しないトラフィックスパイクによって安定したパフォーマンスを維持するために、帯域幅とコンピューティング容量が動的に追加されることを保証します。 同様に、アクティビティが少ない期間に、不要なリソースを縮小できます。 チームはインスタンスの使用状況に関する自動通知と電子メールアラートを受信するので、自信を持ってリソースを管理できます。

![図5：需要を満たすためにサーバーをオートスケーリングします。](./introduction-to-dxp-cloud/images/03.png)

## リアルタイムアラート

管理者は、リソース使用率の更新をリアルタイムで受信するように、ユーザーごとに [アラート](../manage-and-optimize/real-time-alerts.md) を設定できます。

![図6：管理者はリアルタイムのアラートを設定できます。](./introduction-to-dxp-cloud/images/04.png)

## 組み込みCI/CDによる開発の加速

すぐに使用できる完全に実現されたCI/CD戦略を使用して、アプリケーションをより迅速に提供します。 [GithubとJenkins](../platform-services/continuous-integration.md) 統合により、新しいコミットまたはプル要求ごとに、 [つの本番環境または非本番環境にデプロイできるビルドをトリガーできます](./understanding-dxp-cloud-environments.md)。

これらの堅牢なシステムにより、フォールトトレラントプロセスの作成が可能になり、組織固有のニーズを満たし、一般的なサーバーの問題が発生したときにそれを検出して修復します。 これにより、小さなプログラミングエラーが蓄積してサーバーがクラッシュするのを防ぐことができます。

![図7：ビルドを中央の場所から表示、管理、および展開します。](./introduction-to-dxp-cloud/images/05.png)

## アプリケーション開発ツール

リアルタイムのビルドと [デプロイメントログ](../troubleshooting/log-management.md)を使用して、アプリケーションのデプロイメントとパフォーマンスを追跡します。 チームは、スタックトレースとトラブルシューティングのバグを分析することができます [シェルへのアクセス](../troubleshooting/shell-access.md) Webコンソールまたは端子を介して、および/またはログをダウンロードすることもできます。

![図8：リアルタイムのビルドおよびデプロイメントログは、アプリケーションの問題を解決するのに役立ちます。](./introduction-to-dxp-cloud/images/06.png)

## 洞察に満ちたモニタリング

DXP Cloudは [アプリケーションメトリクスス](../manage-and-optimize/application-metrics.md) 提供するため、ITチームはサイトの正常性とコンピューティング要件を経時的に理解できます。 管理者は、すぐに使用できる [Dynatrace統合](../manage-and-optimize/application-metrics.md#advanced-application-metrics-on-production) を使用して、CPUとメモリの使用状況、データ転送速度などをすばやく理解することもできます。

![図9：さまざまなメトリクスを使用して、Liferay DXPインスタンスを含むDXP Cloudサービスを監視します。](./introduction-to-dxp-cloud/images/07.png)

## 次のステップ

  - [開発要件](./development-requirements.md)
  - [Githubリポジトリの設定](./configuring-your-github-repository.md)
  - [DXP Cloud導入ワークフローの概要](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md)
  - [DXP Cloud導入のウォークスルー](../build-and-deploy/walking-through-the-deployment-life-cycle.md)
