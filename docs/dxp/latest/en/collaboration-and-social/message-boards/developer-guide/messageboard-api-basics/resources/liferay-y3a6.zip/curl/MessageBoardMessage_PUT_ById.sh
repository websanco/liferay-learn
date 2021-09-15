curl \
	-H 'Content-Type: application/json' \
	-X 'PUT' \
	"http://localhost:8080/o/headless-delivery/v1.0/message-board-messages/${1}" \
	-d "{\"articleBody\": \"Goo\", \"headline\": \"Baker Message\"}" \
	-u "test@liferay.com:test"