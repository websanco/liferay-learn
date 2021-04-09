# AMDローダー構成のエクスポート方法

``` note::
  Liferay AMD Loader の仕組みがわからない場合は、まず `Liferay AMD モジュールLoader <./understanding-bundler-loaders.md>`_ を読んでください。
```

[重複除外](./how-portal-publishes-npm-packages.md#package-de-duplication) されている場合、JavaScriptモジュールは、 `/o/js_loader_modules` URLによって返される構成を通じてLiferay AMD Loaderで使用できるようになります。

以下に示すOSGiバンドルは、この記事の参照用に使用されています。

<!-- TODO: Update the following bullets into an actual dir structure diagram akin to: https://learn.liferay.com/dxp-cloud/latest/en/reference/upgrading-your-dxp-cloud-stack.html#preparing-to-upgrade -->

  - `my-bundle/`
      - `META-INF/`
          - `resources/`
              - `package.json`
                  - name: my-bundle-package
                  - version: 1.0.0
                  - main: lib/index
                  - dependencies:
                      - isarray：2.0.0
                      - isobject: 2.1.0
                  - ...
              - `lib/`
                  - `index.js`
                  - ...
              - ...
              - `node_modules/`
                  - `isobject@2.1.0/`
                      - `package.json`
                          - name: isobject
                          - version: 2.1.0
                          - main: lib/index
                          - dependencies:
                              - isarray: 1.0.0
                          - ...
                      - ...
                  - `isarray@1.0.0/`
                      - `package.json`
                          - name: isarray
                          - version: 1.0.0
                          - ...
                      - ...
                  - `isarray@2.0.0/`
                      - `package.json`
                          - name: isarray
                          - version: 2.0.0
                          - ...
                      - ...

たとえば、上記の構造の場合、 [OSGiバンドルとnpmパッケージ構造](./the-structure-of-osgi-bundles-containing-npm-packages.md)で説明されているように、Liferay AMDローダーが使用するために以下の構成が公開されています。

``` javascript
Liferay.PATHS = {
  ...
  'my-bundle-package@1.0.0/lib/index': '/o/js/resolved-module/my-bundle-package@1.0.0/lib/index',
  'isobject@2.1.0/index': '/o/js/resolved-module/isobject@2.1.0/index',
  'isarray@1.0.0/index': '/o/js/resolved-module/isarray@1.0.0/index',
  'isarray@2.0.0/index': '/o/js/resolved-module/isarray@2.0.0/index',
  ...
}
Liferay.MODULES = {
  ...
  "my-bundle-package@1.0.0/lib/index.es": {
    "dependencies": ["exports", "isarray", "isobject"],
    "map": {
      "isarray": "isarray@2.0.0", 
      "isobject": "isobject@2.1.0"
    }
  },
  "isobject@2.1.0/index": {
    "dependencies": ["module", "require", "isarray"],
    "map": {
      "isarray": "isarray@1.0.0"
    }
  },
  "isarray@1.0.0/index": {
    "dependencies": ["module", "require"],
    "map": {}
  },
  "isarray@2.0.0/index": {
    "dependencies": ["module", "require"],
    "map": {}
  },
  ...
}
Liferay.MAPS = {
...
  'my-bundle-package@1.0.0': { value: 'my-bundle-package@1.0.0/lib/index', exactMatch: true}
  'isobject@2.1.0': { value: 'isobject@2.1.0/index', exactMatch: true},
  'isarray@2.0.0': { value: 'isarray@2.0.0/index', exactMatch: true},
  'isarray@1.0.0': { value: 'isarray@1.0.0/index', exactMatch: true},
  ...
}
```

注意：

  - `Liferay.PATHS` プロパティは、JavaScriptモジュールファイルへのパスを記述します。
  - `Liferay.MODULES` プロパティは、各モジュールの依存関係の名前とバージョンを記述します。
  - `Liferay.MAPS` プロパティは、パッケージのメインモジュールのエイリアスを示します。
