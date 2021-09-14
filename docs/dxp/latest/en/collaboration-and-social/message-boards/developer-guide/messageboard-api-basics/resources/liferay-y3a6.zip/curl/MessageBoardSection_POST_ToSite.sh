curl \
	-H 'Content-Type: application/json' \
	-X 'POST' \
	"http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/message-board-sections" \
	-d "{\"description\": \"Foo\", \"title\": \"Able Section\"}" \
	-u "test@liferay.com:test"