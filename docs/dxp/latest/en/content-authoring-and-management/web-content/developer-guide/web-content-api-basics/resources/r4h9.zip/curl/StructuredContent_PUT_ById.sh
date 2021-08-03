curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/headless-delivery/v1.0/structured-contents/${1}" \
	-d "{\"contentFields\": [{\"contentFieldValue\": {\"data\": \"<p>Goo</p>\"}, \"dataType\": \"string\", \"name\": \"content\"}], \"contentStructureId\": \"${2}\", \"friendlyUrlPath\": \"charlie\", \"title\": \"Charlie\"}" \
	-u "test@liferay.com:test"