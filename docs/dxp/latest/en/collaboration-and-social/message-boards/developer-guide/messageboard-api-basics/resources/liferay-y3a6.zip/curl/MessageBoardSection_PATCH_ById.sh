curl \
	-H 'Content-Type: application/json' \
	-X 'PATCH' \
	"http://localhost:8080/o/headless-delivery/v1.0/message-board-sections/${1}" \
	-d "{\"description\": \"Bar\"}" \
	-u "test@liferay.com:test"