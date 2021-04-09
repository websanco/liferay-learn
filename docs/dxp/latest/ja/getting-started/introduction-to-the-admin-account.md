# 管理者アカウントの概要

管理者ユーザーは、Liferay DXPの新規インストールでデフォルトで作成され、Liferay DXPインスタンスのすべてのファセットを変更するための完全なアクセス権を持っています。 管理者ユーザーは、サイト、ユーザー、ページを作成、変更、削除し、すべてのサイト管理タスクを実行できます。

## 管理者ユーザーとしてLiferay DXPにアクセスする

1.  ブラウザで`http://localhost:8080`を開きます。

2.  *[Sign In]* リンクをクリックします。

    ![サインインリンク](./introduction-to-the-admin-account/images/01.png "サインインリンク")

    ``` note::
       If a Liferay DXP bundle is being used, you may be presented with the `Setup Wizard <../installation-and-upgrades/installing-liferay/running-liferay-for-the-first-time.md>`_. 画面上の指示に従って、管理者ユーザーを設定してください。
    ```

3.  ログインするデフォルトの管理者資格情報を入力します。

      - メールアドレス：`test@liferay.com`
      - パスワード：`test`
    
    <!-- end list -->

    ``` warning::
       Liferay DXPの新規インストールを開始するときに、デフォルトのログイン資格情報を更新することを**強く**推奨します。
    ```

## 管理者ユーザーの設定

### ログインパスワードの変更

1.  *ユーザーのアバター*をクリックして、*[Account Settings]* → *[Password]* に移動します。

2.  *現在のパスワード*を入力し、新しいパスワードを入力します。

    ![パスワード設定](./introduction-to-the-admin-account/images/04.png "パスワード設定")

### アカウント情報の変更

1.  *ユーザーのアバター*→ *[Account Settings]* の順にクリックします。

    ![アカウント設定](./introduction-to-the-admin-account/images/02.png "アカウント設定")

2.  *[Information]* タブで、必要に応じてユーザー情報を変更または追加します。

    ![アカウント情報](./introduction-to-the-admin-account/images/03.png "アカウント情報")

## ログアウト

アカウントからログアウトするには：

1.  *ユーザーのアバター*→ *[Sign Out]* の順にクリックします。

    ![ログアウト](./introduction-to-the-admin-account/images/05.png "ログアウト")

## 新しい管理者ユーザーの作成

ログイン方法がわかったので、日常的に使用する新しい管理者ユーザーを作成することを強くお勧めします。

新しい管理者アカウントを作成するには、次の手順に従います。

1.  新しいユーザーを作成します。 方法については、[Adding Users](../users-and-permissions/users/adding-and-managing-users.md)を参照してください。
2.  左側のナビゲーションペイン（*[Edit User]* ページの*[General]* タブ）で*[Roles]* リンクをクリックします。
3.  [Regular Roles]の下にある*[Select]* をクリックします。 リストから[Administrator]ロールを選択します（*[Choose]* をクリック）。 ダイアログボックスが閉じ、ロールがアカウントに関連付けられているロールのリストに追加されます。

ユーザーがポータル管理者になりました。 ログアウトしてから、新しいユーザーアカウントで再度ログインします。

## 次のステップ

引き続きスタートガイドを進め、[DXPの製品メニュー](./navigating-dxp.md)について理解します。

[ユーザーがDXPにログインする方法](../installation-and-upgrades/securing-liferay/authentication-basics.md)について学習します。
