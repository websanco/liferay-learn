# Installing the Liferay Theme Generator
<!-- is there any information about what the liferay theme generator is? or what the value prop is for it? -->
The steps below show how to install the Liferay Theme Generator for your development environment.

1. Install the version of [NodeJS](http://nodejs.org/) (along with Node Package Manager(npm)) that corresponds with your version of Liferay DXP:

    | Liferay Portal Version | Node Version |
    | --- | --- |
    | 7.0.x | 6.6.0 |
    | 7.1.x | 8.10.0 |
    | 7.2.x+ | 10.15.1 |

    For instance, Liferay Portal 7.2 supports [v10.15.1](https://nodejs.org/download/release/v10.15.1/). Make sure to properly [set up your npm environment](TODO:setting-up-your-npm-environment).

1. Use npm to install the [Yeoman](http://yeoman.io/) dependency:

    ```bash
    npm install -g yo
    ```

    ```note::
      In Liferay JS Themes Toolkit v7.x.x and v8.x.x, Gulp is included as a local dependency in generated themes, so you aren't required to install it. It can be accessed by running `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's root folder. In v9.5.0+ the gulp tasks are available as npm scripts and can be run as `npm run [command]`.
    ```

1. Install the Liferay Theme Generator with the command that matches the Liferay Portal version you want to develop on:

    | Liferay Version | Liferay Theme Generator Version | Command |
    | --- | --- | --- |
    | 6.2 | 7.x.x | `npm install -g generator-liferay-theme@7.x.x` |
    | 7.0 | 7.x.x or 8.x.x | Same as above or below |
    | 7.1 | 8.x.x | `npm install -g generator-liferay-theme@8.x.x` |
    | 7.2+ | 9.x.x | `npm install -g generator-liferay-theme@9.x.x` |

    If you're on Windows, follow the instructions in step 4 to install Sass.

1. The generator uses node-sass. If you're on Windows, you must also install [node-gyp and Python](https://github.com/nodejs/node-gyp#installation).
