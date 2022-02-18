# 依存関係の構成

```{toctree}
:maxdepth: 3

configuring-dependencies/finding-artifacts.md
configuring-dependencies/specifying-dependencies.md
configuring-dependencies/resolving-third-party-library-package-dependencies.md
../../developing-applications/reference/jars-excluded-from-wabs.md
```

Liferayは、モジュールがJavaパッケージを介して機能を公開および使用できるコンテナを提供します。 モジュールは、依存関係として構成することにより、他のモジュールまたは従来のライブラリのパッケージを活用できます。 ここでは、アーティファクト（モジュールまたはライブラリ）を見つけて、それらへの依存関係を構成する方法を学習します。

* [Finding Artifacts](./configuring-dependencies/finding-artifacts.md)では、Application Manager、Gogo シェル、およびLiferayのリファレンスドキュメントを使用して、Liferayにデプロイされ、リポジトリで利用可能なアーティファクトを検索する方法について説明します。

* [Specifying Dependencies](./configuring-dependencies/specifying-dependencies.md)では、Liferay Workspace内のモジュールプロジェクトでアーティファクトを参照する方法を示しています。 Liferayがすでにアーティファクトからパッケージをエクスポートしているかどうかを判断する方法と、そのようなアーティファクトをコンパイル時の依存関係として構成する方法を学習します。

* [Resolving Third-Party Library Package Dependencies](./configuring-dependencies/resolving-third-party-library-package-dependencies.md)では、従来のライブラリJAR（OSGiモジュールではないJAR）でのみ使用可能なパッケージを使用するためのワークフローについて説明しています。 推移的な依存関係が最小限に抑えられるため、依存関係をより迅速に解決し、不要なJARでプロジェクトが肥大化するのを防ぐことができます。

最初のステップは、必要なアーティファクトを見つけることです。