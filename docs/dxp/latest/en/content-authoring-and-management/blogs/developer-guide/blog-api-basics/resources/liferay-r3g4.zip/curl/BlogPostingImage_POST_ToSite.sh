curl \
	-F "file=@liferay.png" \
	-H "Content-Type: multipart/form-data" \
	-X POST \
	"http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/blog-posting-images" \
	-u "test@liferay.com:test"