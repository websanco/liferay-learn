## Create a Layout Template

<div class="ahead">

#### Exercise Goals

* Create a Layout Template using the NPM Theme Generator to define the number of rows and columns
* Include a custom layout icon in the project
* Deploy the layout to the platform

</div>

#### Generate a Layout Template
1. **Go to** the _liferay_ folder in your _Command Line/Terminal_.
	* Windows: _C:\liferay_
	* Unix Systems: _[userhome]/liferay_
* **Run** the `yo liferay-theme:layout` command.
* **Type** in _Hotel Front Page_ for the layout when prompted for a name.
* **Press** the _Enter_ key to accept the default id.
* **Press** _Enter_ to accept the 7.3 version of Liferay.

#### Add the First Row with One Column
1. **Type** in _1_ to add 1 column to the first row.
2. **Press** _Enter_ to accept the 100% width for the row.

#### Choose the Second Row and Three Columns
1. **Choose** _Add row_ to add another row.
2. **Type** in _3_ to add 3 columns to the second row.
3. **Choose** the _4/12_ option for the first column.
4. **Choose** the _4/12_ option for the second column.
5. **Press** _Enter_ to accept the only available option for the last column.

#### Add a Third Row with Two Columns
1. **Choose** _Add row_ to add another row.
* **Type** in _2_ to add two columns to the row.
* **Choose** the _4/12_ option for the first column.
* **Choose** the _8/12_ option for the second column.

#### Add the Last Row with One Column
1. **Choose** _Add row_ to add another row.
2. **Type** in _1_ to add one column to the row.
3. **Press** _Enter_ to accept the 100% width for the row.
4. **Choose** the _Finish layout_ option.

<br />

<img src="images/layout.png" style="max-width:100%">

<div class="page"></div>

#### Point to the App Server
1. **Press** _Enter_ to accept the _Local App Server_ as your deployment strategy.
* **Type** in the path to your Tomcat server.
	* Windows: `C:\liferay\bundles\liferay-dxp-[version]\tomcat-[version]`
	* Mac/Unix: `~/liferay/bundles/liferay-dxp-[version]/tomcat-[version]`
* **Press** _Enter_ to accept the default URL (localhost:8080) for your site. 
* **Run** `cd hotel-front-page-layouttpl` in your _Command Line/Terminal_ to go to the layout template folder.

<div class="note">
Note: Liferay layouts created with the theme generator are bundled with Gulp. In order to run Gulp locally from the command line, we must add a script to the package.json file. Additionally, some setups run into issues running scripts with npm. If this is the case, try creating a .npmrc file in the root of your project and add the <code>ignore-scripts=false</code> configuration.
</div>

#### Deploy the Layout Template
1. **Go to** your _`hotel-front-page-layouttpl`_ folder.
* **Copy** the `hotel_front_page.png` file from the module exercise folder.
* **Paste** the file into the `hotel-front-page-layouttpl\docroot` folder.
	* This will replace the existing file in the folder.
* **Run** the `npm run gulp deploy` command in the Terminal or Command Line.

<div class="page"></div>

#### Verify Layout Deployment 
1. **Go to** _`localhost:8080`_ in your browser.
2. **Go to** `Site Builder > Pages` in the _Site Administration_ panel.
3. **Click** the _Add_ button at the top right.
* **Choose** _Public Page_.
* **Click** _Widget_.
* **Type** `Hotel Front Page` as the _Name_.
* **Click** _Add_.
* **Click** the _General_ tab.
* **Choose** the _Hotel Front Page_ layout near the bottom of the page.
* **Click** the _Save_ button at the bottom of the page.

<br />

<img src="images/hotel_layout_tpl.png" style="max-width:100%">

<br />

---

#### Bonus Exercises
1. Go to the new Hotel Front Page and add some widgets to see the layout in action.
2. Create a new custom layout for Hotel Room pages.
3. Add a Layout to a Theme and configure it to deploy with that theme.
