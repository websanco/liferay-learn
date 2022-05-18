# モデルリスナーの作成

モデルリスナーは、指定されたモデルへの変更を通知する永続メソッドの呼び出しをリッスンします（ `update` または `add` メソッドなど）。 モデルリスナーが使用するメソッドのほとんどは、DXPの [`BasePersistenceImpl`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/service/persistence/impl/BasePersistenceImpl.java) クラスから呼び出されます。 すぐに使えるエンティティ（ `JournalArticle` または `AssetEntry`）、または独自のエンティティのモデルリスナーを定義できます。

モデルリスナーを追加するには、 [`ModelListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/model/ModelListener.java) インターフェイスを実装します。

ここでは、モデルリスナーの作成方法を学習します。

1. [実行中のモデルリスナーを調べる](#examine-a-running-model-listener)
1. [モデルクラスとイベントを特定する](#identify-a-model-class-and-event)
1. [モデルを宣言する](#declare-the-model)
1. [イベントを宣言する](#declare-the-event)
1. [ビジネスロジックを実装する](#implement-your-business-logic)
1. [展開とテスト](#deploy-and-test)

<a name="実行中のモデルリスナーを調べる" />

## 実行中のモデルリスナーを調べる

```{include} /_snippets/run-liferay-portal.md
```

Liferay DXPのインスタンスに `JournalArticle` モデルのサンプルモデルリスナーをデプロイします。

1. Acme Model Listenerを`ダウンロードして解凍します `。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/liferay-internals/extending-liferay/liferay-n4g6.zip -O
    ```

    ```bash
    unzip liferay-n4g6.zip
    ```

1. サンプルをビルドしてデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
      このコマンドは、デプロイされたjarをDockerコンテナの/opt/liferay/osgi/modulesにコピーするのと同じです。
    ```

1. Dockerコンテナコンソールでデプロイを確認します。

    ```
    STARTED com.acme.n4g6.impl_1.0.0
    ```

1. 追加されたログメッセージを表示して、サンプルモデルリスナーが追加されたことを確認します。 ブラウザーで `http://localhost/8080` を開き、［サイト］メニュー→ **コンテンツ & データ** → **Webコンテンツ** へ行きます。

   ![Webコンテンツ管理ページは、Webコンテンツを作成するためのインターフェースです。](./creating-a-model-listener/images/01.png)

   追加ボタンをクリックして、 ![追加](../../images/icon-add.png)［**基本Webコンテンツ**］ をクリックして新しい記事を追加します。 タイトルとコンテンツを入力してください。次に、［**Publish**］をクリックします。 コンソールに警告メッセージが表示されます。

   ```
   2020-03-17 23:14:56.301 WARN  [http-nio-8080-exec-5][N4G6ModelListener:23] Added journal article 20478.
   ```

　 `ModelListener`を実装する新しいモデルリスナーが正常に構築され、デプロイされました。

ご覧のとおり、モデルリスナーは特定の **モデル** **イベント** をリッスンします。 このリスナーの場合、イベントは `onAfterCreate`です。 コンテンツが作成されると、リスナーがイベントを「聞き取り」、イベントが発生するとアクションが発生します。

次に、別のイベントをリッスンするようにサンプルを変更します。

<a name="モデルクラスとイベントを特定する" />

## モデルクラスとイベントを特定する

Liferay DXPのモデルクラスは、 [Service Builder](../../developing-applications/data-frameworks/service-builder.md)によって生成されます 。 モデルインターフェイスは、任意のアプリケーションの `-api` モジュールにあります。 例えば、掲示板メッセージのモデルインターフェイスを見つけるには、Liferay DXPのソースコードで [`modules/apps/message-boards/message-boards-api`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/message-boards/message-boards-api) プロジェクトを探してください。

このルールの例外はコアモデルです。 `User`などのコアクラスのモデルリスナーを作成する場合、そのインターフェイスはLiferay DXPのソースコードの [`portal-kernel`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel) フォルダにあります。

作成するモデルリスナーは、メッセージボードメッセージ用です。 メッセージが削除されると、レポートをログに出力するメッセージをトリガーします。 可能なイベントのリストについては、 [のJavadocを参照してくださいBaseModelListener](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/model/BaseModelListener.html) 。

<a name="モデルリスナーの動作" />

## モデルリスナーの動作

モデルリスナーは、特定のエンティティに対して [`ModelListener`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/model/ModelListener.java) インターフェイスを実装します。 モデルリスナーには、エンティティが作成、更新、または削除される前または後に実行するコードを含めることができます。 これらのメソッドはすべて、 `BasePersistenceImpl` クラスから呼び出されます。作成または更新されたエンティティのコードは `BasePersistenceImpl`の `update` メソッドから呼び出され、削除されたエンティティのコードは `BasePersistenceImpl``remove` メソッドから呼び出されます。

モデルリスナーには、他の種類の関連エンティティが追加または削除される前または後に実行するコードを含めることもできます。 これらのメソッドは、 [`TableMapperImpl`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/internal/service/persistence/TableMapperImpl.java) の`_addTableMapping`メソッドから呼び出されます。

次に、プロジェクトを変更して、プロジェクトが `MBMessage` クラスと `onBeforeRemove` イベントで動作するようにします。

<a name="モデルを宣言する" />

## モデルを宣言する

1. テキストエディターまたはIDEで `N4G6ModelListener` クラスを開きます。

1. クラス宣言を見つけます。
    ```java
    @Component(service = ModelListener.class)
    public class N4G6ModelListener extends BaseModelListener<JournalArticle> {
    ```

    `BaseModelListener`拡張する場合、リスナーがイベントをリッスンするモデルクラスを定義します（この例では `JournalArticle`）。

1. モデルクラスを `MBMessage`変更します。

   ```java
   @Component(service = ModelListener.class)
   public class N4G6ModelListener extends BaseModelListener<MBMessage> {
   ```

   このモデルリスナーは登録されると、定義されたモデルのイベントをリッスンします。 モデルは、標準のエンティティまたはカスタムエンティティにすることができます。 `BaseModelListener` クラスを拡張すると、 `ModelListener`のメソッドごとにデフォルトの空の実装が提供されるため、コードはクリーンなままで、必要なイベントのみのオーバーライドが含まれます。

<a name="イベントを宣言する" />

## イベントを宣言する

次に、必要なイベントの実装をオーバーライドします。

1. `onAfterCreate` メソッドを見つけます。

   ```java
   public void onAfterCreate(JournalArticle journalArticle)
   ```

1. メソッドを変更して、`onBeforeRemove`をオーバーライドし、`MBMessage`を`model`と呼ばれるパラメーターとして渡します。

   ```java
   public void onBeforeRemove(MBMessage model)
   ```

<a name="ビジネスロジックを実装する" />

## ビジネスロジックを実装する

特定のアクションをトリガーすることは、特定のモデルイベントをリッスンする典型的な理由です。 この例では、物事をシンプルに保ちます。メッセージボードのメッセージが削除された場合、メッセージの件名をログに報告します。

1. 新しい `onBeforeRemove` メソッドで、 `if` ステートメントを次のステートメントに置き換えます。

   ```java
   if (_log.isWarnEnabled()) {
       _log.warn("Warning! Message " + model.getSubject() + " was just removed.");
   }
   ```

1. `MBMessage` の新しいインポートをファイルの上部にあるインポートセクションに追加します。

   ```java
   import com.liferay.message.boards.model.MBMessage;
   ```

   `JournalArticle`未使用のインポートを削除します。

1. 新しいモデルリスナーを保存します。

<a name="デプロイとテスト" />

## デプロイとテスト

上記と同じように、モデルリスナーをビルドしてデプロイできます。

```bash
./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
```

メッセージボードメッセージを追加してから削除して、リスナーをテストします。

1. ［**プロダクトメニュー**］ &rarr; ［**Content & Data**］ &rarr; ［**掲示板**］ に移動します。

1. ［追加 ![Add](../../images/icon-add.png) ］ボタンをクリックし、件名と本文を入力して、［**公開**］をクリックします。

1. メニューから［**Message Boards**］をもう一度クリックして、メッセージを表示します。 ［Action ![Action](../../images/icon-actions.png) ］ボタンをクリックし、［**Move to Recycle Bin**］を選択します。 メッセージはリサイクルされただけなので、まだログにメッセージが表示されていないことに注意してください。

1. プロダクトメニューから［**ごみ箱**］をクリックすると、メッセージが表示されます。

1. ［Action ![Action](../../images/icon-actions.png) ］ボタンをクリックし、［**Delete**］を選択します。 削除を確認します。

1. ログを確認してください。 メッセージが表示されます：

   ```
   2020-04-17 21:10:31.080 WARN  [http-nio-8080-exec-5][N4G6ModelListener:19] Warning! Message This is a Test Message was just removed.
   ```

<a name="まとめ" />

## まとめ

　 `ModelListener` インターフェースを実装する方法を理解し、Liferay DXPに新しいモデルリスナーを追加しました。

<a name="関連トピック" />

## 関連トピック

[ModelListener Javadoc](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/model/BaseModelListener.html)

[Service Builder](../../developing-applications/data-frameworks/service-builder.md)
