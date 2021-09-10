curl \
	-H 'Content-Type: application/json' \
	-X 'PATCH' \
	"http://localhost:8080/o/headless-delivery/v1.0/message-board-threads/${1}" \
	-d "{\"articleBody\": \"Bar\", \"headline\": \"Baker Thread\"}" \
	-u "test@liferay.com:test"