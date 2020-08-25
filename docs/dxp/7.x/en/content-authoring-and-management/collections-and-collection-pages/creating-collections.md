# Creating Collections

You can create two different type of Collections in Liferay DXP, *Manual* or *Dynamic*.

By default, a new collection does not include a Personalized Variation and shows all the items that are part of the collection. However, you can create variations of these items using Segments. When you use Segment along with Collections, the items that are part of your collection don't change, but the items that are shown depend on the user segment. For more information, see [Customizing Collections Using Segments](./customizing-collections-using-segments.md).


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