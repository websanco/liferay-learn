# Reactコンポーネントユーティリティーリファレンス

Reactを使用してLiferay DXPで高性能コンポーネントとアプリケーションを構築するのに役立ついくつかの便利なツールが利用可能です。

  - [`frontend-js-react-web`モジュール](#frontend-js-react-web-module)
  - [Reactコンポーネントタグ](#react-component-tag)

## `frontend-js-react-web`モジュール

`frontend-js-react-web`共有モジュールは、Liferay DXP全体でReactのすべてのコンシューマで使用できるReactの単一の共通バージョンを提供します。 このモジュールには、Liferay DXPのコンテキストでReactコンポーネントをマウント（つまりレンダリング）する標準的な方法が含まれており、対応するポートレットが破棄されたときにコンポーネントをアンマウント（クリーンに破棄）する場合などの詳細に対応しています。 例を以下に示します。

``` javascript
import {render} from 'frontend-js-react-web';
render(
renderable,
renderData,
container
);
```

次のパラメーターは必須です。

`renderable`: レンダリングするために1を返すReact要素または関数

`renderData`: プロップとしてレンダリング可能なコンポーネントに渡すデータ

`container`: コンポーネントがマウントされるDOMノード（例： `document.getElementById(placeholderId).parentElement`）

### 共通フック

`frontend-js-react-web`モジュールには、コードを複製することなく、コンポーネントに便利な動作を取り入れるために使用できる一連の共通フックも含まれています。

[`usePrevious`フック](https://reactjs.org/docs/hooks-faq.html#how-to-get-the-previous-props-or-state)は、現在の値と以前の値（状態、プロップ、または任意の値）を比較するために使用できます。

`useIsMounted`フックは、コンポーネントがまだマウントされているかどうかを判別するために使用できます。 例を以下に示します。

``` javascript
import {useIsMounted} from 'frontend-js-react-web';

const InlineConfirm = props => {
    const isMounted = useIsMounted();

    const _handleConfirmButtonClick = () => {
        props.onConfirmButtonClick().then(() => {
            if (isMounted()) {
                setPerformingAction(false);
            }
        });
    };

    return <button onClick={handleConfirmButtonClick} />;

};
```

`useEventListener`フックは、マウント時にイベントリスナーを追加し、アンマウント時にそれを削除するために使用できます。 以下に例を示します。

``` javascript
import {useEventListener} from 'frontend-js-react-web';

function OverlayContainer({root, allowEdit}) {
    useEventListener('keydown', handleKeydown, true, document);
    useEventListener('click', handleClick, false, document);

    return <></>;
}
```

## Reactコンポーネントタグ

`<react:component>`タグは、JSPにReactビューを追加します。 例を以下に示します。

``` jsp
<react:component
    data="<%= data %>"
    module="js/pages/form-view/EditFormViewApp.es"
/>
```

これは、[上記](#frontend-js-react-web-module)に示す`frontend-js-react-web`モジュールの`render()`関数をラップしています。 ここで渡された`data`は`renderData`として渡され、`module`が`renderable`（[上記](#frontend-js-react-web-module)に示すコンポーネント機能またはReact要素）をエクスポートします。
