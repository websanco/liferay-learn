curl \
    -H 'Content-Type: application/json' \
    -X 'PUT' \
    "http://localhost:8080/o/headless-delivery/v1.0/wiki-pages/${1}" \
    -d "{\"content\": \"This is the updated page content.\", \"encodingFormat\": \"text/x-wiki\", \"headline\": \"Updated Sample Wiki Page\", \"wikiNodeId\": ${1}}" \
    -u "test@liferay.com:test"
# Why is put creating a page, instead of replacing the existing page? Consider removing wikiNodeId...