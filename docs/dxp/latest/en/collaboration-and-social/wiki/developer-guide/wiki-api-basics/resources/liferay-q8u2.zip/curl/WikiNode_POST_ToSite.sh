curl \
	-H 'Content-Type: application/json' \
	-X 'POST' \
	"http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/wiki-nodes" \
	-d "{\"name\": \"Wiki Node\"}" \
	-u "test@liferay.com:test"