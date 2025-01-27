# 表示されたコンテンツの自動公開の設定

> 対応可能：Liferay DXP/Portal 7.4以降

デフォルトでは、すべての公開プロセスは、動的データリスト(DDL)表示、フォーム、Webコンテンツの表示、およびWiki表示のウィジェットを使用して表示されたコンテンツを自動的に再公開します。 これは、公開プロセスの構成に関係なく発生し、本番環境に表示されるコンテンツを最新の状態に保ちます。

ただし、表示されたコンテンツを常に本番環境に再公開する必要はなく、公開が遅くなる可能性があります。 必要に応じて、システム設定でこのデフォルトの動作を無効にして、公開速度を向上させることができます。 無効にすると、公開プロセスの構成に含まれているコンテンツのみが公開されます。

このステージング動作を構成するには、次の手順に従います。

1. **グローバルメニュー**（![Global Menu](../../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ をクリックして、 ［**システム設定**］ &rarr; ［**Infrastructure**］ に移動します。

1. 左側のメニューの［仮想インスタンススコープ］の下にある ［**ステージング**］ をクリックします。

1. ［**Publish Displayed Content by Default**］ をオンまたはオフにします。

   * 有効にすると、公開プロセスの構成に関係なく、表示されたコンテンツが動的データリスト(DDL)表示、フォーム、Webコンテンツの表示、およびWiki表示のウィジェットに対して再公開されます。

   * 無効にすると、公開プロセスの構成に含まれているコンテンツのみが公開されます。

1. ［**Save**］ をクリックします。

![［Publish Displayed Content by Default］をオンまたはオフにします。](./configuring-automatic-publishing-of-displayed-content/images/01.png)

<a name="additional-information" />

## 追加情報

* [ステージング](../staging.md)
* [公開プロセスを理解する](./understanding-the-publishing-process.md)
* [サイトステージングUIリファレンス](./site-staging-ui-reference.md)
