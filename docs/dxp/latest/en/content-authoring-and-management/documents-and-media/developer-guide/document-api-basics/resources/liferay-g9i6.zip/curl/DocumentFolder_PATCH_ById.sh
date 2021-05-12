curl \
	-H "Content-Type: application/json" \
	-X PATCH \
	"http://localhost:8080/o/headless-delivery/v1.0/document-folders/${1}" \
	-d "{\"description\": \"Bar\"}" \
	-u "test@liferay.com:test"