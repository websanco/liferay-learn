# Creating Collections

<<<<<<< HEAD
You can create *Manual* or *Dynamic* Collections. For more information, see [About Collections and Collection Pages](./about-collections-and-collection-pages.md).
=======
You can create two different type of Collections in Liferay DXP, *Manual* or *Dynamic*.

- Manual Collection

    In a Manual Collection, you select the items that are part of the Collection manually. After you include the items in a Manual Collection, the items that are part of the collection do not change.

    For example, you may create a Manual Collection "Success Stories" that includes several blog posts in your public website. After you create the Manual Collection, new blog post about "Success Stories" will not be part of the collection.

- Dynamic Collection

    In the Dynamic Collection, you define the type of items that you want to be part of the collection, but you define additional criteria for these items. The items that are part of the collection may change based on the criteria you select.

    For example, you create a Collection that includes all blog posts with the tag "kitchenware". When a new blog post including the "kitchenware" tag is published, it is automatically included in your Dynamic Collection.

By default, a new collection does not include a Personalized Variation and shows all the items that are part of the collection. However, you can create variations of these items using Segments. When you use Segment along with Collections, the items that are part of your collection don't change, but the items that are shown depend on the user segment. For more information, see [Customizing Collections Using Segments](./customizing-collections-using-segments.md).

>>>>>>> 1d4ec067... First draft for Creating Collections.

## Creating a Manual Collection

1. Go to 
1. Click on the New button () and select *Manual Selection*.
1. Add a *Title* form you Manual Collection and click *Save*.
1. In the *Item Type* drop-down menu, select the item type you want to include in the Collection.
    - If you want to include one item type:

        1. Select the type under *Single Item Type*.
        1. Optionally, if your *Item Type* includes a subtype, select this in *Item Subtype* drop-down menu.
        1. Click *Save

    - If you want to include more than one item type:

        1.  Select *Select Types* under *Multiple Item Types*.
        1. In the dual list box, add or remove the item types you want to include in your collection.

1. Click *Save*.
1. In the Collection Items screen, click *Select* and select the item type to include.
1. Select the items you want to include in the Manual Collection.
1. Click *Add*.
1. If your manual collection includes more than one itme type, click select and include other items as necessary.

## Creating a Dynamic Collection

1. 
1. Click on the New button () and select *Dynamic Selection*.
1. Add a *Title* form you Manual Collection and click *Save*.
1. Configure the criteria for your Dynamic Collection, based on *Scope*, *Filter*, *Content Recommendation*, or *Ordering*

    - *Scope* - Define the source of items in your Dynamic Collection. By default, the current site.
    - *Filter* - Configure the rules for the items in the Dynamic Collections. For example, you may want the collection to include only items with the "Promotion" keyword.
    
        ```tip::
           You can add multiple rules to your Filter clicking on the Add button. The final content in the Dynamic Collection is the result of adding all the rules. 
        ```

    - *Content Recommendation* - Enable this option to display content based on user behavior. For information about *Content Recommendation*, see [Configuring Content Recommendations](../XXXXXXXXXX/configuring-content-recommendations.md).
    - *Ordering* - Configure the order criteria for the items in the Dynamic Collection. Items in the collection appear in the order you define here.

## Creating a Collection from an Asset Publisher 

You can create a new Collection from an Asset Publisher widget.

![You can transform an Asset Publisher widget into a Collection](./creating-collections/images/06.png)

1. Go to *Site Administration* &rarr; *Site Builder* &rarr; *Pages*.
1. Select the Action button next to the Page that contains the Asset Publisher and select *Edit*.
1. Click the *Options* button in the Asset Publisher Widget and select *Configuration*.

    ![Configure the Asset Publisher Widget](./creating-collections/images/07.png)

1. In the *Asset Publisher - Configuration* dialog, click the *Setup* tab and *Asset Selection* sub-tab.
1. Scroll down and click on the *Create a Collection from this Configuration* link.

    ![Configure the Asset Publisher Widget](./creating-collections/images/07.png)

1. Enter the *Title* for your Collection and click *Save*.
1. Close the *Asset Publisher - Configuration* dialog.
1. Find your new Collection in *Site Administration* &rarr; *Site Builder* &rarr; *Collections*.

## Related Information

* [About Collections and Collection Pages](./about-collections-and-collection-pages.md)
* [Displaying Collections and Collection Pages](./displaying-collections-and-collection-pages.md)
* [Displaying Collections and Collection Pages](./displaying-collections-and-collection-pages.md)
