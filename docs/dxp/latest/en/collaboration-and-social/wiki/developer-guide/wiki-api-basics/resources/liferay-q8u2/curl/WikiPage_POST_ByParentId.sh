curl \
    -H 'Content-Type: application/json' \
    -X 'POST' \
    "http://localhost:8080/o/headless-delivery/v1.0/wiki-pages/${1}/wiki-pages" \
    -d "{\"content\": \"This is a child page.\", \"encodingFormat\": \"text/x-wiki\", \"headline\": \"Sample Child Page\"}" \
    -u "test@liferay.com:test"