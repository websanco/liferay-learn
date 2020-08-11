# FedEx配送方法の使用

> サブスクライバー

この記事では、配送方法としてFedExを設定して有効にする方法について詳しく説明します。

## 前提条件

FedEXを配送方法として使用するようにストアを構成する前に、以下を用意してください。

1.  FedExビジネスアカウント番号
2.  FedExアカウントのパスワード
3.  FedEx Webサービス開発者キー（[FedEx Webサービス](https://www.fedex.com/en-us/developer/web-services.html)から入手可能）
4.  FedExメーター番号

## FedEx配送方法を有効にする

選択したチャネルの配送方法としてFedExをアクティブ化するには：

1.  *[Control Panel]* → *[Commerce]* → *[Channels]*に移動します。

    ![チャンネルへの移動](./using-the-fedex-shipping-method/images/01.png)

2.  目的のチャネル（たとえば、Sahara.com）をクリックします。 （Miniumなどのアクセラレータを使用してサイトを作成した場合は、対応するチャネルがデフォルトですでに作成されています。）

3.  [ *発送方法 *]までスクロールします。

    ![Sahara.comポータルチャネルでのFedExのアクティブ化](./using-the-fedex-shipping-method/images/02.png)

4.  FedEXの横にある *編集*を クリックします。

5.  *[Active]* トグルを *[YES]*に切り替えます。

6.  *設定* タブをクリックします。

7.  次のように入力します：

      - **URL**
      - **Key**
      - **パスワード**
      - **アカウント番号**
      - **メーター番号**

8.  [Dropoff Type]を選択します。

9.  適切なチェックボックスをオンにします。

      - **住宅価格を使用**
      - **割引料金を使用**

10. *サービスタイプ*のボックスをオンにします。

11. ポンドとキログラムの両方で*[Max Weight]*を入力します。

12. インチとセンチメートルの両方で*[Max Size]*を入力します。

13. *[Save]*をクリックします。

これで、ストアは配送方法としてFedExを使用するように構成されました。

### Commerce 2.0

サイトの配送方法としてFedExをアクティブ化するには：

1.  *[Site Administration]* → *[Commerce]* → *[Settings]*に移動します。
2.  *[Shipping Methods]*タブをクリックします。
3.  *[FedEx]*をクリックします。
4.  *[Configurations]*タブをクリックします。
5.  次のように入力します：
      - **URL**
      - **Key**
      - **パスワード**
      - **アカウント番号**
      - **メーター番号**
6.  [Dropoff Type]を選択します。
7.  ビジネスニーズに該当する*サービスタイプ*オプションのいずれかをオンにします。
8.  [Packing Type]を選択します。
9.  ポンドとキログラムの両方で*[Max Weight]*を入力します。
10. インチとセンチメートルの両方で*[Max Size]*を入力します。
11. *[Save]*をクリックします。
12. *[Details]* タブをクリックします。
13. *[Active]* トグルを *[YES]*に切り替えます。

配送方法としてFedExを使用するようにストアが設定されました。 ストアでは複数の配送方法を有効にすることができ、資格情報が有効である限り、販売者はFedEx固有の配送オプションを選択できるようになりました。

## 追加情報

FedEx Webサービス開発者ガイド

  - [FedEx Webサービス](https://www.fedex.com/en-us/developer/web-services.html)

拡張ポイントを使用してLiferay Commerceに他の配送方法を追加するには：

  - [Creating New Shipping Methods](https://help.liferay.com/hc/en-us/articles/360020751831)
