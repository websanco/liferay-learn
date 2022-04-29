# Create Themelets to Deploy with the Theme

<!-- <div class="ahead">
<h4>Exercise Goals</h4>
    <ul>
    <li>Create a Widget Dropzone themelet</li>
      <ul>
          <li>Connect the themelet to the theme</li>
      </ul>
</div>

## Generate a Themelet
1. **Go to** the _liferay_ folder you already created in the _Command Line/Terminal_.
	- Windows: _C:\liferay_
	- Mac/Linux: _~/liferay_
2. **Run** the `yo liferay-theme:themelet` command.   
5. **Type** `Widget Dropzone` to name your theme.  
6. **Press** _Enter_.  
7. **Press** _Enter_ to accept the default _themeId_.
	- This is how Liferay refers to your themelet internally.
8. **Choose** _7.2_ for the version.

## Make the Themelet Available
1. **Run** `cd widget-dropzone-themelet` in the _Command Prompt/Terminal_.
	- Different versions of the Liferay Theme Generator may or may not append "themelet" to the end of your themeId. Adjust accordingly.
2. **Type** `npm link`.  
3. **Press** _Enter_. 

## Add CSS to the Themelet 
1. **Drop** the `_custom.scss` file from `liferay/widget-dropzone/src/css` into the _Visual Studio Code_ editor.  
* **Type** `lfr` to view the available code snippets.
* **Choose** the `11-widget-drag-themelet` snippet.
* **Save** the file.  

## Deploy the Drag Indicator Themelet
1. **Run** `cd ../livingstone-fjord-theme` in the _Command Prompt/Terminal_.  
2. **Run** `npm run gulp extend`.  
3. **Choose** _Themelet_.   
4. **Choose** _Search globally installed npm modules_.  
5. **Press** _Space_ to select _widget-dropzone_.  
6. **Press** _Enter_ to apply the themelet.  
7. **Run** `npm run gulp deploy`.

The themelet has been added to the Livingstone Fjord Theme. Now when you drag and drop a widget on the page, you are given an indication of where the widget will appear on the page by a dotted outline and grey color.

<!--
## Generate a Second Themelet
1. **Go to** the _liferay_ folder you already created in the _Command Line/Terminal_.
	- Windows: _C:\liferay_
	- Mac/Linux: _~/liferay_
2. **Run** the `yo liferay-theme:themelet` command.   
3. **Type** `Product Menu Animation` to name your theme.  
4. **Press** _Enter_.  
5. **Press** _Enter_ to accept the default _themeId_.
	- This is how Liferay refers to your theme internally.
6. **Choose** _7.2_ for the version.
7. **Run** `cd product-menu-animation-themelet` in the _Command Prompt/Terminal_.  
8. **Run** `npm link`.  

## Add Animation CSS
1. **Drop** the `_custom.scss` file from `liferay/product-menu-animation-themelet/src/css` into the _Visual Studio Code_ editor.  
* **Type** `lfr` to view the available code snippets.
* **Choose** the `12-product-menu-themelet` snippet.
* **Save** the file.  

## Deploy the Menu Animation Themelet
1. **Run** `cd ../livingstone-fjord-theme` in the _Command Prompt/Terminal_.  
2. **Run** `gulp extend`.  
3. **Choose** _Themelet_.   
4. **Choose** _Search globally installed npm modules_.  
5. **Press** _Space_ to select _product-menu-animation-themelet_.  
6. **Press** _Enter_ to apply the themelet.  
7. **Run** `npm run gulp deploy`.  

<img src="../../images/menu-animation.png" style="max-width: 100%">
--> -->
