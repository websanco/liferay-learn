# Configuring the Questions App

## Configuration from the App

To configure the app,

1. Click on the Options icon (![Options icon](../../../images/icon-options.png)) of the app and click *Configuration*.

    ![Click on the configuration link of the app.](./configuring-the-questions-app/images/01.png)

1. Under General Settings, there is a Show Cards for Topic Navigation option. If this is checked, the app will display separate cards for each question topic. If it is not checked, the app will display a list of all the questions.

    ![The configuration window opens with different options.](./configuring-the-questions-app/images/02.png)

1. By default, the app will display all existing topics. To show the questions of one specific topic, click the *Select* button under Root Topic ID.

    ![Select an individual topic as the root topic.](./configuring-the-questions-app/images/03.png)

1. Select a topic and click *Save*. The app will now only show questions for the selected topic.

To modify permissions for the app,

1. Click on the Options icon (![Options icon](../../../images/icon-options.png)) of the app and click *Permissions*.

    ![Click on the configuration link of the app.](./configuring-the-questions-app/images/01.png)

1. A new window will open. 

    ![Assign permissions for different roles on this page.](./configuring-the-questions-app/images/04.png)

1. Assign permissions for different Roles. Click *Save*.

## Configuration from System Settings

A few additional settings are available for configuration. Navigate to *Control Panel* &rarr; *System Settings* &rarr; *Third Party*. Click on *Questions* under Widget Scope.

![Navigate to the Questions app settings in system settings.](./configuring-the-questions-app/images/05.png)

If the Enable Redirect to Login is checked, a Guest user will be redirected to a login page when they try to click *Ask Question*.

```Note::
A Service Access Policy needs to be created to allow Guest users to view the app.
```

To create a new policy,

1. Navigate to *Control Panel* &rarr; *Service Access Policy*. Click on the *Add* icon (![Add icon](../../../images/icon-add.png)).

1. Give the policy a Name and Title. Click the *Switch to Advanced Mode* button at the bottom. Add the following two signatures under Allowed Service Signatures,

        com.liferay.message.boards.service.MBCategoryService#getCategoriesCount
        com.liferay.message.boards.service.MBCategoryService#getCategory

    ![Create a new policy and add the signatures.](./configuring-the-questions-app/images/06.png)

1. Click *Save* to save the new policy.

The other available app settings are,

| Setting | Description |
| --- | --- |
| Show Cards for Topic Navigation | If this is checked, the app will display separate cards for each question topic. If it is not checked, the app will display a list of all the questions. |
| Use Topic Names in URL | If this is checked, each topic name will be used in a more friendly URL instead of a topic ID number. |
| Root Topic ID | This setting is ignored. |