# Self-Healing

The self-healing functionality of DXP Cloud detects if a service or application has become unresponsive and automatically initiates procedures to recover the unresponsive service. The platform uses probes to monitor the services.

## Using and Configuring Probes

DXP Cloud offers two probes used in conjunction to manage applications:

* Liveness Probe: Indicates whether the service is running.
* Readiness Probe: Indicates whether the service is ready to receive requests.

Each probe can be configured with the following options:

| Property Name | Description |
| --- | --- |
| `initialDelaySeconds`| Number of seconds after the container has started before the probe is initiated. |
| `periodSeconds` | How often (in seconds) to perform the probe. The default is 10; the minimum is 1. |
| `timeoutSeconds` | Number of seconds after which the probe times out. The default and minimum is 1. |
| `successThreshold` | Minimum consecutive successes for the probe to be considered successful following a failure. The default and minimum is `1`. Must be `1` for liveness. |
| `failureThreshold` | The number of times DXP Cloud repeats a failed probe before giving up. For a liveness probe, giving up means the service will restart. For a readiness probe, giving up means the probe will be marked as Failed. The default is `3`; the minimum is `1`. |

When a probe detects a failure (i.e., it does not return the success message in a status check), the probe automatically takes action to address it (restarting the service or redirecting traffic away from the instance). The probes themselves *do not cause* outages, but they instead attempt to detect and respond to them automatically.

The liveness and readiness probes are deployed to all services regardless of the environment. Typically, customers do not have to make changes to the probes unless they want to adjust their values based on their needs. For example, a system administrator may increase the `initialDelaySeconds` value to account for a longer startup process.

To change the default values, modify the `lcp.json` file. See the [Configuration via LCP.json](../reference/configuration-via-lcp-json.md) article.

## Liveness Probe

The liveness probe uses a URL Request (path/port) to validate that a service is running properly. This probe repeatedly pings a configurable path (for example, `/c/portal/layout` for the `liferay` service). Often, the liveness probe is a lightweight HTTP server inside the application.

```{tip}
The liveness probe's path for the `liferay` service (`/c/portal/layout`) uses a redirect to your instance's home page (by default `/web/guest/home`). The redirect process adds another request that adds to the response time for your probes. Configure the path to point more directly to a page in your instance to reduce the response time and improve the liveness probe's effectiveness.
```

If the probe gets an HTTP response in the 200 or 300 range, it marks the application as healthy. It runs for the whole lifecycle of the service's container, and it fails if the instance is no longer live (for example, due to a crash).

If the probe pings the path a number of times (configurable via the probe's `failureThreshold` field in `LCP.json`) and cannot get a valid response (a probe failure), the service automatically restarts. When the liveness probe fails, it is a sign that either your service is experiencing a problem during startup, the service has crashed and may not recover, or it responds too slowly for your probe's configuration and you may need to adjust it.

Each service's `LCP.json` file contains the following configuration:

```json
{
  "livenessProbe": {
    "httpGet": {
      "path": "/status",
      "port": 3000
    },
    "initialDelaySeconds": 40,
    "periodSeconds": 5,
    "successThreshold": 5
  }
}
```

### Adjusting Liveness Probe Configurations

The best configuration for your liveness probe depends on the customizations you have deployed and the expected startup time of your service. The `path` value should be set to whatever route best indicates that your service is up and running. For the `liferay` service, this could be the path to your main Site's home page (such as `/web/guest/home`), or it could be a page hidden from your navigation that takes longer to load.

The `liferay` service's path typically is the only service that may need adjustment to the path, because of the modules or customizations that may be applied to it. You can create a custom OSGi module or servlet to ping instead, for instance, that verifies that your other custom modules are active before accepting a request to signal that your service is live.

The other values in your liveness probe's configuration (`initialDelaySeconds`, `periodSeconds`, `successThreshold`, `timeoutSeconds`, and `failureThreshold`) should also be tweaked as needed for your service's typical performance. The `initialDelaySeconds` value should accommodate for the expected time for the service to start up. For example, if your `liferay` service runs extra tasks on startup, it may need more time to perform the liveness check.

The `timeoutSeconds` and `failureThreshold` values should be long enough that it is safe to assume the service cannot recover from hanging and must restart. These values should also be tweaked as needed if they are not appropriate for your service's typical performance responding to requests.

You must be aware of any impact to your service's startup and response time that your customizations (custom modules, scripts, etc.) may have, and adjust your liveness probe's configuration accordingly. The `initialDelaySeconds` value you configure should be useful in stopping and restarting the service within a reasonable time if the startup fails, but not so short that the probe restarts the service too fast to start successfully. The `timeoutSeconds`, `periodSeconds`, and `failureThreshold` should be just long enough to give the service sufficient time to recover and resume operation before forcing the service to restart (a restart can also be disruptive to finding the cause of a liveness probe failure if it happens too quickly).

## Readiness Probe

The readiness probe pings an address like the liveness probe, or it uses an executable command to check the availability of your service. If the command returns with the correct exit code, then the container is marked as healthy. Otherwise, it is marked unhealthy, and traffic is directed away from the failing instance.

When the readiness probe fails, it is a sign that your service timed out when performing the configured command. This can indicate that the service is running too slowly and requires tuning, that it is receiving high traffic and cannot respond to all requests, or even that there is a problem with the command it is running.

As soon as the readiness probe detects that the service is ready, then the service may receive traffic. If the probe detects that the service is no longer ready, then it will stop receiving new traffic. However, the readiness probe will not restart the service; only the [liveness probe](#liveness-probe) forces the service to restart.

Each service's `LCP.json` file contains the following configuration:

```json
{
  "readinessProbe": {
    "exec": {
      "command": ["cat", "/tmp/healthy"]
    },
    "initialDelaySeconds": 40,
    "periodSeconds": 5
  }
}
```

Here is a sample log from the readiness probe has been deployed on the `webserver` service. The log shows the Google server hitting the specific path `nginx_status` continuously.

```shell
Oct 04 12:05:51.821 build-14 [webserver-5547c96447-hbrr6] 10.138.0.69 - - [04/Oct/2019:19:05:51 +0000] "GET /nginx_status HTTP/1.1" 200 117 "-" "kube-probe/1.12+" "-"
Oct 04 12:05:53.001 build-14 [webserver-5547c96447-hbrr6] 10.138.15.249 - - [04/Oct/2019:19:05:53 +0000] "GET /nginx_status HTTP/1.1" 200 115 "-" "GoogleHC/1.0" "-"
Oct 04 12:05:53.083 build-14 [webserver-5547c96447-hbrr6] 10.138.0.13 - - [04/Oct/2019:19:05:53 +0000] "GET /nginx_status HTTP/1.1" 200 115 "-" "GoogleHC/1.0" "-"
Oct 04 12:05:53.293 build-14 [webserver-5547c96447-hbrr6] 10.138.15.251 - - [04/Oct/2019:19:05:53 +0000] "GET /nginx_status HTTP/1.1" 200 115 "-" "GoogleHC/1.0" "-"
```

### Adjusting Readiness Probe Configurations

The readiness probe should execute a command that appropriately gauges the availability of your service to receive requests. If an instance of your service is responding to requests too slowly and it needs to be cut off from more traffic (redirecting to other instances if possible), then the configured executable command should also experience this slowness so it can respond appropriately. Services like the `webserver`, by default, only ping a specific address to check the service's readiness. However, you may want to create a different command, such as running a custom script to check the responsiveness of your service.

If necessary, then you must also adjust the other configuration values (`initialDelaySeconds`, `periodSeconds`, `timeoutSeconds`, and `successThreshold`) as appropriate for your needs. If your service should prefer to serve more requests even if it takes a long time to do so (for example, if the work done to service the request is expected to take a long time), then the configuration should allow for a longer timeout. If your service needs to respond to requests quickly or timeout instead, then the configuration should have a shorter timeout.
