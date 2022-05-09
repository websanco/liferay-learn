curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/c/ables/batch" \
	-d "[{\"id\": ${1}, \"name\": \"Able One\"}, {\"id\": ${2}, \"name\": \"Able Two\"}, {\"id\": ${3}, \"name\": \"Able Three\"}]" \
	-u "test@liferay.com:learn"