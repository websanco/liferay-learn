# Activating Liferay Enterprise Search

Starting with Liferay DXP 7.4, the Liferay Enterprise Search (LES) applications are included with all Liferay DXP bundles and Docker containers. To begin using LES, purchase an LES Add-On Subscription: start by contacting a Liferay sales representative or visit <https://www.liferay.com/contact-sales>.

LES is enabled by default and requires no additional installation steps. However, a LES add-on subscription must be purchased to receive official Liferay Support and additional benefits. Maintenance and updates to LES applications are delivered through Fix Pack and Service Pack releases. If you do not have a subscription to LES, access can be disabled completely by following these steps described below.

## Local Bundle: Deactivating LES

LES applications are enabled by default in all Liferay DXP 7.4+ bundles. If you don't have a subscription and the accompanying license to use LES, deactivate it by setting the `enterprise.product.enterprise.search.enabled` [portal property](../../installation-and-upgrades/reference/portal-properties.md) to `false`:

```properties
enterprise.product.enterprise.search.enabled=false
```

## Docker: Deactivating LES

LES applications are enabled by default in all Liferay DXP 7.4+ Docker images. If you don't have a subscription and the accompanying license to use LES, deactivate it in one of two ways:

1. If using properties files to configure the container, deactivate it by setting the `enterprise.product.enterprise.search.enabled` [portal property](../../installation-and-upgrades/reference/portal-properties.md) to `false`:

    ```properties
    enterprise.product.enterprise.search.enabled=false
    ```

1. To instead override the portal property in an environment variable, set 

    ```properties
    LIFERAY_ENTERPRISE_PERIOD_PRODUCT_PERIOD_ENTEPRISE_PERIOD_SEARCH_PERIOD_ENABLED=false
    ```

See [Configuring Containers](../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/configuring-containers.md) for more information.
