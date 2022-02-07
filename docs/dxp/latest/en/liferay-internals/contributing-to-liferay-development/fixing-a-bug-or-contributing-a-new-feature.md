# Fixing a Bug or Contributing a New Feature

The first thing to do in learning to fix bugs or contributing a feature is to become familiar with how to build the system. The Liferay Portal build system now includes an option for using nightly snapshot bundles as opposed to using a full build via ant all. This significantly cuts down on the amount of time needed to build the system. *ant all* can sometimes take 15-30 minutes to build a working system whereas using a snapshot takes only a few minutes. 

For more information, see [Building Liferay Source](./building-liferay-source.md)

## Fix a Bug

Once Liferay Portal is built, see if the bug can be reproduced using the latest revision of the platform. Often times the bug is already addressed in development builds of Liferay Portal. Also be sure to issue any pull requests against the master branch. Pull requests against other branches will not be accepted.

Learn the basic code structure of Liferay Portal to see if you can find where the bug might be. Liferay Portal is a very large product and finding where the code resides for a particular feature can be daunting at first. However, the code follows well defined patterns and once that pattern learned it can be very easy to locate an area of code. For more information, see [How the Source is Organized](./organizing-the-source.md).

Try to fix the bug, rebuild and restart Liferay, and see if you've fixed it! If not, keep iterating until you've fixed it. Try not to touch more code than is absolutely necessary.

Commit the code change to your git repository, and issue a pull request against the main [liferay-portal repo on Github](https://github.com/liferay/liferay-portal).

Click Contribute Solution on the bug report on [issues.liferay.com](https://issues.liferay.com/secure/Dashboard.jspa) to document that you fixed it and have recorded the pull request, and congratulate yourself on giving back to our community! For more information, see [Building Liferay Source](./building-liferay-source.md).

## Contribute a New Feature

Liferay thrives on the contribution and innovation from our developer community, and implementing a new idea ensures the continued evolution of the platform to meet the needs of its users. If you've never developed for Liferay before, you should take some time to learn the code, and perhaps fix a few bugs to understand the process. 

Many features in Liferay Portal are now added through modules which makes it easier to publish new features through Liferay Marketplace. The general rule of thumb is if it can be a Marketplace plugin then it should be a Marketplace plugin. Marketplace plugins are generally more easy to accept than a core contribution. If the new feature cannot be implemented as a Liferay Marketplace plugin then it's best to start a discussion on the [Suggestions and Features](https://liferay.dev/en/forums-redirect/-/message_boards/category/1108052) forum to see if there is interest in your idea, and to identify potential helpers that can help guide you. For more information, see [Marketplace WebSite](https://marketplace.liferay.dev/).

Implement the feature, any associated tests, and thoroughly test and ensure it does what you think it should do. Issue pull requests as needed.

Click *Contribute Solution* on the feature request ticket on [issues.liferay.com](https://issues.liferay.com/secure/Dashboard.jspa) to document that you've implemented it and have recorded the pull request(s), and congratulate yourself on giving back to our community!