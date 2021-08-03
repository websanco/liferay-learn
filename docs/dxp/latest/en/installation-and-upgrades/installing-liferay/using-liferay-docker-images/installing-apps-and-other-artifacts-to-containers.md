# Installing Apps and Other Artifacts to Containers

Applications and other artifacts (such as [DXP activation keys](../../setting-up-liferay/activating-liferay-dxp.md)) are installed to DXP Docker containers via the container's `/mnt/liferay/deploy` folder. The container entry point symbolically links the `/mnt/liferay/deploy` folder to the container's `[Liferay Home]/deploy` folder (i.e., `/opt/liferay/deploy`). Any artifacts that you provide to the `/mnt/liferay/deploy` folder are auto-deployed to Liferay.

Here are two ways to install artifacts:

* [Installing Artifacts Using a Bind Mount](#installing-artifacts-using-a-bind-mount)
* [Installing Artifacts Using `docker cp`](#installing-artifacts-using-docker-cp)

```note::
   A `Docker volume <https://docs.docker.com/storage/volumes/>`_ can also be used to install artifacts to a container.
```

## Installing Artifacts Using a Bind Mount

Here are the steps:

1. Create a host folder and a subfolder called `deploy`.

    ```bash
    mkdir -p [host folder]/deploy
    ```

1. Copy your artifact into the `[host folder]/deploy` folder. For example,

    ```bash
    cp my-app.lpkg [host folder]/deploy
    ```

1. Create a container, that includes a bind mount that maps your artifact's folder to the container's `/mnt/liferay/deploy` folder. Since this example's artifact is in a folder called `deploy`, you can [bind mount to the container's `/mnt/liferay` folder](./providing-files-to-the-container.md#bind-mounting-a-host-folder-to-mnt-liferay).

    ```bash
    docker run -it -m 8g -p 8080:8080 -v [host folder path]:/mnt/liferay liferay/dxp:[tag]
    ```

Liferay launches and installs the artifact. The container reports a message like this:

```message
[LIFERAY] The directory /mnt/liferay/deploy is ready. Copy files to [host folder]/deploy on the host operating system to deploy modules to Liferay Portal at runtime.
```

```note::
   After Liferay launches, you can install additional artifacts to Liferay by copying them to your ``[host folder]/deploy`` folder.
```

## Installing Artifacts Using `docker cp`

Use a `docker cp` command like this one to copy your artifact to your running container's `/mnt/liferay/deploy` folder.

```bash
docker cp ~/my-apps/some-app.lpkg [container]:/opt/liferay/deploy
```

Now you know how to install apps and other artifacts to Liferay.

## Additional Information

* [Docker Container Basics](./docker-container-basics.md)
* [Providing Files to the Container](./providing-files-to-the-container.md)
* [Container Lifecycle and API](./container-lifecycle-and-api.md)
* [Configuring Containers](./configuring-containers.md)
* [Patching DXP in Docker](./patching-dxp-in-docker.md)