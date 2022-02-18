# パッケージのインポート

他のモジュールで提供されている機能が必要になることはよくあります。 この機能にアクセスするには、他のモジュールから自分のモジュールのクラスパスにパッケージをインポートする必要があります。 このためには、これらの他のモジュールが、必要な機能を含むパッケージをすでに[エクスポート](./exporting-packages.md)している必要があります。 OSGiフレームワークは、パッケージをインポートするモジュールのクラスパスに接続します。 モジュールJARの`META-INF/MANIFEST.MF`ファイルは、`Import-Package` OSGiヘッダを使用してパッケージをインポートします。

```properties
Import-Package: javax.portlet,com.liferay.portal.kernel.util
```

インポートパッケージは手動で指定する必要がある場合がありますが、必ずしもそうではありません。 便利なことに、[ワークスペース](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)ベースのモジュールプロジェクトは、必要なパッケージを自動的に検出し、それらをモジュールマニフェストのパッケージインポートリストに追加します。 インポートパッケージは手動で指定する必要がある場合があります。

2つの異なるパッケージインポートのシナリオがあります。

* [パッケージの自動インポート](#automatic-package-imports)
* [手動パッケージインポート](#manual-package-imports)

これらのシナリオでパッケージのインポートがどのように指定されるかについては、以下をお読みください。

## パッケージの自動インポート

チュートリアル例（[モジュールプロジェクト](./module-projects.md)を参照）の[ワークスペース](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)ベースのプロジェクト、または[Blade CLI](../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)または[Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md)を使用して作成されたプロジェクトは[Bnd](http://bnd.bndtools.org/)を使用します。 GradleプラグインはBndを呼び出し、BndはGradleの依存関係を読み取り、インポートを解決できます。 プロジェクトのJARをビルドすると、Bndはモジュールが使用するパッケージを検出し、`META-INF/MANIFEST.MF`ファイルを生成し、パッケージを`Import-Package`ヘッダに割り当てます。 その意味で、パッケージのインポートは自動的に行われます。なぜなら、依存関係を定義する必要があるのは、1か所（ビルドスクリプト）だけだからです。

```{note}
Liferayのプロジェクトテンプレートは、[サードパーティのGradleプラグイン](https://github.com/TomDmitriev/gradle-bundle-plugin)を使用してBndを呼び出します。
```

たとえば、[Gogoコマンドサンプル](https://github.com/liferay/liferay-blade-samples/tree/7.3/liferay-workspace/extensions/gogo)の`build.gradle`は、`com.liferay.portal.kernel`および`org.osgi.service.component.annotations`のパッケージを使用します。 サンプルの`build.gradle`ファイルは次のとおりです。

```groovy
dependencies {
    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
    compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations"
}
```

BndがサンプルJARの`META-INF/MANIFEST.MF`ファイルで生成する`Import-Package`ヘッダは次のとおりです。

```properties
Import-Package: com.liferay.portal.kernel.service;version="[4.3,5)"
```

ビルドファイルは依存関係を指定します。 Bndは、モジュールのクラスパスを調べて、モジュールが使用するパッケージをインポートします。 検査には、クラスパスで見つかったすべてのクラスが含まれます（埋め込まれた[サードパーティライブラリJAR](./configuring-dependencies/resolving-third-party-library-package-dependencies.md)のクラスも含まれます）。

```{note}
プラグインWARプロジェクトの場合、Liferayの[WABジェネレータ](../../developing-applications/reference/deploying-wars-wab-generator.md)は、WARのJSP、記述子ファイル、およびクラス（ `WEB-INF/classes`および埋め込みJAR）で使用されるパッケージを検出します。  また、WABジェネレータは、`web.xml`、` liferay-web.xml`、 `portlet.xml`、` liferay-portlet.xml`、および `liferay-hook.xml`記述子ファイルを検索します。 プラグインの `WEB-INF/classes`フォルダにも埋め込みJARにも見つからないクラスのパッケージインポートを追加します。
```

## 手動パッケージインポート

モジュールが次の場所でのみクラスを参照する場合は、パッケージインポートを手動で追加する必要があります。

* 認識されない記述子ファイル
* カスタムまたは認識されない記述子要素または属性
* リフレクションコード
* クラスローダーコード

パッケージを手動でインポートする方法は次のとおりです。

1. モジュールの`bnd.bnd`ファイルを開きます。

1. `Import-Package`ヘッダを追加します。

1. パッケージをヘッダのパッケージリストに追加します。

```properties
Import-Package: [... existing package list,][add the package here]
```

```{note}
プラグインWARプロジェクトにパッケージを手動でインポートするには、上記のような `Import-Package`ヘッダをプロジェクトの`WEB-INF/liferay-plugin-package.properties`ファイルに追加します。
```

### Java APIパッケージ

JavaポートレットなどのJava APIのパッケージは、[セマンティックにバージョン管理](./semantic-versioning.md)されていませんが、[ポータブルJavaコントラクト](https://www.osgi.org/portable-java-contract-definitions/)があります。 各APIのコントラクトは、それが満たすJSRを指定します。 これらのAPIを使用するモジュールは、APIコントラクトの要件を指定する必要があります。 コントラクト要件は、インポートされたAPIパッケージとのモジュールの関係を定義します。 実行しているシステムが正確なコントラクトを提供*しない*場合、モジュールは解決されません。 欠落しているパッケージを解決するほうが、実行中の非互換性の失敗を処理するよりもましです。

[ワークスペース](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)ベースのプロジェクトは、ポータブルJavaコントラクトを自動的に指定します。 たとえば、モジュールがJavaポートレットAPIを使用し、Javaポートレット2.0アーティファクトに対してコンパイルする場合、パッケージのコントラクト要件がモジュールのマニフェストに追加されます。

```{note}
WARプロジェクトはBndを使用せず、 `WEB-INF/liferay-plugin-package.properties`ファイルでコントラクトを指定する必要があります。 たとえば、`JavaPortlet` 2.0に指定されたコントラクトは次のとおりです：`Import-Package: javax.portlet Require-Capability: osgi.contract;filter:=(&(osgi.contract=JavaPortlet)(version=2.0))`
```

　 これで、モジュールで使用するすべての種類のパッケージをインポートできるようになりました。

## 追加情報

* [依存関係の構成](./configuring-dependencies.md)
* [Blade CLI](../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)
* [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md)
* [ワークスペース](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)
* [セマンティックバージョニング](./semantic-versioning.md)
* [Deploying WARs \(WAB Generator\)](../../developing-applications/reference/deploying-wars-wab-generator.md)