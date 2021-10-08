curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/headless-admin-user/v1.0/accounts/${1}" \
	-d "{\"description\": \"Goo\", \"name\": \"Baker\"}" \
	-u "test@liferay.com:test"