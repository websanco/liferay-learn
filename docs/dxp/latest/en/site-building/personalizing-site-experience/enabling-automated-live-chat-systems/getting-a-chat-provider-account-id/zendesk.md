# Zendesk

[Zendesk](https://www.zendesk.com/) is an customer service platform. This article documents how to locate your Zendesk account ID in order to [enable integration](../enabling-automated-live-chat-systems.md) with your Liferay instance.

## Locating your Zendesk Account ID

1. Log in to your [Zendesk account](https://www.zendesk.com/login/#login).

1. On the Dashboard home page, click on the *Manage widget* link. 

    ![You can see the Zendesk Dashboard page.](./zendesk/images/01.png)

1. (Optional) Go to *Settings* and follow the instructions in the next step.  

    ![You can see the Zendesk Settings section.](./zendesk/images/02.png)

1. Zendesk provides a code snippet to embed their web widget. The Account ID is the portion of the snippet after `...snippet.js?key=`. Select and copy that portion of the snippet and use it as the Chat Provider Account ID to [enable automated live chat integration](../enabling-automated-live-chat-systems.md) with your Liferay instance.

   ![Copy the portion of the snippet and use it as the Chat Provider Account ID.](./zendesk/images/03.png)