curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/c/customobjects/batch" \
	-d "[{\"id\": ${1}, \"name\": \"Dog\"}, {\"id\": ${2}, \"name\": \"Easy\"}, {\"id\": ${3}, \"name\": \"Fox\"}]" \
	-u "test@liferay.com:test"