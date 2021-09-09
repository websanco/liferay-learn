curl \
	-H 'Content-Type: application/json' \
	-X 'PUT' \
	"http://localhost:8080/o/headless-delivery//v1.0/message-board-messages/${1}" \
	-d "{\"articleBody\": \"Goo\", \"encodingFormat\": \"bbcode\", \"headline\": \"Charlie Message\"}" \
	-u "test@liferay.com:test"