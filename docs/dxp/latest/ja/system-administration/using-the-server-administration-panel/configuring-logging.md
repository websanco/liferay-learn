# ロギングの構成

［サーバー管理］パネルの［ログレベル］タブでは、Liferay DXPのクラス階層でクラスとパッケージのログレベルを設定し、サーバー管理ログUIを使用して同様に管理するカスタムオブジェクトを追加できます。 クラス階層の最上位近くのログレベル（ `com.liferay`）を変更すると、その階層の下にあるすべてのクラスのログレベルも変更されます。 階層内で不必要に高い変更を行うと、生成されるメッセージが多すぎて役に立たなくなります。

```tip::
   変更によってログメッセージが増える場合は、できるだけ具体的な内容にしてください。
```

各ロギング構成は、カテゴリーと呼ばれます。

<a name="adding-a-class-or-package-to-the-log-levels-user-interface" />

## ログレベルのユーザーインターフェイスへのクラスまたはパッケージの追加

カテゴリを追加するには、

1. ［コントロールパネル］&rarr; ［設定］&rarr;［サーバ管理］に移動し、 ［**ログレベル**］ タブをクリックします。

1. ［追加］ボタン ![Add](../../images/icon-add.png)クリックして、［カテゴリの追加］フォームを開きます。

1. フォームに入力し、［**保存**］をクリックします。

1. ［**保存**］ をクリックします。

**ロガー名** ： `com.liferay.portal.workflow.kaleo.runtime.internal.notification.TemplateNotificationMessageGenerator`などのサーバーにデプロイされたクラス。

**ログレベル** ：表示するログレベルを選択します。オフ、致命的、エラー、警告、情報、デバッグ、トレース、またはすべて。

![独自にデプロイしたクラスをロギングレベルのカテゴリに追加できます。](./configuring-logging/images/01.png)

ロギングレベルの説明については、 [ApacheのLog4j `Level` クラスJavadoc](https://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/Level.html) 参照してください。

<a name="changing-the-log-level-of-an-existing-category" />

## 既存のカテゴリのログレベルの変更

既存のロギング構成のログレベルを変更するには、

1. ［コントロールパネル］ &rarr; ［設定］&rarr; ［サーバ管理］に移動し、 ［**ログレベル**］ タブをクリックします。

1. 構成するカテゴリを参照または検索します。

1. セレクターを使用してレベルを変更します。

1. ［**保存**］ をクリックします。

![考えられる問題をデバッグするために、ログに記録できるクラスのログレベルを変更できます。](./configuring-logging/images/02.png)
