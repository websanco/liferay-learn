# 仮想インスタンスを理解する

Liferay DXPは、1つのインストールで複数の「ポータル」をホストできます。 これらは仮想インスタンスと呼ばれます。 各仮想インスタンスには、完全に個別のデータと設定があります。 それぞれが一意のドメイン名を介してアクセスされ、あらゆる目的のために、追加のインスタンスとインストールを共有する個別のLiferayベースの実装です。

Liferay DXPシステムの設定は異なる[スコープ](../understanding-configuration-scope.md)で行われます。 仮想インスタンススコープでの設定は、システムスコープに次ぐ最も広いスコープです。

```{important}
The system scope is the highest level configuration scope. All virtual instances are impacted by configuration done at this scope. The *instance* scope applies only to one particular virtual instance.

If your installation contains only one virtual instance, there's no practical difference between a system scoped configuration and a virtual instance scoped configuration. Both types of configuration apply throughout your system.
```

Liferay DXPのインストールとデータベースをホストするサーバーの用意はおそらく済んでいると思います。 そのサーバーには、多数の[ユーザー](../../../users-and-permissions/users/understanding-users.md)、[サイト](../../../site_building.rst)および特定の[インスタンス設定](instance-configuration.md)が含まれています。 2つ目の同様のインストールが必要な場合は、*仮想インスタンス*を追加することをお勧めします。

一意のドメイン名のため、訪問者は正しい仮想インスタンスに誘導されます。 仮想インスタンスはアプリケーションサーバーとOSGiコンテナを共有するため、次のカスタマイゼーションも共有します。

  - デプロイしたすべてのカスタムコード
  - [システムスコープの設定](../system-settings.md)（たとえば、`.config`ファイルや、*[コントロールパネル]* → *[設定]* → *[システム設定]* で行われた変更）。
  - アプリケーションサーバーの設定。

仮想インスタンスの追加と管理は、*[コントロールパネル]* → *[設定]* → *[仮想インスタンス]* で行うことができます。

![コントロールパネルの[仮想インスタンス]セクションで、Liferayの仮想インスタンスを追加および管理します。](./understanding-virtual-instances/images/01.png)

インスタンスは、*[コントロールパネル]* → *[設定]* → *[Instance Settings]* で設定できます。 インスタンス設定は、次の3つのセクションで構成されています。

  - プラットフォーム
  - セキュリティ
  - コンテンツとデータ

このセクションでは、*プラットフォーム*設定に焦点を当てています。

![インスタンス設定には、いくつかのプラットフォームカテゴリがあります。](./understanding-virtual-instances/images/02.png)

## 関連トピック

  - [セキュリティ](../../../installation-and-upgrades/securing-liferay.md)
  - [検索機能](../../../using-search.md)
  - [システム設定](../system-settings.md)
