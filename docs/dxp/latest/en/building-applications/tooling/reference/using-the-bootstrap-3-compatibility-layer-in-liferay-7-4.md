# Using the Bootstrap 3 Compatibility Layer in Liferay 7.4

As of Liferay DXP 7.4 GA1, the [Bootstrap 3](https://getbootstrap.com/) compatibility layer is removed from the built-in packages in Liferay frontend applications. If you are upgrading from a previous version, then you must manually include the compatibility layer as an external package for any of your projects that depend on Bootstrap 3 for their CSS code.

```{note}
If you use the `gulp upgrade` command to [upgrade a theme](../../../site-building/site-appearance/themes/upgrading-a-theme.md) to Liferay 7.4, then you have the option to automatically include the Bootstrap 3 compatibility layer with the upgrade. Then you do not need to manually add the compabitility layer to that theme.
```

```{warning}
This compatibility layer is provided as an external package only to help upgrades to Liferay 7.4 GA1 go more smoothly, and it is not intended to work for later versions. After you upgrade, it is recommended that you change your project's code so that you can remove the dependency on Bootstrap 3.
```

## Installing the Compatibility Layer

Run this command to install the compatibility layer so you can use it in your projects:

```bash
npm install @liferay/bs3-bs4-compat
```

You can also run this command to install it using Yarn:

```bash
yarn add @liferay/bs3-bs4-compat
```

Once you have the compatibility layer installed, you can use it in any project that still needs to be compatible with Bootstrap 3.

## Using the Compatibility Layer in Your Project

To use the compatibility layer, you must import it into your project's `main.scss` and `clay.scss` files.

Add this to your project's `src/css/main.scss` file:

```css
@import 'clay/base';

@import 'clay/atlas';

@import '@liferay/bs3-bs4-compat/scss/atlas_variables';

@import '@liferay/bs3-bs4-compat/scss/components';

@import '@liferay/bs3-bs4-compat/scss/variables';
```

Then, if you require the atlas theme variables, then add this to your project's `src/css/clay.scss` file:

```css
@import 'clay/atlas';

@import '@liferay/bs3-bs4-compat/scss/variables';

@import '@liferay/bs3-bs4-compat/scss/atlas_variables';

@import '@liferay/bs3-bs4-compat/scss/components';
```

If you **do not** require the atlas theme variables, then add this to your project's `src/css/clay.scss` file instead:

```css
@import 'clay/base';

@import '@liferay/bs3-bs4-compat/scss/variables';

@import '@liferay/bs3-bs4-compat/scss/components';
```

Now the compatibility layer is properly included in your theme, and your projects may still use Bootstrap 3 with Liferay DXP 7.4 GA1.

## Additional Information

* [Upgrading a Theme](../../../site-building/site-appearance/themes/upgrading-a-theme.md)
* [Features Deprecated in 7.4](../../../installation-and-upgrades/upgrading-liferay/reference/maintenance-mode-and-deprecations-in-7-4.md#features-deprecated-in-7-4)
