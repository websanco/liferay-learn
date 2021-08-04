curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/structured-contents" \
	-d "{\"friendlyUrlPath\": \"able\", \"title\": \"Able\", \"contentStructureId\": \"${2}\"}" \
	-u "test@liferay.com:test"