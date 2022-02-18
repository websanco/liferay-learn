# サードパーティライブラリパッケージの依存関係の解決

アプリケーションは、複数のOSGiモジュールに依存できます。 Javaパッケージの依存関係を解決するのは難しい場合があります。 すべてのパッケージがOSGi JARで配布されるのが理想的ですが、多くのパッケージは従来のライブラリ（非OSGi JAR）にのみ存在しています。 サードパーティの非OSGi JARへの依存関係は、いくつかの方法で解決できます。

1. [Eclipse Orbit](https://www.eclipse.org/orbit/) や [ServiceMixバンドル](https://servicemix.apache.org/developers/source/bundles-source.html) などのプロジェクトは、何百もの従来のJavaライブラリをOSGiモジュールに変換します。 パッケージを含むOSGi JARが見つかる場合があります。

    * [Eclipse Orbitのダウンロード\（ビルドを選択\）](https://download.eclipse.org/tools/orbit/downloads/)
    * [ServiceMix バンドル](https://mvnrepository.com/artifact/org.apache.servicemix.bundles)

    パッケージを含むモジュールを見つけたら、それを[デプロイ](../../../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md)し、[それに`compileOnly`依存関係を追加](./specifying-dependencies.md)します。 パッケージのモジュールがない場合は、次の手順に進みます。

1. 使用しているライブラリパッケージをLiferayがすでにエクスポートしているかどうかを確認します。 Liferayがそれらをエクスポートしている場合は、[Exported Third Party Packages](../../reference/exported-third-party-packages.md)の指示に従って依存関係を調整してください。

1. 非OSGi JARを`compileInclude`依存関係として追加します。

    ```groovy
    dependencies {
        compileInclude group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0'
    }
    ```

    Liferayの`compileInclude`構成は推移的です---ライブラリとそのすべての依存関係をモジュールJARの`lib`フォルダに埋め込み、JARをモジュールの`Bundle-ClassPath`マニフェストヘッダに追加します。

    ```{note}
    `compileInclude`構成は推移的な [オプションの依存関係](https://maven.apache.org/guides/introduction/introduction-to-optional-and-excludes-dependencies.html) をダウンロードしません。 オプションの依存関係からのパッケージが必要な場合は、別のサードパーティライブラリパッケージと同じようにパッケージを解決してください。
    ```

1. モジュールをコンパイルします。

1. モジュールをデプロイし、未解決のパッケージの依存関係を確認します。

1. モジュールが使用していないパッケージに未解決の依存関係がある場合は、そのパッケージのインポートをブロックします。

    ```
    Import-Package:\
        !foo.bar.baz,\
        *
    ```

    `!`文字は、パッケージのインポートを無効にします。 `*`文字は、モジュールが明示的に参照するすべてのパッケージを表します。 `*`をリストの最後に置くと、Bndはモジュールが参照するすべてのパッケージをインポートします。

```{note}
WARファイルに[Liferayがすでにエクスポートしている](../../reference/exported-third-party-packages.md)サードパーティパッケージとは異なるバージョンが必要な場合は、[`Import-Package:` リスト](../importing-packages.md)でそのパッケージを指定してください。 そのパッケージがOSGiモジュールにある場合は、それをデプロイします。

Liferay DXPはWARをデプロイするときに、それを [WAB](https://docs.osgi.org/specification/osgi.cmpn/7.0.0/service.war.html) に変換し、デプロイ時にWABからすでにエクスポートされたサードパーティのJARを取り除きます。 Liferayがエクスポートしているものとは異なるバージョンを強制的にデプロイするには、そのパッケージの非OSGi JARの名前を[WABジェネレータが除外するJAR](../../../developing-applications/reference/jars-excluded-from-wabs.md)とは異なる名前に変更し、プロジェクトに [そのJARを埋め込む](#embedding-a-library) 必要があります。
```

　 非OSGi JARからのパッケージへの依存関係を解決しました。

<a name="追加情報" />

## 追加情報

* [パッケージのインポート](../importing-packages.md)
* [パッケージのエクスポート](../exporting-packages.md)
* [セマンティックバージョニング](../semantic-versioning.md)