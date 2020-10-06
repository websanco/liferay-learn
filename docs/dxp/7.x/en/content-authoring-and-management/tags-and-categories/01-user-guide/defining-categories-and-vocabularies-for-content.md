# Defining Categories and Vocabularies for Content

Using Categories, you can group similar assets and better organize your site content. Along with Tags, Categories help you and your users to find relevant information through search or navigation. For more information about Tags and Categories in Liferay DXP and the different usage scenarios, see [Organizing Content with Categories and Tags](organizing-content-with-categories-and-tags.md).

You can add properties to categories, to provide additional information about the category. You can think of category properties as tags for you categories.

## Defining Vocabularies

1. Open the Product Menu and, under the Site Menu, go to *Categorization* &rarr; *Categories*.
1. Click the *New Vocabulary* (![New Vocabulary](../../../images/icon-plus.png)) button.
1. Enter a name for the Vocabulary and, optionally, a description.
1. Complete the rest of the Category options:

    - *Allow Multiple Categories*: When this option is enabled, you can apply more than one category from the vocabulary to your asset. When this option is disabled, you can only apply one category from the vocabulary.
    - *Visibility*: See [Vocabularies Visibility](##vocabularies-visibility) for more information

        ```important::
           You cannot change the Visibility after saving the Vocabulary.
        ```

    - *Associated Asset Type*: Select the type of assets where you can apply a category from this vocabulary. Set *Required* on if you want this type of asset to always have a category.
    
        ```tip::
           Click the *Add* (![Add](../../../images/icon-plus.png)) button under the *Associated Asset Type* section to include more than one asset type.
        ```
        ![Define the category options for multiple asset types](./defining-categories-and-vocabularies-for-content/images/02.png)
        
1. Click *Save*.
1. To edit an existing Vocabulary, click the *Actions* (![Actions](../../../images/icon-actions.png)) menu next to the Vocabulary name and select *Edit*.

    ![Edit an existing Vocabulary using the Actions menu](./defining-categories-and-vocabularies-for-content/images/08.gif)

## Defining Categories

To define and manage Categories, open the Product Menu and, under the Site Menu, go to *Categorization* &rarr; *Categories*. You can also create new Categories from the Categorization Section in the Content Editor, using the *Select* button.

![Add new Categories from the Categorization section in the Content Editor](./defining-categories-and-vocabularies-for-content/images/04.png)

### Creating Categories

1. Open the Product Menu and, under the Site Menu, go to *Categorization* &rarr; *Categories*.
1. Under the *Vocabularies* list, click the Vocabulary you want to modify.
1. Click *Add Category* (![Add Category](../../../images/icon-add.png)).
1. Enter a name for the Category and, optionally, a description.
1. Click *Save* or *Save and Add a New One* to add more Categories.
1. To edit an existing Category, click the *Actions* (![Actions](../../../images/icon-actions.png)) menu

### Creating Subcategories

You can create a nested hierarchy of Categories and Subcategories:

1. Open the Product Menu and, under the Site Menu, go to *Categorization* &rarr; *Categories*.
1. Under the *Vocabularies* list, click the Vocabulary you want to modify.
1. Click the Category name where you want to create the new Subcategory.
1. In the *Add New Subcategory* screen, enter the Subcategory's name and, optionally, a description.
1. Click *Save* or *Save and Add a New One* to add more Subcategories.

### Moving Categories

You can move a Category to a different Vocabulary, or as a Subcategory of the same Vocabulary.

1. Open the Product Menu and, under the Site Menu, go to *Categorization* &rarr; *Categories*.
1. Click the *Actions* (![Actions](../../../images/icon-actions.png)) menu and select *Move*.

    ![Use the Move option to organize Categories](./defining-categories-and-vocabularies-for-content/images/03.png)

1. Select the *Vocabulary* and *Category* where you want to move the Category.

    ```note::
       You can only move a Category into a Vocabulary of the same [Visibility type](#vocabulary-visibility).
    ```

### Editing Additional Properties
