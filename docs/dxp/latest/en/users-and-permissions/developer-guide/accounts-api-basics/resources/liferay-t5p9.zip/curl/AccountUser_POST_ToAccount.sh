curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-admin-user/v1.0/accounts/${1}/user-accounts" \
	-d "{\"alternateName\": \"Able\", \"emailAddress\": \"able@liferay.com\", \"familyName\": \"Foo\", \"givenName\": \"Able\"}" \
	-u "test@liferay.com:test"