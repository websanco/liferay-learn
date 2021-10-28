curl \
	-H "accept: application/json" \
	-H "Content-Type: application/json" \
	-X 'POST' "http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/structured-contents" \
	-u "test@liferay.com:test" \
	--data-binary @- << EOF
		{
			"contentFields" : [ {
				"contentFieldValue" : {
					"data" : "This text describes Foo."
				},
				"name" : "TextReference"
			}, {
				"contentFieldValue" : {
					"image" : {
						"description" : "This text describes Foo's image.",
						"id" : "${3}"
					}
				},
				"name" : "ImageReference"
			}, {
				"contentFieldValue" : {
					"data" : "2021-08-30T00:00:00Z"
				},
				"name" : "DateReference"
			}, {
				"contentFieldValue" : {
					"data" : "Foo"
				},
				"name" : "SingleSelectionReference"
			} ],
			"contentStructureId" : "${2}",
			"title" : "Able"
		}
	EOF