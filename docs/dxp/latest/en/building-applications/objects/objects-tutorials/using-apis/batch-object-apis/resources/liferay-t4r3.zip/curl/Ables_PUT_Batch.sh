curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/c/ables/batch" \
	-d "[{\"id\": ${1}, \"name\": \"Able 4\"}, {\"id\": ${2}, \"name\": \"Able 5\"}, {\"id\": ${3}, \"name\": \"Able 6\"}]" \
	-u "test@liferay.com:test"