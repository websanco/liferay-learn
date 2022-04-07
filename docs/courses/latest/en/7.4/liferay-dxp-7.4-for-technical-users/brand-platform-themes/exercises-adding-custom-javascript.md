# Add Custom JavaScript to a Theme

Coming Soon!

<!--

#### Exercise Goals

* Add JavaScript to the theme
    * Add DOM variables
    * Add the Sign-in Modal
    * Add the Click event for closing the nav

</div>

#### Add Main.js to the Theme
1. **Copy** the `main.js` from the _`exercise-src\js`_ folder. 
3. **Paste** it into your `livingstone-fjord-theme/src/js` folder.

#### Set DOM Variables and the Sign-In Widget
1. **Drop** the `main.js` from _`livingstone-fjord-theme\src\js`_ into the _Visual Studio Code_ editor. 
2. **Click** to highlight the `// Insert snippet 01-variables-and-sign-in here` comment.
3. **Type** `lfr` to view the available code snippets.
4. **Choose** the `01-variables-and-sign-in` snippet.
5. **Save** the file. 
  * Alternatively, you can type and save the following:

```JavaScript
    var BODY = A.getBody();
    var signIn = A.one('#sign-in');

    if (signIn && signIn.getData('redirect') !== 'true') {
        signIn.plug(Liferay.SignInModal);
    }

    var fullScreenToggleIcon = A.one('.full-screen-navigation #banner .navbar-toggler');
```

#### Add the Click Event for Closing the Navigation
1. **Click** to highlight the `// Insert snippet 02-close-nav-click here` comment.
2. **Type** `lfr` to view the available code snippets.
3. **Choose** the `02-close-nav-click` snippet.
4. **Save** the file. 
  * Alternatively, you can type and save the following:

```JavaScript
      if (fullScreenToggleIcon) {
        fullScreenToggleIcon.on(
          'click',
          function(event) {
            BODY.toggleClass('main-nav-opened', event.currentTarget.hasClass('collapsed'));
          }
        );
      }
```

#### Deploy the Theme to See the JavaScript Changes
1. **Run** `npm run deploy` in the _Command Line_ or _Terminal_. 
  * If you're already running gulp watch, this isn't needed.
* **Open** your browser.
  * You should already have a tab open at localhost:8080
* **Click** to _Refresh_ the page after the theme changes have finished deploying.

-->