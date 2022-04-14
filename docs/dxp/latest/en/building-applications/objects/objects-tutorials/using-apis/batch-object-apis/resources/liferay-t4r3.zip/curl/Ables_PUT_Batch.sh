curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/c/ables/batch" \
	-d "[{\"id\": ${1}, \"name\": \"Able Four\"}, {\"id\": ${2}, \"name\": \"Able Five\"}, {\"id\": ${3}, \"name\": \"Able Six\"}]" \
	-u "test@liferay.com:test"