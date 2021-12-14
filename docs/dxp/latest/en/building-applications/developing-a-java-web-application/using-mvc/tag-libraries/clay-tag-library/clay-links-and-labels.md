# Clay Labels and Links

Liferay Clay taglibs provide tags for creating labels and links in your app. Both of these elements are covered below.

## Labels

The Liferay Clay taglibs provide a few different labels for your app. Use the `clay:label` tag to add a label to your app. You can create color-coded labels, removable labels, and labels that contain links. The sections below demonstrate all of these options.

### Color-coded Labels

The Liferay Clay labels come in four different colors: dark-blue for info, light-gray for status, orange for pending, red for rejected, and green for approved.

Info labels are dark-blue, and since they stand out a bit more than status labels, they are best for conveying general information. To use an info label, set the `style` attribute to `info`:

```jsp
<clay:label label="Label text" style="info" />
```

![Info labels convey general information.](./clay-links-and-labels/images/01.png)

Status labels are light-gray, and due to their neutral color, they are best for conveying basic information. Status labels are the default label and therefore require no `style` attribute:

```jsp
<clay:label label="Status" />
```

![Status labels are the least flashy and best for displaying basic information.](./clay-links-and-labels/images/02.png)

Warning labels are orange, and due to their color, they are best for conveying a warning message. To use a warning label, set the `style` attribute to `warning`:

```jsp
<clay:label label="Pending" style="warning" />
```

![Warning labels notify the user of issues, but nothing app breaking.](./clay-links-and-labels/images/03.png)

Danger labels are red and indicate that something is wrong or has failed. To use a danger label, set the `style` attribute to `danger`:

```jsp
<clay:label label="Rejected" style="danger" />
```

![Danger labels convey a sense of urgency that must be addressed.](./clay-links-and-labels/images/04.png)

Success labels are green and indicate that something has completed successfully. To use a success label, set the `style` attribute to `success`:

```jsp
<clay:label label="Approved" style="success" />
```

![Success labels indicate a successful action.](./clay-links-and-labels/images/05.png)

Labels can also be bigger. Set the `size` attribute to `lg` to display large labels:

```jsp
<clay:label label="Approved" size="lg" style="success" />
```

### Removable Labels

If you want to let a user close a label (e.g. a temporary notification), you can  make the label removable by setting the `closeable` attribute to `true`.

```jsp
<clay:label closeable="<%= true %>" label="Normal Label" />
```

![Labels can be removable.](./clay-links-and-labels/images/06.png)

### Labels with Links

You can make a label a link by adding the `href` attribute to it just as you would an anchor tag:

```jsp
<clay:label href="#" label="Label Text" />
```

![Labels can also be links.](./clay-links-and-labels/images/07.png)

## Links

You can add traditional hyperlinks to your app with the `<clay:link>` tag:

```jsp
<clay:link href="#" label="link text" />
```

![Clay taglibs also provide link elements.](./clay-links-and-labels/images/08.png)

Now you know how to add links and labels to your apps!

## Related Topics

* [Clay Badges](./clay-badges.md)
* [Clay Cards](./clay-cards.md)
* [Clay Form Elements](./clay-form-elements.md)