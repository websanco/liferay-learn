# AMD向けにJavaScriptモジュールをフォーマットする方法

Liferay AMDローダーは[AMD仕様](https://github.com/amdjs/amdjs-api/wiki/AMD)に基づいています。 npm OSGiバンドル内のすべてのモジュールは、AMD形式でなければなりません。 これは [CommonJS](http://www.commonjs.org/)モジュールに対して、`定義`呼び出しの中でモジュールコードをラップすることによって行われます。 liferay-npm-bundlerは、モジュールをラップして、このプロセスを自動化するのに役立ちます。 以下の構造について詳しくは、 [OSGi Bundles and npm Package Structure](./the-structure-of-osgi-bundles-containing-npm-packages.md)をご覧ください。

- `my-bundle/`
    - `META-INF/`
        - `リソース/`
            - `package.json`
                - name: my-bundle-package
                - バージョン：1.0.0
                - main: lib/index
                - 依存関係:
                    - my-bundle-package$isarray: 2.0.0
                    - my-bundle-package$isobject: 2.1.0
                - ...
            - `lib/`
                - `index.js`
                - ...
            - ...
            - `node_modules/`
                - `my-bundle-package$isobject@2.1.0/`
                    - `package.json`
                        - name: my-bundle-package$isobject
                        - バージョン：2.1.0
                        - main: lib/index
                        - 依存関係:
                            - my-bundle-package$isarray: 1.0.0
                        - ...
                    - ...
                - `my-bundle-package$isarray@1.0.0/`
                    - `package.json`
                        - name: my-bundle-package$isarray
                        - バージョン：1.0.0
                        - ...
                    - ...
                - `my-bundle-package$isarray@2.0.0/`
                    - `package.json`
                        - name: my-bundle-package$isarray
                        - バージョン：2.0.0
                        - ...
                    - ...

たとえば、 `my-bundle-package$isobject@ 2.1.0` パッケージの `index.js` ファイルには、次のコードが含まれています。

```javascript
'use strict';

var isArray = require('my-bundle-package$isarray');

module.exports = function isObject(val) {
    return val != null && typeof val === 'object' && isArray(val) === false;
};
```

AMD形式用に構成された更新されたモジュールコードを以下に示します。

```javascript
define(
    'my-bundle-package$isobject@2.1.0/index', 
    ['module', 'require', 'my-bundle-package$isarray'], 
    function (module, require) {
        'use strict';

        var define = undefined;

        var isArray = require('my-bundle-package$isarray');

        module.exports = function isObject(val) {
            return val != null && typeof val === 'object' 
            && isArray(val) === false;
        };
    }
);
```

```{note}
モジュールの名前は、そのパッケージ、バージョン、およびファイルパスに基づいている必要があります（たとえば、「my-bundle-package$isobject@2.1.0/index」）。そうでない場合、Liferay AMD Loaderはそれを見つけることができません。
```

モジュールの依存関係に注意してください： `['module'、 'require'、 'my-bundle-package$isarray']`。

AMD仕様で定義されているように、 `module.exports` オブジェクトとローカル `require` 関数への参照を取得するには、 `module` および `require` 使用する必要があります。

後続の依存関係は、このモジュールが依存するモジュールを示します。 例の `my-bundle-package$isarray` はパッケージではなく、 `my-bundle-package$isarray` パッケージのメインモジュールのエイリアスであることに注意してください（したがって、 `my-bundle-package$isarray/ indexと同等です）`）。

`package.json`ファイルから、`my-bundle-package$isarray<code> が <code>my-bundle-package$isarray/index<code> を指していることがわかりますが、さらにそれがはそのようなパッケージのバージョン <code>1.0.0<0> に解決しなければならないということ、つまり、この場合の <0>my-bundle-package$isarray/index` は <0>my-bundle-package$isarray@1.0.0/index</code> を指しているという情報がファイルからわかります。

また、ファイルの先頭に `var define = undefined;` が追加されていることにもお気づきでしょう。 これは `liferay-npm-bundler` によって導入され、モジュールを（AMD環境ではなく）CommonJS環境内にあると考えさせます。 これは、一部のnpmパッケージがUMD形式で記述されており、AMD `define（）` 呼び出し内にラップしているため、独自の `define（）` を実行させたくないが、CommonJSを使用することを好むからです。パス、エクスポートは `module.exports` グローバルを介して行われます。

これで、liferay-npm-bundlerがAMDのJavaScriptモジュールをどのようにフォーマットするかについて理解を深めることができました。
