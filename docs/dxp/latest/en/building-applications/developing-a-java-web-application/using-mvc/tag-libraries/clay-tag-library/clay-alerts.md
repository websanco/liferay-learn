# Clay Alerts

Clay alerts come in two types: embedded and stripe. Both types, along with several examples of each, are shown below.

## Embedded Alerts

Embedded alerts are usually used inside forms. The element that contains it determines an embedded alert's width. The close action is not required for embedded alerts. The following embedded alerts can be created with Clay taglibs:

Danger alert (embedded):

```markup
<clay:alert
	message="This is an error message."
	style="danger"
	title="Error"
/>
```

![The danger alert notifies the user of an error or issue.](./clay-alerts/images/01.png)

Success alert (embedded):

```markup
<clay:alert
	message="This is a success message."
	style="success"
	title="Success"
/>
```

![The success alert notifies the user when an action is successful.](./clay-alerts/images/02.png)

Info alert (embedded):

```markup
<clay:alert
	message="This is an info message."
	title="Info"
/>
```

![The info alert displays general information to the user.](./clay-alerts/images/03.png)

Warning alert (embedded):

```markup
<clay:alert
	message="This is a warning message."
	style="warning"
	title="Warning"
/>
```

![The warning alert displays a warning message to the user.](./clay-alerts/images/04.png)

## Stripe Alerts

Stripe alerts are placed below the last navigation element (either the header or the navigation bar), and they usually appear on *Save* action, communicating the status of the action once received from the server. Unlike embedded alerts, stripe alerts require the close action. A stripe alert is always the full width of the container and pushes all the content below it. The following stripe alerts can be created with Clay taglibs:

Danger alert (stripe):

```markup
<clay:stripe
	message="This is an error message."
	style="danger"
	title="Error"
/>
```

![The danger striped alert notifies the user that an action has failed.](./clay-alerts/images/05.png)

Success alert (stripe):

```markup
<clay:stripe
	message="This is a success message."
	style="success"
	title="Success"
/>
```

![The success striped alert notifies the user that an action has completed successfully.](./clay-alerts/images/06.png)

Info alert (stripe):

```markup
<clay:stripe
	message="This is an info message."
	title="Info"
/>
```

![The info striped alert displays general information about an action to the user.](./clay-alerts/images/07.png)

Warning alert (stripe):

```markup
<clay:stripe
	message="This is a warning message."
	style="warning"
	title="Warning"
/>
```
 
![The warning striped alert warns the user about an action.](./clay-alerts/images/08.png)

Now you know how to alert users!

## Related Topics

* [Clay Buttons](./clay-buttons.md)
* [Clay Form Elements](./clay-form-elements.md)
* [Clay Labels and Links](./clay-links-and-labels.md)