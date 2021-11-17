# Clay Badges

Badges help highlight important information such as notifications or new and unread messages. Badges have circular borders and are only used to specify a number. This covers the different types of Clay badges you can add to your app.

## Badge Types

The following badge styles are available:

Primary badge:

```markup
<div class="col-md-1">
    <clay:badge label="8" />

    <div>Primary</div>
</div>
```

![A primary badge is bright blue, commanding attention like the primary button of a form.](./clay-badges/images/01.png)

Secondary badge:

```markup
<div class="col-md-1">
    <clay:badge label="87" style="secondary" />

    <div>Secondary</div>
</div>
```

![A secondary badge is light-grey and draws less focus than a primary button.](./clay-badges/images/02.png)

Info badge:

```markup
<div class="col-md-1">
    <clay:badge label="91" style="info" />

    <div>Info</div>
</div>
```

![A info badge is dark blue and meant for numbers related to general information.](./clay-badges/images/03.png)

Error badge:

```markup
<div class="col-md-1">
    <clay:badge label="130" style="danger" />

    <div>Error</div>
</div>
```

![An error badge displays numbers related to an error.](./clay-badges/images/04.png)

Success badge:

```markup
<div class="col-md-1">
    <clay:badge label="1111" style="success" />

    <div>Success</div>
</div>
```

![A success badge displays numbers related to a successful action.](./clay-badges/images/05.png)

Warning badge:

```markup
<div class="col-md-1">
    <clay:badge label="21" style="warning" />

    <div>Warning</div>
</div>
```

![A warning badge displays numbers related to warnings that should be addressed.](./clay-badges/images/06.png)

Now you know how to use badges to keep track of values in your app.

## Related Topics

* [Clay Labels and Links](./clay-links-and-labels.md)
* [Clay Cards](./clay-cards.md)
* [Clay Stickers](./clay-stickers.md)