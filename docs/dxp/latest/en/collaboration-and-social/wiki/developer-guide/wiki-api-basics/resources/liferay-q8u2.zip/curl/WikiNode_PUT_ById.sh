curl \
	-H 'Content-Type: application/json' \
	-X 'PUT' \
	"http://localhost:8080/o/headless-delivery/v1.0/wiki-nodes/${1}" \
	-d "{\"description\": \"Bar\", \"name\": \"Baker Node\"}" \
	-u "test@liferay.com:test"