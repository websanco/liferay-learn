# Elasticsearch Clustering with Docker

To configure an Elasticsearch cluster of Docker containers, use these files:

- `instances.yml`
- `.env`
- `docker-compose.yml`
- `create-certs.yml`

> **Related Elasticsearch Documentation:** 
> - [Install Elasticsearch with Docker](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/docker.html)
> - [Encrypt Elasticsearch with Docker](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/configuring-tls-docker.html)

```tip::
   **Docker:** For a simple single-node test cluster, you can specify the cluster name (and any other configuration options) when first creating and starting the container (see `Start Elasticsearch`_ for an example ``docker run ...`` directive).
```

The `docker-compose.yml` will contain `services` entries for each node and some common `volume` and `network` properties:

```yaml
version: '2.2'
services:
  es01:
    image: docker.elastic.co/elasticsearch/elasticsearch:7.9.0
    container_name: es01
    environment:
      - node.name=es01
      - cluster.name=es-docker-cluster
      - discovery.seed_hosts=es02,es03
      - cluster.initial_master_nodes=es01,es02,es03
      - bootstrap.memory_lock=true
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
      # include any additional settings, like ssl and http security
    ulimits:
      memlock:
        soft: -1
        hard: -1
    volumes:
      - data01:/usr/share/elasticsearch/data
    ports:
      - 9200:9200
    networks:
      - elastic
  # ...
  # more nodes
  # ...
volumes:
  data01:
    driver: local
  data02:
    driver: local
  data03:
    driver: local

networks:
  elastic:
    driver: bridge
```
<!-- add security settings-->

## Start Elasticsearch

```bash
docker-compose up
```
