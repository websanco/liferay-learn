# ローリング再起動の実行

ローリング再起動クラスターのメンテナンスプロセスでは、ノードがすべて更新されるまで、ノードを一度に1つずつ（他のノードが動作している間）シャットダウンして更新します。 この方法により、クラスターを更新する際の稼働時間を最大化できます。 ローリング再起動は、コンテナおよびイメージベースの環境で使用できます。

ローリング再起動の手順は次のとおりです。

1. 1つのクラスターノード（JVMインスタンス）をシャットダウンします。

1. そのノードのデプロイを更新/変更します（以下のメンテナンスシナリオを参照）。

1. ノードを起動します。

1. 他のすべてのクラスターノードに対してこれらの手順を繰り返します。

    ```{note}
      Maintenance scenarios vary in how they behave in rolling restarts. For example, UI changes in a plugin update are only visible on the updated nodes. Users on nodes that haven't been updated don't see the UI changes. Maintenance scenarios might have specific cases that cannot be performed in rolling restarts --- the scenario descriptions mention these cases.
    ```

ローリング再起動の対象となるメンテナンスシナリオを以下に示します。

<a name="new-modules-and-plugins" />

## 新しいモジュールとプラグイン

新しいプラグインまたはモジュール（クラスターにまだ存在しないもの）がローリング再起動の対象になるには、既存のプラグインまたはモジュールとの互換性を損なうような方法でデータを変更したり、データベース列を削除または名前変更したりしてはなりません。

<a name="updating-existing-modules-and-plugins" />

## 既存のモジュールとプラグインの更新

既存のプラグインまたはモジュールの新しいバージョンをローリング再起動の対象にするためには、既存のバージョンのプラグインまたはモジュールとの互換性を損なう方法で、データを変更したり、データベース列を削除または名前変更したりしてはなりません。

<a name="applying-fix-packs" />

## フィックスパックの適用

> サブスクリプション

カスタマーポータルは、復帰不可能な[フィックスパック](../patching-dxp-7-3-and-earlier.md)を特定し、ローリング再起動の対象外となります。 他のすべてのフィックスパックは対象です。

## フィックスパックの復元

> サブスクリプション

復帰可能なフィックスパックは、ローリング再起動で削除できます。

<a name="portal-properties-controlled-by-portal-extproperties" />

## portal-ext.propertiesによって制御されるポータルプロパティ

[ポータルプロパティ](../../reference/portal-properties.md) ファイルの変更は、ローリング再起動に適用できます。

<a name="system-settings-controlled-by-configuration-admin-files" />

## 構成管理ファイルによって制御されるシステム設定

[システム構成](../../reference/system-properties.md)ファイルは、ローリング再起動に適用できます。

<a name="application-server-or-jvm-setting-modifications" />

## アプリケーションサーバーまたはJVM設定の変更

アプリケーションサーバーとJVM設定の変更は、ローリング再起動時に実行できます。

<a name="java-version-updates" />

## Javaバージョンの更新

Javaのマイナーバージョンの更新はローリング再起動時に適用できます。 メジャーバージョンの更新はローリング再起動ではサポートされていません。代わりに、すべてのクラスターノードがシャットダウンされたときに実行する必要があります。

ローリング再起動の対象となるすべての更新は、前述のローリング再起動手順を使用して適用できます。 他の更新は、次に説明するように異なる方法で行う必要があります。

## 追加情報

* [ブルーグリーンのデプロイ](./blue-green-deployments.md)
* [Clustering for High Availability](../../setting-up-liferay/clustering-for-high-availability.md)
* [DXP へのパッチ適用](../patching-dxp-7-3-and-earlier.md)