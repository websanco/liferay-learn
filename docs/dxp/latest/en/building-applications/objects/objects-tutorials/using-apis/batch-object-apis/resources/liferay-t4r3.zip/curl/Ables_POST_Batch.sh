curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/c/ables/batch" \
	-d "[{\"name\": \"Able One\"}, {\"name\": \"Able Two\"}, {\"name\": \"Able Three\"}]" \
	-u "test@liferay.com:test"