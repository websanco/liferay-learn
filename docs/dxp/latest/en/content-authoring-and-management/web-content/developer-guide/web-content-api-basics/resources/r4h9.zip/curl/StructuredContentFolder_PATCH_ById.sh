curl \
    -H "Content-Type: application/json" \
	-X PATCH \
	"http://localhost:8080/o/headless-delivery/v1.0/structured-content-folders/${1}" \
	-d "{\"name\": \"Bar\"}" \
	-u "test@liferay.com:test"