curl \
	-H "Content-Type: application/json" \
	-X PATCH \
	"http://localhost:8080/o/headless-delivery/v1.0/structured-contents/${1}" \
	-d "{\"friendlyUrlPath\": \"my-new-web-friendly-url\"}" \
	-u "test@liferay.com:test"
