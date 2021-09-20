# 基礎

```{toctree}
:maxdepth: 3

fundamentals/module-projects.md
fundamentals/apis-as-osgi-services.md
fundamentals/using-an-osgi-service.md
fundamentals/importing-packages.md
fundamentals/exporting-packages.md
fundamentals/semantic-versioning.md
fundamentals/configuring-dependencies.md
fundamentals/using-the-gogo-shell.md
```

Liferay開発プロジェクトは、主に単純な.jarファイルで構成されています。 これらには、OSGiモジュールにするいくつかの追加の構成ファイルが含まれていますが、Javaを知っている人なら誰でも簡単に理解できます。

OSGiの強力な機能の1つは、DXPのAPIにアクセスするプロジェクトを構築し、さまざまな機能を拡張およびオーバーライドしたり、新しいソフトウェアを展開用にパッケージ化したりするための単一の一貫した方法があることです。 これらのプロジェクトまたはモジュールは、機能性を相互に依存し、実行時に操作できるエコシステムを形成します。

これらは、Liferay DXPのエコシステムの基本です。

  - **[モジュールプロジェクト](./module-projects.md)** では、OSGiモジュールとは何か、そのプロジェクト構造、およびLiferayのOSGiフレームワークでの使用方法について説明します。

  - **[依存関係の構成](./configuring-dependencies.md)** は、プロジェクトでJavaパッケージを使用するためにLiferayアーティファクトとサードパーティのアーティファクトを識別および構成する方法を示しています。

  - **[インポート](./importing-packages.md) および [パッケージのエクスポート](./exporting-packages.md)** は、他のモジュールの機能を使用し、他のモジュールが使用できるように機能を公開する方法を示しています。 Liferayのツールは、パッケージの使用を検出し、パッケージのインポートを自動的に指定します。

  - **[セマンティックバージョニング](./semantic-versioning.md)** は、Liferayが新しいバージョンに進化するときにコードへの依存関係を管理する方法を説明します。これにより、同じメカニズムを使用してバージョンを管理し、コードの破損を回避できます。

  - **[Gogo Shell](./using-the-gogo-shell.md)** は、シェルにコマンドを発行することにより、コンポーネントの調査、問題のデバッグ、および実行時のデプロイメントの管理方法を示しています。

最初に、 [モジュールプロジェクトについて学習します](./module-projects.md)。