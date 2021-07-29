curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/structured-contents/by-external-reference-code/${2}" \
	-d "{\"contentStructureId\": \"${3}\", \"title\": \"Updated Web Content article!\"}" \
	-u "test@liferay.com:test"
