curl \
    -H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/headless-delivery/v1.0/structured-content-folders/${1}" \
	-d "{\"description\": \"Goo\", \"name\": \"Baker Folder\"}" \
	-u "test@liferay.com:test"