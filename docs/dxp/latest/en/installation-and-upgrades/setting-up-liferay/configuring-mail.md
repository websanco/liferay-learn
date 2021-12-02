# Configuring Mail

```{toctree}
:maxdepth: 3

configuring-mail/configuring-default-email-senders.md
configuring-mail/alternative-email-configuration-methods.md
```

Liferay DXP/Portal can be configured to use a mail server to send email notifications for a variety of purposes: User registration and password management, Site membership notifications, and content updates. For demonstration purposes, you'll walk through configuring the built-in mail session to use a Gmail server.

```{warning}
If using the [IMAP](https://support.google.com/mail/answer/7126229?hl=en) protocol for a category’s mailing list, make sure to [configure the IMAP inbox to delete messages](https://support.google.com/mail/answer/78892?hl=en) as they are pulled by the email client that sends messages to the users on the mailing list. Otherwise, each email message retained on the server is sent to the mailing list each time there's a new post or update in the category.
```

## Configuring the Built-in Mail Session

Follow these steps to configure the mail session from the Control Panel:

1. Sign in as the administrative User (the User specified on the [Basic Configuration page](../../getting-started/using-the-setup-wizard.md)).
1. Navigate to *Control Panel &rarr; Configuration &rarr; Server Administration &rarr; Mail*.
1. Enter your values for the [following fields](#mail-configuration-reference):

    * **Incoming POP Server:** pop.gmail.com
    * **Incoming Port:** 110
    * **Use a Secure Network Connection:** Flagged
    * **User Name:** joe.bloggs
    * **Password:** *****
    * **Outgoing SMTP Server:** smtp.gmail.com
    * **Outgoing Port:** 465
    * **Use a Secure Network Connection:** Flagged
    * **User Name:** joe.bloggs
    * **Password:** *****
    * **Manually specify additional JavaMail properties to override the above configuration:** If there are additional properties you need to specify, supply them here.

    ![Configuring a Mail Server](./configuring-mail/images/01.png)

1. Click *Save*.

DXP connects to the mail session immediately.

## Mail Configuration Reference

| Field | Description |
| --- | --- |
| Incoming POP Server | The hostname for a server running the Post Office Protocol. DXP checks this mailbox for incoming messages, such as message board replies. |
| Incoming Port | The port on which the POP server is listening. |
| Use a Secure Network Connection | The checkbox to enable whether to use an encrypted connection when connecting to the POP server. |
| User Name | The user ID DXP uses to log into the POP server. |
| Password | The password DXP uses to log into the POP server. |
| Outgoing SMTP Server | The hostname for a server running the Simple Mail Transfer Protocol. DXP uses this server to send emails, such as password change emails and other notifications. |
| Outgoing Port | The port on which the SMTP server is listening. |
| Use a Secure Network Connection | Use an encrypted connection when connecting to the SMTP server. |
| User Name | The user ID DXP uses to log into the SMTP server. |
| Password | The password DXP uses to log into the SMTP server. |
| Manually specify additional JavaMail properties to override the above configuration| This field is for any additional JavaMail settings. |

## Additional Information

* [Configuring Default Email Senders](./configuring-mail/configuring-default-email-senders.md)
* [Alternative Email Configuration Methods](./configuring-mail/alternative-email-configuration-methods.md)