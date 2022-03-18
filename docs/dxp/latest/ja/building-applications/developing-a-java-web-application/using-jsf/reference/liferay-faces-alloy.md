# Liferay Faces Alloy

Liferay Faces Alloyは、`.jar`ファイルで配布されます。 Liferay Faces Alloyをポートレットプロジェクトへの依存関係として追加して、JSF開発と一貫した方法でAlloyUIを使用できます。

```{note}
AlloyUIはLiferay DXP 7.2で廃止予定になりました。
```

Liferay IDE/Developer StudioでのJSFポートレットの作成中に、ポートレットのJSF Component Suiteを選択するオプションがあります。 オプションには、*JSF標準*、[*ICEfaces*](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf)、[*PrimeFaces*](http://primefaces.org/)、[*RichFaces*](http://richfaces.jboss.org/)、および*Liferay Faces Alloy*があります。

ポートレットのセットアップ中にLiferay Faces Alloy JSF Component Suiteを選択した場合、`.jar`ファイルはポートレットプロジェクトに含まれています。

Liferay Faces Alloyプロジェクトは、AlloyUIを利用する一連のUIコンポーネントを提供します。 たとえば、サポートされている`aui:`タグのいくつかの簡単なリストを以下に示します。

* 入力：`alloy:inputText`、`alloy:inputDate`、`alloy:inputFile`
* パネル：`alloy:accordion`、`alloy:column`、`alloy:fieldset`、`alloy:row`
* 選択：`alloy:selectOneMenu`、`alloy:selectOneRadio`、`alloy:selectStarRating`

YUI3に基づくLiferayのAlloyUIテクノロジーを利用する場合は、Liferay Faces Alloyの`.jar`ファイルをJSFポートレットプロジェクトに含める必要があります。 JSFポートレットのセットアップ中に*Liferay Faces Alloy*を選択した場合、プロジェクトでLiferay Faces Alloyが事前構成されているため、`alloy:`タグを自動的に使用できます。

ご覧のとおり、LiferayのAlloyUIタグを使用するようにJSFアプリケーションを構成するのは非常に簡単です。

## 追加情報

* [Developing a JSF Portlet Application \(Help Center\)](https://help.liferay.com/hc/en-us/articles/360029069451-Developing-a-JSF-Portlet-Application)
* [Liferay Faces Bridge](./liferay-faces-bridge.md)
* [Liferay Faces Portal](./liferay-faces-portal.md)