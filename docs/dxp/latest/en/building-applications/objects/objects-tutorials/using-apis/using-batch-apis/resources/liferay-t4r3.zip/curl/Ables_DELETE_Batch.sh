curl \
	-H "Content-Type: application/json" \
	-X DELETE \
	"http://localhost:8080/o/c/ables/batch" \
	-d "[{\"id\": ${1}}, {\"id\": ${2}}, {\"id\": ${3}}]" \
	-u "test@liferay.com:learn"