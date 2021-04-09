# How AMD Loader Configuration is Exported

```note::
  If you don't understand how Liferay AMD Loader works under the hood, please read `Liferay AMD Module Loader <./understanding-bundler-loaders.md>`_ first.
```

With [de-duplication](./how-portal-publishes-npm-packages.md#package-de-duplication) in place, JavaScript modules are made available to Liferay AMD Loader through the configuration returned by the `/o/js_loader_modules` URL.

The OSGi bundle shown below is used for reference in this article:
<!-- TODO: Update the following bullets into an actual dir structure diagram akin to: https://learn.liferay.com/dxp-cloud/latest/en/reference/upgrading-your-dxp-cloud-stack.html#preparing-to-upgrade -->
* `my-bundle/`
    * `META-INF/`
        * `resources/`
            * `package.json`
                * name: my-bundle-package
                * version: 1.0.0
                * main: lib/index
                * dependencies:
                    * isarray: 2.0.0
                    * isobject: 2.1.0
                * ...
            * `lib/`
                * `index.js`
                * ...
            * ...
            * `node_modules/`
                * `isobject@2.1.0/`
                    * `package.json`
                        * name: isobject
                        * version: 2.1.0
                        * main: lib/index
                        * dependencies:
                            * isarray: 1.0.0
                        * ...
                    * ...
                * `isarray@1.0.0/`
                    * `package.json`
                        * name: isarray
                        * version: 1.0.0
                        * ...
                    * ...
                * `isarray@2.0.0/`
                    * `package.json`
                        * name: isarray
                        * version: 2.0.0
                        * ...
                    * ...

For example, for the above structure, as explained in [OSGi Bundles and npm Package Structure](./the-structure-of-osgi-bundles-containing-npm-packages.md), the configuration below is published for Liferay AMD loader to consume:

```javascript
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

Note:

* The `Liferay.PATHS` property describes paths to the JavaScript module files.
* The `Liferay.MODULES` property describes the dependency names and versions of each module.
* The `Liferay.MAPS` property describes the aliases of the package's main modules.
