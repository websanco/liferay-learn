# ヘッドレスプラットフォームとしてのLiferayの使用

Liferayでは、Webインターフェイスで利用可能なアクションと同じ種類のアクションを実行する一連のAPIを提供しています。 これは、モバイルアプリケーション、カスタムWebアプリケーション、自動化されたプロセスの作成など、機械可読形式でデータを取得する必要がある場合に不可欠です。 すぐに使用できるインターフェイスよりも多くの労力を要しますが、仕事を進める上でさらに強力な機能が備わっています。

## 接続する方法

クライアントがWeb APIを介してLiferay DXPに接続するには、3つの異なるアプローチがあります。

  - OpenAPIの仕様に準拠した[**ヘッドレスREST API**](#headless-rest-apis)
  - GraphQLの仕様に準拠した[**GraphQL API**](#graphql-api)
  - サービスビルダによって生成された、[**プレーンWeb / RESTサービス**](#plain-webrest-services)

### ヘッドレスREST API

LiferayのヘッドレスAPIは、Liferay DXPリソースとの[RESTful](https://www.w3.org/TR/2004/NOTE-ws-arch-20040211/#relwwwrest)インタラクションを可能にします。 これらのAPIは、RESTインターフェイスの標準を定義する[OpenAPI仕様](https://swagger.io/docs/specification/about/)に準拠しているため、より簡単な実装と使用が可能です。

`[server][:port]/o/api`で実行中のLiferay DXPインスタンスの定義にアクセスすると、これらのAPIの詳細情報を確認できます。 最も関連性の高いドキュメントは、`[server][:port]/o/[api-name]/[api-version]/openapi.[yaml or json]`でraw形式でも入手できます。 ドキュメントは[SwaggerHub](https://app.swaggerhub.com/apis/liferayinc/)で入手できますが、実行中のバージョンと一致しない場合があります。

### GraphQL API

[GraphQL](https://graphql.org/) APIは、ヘッドレスREST APIと同様のインタラクションをサポートするクエリ言語ですが、若干柔軟性が高いものです。 Liferay DXPは`[server][:port]/o/graphql`でこのAPIを公開しています。

[GraphQLクライアント](https://graphql.org/graphql-js/graphql-clients/)を介してAPIまたはそのドキュメントを調べることにより、詳細情報を確認できます。 Liferayには組み込みクライアントが含まれており、`[server][:port]/o/api`（右上の*GraphQL*を選択）で実行中のインスタンスで確認できます。

### プレーンWeb / RESTサービス

LiferayのWebサービスは古いフレームワークの一部であり、DXPで引き続きサポートされていますが、複雑なヘッドレス操作には推奨されなくなりました。 これらのAPIは、Liferay DXPの内部動作と密接に関連しているため、新しいヘッドレスオプションが提供するパワーと柔軟性に欠けています。

ただし、Webサービスは、特定のタスクを実行するためのより簡単な方法を提供する場合があります。 使用方法については、[Service Builder Web Services](../building-applications/data-frameworks/service-builder.md)を参照してください。

## 機能一覧

### 任意のクライアントと連携する

LiferayのヘッドレスAPIはWeb経由でデータを提供するため、Web呼び出しを行うことができるすべてのアプリケーションがクライアントとして機能できます。 これらのAPIは、デフォルトでJSONコンテンツで応答しますが、XMLもネイティブでサポートしています。 拡張機能によって、必要に応じて他の方法でコンテンツを提供できます。 詳細は、[APIヘッダーリファレンス](./content-delivery-apis/api-headers-reference.md#accept)を参照してください。

### 安全に接続する

LiferayのWebインターフェイスと同様に、ヘッドレスAPIを介したすべてのインタラクションは、特定のユーザーアカウントを使用して（またはゲストとして）行われます。 LiferayのAPIは、基本認証、OAuthトークン、Cookieの3つの認証方法をサポートしています。 詳しくは、[APIヘッダーリファレンス](./content-delivery-apis/api-headers-reference.md#authorization)を参照してください。 ゲストとしてAPIリクエストを行うことも可能です。詳細は、[認証されていないリクエストを行う](./consuming-apis/making-unauthenticated-requests.md)を参照してください。

### データサイズを管理しやすくする

大量のデータセットを伴う作業では、処理するクライアントに負荷がかかる場合があります。パフォーマンスの問題はユーザーにとってストレスのたまるものであり、人件費も高額になる可能性があります。 LiferayのヘッドレスAPIでは、コレクションを管理可能なチャンクに分割し、必要なデータを正確に取得する方法を提供します。

リクエストの中で`page`と`pageSize`パラメーターを渡すことで、一度にどれだけの情報が欲しいのか、また特定のリクエストでどの情報のサブセットが欲しいのかを API に伝えることができます。  `sort`パラメーターは、ページングされた応答と組み合わせても効果的であり、どの要素を最初に返すかを指定できます。

`search`パラメーターはキーワード検索を実行し、エントリの任意の部分にそのキーワードを含む要素を生成します。 `filter`パラメーターは同様の検索を実行しますが、エントリのどこにコンテンツを含める必要があるかを正確に指定します。

`fields`パラメーターは、応答の各要素に特定のフィールドのみを列挙するように要求し、逆に`restrictFields`パラメーターは、指定されたフィールドが返されないようにします。

`flatten`パラメーターは、階層APIの場合、階層コンテキストからリクエストを実行します。 これにより、たとえば、関連する組織を見つけるために組織図をトラバースする必要がなく、「マーケティング」と呼ばれる組織で検索を実行できます。

これらのパラメーターのいずれかを使用する方法の詳細は、[API Parameters Reference](./consuming-apis/api-headers-reference.md)を参照してください。
