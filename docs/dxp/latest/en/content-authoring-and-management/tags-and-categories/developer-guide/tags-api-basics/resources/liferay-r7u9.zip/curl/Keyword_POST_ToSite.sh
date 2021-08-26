curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-admin-taxonomy/v1.0/sites/${1}/keywords" \
	-d "{\"name\": \"Foo\"}" \
	-u "test@liferay.com:test"