新しいLiferayインスタンスを実行します。

```bash
docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
```

`http://localhost:8080`でLiferayにサインインします。メールアドレス*test@liferay.com*とパスワード*test*を使用してください。プロンプトが表示されたら、パスワードを*learn*に変更します。