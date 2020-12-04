# npmbundlerrc構造について

liferay-npm-bundlerは、ウィジェットプロジェクトのルートフォルダーに配置された `.npmbundlerrc` ファイルを介して構成されます。 完全な構成を手動で作成するか、（Babelを介して）構成プリセットを拡張できます。

デフォルトプリセットがliferay-npm-bundlerを構成する方法については、 [デフォルトプリセットリファレンス](./how-the-default-preset-configures-the-liferay-npm-bundler.md) を参照してください。 Liferay JS Generatorと一緒にliferay-npm-bundlerを使用してJavaScriptウィジェットを作成する方法については、「 [JavaScriptツール](../liferay-js-generator.md) を使用したJavaScriptウィジェットの作成」を参照してください。

## 構造

`.npmbundlerrc` ファイルには、4つの可能なフェーズ定義があります *コピープロセス* *プリプロセス* *ポストプロセス* *バベル*です。 これらのフェーズの定義について、以下で詳しく説明します。

**コピープロセス：** `コピープラグイン` プロパティで定義されます（依存パッケージでのみ使用可能）。 特定の各パッケージからコピーまたは除外するファイルを指定します。

**プリプロセス：** `プラグイン` プロパティで定義。 Babelフェーズが実行される前に実行するプラグインを指定します。

**Babel：** `.babelrc` 定義で定義。 パッケージの `.js` ファイルを通じてBabelを実行するときに使用する `.babelrc` ファイルを指定します。

``` note::
  このフェーズでは、Babelはパッケージファイルを変換します（たとえば、必要に応じてそれらをAMD形式に変換します）が、それらをトランスパイルしません。 理論的には、適切なプラグインを設定することでそれらをトランスパイルすることもできます。 関係のない両方のプロセスが混在しないように、バンドルを実行する前にトランスパイルすることをお勧めします。
```

**ポストプロセス：** `ポストプラグイン` プロパティで定義。 *プリプロセス* フェーズを使用する代わりに、これはバベルフェーズの完了後に実行するプラグインを指定します。

以下は、 `.npmbundlerrc` 構成の例です。

``` json
{
    "exclude": {
        "*": [
            "test/**/*"
        ],
        "some-package-name": [
            "test/**/*",
            "bin/**/*"
        ],
        "another-package-name@1.0.10": [
            "test/**/*",
            "bin/**/*",
            "lib/extras-1.0.10.js"
        ]
    },
    "include-dependencies": [
        "isobject", "isarray"
    ],
    "output": "build",
    "verbose": false,
    "dump-report": true,
    "config": {
        "imports": {
            "npm-angular5-provider": {
                "@angular/common": "^5.0.0",
            "@angular/core": "^5.0.0"
            }
        }
    },
    "/": {
    "plugins": ["resolve-linked-dependencies"],
    ".babelrc": {
      "presets": ["liferay-standard"]
    },
    "post-plugins": [
            "namespace-packages",
            "inject-imports-dependencies"
        ]
    },
    "*": {
      "copy-plugins": ["exclude-imports"],
      "plugins": ["replace-browser-modules"],
      ".babelrc": {
        "presets": ["liferay-standard"]
      },
      "post-plugins": [
        "namespace-packages",
        "inject-imports-dependencies",
        "inject-peer-dependencies"
      ]
    },
    "packages": {
        "a-package-name": [
        "copy-plugins": ["exclude-imports"],
        "plugins": ["replace-browser-modules"],
        ".babelrc": {
          "presets": ["liferay-standard"]
        },
        "post-plugins": [
          "namespace-packages",
          "inject-imports-dependencies",
          "inject-peer-dependencies"
        ]
        ],
        "other-package-name@1.0.10": [
          "copy-plugins": ["exclude-imports"],
          "plugins": ["replace-browser-modules"],
          ".babelrc": {
            "presets": ["liferay-standard"]
          },
          "post-plugins": [
            "namespace-packages",
            "inject-imports-dependencies",
            "inject-peer-dependencies"
          ]
        ]
    }
}
```

``` note::
  上記のすべての定義形式（「*」、「some-package-name」、「some-package-name @ version」）が必要なわけではありません。 ほとんどの場合、ワイルドカード定義（ `` * ``）で十分です。 ワイルドカード以外の形式（「some-package-name」と「some-package-name @ version」）は、ワイルドカード定義が提供するよりも具体的な構成を必要とするパッケージのまれな例外です。
```

### 標準構成オプション

以下は、 `.npmbundlerrc` ファイルの標準構成オプションです。

`config`：すべてのliferay-npm-bundlerおよびBabelプラグインで使用できるようにするグローバル構成を定義します。 特定のプラグインごとに利用可能なオプションを見つけるには、各プラグインのドキュメントを参照してください。

``` json
{
  "config": {
    "imports": {
      "vuejs-provider": {
        "vue": "^2.0.0"
      }
    }
  }
}
```

`dump-report：` デバッグレポートを生成するかどうかを設定します。 `true`場合、プロジェクトおよびnpmモジュールの処理時に行われるすべてのアクションと決定を説明する `liferay-npm-bundler-report.html` ファイルがプロジェクトディレクトリに生成されます。 これをビルドフラグ `$ liferay-npm-bundler --dump-report` または `$ liferay-npm-bundler -r`として渡すこともできます。 デフォルト値は `false`です。

`no-tracking:` 使用状況分析をサーバーに送信するかどうかを設定します。 CLI引数 `$ liferay-npm-bundler --no-tracking`を使用するか、 `.liferay-npm-bundler-no-tracking` と呼ばれるマーカーファイルを作成して、これをビルドフラグとして渡すこともできます`プロジェクトのルートフォルダーまたはその祖先のいずれか、または環境変数を設定する <code>LIFERAY_NPM_BUNDLER_NO_TRACKING = ''`。 デフォルト値は `false`です。

`出力：` デフォルトでは、バンドラーは標準のGradleリソースフォルダーにパッケージを書き込みます： `build/resources/main/META-INF/resources`。 この値を設定して、デフォルトの出力フォルダーをオーバーライドします。 依存関係npmパッケージは、ビルドフォルダー内の `node_modules` フォルダーに配置されることに注意してください。 `create-jar` が設定されている場合、デフォルトの出力フォルダーは `build`です。

`preset:` 基本構成として使用する `liferay-npm-bundler` プリセットを指定します。 `.npmbundlerrc` ファイルが提供されていない場合、デフォルトの `liferay-npm-bundler-preset-standard` プリセットが使用されることに注意してください。 プリセットで提供されるすべての設定は継承されますが、上書きすることができます。

`verbose:` ツールが実行していることに関する詳細情報をコンソールに出力するかどうかを設定します。 デフォルト値は `false`です。

### パッケージ処理オプション

`"/"`：プロジェクトのパッケージのプラグインの構成。

`"\"`：依存パッケージのプラグインの設定。

*（アスタリスク）*：すべてのnpmパッケージのデフォルトのプラグイン構成を定義します。 対応するキーで識別される4つの値が含まれます。 キー `コピープラグイン`、 `プラグ` 及び `ポストプラグ` アレイ識別 `のLiferay-NPM-バンドラ` コピーに適用するプラグイン、事前および事後処理ステップを。 キー `.babelrc` は、Babelステップで使用する構成を指定するオブジェクトを識別し、標準の `.babelrc` ファイルと同じ構造を持っています。

`exclude：` すべてまたは特定のパッケージからのバンドルから除外するファイルのglob式を定義します。 各リストは、 `*` （任意のパッケージ）、 `{package name}` （任意のバージョンのパッケージ）、または `{package name}@{version}` （特定のバージョンのパッケージ）のいずれかのキーで識別される配列です。 以下は設定例です：

``` json
{
  "exclude": {
    "*": ["__tests__/**/*"],
    "is-object": ["test/**/*"],
    "is-array@1.0.1": ["test/**/*", "Makefile"]
  }
}
```

`無視：` プロジェクトのBabelで指定されたJavaScriptファイルの処理をスキップします。 構成例を次に示します。

``` json
{
  "無視"：["lib/legacy/**/*。js"]
}
```

`含ま依存性：` 定義のパッケージをそれらが下に記載されていない場合でも、バンドルに含める `依存性` のセクション `package.json`。 これらのパッケージは、 `node_modules` フォルダーで使用できる必要があります（つまり、 `package.json`保存せずに手動でインストールするか、 `devDependencies` セクションにリストされています）。

`パッケージ：` パッケージごとに、npmパッケージのプラグイン構成を定義します。

`max-parallel-files：` EMFILEエラーを回避するために並列処理するファイルの最大数を定義します（特にWindowsの場合）。 デフォルト値は `128`です。

`プロセスシリアル：` **注**：v 2.7.0から削除されました。 `max-parallel-files`置き換えられました。

`ルール：` ローダーでプロジェクトソースファイルに適用するルールを定義します。 ルールには、使用するローダーを定義する `use` 配列プロパティが必要です。これは、パッケージ名または `ローダー` と `オプション` プロパティ（該当する場合）を持つオブジェクトを使用して指定でき、以下の1つ以上のプロパティがあります。

  - `テスト`：ルールを適用するかどうかを決定するために `ソース` フォルダー内のファイルをフィルターする正規表現を定義します。 適格な各ファイルのプロジェクト相対パスが正規表現と比較され、一致するファイルはローダーによって処理されます。
  - `exclude`：除外するファイルを指定して、 `test` 式を絞り込みます。
  - `include`：含めるファイルを指定して、 `test` 式を絞り込みます。

構成例を次に示します。

``` json
{
  "rules": [
    {
      "test": "\\.js$",
      "exclude": "node_modules",
      "use": [
        {
          "loader": "babel-loader",
          "options": {
            "presets": ["env", "react"]
          }
        }
      ]
    },
    {
      "test": "\\.css$",
      "use": ["style-loader"]
    },
    {
      "test": "\\.json$",
      "use": ["json-loader"]
    }
  ]
}
```

`source:` これらのプロジェクトフォルダー内のファイルにルールが適用されます。 フォルダを入れ子にすることができる（例えば `/ srcに/メイン/リソース/`）とPOSIXのパスセパレータを使用して書かれなければならない（すなわち、使用する `/` の代わりに、 `\` Win32システム上）。 ルールはプロジェクトのパッケージ依存関係ファイルに自動的に適用されることに注意してください。

構成例を次に示します。

``` json
{
  "sources": ["src", "assets"]
}
```

### OSGiバンドル作成オプション

バージョン2.2.0以降、liferay-npm-bundlerはウィジェットOSGiバンドルを作成できます。 詳しい手順については、「 [JavaScriptウィジェットを作成してJavaScriptツール](../liferay-js-generator.md) でバンドルする」を参照してください。 OSGiバンドル作成の構成オプションは次のとおりです。

  - **create-jar**：真の値に設定すると、OSGiバンドルが作成されます。 `true`設定すると、すべてのサブオプションはデフォルト値を取ります。 オブジェクトが渡されると、各サブオプションを個別に構成できます。 これをビルドフラグとして渡すこともできます： `$ liferay-npm-bundler --create-` または `$ liferay-npm-bundler -j`。 デフォルト値は `false`です。

<!-- end list -->

``` json
{
  "create-jar"：true
}
```

  - **create-jar.auto-deploy-portlet**：このオプションは非推奨です。 代わりに `create-jar.features.js-extender` オプションを使用してください。

  - **create-jar.features.configuration**：使用するシステム（OSGi）およびウィジェットインスタンス（ポートレット仕様で定義されているウィジェット設定）の構成を記述したファイルを指定します。 （必要な設定構成の詳細については、「 [JavaScriptウィジェットのシステム設定とインスタンス設定の構成](../liferay-js-generator.md) を参照してください）。 そのファイルが存在する場合、デフォルト値は `features/configuration.json` です。それ以外の場合、デフォルトは `undefined`です。

<!-- end list -->

``` json
{
  "create-jar"：{
    "features"：{
      "configuration"： "features/configuration.json"
    }
  }
}
```

  - **create-jar.output-dir：** 最終的なJARを配置する場所を指定します。

<!-- end list -->

``` json
{
  "create-jar"：{
    "output-dir"： "dist"
  }
}
```

  - **create-jar.features.js-extender：** OSGiバンドルをJS Portlet Extenderで処理するかどうかを制御します。 バンドルに使用するエクステンダーの最低限必要なバージョンを指定することもできます。 これは、バンドルで高度な機能を使用したいが、Extenderの古いバージョンで展開可能にしたい場合に役立ちます。 ストリング `"any"` を渡して、バンドルをエクステンダーの任意のバージョンにデプロイできるようにします。 `true`場合、liferay-npm-bundlerは、バンドルで使用される機能に必要なエクステンダーの最小バージョンを自動的に決定します。 デフォルト値は `true`です。 構成例を次に示します。

<!-- end list -->

``` json
{
  "create-jar": {
    "features": {
      "js-extender": "1.1.0"
    }
  }
}
```

  - **create-jar.features.web-context：** バンドルの静的リソースの公開に使用するコンテキストパスを指定します。 デフォルト値は `/{project name}-{project version}`。

<!-- end list -->

``` json
{
  "create-jar"：{
    "features"：{
      "web-context"： "/ my-project"
    }
  }
}
```

  - **create-jar.features.localization：** バンドルに使用するL10Nファイルを指定します。ウィジェットでのローカリゼーションの使用の詳細については、「 [JavaScriptウィジェットでローカリゼーションを提供する](../liferay-js-generator.md) を参照してください。 デフォルト値は、そのベース名を持つプロパティファイルが存在する場合、 `features/localization/Language` です。それ以外の場合、デフォルトは `undefined`です。

<!-- end list -->

``` json
{
  "create-jar"：{
    "features"：{
      "localization"： "features/localization/Language"
    }
  }
}
```

  - **create-jar.features.settings：** このオプションは非推奨です。 代わりに `create-jar.features.configuration` オプションを使用してください。

<!-- end list -->

``` note::
  プラグイン構成は（ `バベルのマニュアルを参照バベルを実行するときに使用可能なすべてのフェーズでは、プラグインと同様に、` `.babelrc``ファイルを設定するためのオプションを指定します <https://babeljs.io/docs/usage/babelrc/>そのファイルフォーマットの詳細については、` _を）。
```

``` note::
  liferay-npm-bundlerのバージョン1.4.0より前では、パッケージ構成はツールオプション（「*」、「出力」、「除外」など）の横に配置されていました パッケージ名の衝突を防ぐために、パッケージ構成は名前空間になり、「パッケージ」セクションの下に配置されます。 後方互換性を維持するために、パッケージ構成がない場合、liferay-npm-bundlerは、パッケージ構成の「packages」以外のルートセクションにフォールバックします（「package-name @ version」、「package-name」、または「*」）は「パッケージ」セクションにあります。
```

これで、 `.npmbundlerrc` ファイルの構造がわかりました\！
