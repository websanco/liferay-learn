# How the Bundler Publishes npm Packages

When you deploy an OSGi bundle with the specified [structure](./the-structure-of-osgi-bundles-containing-npm-packages.md), its modules are made available for consumption through canonical URLs. To better illustrate resolved modules, the example structure below is the standard structure that the liferay-npm-bundler 1.x generates. It doesn't have the namespaced packages that the 2.x version generates. The liferay-npm-bundler 2.0 overrides this de-duplication mechanism to implement isolated dependencies and imports, as explained below.

- `my-bundle/`
    - `META-INF/`
        - `resources/`
            - `package.json`
                - name: my-bundle-package
                - version: 1.0.0
                - main: lib/index
                - dependencies:
                    - isarray: 2.0.0
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

If you deploy the example OSGi bundle shown above, the following URLs are made available (one for each module):

* [http://localhost/o/js/module/598/my-bundle-package@1.0.0/lib/index.js](http://localhost/o/js/module/598/my-bundle-package@1.0.0/lib/index.js)

* [http://localhost/o/js/module/598/isobject@2.1.0/index.js](http://localhost/o/js/module/598/isobject@2.1.0/index.js)

* [http://localhost/o/js/module/598/isarray@1.0.0/index.js](http://localhost/o/js/module/598/isarray@1.0.0/index.js)

* [http://localhost/o/js/module/598/isarray@2.0.0/index.js](http://localhost/o/js/module/598/isarray@2.0.0/index.js)

```note::
  The OSGi bundle ID (598) may vary.
```

## Package De-duplication

Since two or more OSGi modules may export multiple copies of the same package and version, Liferay Portal must de-duplicate such collisions, by using a new concept called a *resolved module*.

A resolved module is the reference package exported to Liferay Portal's front-end, when multiple copies of the same package and version exist. It's randomly referenced from one of the several bundles exporting the same copies of the package.

Using the example above, for each group of canonical URLs referring to the same module inside different OSGi bundles, there's another canonical URL for the resolved module. The example structure has the resolved module URLs shown below:

* [http://localhost/o/js/resolved-module/my-bundle-package@1.0.0/lib/index.js](http://localhost/o/js/resolved-module/my-bundle-package@1.0.0/lib/index.js)

* [http://localhost/o/js/resolved-module/my-bundle-package$isobject@2.1.0/index.js](http://localhost/o/js/resolved-module/my-bundle-package$isobject@2.1.0/index.js)

* [http://localhost/o/js/resolved-module/my-bundle-package$isarray@1.0.0/index.js](http://localhost/o/js/resolved-module/my-bundle-package$isarray@1.0.0/index.js)

* [http://localhost/o/js/resolved-module/my-bundle-package$isarray@2.0.0/index.js](http://localhost/o/js/resolved-module/my-bundle-package$isarray@2.0.0/index.js)

```note::
  The OSGi bundle ID (598 in the example) is removed and module is replaced by `resolved-module`.
```

Next you can learn how the bundler (since version 2.0.0) isolates package dependencies. See [What Changed Between liferay-npm-bundler 1.x and 2.x](./changes-between-bundler-1.x-and-2.x.md) for more information on why this change was made. 

## Isolated Package Dependencies

A typical OSGi bundle structure generated with liferay-npm-bundler 2.x is shown below:

- `my-bundle/`
    - `META-INF/`
        - `resources/`
            - `package.json`
                - name: my-bundle-package
                - version: 1.0.0
                - main: lib/index
                - dependencies:
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
                        - version: 2.1.0
                        - main: lib/index
                        - dependencies:
                            - my-bundle-package$isarray: 1.0.0
                        - ...
                    - ...
                - `my-bundle-package$isarray@1.0.0/`
                    - `package.json`
                        - name: my-bundle-package$isarray
                        - version: 1.0.0
                        - ...
                    - ...
                - `my-bundle-package$isarray@2.0.0/`
                    - `package.json`
                        - name: my-bundle-package$isarray
                        - version: 2.0.0
                        - ...
                    - ...

Note that each package dependency is namespaced with the bundle's name (`my-bundle-package$` in the example structure). This lets each project load its own dependencies and avoid potential collisions with projects that export the same package. For example, consider the two widget projects below:

- `my-widget`
    - package.json
        - dependencies:
            - a-library 1.0.0
            - a-helper 1.0.0
    - node_modules
        - a-library
            - version: 1.0.0
            - dependencies:
                - a-helper ^1.0.0
        - a-helper
            - version: 1.0.0

- `another-widget`
    - package.json
        - dependencies:
            - a-library 1.0.0
            - a-helper 1.2.0
    - node_modules
        - a-library
            - version: 1.0.0
            - dependencies:
                - a-helper ^1.0.0
        - a-helper
            - version: 1.2.0

In this example, `a-library` depends on `a-helper` at version 1.0.0 or higher (note the caret ^ expression in the dependencies). The bundler implements isolated dependencies by prefixing the name of the bundle to the modules, so that `my-widget` gets its `a-helper` at 1.0.0, while `another-widget` gets its `a-helper` at 1.2.0.
 
The dependencies isolation not only avoids collisions between bundles, but also makes peer dependencies behave deterministically as each widget gets what it had in its `node_modules` folder when it was developed. 

Now that you understand how namespacing modules isolates bundle dependencies, avoiding collisions, you can learn about de-duplication next. 

## De-duplication through Importing

Isolated dependencies are useful, but sometimes sharing the same package between modules would be more beneficial. To do this, the liferay-npm-bundler can import packages from an external OSGi bundle, instead of using your own. Now you can put shared dependencies in one project and reference them from the rest. 

For example, if three widgets, `my-toolbar`, `my-menu`, and `my-content`, compose the home page of your site, these widgets depend on the fake, but awesome, Wonderful UI Components (WUI) framework. This quite limited framework contains only three packages:

1.  `component-core`
1.  `button`
1.  `textfield`

Since the bundler namespaces each dependency package with the widget's name by default, you end up with three namespaced copies of the WUI package on the page. This is not what you want. Since they share the same package, you can create a fourth bundle that contains the WUI package and import the WUI package in the three widgets. This results in the structure below:

- `my-toolbar/`
    - `.npmbundlerrc`
        - config:
            - imports:
                - wui-provider:
                    - component-core: ^1.0.0
                    - button: ^1.0.0
                    - textfield: ^1.0.0
- `my-menu/`
    - `.npmbundlerrc`
        - config:
            - imports:
                - wui-provider:
                    - component-core: ^1.0.0
                    - button: ^1.0.0
                    - textfield: ^1.0.0
- `my-content/`
    - `.npmbundlerrc`
        - config:
            - imports:
                - wui-provider:
                    - component-core: ^1.0.0
                    - button: ^1.0.0
                    - textfield: ^1.0.0
- `wui-provider/`
    - `.package.json`
        - name: wui-provider
        - dependencies: 
            - component-core: 1.0.0
            - button: 1.0.0
            - textfield: 1.0.0

The bundler switches the namespace of certain packages, thus pointing them to an external bundle. Say that you have the following code in `my-toolbar` widget:

```javascript
var Button = require('button');
```

By default, the bundler 2.x transforms this into the following when not imported from another bundle:

```javascript
var Button = require('my-toolbar$button');
```

But, because `button` is imported from `wui-provider`, it is instead changed to the value below:

```javascript
var Button = require('wui-provider$button');
```

Also, a dependency on `wui-provider$button` at version `^1.0.0` is included in `my-toolbar`'s `package.json` file so that the loader finds the correct version. That's all you need. Once `wui-provider$button` is required at runtime, it jumps to `wui-provider`'s context and loads the subdependencies from there on, even if code is executed from `my-toolbar`. This works because, as you can imagine, `wui-provider`'s modules are namespaced too, and once you load a module from it, it keeps requiring `wui-provider$` prefixed modules all the way down. 

## Strategies When Importing Packages

De-duplication by importing is a powerful tool, but you must design a versioning strategy so that you don't run into errors. 

First, you must decide if you want to declare imported dependencies only in the `.npmbundlerrc` file or in the `package.json` too. Listing an imported dependency in `.npmbundlerrc` is enough; even if it's not in your `node_modules` folder the loader finds it at runtime. Listing an imported dependency in `.npmbundlerrc` is enough; even if it isn't present in your `node_modules` folder the loader finds it at runtime. If you have previous experience with dynamic linking support in standard operating systems, think of it as a DLL or shared object.

You may need to install your dependencies in `node_modules` too if you use them for tests, or if they contain types needed to compile (like in Typescript), etc. If that is the case, you can place them in the `dependencies` or `devDependencies` section of your `package.json`. If you list them under the latter, they are excluded automatically from the output bundle by the liferay-npm-bundler. Otherwise, you must exclude them in the `.npmbundlerrc` file so they don't redundantly appear in the output.

If you list dependencies both in `package.json` and `.npmbundlerrc`, decide how to keep versions in sync. The best advice is to use the same version constraints in both files, but you may decide not to do so if it is necessary. For example, imagine that you import one of your dependencies from another bundle during runtime to run tests. Say you are using version constraint ^1.5.1. It would be desirable that if you have tested your code with a version >=1.5.1 and <2.0.0 (that's what ^1.5.1 means), you get a compatible version during runtime. Thus, you would declare the dependency with ^1.5.1 in `.npmbundlerrc` too. 

However, there are times when you may want to be more lenient, and you may need to get a lower version (1.4.0 for example) during runtime, even if you are developing against ^1.5.1. In that case, you can declare ^1.5.1 in your `package.json` and ^1.0.0 in `.npmbundlerrc`. 

In the end, it's up to you to decide how you want to handle your dependencies:

1. `package.json` (While developing)
1. `.npmbundlerrc` (During runtime)

we recommend that you choose a versioning strategy and stick to it, to ensure dependencies are satisfied at runtime. 
