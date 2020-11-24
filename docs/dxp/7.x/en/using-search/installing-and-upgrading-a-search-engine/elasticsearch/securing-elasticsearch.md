# Securing Elasticsearch 

The very first thing you must do to secure Elasticsearch is [enable X-Pack Security](#enable-x-pack-security). After that you can begin configuring authentication and encrypted communication.

```note::
   **Elasticsearch 6.x:** If you're using Elasticsearch 6, you'll need a Liferay Enterprise Search (LES) subscription and the Liferay Enterprise Search Security application to use Elastic's X-Pack Security. Starting with the Liferay Connector to Elasticsearch 7 (available on `the Customer Downloads portal <https://customer.liferay.com/downloads>`_ and bundled in Liferay 7.3), support for Elastic's X-Pack security is included by default. To integrate with Elastic's X-Pack monitoring, LES is required.
```

## Enable X-Pack Security

To enable security, add this setting in each Elasticsearch node's `[Elasticsearch Home]/config/elasticsearch.yml` file:

```yaml
xpack.security.enabled: true
```

Now you can set up X-Pack users.

## Set Up X-Pack Users

In a system using X-Pack, these [built-in X-Pack users](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/built-in-users.html) are important:

* `kibana`
* `elastic`

On your Elasticsearch server, use the [`setup-passwords` command](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/setup-passwords.html) to set passwords for them:

```bash
./bin/elasticsearch-setup-passwords interactive
```

```note::
  The configurations shown below assume all passwords are set to *liferay*. Use your own passwords for your installation.
```

```note::
  To update a built-in user's password, use Kibana's UI or the `Change Password API <https://www.elastic.co/guide/en/elasticsearch/reference/7.x/security-api-change-password.html>`_.
```

## Encrypting Elasticsearch Communication

Enabling Transport Layer Security (TLS) involves generating node certificates and keys and applying them to the Elasticsearch servers and Liferay servers.

### Generate Node Certificates

[Generate a certificate](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html#node-certificates) for each node (or you can generate a certificate that is for all nodes and clients like Liferay DXP). Alternatively, use your certificate authority to obtain node certificates.

1. Generate X-Pack certificate authority using the X-Pack's [`certutil`](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/certutil.html) command:

    ```bash
    ./bin/elasticsearch-certutil ca --ca-dn CN=elastic-ca
    ```

    This generates a file called `elastic-stack-ca.p12` unless you chose a different name.

1. Move `elastic-stack-ca.p12` to the `[Elasticsearch Home]/config/certs` folder.

1. Generate X.509 certificates and private keys (in `PKCS#12` format) using the CA you created:

    ```bash
    ./bin/elasticsearch-certutil cert --ca config/certs/elastic-stack-ca.p12 --ca-pass liferay --dns localhost --ip 127.0.0.1 --name elastic-nodes
    ```

    If you are using a `PEM` format CA certificate, run:
    
    ```bash
    ./bin/elasticsearch-certutil cert --pem --ca-cert config/certs/ca.crt --ca-key config/certs/ca.key --dns localhost --ip 127.0.0.1 --name elastic-nodes
    ```

    ```note::
    On DXP 7.3, only the following keystore types can be used in the Elasticsearch 7 connector configuration: https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#keystore-types
    ```

    To generate a certificate that works with multiple hosts (for example to use the same certificate on all Elasticsearch and DXP servers) use

    ```
    --dns localhost,example.com,es-node1,es-node2,es-node3 --ip 127.0.0.1,<IPv4-address-2>,<IPv4-address-3>,<IPv4-address-4>
    ```

    This generates another file called `elastic-nodes.p12` (you can name it differently).

    ```note::
       The ``certutil`` command defaults to using the *PKSC#12* format for certificate generation. Since Kibana does not work with PKSC#12 certificates, the ``--pem`` option (generates the certificate in *PEM* format) is important if you're using Kibana and *Liferay Enterprise Search Monitoring*. This will generate you two files in each case in a form of a ZIP file: ``ca.crt`` and ``ca.key``, ``elastic-nodes.crt`` and ``elastic-nodes.key``. Unzip the archives' contents in the *[Elasticsearch Home]/config/certs* folder.
    ```

1. Move `elastic-nodes.p12` to the `[Elasticsearch Home]/config/certs` folder.

    **Checkpoint:** You now have the following files in your `[Elasticsearch Home]/config/certs` folder:

    ```bash
    elastic-nodes.p12
    elastic-stack-ca.p12
    ```
    
    or
    
    ```bash
    ca.crt
    ca.key
    elastic-nodes.crt
    elastic-nodes.key
    ```

1. Copy the files to the same folder on each Elasticsearch and Liferay server node.

The certificates and keys are ready to use in your Elasticsearch configuration.

### Configure TLS for Elasticsearch

[Enable TLS](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls.html#enable-ssl) on each node via its `[Elasticsearch Home]/config/elasticsearch.yml` file.

1. Enable transport layer TLS with these settings in `elasticsearch.yml` for inter-node communication:

    ```yaml
    xpack.security.transport.ssl.enabled: true
    ```

1. Add the certificate, key and certificate authority paths to each node's `elasticsearch.yml`:

    ```yaml
    # PKCS#12
    xpack.security.transport.ssl.keystore.path: certs/elastic-nodes.p12
    xpack.security.transport.ssl.keystore.password: liferay
    xpack.security.transport.ssl.truststore.path: certs/elastic-nodes.p12
    xpack.security.transport.ssl.truststore.password: liferay
    # PEM
    #xpack.security.transport.ssl.certificate_authorities: [ "certs/ca.crt" ]
    #xpack.security.transport.ssl.certificate: certs/elastic-nodes.key
    #xpack.security.transport.ssl.key: certs/elastic-nodes.crt
    
    xpack.security.transport.ssl.verification_mode: certificate
    ```

    The example paths above assume you added the certificates to `[Elasticsearch Home]/config/certs`. 

1. Enable TLS on the HTTP layer to encrypt client communication:

    ```yaml
    xpack.security.http.ssl.enabled: true
    ```

1. Configure the certificate, key, and certificate authority paths to each node's `elasticsearch.yml`:

    ```yaml
    # PKCS#12
    xpack.security.http.ssl.keystore.path: certs/elastic-nodes.p12
    xpack.security.http.ssl.keystore.password: liferay
    xpack.security.http.ssl.truststore.path: certs/elastic-nodes.p12
    xpack.security.http.ssl.truststore.password: liferay
    # PEM
    #xpack.security.http.ssl.certificate_authorities: [ "certs/ca.crt" ]
    #xpack.security.http.ssl.certificate: certs/elastic-nodes.crt
    #xpack.security.http.ssl.key: certs/elastic-nodes.key
    
    xpack.security.http.ssl.verification_mode: certificate
    ```

### Example Elasticsearch Security Configuration

Here is the complete Elasticsearch 7 configuration (`elasticsearch.yml`; applies equally to Elasticsearch 6.5.x+):

```yaml
cluster.name: LiferayElasticsearchCluster

# X-Pack Security
xpack.security.enabled: true

## TLS/SSL settings for Transport layer (PKCS#12)
xpack.security.transport.ssl.keystore.path: certs/elastic-nodes.p12
xpack.security.transport.ssl.keystore.password: liferay
xpack.security.transport.ssl.truststore.path: certs/elastic-nodes.p12
xpack.security.transport.ssl.truststore.password: liferay
xpack.security.transport.ssl.verification_mode: certificate

# TLS/SSL settings for HTTP layer (PKCS#12)
xpack.security.http.ssl.keystore.path: certs/elastic-nodes.p12
xpack.security.http.ssl.keystore.password: liferay
xpack.security.http.ssl.truststore.path: certs/elastic-nodes.p12
xpack.security.http.ssl.truststore.password: liferay
xpack.security.http.ssl.verification_mode: certificate

# Comment out when Kibana and Liferay's X-Pack Monitoring are also configured
#xpack.monitoring.collection.enabled: true
```

## Configure Secure Connection to Elasticsearch in Liferay DXP

On Liferay, security can be configured in the Control Panel or using a configuration file. Navigate to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*. Find the *Search* category and click on the *Elasticsearch 7* entry on Liferay DXP 7.3 or *X-Pack Security* entry on Liferay DXP 7.2. You can enter the property values here, but it's more common to use a configuration file deployed to `[Liferay Home]/osgi/configs`.

The exact contents of the file depend on your X-Pack setup. The `password` must match the one you set during the X-Pack user password setup above. 

The certificate and key files referenced here are the same ones used on the Elasticsearch server nodes. Copy them to the Liferay server and update their paths in the configuration accordingly.

Besides configuring TLS, we also enable authentication in the settings below by setting `authenticationEnabled`/`requiresAuthentication` to `true` and providing the credentials for the Elasticsearch user that will be used by Liferay DXP to authenticate into Elasticsearch.

When you're finished configuring security, restart Elasticsearch. These steps require a full Elasticsearch cluster restart.

### Configure Secure Connection to Elasticsearch in Liferay DXP 7.3

```tip::
   The `Installing Elasticsearch <./installing-elasticsearch.md>`__ and `Connecting to Elasticsearch <./connecting-to-elasticsearch.md>`__ articles default to enabling and configuring security, so you can also visit those articles for the 7.3 applicable security configurations.
```

The Liferay Connector to Elasticsearch 7 bundled with DXP 7.3 includes X-Pack Security support. Remember that only the following keystore types can be used in the Elasticsearch 7 connector configuration: https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#keystore-types.

Create a file called

```bash
com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
```

and populate the file like this:

```properties
operationMode="REMOTE"
productionModeEnabled=B"true"
username="elastic"
password="liferay"
authenticationEnabled=B"true"
httpSSLEnabled=B"true"
networkHostAddresses=["https://localhost:9200"]
truststorePassword="liferay"
truststorePath="/PATH/TO/elastic-nodes.p12"
truststoreType="pkcs12"
```

### Configure Secure Connection to Elasticsearch in Liferay DXP 7.2

All Liferay connector's to Elasticsearch 7 include X-Pack Security support.

```note::
   If you are on Liferay 7.2 and Elasticsearch 6.x and have a Liferay Enterprise Search subscription, `download <https://customer.liferay.com/downloads/-/download/liferay-enterprise-search-for-liferay-dxp-7-2>`__ the `Liferay Enterprise Search Security` application. Install the LPKG file by copying it into the ``[Liferay Home]/deploy`` folder.
```

Create a file called

```bash
com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration.config
```

(or `com.liferay.portal.search.elasticsearch6.configuration.XPackSecurityConfiguration.config` if you are using Elastic Stack 6.x and the `Liferay Enterprise Search Security` application) 

and populate the file like this (`PKCS#12`):

```properties
certificateFormat="PKCS#12"
sslKeystorePath="/PATH/TO/elastic-nodes.p12"
sslKeystorePassword="liferay"
sslTruststorePath="/PATH/TO/elastic-nodes.p12"
sslTruststorePassword="liferay"
requiresAuthentication=B"true"
username="elastic"
password="liferay"
transportSSLVerificationMode="certificate"
transportSSLEnabled=B"true"
```

or like this if you are using `PEM` format certificates:

```properties
certificateFormat="PEM"
sslKeyPath="/PATH/TO/elastic-nodes.key"
sslCertificatePath="/PATH/TO/elastic-nodes.crt"
requiresAuthentication=B"true"
username="elastic"
password="liferay"
sslCertificateAuthoritiesPaths="/PATH/TO/ca.crt"
transportSSLVerificationMode="certificate"
transportSSLEnabled="true"
```

### Elasticsearch 7 Connector Security Settings in Liferay DXP 7.3

Here's the complete list of security settings for the Elasticsearch 7 connector in DXP 7.3:

* `authenticationEnabled`: Enable or disable authentication to Elasticsearch with a username and password. Defaults to `false`.
* `username`: Set the username for authenticating to Elasticsearch if Authentication Enabled is checked. Defaults to `elastic`.
* `password`: Set the password for authenticating to Elasticsearch if Authentication Enabled is checked.
* `httpSSLEnabled`: Enable or disable TLS/SSL. Defaults to `false`.
* `truststoreType`: Set the truststore type if HTTP SSL Enabled is checked. Defaults to `pkcs12`.
* `truststorePath`: Set the path to the truststore file if HTTP SSL Enabled is checked. Defaults to `/path/to/localhost.p12`.
* `truststorePassword`: Set the password to the truststore if HTTP SSL Enabled is checked.

### Enterprise Search Security / X-Pack Security Settings in Liferay DXP 7.2

Here's the complete list of settings for the X-Pack Security configuration on DXP 7.2:

* `sslKeyPath`: Set the path to the PEM encoded file containing the private key. Defaults to `/path/to/instance.key`.
* `sslCertificatePath`: Set the path to a PEM encoded file containing the certificate (or certificate chain) that will be presented to clients when they connect. Defaults to `/path/to/instance.crt`.
* `sslCertificateAuthoritiesPaths`: Provide a list of paths to trusted PEM encoded certificate files. Defaults to `["/path/to/ca.crt"]`.
* `certificateFormat`: Specify the certificate format (`PEM` or `PKCS#12`). Defaults to `PKCS#12`.
* `requiresAuthentication`: If enabled, connections with Elasticsearch/X-Pack are authenticated with the configured username and password. Defaults to `false`.
* `username`: If Requires Authentication is enabled, set the username for authenticating to Elasticsearch is required. Defaults to `elastic`.
* `password`: If Requires Authentication is enabled, a password is required.
* `transportSSLVerificationMode`: Specify the verification type (`none`, `certificate`, or `full`) when using LDAP to protect against man in the middle attacks and certificate forgery. Defaults to `certificate`.
* `transportSSLEnabled`: Set or disable TLS/SSL. Defaults to `false`.
* `sslKeystorePath`: Set the path to the keystore holding the private key and certificate. Defaults to `/path/to/elastic-certificates.p12`.
* `sslKeystorePassword`: Set the password to the PKCS#12 file.
* `sslTruststorePath`: Set the path to the truststore file. Defaults to `/path/to/elastic-certificates.p12`.
* `sslTruststorePassword`: Set the password to the truststore.
