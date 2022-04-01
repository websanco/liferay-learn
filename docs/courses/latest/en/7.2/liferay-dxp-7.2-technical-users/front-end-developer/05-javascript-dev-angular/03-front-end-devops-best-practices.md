## Front-End Development, DevOps, and Best Practices

Liferay is compatible with as many tools as possible, so front-end developers can work efficiently with the tools they know best.

Using Angular to build applications and widgets in Liferay saves developers time and gives them another tool they can use to build diverse and powerful platforms.

A key part of any product's lifecycle is deploying to production. System administrators and operations engineers play a vital role in this process. Traditionally, however, there have been significant barriers between the developers who create software and the administrators who deploy and manage that software's code in a production environment. Not uncommon is a constant ideological struggle between developers looking to increase development speed and system administrators aiming for operational stability. A separation of responsibilities and seemingly opposed goals lead to issues that negatively impact product, teams, and, ultimately, the business. How many times have you heard someone say, _"That's a development issue"_ or _"Sorry, that's an operations problem"_? Front-end development in Liferay should avoid these issues by implementing a sharing culture and using a common codebase to develop themes, templates, fragments, and applications, together.

#### Livingstone Hotels & Resorts {#Livingstone}

Kaito's front-end team at Livingstone Hotels & Resorts needs to find a way to decrease the time to market for their deliverables. Their content, sites, and apps need to be updated quickly in order to make the most impact. After a series of evaluations and input from members of both the development and operations teams, management has decided that these teams can be taking steps to make better use of their time and more effectively deliver their products. 

The Livingstone team is prepared to make the move to restructure their organizational culture using a DevOps philosophy and methodology. They'll be aiming for improved inter-team dynamics and more automated processes and tools in order to reduce friction and decrease time to market. Specifically, they will be looking at creating a common codebase to be used for all of the front-end development in the platform.

### DevOps in Front-End Developments {#DevOps}

The DevOps philosophy aims to tear down the silos that often isolate development and operations teams by increasing communication and collaboration between these teams. This ultimately leads to the creation of products that are more stable, higher quality, and delivered more quickly. DevOps is a combination of _Development_ and _Operations_. The DevOps movement has its roots in Agile methodologies. It is _primarily_ a culture and set of values that has subsequently developed into a set of processes, practical tools, and practices for getting work done.

<div class="key-point">
Key Point:<br />
DevOps encapsulates both a philosophy and a collection of best practices and tools.
</div>

According to Damon Edwards and John Willis, two of the leaders in the DevOps movement, there are four core values that define DevOps: 

1. **Culture**: DevOps is not simply a job title or a set of tools. In order to make lasting impact, a complete shift is required in the way teams and businesses think about working together and how to get work done most effectively.

2. **Automation**: Automation plays a huge part in the DevOps methodology. People will always make mistakes, but automation is one way to improve quality by increasing predictability. Just as importantly, automating the repetitive tasks that take up a resource's precious time allows for freeing up team members to do more strategic work. For example, operations team members can work more closely with developers to understand the product and vice versa, so that both sides have a better picture of the entire lifecycle. This can influence how work is done and lead to better and more stable products. 

3. **Measurement**: Once a product is deployed to production, it is constantly being measured and monitored. Operations team members keep track of their applications, whether or not they are working as expected, how they are being used, and how they can make improvements. All of this data is organized and analyzed to make products better and make changes more quickly.

4. **Sharing**: Perhaps the most important ingredient for a successful DevOps team is making sure that feedback and communication are at the forefront of the team's values. Teams must constantly be communicating, both internally, with other teams, and with stakeholders. Team members should always be willing to share their expertise and wisdom, as this can only benefit everyone around them. Improved communication often leads to more opportunities for collaboration, a better understanding of the product and processes, and healthier teams overall.

DevOps takes these ideas and philosophies about how to do work and pairs them with processes and tools in order to implement this vision. In practical terms for the front-end developer, these sets of tools and practices are designed to make your work more effective, more automated, and maybe even more enjoyable. Keep in mind that DevOps does not mean that developers will take over operations roles. It's a means of bridging the gap between roles for more successful work, for both you and the operations team.

In the next exercise you will learn how to set up a GitHub repository to use as a common codebase for your front-end Liferay projects. If you want to learn more about DevOps, Liferay also offers a DevOps course and course on DXP Cloud, an Enterprise PaaS tailored to Liferay DXP integrates perfectly with the DevOps philosophy.

### Best Practices for Front-End Development {#BestPractices}

The goal of front-end development is to create an environment that is efficient, User-friendly, and aesthetically pleasing. It needs to contain all the content a business needs without ever being confusing, so a consistent layout is vital to the success of every website. The platform also defines a business' online brand-image, so developers are tasked with styling everything perfectly. Finally, businesses require demanding functionality that front-end developers must implement with JavaScript. It's important to remember the best way to go about this implementation, but also that front-end best practices are ultimately uniquely defined by individual businesses and their front-end development teams to suit their specific needs.

#### Theme Development Best Practices {#Theme}

We recommend you build the theme from the `_styled` base theme to include the Clay base. Potential issues and risk arise when trying to build on existing themes, including being unaware of dependencies in the theme. Building on existing themes may seem time-saving, but in reality, developers can spend pretty much the same amount of time digging into code and troubleshooting as building from scratch. If you must build on an existing theme, it's better to take the css, html, JavaScript, and images and place them in a theme built on `_styled`.

Remember the general best practices for the main source files:
* When editing `portal_normal.ftl`, keep the HTML uncluttered. 
* Whenever a larger chunk of functionality is to be added, create a separate file and use the FreeMarker `<#include>` directive to include it in `portal_normal`.

`_custom.scss` is similar. It should only be used to create custom global styles. More specific styling should be done in *partials*, small snippets of scss styling, and imported to `_custom.scss`. 

The same applies to `main.js`, which should only have JavaScript required at the global level written directly in it, but it should require other JavaScript files for more specific actions. Additionally, keep in mind that if you want to include ECMAScript in your theme, it needs to be saved as a `.es.js` file type and called up in `main.js`.

<div class="note">
Note: We recommend you use plain JavaScript wherever possible to maximize efficiency.
</div>

Remember that images can also be added into your theme, but it's recommended only to include image sprite/maps and to use CSS to place them. This should cut down overhead.

Finally, when configuring your theme, it is a best practice to use the `sitemap.json` and not a LAR file. While using a LAR file is easier initially, using the `sitemap.json` is best for long-term functionality and upgrading your theme.

Front-end developers need to configure their themes to include a number of configurable settings for Platform Administrators to use. This gives them greater control beyond simply adding a theme to the platform or Sites. It's a good idea to include Theme Settings and color schemes in particular, as this allows Site and Platform Administrators to make the stylistic changes they need when setting up their Sites.

#### Styling Best Practices {#Styling}

<div class="key-point">
Key Point: <br />
Take advantage of inheritance, mixins, and variables available via Liferay's utilization of Sass and Bourbon.
</div>

Bourbon mixins cut back on code and reduce overhead while simultaneously adding functionality. Similarly, inheritance cuts back on the total amount of code that needs to be compiled, which reduces your overhead. Scoping variables in Sass gives developers greater control over their styles, resulting in a clean look-and-feel without overly cluttered or complex CSS.

The `_custom.scss` file should be used to make global styling changes. Styles of individual widgets, applications and components should be defined in partials and included into `_custom.scss`. This creates a modular approach that is much better for maintaining and organizing theme files.

#### JavaScript Best Practices {#JavaScript}

<div class="key-point">
Key Point: <br />
Edit main.js to add custom JavaScript that can be accessed globally.
</div>

<div class="note">
Note: The `main.js` file can also be written to require other JavaScript files, which you should take advantage of for better organization and maintenance.
</div>

The Liferay Bundle Generator can be used to create the basic bundle needed to create JavaScript, Angular, React, Vue.js, and Metal.js portlets. It can also be used to create a shared bundle that can hold the dependencies and packages used by multiple portlets to improve deployment times and minimize application size. Creating applications for Liferay is no longer only the realm of back-end developers, but a viable option for front-end developers as well.

<div class="note">
Note: Before writing your JavaScript, consider what JavaScript Libraries you want to include. Adding libraries that are not included by default with Liferay will introduce overhead.
</div>

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>DevOps encapsulates both a ____________________________ and a set of ____________________________.</li>
  <li>Remember to take a ____________________________ approach when editing the main source files of a theme.</li>
  <li>Add Theme ____________________________ and color ____________________________ to give Administrators styling options.</li>
  <li>Remember ____________________________ syntax and variables, and _________________________ mixins when styling.</li>
  <li>Use the ____________________________ to create JavaScript, Angular, React, Vue, and Metal portlets.</li>
</ul>
</div>