curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/c/charlieobjects/" \
	-d "{\"name\": \"Goo\", \"r_bakerToCharlie_c_bakerObjectId\": ${1}}" \
	-u "test@liferay.com:test"