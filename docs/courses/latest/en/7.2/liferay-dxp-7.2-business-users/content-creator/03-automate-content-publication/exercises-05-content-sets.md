<h3 class="exercise">Exercises</h3>

## Creating Content Sets to Personalize Site Content

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Create a User Segment for Livingstone Life subscribers</li>
    <li>Create a Content Set for Livingstone Life using the Asset Publisher configuration</li>
    <li>Edit the Content Set from Site Administration to create a Personalized Variant</li>
    <li>View both variants in an Asset Publisher on the Livingstone Life site</li>
</ul>
</div>

#### Create User Segments for Livingstone Life Subscribers and Non-Subscribers
1. **Open** the _Menu_.
* **Click** the _Site Selector_ in the _Site Administration_ panel.
* **Click** the _My Sites_ tab.
* **Choose** _Livingstone Life_.
* **Go to** _`People → Segments`_ in the _Site Administration_ panel.
* **Click** the _Add_ button.
* **Type** `Livingstone Life Subscribers` as the _Name_ at the top of the page.
* **Click** and drag the _Site_ property underneath _Conditions_.
* **Click** _Select_ on the field to the right of the _Site_ property.
* **Choose** the _Livingstone Life_ site.
* **Click** _Save_ in the top right corner of the screen.

<img src="../images/livingstone-life-segment.png" style="max-height:25%;" />

#### Create a Content Set from the Livingstone Life Asset Publisher
1. **Click** _Go to Site_ in the _Site Administration_ panel.
* **Click** the _What's New_ tab.
* **Click** the _Options_ menu for the _Recently Published_ Asset Publisher.
* **Choose** _Configuration_.
* **Click** the _Create a content set from this configuration_ link at the bottom of the pop-up.
* **Type** `Livingstone Life's Latest Content` as the _Title_.
* **Click** _Save_.
* **Close** the pop-up.

<img src="../images/livingstone-life-content-set.png" style="max-height:25%;" />

#### Import Content for the Content Set
1. **Go to** _`Content & Data → Web Content`_ in the _Site Administration_ panel.
* **Click** the _Options_ icon.
* **Choose** _Export/Import_.
* **Click** the _Import_ tab.
* **Click** _Browse/Choose File_.
* **Choose** the `livingstone-life-content.lar` file found in your Course Module exercises folder.
* **Click** _Continue_.
* **Click** the _Import_ button.
* **Close** the pop-up.

<img src="../images/content-imported.png" style="max-height:20%;" />

#### Create a Personalized Variant of the Content Set
1. **Go to** _`Content & Data → Content Sets`_ in the _Site Administration_ panel.
* **Click** _Livingstone Life's Latest Content_.
* **Click** the _New Personalized Variant_ button.
* **Choose** the _Livingstone Life Subscriber_ User Segment.
* **Expand** the _Source_ section if it is not already expanded.
* **Choose** _Blogs Entry_ under Asset Entry Type.
* **Expand** the _Ordering_ section if it is not already expanded.
* **Choose** _Publish Date_ under _Order by_.
* **Click** _Save_.

<img src="../images/subscriber-set.png" style="max-height:32%;" />

#### Update the Anyone Variant for the Content Set
1. **Click** the _Anyone_ Personalized Variation.
* **Expand** the _Source_ section if it is not already expanded.
* **Choose** _Web Content Article_ under Asset Entry Type.
* **Choose** _4 Image Grid_ under Web Content Article Structures.
* **Click** _Save_.

<img src="../images/anyone-variant.png" style="max-height:30%;" />

#### View the Content Set via the Asset Publisher
1. **Click** _Go to Site_ in the _Site Administration_ panel.
* **Click** the _What's New_ tab.
* **Click** the _Options_ menu above the _Recently Published_ Asset Publisher.
* **Choose** _Configuration_.
* **Expand** the _Asset Selection_ section of the pop-up.
* **Choose** _Content Set_.
* **Click** the _Select Content Set_ drop-down.
* **Click** _Select_.
* **Choose** _Livingstone Life's Latest Content_.
* **Click** the _Display Settings_ tab.
* **Choose** _Full Content_ under the _Display Template_ drop-down.
* **Click** the _Show Asset Title_ slider from _Yes_ to _No_.
* **Click** _Save_.
* **Close** the pop-up.

<img src="../images/new-content-livingstone-life.png" style="max-height:28%;" />

---

#### Bonus Exercises
1. Create a Content Set that includes both the Blog and Web Content Article seen in the exercise above.
2. Create a User Segment for non-subscribers (Site does not equal Livingstone Life under Conditions) and create a Personalized Variation for the Content Set we made above using this new User Segment. This time make it so that only non-subscribers can see the blog.
