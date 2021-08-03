curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/headless-delivery/v1.0/content-structures/${1}/permissions" \
    -d "[{\"actionIds\": [\"DELETE\", \"VIEW\"], \"roleName\": \"Power User\"}]" \
	-u "test@liferay.com:test"