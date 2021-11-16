# Clay Stickers

Whereas badges display numbers and labels display short information, stickers are small visual indicators of the content (usually the content type). They can include a small label or a Liferay icon, and they come in two shapes: circle and square.

Square sticker with label:

```markup
<clay:sticker label="JPG" />
```

![You can include stickers in your apps.](./clay-stickers/images/01.png)

Square sticker with icon:

```markup
<clay:sticker icon="picture" />
```

![Stickers can include icons.](./clay-stickers/images/02.png)

Circle sticker:

```markup
<clay:sticker label="JPG" shape="circle" />
```

![You can also have circle stickers.](./clay-stickers/images/03.png)

Stickers can be positioned in any corner of a div. Indicate their position with the `position` attribute: `top-left`, `bottom-left`, `top-right`, or `bottom-right`:

```markup
<div class="aspect-ratio">

	<img class="aspect-ratio-item-fluid" src="https://claycss.com/images/thumbnail_hot_air_ballon.jpg" />

	<clay:sticker label="PDF" position="top-left" style="danger" />
</div>
```

![You can specify the position of the sticker within a container.](./clay-stickers/images/04.png)

Now you know how to use Clay stickers in your app!

## Related Topics

* [Clay Badges](./clay-badges.md)
* [Clay Cards](./clay-cards.md)
* [Clay Icons](./clay-icons.md)