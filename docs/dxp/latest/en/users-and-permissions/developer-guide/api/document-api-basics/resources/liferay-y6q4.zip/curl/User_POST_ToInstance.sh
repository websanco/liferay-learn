curl \
  -H "Content-Type: application/json" \
  -X POST \
  "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts" \
  -d "{\"alternateName\": \"Foo\", \"emailAddress\": \"foo@liferay.com\", \"familyName\": \"Liferay\", \"givenName\": \"Foo\"}" \
  -u "test@liferay.com:test"