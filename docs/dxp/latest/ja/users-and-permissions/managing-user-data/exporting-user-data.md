# ユーザーデータのエクスポート

一般データ保護規則（GDPR）の信条の1つは、ユーザーには*データポータビリティ*の権利があるということです。

データポータビリティとは、ユーザーが機械可読形式で個人データを受け取る権利があることを意味します。

コントロールパネルのユーザー管理システムは、ダウンロード用のZIPファイルへのユーザーの個人データのエクスポートをネイティブにサポートしています。 形式はXML形式です。

## エクスポートとダウンロード

ユーザーデータをエクスポートするには、

1.  グローバルメニュー（![Global Menu](../../images/icon-applications-menu.png)）を開きます。

2.  *[コントロールパネル]* → *[ユーザー]* → *[ユーザーと組織]* に移動します。

3.  ユーザーを見つけてアクションボタン（![Actions](../../images/icon-actions.png)）をクリックし、*[個人データをエクスポート]* をクリックします。

    ユーザーの[個人データをエクスポート]画面が開きます。

4.  既存のエクスポートプロセスが表示されていない場合は、*追加*ボタン（![Add](../../images/icon-add.png)）をクリックします。 ユーザーのデータをエクスポートするためのツールが表示されます。

    ![個人データのエクスポートツールを使用すると、ユーザーのデータのすべてまたは一部をエクスポートできます。](./exporting-user-data/images/01.png)

5.  ほとんどの場合、利用可能なすべてのデータをエクスポートします。 *[アイテムを選択]* をクリックすると、ユーザーデータを含むすべてのアプリケーションがUIで選択されます。

6.  *[エクスポート]* をクリックします。 ユーザーの[個人データをエクスポート]画面に戻りますが、リストにエクスポート処理が表示されます。

    ![ユーザーデータが正常にエクスポートされると、エクスポート処理がユーザーの[個人データをエクスポート]リストに表示されます。](./exporting-user-data/images/02.png)

7.  データをダウンロードします。 プロセスのアクションボタン（![Actions](../../images/icon-actions.png)）をクリックし、*[Download]* を選択します。

## エクスポートされたデータの調査

エクスポートされたデータは次のようになります。

``` xml
<?xml version="1.0"?>

<model>
    <model-name>com.liferay.message.boards.model.MBMessage</model-name>
    <column>
        <column-name>messageId</column-name>
        <column-value><![CDATA[38099]]></column-value>
    </column>
    <column>
        <column-name>statusByUserId</column-name>
        <column-value><![CDATA[38045]]></column-value>
    </column>
    <column>
        <column-name>statusByUserName</column-name>
        <column-value><![CDATA[Jane Slaughter]]></column-value>
    </column>
    <column>
        <column-name>userId</column-name>
        <column-value><![CDATA[38045]]></column-value>
    </column>
    <column>
        <column-name>userName</column-name>
        <column-value><![CDATA[Jane Slaughter]]></column-value>
    </column>
    <column>
        <column-name>subject</column-name>
        <column-value><![CDATA[Great list. I was thinking of bringing the family,...]]></column-value>
    </column>
    <column>
        <column-name>body</column-name>
        <column-value><![CDATA[<p>Great list. I was thinking of bringing the family, but I don&#39;t
  actually believe humans have ever been to the moon, so I guess it
  would be silly to book a trip! LOL!</p>]]></column-value>
    </column>
</model>
```

この例では、ユーザーであるJane Slaughterさんが、掲示板のメッセージ投稿を行い、そのユーザ情報が`MBMessage`モデルのデータベーステーブルに記録されました。

これは実際にはブログエントリへのコメントに対応しています。

![ブログ投稿へのコメントは、ユーザー関連データです。](./exporting-user-data/images/03.png)

ユーザーデータをエクスポートすると、サイトの所有者とユーザーに、サイトに含まれる個人データの量が通知されます。
