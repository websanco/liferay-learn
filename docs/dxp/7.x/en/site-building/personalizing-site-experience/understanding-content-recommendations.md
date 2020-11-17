# Understanding Content Recommendations

When users visit your website, they interact with your content and show different behaviors depending on their interests. Liferay DXP, in combination with Liferay Analytics Cloud, can analyze this browsing behavior and show relevant content for your users. This can be useful to promote certain products or content that your users are more likely to buy or consume. For example, when a user visits your online store and shows interest in a particular type of product, you can use content recommendations to show the user content about similar products or related product's promotions.

```important::
    You must connect Analytics Cloud to your Liferay DXP instance to provide content recommendations to your users.
```

## How Content Recommendations Work

Configuring content recommendations is a two-step process. First, you track users' behavior and define topics of interest. This step requires Analytics Cloud connected to your DXP instance. Using previous user's behavior in the site, Analytics Cloud defines and ranks topics of interest, and associates these topics of interests to the user identifier in the site. Second, you need to show users recommended content, based on their interests. This step requires creating a Dynamic Collection and showing this Collection in your website. You need a Collection to define the type of content you want to show to your users.

Analytics Cloud creates topics of interest based on different content elements like tile, description, categories, or tags. Adding Categories and Tags to your content is not strictly required to generate content recommendations. However, when you categorize and tag your content, Analytics Cloud has more information to define topics of interest and can provide better recommendations. Besides, using Tags and Categories for your content provides more control about the content you want to recommend.

```note::
    For titles and descriptions, only English language is considered at this time.
```

Content recommendations work automatically as long as the following elements are in place:

1. Analytics Cloud is connected to your Liferay DXP instance and the Site content is synchronized.
2. You create a Dynamic Collection in Liferay DXP including different content.
3. You display the Dynamic Collection for your site using an Asset Publisher, a Collection Display Fragment, or a Collection Page.

```note:
Collections are named Content Sets in Liferay DXP 7.2.
```

In Liferay DXP 7.2, you show the Content Sets using a Display Page with an Asset Publisher. In DXP 7.3+, in addition to the Asset Publisher, you can show the Collection's content using the Collection Display Fragment and the Collection Pages.

To understand content recommendations in context, consider the following example. In your online Kitchenware store you offer a variety of kitchen-related products. A user visits your site and interacts with coffee accessories like coffee makers, coffee mugs, or coffee blenders. Based on the user interaction, Analytics Cloud generates a topic of interest for the user ("coffee", in our example.) In DXP, you create a Dynamic Collection where you include information and promotions for different types of products, like cutlery, cookware, food storage, and coffee accessories. When the same user accesses a page displaying this Collection, you Kitchenware site prioritizes coffee-related products and promotions over other types of products, showing this content more prominently to this user. The more the user visits and interacts with coffee-related content, the higher "coffee" is associated for this user individual profile, increasing the amount of content recommendations about coffee and decreasing the recommendations for other products.

## Content Recommendations and Segments

Showing recommended content based on users' behavior is not the same as personalizing users' experience using Segments [cross-link needed]. When you use Segments, you start categorizing your users into groups and then recommending content for that particular group of users. In segment-based personalization you target specific content to a specific group of users. With content recommendations you don't need to define a group because content is automatically recommended to users based on their previous behavior on your site.

However, you can leverage the ability to customize Collections with Segments to provide content recommendations to specific groups of users. For example, you can combine the Dynamic Collection for content recommendations with a Segment that includes only visitors from Germany. In this case, only visitors to your site from Germany will receive content recommendations from this Collection.

## Related Information