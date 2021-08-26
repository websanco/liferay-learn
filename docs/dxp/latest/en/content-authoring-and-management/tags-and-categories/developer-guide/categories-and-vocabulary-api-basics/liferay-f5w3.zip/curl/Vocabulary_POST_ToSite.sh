curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-admin-taxonomy/v1.0/sites/${1}/taxonomy-vocabularies" \
	-d "{\"description\": \"Foo\", \"name\": \"Able\"}" \
	-u "test@liferay.com:test"