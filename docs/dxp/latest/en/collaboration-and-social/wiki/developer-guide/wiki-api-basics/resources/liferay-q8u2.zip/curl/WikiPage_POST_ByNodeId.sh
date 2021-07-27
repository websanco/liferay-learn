curl \
	-H 'Content-Type: application/json' \
	-X 'POST' \
	"http://localhost:8080/o/headless-delivery/v1.0/wiki-nodes/${1}/wiki-pages" \
	-d "{\"content\": \"This is a Wiki Page.\", \"encodingFormat\": \"text/x-wiki\", \"headline\": \"Wiki Page\"}" \
	-u "test@liferay.com:test"