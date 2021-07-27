curl \
    -H 'Content-Type: application/json' \
    -X 'PUT' \
    "http://localhost:8080/o/headless-delivery/v1.0/wiki-pages/${1}" \
    -d "{\"content\": \"This is the updated page content.\", \"encodingFormat\": \"text/x-wiki\", \"headline\": \"Updated Sample Wiki Page\"}" \
    -u "test@liferay.com:test"