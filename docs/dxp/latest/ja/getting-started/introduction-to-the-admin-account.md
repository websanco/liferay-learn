# 管理者アカウントの概要

管理者ユーザーアカウントは、Liferay DXPの新規インストールでデフォルトで作成され、Liferay DXPインスタンスのすべてのファセットを変更するための完全なアクセス権を持っています。 管理者ユーザーは、サイト、ユーザー、ページを作成、変更、削除し、すべてのサイト管理タスクを実行できます。

## 管理者ユーザーとしてLiferay DXPにアクセスする

1. ブラウザで`http://localhost:8080`を開きます。

1. _［ログイン］_リンクをクリックします。

    ![サインインリンク](./introduction-to-the-admin-account/images/01.png "サインインリンク")

    ```{note}
    Liferay DXPバンドルが使用されている場合、[Setup Wizard](../installation-and-upgrades/installing-liferay/running-liferay-for-the-first-time.md)が表示されることがあります。 画面上の指示に従って、管理者ユーザーを設定します。
    ```

1. ログインするデフォルトの管理者資格情報を入力します。

    * メールアドレス：`test@liferay.com`
    * パスワード：`test`

    ```{warning}
       Liferay DXPの新規インストールを開始するときに、デフォルトのログイン資格情報を更新することを **強く** 推奨します。
    ```

<a name="configuring-the-admin-user" />

## 管理者ユーザーの設定

### ログインパスワードの変更

1. _［User Avatar］_をクリックして、_［アカウント設定］_ &rarr; _［Password］_に移動します。

1. _現在のパスワード_を入力し、新しいパスワードを入力します。

    ![パスワード設定](./introduction-to-the-admin-account/images/02.png "パスワード設定")

### アカウント情報の変更

1. _［User Avatar］_ &rarr; _［アカウント設定］_の順にクリックします。

    ![アカウント設定](./introduction-to-the-admin-account/images/03.png "アカウント設定")

1. _［Information］_タブで、必要に応じてユーザー情報を変更または追加します。

    ![アカウント情報](./introduction-to-the-admin-account/images/04.png "アカウント情報")

## ログアウト

アカウントからログアウトするには：

1. _［User Avatar］_ &rarr; _［Sign Out］_の順にクリックします。

    ![ログアウト](./introduction-to-the-admin-account/images/05.png "ログアウト")

<a name="creating-a-new-admin-user" />

## 新しい管理者ユーザーの作成

ログイン方法がわかったので、日常的に使用する新しい管理者ユーザーを作成することを強くお勧めします。

新しい管理者アカウントを作成するには、次の手順に従います。

1. 新しいユーザーを作成します。 方法については、[Adding Users](../users-and-permissions/users/adding-and-managing-users.md)を参照してください。

1. 左側のナビゲーションペイン（［一般］の下の［ユーザーの編集］ページ）から*［ロール］*リンクをクリックします。

    ![ロールリンクをクリックして、ユーザーのロールを編集します。](./introduction-to-the-admin-account/images/06.png)

1. *［Regular Roles］*の横にある*［選択］*をクリックします。

1. リストから*管理者*ロールを選択します（*［Choose］*をクリック）。 ダイアログボックスが閉じ、ロールがアカウントに関連付けられているロールのリストに追加されます。

ユーザーがポータル管理者になりました。 ログアウトしてから、新しいユーザーアカウントで再度ログインします。

<a name="whats-next" />

## 次のステップ

引き続きスタートガイドを進め、[DXPのグローバルメニュー](./navigating-dxp.md)について慣れてください。

[ユーザーがDXPにログインする方法](../installation-and-upgrades/securing-liferay/authentication-basics.md)について学習します。
