# Licensing DXP in Docker

If you're using a DXP trial license that's expiring or you have a new license (activation key) you must install, you can replace your container's existing license.

Here's an example license expiration log message:

```
ERROR [fileinstall-directory-watcher][LicenseManager:?] DXP Development license is expired
```

```{note}
Each DXP Docker image contains a temporary trial license (`trial-dxp-license-[id-number].xml`) that expires one month after the image was created.
```

Here's how to replace a license:

1. Open a Bash shell in your container.

    ```bash
    docker exec -it [container] bash
    ```

1. Remove the existing license files.

    ```bash
    rm /opt/liferay/data/license/*
    ```

1. Remove all trial licenses and activation key files from the OSGi modules folder.

    ```bash
    rm /opt/liferay/osgi/modules/*license*.xml /opt/liferay/osgi/modules/*activation*.xml
    ```

1. Exit the Bash shell.

    ```bash
    exit
    ```

1. Copy the new license/key file to the container.

    ```bash
    docker cp [license file] [container]:/opt/liferay/deploy
    ```

    Alternatively, if you have associated a [bind mount](./providing-files-to-the-container.md) with the container, you can copy the license/key file to the folder mapped to `/mnt/liferay/deploy`.

DXP logs the license installation. For example,

```
INFO  [fileinstall-directory-watcher][LicenseManager:?] License registered for DXP Development
INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:?] Processing trial-dxp-license-123.xml
INFO  [fileinstall-directory-watcher][LicenseManager:?] DXP Development license validation passed
INFO  [fileinstall-directory-watcher][LicenseManager:?] License registered for DXP Development
```

Congratulations on updating your DXP license!

## Additional Information

* [Activating Liferay DXP](../../setting-up-liferay/activating-liferay-dxp.md)
* [Providing Files to the Container](./providing-files-to-the-container.md)
* [Upgrading to a New Docker Image](./upgrading-to-a-new-docker-image.md)