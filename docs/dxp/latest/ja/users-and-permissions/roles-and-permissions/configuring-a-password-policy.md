# パスワードポリシーの設定

パスワードポリシーは、インストールのセキュリティを強化します。 Liferayに同梱されているデフォルトのポリシー（変更するか現状のまま）を使用することも、独自のポリシーを作成することもできます。 ポリシーは、ユーザーまたは組織に割り当てることができます。

```{note}
   新規ユーザーには、デフォルトでデフォルトパスワードポリシーが割り当てられます。 ユーザーをカスタムパスワードポリシーに関連付ける場合は、そのポリシーのメンバーとして割り当てられる必要があります。
```

<a name="overview" />

## 概要

1. [デフォルトのパスワードポリシーの変更](#modifying-the-default-password-policy)
1. [カスタムパスワードポリシーの作成](#creating-a-custom-password-policy)
1. [パスワードポリシーへのメンバーの割り当て](#assigning-members-to-a-password-policy)
1. [プロパティファイルの活用](#using-the-properties-file)
1. [パスワードのプロパティリファレンス](#password-properties-reference)

<a name="modifying-the-default-password-policy" />

## デフォルトのパスワードポリシーの変更

デフォルトのポリシーを変更するには

1. ［**コントロールパネル**］ &rarr; ［**セキュリティ**］ &rarr; ［**パスワードポリシー**］ へ行きます。

1. デフォルトのパスワードポリシー］の横にあるアクションボタン（![Actions](../../images/icon-actions.png)）をクリックし、 ［**編集**］ をクリックします。

   ![ポリシーを変更するには、［編集］をクリックします。](./configuring-a-password-policy/images/01.png)

1. 設定画面で必要な変更を行います。

   ![設定画面で必要な変更を行います。](configuring-a-password-policy/images/02.png)

1. 完了したら、 ［**保存**］ をクリックします。

### ポリシー設定リファレンス

**Password Changes：** パスワードの変更を許可または禁止し、パスワードリセットリンクの有効期間を設定します。

**パスワードの構文確認：** パスワードを選択するときに特定の構文が必要です。 このセクションでは、辞書に載っている単語を禁止したり、最小の長さを設定したりすることができます。

**パスワードの履歴。** 履歴に残すパスワードの数を決めて、古いパスワードの再利用を防ぎます。

**パスワードの有効期限：** パスワードの有効期限を設定する場合、パスワードの有効期間、警告を送信するタイミングと送信するかどうか、および **許可する回数**（パスワードの有効期限が切れた後、パスワードの変更を強制する前に許可されるログイン数）を指定します。

**ロックアウト：** アカウントがロックされるまでの認証試行の最大失敗回数、試行回数の保存期間、およびロックアウトの継続期間を設定します。

<a name="creating-a-custom-password-policy" />

## カスタムパスワードポリシーの作成

カスタムのパスワードポリシーは、いくつかのシナリオで役に立つかもしれません。 例えば、特定のユーザーやユーザーグループに対して、より厳しいパスワードルールを設定することができます。 カスタムポリシーを作成するには

1. ［**コントロールパネル**］ &rarr; ［**セキュリティ**］ &rarr; ［**パスワードポリシー**］ へ行きます。

1. ［**追加**］ アイコン (![Add](../../images/icon-add.png)) をクリックして、新しいポリシーを追加します。

1. 設定ウィンドウで、パスワードポリシーの名前と説明を入力します。

1. ポリシーのプロパティを設定します（上記参照）。 完了したら、 ［**保存**］ をクリックします。

<a name="assigning-members-to-a-password-policy" />

## パスワードポリシーへのメンバーの割り当て

1. パスワードポリシーの横にあるアクションボタン（![Actions button](../../images/icon-actions.png)）をクリックします。 ［**メンバーの割り当て**］ をクリックします。

   ![［メンバーの割り当て］リンクをクリックします。](configuring-a-password-policy/images/03.png)

1. 個々のユーザーまたは組織をパスワードポリシーに追加できます。 ［**追加**］ アイコン (![Add icon](../../images/icon-add.png)) をクリックすると、新しいフォームがポップアップします。

1. 選択を行います（ユーザーまたは組織）。 完了したら、 ［**追加**］ をクリックします。

   ![選択して、［追加］ボタンをクリックします。](configuring-a-password-policy/images/04.png)

   これで、ユーザーまたは組織がパスワードポリシーに関連付けられました。

<a name="using-the-properties-file" />

## プロパティファイルの使用

デフォルトのパスワードポリシーは、UIの設定からのみ変更できます。

新しいパスワードポリシーは、UIで作成するか、[`portal-ext.properties`](../../installation-and-upgrades/reference/portal-properties.md) ファイルファイルで追加できます。 カスタマイズしたいプロパティや値をファイルに追加します。 カスタムパスワードポリシーの名前を必ず追加してください。

例えば、最小の長さ、数字、記号を必要とするカスタムパスワードポリシーを設定したい場合、これらの値を設定することができます。

```properties
#
# Properties for a Custom Password Policy
#

...
passwords.default.policy.name=Custom Password Policy
passwords.default.policy.check.syntax=true
passwords.default.policy.min.length=10
passwords.default.policy.min.numbers=1
passwords.default.policy.min.symbols=1
...
```

パスワードポリシーをカスタマイズするために使用できるプロパティの全リストは、以下のリファレンスを参照してください。

`portal-ext.properties` ファイルを作成したら、 [`[Liferay Home]`](../../installation-and-upgrades/reference/liferay-home.md) フォルダに配置します。 [ポータルのプロパティの詳細](../../installation-and-upgrades/reference/portal-properties.md) または、Dockerを使用している場合は、 [Dockerコンテナの設定](../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/configuring-containers.md)を参照してください。

<a name="password-properties-reference" />

## パスワードのプロパティリファレンス

| プロパティ                                             | デフォルト値                   | 説明                                                              |
| ------------------------------------------------- | ------------------------ | --------------------------------------------------------------- |
| `passwords.default.policy.allow.dictionary.words` | true                     | 一般的な辞書の単語をユーザーのパスワードとして許可する                                     |
| `passwords.default.policy.changeable`             | true                     | ユーザーが自分のパスワードを変更できる                                             |
| `passwords.default.policy.change.required`        | true                     | ユーザーが最初にログインする際に、パスワードの変更を求められる                                 |
| `passwords.default.policy.check.syntax`           | false                    | パスワードは、特定の単語、特定の長さ、特定の文字などをチェックします。                             |
| `passwords.default.policy.expireable`             | false                    | 設定した時間後にパスワードを失効させることができる                                       |
| `passwords.default.policy.grace.limit`            | 0                        | パスワードの有効期限が切れた後、ユーザーが新しいパスワードの入力を要求されるまでにログインできる回数              |
| `passwords.default.policy.history`                | false                    | ユーザーのパスワードの履歴を残し、以前のパスワードの再利用を防ぐことができる                          |
| `passwords.default.policy.history.count`          | 6                        | 履歴に残す過去のパスワードの数を決定する                                            |
| `passwords.default.policy.lockout`                | false                    | ユーザーが一定回数ログインを試みると、アカウントがロックされます。                               |
| `passwords.default.policy.lockout.duration`       | 0                        | ユーザーのアカウントがロックされる時間です。 管理者のみがパスワードを解除できる場合は0。 時間の単位は秒           |
| `passwords.default.policy.max.age`                | 8640000                  | パスワードの変更が必要となるまでの有効期間を決定します。 時間の単位は秒                            |
| `passwords.default.policy.max.failure`            | 3                        | ユーザーが間違ったパスワードでログインしようとする最大回数                                   |
| `passwords.default.policy.min.age`                | 0                        | ユーザーが再びパスワードを変更するまでの時間を決定します。 時間の単位は秒                           |
| `passwords.default.policy.min.alphanumeric`       | 0                        | ユーザーのパスワードに必要な英数字の最小文字数                                         |
| `passwords.default.policy.min.length`             | 6                        | ユーザーのパスワードに要求される最小の長さ                                           |
| `passwords.default.policy.min.lowercase`          | 0                        | ユーザーのパスワードに必要な小文字の最小数                                           |
| `passwords.default.policy.min.numbers`            | 1                        | ユーザーのパスワードに必要な最低限の数字の数                                          |
| `passwords.default.policy.min.symbols`            | 0                        | ユーザーのパスワードに必要な最低限の記号の数                                          |
| `passwords.default.policy.min.uppercase`          | 1                        | ユーザーのパスワードに必要な大文字の最小数                                           |
| `passwords.default.policy.name`                   | デフォルトのパスワードポリシー          | パスワードポリシーの名称                                                    |
| `passwords.default.policy.regex`                  | (?=.{4})(?:[a-zA-Z0-9]*) | RegExpToolkitを使用してパスワードを生成する場合は、パスワードの生成と検証に使用する正規表現パターンを設定します。 |
| `passwords.default.policy.reset.failure.count`    | 600                      | 失敗したログインの記録がユーザーのために保存される時間です。 時間の単位は秒                          |
| `passwords.default.policy.reset.ticket.max.age`   | 86400                    | パスワードリセットリンクの有効期間。 時間の単位は秒                                      |
| `passwords.default.policy.warning.time`           | 86400                    | パスワードの有効期限が切れる前に、ユーザーに通知する時間を指定します。 時間の単位は秒                     |

<a name="additional-information" />

## 追加情報

* [認証の基本](../../installation-and-upgrades/securing-liferay/authentication-basics.md)
