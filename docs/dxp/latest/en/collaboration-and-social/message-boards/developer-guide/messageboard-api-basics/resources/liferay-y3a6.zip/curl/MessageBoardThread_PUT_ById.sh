curl \
	-H 'Content-Type: application/json' \
	-X 'PUT' \
	"http://localhost:8080/o/headless-delivery/v1.0/message-board-threads/${1}" \
	-d "{\"articleBody\": \"Goo\", \"headline\": \"Charlie Thread\"}" \
	-u "test@liferay.com:test"