curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/c/charlies/" \
	-d "{\"name\": \"Charlie 1\", \"r_bakerToCharlie_c_bakerId\": ${1}}" \
	-u "test@liferay.com:learn"

curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/c/charlies/" \
	-d "{\"name\": \"Charlie 2\", \"r_bakerToCharlie_c_bakerId\": ${1}}" \
	-u "test@liferay.com:learn"

curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/c/charlies/" \
	-d "{\"name\": \"Charlie 3\", \"r_bakerToCharlie_c_bakerId\": ${1}}" \
	-u "test@liferay.com:learn"