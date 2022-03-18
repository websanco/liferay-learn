# OSGiバンドルとnpmパッケージ構造

JavaScriptモジュールをデプロイするには、プロジェクトの `node_modules` フォルダーから抽出されたnpm依存関係でOSGiバンドルを作成し、それらを [Liferay AMD Loader](https://github.com/liferay/liferay-amd-loader)動作するように変更する必要があります。 liferay-npm-bundlerはこのプロセスを自動化し、以下のようなバンドルを作成します。

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

`node_modules` 内のパッケージはnpmツールと同じ形式で、標準の `node_modules` フォルダーから（AMDへの変換などの処理後に）コピーできます。 `node_modules` フォルダーは、任意の数のnpmパッケージ（同じパッケージの異なるバージョンであっても）を保持するか、npmパッケージをまったく保持できません。

これでnpmパッケージを含むOSGiバンドルの構造がわかったと思うので、津次はliferay-npm-bundlerがインラインJavaScriptパッケージを処理する方法について説明します。

## インラインJavaScriptパッケージ

liferay-npm-bundlerが作成する結果のOSGiバンドルは、1つのインラインJavaScriptパッケージ（例では `my-bundle-package` という名前）と、 `node_modules` フォルダ内に配置された複数のnpmパッケージを、フォルダごとに1つずつデプロイするように構成されています。

インラインパッケージは、OSGi標準の `META-INF/resources` フォルダにネストされ、標準のnpm `package.json` ファイルで定義されます。

インラインパッケージはオプションですが、1つのOSGiバンドルにつき1つのインラインパッケージしか許可されません。 OSGiバンドルに含まれている場合、インラインパッケージは通常、ウィジェットのJavaScriptコードを提供します。 なお、このアーキテクチャでは、公開後のインラインパッケージとnpmパッケージを区別していません。 インラインパッケージは、組織的な目的でのみ使用されます。

これでliferay-npm-bundlerがどのようにしてnpmパッケージのOSGiバンドルを作成するかがわかりましたね。
