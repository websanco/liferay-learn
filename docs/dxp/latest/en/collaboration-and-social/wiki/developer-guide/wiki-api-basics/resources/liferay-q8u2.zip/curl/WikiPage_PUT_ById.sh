curl \
	-H 'Content-Type: application/json' \
	-X 'PUT' \
	"http://localhost:8080/o/headless-delivery/v1.0/wiki-pages/${1}" \
	-d "{\"content\": \"Goo\", \"encodingFormat\": \"text/x-wiki\", \"headline\": \"Able Page\"}" \
	-u "test@liferay.com:test"