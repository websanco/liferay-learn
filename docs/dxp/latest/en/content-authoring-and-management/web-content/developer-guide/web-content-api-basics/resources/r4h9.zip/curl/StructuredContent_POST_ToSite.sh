curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/structured-contents" \
	-d "{\"title\": \"New Web Content Article\", \"contentStructureId\": \"${2}\"}" \
	-u "test@liferay.com:test"
