curl -F "file=@document_add_to_folder.sh" -H  "Content-Type: multipart/form-data" -X POST "http://localhost:8080/o/headless-delivery/v1.0/document-folders/${1}/documents" -u "test@liferay.com:test"
