curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/headless-delivery/v1.0/structured-contents/${1}" \
	-d "{\"contentStructureId\": \"${2}\", \"title\": \"Goo\", \"friendlyUrlPath\": \"goo-url\"}" \
	-u "test@liferay.com:test"