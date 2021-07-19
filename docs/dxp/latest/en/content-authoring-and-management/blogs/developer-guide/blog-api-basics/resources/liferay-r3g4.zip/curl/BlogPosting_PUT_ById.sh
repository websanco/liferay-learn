curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/headless-delivery/v1.0/blog-postings/${1}" \
	-d "{\"articleBody\": \"Goo\", \"headline\": \"Able\"}" \
	-u "test@liferay.com:test"