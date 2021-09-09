curl \
	-H 'Content-Type: application/json' \
	-X 'POST' \
	"http://localhost:8080/o/headless-delivery/v1.0/message-board-threads/${1}/message-board-messages" \
	-d "{\"articleBody\": \"Foo\", \"encodingFormat\": \"bbcode\", \"headline\": \"Able Message\"}" \
	-u "test@liferay.com:test"