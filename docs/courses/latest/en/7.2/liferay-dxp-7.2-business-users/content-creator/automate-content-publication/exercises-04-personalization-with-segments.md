<h2 class="exercise">Exercises</h2>

## Personalizing Content with User Segments

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Create a User Segment for Members of the Livingstone Rewards Group</li>
    <li>Create a Content Page for the Livingstone Rewards Group</li>
    <li>Create a new Experience for the Rewards Group Users on the new Content Page</li>
</ul>
</div>

#### Create the Livingstone Rewards Group
1. **Open** the _Menu_.
* **Go to** _`Users → User Groups`_ in the _Control Panel_.
* **Click** the _Add_ button.
* **Type** `Livingstone Rewards` as the _Name_.
* **Type** `Users in this group signed up for Livingstone's loyalty program` as the _Description_.
* **Click** _Save_.

<img src="../images/rewards-created.png" style="max-height:27%;" />

#### Create a User Segment for Rewards Group Users
1. **Click** the _Site Selector_ in the _Site Administration_ panel.
* **Choose** _Livingstone Hotels & Resorts_.
* **Go to** _`People → Segments`_ in the _Site Administration_ panel.
* **Click** the _Add_ button.
* **Type** `Livingstone Rewards` as the _Name_ at the top of the page.
* **Click** and drag the _User Group_ property underneath _Conditions_.
	* This is found by scrolling down under the User drop-down.
* **Click** _Select_ on the field to the right of the _User Group_ property.
* **Choose** the _Livingstone Rewards_ group.
* **Click** _Save_ in the top right corner of the screen.

<img src="../images/rewards-segment.png" style="max-height:26%;" />

#### Import Page Fragments for Livingstone Rewards
1. **Go to** _`Site Builder → Page Fragments`_ in the _Site Administration_ panel.
* **Click** the _Options_ icon next to _Collections_.
* **Choose** _Import_.
* **Click** _Browse/Choose File_.
* **Choose** the `rewards-page.zip` file located in your Course Module exercises folder.
* **Click** _Import_.
* **Close** the pop-up.

<img src="../images/rewards-fragments.png" style="max-height:25%;" />

#### Create a Content Page for Livingstone Rewards
1. **Go to** _`Site Builder → Pages`_ in the _Site Administration_ panel.
* **Click** the _Add_ button.
* **Choose** _Content Page_.
* **Type** `Livingstone Rewards`.
* **Click** _Add_.
* **Expand** the _Rewards-Page_ Fragments.
* **Click** to add the _01-Join-Rewards_ fragment.
* **Click** to add the _02-Join-Now_ fragment.
* **Click** _Publish_.

<img src="../images/content-page-created.png" style="max-height:25%;" />

#### Add a New Experience for Livingstone Rewards Members
1. **Click** the _Options_ icon next to the Livingstone Rewards page we just created.
* **Choose** _Edit_.
* **Click** the _Default_ drop-down at the top of the page.
* **Click** the _New Experience_ button.
* **Type** `Rewards Members Experience`.
	- Make sure the chosen _Audience_ is Livingstone Rewards.
	- If you don't see the pop-up, make sure the Fjord theme is turned off and the Classic theme is selected.
* **Click** _Save_.
* **Click** the heading of the _01-Join-Rewards_ fragment.
* **Click** the _Edit_ icon.
	- This is the icon that looks like a pencil.
* **Replace** the existing text with `Welcome to Livingstone Rewards!`.
* **Click** the button to remove the _02-Join-Now_ fragment at the bottom of the page.
* **Expand** the _Livingstone Rewards Fragments_ section.
* **Click** to add the _03-Rewards-Active_ fragment.
* **Click** _Publish_.

<img src="../images/rewards-experience.png" style="max-height:50%;" />

#### View the Livingstone Rewards Page
1. **Open** the _Menu_.
* **Click** _Go to Site_ in the _Site Administration_ panel.
* **Click** _Livingstone Rewards_ in the navigation menu.
	- You should see the default Experience version of the page.

<img src="../images/view-rewards-page.png" style="max-height:33%;" />

#### View the Rewards Members Experience Version of the Page
1. **Go to** _`Users → User Groups`_ in the _Control Panel_.
* **Click** the _Livingstone Rewards_ group.
* **Click** the _Add_ button.
* **Check** the box next to _Josiah Copeland_.
* **Click** _Add_.
* **Expand** the _Site Administration_ panel in the _Menu_.
* **Click** _Go to Site_.
* **Click** _Livingstone Rewards_ in the navigation menu.
	- You should see the Rewards Members Experience version of the page.

<img src="../images/rewards-experience-view.png" style="max-height:33%;" />

<br />

---

#### Bonus Exercises
1. Create a User Segment for Business Rewards members. You will need to create a new User Group for Business Rewards Members. In the Segment Editor, ensure that Livingstone Rewards group members are not included as a Condition of the Business Rewards members Segment.
2. Create a new Experience for the Livingstone Rewards Content Page. This Experience should be targeted to the Business Rewards Members Segment and be composed of existing fragments that are edited to include exclusive deals for business travelers.
