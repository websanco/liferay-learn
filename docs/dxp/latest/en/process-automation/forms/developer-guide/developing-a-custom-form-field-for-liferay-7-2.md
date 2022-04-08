# Developing a Custom Form Field for Liferay 7.2

React-based custom form fields can be developed for Liferay 7.3 and Liferay 7.4. See [Writing Custom Form Field Types](./writing-a-form-storage-adapter.md) to learn how. Liferay 7.2 used a different frontend technology, based on MetalJS and Soy closure templates. In this tutorial you can learn to adapt the React-based Acme C2P9 Slider field to run on a Liferay 7.2 installation.

## Adjusting the Acme C2P9 Slider to Run on Liferay 7.2

Liferay 7.2 used a different frontend framework for custom form fields. To adjust the Acme C2P9 Slider field in order to run it on Liferay 7.2,

1. Download and unzip [the Custom Forms Field Type project](./writing-a-custom-form-field-type/resources/liferay-c2p9.zip).

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/process-automation/forms/developer-guide/liferay-c2p9.zip -O
    ```

    ```bash
    unzip liferay-c2p9.zip
    ```
1. Open the `liferay-c2p9.zip/gradle.properties` file and change the line

   ```properties
   liferay.workspace.product=portal-7.4-ga8
   ```

   to

   ```properties
   liferay.workspace.product=portal-7.2-ga2
   ```

1. Open the `liferay-c2p9.zip/settings.gradle` file and change the line

   ```groovy
   classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "latest.release"
   ```

   to

   ```groovy
   classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "3.4.17"
   ```

1. Open the `liferay-c2p9.zip/c2p9-impl/build.gradle` file and add the lines

   ```groovy
    compileOnly group: "org.osgi", name: "osgi.cmpn"
    jsCompile group: "com.liferay", name: "com.liferay.dynamic.data.mapping.form.field.type"
    ```

1. Open the `liferay-c2p9.zip/c2p9-impl/package.json` file. Begin by replacing the `devDependencies` line

   ```json
    "@liferay/portal-7.4": "*"
   ```

   with these two lines:

   ```json
    "@liferay/portal-7.2": "*",
    "metal-tools-soy": "4.3.2"
   ```
 
   Then replace the existing `scripts` content (two lines) with these three lines:

   ```json
   "build": "npm run build-soy && npm run build-js && liferay-npm-bundler",
   "build-js": "babel --source-maps -d build/resources/main/META-INF/resources src/main/resources/META-INF/resources",
   "build-soy": "metalsoy --externalMsgFormat \"Liferay.Language.get('\\$2')\" --soyDeps \"./node_modules/clay-*/src/**/*.soy\" \"./node_modules/com.liferay.dynamic.data.mapping.form.field.type/META-INF/resources/+(FieldBase|components)/**/*.soy\""
   ```

1. Open the `liferay-c2p9.zip/c2p9-impl/bnd.bnd` file and add these lines:

   ```properties
   Provide-Capability:\
   	soy;\
   		type:String="LiferayFormField"
   ```

1. Replace the contents of `liferay-c2p9.zip/c2p9-impl/.babelrc.js` with

   ```js
   module.exports = {
       presets: ['@babel/preset-env'],
   };
   ```

   **Checkpoint:** The project is reconfigured to expect the 7.2 frontend framework, so you must replace the frontend of the form field project.

1. Remove the existing `Slider.es.js` file and create these three files to replace it:
   - `Slider.es.js`: the new slider component using the MetalJS + Soy frontend.

      ```js
      import 'dynamic-data-mapping-form-field-type/FieldBase/FieldBase.es';
      import './SliderRegister.soy.js';
      import templates from './Slider.soy.js';
      import Component from 'metal-component';
      import Soy from 'metal-soy';
      import {Config} from 'metal-state';

      class Slider extends Component {

          dispatchEvent(event, name, value) {
              this.emit(name, {
                  fieldInstance: this,
                  originalEvent: event,
                  value
              });
          }

          _handleFieldChanged(event) {
              const {value} = event.target;

              this.setState(
                  {
                      value
                  },
                  () => this.dispatchEvent(event, 'fieldEdited', value)
              );
          }
      }

      Slider.STATE = {

          label: Config.string(),

          name: Config.string().required(),

          predefinedValue: Config.oneOfType([Config.number(), Config.string()]),

          required: Config.bool().value(false),

          showLabel: Config.bool().value(true),

          spritemap: Config.string(),

          value: Config.string().value('')
      }

      Soy.register(Slider, templates);

      export default Slider;
      ```
   - `Slider.soy`: the Soy template for the field.
      ```shell
      {namespace Slider}

      {template .render}
          {@param label: string}
          {@param name: string}
          {@param showLabel: bool}
          {@param tip: string}
          {@param value: ?}
          {@param? _handleFieldChanged: any}
          {@param? predefinedValue: any}
          {@param? required: bool}
          {@param? spritemap: string}

          {call FieldBase.render}
              {param contentRenderer kind="html"}
                  {call .content}
                      {param _handleFieldChanged: $_handleFieldChanged /}
                      {param name: $name /}
                      {param predefinedValue: $predefinedValue /}
                      {param value: $value /}
                  {/call}
              {/param}
              {param label: $label /}
              {param name: $name /}
              {param required: $required /}
              {param showLabel: $showLabel /}
              {param spritemap: $spritemap /}
              {param tip: $tip /}
          {/call}
      {/template}

      {template .content}
          {@param name: string}
          {@param value: ?}
          {@param? _handleFieldChanged: any}
          {@param? predefinedValue: any}
          {let $attributes kind="attributes"}
              class="ddm-field-slider form-control slider"

              data-oninput="{$_handleFieldChanged}"

              id="myRange"

              max="100"

              min="1"

              name="{$name}"

              type="range"

              {if $value}
                  value="{$value}"
              {else}
                  value="{$predefinedValue}"
              {/if}
          {/let}

          <input {$attributes}>
      {/template}
      ```

   - `SliderRegister.soy`: the registration code for the Slider's Soy template.

      ```shell
      {namespace SliderRegister}

      {deltemplate PageRenderer.RegisterFieldType variant="'slider'"}
          {call Slider.render data="all" /}
      {/deltemplate}
      ```

1. A minor adjustment to the `DDMFormFieldType` is needed: the identifier for the field (the `name`) cannot have a hyphen (`-`) in it because oit is referenced as a String literal in the Soy template. Open the `C2P9DDMFormFieldType` class and remove the hyphen from

   - the component property `ddm.form.field.type.name`
   - the String returned by `getName`

   The value in both locations is now `c2p9slider`.

1. Once the project's frontend is replaced, deploy it to a running Liferay 7.2. 

   - To start a Liferay 7.2 Docker container run
      ```shell
      docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
      ```
   - To deploy the reconfigured form field project, go to the `liferay-c2p9.zip` folder and run
      ```shell
      ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
      ```

1. Confirm the deployment in the Liferay Docker container console.

   ```bash
   STARTED com.acme.c2p9.impl_1.0.0 [1009]
   ```

The form field is deployed and [ready to use](writing-a-custom-forms-field-type.md#use-the-deployed-slider-field) on Liferay 7.2.

