# DXP Cloudインフラストラクチャー

DXP Cloudは、堅牢で信頼性が高く、管理しやすいLiferay DXPの実装を提供するための主要コンポーネントを集めた柔軟なプラットフォームです。 この図は、これらのコンポーネントがどのように組み合わされてこの実装が完成するかを示しています。

![DXP Cloudに統合されている主要なサービスやコンポーネントはすべて、ここにその接続先が示されています。](./dxp-cloud-infrastructure/images/01.png)

各コンポーネントの説明は以下のリストを参照してください。

## 高度なアプリケーション・モニタリング

DXP Cloudでは、 [Dynatrace](https://www.dynatrace.com/) モニタリングの使用をサポートしています。 このアプリは、アプリケーションのパフォーマンスやインフラなどをリアルタイムに監視します。 詳細は、 [高度なアプリケーションメトリクス](../manage-and-optimize/application-metrics.md#advanced-application-metrics-production-only) を参照してください。

## リポジトリの統合

DXP CloudはGitを使ってプロジェクトのソースコードと統合します。 Jenkinsとの統合により、新しいコミットやプルリクエストが自動的にビルドのトリガーとなり、DXP Cloud環境にデプロイすることができます。

[GitHub](../getting-started/configuring-your-github-repository.md)、 [Bitbucket](../getting-started/configuring-your-bitbucket-repository.md)、 [GitLab](../getting-started/configuring-your-gitlab-repository.md) の統合に対応しています。

## VPNサーバーとクライアント

DXP Cloud環境では、お客様自身のVPNサーバーを利用した暗号化トンネルでデータを送受信することができます。 DXP Cloudでは、サポートされているVPNからその環境への接続を可能にするクライアント・ツー・サイトVPNサービスを提供しています。 この接続では、OpenVPNとIPSec（IKEv2）のプロトコルがサポートされています。 詳細は、 [VPNインテグレーションの概要](../infrastructure-and-operations/networking/vpn-integration-overview.md) を参照してください。

## Docker Hub

DXP Cloudでは、サービスイメージやバージョン（タグとして共有）の公開プラットフォームとして、 [Docker Hub](https://hub.docker.com/) を使用しています。 また、この実装により、お客様は任意の公開リポジトリやローカルの開発ワークスペースからカスタムDockerイメージを使用することができます。 サービスイメージのレジストリについては、Docker Hubの [DXP Cloud profile](https://hub.docker.com/u/liferaycloud) を参照してください。

## DDoSプロテクション

DXP Cloudは、分散型サービス妨害（DDoS）攻撃からの保護を内蔵しています。 また、[GCP POPネットワーク](https://peering.google.com/#/infrastructure) を使用して、受信トラフィックが殺到しないように環境を保護し、世界中のエンドユーザーにコンテンツを安全かつ安定的に配信しています。

## HTTP(S) ロードバランサー

DXP Cloudは、HTTP(S)のトラフィックを複数のインスタンスに分散させます。 また、 [GKE Ingress](https://cloud.google.com/kubernetes-engine) を使用してトラフィックを分散させ、サービスが過負荷になるリスクを低減します。

## ウェブサーバー

すべてのDXP Cloud環境では、他のサービスへのゲートウェイとして [Nginx](https://www.nginx.com/) Webサーバーが使用されています。 これには、レイヤー7攻撃防御、IP保護、監査ログを提供する内蔵ファイアウォールが含まれます。

このサーバーは、DXP Cloudの主要サービスの一つとして設定可能です。 詳しくは [Web Server Service](https://learn.liferay.com/dxp-cloud/latest/en/platform-services/web-server-service.html) を参照してください。

## Liferay DXP

[Liferay DXP](https://www.liferay.com/products/dxp) は、さまざまなアプリケーションからのデータやサービスを、1つの中央ユーザー・インターフェース・プラットフォームに接続し、編成し、統合しています。 DXP Cloudは、クラウド上のDXPインスタンスのための迅速かつ信頼性の高い実装を提供します。 [Webサーバーサービス](#web-server) は、DXPへのすべてのHTTP(S)トラフィックのエントリーポイントとゲートウェイを提供します。

設定とカスタムモジュールを[独自のプロジェクトリポジトリ](#repository-integration)に追加することにより、DXPサービスは通常のDXPインストールと同じように高度にカスタマイズできます。 詳しくは [Liferay DXPサービスの概要](../using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.md) を参照してください。

## 検索機能

DXP Cloudでは、各環境内のRESTfulな検索、インデックス、分析機能として、 [Elasticsearch](https://www.elastic.co/elasticsearch/service) を使用しています。 検索サービスは、TCPまたはUDP接続によるプライベートネットワークトラフィックを介してのみアクセス可能です。

Elasticsearchエンジンは、DXP Cloudの主要サービスの一つとして提供されているため、高い拡張性とカスタマイズ性を備えています。 詳しくは、 [Search Service](../platform-services/search-service.md) を参照してください。

## MySQLデータベース

DXP Cloudは、Liferay DXPサービスをプライベートネットワーク内の独立した、データベースストレージサービスに接続します。 [MySQL](https://www.mysql.com/) データベースを使用して、信頼性、安全性、拡張性の高いDXPの実装をクラウドで実現しています。 データベースサービスは、TCPまたはUDP接続によるプライベートネットワークトラフィックを介してのみアクセス可能です。

MySQLデータベースは、DXP Cloudの主要サービスの一つとして提供されているため、自由に設定・カスタマイズが可能です。 詳しくは、 [Database Service](../platform-services/database-service/database-service.md) を参照してください。

## パーシステッドストレージ（ボリュウム

DXP Cloudでは、ボリュームを必要とするすべてのサービスのために、プライベートネットワーク内の永続的なデータストレージとして使用しています。 サービスの種類に応じて、ボリュームはそのサービス専用のSSDドライブに格納されるか、ネットワークファイルシステム（NFS）上でサービス間で共有されるかのいずれかになります。 ボリュームは、プライベートネットワークトラフィックを介してのみアクセス可能です。

デフォルトでは、Web Server、Liferay DXP、Backupの各サービスはNFSでボリュームを保存し、SearchとCIの各サービスは専用のSSDディスクを使用しています。 詳細は、 [永続的なファイルストレージボリュームの設定](../build-and-deploy/configuring-persistent-file-storage-volumes.md) を参照してください。

## バックアップ

DXP Cloudは、Liferay DXPのDatabaseサービスとボリュームのコピーを、プライベートネットワーク内に保存します。 バックアップは本番環境専用に作成されますが、お客様は本番環境のバックアップを任意の環境にリストアすることができます。 デフォルトでは、バックアップはHTTP(S)接続を介してパブリックウェブトラフィックにアクセスできます。

バックアップはDXP Cloudの主要サービスの一つとして提供されており、その運用ルール（バックアップの頻度や保存期間など）を自由に設定することができます。 詳しくは [バックアップサービスの概要](../platform-services/backup-service/backup-service-overview.md) を参照してください。

## CIサーバー

DXP Cloudは、開発中のお客様のプロジェクトのビルドとデプロイメントのプロセスを自動化します。 プロジェクトが [顧客のリポジトリ](#repository-integration)に結びついている状態で、Jenkinsをウェブフックで使用し、新しいコミットやプルリクエストを自動的にあらゆる環境にデプロイ可能なビルドに変換します。 このサービスは、特別な独立した `インフラ` 環境の中に存在しており、通常のユーザーはアクセスできません。 またDXP Cloudでは、Jenkinsのビルドの詳細を確認できるダッシュボードをクラウドコンソールでお客様に提供しています。

Jenkinsビルドに使用するCIサービスは、DXP Cloudの主要サービスの一つとして提供されており、お客様はこのサービスを設定し、必要に応じてJenkinsのパイプラインをカスタマイズすることもできます。 詳しくは [継続的インテグレーション](../platform-services/continuous-integration.md) を参照してください。

## CLIツール

DXP Cloudでは、CLIツールを使って、環境やサービスに対して様々な管理作業を行うことができます。 このアプリケーションは、お客様のすべてのプロジェクトに使用できる中央のインターフェースとして機能し、HTTP(S)トラフィックにアクセスできます。 詳しくは、 [Command-line Tool](./command-line-tool.md) 参照してください。

## アンチウイルス

DXP Cloudは、トロイの木馬、ウイルス、マルウェア、その他の悪意のある脅威を、 [ClamAV](https://www.clamav.net/)を使って自動的に検出します。 このエンジンは、クラウド内のバックグラウンドサービスとして動作しており、ユーザーや他のサービスからはアクセスできないようになっています。 DXP Cloudの各クラスターには、アンチウイルスのインスタンスが1つずつ稼働しています。

## 侵入検知システム

DXP Cloudは、 [Threat Stack](https://www.threatstack.com/cloud-security-platform/intrusion-detection)を使用して、コンポーネント内の侵入を自動的に検出します。 このシステムも内蔵の [ウイルス対策ソフト](#antivirus)と同様にバックグラウンドサービスとして動作しており、ユーザーや他のサービスからはアクセスできないようになっています。 DXP Cloudの各クラスターには1つのインスタンスがあります。
