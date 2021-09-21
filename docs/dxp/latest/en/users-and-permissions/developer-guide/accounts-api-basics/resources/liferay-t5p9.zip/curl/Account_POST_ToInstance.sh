curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-admin-user/v1.0/accounts" \
	-d "{\"description\": \"Foo\", \"name\": \"Able\"}" \
	-u "test@liferay.com:test"