curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/c/customobjects/" \
	-d "{\"name\": \"Foo\"}" \
	-u "test@liferay.com:test"