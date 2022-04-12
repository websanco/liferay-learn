curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/c/customobjects/${1}" \
	-d "{\"name\": \"Bar\"}" \
	-u "test@liferay.com:test"