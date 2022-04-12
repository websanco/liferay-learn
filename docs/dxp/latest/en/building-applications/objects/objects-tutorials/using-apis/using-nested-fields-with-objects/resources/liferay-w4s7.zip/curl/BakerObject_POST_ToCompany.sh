curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/c/bakerobjects/" \
	-d "{\"name\": \"Bar\", \"r_ableToBaker_c_ableObjectId\": ${1}}" \
	-u "test@liferay.com:test"