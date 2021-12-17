# Configuring Persistent File Storage Volumes

Administrators can configure the volumes for their services in DXP Cloud depending on their deployment type (`Deployment` or `StatefulSet`). Volumes can be stored either with persistent shared storage (NFS) or with dedicated storage (SSD), depending on the deployment type. This article documents how to configure volumes via a service's `LCP.json` file. See [Understanding Deployment Types](./understanding-deployment-types.md) for more information on deplyoment types.

1. Choose the folders that contain the data to be persisted (for example `/liferay/opt/data`).

1. Navigate to the `LCP.json` file in the repository for the specific environment (for example, `liferay/`).

1. Add the `volumes` configuration to the `LCP.json` file. This configuration must contain a key for each volume. For example, the following configuration contains a `data` key for `/liferay/opt/data`:

```json
{
    "id": "lfr",
    "memory": 6144,
    "cpu": 4,
    "volumes": {
        "data": "/liferay/opt/data"
    }
}
```

```{important}
For `StatefulSet` type services, you must delete and then redeploy the service for changes to the volume configuration to take effect.
```

```{note}
If you are using version 3.x.x services, then the `LCP.json` file for the `liferay` service is instead located in the `lcp/liferay/` directory. See [Understanding Service Stack Versions](../reference/understanding-service-stack-versions.md) for more information on checking the version.
```

## Sharing Volumes Between Different Services

Only volumes in `Deployment` type services may be shared with other services in the same environment using NFS. `StatefulSet` type services each have their own volumes which may not be shared.

To share a volume:

1. Navigate to the `LCP.json` file in the Github repository for the service (`[ProjectID]/liferay/LCP.json`).

1. Enter the following:
     * service's ID
     * location (absolute path) of the content to be shared
     * the same volume key for the different services

In the following example, `service1` and `service2` will share files from `/documents/images` using a shared volume in NFS. This way, both services can access the files within the volume via the key and declared file paths.

The first service (`service1`) shares photos from `/documents/images`:

```json
{
  "id": "service1",
  "volumes": {
    "photos": "/documents/images"
  }
}
```

The second service (`service2`) declares a volume in the same location, allowing it to share it via NFS:

```json
{
  "id": "service2",
  "volumes": {
    "photos": "/documents/images"
  }
}
```

Both services will be able to access the specified volume in NFS on the next deployment after the services are restarted.

## Removing Contents of a Volume

Volumes persist in your environment even when the services are deleted. You can change the name of the volume in use for your service (or rename the existing volume) to use a new volume, but the old volume's contents will still exist (in either NFS or the service's SSD). You must also remove the contents of any volume yourself if you do not want them to persist.

## Additional Information

* [Configuring Your Github Repository](../getting-started/configuring-your-github-repository.md)
* [Configuring via LCP.json](../reference/configuration-via-lcp-json.md)
* [Understanding Deployment Types](./understanding-deployment-types.md)
