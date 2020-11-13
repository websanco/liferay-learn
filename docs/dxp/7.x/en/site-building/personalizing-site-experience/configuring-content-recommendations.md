# Configuring Content Recommendations

[Describe the process Content Recommendations are built]

Liferay Analytics Cloud generates areas of interest for your users, based no the user behavior in your Liferay DXP site. The overall process occurs as follows:


Users browse your Liferay DXP site and interact with your content. 
Analytics Cloud automatically creates topics of interest for the user, based on user behavior.
Using.

As users visit different types of content, Analytics Cloud creates different topics of interest and assign a ranking to the. 

You create web content that includes different types of topics. To show this content to users, you use a Collections

These topics of interest get associated to the user identifier in the site.
Create web content for your site, and tag and categorize this content.

(((Using a Display Page Template, we create content in our site.
When our content is)))

The more an Individual visits pages and assets that contain a topic, the higher the topic’s ranking is for that Individual’s Segments.

[Describe the components in the Content Recommendations equation]



[Describe how to configure each component]

## Configure Analytics Cloud to Work along with Liferay DXP

Analytics Cloud processes the content users interact with creating topics of interest for these users, and it's a requirement to provide content recommendations.

[cross-link here]

## Create a Dynamic Collection

[cross-link the task here]

To show users content based on the interests provided by Analytics Cloud, you must use a Dynamic Collection. The  Collection is the component that connects the topics of interest with the content recommendations in your site. Using this Collection we determine what type of items we want to show and, optionally, to what audiences.

1. Create the Dynamic Collection, and consider the following:
    1. You don't need to specify a Filter.
    1. Enable the *Content Recommendations* option. 

Note: If the *Content Recommendations* option is disabled, the Collection does not consider...    

1. Optionally, configure the Segment this Collection applies to by creating a Personalized Variation.

The filter in the Dynamic Collection works in addition to the *Content Recommendations* option. For example, if you use the "promotion" tag as a filter, a user will only see recommended content with the "promotion" tag. If you remove the filter, the user will see all the recommended content.

If you want to target content recommendations to a specific group of users, create a Personlized Variation.

## Configure the Display Page

## Display the Collection using the Asset Publisher

dkfjkdjf
dljfdklfj
ldjfdlkfj

## Display the Collection using a Collection Page or Collection Display Fragment

> Liferay DXP 7.3+

[[## Categorize and Tag you Content]]

Analytics Cloud creates the topics of interest based on different content elements, including Categories and Tags. Although Categories and Tags are not a requirement to create topics of interest, your content recommendations your users receive based on their behavior.

[[## Map the Web Content to the Display Page]]

## Display the Collection

To display recommended content to users based on users' behaviour you use a Dynamic Collection. You can show a Collection using a Collection Page, a Collection Display Fragment, or an Asset Publisher. For more inforation, see


Consider the following example. In your Kitchenware store webpage, you want to include a section in the front page that shows offers to users based on their browsing behaviour. Your site contains different web content, and part of this content are promotional items that you describe using the "promotion" tag. Your site contains promotions for different types of products, like coffee makers, glassware, cutlery, and others. 

you want to include a section in the front page that shows offers to users based on their browsing behaviour.

You create a Dynamic Collection using the "promotion" tag as a filter, and you display the collectio using a Collection Display Fragment or Collection Page on your website. Based on user behaviour, your website visitor receive content recommendation. In this example, a user that browse for coffee-related product will see promotions for coffee products in your store.

## Analyzing Content Recommendations

Analytics Cloud generates the topics of interest for users, based on users' behaviour in your site. To analyze these interests, 

## Related Information

- [[Categories and Tags]]()
- [[Collections]]()
- [[Individual Profiles]](https://learn.liferay.com/analytics-cloud/latest/en/individuals-and-segments/individual-profiles/individual-profiles.html)
- [[Interests]](https://learn.liferay.com/analytics-cloud/latest/en/individuals-and-segments/interests.html)