curl \
	-H "Content-Type: application/json" \
	-X PATCH \
	"http://localhost:8080/o/headless-delivery/v1.0/blog-postings/${1}" \
	-d "{\"articleBody\": \"Bar\"}" \
	-u "test@liferay.com:test"