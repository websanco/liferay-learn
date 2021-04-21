curl \
	-F "document={\"description\": \"Goo\", \"title\": \"Document_PUT_ById.sh\"}" \
	-F "file=@Document_PUT_ById.sh" \
	-H "Content-Type: multipart/form-data; boundary=ARBITRARY" \
	-X PUT \
	"http://localhost:8080/o/headless-delivery/v1.0/documents/${1}" \
	-u "test@liferay.com:test"