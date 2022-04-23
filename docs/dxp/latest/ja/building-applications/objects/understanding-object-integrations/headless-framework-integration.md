# ヘッドレスフレームワークの統合

オブジェクトを公開するときは、ヘッドレスREST APIも一緒に公開します。 Liferayは、作成するオブジェクトごとにREST APIと [GraphQL](https://graphql.org) APIの両方を自動的に生成します。 オブジェクト用に生成されたAPIは、そのスコープ（つまり、会社またはサイト）によって異なります。

すべてのオブジェクトは、`c/[pluralobjectlabel]`命名パターン（`c/timeoffrequests`など）を使用します。 各オブジェクトエントリースキーマには、オブジェクトのすべてのフィールドとリレーションシップが含まれています。

オブジェクトのAPIは、Liferay API Explorerを介して`[server]:[port]/o/api`（例：`localhost:8080/o/api`）で表示およびテストできます。 REST APIは、 ［**REST Applications**］ の下にリストされています。 ［**GraphQL**］ をクリックして、Liferayの [Graph **i** QL](https://github.com/graphql/graphiql) ブラウザにアクセスします。

## オブジェクトREST API

REST APIを使用すると、オブジェクトエントリーに対して単一およびバッチの両方のCRUD操作を実行できます。

![各オブジェクトは［REST Applications］の下に表示されます。](./headless-framework-integration/images/01.png)

すべてのエンドポイントとJavaメソッドは、オブジェクトの名前を使用します。 次の例では、`ObjectName`/`objectName`をオブジェクトの名前に置き換えます。

詳しくは、 [RESTサービスの使用](../../../headless-delivery/consuming-apis/consuming-rest-services.md) を参照してください。

```{important}
Liferay DXP 7.4 GA1では、選択リストフィールドの値は、オブジェクトAPIコールで以下のフォーマットを使用する必要があります。`"samplePicklist":"string"`です。 Liferay DXP 7.4 U1では、フォーマットが変更され、値はこのフォーマットを使用する必要があります。`"samplePicklist":{"key":"string"}`.
```

### 会社に範囲指定されているオブジェクト

次のREST APIは、すべての会社に範囲指定されているオブジェクトで使用できます。

| HTTP メソッド | HTTPエンドポイント                                           | Javaメソッド                        | 説明                                                                  |
| --------- | ----------------------------------------------------- | ------------------------------- | ------------------------------------------------------------------- |
| GET       | `/`                                                   | `getObjectNamePage`             | Liferayインスタンスのオブジェクトエントリーの完全なリストを返します。結果は、ページ分割、絞り込み、検索、およびソートが可能です |
| POST      | `/`                                                   | `postObjectNamePage`            | API呼び出しで提供された詳細を使用して、新しいオブジェクトエントリーを作成します                           |
| DELETE    | `/by-external-reference-code/{externalReferenceCode}` | `deleteByExternalReferenceCode` | 外部参照コードを使用して、指定されたオブジェクトエントリーを削除します                                 |
| GET       | `/by-external-reference-code/{externalReferenceCode}` | `getByExternalReferenceCode`    | 外部参照コードを使用して、指定されたオブジェクトエントリーの詳細を返します                               |
| PUT       | `/by-external-reference-code/{externalReferenceCode}` | `putByExternalReferenceCode`    | 指定されたオブジェクトエントリーの詳細を、外部参照コードを使用してAPI呼び出しで提供されたものに置き換えます             |
| DELETE    | `/バッチ`                                                | `deleteObjectNameBatch`         | 複数のオブジェクトエントリーを削除します                                                |
| POST      | `/バッチ`                                                | `postObjectNameBatch`           | API呼び出しで提供された詳細を使用して複数のオブジェクトエントリーを作成します                            |
| PUT       | `/バッチ`                                                | `putObjectNameBatch`            | API呼び出しで提供された詳細を使用して、複数のオブジェクトエントリーを置き換えます                          |
| DELETE    | `/{objectNameId}`                                     | `deleteObjectName`              | 指定されたオブジェクトエントリーを削除し、操作が成功した場合は204を返します                             |
| GET       | `/{objectNameId}`                                     | `getObjectName`                 | 指定されたオブジェクトエントリーの詳細を返します                                            |
| PATCH     | `/{objectNameId}`                                     | `patchObjectName`               | 指定されたオブジェクトエントリーのAPI呼び出しで指定されたフィールドを更新します。他のフィールドは変更されません           |
| PUT       | `/{objectNameId}`                                     | `putObjectName`                 | 指定されたオブジェクトエントリーの詳細を、API呼び出しで提供されたものに置き換えます                         |

### サイトに範囲指定されたオブジェクト

次のREST APIは、すべてのサイトに範囲指定されているオブジェクトで使用できます。

| HTTP メソッド | HTTPエンドポイント                                                             | Javaメソッド                                     | 説明                                                               |
| --------- | ----------------------------------------------------------------------- | -------------------------------------------- | ---------------------------------------------------------------- |
| DELETE    | `/scopes/{scopeKey}/by-external-reference-code/{externalReferenceCode}` | `deleteScopeScopeKeyByExternalReferenceCode` | スコープキーと外部参照コードを使用して、指定されたオブジェクトエントリーを削除します                       |
| GET       | `/scopes/{scopeKey}/by-external-reference-code/{externalReferenceCode}` | `getScopeScopeKeyByExternalReferenceCode`    | スコープキーと外部参照コードを使用して、指定されたオブジェクトエントリーの詳細を返します                     |
| PUT       | `/scopes/{scopeKey}/by-external-reference-code/{externalReferenceCode}` | `putScopeScopeKeyByExternalReferenceCode`    | 指定されたオブジェクトエントリーの詳細を、そのスコープキーと外部参照コードを使用してAPI呼び出しで提供されたものに置き換えます |
| GET       | `/scopes/{scopesKey}`                                                   | `getScopeScopeKeyPage`                       | サイトのオブジェクトエントリーの完全なリストを返します。結果は、ページ分割、絞り込み、検索、およびソートが可能です        |
| POST      | `/scopes/{scopesKey}`                                                   | `postScopeScopeKey`                          | API呼び出しで提供された詳細を使用して、指定されたサイトに新しいオブジェクトエントリーを作成します               |
| DELETE    | `/{objectNameId}`                                                       | `deleteObjectName`                           | 指定されたオブジェクトエントリーを削除します                                           |
| GET       | `/{objectNameId}`                                                       | `getObjectName`                              | 指定されたオブジェクトエントリーの詳細を返します                                         |
| PATCH     | `/{objectNameId}`                                                       | `patchObjectName`                            | 指定されたオブジェクトエントリーのAPI呼び出しで指定されたフィールドを更新します。他のフィールドは変更されません        |
| PUT       | `/{objectNameId}`                                                       | `putObjectName`                              | 指定されたオブジェクトエントリーの詳細を、API呼び出しで提供されたものに置き換えます                      |

```{note}
APIの `scopesKey`パラメーターには、目的のデータスコープの適切な識別子（サイトID、ユーザーロールなど）を使用します。
```

## オブジェクトGraphQL API

GraphQL APIでは、オブジェクトデータを照会したり、変更したりすることができます。 LiferayのGraph **i** QL IDEを使用して、オブジェクトスキーマの検索、クエリのドラフト、リクエストの実行などを行います。

![LiferayのGraphiQLエクスプローラーを使用して、オブジェクトスキーマを検索し、リクエストを実行します。](./headless-framework-integration/images/02.png)

詳しくは、 [GraphQL APIの使用](../../../headless-delivery/consuming-apis/consuming-graphql-apis.md) を参照してください。

## 追加情報

* [RESTサービスの使用](../../../headless-delivery/consuming-apis/consuming-rest-services.md)
* [GraphQL APIの使用](../../../headless-delivery/consuming-apis/consuming-graphql-apis.md)
