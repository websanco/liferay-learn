# Optional Exercise: Test the Service Through Remote API

Coming Soon!

<!--

Note to the editor: I had to change some snippet instructions to remove the !--, #--, or -- in order to comment out the entire file. Make sure to review and ensure the snippets are correct.

#### Exercise Goals

- Find the test site ID
- Add an Assignment using browser console
- Get an Assignment through JSON API test page
- Delete an Assignment through JSON API test page

<div class="note">
Note: This exercise is optional. It is not written as step-by-step exercises so that you can explore and experiment more.
</div>

</div>
	
#### Find the Test Site ID

We have to find an ID (`groupId`) for the site where we are going to create our test assignments. We will use the default guest site for this exercise. Sign in to your Liferay DXP instance with your web browser at localhost:8080. Open the site administration panel in the *Product Menu*. Find the *Site ID* value in the Configuration settings.

#### Add an Assignment Using Browser Console 

Make sure that you are logged in to your instance. Open the JavaScript console of your favorite browser (Usually __Ctrl+Shift+J__ (Windows / Linux) or __Cmd+Opt+J__ (OSX)). Use the following JSON snippet in the Javascript console (__check that the site ID matches__).

```json
Liferay.Service('/gradebook.assignment/add-assignment', {
	groupId: 20123,
	titleMap: { 'en_US': 'How to make a birthday cake'} ,
	descriptionMap: { 'en_US': 'Design most delicious and beautiful birthday cake.'},
	dueDate: (new Date('2019-08-22')).getTime()
```

< pagebreak for pdf book >

```json
}, function(obj) {
	console.log(obj);
});
```

Make sure to find the `assignmentId` in the JSON response, that is a requirement for the JSON API test page.

#### Get an Assignment Through the JSON API Test Page 

Go to http://localhost:8080/api/jsonws in your web browser and choose *Gradebook* in the *Context Name* menu. In the menu, you'll see a list of methods we just added to our remote service. We'll now test our service with a browser's Javascript console. Click *get-assignment* and enter the assignment ID, then click **invoke**.

#### Delete an Assignment Through the JSON API Test Page 

Now try deleting an assignment. Click *delete-assignment* on the page menu. Enter the assignment ID and click *invoke*.

And that's it. Try it again with another assignment if you'd like.

-->