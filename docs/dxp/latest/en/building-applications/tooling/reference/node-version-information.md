# Node Version Information

Liferay DXP uses [Node](https://nodejs.org/en/) (and [NPM](https://www.npmjs.com/)) for many different frontend components. Specifically, the [Liferay Theme Generator](../../../site-building/site-appearance/themes/theme-development/getting-started/setting-up-an-environment-and-creating-a-theme.md) and Liferay's [JavaScript application tools](../../developing-a-javascript-application.md) require the use of NPM commands to build and deploy.

These dependencies are designed to use specific versions of Node and NPM. Check the [Node version compatibility matrix](#version-compatibility-matrix) to ensure you have the correct versions installed when developing themes or JavaScript applications.

## Updating Your Node Version

If you have an old or unsupported version of Node installed for your Liferay installation, then you may need to update it to work correctly. Follow these steps to update Node to a specific major version.

1. Remove your existing Node installation.

	```bash
	sudo apt-get remove node npm
	```

1. Update your repositories.

	```bash
	sudo apt-get update
	```

1. Retrieve the desired *major version* of Node. Check the [version compatibility matrix](#version-compatibility-matrix) for the major version of Node required for your Liferay DXP version.

	```bash
	curl -sL deb.nodesource.com/setup_##.x | sudo -E bash -
	```

	For example, if you are using Liferay version 7.4, then run the following for version Node version `16.x`:

	```bash
	curl -sL deb.nodesource.com/setup_16.x | sudo -E bash -
	````

1. Install Node and NPM with a single command.

	```bash
	sudo apt-get install -y nodejs
	````

1. Verify that you have the expected versions of Node and NPM.

	```bash
	node --version
	```

	```bash
	npm --version
	```

You should now have a supported version of Node and NPM for your Liferay installation (updated to the latest minor version that is supported). However, if your NPM version does not match the expected version, then you can install a specific version of NPM with this command:

```bash
npm install -g npm@x.x.x
```

## Specifying a Module's Node Version

If your module requires a specific version of Node, then you can configure this in your module's `build.gradle` file. For example, if your module requires Node version `10.15.3` and NPM version `6.4.1`, then you can add this code to the `build.gradle` file:

```
node {
	nodeVersion = "10.15.3"
	npmVersion = "6.4.1"
}
```

## Version Compatibility Matrix

| **Liferay Version** | **Supported Node Version** | **Supported NPM Version** |
| :---: | :---: | :---: |
| 7.0 | 6.6.0 | 6.4.1 |
| 7.1 | 8.15.0 | 6.4.1 |
| 7.2 | 10.15.3 | 6.4.1 |
| 7.3 | 10.15.3 | 6.4.1 |
| 7.4 | 16.13.0 | 8.1.0 |
