# Running Theme Tasks

Several Theme tasks are available for Themes that use the [liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), such as those created with the [Liferay Theme Generator](../../reference/themes/installing-the-theme-generator-reference.md). You can access them from the `scripts` section of your Theme's `package.json` using the pattern below:

```bash
npm run [command]
```

You can use the available Theme tasks to [change your Theme's base Theme](./changing-the-base-theme), [extend your Theme with Themelets](), [configure your Theme's app server](./configuring-your-themes-app-server), and more. You can also [overwrite and extend these Theme tasks](../../../extending-themes/overwriting-and-extending-liferay-theme-tasks.md) if you want.