curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/c/ables/${1}" \
	-d "{\"name\": \"Able One\"}" \
	-u "test@liferay.com:learn"