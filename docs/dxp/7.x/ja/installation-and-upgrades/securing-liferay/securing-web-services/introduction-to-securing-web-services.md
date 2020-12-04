# Webサービスの保護の概要

Liferay Portalは、Webサービスに4つのセキュリティレイヤーを提供しています。

**IPアクセス許可レイヤー：**Webサービス呼び出し要求の発信元のIPアドレスは、ポータルプロパティファイルでホワイトリストに登録されている必要があります。 ホワイトリストに登録されていないIPアドレスからのWebサービスの呼び出しは自動的に失敗します。

**サービスアクセスポリシーレイヤー：**Webサービス呼び出し要求に対応するメソッドは、有効になっている各サービスアクセスポリシーによってホワイトリストに登録されている必要があります。 ワイルドカードを使用して、明示的にホワイトリストに登録する必要があるサービスクラスとメソッドの数を減らすことができます。

**認証/検証レイヤー（ブラウザーのみ）：**Webサービス呼び出し要求がブラウザから送信される場合、その要求には認証トークンが含まれている必要があります。 この認証トークンは、`p_auth` URLパラメータの値です。 トークンは@product@によって生成され、ブラウザセッションに関連付けられます。 JSON WebサービスのAPIページまたは`Liferay.Service(...)`を使用したJavaScriptを介して@product@ Webサービスを呼び出すと、`p_auth`パラメータが自動的に提供されます。 @product@が呼び出し元の認証トークンをユーザーに関連付けることができない場合、Webサービス呼び出し要求は失敗します。

**ユーザーアクセス許可レイヤー：**適切に実装されたWebサービスには、アクセス許可チェックがあります。 Webサービスを呼び出すユーザーには、サービスを呼び出す権限が必要です。

<!-- I would prefer to not include this image until we get design to do a pass on it because I find the visual to be more distracting than helpful in its current iteration. ![Figure 1: To get to a service, a request must pass through the door lock of user permissions, the padlock of the verification layer, the brick wall of service access policies, and finally the safe of predefined IP permissions.](./images/service-access-policies-security-layers.png)  -->

## 承認

Liferay DXPには、いくつかの調整可能な承認レイヤーが含まれています。

  - Liferay PortalのJavaサーブレットへのアクセスを制限するためのリモートIPおよびHTTPSトランスポートチェック
  - ポータルサービス関連の承認チェックを実行するための拡張可能なアクセスコントロールポリシーレイヤー
  - Liferayアセット用の拡張可能な[ロールベースの権限フレームワーク](../../../users-and-permissions/roles-and-permissions/README.md)（データベースまたは他の場所に保存されています）
  - ポートレットアクセスを制御するポートレットコンテナのセキュリティチェック
  - リモートAPI認証方法のリモートIPチェック
  - リモートAPIへのアクセスを制御する[サービスアクセスポリシー](./setting-service-access-policies.md)
  - 提供された資格情報を検証する[Authentication Verifier](./using-authentication-verifiers.md)。
  - [クロスオリジンリソース共有](./setting-up-cors.md)設定では、信頼できるソースからのみリソースを取得できます。
