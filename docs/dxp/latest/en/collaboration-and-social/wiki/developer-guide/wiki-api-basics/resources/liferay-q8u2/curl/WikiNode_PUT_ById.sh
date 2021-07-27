curl \
    -X 'PUT' \
    "http://localhost:8080/o/headless-delivery/v1.0/wiki-nodes/${1}" \
    -d "{\"name\": \"Updated Sample Wiki Node\"}" \
	-u "test@liferay.com:test"

    # Removed
    # -H 'Content-Type: application/json' \