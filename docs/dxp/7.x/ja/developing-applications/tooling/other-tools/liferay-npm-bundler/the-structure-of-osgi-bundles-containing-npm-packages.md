# OSGiバンドルとnpmパッケージ構造

JavaScriptモジュールをデプロイするには、プロジェクトの `node_modules` フォルダーから抽出されたnpm依存関係でOSGiバンドルを作成し、それらを [Liferay AMD Loader](https://github.com/liferay/liferay-amd-loader)動作するように変更する必要があります。 liferay-npm-bundlerはこのプロセスを自動化し、以下のようなバンドルを作成します。

  - `my-bundle/`
      - `META-INF/`
          - `resources/`
              - `package.json`
                  - name: my-bundle-package
                  - version: 1.0.0
                  - main: lib/index
                  - dependencies:
                      - my-bundle-package$isarray：2.0.0
                      - my-bundle-package$isobject：2.1.0
                  - ...
              - `lib/`
                  - `index.js`
                  - ...
              - ...
              - `node_modules /`
                  - `my-bundle-package$isobject@2.1.0/`
                      - `package.json`
                          - name: my-bundle-package$isobject
                          - version: 2.1.0
                          - main: lib/index
                          - dependencies:
                              - my-bundle-package$isarray：1.0.0
                          - ...
                      - ...
                  - `my-bundle-package$isarray@1.0.0/`
                      - `package.json`
                          - name: my-bundle-package$isarray
                          - version: 1.0.0
                          - ...
                      - ...
                  - `my-bundle-package$isarray@ 2.0.0 /`
                      - `package.json`
                          - name: my-bundle-package$isarray
                          - version: 2.0.0
                          - ...
                      - ...

`node_modules` 内のパッケージはnpmツールと同じ形式で、標準の `node_modules` フォルダーから（AMDへの変換などの処理後に）コピーできます。 `node_modules` フォルダーは、任意の数のnpmパッケージ（同じパッケージの異なるバージョンであっても）を保持するか、npmパッケージをまったく保持できません。

npmパッケージを含むOSGiバンドルの構造がわかったので、liferay-npm-bundlerがインラインJavaScriptパッケージを処理する方法を学習できます。

## インラインJavaScriptパッケージ

liferay-npm-bundlerが作成する結果のOSGiバンドルは、1つのインラインJavaScriptパッケージ（例では `my-bundle-package` という名前）をデプロイするように構成され、複数のnpmパッケージが `node_modules` フォルダー内に配置され、パッケージごとに1つフォルダ。

インラインパッケージはOSGi標準 `META-INF/resources` フォルダーにネストされ、標準のnpm `package.json` ファイルで定義されます。

インラインパッケージはオプションですが、OSGiバンドルごとに許可されるインラインパッケージは1つだけです。 OSGiバンドルに含まれている場合、インラインパッケージは通常、ウィジェットのJavaScriptコードを提供します。 いったん公開されると、アーキテクチャはインラインパッケージとnpmパッケージを区別しないことに注意してください。 インラインパッケージは、組織的な目的でのみ使用されます。

これで、liferay-npm-bundlerがnpmパッケージ用のOSGiバンドルを作成する方法がわかりました\！
