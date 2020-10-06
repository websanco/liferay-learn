# 3.0 Breaking Changes

This is a chronological list of changes that break existing functionality, APIs, or contracts with third party Liferay Commerce developers or Users in Commerce 3.0. We try our best to minimize these disruptions, but sometimes they are unavoidable.

Here are some of the types of changes documented in this file:

* Functionality that is removed or replaced.

* API incompatibilities: Changes to public Java or JavaScript APIs.

* Changes to context variables available to templates.

* Changes in CSS classes available to Liferay themes and portlets.

* Configuration changes: Changes in configuration files, like `com.liferay.commerce.*.cfg`, etc.

* Execution requirements: Java version, J2EE Version, browser versions, etc.

* Deprecations or end of support: For example, warning that a certain feature or API will be dropped in an upcoming version.

* Recommendations: For example, recommending using a newly introduced API that replaces an old API, in spite of the old API being kept in Liferay Portal for backwards compatibility.

## Changes to the Commerce Menu

* **Date:** Sept. 3, 2020

* **JIRA Ticket:** [COMMERCE-4565](https://issues.liferay.com/browse/COMMERCE-4565)

### What changed?

Commerce menu items are moved from the Control Panel to the new Global Menu in DXP 7.3. A new _Commerce_ tab (on the same level as the Applications and Control Panel tabs) contains these menu items.

The `commerce-admin-api` and `commerce-admin-web` modules are also removed.

### Who is affected?

This affects menu items added to Commerce &rarr; Settings. Developers can no longer implement `CommerceAdminModule` to add menu items to Commerce &rarr; Settings.

This also affects end users, who now access Commerce management items from the new Commerce Menu in the Global Menu.

### Why was this change made?

The menu change was made to keep the navigation scope in the header bar while remaining compliant with DXP 7.3 standards. The `commerce-admin-api` and `commerce-admin-web` modules were removed since they are no longer in use with the removal of the old menu location.

## Files Moved

* **Date:** Aug. 20, 2020

* **JIRA Ticket:** [COMMERCE-4052](https://issues.liferay.com/browse/COMMERCE-4052)

### What changed?

The following files are moved:

* `com.liferay.commerce.pricing.web.servlet.taglib.ui.CommerceDiscountScreenNavigationConstants`

* `com.liferay.commerce.pricing.web.servlet.taglib.ui.CommercePricingClassScreenNavigationConstants`

### Who is affected?

This affects any developers referencing or using these files in their code.

### How should I update my code?

Replace old references to these files with their new paths.

The files are now located in these paths:

* `com.liferay.commerce.pricing.web.internal.constants.CommerceDiscountScreenNavigationConstants`

* `com.liferay.commerce.pricing.web.internal.constants.CommercePricingClassScreenNavigationConstants`

### Why was this change made?

These files were moved to follow Liferay's coding convention.

## Destination Names Changed

* **Date:** Sept. 10, 2020

* **JIRA Ticket:** [COMMERCE-4762](https://issues.liferay.com/browse/COMMERCE-4762)

### What changed?

The prefix `commerce_` has been added to the Commerce destinations defined in `com.liferay.commerce.constants.CommerceDestinationNames`:

* `liferay/commerce_order_status`

* `liferay/commerce_payment_status`

* `liferay/commerce_shipment_status`

* `liferay/commerce_stock_quantity`

* `liferay/commerce_subscription_status`

### Who is affected?

This change affects anyone who references or uses any of these destinations in their code.

### How should I update my code?

Update any explicit references to Commerce destinations with the prefix `commerce_`.

### Why was this change made?

The destination names were changed to follow Liferay's naming convention.
