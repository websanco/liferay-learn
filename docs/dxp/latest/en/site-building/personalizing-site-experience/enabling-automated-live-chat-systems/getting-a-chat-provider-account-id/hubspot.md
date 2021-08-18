# Hubspot 

[Hubspot](https://www.hubspot.com/) is a CRM platform that includes Live Chat functionality. Enabling integration with Hubspot requires the creation of a live chat and a combination of two values that can be retrieved from your Hubspot account: your Hubspot User Account ID and the Hubspot API Token.

To create and configure a live chat, follow the steps in [Hubspot's Knowledge Base](https://knowledge.hubspot.com/chatflows/create-a-live-chat). 

## Locating Your Hubspot User Account ID

1. Log in to your [Hubspot Account](https://app.hubspot.com/login).

1. Go to your profile in the top right corner.

    Copy the account number that corresponds to the channel you want to use. This number corresponds with the Account ID used when enabling Click to Chat on Liferay Portal.

    ![Go to your profile in the top right corner and copy the account number to the channel you want to use.](./hubspot/images/01.png)

## Getting the Hubspot API Token

While logged in to your Hubspot account:

1. Click the *Settings* button.

    ![Click on the Settings button.](./hubspot/images/02.png)

1. Click on *Integrations* &rarr; *API Key* on the left side of the page.

    ![Click on Integrations to access your API Key.](./hubspot/images/03.png)

1. Click *Show* to reveal your API token key.

    ![Click on Show to reveal your API token key.](./hubspot/images/04.png)

## Conclusion

Once you have your User Account ID and your API token, combine them to use as your Chat Provider Account ID when [enabling live chat](../enabling-automated-live-chat-systems.md) with your Liferay instance. The Chat Provider Account ID follows this format: `[Hubspot Account ID]/[Hubspot API Token]`.