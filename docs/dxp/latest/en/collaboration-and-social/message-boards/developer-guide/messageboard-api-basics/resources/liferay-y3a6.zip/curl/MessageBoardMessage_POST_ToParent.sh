curl \
	-H 'Content-Type: application/json' \
	-X 'POST' \
	"http://localhost:8080/o/headless-delivery/v1.0/message-board-messages/${1}/message-board-messages" \
	-d "{\"articleBody\": \"Foo\", \"headline\": \"Dog Message\"}" \
	-u "test@liferay.com:test"