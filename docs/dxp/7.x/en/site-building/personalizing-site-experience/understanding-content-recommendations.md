# Understanding Content Recommendations

When users visit your website, they interact with your content and show different behaviors depending on their interests. Liferay DXP, in combination with Liferay Analytics Cloud, can analyze this browsing behavior and show relevant content for your users. This can be useful to promote certain products or content to your users that they are more likely to buy or consume. For example, when a user visits your online store and shows interest in a particular type of product, you can use content recommendations to show more content about this product or related product's promotions.

```important::
    Along with Liferay DXP, you must use Analytics Cloud to provide content recommendation to your users.
```

## How Content Recommendations Work

Configuring content recommendations is a two-step process. First, you track users' behavior and define topics of interest. This step requires Analytics Cloud connected to your DXP instance. Analytics Cloud processes users' behavior and define topics o interest. Second, you need to show users recommended content, based on their interests. This step requires creating a Dynamic Collection and showing this Collection in your website. You need a Collection to define the type of content you want to show to your users.

Content recommendations work automatically as long as the following elements are in place:

- The Liferay DXP instance is connected with Analytics Cloud and the site content is synchronized.
- You create a Dynamic Collection in Liferay DXP with different content.
- The *Enable Content Recommendations* option in the Dynamic Collection is enabled.
- You show your Dynamic Collection to your users.

```note:
In Liferay DXP 7.2 Collections are named Content Sets.
```

In Liferay DXP 7.2 you show the Content Sets using a Display Page with an Asset Publisher. In DXP 7.3+, in addition to the Asset Publisher, you can use the Collection Fragment and the Collection Pages.

To understand content recommendations in context, consider the following example. In your online Kitchenware store you offer a variety of kitchen-related products. A user visits your site and interacts with coffee accessories like coffee makers, coffee mugs, or coffee blenders. Based on the interaction of this user with this content, Analytics Cloud creates a topic of interest for the user ("coffee" in our example.) In DXP, you create a Dynamic Collection where you include information and promotions for different types of products, like cutlery, cookware, food storage, or coffee products. When the user access a Display Page with or a Collection Page including this Collection, the content related to coffee is prioritized over other content. The more the user visits and interacts with coffee-related content, the higher "coffee" is associated for this user individual profile, increasing the amount of content recommendations about coffee and decreasing the recommendations for other types of products.

```note::
    Analytics Cloud creates the topics of interest based on elements of your content like tile, description, categories, or tags. For titles and descriptions, only English language is considered at this time
```

## Content Recommendations and Segments.

Showing recommended content based on users' behavior is not the same as personalizing users' experience using Segment [cross-link needed]. When you use Segments, you start categorizing your users into groups and then recommending content for that particular group of users. In segment-based personalization you create a preconceived group of users sharing a common criteria. With content recommendations you don't create a group, content is automatically recommended to users based on their previous behavior on your site. With Segments, you target specific content to a specific group of users, while content recommendations can recommend users any different content based on their browsing behavior.

However, you can leverage the ability to customize Collections with Segments to provide content recommendations to specific groups of users. For example, you can combine a Collection where you offer free delivery options with a Segment that includes only USA visitors. In this case, XXXXX

## Related Information