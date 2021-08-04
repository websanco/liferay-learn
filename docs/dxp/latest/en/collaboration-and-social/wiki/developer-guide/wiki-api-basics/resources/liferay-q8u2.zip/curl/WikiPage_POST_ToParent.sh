curl \
	-H 'Content-Type: application/json' \
	-X 'POST' \
	"http://localhost:8080/o/headless-delivery/v1.0/wiki-pages/${1}/wiki-pages" \
	-d "{\"content\": \"Foo\", \"encodingFormat\": \"text/x-wiki\", \"headline\": \"Able Child\"}" \
	-u "test@liferay.com:test"