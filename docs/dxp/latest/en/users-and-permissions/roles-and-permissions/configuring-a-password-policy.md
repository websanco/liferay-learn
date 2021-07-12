# Configuring a Password Policy

Password policies enhance the security of your installation. You can use the default policy that ships with Liferay (modified or as is), or you can create your own policies. Policies can be assigned to Users or Organizations.

```note::
   New Users are assigned to the Default Password Policy by default. If you wish to associate Users with a custom password policy they must be assigned as members of that policy.
```

## Overview

1. [Modifying the Default Password Policy](#modifying-the-default-password-policy)
1. [Creating a Custom Password Policy](#creating-a-custom-password-policy)
1. [Assigning Members to a Password Policy](#assigning-members-to-a-password-policy)
1. [Utilizing the Properties File](#using-the-properties-file)
1. [Password Properties Reference](#password-properties-reference)

## Modifying the Default Password Policy

To change the default policy,

1. Navigate to *Control Panel* &rarr; *Security* &rarr; *Password Policies*.

1. Click the Actions button (![Actions](../../images/icon-actions.png)) next to the Default Password Policy and click *Edit*.

   ![Click edit to make changes to the policy.](./configuring-a-password-policy/images/01.png)

1. Make your desired changes in the configuration window.

   ![Make your desired changes in the configuration window.](configuring-a-password-policy/images/02.png)

1. When finished, click *Save*.

### Policy Configuration Reference

**Password Changes:** Allow or prevent password changes, and set a time limit on the validity of password reset links.

**Password Syntax Checking:** Require a certain syntax when choosing a password. You can disallow dictionary words, set a minimum length, and more in this section.

**Password History:** Decide how many passwords to keep in the history, preventing old passwords from being reused.

**Password Expiration:** If passwords expire, specify how long passwords are valid, when and whether a warning is sent, and a *Grace Limit*: how many logins are allowed after the password is expired before forcing a password change.

**Lockout:** Set a maximum number of failed authentication attempts before the account is locked, how long the number of attempts is stored, and how long the lockout lasts.

## Creating a Custom Password Policy

A custom password policy might be useful in some scenarios. For example, you may want certain Users or User Groups to have stricter password rules. To create a custom policy,

1. Navigate to *Control Panel* &rarr; *Security* &rarr; *Password Policies*.

1. Click the *Add* icon (![Add](../../images/icon-add.png)) to add a new policy.

1. In the configuration window, provide a Name and Description for your password policy. 

1. Set the properties for your policy (see above). When finished, click *Save*. 

## Assigning Members to a Password Policy

1. Click the Actions button (![Actions button](../../images/icon-actions.png)) next to the password policy. Click *Assign Members*.

   ![Click on the Assign Members link.](configuring-a-password-policy/images/03.png)

1. You can add individual Users or Organizations to the password policy. Click the _add_ icon (![Add icon](../../images/icon-add.png)) and a new form will pop up.

1. Make your selection (of Users or Organizations). When finished, click *Add*. 

   ![Make your selection and click the Add button.](configuring-a-password-policy/images/04.png)

   Your Users or Organizations are now associated with your password policy.

## Using the Properties File

The Default Password Policy can only be modified from the UI in settings.

A new password policy can be created in the UI or added via the [`portal-ext.properties`](../../installation-and-upgrades/reference/portal-properties.md) file. Add the properties and values you want to customize into the file. Make sure to add a name for the custom password policy. 

For example, if you wanted to have a custom password policy that required a minimum length, numbers, symbols you might have these values set: 

```properties
#
# Properties for a Custom Password Policy
#

...
passwords.default.policy.name=Custom Password Policy
passwords.default.policy.check.syntax=true
passwords.default.policy.min.length=10
passwords.default.policy.min.numbers=1
passwords.default.policy.min.symbols=1
...
```

See the reference below for a full list of properties that can be used to customize your password policy.

Once you have your `portal-ext.properties` file, place it in the [`[Liferay Home]`](../../installation-and-upgrades/reference/liferay-home.md) folder. Learn more about [portal properties](../../installation-and-upgrades/reference/portal-properties.md) or if using Docker, [configuring a Docker container](../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/configuring-containers.md).

## Password Properties Reference

| Property | Default Value | Description |
| --- | --- | --- |
| `passwords.default.policy.allow.dictionary.words` | true | Common dictionary words are allowed as the user's passwords |
| `passwords.default.policy.changeable` | true | The user can change their password |
| `passwords.default.policy.change.required` | true | The user is required to change their password when they first log in |
| `passwords.default.policy.check.syntax` | false | The password is checked for certain words, certain length, certain characters, etc. |
| `passwords.default.policy.expireable` | false | Expire passwords after a set amount of time |
| `passwords.default.policy.grace.limit` | 0 | The number of times a user can log in after their password has expired, before they are required to enter a new password |
| `passwords.default.policy.history` | false | Keep a history of the user's passwords and prevent them from reusing a previous password |
| `passwords.default.policy.history.count` | 6 | Determines the number of previous passwords to keep in the history |
| `passwords.default.policy.lockout` | false | A user can attempt to log in a certain number of times before their account is locked |
| `passwords.default.policy.lockout.duration` | 0 | The amount of time that a user's account is locked. 0 if password can only be unlocked by administrator. Time is in seconds |
| `passwords.default.policy.max.age` | 8640000 | Determines how long a password is good for before it needs to be changed. Time is in seconds |
| `passwords.default.policy.max.failure` | 3 | The maximum number of times that a user can attempt to log in with the wrong password |
| `passwords.default.policy.min.age` | 0 | Determines how long a user must wait before changing their password again. Time is in seconds |
| `passwords.default.policy.min.alphanumeric` | 0 | The minimum number of alpha numeric letters required in the user's password |
| `passwords.default.policy.min.length` | 6 | The minimum length required of the user's password |
| `passwords.default.policy.min.lowercase` | 0 | The minimum number of lower case letters required in the user's password |
| `passwords.default.policy.min.numbers` | 1 | The minimum number of numbers required in the user's password |
| `passwords.default.policy.min.symbols` | 0 | The minimum number of symbols required in the user's password |
| `passwords.default.policy.min.uppercase` | 1 | The minimum number of upper case letters required in the user's password |
| `passwords.default.policy.name` | Default Password Policy | Name of your password policy |
| `passwords.default.policy.regex` | (?=.{4})(?:[a-zA-Z0-9]*) | If using the RegExpToolkit to generate passwords, set the regular expression pattern to be used to generate and validate passwords |
| `passwords.default.policy.reset.failure.count` | 600 | The amount of time that a record of failed logins is kept for the user. Time is in seconds |
| `passwords.default.policy.reset.ticket.max.age` | 86400 | How long a password reset link is valid for. Time is in seconds |
| `passwords.default.policy.warning.time` | 86400 | How long before a password expires that the user will be notified. Time is in seconds |

## Additional Information

* [Authentication Basics](../../installation-and-upgrades/securing-liferay/authentication-basics.md)
