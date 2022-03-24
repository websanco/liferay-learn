# Exercise 4: Manage Teams and Community Permissions 

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/3VPHze0KvHY

## Exercise Goals 

* Create a Site Team for the Mondego Publications Site 
* Modify Permissions on a Resource on the Site 

## Go to the Mondego Publications Site 

1. **Open** the _Site Menu_. 
2. **Click** the _Site Selector_ icon to view available Sites. 
3. **Click** on the _My Sites_ tab. 
	* If you've followed along with the Main and Bonus Exercises from the _Build New Sites with Liferay DXP_ module, you should have seven Sites, five Mondego Sites and two Organization Sites. 
4. **Click** on the _4 Child Sites_ link. 
5. **Choose** the _Mondego Publications_ Site. 

## Create a Web Moderators Site Team 

1. **Open** the _Site Menu_. 
2. **Go to** `People` &rarr; `Teams`. 
3. **Click** the _Add_ icon at the top right. 
4. **Type** `Web Moderators` for the _Name_. 
5. **Type** `A team for moderating message boards, blog comments, and community engagement` for the _Description_. 
6. **Click** _Save_. 

## Add Ayokunle Idowu to the Mondego Publications Site 

1. **Click** _Web Moderators_ to open the new team. 
2. **Click** the _Add_ icon at the top right. 
	- The only available User should be your Administrator. We need to grant Users membership before we can add them to the Web Moderators Team. 
3. **Close** the pop-up. 
4. **Open** the _Control Panel_ in the _Global Menu_. 
5. **Go to** `Users` &rarr; `Users and Organizations`. 
6. **Click** _Ayokunle Idowu_. 
7. **Go to** _Memberships_ in the left panel. 
8. **Click** _Select_ next to _Sites_. 
9. **Choose** _Mondego Publications_. 
10. **Click** _Save_ at the bottom of the page. 

## Add Ayokunle Idowu to the Web Moderators Site Team 

1. **Open** the _Global Menu_. 
2. **Select** _Mondego Publications_ from the _Sites_ tab. 
3. **Open** the _Site Menu_. 
4. **Go to** `People` &rarr; `Teams`. 
5. **Click** _Web Moderators_ to open the team. 
6. **Click** the _Add_ icon at the top right. 
7. **Check** the box next to _Ayokunle Idowu_. 
8. **Click** _Add_. 

## Create a New Community Page 

1. **Open** _Site Builder_ in the _Site Administration_ panel. 
2. **Click** _Pages_. 
3. **Click** _Add_ at the top right. 
4. **Choose** _Public Page_. 
5. **Click** _Blank_ to add a Content Page. 
6. **Type** `Community` for the _Name_. 
7. **Click** _Add_. 

## Add a Message Board Widget to the New Page 

1. **Open** the _Fragments and Widgets_ menu (the plus icon) at the right. 
2. **Go to** `Widgets` &rarr; `Collaboration`. 
3. **Drop** a _Message Boards_ widget onto the page. 
4. **Click** the _Publish_ button at the top right. 
	- You should see two Public Pages: _Blog_ and _Community_. 

## Grant the Web Moderator Team Permissions to the Message Board Widget 

1. **Click** on the _Community_ page. 
2. **Click** on the _Options_ menu next to the _New Thread_ button. 
3. **Click** _Permissions_. 
4. **Check** every permission except for _Permissions_ for _Web Moderators_. 
5. **Click** _Save_. 
6. **Close** the pop-up. 

## Bonus Exercises 

1. Grant Bethany Park and Corrie Alders membership to the Mondego Publications Site. Add them to the Web Moderators Team. Sign out of your administrator account and sign in as either Bethany or Corrie. 
2. Create at least one additional User and add them to the Web Moderators Team. Add a new Widget to the Community Page and grant permissions to the team. 