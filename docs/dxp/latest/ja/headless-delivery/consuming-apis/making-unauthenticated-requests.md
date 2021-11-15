# 認証されていないリクエストを行う

デフォルトでは、Liferay DXPはセキュリティのためにAPIアクセスを制限し、有効な応答を取得するために認証を必要とします。 ただし、場合によっては、ゲストアクセス用のAPIを開くことがよい場合があります。

認証情報を渡さずにAPIリクエストを行うと、通常、エラー応答が発生します。

    curl localhost:8080/o/headless-admin-user/v1.0/sites/20122
    
    {
      "message" : "Access denied to com.liferay.headless.admin.user.internal.resource.v1_0.SiteResourceImpl#getSite"
    }

認証情報なしでAPIにアクセスする必要がある場合は、サービス・アクセス・ポリシーを介して無制限のアクセスを許可します。

## サービス・アクセス・ポリシーによるAPI制限の緩和

[サービス・アクセス・ポリシー](../../installation-and-upgrades/securing-liferay/securing-web-services/setting-service-access-policies.md)を使用して、APIアクセスを詳細レベルで定義できます。

1.  ブラウザで `http://localhost:8080`アクセスします。

2.  デフォルトの認証情報を使用してサインインします。

    **ユーザ名：** `test@liferay.com`  
   **パスワード：** `test`

3.  [グローバルメニュー] → [コントロールパネル] → [セキュリティ]→[サービス・アクセス・ポリシー]に移動します。

4.  *追加*（![add](../../images/icon-add.png)）をクリックします。

5.  ポリシーにわかりやすい名前を付けます。 「SITE\_API\_GUEST\_ACCESS」などのようにするとよいでしょう。

6.  *[Enabled]* に切り替えて、ポリシーを有効にします。

7.  *[デフォルト]* というラベルの付いたトグルを切り替えて、認証されていないリクエストと認証されているリクエストにポリシーを適用します。

8.  ポリシーにローカライズされたタイトルをつけます（例：*ゲストにサイトAPIへのアクセスを許可する*）

9.  下部にある*[詳細モードに切り替え]* をクリックします。

10. 上のエラーメッセージからメソッドシグネチャをコピー＆ペーストします：`com.liferay.headless.admin.user.internal.resource.v1_0.SiteResourceImpl#getSite`

11. *[保存]* をクリックします。

![サービス・アクセス・ポリシーは、APIへのアクセスのルールを定義します。](./making-unauthenticated-requests/images/01.png)

これでサービス・アクセス・ポリシーが有効になり、ゲストにサイトAPIへのアクセスが許可されます。

## アクセスの確認

サービス・アクセス・ポリシーの変更が適用されたため、以前は失敗したAPI呼び出しが成功します。

    curl localhost:8080/o/headless-admin-user/v1.0/sites/20122
    
    {
      "availableLanguages" : [ "en-US" ],
      "description" : "",
      "friendlyUrlPath" : "/guest",
      "id" : 20122,
      "key" : "Guest",
      "membershipType" : "open",
      "name" : "Guest",
      "parentSiteId" : 0,
      "sites" : [ ]
    }

このパターンを適用して、ヘッドレスREST APIへのアクセスを開くことができます。 サービス・アクセス・ポリシーの柔軟性を活用して、必要に応じて詳細な設定が可能です。

```{important}
すべてのAPIが単一のエンドポイントに統合されているため、サービス・アクセス・ポリシーでは、ヘッドレスREST APIほど簡単にGraphQL APIを区別できません。 したがって、このようにGraphQLアクセスを開くと機能する場合もありますが、誤って大量のデータへのアクセスを許可する可能性があるため、通常はお勧めしません。
```
