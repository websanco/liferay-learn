curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/c/customobjects/batch" \
	-d "[{\"name\": \"Able\"}, {\"name\": \"Baker\"}, {\"name\": \"Charlie\"}]" \
	-u "test@liferay.com:test"