curl \
    -H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/structured-content-folders" \
	-d "{\"description\": \"Foo\", \"name\": \"Able Folder\"}" \
	-u "test@liferay.com:test"