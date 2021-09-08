curl \
	-H 'Content-Type: application/json' \
	-X 'PUT' \
	"http://localhost:8080/o/headless-delivery/v1.0/message-board-sections/${1}" \
	-d "{\"description\": \"Goo\", \"title\": \"Charlie Section\"}" \
	-u "test@liferay.com:test"