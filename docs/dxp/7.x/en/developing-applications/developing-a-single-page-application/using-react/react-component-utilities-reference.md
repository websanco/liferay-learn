# React Component Utilities Reference

Several useful tools are available to help you build high-performance components and applications in Liferay DXP using React: 

* [`frontend-js-react-web` module](#frontend-js-react-web-module)
* [React Component Tag](#react-component-tag)

## `frontend-js-react-web` Module

The `frontend-js-react-web` shared module provides a single common version of React that you can use in all consumers of React across Liferay DXP. The module includes a standard way to mount (i.e. render) a React component in the context of Liferay DXP, taking care of such details as unmounting the component (cleanly disposing of it) when its corresponding portlet is destroyed. An example is shown below:

```javascript
import {render} from 'frontend-js-react-web';
render(
renderable,
renderData,
container
);
```

These parameters are required:

`renderable`: The React element or a function that returns one to render

`renderData`: Data to pass to the renderable component as props

`container`: The DOM node where the component is to be mounted (e.g. `document.getElementById(placeholderId).parentElement`)

### Common Hooks

The `frontend-js-react-web` module also includes a set of common hooks that you can use to mix-in useful behaviors to your components without having to duplicate code:

[`usePrevious` hook](https://reactjs.org/docs/hooks-faq.html#how-to-get-the-previous-props-or-state) can be used for comparing current and previous values (state, props or any arbitrary value).

`useIsMounted` hook can be used for determining whether a component is still mounted. An example is shown below:

```javascript
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

`useEventListener` hook can be used for adding an event listener on mount and removing it on unmount. Here's an example: 

```javascript
import {useEventListener} from 'frontend-js-react-web';

function OverlayContainer({root, allowEdit}) {
    useEventListener('keydown', handleKeydown, true, document);
    useEventListener('click', handleClick, false, document);

    return <></>;
}
```

## React Component Tag

The `<react:component>` tag adds a React view to your JSP. An example is shown below:

```jsp
<react:component
    data="<%= data %>"
    module="js/pages/form-view/EditFormViewApp.es"
/>
```

It wraps the `render()` function for the `frontend-js-react-web` module mentioned [above](#frontend-js-react-web-module). The `data` passed here is passed as `renderData` and the `module` must export a `renderable` (component function or React element as mentioned [above](#frontend-js-react-web-module)).
