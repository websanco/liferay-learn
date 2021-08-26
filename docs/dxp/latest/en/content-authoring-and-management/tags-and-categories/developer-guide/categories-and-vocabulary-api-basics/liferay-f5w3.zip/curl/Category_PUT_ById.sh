curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/headless-admin-taxonomy/v1.0/taxonomy-categories/${1}" \
	-d "{\"description\": \"Goo\", \"name\": \"Able\"}" \
	-u "test@liferay.com:test"