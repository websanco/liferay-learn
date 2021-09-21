curl \
	-H "Content-Type: application/json" \
	-X PATCH \
	"http://localhost:8080/o/headless-admin-user/v1.0/accounts/${1}" \
	-d "{\"description\": \"Bar\"}" \
	-u "test@liferay.com:test"