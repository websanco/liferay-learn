curl -s \
    "http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/documents" \
    -u "test@liferay.com:test" \
    | awk '/"fileExtension" : "png"/{p=10} p>0 {print $0; p--}' \
    | awk '/title/{print $3, "\n"}; /id/{print $3}'