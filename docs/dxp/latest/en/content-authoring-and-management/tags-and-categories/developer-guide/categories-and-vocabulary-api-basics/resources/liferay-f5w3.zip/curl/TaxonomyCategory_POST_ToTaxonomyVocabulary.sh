curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-admin-taxonomy/v1.0/taxonomy-vocabularies/${1}/taxonomy-categories" \
	-d "{\"description\": \"Foo\", \"name\": \"Able\"}" \
	-u "test@liferay.com:test"