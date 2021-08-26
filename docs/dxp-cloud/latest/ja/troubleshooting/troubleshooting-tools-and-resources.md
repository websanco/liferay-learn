# トラブルシューティングツールとリソース

技術的な問題を素早く診断し、解決する能力が不可欠です。 Liferay DXP Cloudプロジェクトの問題をトラブルシューティングするための利用可能なツールとリソースを熟知してください。

  - [アプリケーションメトリクス](#application-metrics)
  - [リアルタイムアラート](#real-time-alerts)
  - [環境アクティビティ](#environment-activities)
  - [環境ログ](#environment-service-logs)
  - [シェルアクセス](#shell-access)
  - [セルフヒーリング](#self-healing)
  - [災害復旧](#disaster-recovery)
  - [Liferay Cloud Platformの現状](#liferay-cloud-platform-status)
  - [サポートアクセス](#support-access)
  - [ヘルプセンター](#help-center)

これらのツールやリソースを使用することで、プロジェクト活動の追跡、サービスの設定、技術的な問題の解決などが可能になります。

``` note::
   詳細については、Liferayとの法的契約に従うものとします。 法的な契約やサービスに関する情報は、`Liferay Legal <https://www.liferay.com/legal>`_ページをご覧ください。
```

## アプリケーションメトリクス

Liferay DXP Cloudに組み込まれたモニタリング機能により、ユーザーは各環境サービスが使用するリソースを追跡することができます。 これらのアプリケーションメトリクスには、メモリとCPUの使用量、およびネットワークデータ転送量が含まれます。

メトリクスは、デフォルトのDXP Cloudのスタックサービス（Webサーバ、Liferay、検索、データベース、バックアップ）で利用できます。 CIメトリクスはinfra環境でも利用可能です。

![図1：DXP Cloudコンソールのモニタリングページでアプリケーション・メトリクスを見る](./troubleshooting-tools-and-resources/images/01.png)

ユーザーは、[Dynatrace](../manage-and-optimize/application-metrics.md#advanced-application-metrics-production-only)の高度なパフォーマンス・モニタリングを本番環境に統合することもできます。

詳しくは、 [アプリケーションメトリクス](../manage-and-optimize/application-metrics.md) を参照してください。

## リアルタイムアラート

Liferay DXP Cloudは、プロジェクトの予期せぬ動作をシステム管理者に警告することができます。 予期せぬ動作の例としては、オートスケーリングイベント、予想以上のメモリ消費量、割り当てられたCPUクォータへの到達、データベース接続の問題などがあります。

DXP Cloudのコンソールですべての環境アラートを表示し、アラートの設定を行うことができます。

![図2：DXP Cloudコンソールの「Alerts」ページによるアラートの表示と管理](./troubleshooting-tools-and-resources/images/02.png)

詳しくは [Real-Time Alerts](../manage-and-optimize/real-time-alerts.md) をご覧ください。

## 環境アクティビティ

ユーザーはDXP Cloudのコンソールで環境のアクティビティを監視することができ、2つのセクションに分類されます。 *ビルドとデプロイメント* と *一般的なアクティビティ*です。

ビルド、デプロイメント、サービスアクティビティなどのステータスを確認し、環境問題のトラブルシューティングに役立てることができます。

![図3：DXP Cloudコンソールからの環境アクティビティの表示](./troubleshooting-tools-and-resources/images/03.png)

詳しくは [Team Activities](../manage-and-optimize/team-activities.md) をご覧ください。

## 環境ログ

環境ログは、プロジェクトにおける技術的な問題を診断し、解決するために非常に重要です。 ユーザーはDXP CloudのコンソールやOS端末から環境ログにアクセスし、ダウンロードすることができます。

各DXP Cloudサービスのアプリケーションログ、ステータスログ、ビルドログを提供しています。

  - **Application Logs**：これらのログは、アプリケーションが実行され、ユーザーがアクセスした後に生成されたランタイム情報をリストアップします。
  - **Application Logs**：これらのログは、アプリケーションが実行され、ユーザーがアクセスした後に生成されたランタイム情報をリストアップします。
  - **Build Logs**：これらのログは、アプリケーションの起動時に生成されるビルド情報を一覧表示します。

![図4：DXP CloudコンソールのLogsページによるログの表示](./troubleshooting-tools-and-resources/images/04.png)

詳しくは [Log Management](./log-management.md) をご覧ください。

## シェルアクセス

DXP Cloudコンソールのコマンドラインツールは、スピード、コントロール、トレーサビリティ、スクリプト、自動化の機能を提供し、開発者のワークフローに貢献します。

トラブルシューティングの際には、シェルアクセスツールを使用してアプリケーションの内部を確認し、ログでは簡単に見つけられない詳細を見ることができます。

![図5：DXP Cloudコンソールのシェルアクセスツールを使って、アプリケーション内部で何が行われているかを確認する。](./troubleshooting-tools-and-resources/images/05.png)

シェルは、 `liferay`、 `search`、 `webserver` など、ほとんどのサービスでアクセス可能です。 サーバーのファイルシステムに直接アクセスしたり、トラブルシューティングのためのコマンドを実行したりすることができます。

例えば、 `liferay` サービスのシェルから以下のコマンドを実行して、アドレスや外部サーバーへの接続をテストすることができます。

``` bash
curl -v [address]
```

詳しくは [Shell Access](./shell-access.md) をご覧ください。

## セルフヒーリング

DXP Cloudのセルフヒーリング機能は、サービスやアプリケーションが応答しなくなったことを検知し、応答しなくなったサービスを回復するための手順を自動的に開始します。 このプラットフォームでは、プローブを使ってサービスを監視しています。

DXP Cloudでは、アプリケーションを管理するために2つのプローブを併用しています。

  - **Liveness Probe**：サービスが実行されているかどうかを示すプローブです。
  - **Readiness Probe**：サービスがリクエストを受信する準備ができているかどうかを示すプローブです。

各プローブの設定については、「 [セルフヒーリング](./self-healing.md) 」を参照してください。

## 災害復旧

災害時には、Liferay DXP Cloudはプロジェクト復旧のための2つの戦略を提供します。自動」と「Cross-Region」です。

Liferay DXP Cloudの自動災害復旧戦略は、同一地域内の3つのアベイラビリティーゾーン間でサービスを複製します。 あるアベイラビリティーゾーンが利用できなくなった場合、ロードバランサーはユーザーの操作を必要とせず、残りのアベイラビリティーゾーンに自動的にルーティングします。

しかし、地域を越えた災害では、ユーザーの介入が必要です。 クロスリージョン ディザスタリカバリのツールやプロセスについては、 [クロスリージョンディザスタ リカバリの設定](./configuring-cross-region-disaster-recovery.md)を参照してください。

両方のディザスタリカバリ戦略の詳細は、 [ディザスタリカバリの概要](./disaster-recovery-overview.md) を参照してください。

## Liferay Cloud Platformの現状

技術的な問題を解決する際には、すべてのクラウドプラットフォームシステムが動作していることを確認してください。

ユーザーは、 [Liferay Cloud Platform](https://status.liferay.cloud/) ステータス ページから、Liferay Cloud Platform システムのステータス、計画されたメンテナンス ウィンドウ、およびインシデントの履歴を確認できます。

クラウドプラットフォームのステータス変更の通知を受け取るには、 *Subscribe to Updates*をクリックし、希望する通知方法を選択してください。

![図6：Liferay Cloud Platformシステムのステータスを表示します。](./troubleshooting-tools-and-resources/images/06.png)

詳細は、 [Liferay Cloud Platformのステータス](./liferay-cloud-platform-status) を参照してください。

## サポートアクセス

サポートアクセスはオプションの環境設定で、LiferayのエンジニアがDXP Cloudのプロジェクト環境に直接アクセスすることで、トラブルシューティングを迅速に行うことができます。

デフォルトでは、サポートアクセスは各プロジェクト環境で有効になっていますが、管理者は *設定* ページから無効にすることができます。

![図7：環境管理者は「Settings」ページで「Support Access」を有効または無効にできる](./troubleshooting-tools-and-resources/images/07.png)

詳しくは、 [Support Access](./support-access.md) をご覧ください。

## ヘルプセンター

DXP Cloud [Documentation](https://learn.liferay.com/dxp-cloud-latest/) で回答されていない質問がある場合や、問題を報告したい場合は、Liferay [Help Center](https://help.liferay.com/) にサインインして、サブスクリプション専用のリソースを閲覧したり、チケットを提出したりしてください。

![図8：Liferay Help Centerページによるサブスクリプション専用リソースの閲覧またはチケットの提出](./troubleshooting-tools-and-resources/images/08.png)

ここからユーザーは、DXP Cloudの [ナレッジベース](https://help.liferay.com/hc/en-us/categories/360001132872)、 [プロダクトサポート](https://help.liferay.com/hc/en-us/articles/360030208451-DXP-Cloud-Support-Overview)、 [アナウンスメント](https://help.liferay.com/hc/en-us/categories/360001192512)にアクセスすることができます。

また、ユーザーは、DXP Cloudの最新の [ヘルプセンターの更新情報](https://www.liferay.com/web/l/subscribe-to-liferay-dxp-cloud-updates)のメール通知を受け取ることができます。 これらのアップデートには、新しいプラットフォームのリリース、サービスアップデート、セキュリティアラートやパッチなどが含まれます。

``` note::
   Liferay DXP Cloudの通知は、有効なLiferay Enterprise Subscriptionをお持ちのお客様にのみ配信されます。
```

追加のサポートについては、ユーザーはLiferayのグローバルな [サポートセンター](https://help.liferay.com/hc/en-us/articles/360017784212?_ga=2.254167624.1908736764.1562000563-1350017715.1560788053) に電話で問い合わせるか、 [ヘルプセンター](https://help.liferay.com/) のページからチケットを提出することができます。

チケットを提出する際には、現在使用しているビルドイメージ、直面している動作や問題、問題を再現するための手順、実際の動作と期待される動作の両方の説明などの情報を提供してください。

## 追加情報

  - [DXP Cloudサポートの概要](https://help.liferay.com/hc/en-us/articles/360030208451-DXP-Cloud-Support-Overview)
  - [Liferay DXP Cloud ナレッジベース](https://help.liferay.com/hc/en-us/categories/360001132872)
  - [Liferay DXP Cloudの発表](https://help.liferay.com/hc/en-us/categories/360001192512)
  - [サポートアクセス](./support-access.md)
