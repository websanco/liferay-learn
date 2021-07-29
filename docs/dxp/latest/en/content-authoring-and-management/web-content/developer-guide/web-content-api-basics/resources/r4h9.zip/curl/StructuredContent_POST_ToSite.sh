curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/structured-contents" \
	-d "{\"contentFields\": [{\"contentFieldValue\": {\"data\": \"<p>Foo</p>\"}, \"dataType\": \"string\", \"name\": \"content\"}], \"contentStructureId\": \"${2}\", \"title\": \"Able\"}" \
	-u "test@liferay.com:test"