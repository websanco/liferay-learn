---
description: 6 - Real World Application
title: Test the Service Through Remote API
order: 1
---

<h2 class="exercise">Optional Exercise</h2>

## Test the Service Through Remote API

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Find the test site ID</li>
		<li>Add an Assignment using browser console</li>
		<li>Get an Assignment through JSON API test page </li>
		<li>Delete an Assignment through JSON API test page </li>
	</ul>
</div>

#### Find the Test Site ID

We have to find an ID (`groupId`) for the site where we are going to create our test assignments. We'll be using the default guest site for this exercise:

1. **Sign in** to Liferay with your web browser.
2. **Open** the site administration panel in the *Product Menu*.
3. **Click** on  *Configuration → Settings*.
4. **Find** the *Site ID* value.

<img src="../images/find-the-site-id.png" style="max-height: 30%"/>

#### Add an Assignment Using Browser Console 

1. **Make sure** that you are logged in to the portal.
2. **Open** the JavaScript console of your favorite browser (Usually __Ctrl+Shift+J__ (Windows / Linux) or __Cmd+Opt+J__ (OSX))
	
	<br />
	<img src="../images/js-console.png" style="max-height: 85%"/>
	<br />
3. **Use** following JSON snippet in the Javascript console (__check that the site ID matches__):
	```json
	Liferay.Service('/gradebook.assignment/add-assignment', {
		groupId: 20123,
		titleMap: { 'en_US': 'How to make a birthday cake'} ,
		descriptionMap: { 'en_US': 'Design most delicious and beautiful birthday cake.'},
		dueDate: (new Date('2019-08-22')).getTime()
	}, function(obj) {
		console.log(obj);
	});
	```
	* You should get an assignment as JSON as a response:
	<img src="../images/add-assignment-response.png" style="max-height: 40%"/>
4. **Find** the `assignmentId` in the JSON response, that is required at the next step.

#### Get an Assignment through JSON API Test Page 

1. **Open** http://localhost:8080/api/jsonws in your web browser.
2. **Choose** *Gradebook* in the *Context Name* menu.

<img src="../images/select-gradebook-context.png" style="max-height: 100%"/>

On the menu, you'll see a list of methods we just added to our remote service. We'll now test our service with a browser's Javascript console.

3. **Click** *get-assignment* on the JSONWS API test page menu.
4. **Enter** the assignment ID.
5. **Click** *Invoke*.

#### Delete an Assignment through JSON API Test Page 

1. **Click** *delete-assignment* on the page menu.
2. **Enter** the assignment ID.
3. **Click** *Invoke*.

