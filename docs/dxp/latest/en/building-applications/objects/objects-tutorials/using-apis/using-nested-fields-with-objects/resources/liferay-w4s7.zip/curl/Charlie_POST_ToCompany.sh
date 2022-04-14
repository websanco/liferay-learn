curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/c/charlies/" \
	-d "{\"name\": \"Charlie One\", \"r_bakerToCharlie_c_bakerId\": ${1}}" \
	-u "test@liferay.com:test"