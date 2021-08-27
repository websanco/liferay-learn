curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/structured-contents" \
	-d "{\"contentFields\": [{\"contentFieldValue\": {\"data\": \"Foo\"}, \"name\": \"TextReference\"}, {\"contentFieldValue\": {\"image\": {\"description\": \"Foo alt-image description\", \"id\": \"${3}\"}}, \"name\": \"ImageReference\"}, {\"contentFieldValue\": {\"data\": \"2021-08-30T00:00:00Z\"}, \"dataType\": \"date\", \"name\": \"DateReference\"}, {\"contentFieldValue\": {\"data\": \"Foo\"}, \"name\": \"SingleSelectionReference\"}], \"contentStructureId\": \"${2}\", \"title\": \"Able Article\"}" \
	-u "test@liferay.com:test"