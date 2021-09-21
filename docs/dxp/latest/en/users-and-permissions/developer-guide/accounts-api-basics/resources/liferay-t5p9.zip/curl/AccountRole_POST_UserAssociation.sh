curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-admin-user/v1.0/accounts/${1}/account-roles/${1}/user-accounts/${1}" \
	-u "test@liferay.com:test"