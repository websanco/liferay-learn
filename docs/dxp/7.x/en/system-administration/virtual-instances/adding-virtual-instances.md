# Adding a Virtual Instance

Follow these steps to create a Virtual Instance: 

1. Before you create a Virtual Instance, configure its domain name in your network. 

1. Go to *Control Panel* &rarr; *Configuration* &rarr; *Virtual Instances*. 

1. Click the *Add* button (![Add](../../../images/icon-add.png)).  This opens the *New Instance* form. 

1. Complete the New Instance form as follows:

**Web ID:** The instance's ID. Using the domain name is a common convention.

**Virtual Host:** The domain name you configured in your network. When users are directed to your server via this domain name, they'll be sent to the Virtual Instance that contains their data.

**Mail Domain:** The mail host's domain name for the Virtual Instance.  Email notifications are sent from the instance using this domain. 

**Max Users:** The maximum number of user accounts the Virtual Instance supports. Enter *0* to support unlimited users. 

**Active:** Whether the Virtual Instance is active. Note that inactive Virtual Instances aren't accessible to anyone, even the administrator. 

Click *Save* when you're done filling out the form. 

Now you can navigate to the instance using its domain name. You're brought to what looks like a clean install of @product@. This is your new Virtual Instance!  You can configure it any way you like. The remaining articles in this section show you how to configure an instance's settings. 
