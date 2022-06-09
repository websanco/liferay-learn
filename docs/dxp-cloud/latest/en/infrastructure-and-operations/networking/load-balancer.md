# Load Balancer

The Ingress Load Balancer gives internet access to your environment's services via proxied HTTP(S) connections using TLS (1.0 to 1.2) protocol. Each load balancer has a static IP that you can use to set up custom domains.

![You can configure your environment's load balancer with a custom domain.](./load-balancer/images/01.png)

Having a dedicated load balancer provides a myriad of enhanced features, such as port configuration, custom SSL certificates, and a CDN. These features can be configured in a service's `LCP.json` file:

```json
{
  "id": "webserver",
  "loadBalancer": {
    "cdn": true,
    "targetPort": 80,
    "customDomains": ["acme.liferay.cloud"],
    "ssl": {
      "key": "...",
      "crt": "..."
    }
  }
}
```

## CDN

Liferay's Content Delivery Network (CDN) is a built-in feature provided with DXP Cloud. This CDN caches your static content globally, greatly enhancing your delivery speed. By default, it's enabled in all environments except `dev` environments. You can enable or disable the CDN for a service (in its `LCP.json` file) by setting the value of `cdn` within the `loadbalancer` object:

```json
{
    "loadBalancer": {
        "cdn": true
    }
}
```

![The CDN's status is visible on the Network page.](./load-balancer/images/02.png)

```{note}
The CDN is not currently supported for the Dubai/Northern UAE region.
```

### Clearing the CDN Cache

The CDN improves performance by reducing latency for delivering static content to users. However, it is possible that some of this content is delivered to users before the cache is updated, when the content is no longer valid.

If it is necessary to clear the CDN cache to force the content to be retrieved again, then you can manually clear it from the DXP Cloud console:

1. Log into the DXP Cloud console and navigate to the appropriate environment.

1. Click *Network* from the menu on the left.

1. Under the *CDN* section, click *Clear CDN Cache...*

    ![Click the Clear CDN Cache button on the Network page for your environment.](./load-balancer/images/03.png)

1. On the Clear CDN cache page, select all the checkboxes to confirm that you understand the consequences of clearing the cache, and that it applies to all services with CDN enabled.

    ![The Clear CDN cache page.](./load-balancer/images/04.png)

1. Click *Request Cache Clearance*.

The request is sent to clear the cache when you click the button. Allow up to 30 minutes for the cache to be cleared.

```{warning}
Clearing the CDN cache too frequently can negatively impact server performance, because it can cause a short-term spike in requests to your services that the cache would have served otherwise. Limit clearing the cache to exceptional circumstances to mitigate this impact.
```

## Port

You can set which internal port (`targetPort`) the load balancer's service endpoint routes to. DXP Cloud automatically configures the correct port for the services it provides.

```json
"targetPort": 3000
```

![The load balancer shows your port configurations.](./load-balancer/images/05.png)

## Custom SSL

When you specify the load balancer attribute for a service, it adds a service endpoint using the following naming pattern:

- `<SERVICE-NAME>-<PROJECT-NAME>-<ENVIRONMENT-NAME>.lfr.cloud`

Domains created by DXP Cloud's infrastructure at `.lfr.cloud` are covered by a wildcard certificate that is not displayed in the Network page's SSL certificates section.

For all custom domains added through the console or `LCP.json`, Liferay DXP Cloud reaches out to [Let's Encrypt](https://letsencrypt.org/) for a certificate that renews automatically and covers all custom domains you create.

### Adding Custom SSL Certificates

You can also add your own SSL certificate to cover any custom domains you create. You can either use the SSL certificate provided by Let's Encrypt (for any custom domains added through the DXP Cloud console), or you can define one or more custom certificates by referencing secret values in your `webserver` service's `LCP.json` file. If certificates exist in both places, then any custom certificates defined in the `LCP.json` file take precedent.

When creating custom certificates, note that DXP Cloud only accepts keys and certificates that are in the proper PEM format with [Base64](https://tools.ietf.org/html/rfc4648#section-4) encoding, which must include encapsulation boundaries.

To add a single SSL certificate to the `LCP.json` file:

1. Add [secret variables](../security/managing-secure-environment-variables-with-secrets.md#adding-a-new-secret) to your chosen environment for the certificate's `key` and `crt` values.

1. In your project repository's `webserver/LCP.json` file, add an `ssl` object inside of the `loadbalancer` object, with `key` and `crt` values that [reference the keys for the secrets you added](../security/managing-secure-environment-variables-with-secrets.md#adding-secret-variables-via-lcp-json):

```json
{
    "loadbalancer": {
        "ssl": {
            "key": "@ssl-key-secret",
            "crt": "@ssl-crt-secret"
        }
    }
}
```

Using the `ssl` object in your `LCP.json` file creates a single custom SSL certificate that maps to all custom domains used in this environment.

### Mapping Multiple SSL Certificates to Custom Domains

You can also map different SSL certificates to multiple custom domains by using the `certs` property instead of the `ssl` object.

Use the `certs` property in your web server's `LCP.json` file to create a list of certificates that you can use. Group the `key` and `crt` values for each certificate together with the custom domains they will map to:

```json
{
    "loadbalancer": {
        "certs": [
            {
                "customDomains": ["acme.liferay.cloud"],
                "key": "...",
                "crt": "..."
            },
            {
                "customDomains": ["acme2.liferay.cloud"],
                "key": "...",
                "crt": "..."
            }
        ]
    }
}
```

```{note}
Mapping multiple SSL certificates to your custom domains requires adding the `certs` property to the `webserver` service's `LCP.json` file. Adding custom domains through the DXP Cloud console instead maps all of the custom domains to a single certificate.
```

### Generating an SSL Certificate

When generating a key, you must use either RSA-2048 or ECDSA P-256 encryption algorithms and avoid using passphrase protected keys.

Once custom certificates are made, users are responsible for managing them (e.g., updating them when new custom domains are added, renewing them when they expire).

If it has not already been encoded, then the certificate and key files may both contain text like the following snippet (with either `CERTIFICATE` or `KEY` in the begin/end tags, respectively):

```xml
-----BEGIN CERTIFICATE-----
base64encodedcertificate
-----END CERTIFICATE-----
```

To encode the contents of these files and use them, perform the following steps:

1. Create a new file for both the `key` and `cert` contents:

    ```bash
    touch originalkeyfile.key
    ```

    ```bash
    touch originalcertfile.crt
    ```

1. Open the `key` file and copy all of the contents **between and including the begin and end key tags**, and then copy them into the new file created for it (in this example, `originalkeyfile.key`). Save the file.

1. Open the `cert` file and copy all of the contents **between and including the begin and end cert tags**, and then copy them into the new file created for it (in this example, `originalcertfile.crt`). Save the file.

1. Run the following commands (or use any other preferred encoding method) to convert the files into new files with base64 encoding:

    ```bash
    openssl base64 -in originalkeyfile.key -out base64keyfile.key
    ```

    ```bash
    openssl base64 -in originalcertfile.crt -out base64certfile.crt
    ```

1. Copy all of the contents from the new, encoded `key` file (in this example, `base64keyfile.key`) and paste them into the `key` variable in your `webserver` service's `LCP.json` file.

1. Copy all of the contents from the new, encoded `cert` file (in this example, `base64certfile.crt`) and paste them into the `crt` variable in your `webserver` service's `LCP.json` file.

The `key` and `cert` values are now encoded and usable in your web server configuration.

```{tip}
It is possible to include multiple values for the `cert` by concatenating certificates together into a single string, and then encoding the result in base-64 for the `crt` field.
```

The Network page shows any custom certificates, with a maximum of one per service. For more information, see [Custom Domains](./custom-domains.md).

![DXP Cloud shows the status of SSL certificates that cover custom domains.](./load-balancer/images/06.png)

## Environment Variables Reference

| Name | Value | Description |
| :--- | :--- | :--- |
| `cdn` | false | CDN is disabled by default; can be enabled by setting to `true` |
| `customDomains` | ["example.com", "www.example.com"] | Name of the custom domain; can list more than one |
| `targetPort` | 3000 | Port number for the load balancer |
| `key` | | SSL certificate's key in Base64 format. Group this in an [`ssl`](#adding-custom-ssl-certificates) object, or a [`certs`](#mapping-multiple-ssl-certificates-to-custom-domains) object (to list multiple certificates). |
| `crt` | | SSL certificate's crt in Base64 format. Group this in an [`ssl`](#adding-custom-ssl-certificates) object, or a [`certs`](#mapping-multiple-ssl-certificates-to-custom-domains) object (to list multiple certificates). |

## Additional Information

* [Private Network](./private-network.md)
* [VPN Integration Overview](./vpn-integration-overview.md)
* [Custom Domains](./custom-domains.md)
