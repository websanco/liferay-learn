# Modifying Database Fields in Development

As you develop an application, you might need to add fields to your database. This is a normal process of iterative development: you get an idea for a new feature, or it's suggested to you, and that feature requires additional data in the database. **New fields added to `service.xml` are not automatically added to the database.** To add the fields, you must do one of two things: 

1. Write an [upgrade process](https://help.liferay.com/hc/en-us/articles/360031165731-Introduction-to-Upgrade-Processes) to modify the tables and preserve the data, or

1. Run the `cleanServiceBuilder` [Gradle task](https://help.liferay.com/hc/en-us/articles/360029145991-DB-Support-Gradle-Plugin) (also supported on Maven and Ant), which drops your tables so they get re-created the next time your app is deployed. The [Maven DB Support Plugin](https://help.liferay.com/hc/en-us/articles/360029146431-DB-Support-Plugin) reference article explains how to run this command from a Maven project.

Use the first option if you have a released application and you must preserve user data. Use the second option if you're adding new columns during development. 

## Related Topics

[Upgrade Processes](https://help.liferay.com/hc/en-us/articles/360031165731-Introduction-to-Upgrade-Processes) 

[Gradle DB Support Plugin](https://help.liferay.com/hc/en-us/articles/360029145991-DB-Support-Gradle-Plugin)

[Maven DB Support Plugin](https://help.liferay.com/hc/en-us/articles/360029146431-DB-Support-Plugin)