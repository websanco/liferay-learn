<h2 class="exercise">Exercises</h2>

## Customize the Site Content Reviewer Role

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Modify the <i>Default Site Content Reviewer</i> role to include the following:</li>
    <ul>
        <li>Edit and View permissions for Web Content</li>
        <li>Edit and View permissions for Blogs</li>
    </ul>
</ul>
</div>

#### Go to the Site Roles Tab in the Roles Section of the Control Panel
1. **Sign in** to Liferay if you're not already logged in.
    * If you followed along with Module 2, you will need to sign out as Jason Murray and sign in as your administrative User.
2. **Open** the _Menu_.
3. **Go to** the _`Control Panel → User → Roles`_.
4. **Click** the _Site Roles_ tab.

<img src="../images/site-roles-tab.png" style="max-height: 100%">

#### Add Web Content Editing and View Permissions
1. **Click** on the _Site Content Reviewer_ Role.  
2. **Click** the _Define Permissions_ tab.  
3. **Open** the _Site Administration_ drop-down.
4. **Open** the _Content & Data_ drop-down.
5. **Click** the _Web Content_ option.
6. **Check** _Update_ under _Web Content Article_.
7. **Check** _View_ under _Web Content Article_.
8. **Click** _Save_.

<img src="../images/web-content-permissions.png" style="max-width: 65%">

#### Add Blog Editing and View Permissions
1. **Open** the _Site Administration_ drop-down.
2. **Open** the _Content & Data_ drop-down.
3. **Click** the _Blogs_ option.
4. **Check** _Update_ under _Blogs Entry_.
5. **Check** _View_ under _Blogs Entry_.
6. **Click** _Save_.

<img src="../images/blog-permissions.png" style="max-width: 80%">

---

#### Bonus Exercises:
1. Create a new User in _Users and Organizations_ and make them a member of the main site.
2. Grant the new User the _Site Content Reviewer_ role.
3. Go to the main Site _Configuration_ section and turn workflow on for Web Content.
4. Create a new Web Content Article in the main site as the Admin User.
5. Log in as the new User to check for a notification from the workflow.
6. Assign it to yourself and edit the Web Content.
