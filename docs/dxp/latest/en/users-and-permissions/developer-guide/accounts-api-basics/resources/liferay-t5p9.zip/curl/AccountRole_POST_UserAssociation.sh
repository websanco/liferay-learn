curl \
	-H "Content-Type: application/json" \
	-X POST \
	"http://localhost:8080/o/headless-admin-user/v1.0/accounts/${1}/account-roles/${2}/user-accounts/${3}" \
	-u "test@liferay.com:learn"