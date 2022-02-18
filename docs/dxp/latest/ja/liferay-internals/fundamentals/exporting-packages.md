# パッケージのエクスポート

OSGiでは、パッケージはデフォルトでプライベートです。 他のモジュールがそれらを[インポート](./importing-packages.md)して使用できるように、パッケージを明示的にエクスポートする必要があります。

パッケージをエクスポートする方法は次のとおりです。

1. `bnd.bnd`ファイルを開きます。

1. `Export-Package:`ヘッダを追加します（まだ追加していない場合）。

1. `Export-Package:`ヘッダの後にパッケージ名をリストします。

    ```groovy
    Export-Package: com.liferay.petra.io,com.liferay.petra.io.unsync
    ```

[Blade CLI](../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)または[Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md)を使用して作成された[ワークスペース](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)ベースのプロジェクトには[Bnd](http://bnd.bndtools.org/)があります。 Bndは、OSGiヘッダを`bnd.bnd`ファイルからモジュールJARの`META-INF/MANIFEST.MF`ファイルにプロパゲートします。

```{important}
同じパッケージを複数のJARにエクスポートしないでください。 異なるモジュールから同じパッケージをエクスポートすると、予測できない副作用を伴う「分割パッケージ」の問題が発生します。
```

```{note}
Bndは、モジュールのエクスポートされたパッケージを*置換可能*にします。 つまり、Bndは、モジュールのエクスポートされたパッケージを、名前は同じだがバージョンが異なる可能性のある、別のモジュールからエクスポートされた互換性のあるパッケージに置き換えます。 Bndは、エクスポートするすべてのパッケージをモジュールに自動的にインポートさせることで、これを可能にします。 このように、モジュールはそれ自体で動作できますが、パッケージの異なる（互換性のある）バージョン、あるいは同じバージョンを提供するモジュールと連携して動作することもできます。 別のモジュールのパッケージのほうが、他のモジュールとうまく「配線」できる可能性があります。 [Peter Kriensのブログ記事](http://blog.osgi.org/2007/04/importance-of-exporting-nd-importing.html)では、置換可能なエクスポートについて説明しています。
```

モジュールのパッケージを共有できるようになりました。

## 追加情報

* [Configuring Dependencies](./configuring-dependencies.md)
* [セマンティックバージョニング](./semantic-versioning.md)
* [Liferay Workspace](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)
* [Blade CLI](../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)
* [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md)