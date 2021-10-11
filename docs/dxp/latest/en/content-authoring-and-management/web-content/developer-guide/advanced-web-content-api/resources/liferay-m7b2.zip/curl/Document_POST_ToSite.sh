curl \
	-F "file=@${1}" \
	-H "Content-Type: multipart/form-data" \
	-X POST \
	"http://localhost:8080/o/headless-delivery/v1.0/sites/${2}/documents" \
	-u "test@liferay.com:test"