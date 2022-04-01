## Shared Bundles and Moving Angular Applications to Liferay

If you are developing applications that share a lot of dependencies and use the same packages, it makes sense to bundle those dependencies to create lighter applications. Liferay understands this and meets front-end developers needs by providing shared bundle creation via the Liferay NPM Bundler.

Likewise, moving existing Angular applications to Liferay is also useful to front-end developers. You need to know which files need to be copied over and which new file structure needs to be created to make your application work in Liferay. In this section we will be covering both shared bundles and looking at how to change an existing Angular application to a Liferay portlet.

#### Livingstone Hotels & Resorts {#Livingstone}

Kaito Tanaka and his team of front-end developers want to minimize the size of their applications for easier deployment. They also want to use their existing Angular applications in Liferay. Kaito knows he can create a shared bundle with the Liferay Bundle Generator and needs to know what else he needs to do to share dependencies between portlets, which will make the individual portlet file sizes much smaller. He also knows that he can use the Liferay Bundle Generator to create a basic bundle for an Angular portlet in Liferay, but needs to know what exactly he needs to add or modify to get his team's Angular applications working in Liferay.

#### Using Shared Bundles with Angular Apps {#SharedBundles}

Shared Bundles determine packages used and shared among portlets so that multiple applications with the same dependencies do not need to have the packages they are dependent on built and deployed over and over again.

<div class="key-point">
Key Point: <br />
Shared Bundles can be created with the Liferay Bundle Generator and npm bundler in the same way that an Angular application can be created.
</div>

<br />

<figure>
	<img src="../images/lecture-images/npm-bundler-shared-packages.png" />
	<figcaption style="font-size: x-small">Fig.1 Using the Liferay Bundle Generator to create a shared bundle.</figcaption>
</figure>

<br/>

In order to use the shared bundle, you will need to copy all relevant (i.e. shared) dependencies from your application(s) `package.json` file to the shared bundle's `package.json`. Any changes made to the `.npmbundlerrc` will also need to be transferred over to the shared bundle as well. In your application(s) `.npmbundlerrc` you will need to exclude the application's dependencies found in the `package.json` with:

```
"exclude": {
    "*": true
}
```

Imports will also need to be added to the `.npmbundlerrc` to pull from the shared bundle and the `polyfill.ts`. When you build and deploy an application using a shared bundle, you will notice that the application is much lighter and deploys faster. We will walk through all of these steps in the next exercise.

#### Using Existing Angular Apps with Liferay {#ExistingAngular}

As mentioned in the last section, in order to run an existing Angular app on Liferay DXP the app should be available as a widget for using on site pages. You must use the Liferay Bundle Generator and Liferay npm Bundler to merge your files into a portlet bundle, adapt your routes and CSS, and deploy your bundle.

<div class="key-point">
Key Point: <br />
To move an Angular application to a Liferay portlet, start by generating a bundle with the Liferay Bundle Generator.
</div>

The next step in moving your Angular application to Liferay is to copy your app files, matching the types listed below, into your new project.

| File type | Destination | Comments |
| --------- | ----------- | -------- |
| HTML | `assets/app/` | Merge your main component with the existing `app.component.html`. |
| CSS  | `assets/css/` | Overwrite `styles.css`. |
| TypeScript and JavaScript | `src/app/` |  Merge with all files **except** `app.module.ts`---the root module merge is explained in a later step. |

You will also need to update your component class `templateUrl`s to use the `web-context` value declared in your project's `.npmbundlerrc`  file. Here's the format: 

        templateUrl: `/o/[web-context]/app/[template]`

Here's an example:

        templateUrl: '/o/my-angular-guestbook/app/add-entry/add-entry.component.html'

<div class="key-point">
Key Point: <br />
Your Angular application's HTML, CSS, and TypeScript/JavaScript files will need to be moved to the appropriate folders in your new Liferay bundle.
</div>

Your application will need to be updated to use portlet-level styling. Do this by importing all component CSS files through the main CSS file that the `package.json` file sets for your portlet (default is `styles.css`). Here's what the default setting looks like:

```json
"portlet": {
"com.liferay.portlet.header-portlet-css": "/css/styles.css",
...
}
```

The `selector` and `styleUrls` properties will need to be removed from your component classes for Liferay styling to work properly. 

<div class="key-point">
Key Point: <br />
When modifying your Angular application into a Liferay portlet, you need to import all component CSS files through the <code>styles.css</code> file in order to use portlet-level styling.
</div>

In your routing module's `@NgModule` decorator, the router option will need to be configured to `useHash: true`. This tells Angular to use client-side routing in the form of `.../#/[route]`, which prevents client-side parameters (i.e., anything after `#`) from being sent back to Liferay. 

For example, your routing module class `@NgModule` decorator might look like this:

```javascript
@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
```

Also in your routing module, view components will need to be exported for your root module to use, like so:

```javascript
export const routingComponents = [ViewComponent1, ViewComponent2]
```

Merge your root module with `src/app/app.module.ts`, configuring it to dynamically load components. 

<div class="note">
Note: Components must be loaded dynamically to attach to the portlet's DOM. The DOM is determined at run time when the portlet's page is rendered.
</div>

Import the `routingComponents` constant and the app routing module class from your app routing module. For example:

```javascript
import { AppRoutingModule, routingComponents } from './app-routing.module';
```

The base href that the router to use will need to be specified in the navigation URLs:

```javascript
import { APP_BASE_HREF } from '@angular/common';
...

@NgModule({
    ...
    providers: [{provide: APP_BASE_HREF, useValue: '/'}]
})
```

Declare the `routingComponents` constant in your `@NgModule` decorator. 

```javascript
@NgModule({
  declarations: [
      routingComponents,
      ...
  ],
  ...
})
```

Make sure your `@NgModule` `bootstrap` property has no components. All components are loaded dynamically using the `entryComponents` array property. The empty `ngDoBootstrap()` method nullifies the default bootstrap implementation. 

```javascript
@NgModule({
  ...
  entryComponents: [AppComponent],
    bootstrap: [],
    ...
})
export class AppModule {
    ngDoBootstrap() {}
    ...
}
```

You will want to comment out any HTTP client and in-memory data module code before deploying your bundle, as Liferay DXP handles the requests and data via the bundle's portlet.

<br />

<div class="key-point">
Key Point: <br />
A variety of changes need to be made to your root module <code>app.module.ts</code>. The changes should look something like this: 
</div>

<br />
<br />

```javascript
import { APP_BASE_HREF } from '@angular/common';
import { AppRoutingModule, routingComponents } from './app-routing.module';
// more imports ...

@NgModule({
  declarations: [
    AppComponent,
    routingComponents, 
    // more declarations ...
  ],
  imports: [
    AppRoutingModule,
    // more imports ...
  ],
  entryComponents: [AppComponent],
  providers: [{provide: APP_BASE_HREF, useValue: '/'}],
  bootstrap: [],
  // more properties ...
})
export class AppModule {
    ngDoBootstrap() {}

    // ...
}
```

Lastly, you will need to merge your app `package.json` file's `dependencies` and `devDependencies` into the bundle's `package.json` file before your new Angular Liferay portlet will be ready to deploy. In a two exercises, we will walk through all the changes discussed above to create a working Liferay portlet.

<div class="note">
Note: To work around build errors caused by the `rxjs` dependency, set the dependency to version <code>"~6.0.0"</code> like we did in the previous exercise.
<br />
Also note: If you're building on Windows, don't forget to set <code>"process-serially": true</code> in your bundle's <code>.npmbundlerrc</code> file.
</div>

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
	<li>Shared Bundles can be created with the _________________________ and _________________________.</li>
    <li>In order to turn your Angular application into a Liferay portlet, _________________________, _________________________, and _________________________/_________________________ files need to be moved to the appropriate folders.</li>
    <li>Import all CSS components through the _________________________ file when moving an Angular application to Liferay.</li>
</ul>
</div>