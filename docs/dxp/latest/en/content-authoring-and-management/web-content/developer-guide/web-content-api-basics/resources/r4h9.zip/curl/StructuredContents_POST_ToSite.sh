curl -X 'POST' \
  "http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/structured-contents" \
  -H "accept: application/json" \
  -H "Content-Type: application/json" \
  -u "test@liferay.com:test" \
  -d \
    '{
        "contentFields" : [ {
          "contentFieldValue" : {
            "data" : "This is my new Web Content element"
          },
          "dataType" : "string",
          "label" : "Text",
          "name" : "'"${3}"'",
          "nestedContentFields" : [ ],
          "repeatable" : false
          }, {
          "contentFieldValue" : {
            "data" : "<p>Description of my new Web Content Article.</p>"
          },
          "dataType" : "string",
          "label" : "Product Description",
          "name" : "'"${4}"'",
          "nestedContentFields" : [ ],
          "repeatable" : false
        } ],
        "contentStructureId" : "'"${2}"'",
        "title" : "New Web Content Element"
    }'
