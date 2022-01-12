# Mercanet

この記事では、Mercanetを支払い方法として有効にする方法について詳しく説明します。 Mercanetは **EURのみ** 対応しているため、ストアの主要通貨がEURでなければなりません。

<a name="mercanet-guide" />

## Mercanetガイド

1. [前提条件を確認します。](#prerequisites)
1. [EURをストアの主要通貨として設定します。](#set-eur-as-the-primary-store-currency)
1. [支払い方法としてMercanetを有効にします。](#activate-mercanet-as-a-payment-method)

<a name="prerequisites" />

## 前提条件

[ Mercanet](https://documentation.mercanet.bnpparibas.net/index.php?title=Obtenir **sa** cl%C3%A9_secr%C3%A8te#) を使用するようにストアを設定する前に、以下を生成する必要があります。

1. Mercanetの販売者ID
1. 秘密鍵
1. 鍵バージョン

<a name="set-eur-as-the-primary-store-currency" />

## EURをストアの主要通貨として設定する

Mercanetを使用する場合、ストアではユーロを主要通貨として使用する必要があります。 主要通貨を変更するには：

1. ［**コントロールパネル］ → ［コマース］ → ［設定**］ に移動します。
1. ［**通貨**］ をクリックします。
1. ［ユーロ］の隣の **3ドットアイコン** をクリックします。
1. ［**Set as Primary**］ をクリックします。

**ユーロ** が主要通貨として設定されました。

![主要通貨を設定](./mercanet/images/01.png)

<a name="activate-mercanet-as-a-payment-method" />

## 支払い方法としてMercanetを有効にする

1. ［**サイト管理］ → ［コマース］ → ［設定］ → ［支払い方法**］ に移動します。
1. ［**支払い方法**］ をクリックします。
1. ［**Mercanet**］ をクリックします。
1. ［**設定**］ をクリックします。
1. 次のように入力します：
    ***販売者ID**
    ***秘密鍵**
    ***鍵バージョン**
1. ライブサイトの場合は ［**Production**］ を、テスト環境の場合は ［**Test**］ または ［**Simulation**］ を選択します。 ![Mercanetの設定](./mercanet/images/02.png)
1. ［**保存**］ をクリックします。
1. ［**Mercanet**］ の隣にある **3ドットアイコン** をクリックし、次に ［**有効にする**］ をクリックします。

Mercanetがストアで有効になりました。

<a name="additional-information" />

## 追加情報

* [新しい通貨の追加](../currencies/adding-a-new-currency.md)
* [Authorize.Net](./authorize.net.md)
* [PayPal](./paypal.md)
