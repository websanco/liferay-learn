# Liferay Enterprise Searchを有効にする

Liferay DXP 7.4より、Liferay Enterprise Search (LES)アプリケーションは、すべてのLiferay DXPバンドルとDockerコンテナに含まれています。 LESの使用を開始するには、LESアドオンサブスクリプションを購入します。まず、Liferayの営業担当者に連絡するか、<https://www.liferay.com/contact-sales>にアクセスします。

LESはデフォルトで有効になっており、追加のインストール手順は必要ありません。 ただし、Liferayの公式サポートや追加特典を受けるためには、LESのアドオン・サブスクリプションを購入する必要があります。 LESアプリケーションのメンテナンスとアップデートは、Fix PackとService Packのリリースを通じて行われます。 LESのサブスクリプションをお持ちでない方は、以下の手順でアクセスを完全に無効にすることができます。

<a name="ローカルバンドルlesの無効化" />

## ローカルバンドルLESの無効化

LESアプリケーションは、すべてのLiferay DXP 7.4バンドルでデフォルトで有効になっています。 LESを使用するためのサブスクリプションとそれに付随するライセンスを持っていない場合は、 `enterprise.product.enterprise.search.enabled` [ポータルプロパティ](../../installation-and-upgrades/reference/portal-properties.md) を `false`に設定して、LESを無効にします。

```properties
enterprise.product.enterprise.search.enabled=false
```

<a name="dockerの場合lesの無効化" />

## Dockerの場合LESの無効化

LESアプリケーションは、すべてのLiferay DXP 7.4のDockerイメージでデフォルトで有効になっています。 LESを使用するためのサブスクリプションとそれに付随するライセンスを持っていない場合は、以下の2つの方法のいずれかで無効化してください。

1. プロパティファイルを使用してコンテナを構成する場合は、 `enterprise.product.enterprise.search.enabled`を設定してコンテナを非アクティブにします。 [portal property](../../installation-and-upgrades/reference/portal-properties.md) を`false`：

    ```properties
    enterprise.product.enterprise.search.enabled=false
    ```

1. 代わりに環境変数でポータル・プロパティを上書きするには、次のように設定します。

    ```properties
    LIFERAY **ENTERPRISE** PERIOD **PRODUCT** PERIOD **ENTEPRISE** PERIOD **SEARCH** PERIOD_ENABLED=false
    ```

詳しくは、 [コンテナーの設定](../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/configuring-containers.md) を参照してください。
