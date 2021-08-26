curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/headless-admin-taxonomy/v1.0/keywords/${1}" \
	-d "{\"name\": \"Bar\"}" \
	-u "test@liferay.com:test"