# Mercanet

この記事では、Mercanetを支払い方法として有効にする方法について詳しく説明します。 Mercanetは**EURのみ**対応しているため、ストアの主要通貨がEURでなければなりません。

## Mercanetガイド

1. [前提条件を確認します。](#prerequisites)
1. [EURをストアの主要通貨として設定します。](#set-eur-as-the-primary-store-currency)
1. [支払い方法としてMercanetを有効にします。](#activate-mercanet-as-a-payment-method)

## 前提条件

[ Mercanet](https://documentation.mercanet.bnpparibas.net/index.php?title=Obtenir _sa_ cl%C3%A9_secr%C3%A8te#) を使用するようにストアを設定する前に、以下を生成する必要があります。

1. Mercanetの販売者ID
1. 秘密鍵
1. 鍵バージョン

## EURをストアの主要通貨として設定する

Mercanetを使用する場合、ストアではユーロを主要通貨として使用する必要があります。 主要通貨を変更するには：

1. ［_コントロールパネル］ → ［コマース］ → ［設定_］ に移動します。
1. ［_通貨_］ をクリックします。
1. ［ユーロ］の隣の _3ドットアイコン_ をクリックします。
1. ［_Set as Primary_］ をクリックします。

_ユーロ_ が主要通貨として設定されました。

![主要通貨を設定](./mercanet/images/01.png)

## 支払い方法としてMercanetを有効にする

1. ［_サイト管理］ → ［コマース］ → ［設定］ → ［支払い方法_］ に移動します。
1. ［_支払い方法_］ をクリックします。
1. ［_Mercanet_］ をクリックします。
1. ［_設定_］ をクリックします。
1. 次のように入力します：
    * _販売者ID_
    * _秘密鍵_
    * _鍵バージョン_
1. ライブサイトの場合は ［_Production_］ を、テスト環境の場合は ［_Test_］ または ［_Simulation_］ を選択します。 ![Mercanetの設定](./mercanet/images/02.png)
1. ［_保存_］ をクリックします。
1. ［_Mercanet_］ の隣にある _3ドットアイコン_ をクリックし、次に ［_有効にする_］ をクリックします。

Mercanetがストアで有効になりました。

## 追加情報

* [新しい通貨の追加](../currencies/adding-a-new-currency.md)
* [Authorize.Net](./authorize.net.md)
* [PayPal](./paypal.md)
