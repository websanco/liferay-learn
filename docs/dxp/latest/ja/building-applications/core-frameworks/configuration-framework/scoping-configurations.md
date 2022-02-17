# スコープ設定

Liferay DXPでは、アプリケーションの構成をさまざまなレベルのスコープ（システム、インスタンス、サイト、またはポートレット）に設定できます。 たとえば、インスタンススコープ構成のアプリケーションを作成する場合、Liferay DXPのインストールでセットアップしたインスタンスごとにアプリケーションを個別に構成できます。 これは、各ユーザーに柔軟性とコントロールを提供するため、便利です。 たとえば、DXPインスタンスの管理者は、他のインスタンスから独立した独自のインスタンスの構成を設定できます。

スコープの詳細については、 [構成スコープについて](../../../system-administration/configuring-liferay/understanding-configuration-scope.md) を参照してください。

インスタンススコープ構成の例を確認するには、 [構成の設定とアクセス](./setting-and-accessing-configurations.md) のポートレットの例を参照してください。

ポートレットスコープの構成は異なるアプローチを取ることに注意してください。 詳細については、 [ポートレットレベルの設定](./portlet-level-configuration.md) を参照してください。

<a name="構成インターフェイスでスコープを指定する" />

## 構成インターフェイスでスコープを指定する

構成のスコープを設定するには、構成インターフェイスでスコープを指定します。 `@ExtendedObjectClassDefinition`アノテーションを使用して、スコープを設定します。 システムスコープの場合は`Scope.SYSTEM`を使用します。 インスタンススコープの場合は`Scope.COMPANY`を使用します。 サイトスコープの場合は`Scope.GROUP`を使用します。

```{literalinclude} ./scoping-configurations/resources/liferay-n2f3.zip/n2f3-web/src/main/java/com/acme/n2f3/web/internal/configuration/N2F3WebConfiguration.java
:language: java
:lines: 7-9
```

<a name="構成プロバイダーを使用する" />

## 構成プロバイダーを使用する

構成を取得するには、`ConfigurationProvider`を使用します。 構成プロバイダーAPIは、構成にアクセスする簡単な方法を提供します。 アプリケーションのニーズに合わせて特定の方法を選択してください。

* `getSystemConfiguration()`：システムスコープの構成を取得します。 アプリケーションのシステムレベルの構成は、 ［**コントロールパネル**］ &rarr; ［**設定**］ &rarr; ［**システム設定**］ にあります。

* `getCompanyConfiguration()`：インスタンススコープの構成を取得します。 `Portal`のインスタンスを使用して`companyId`を取得します。 たとえば、`_portal.getCompanyId(renderRequest)`となります。 アプリケーションのインスタンスレベルの構成は、 ［**コントロールパネル**］ &rarr; ［**設定**］ &rarr; ［**Instance Settings**］ にあります。

* `getGroupConfiguration()`：サイトスコープの構成を取得します。 `Portal`のインスタンスを使用して`groupId`を取得します。 たとえば、`_portal.getScopeGroupId(renderRequest)`となります。 アプリケーションのサイトレベルの構成は、 ［**コントロールパネル**］ &rarr; ［**設定**］ &rarr; ［**サイト設定**］ にあります。 これは Liferay 7.4で利用できることに注意してください。

ポートレットスコープの構成については、 [ポートレットレベルの設定](./portlet-level-configuration.md) を参照してください。
