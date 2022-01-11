# Modifying Database Fields in Development

As you develop an application, you might need to add fields to your database. This is a normal process of iterative development: you get an idea for a new feature, or it's suggested to you, and that feature requires additional data in the database. **New fields added to `service.xml` are not automatically added to the database.** To add the fields, you must do one of two things: 

1. Write an [upgrade process](../../../data-frameworks/upgrade-processes.md) to modify the tables and preserve the data, or

2. Run the `cleanServiceBuilder` [Gradle task](../../../tooling/other-tools/gradle-plugins.md) (also supported on Maven and Ant), which drops your tables so they get re-created the next time your app is deployed. 

Use the first option if you have a released application and you must preserve user data. Use the second option if you're adding new columns during development. 

## Related Topics

[Upgrade Processes](../../../data-frameworks/upgrade-processes.md) 

[Gradle DB Support Plugin](../../../tooling/other-tools/gradle-plugins.md)