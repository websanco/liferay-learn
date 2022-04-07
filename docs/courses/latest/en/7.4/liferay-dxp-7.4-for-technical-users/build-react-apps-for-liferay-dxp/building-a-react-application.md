# Add a React SPA to Your Platform

Coming Soon!

<!--

#### Exercise Goals

- Adapt a React SPA to Run on Liferay DXP
- Deploy the Application to Your Platform and Add it to a Page

 </div>

#### Adapt the Faria Financial Manager App Using the Liferay JS Toolkit
1. **Open** a Terminal window in the root directory of the _Faria Financial Manager_ project. 
	* For example, `C:\faria-financial-manager\faria-manager` as shown in the exercise video. 
2. **Run** the command `liferay adapt`.
	* This utilizes the adapt feature of the @liferay/cli tool to enable the React application to run on Liferay DXP.
3. **Type** `react` for _Under which category should your widget be listed?_.
4. **Run** the command `npm install`.
5. **Run** the command `npm run build:liferay`. 

#### Deploy the Faria Financial Management App to Your Platform
1. **Go to** the _build.liferay_ folder of the _Faria Financial Manager_ project.
2. **Copy** the `faria-manager-0.1.0.JAR` file.
3. **Paste** the `faria-manager-0.1.0.JAR` file in the `deploy` folder of your local instance of Liferay DXP.
	* To confirm a successful deployment of your application, look for `STARTED faria-manager_0.1.0` in the log of your Tomcat server. 

#### Create a Financial Management Page
1. **Go to** _`Site Builder > Pages`_ in the _Site Menu_.     
2. **Click** _Pages_ next to _Public Pages_ towards the top of the screen.
	* This enables the creation of Private Pages.
3. **Click** the _Add_ icon.  
4. **Choose** _Private Page_.
5. **Choose** _Blank_ for the Page Template.
5. **Type** `Financial Management` as the _Name_.
6. **Click** _Add_.

#### Add the Faria Financial Management Widget to the Financial Management Page
1. **Click** the _Fragments and Widgets_ (gray plus sign) icon in the far right toolbar.   
2. **Drag and Drop** a _Container_ layout element onto the page.
3. **Click** the _Widgets_ tab.  
4. **Click** the _React_ category.
5. **Drag and Drop** a _Faria Manager_ widget into the _Container_.

#### Test the Widget
1. **Sign in** as your administrative User.  
2. **Click** _Submit_.
	* You should see the Dashboard page of the Faria Financial Manager application with account information for your Administrator on display.


---
#### Bonus Exercise
1. Create a dashboard page for Mondego Group customers. Be sure to include the Faria Financial Manager App as well as areas for blog posts, comments, and a profile.

-->