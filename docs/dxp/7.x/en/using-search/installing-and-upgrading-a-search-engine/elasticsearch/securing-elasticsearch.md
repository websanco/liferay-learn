# Securing Elasticsearch 

The very first thing you must do to secure Elasticsearch is enable X-Pack Security. After that you can begin configuring authentication and Transport Layer Security.

```note::
   **Elasticsearch 6.x:** If you're using Elasticsearch 6, you'll need a Liferay Enterprise Search (LES) subscription to use X-Pack. Starting with the Liferay Connector to Elasticsearch 7 (available on `Liferay Marketplace <../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md>`_), X-Pack security is included by default. X-Pack monitoring still requires LES.
```

## Enabling X-Pack Security

To enable security, add this setting in each Elasticsearch node's `[Elasticsearch Home]/config/elasticsearch.yml` file:

```yaml
xpack.security.enabled: true
```

Now you can set up X-Pack users.

## Set Up X-Pack Users

In a system using X-Pack Security and X-Pack Monitoring, these [built-in X-Pack users](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/built-in-users.html) are important:

* `kibana`
* `elastic`

Use the [`setup-passwords` command](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/setup-passwords.html) to set passwords for them:

```bash
./bin/elasticsearch-setup-passwords interactive
```

```note::
  The configurations shown below assumes all passwords are set to *liferay*. Use your own passwords for your installation.
```

```note::
  To update a build-in user's password, use Kibana's UI or the `Change Password API <https://www.elastic.co/guide/en/elasticsearch/reference/7.x/security-api-change-password.html>`_.
```

## Enable Transport Layer Security

Enabling Transport Layer Security (TLS) involves generating node certificates and keys, and applying them to the Elasticsearch servers and Liferay Servers (covered in [Connecting to Elasticsearch](./connecting-to-elasticsearch.md)).

### Generate Node Certificates

[Generate an X-Pack certificate](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html#node-certificates) for each node. Alternatively, use a certificate authority to obtain node certificates.

1. Generate X-Pack certificate authority using the X-Pack's [`certutil`](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/certutil.html) command:

    ```bash
    ./bin/elasticsearch-certutil ca --pem --ca-dn CN=localhost
    ```

    This generates a ZIP file.

1. Unzip the certificate authority ZIP file contents in the `[Elasticsearch Home]/config/certs` folder.

1. Generate X.509 certificates and private keys using the CA you created (replace `[Elasticsearch Home]` with your Elasticsearch Home absolute path):

    ```bash
    ./bin/elasticsearch-certutil cert --pem --ca-cert [Elasticsearch Home]/config/certs/ca.crt --ca-key [Elasticsearch Home]/config/certs/ca.key --dns localhost --ip 127.0.0.1 --name localhost
    ```

    This generates another ZIP file.

    ```note::
       The ``certutil`` command defaults to using the *PKSC#12* format for certificate generation. Since Kibana does not work with PKSC#12 certificates, the ``--pem`` option (generates the certificate in PEM format) is important if you're using X-Pack monitoring.
    ```

1. Extract the ZIP file contents in the `[Elasticsearch Home]/config/certs` folder.

    **Checkpoint:** You now have the following files in your `[Elasticsearch Home]/config/certs` folder:

    ```bash
    ca.crt
    ca.key
    localhost.crt
    localhost.key
    ```

1. Copy the files to each Elasticsearch and Liferay server node.

The certificates and keys are ready to use in your Elasticsearch configuration.

### Configure TLS for Elasticsearch 7

[Enable TLS](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html#enable-ssl) on each node via its `[Elasticsearch Home]/config/elasticsearch.yml` file.

1. Enable transport layer TLS with these settings in `elasticsearch.yml` for inter-node communication:

    ```yaml
    xpack.security.transport.ssl.enabled: true
    ```

1. Add the certificate, key and certificate authority paths to each node's `elasticsearch.yml`:

    ```yaml
    xpack.security.transport.ssl.certificate_authorities: [ "certs/ca.crt" ]
    xpack.security.transport.ssl.certificate: certs/localhost.key
    xpack.security.transport.ssl.key: certs/localhost.crt
    xpack.security.transport.ssl.verification_mode: certificate
    ```

    The example paths above assume you added the certificate to `[Elasticsearch Home]/config/`. 

1. Enable TLS on the HTTP layer to encrypt client communication:

    ```yaml
    xpack.security.http.ssl.enabled: true
    ```
1. Configure the certificate, key, and certificate authority paths to each node's `elasticsearch.yml`:

    ```yaml
    xpack.security.http.ssl.certificate_authorities: [ "certs/ca.crt" ]
    xpack.security.http.ssl.certificate: certs/localhost.crt
    xpack.security.http.ssl.key: certs/localhost.key
    xpack.security.http.ssl.verification_mode: certificate
    ```

### Configure TLS for Elasticsearch 6

The settings on Elasticsearch 6 are slightly different than those presented above for Elasticsearch 7. [Enable TLS](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html#enable-ssl) on each node via its `[Elasticsearch Home]/config/elasticsearch.yml` file.

1. Add the certificate, key and certificate authority paths to each node's `elasticsearch.yml`:

    ```yaml
    xpack.ssl.certificate_authorities: [ "certs/ca.crt" ]
    xpack.ssl.certificate: certs/localhost.crt 
    xpack.ssl.key: certs/localhost.key
    xpack.ssl.verification_mode: certificate 
    ```

    The example paths above assume you added the certificate to `[Elasticsearch Home]/config/`. 

1. Enable transport layer TLS with these settings in `elasticsearch.yml`:

    ```yaml
    xpack.security.transport.ssl.enabled: true
    ```

1. Enable TLS on the HTTP layer to encrypt client communication:

    ```yaml
    xpack.security.http.ssl.enabled: true
    ```

After X-Pack is installed and TLS is enabled, configure the X-Pack Security adapter in Liferay.

### Example Elasticsearch Security Configuration

For copying and pasting, here is the complete Elasticsearch 7 configuration (`elasticsearch.yml`) used in this guide (with the Elasticsearch 6 example commented out):

```yaml
# For Elasticsearch 7.3/7.4
cluster.name: LiferayElasticsearchCluster

# X-Pack Security
xpack.security.enabled: true

## TLS/SSL settings for Transport layer
xpack.security.transport.ssl.enabled: true 
xpack.security.transport.ssl.certificate_authorities : [ "certs/ca.crt" ]
xpack.security.transport.ssl.certificate: certs/localhost.crt
xpack.security.transport.ssl.key: certs/localhost.key
xpack.security.transport.ssl.verification_mode: certificate

# TLS/SSL settings for HTTP layer
xpack.security.http.ssl.enabled: true
xpack.security.http.ssl.certificate_authorities : [ "certs/ca.crt" ]
xpack.security.http.ssl.certificate: certs/localhost.crt
xpack.security.http.ssl.key: certs/localhost.key
xpack.security.http.ssl.verification_mode: certificate 

# Comment out when Kibana and Liferay's X-Pack Monitoring are also configured
#xpack.monitoring.collection.enabled: true

# For Elasticsearch 6.5/6.8
#cluster.name: LiferayElasticsearchCluster
#
# X-Pack Security
#xpack.security.enabled: true
#
# Enable TLS/SSL
#xpack.security.transport.ssl.enabled: true # To enable Transport level SSL for internode-communication
#xpack.security.http.ssl.enabled: true # To enable HTTP level SSL required by Kibana
#
## General TLS/SSL settings for both Transport and HTTP levels
#xpack.ssl.verification_mode: certificate 
#xpack.ssl.key: certs/localhost.key
#xpack.ssl.certificate: certs/localhost.crt
#xpack.ssl.certificate_authorities : [ "certs/ca.crt" ]
#
# Comment out when Kibana and Liferay's X-Pack Monitoring are also configured
#xpack.monitoring.collection.enabled: true
```

## Liferay 7.2: Install the Liferay Connector to X-Pack Security

```important::
   Skip these instructions if you're using Liferay 7.3 because it the Liferay Connector to Elasticsearch 7 includes X-Pack support already.
``

If you are on Liferay 7.2 and have a Liferay Enterprise Search subscription, [download](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search)  the Liferay Connector to X-Pack Security [Elastic Stack 6.x]. Install the LPKG file by copying it into the `Liferay Home/deploy` folder. 

To configure the X-Pack adapter, navigate to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*. Find the *Search* category and click on the *X-Pack Security* entry. You can enter the property values here, but it's more common to use a configuration file deployed to `[Liferay Home]/osgi/configs`. For the X-Pack security connector, create a file called

```sh
com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration.config
```

The exact contents of the file depend on your X-Pack setup. To configure the adapter according to the Elasticsearch setup documented here, populate the file like this:

```properties
sslKeyPath="/path/to/localhost.key"
sslCertificatePath="/path/to/localhost.crt"
certificateFormat="PEM"
requiresAuthentication="true"
username="elastic"
password="liferay"
sslCertificateAuthoritiesPaths="/path/to/ca.crt"
transportSSLVerificationMode="certificate"
transportSSLEnabled="true"
```

The `password` must match the one you set during the X-Pack user password setup above. 

The certificate and key files referenced here are the same ones used on the Elasticsearch server. Copy them to the Liferay server and update their paths in the configuration accordingly.

Enable authentication by setting `requiresAuthentication` to `true` and providing the credentials for the Elasticsearch user. For TLS, enable transport TLS, set the certificate verification mode and certificate format, and provide the path to the certificate, key, and certificate authority. Of course, the exact values depend on your X-Pack configuration. 

Here's the complete list of configuration options for the X-Pack Connector:

* `sslKeyPath`
* `sslCertificatePath`
* `sslCertificateAuthoritiesPaths`
* `certificateFormat`
* `requiresAuthentication`
* `username`
* `password`
* `transportSSLVerificationMode`
* `transportSSLEnabled`
* `sslKeystorePath`
* `sslKeystorePassword`
* `sslTruststorePath`
* `sslTruststorePassword`

When you're finished configuring X-Pack Security, restart Elasticsearch. These steps require a full Elasticsearch cluster restart.

## Disable Elasticsearch Deprecation Logging

Some Elasticsearch APIs used by Liferay's Elasticsearch 6 connector were deprecated as of Elasticsearch 6.6 and 6.7. This can result WARN log entries in Elasticsearch's deprecation log when Liferay is configured with Elasticsearch 6.8.x and X-Pack Security is enabled:

```sh
[2019-07-16T14:47:05,779][WARN ][o.e.d.c.j.Joda           ] [
ode_name]'y' year should be replaced with 'u'. Use 'y' for year-of-era. Prefix your date format with '8' to use the new specifier.
[2019-07-16T14:47:06,007][WARN ][o.e.d.c.s.Settings       ] [
ode_name][xpack.ssl.certificate] setting was deprecated in Elasticsearch and will be removed in a future release! See the breaking changes documentation for the next major version.
[2019-07-16T14:47:06,007][WARN ][o.e.d.c.s.Settings       ] [
ode_name][xpack.ssl.certificate_authorities] setting was deprecated in Elasticsearch and will be removed in a future release! See the breaking changes documentation for the next major version.
[2019-07-16T14:47:06,008][WARN ][o.e.d.c.s.Settings       ] [
ode_name][xpack.ssl.key] setting was deprecated in Elasticsearch and will be removed in a future release! See the breaking changes documentation for the next major version.
[2019-07-16T14:47:06,463][WARN ][o.e.d.x.c.s.SSLService   ] [
ode_name]SSL configuration [xpack.http.ssl] relies upon fallback to another configuration for [key configuration, trust configuration], which is deprecated.
[2019-07-16T14:47:06,464][WARN ][o.e.d.x.c.s.SSLService   ] [
ode_name]SSL configuration [xpack.security.transport.ssl.] relies upon fallback to another configuration for [key configuration, trust configuration], which is deprecated.
1. 07-16T14:47:05,779][WARN ][o.e.d.c.j.Joda           ] [
```

These warnings do not signal any functional issues, and can be disabled (see [Deprecation Logging](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/logging.html#deprecation-logging) to learn how).
