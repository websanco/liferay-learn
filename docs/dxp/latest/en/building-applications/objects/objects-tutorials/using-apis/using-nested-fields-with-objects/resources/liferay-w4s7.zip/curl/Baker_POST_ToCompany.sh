curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/c/bakers/" \
	-d "{\"name\": \"Baker One\", \"r_ableToBaker_c_ableId\": ${1}}" \
	-u "test@liferay.com:test"