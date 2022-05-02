# レイアウト要素の使用

> 利用可能：Liferay Portal 7.3 GA2以降、以前は*パネル*および*行*を含むレイアウトと呼ばれていました。

*レイアウト要素*は、ページとテンプレートのレイアウトを設計するために使用できるドロップゾーンを定義します。 デフォルトでは、Liferayには [*コンテナ*](#containers) と [*グリッド*](#grids) の2つのレイアウト要素が含まれています。 追加すると、ウィジェットやその他のフラグメントをこれらのドロップゾーンにドラッグアンドドロップでき、さらにコンテナやグリッドを追加することで、さらに複雑なレイアウトを作成できます。 レイアウトを設計した後、レイアウト構成を保存して、他のページやテンプレートで再利用できます。 詳細については、 [フラグメント構成の保存](./saving-fragment-compositions.md) を参照してください。

フラグメントに対応している新しいページまたはテンプレートを作成すると、デフォルトのドロップゾーンが表示されます。 この領域には任意のフラグメントを追加できますが、コンテナフラグメントをコンポジションの最初の要素として使用すると、重要な利点があります。

* [高度なコンポジション](./saving-fragment-compositions.md#creating-advanced-compositions-with-the-container-fragment) の*Flex*ディスプレイプロパティを含む、ページデザインのレイアウトコントロールの強化（Liferay DXP 7.4以降）
* 他のページやテンプレートでコンポジションをすばやく[保存して再利用](./saving-fragment-compositions.md)する機能
* フラグメントコンポジションにURLまたはページリダイレクトを追加する機能
* [スタイルブック](../../../site-appearance/style-books/using-a-style-book-to-standardize-site-appearance.md)を活用してサイトの外観を標準化する機能

コンテナを追加したら、グリッドフラグメントを使用して、さまざまなビューポートのコンテンツレイアウトをカスタマイズできます。 詳細については、 [グリッドフラグメントを使用したレスポンシブレイアウトの構築](./../../../optimizing-sites/building-a-responsive-site/building-responsive-layouts-with-the-grid-fragment.md) を参照してください。

## コンテナ
<!--TASK: Add Image-->
コンテナフラグメントは、構成可能なドロップゾーンをコンテントページに追加します。 各コンテナには、標準の[一般](./configuring-fragments/general-settings-reference.md)オプションと[スタイル](./configuring-fragments/styles-reference.md)オプションが含まれています。 幅を設定してリンクを追加することもできます。 詳細については、 [Container Options](./configuring-fragments/general-settings-reference.md#container-options) を参照してください。

## グリッド
<!--TASK: Add Image-->
グリッドフラグメントは、ページまたはテンプレートに複数のドロップゾーンモジュールを追加します。 これらのモジュールは、水平および垂直に配置できます。

標準の[一般](./configuring-fragments/general-settings-reference.md)および[スタイル](./configuring-fragments/styles-reference.md)オプションに加えて、各グリッドのモジュール数、行ごとのモジュール数、および各モジュールのコンテンツの垂直位置を決定できます。 各モジュールの幅を手動で調整し、モジュール間のパッディングを追加または削除することもできます。 詳細については、 [Grid Options](./configuring-fragments/general-settings-reference.md#grid-options) を参照してください。

## 追加情報

* [デフォルトのフラグメントリファレンス](./default-fragments-reference.md)
* [フラグメント構成の保存](./saving-fragment-compositions.md)
* [フラグメントの設定](./configuring-fragments.md)
