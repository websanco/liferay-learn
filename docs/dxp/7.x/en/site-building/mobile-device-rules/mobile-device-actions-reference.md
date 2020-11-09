# Mobile Device Actions Reference

By default, there are four kinds of actions that can be configured for mobile families:

* **Layout Template Modification:** Changes the way portlets are arranged on pages delivered to mobile devices. For example, you could have pages with more complex layouts automatically switch to a simpler template if it detects a mobile device---even if the resolution is theoretically high enough to support the standard layout.

* **Theme Modification:** Selects a specific theme for different mobile device families. You'd have to have a mobile version of your Site's theme that is automatically applied when a device hits your page.

* **URL Redirect:** Sends mobile users to any URL. This can be used to direct mobile users to a mobile app download or a mobile version of the page.

* **Site Redirect:** Sends mobile users to a different Site on your portal. In some cases, mobile content could be created on a mirror of your Site.

```tip:
   DXP was designed from the ground up to be responsive and adapt to any device that might be accessing it. Before creating new themes or forcing a layout template change, you should test how the Site behaves using DXP out of the box. Certain features, like URL Redirects, can be disruptive and frustrating for users if used improperly.
```

## Additional Information

* [Creating Mobile Device Rules](./creating-mobile-device-rules.md)
