curl \
	-H "Content-Type: application/json" \
	-X PUT \
	"http://localhost:8080/o/headless-delivery/v1.0/blog-postings/${1}" \
	-d "{\"headline\": \"Able\", \"articleBody\": \"Goo\"}" \
	-u "test@liferay.com:test"