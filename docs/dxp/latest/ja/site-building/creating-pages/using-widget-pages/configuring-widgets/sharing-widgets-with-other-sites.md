# ウィジェットを他のサイトと共有する

Facebook、OpenSocial、Netvibesなど、お客様のサイトで動作しているウィジェットのインスタンスを他のWebサイトに埋め込むことで、他のサイトとウィジェットを共有することができます。 これにより、他の方法では得られなかった、まったく新しいWebサイトへの露出の道が開かれます。

![ウィジェットの設定メニューにある共有タブでは、様々な方法でウィジェットを共有することができます。](./sharing-widgets-with-other-sites/images/01.png)

1. ウィジェットの **オプション** アイコン (![Options](../../../../images/icon-app-options.png)) &rarr; ［**Configuration**］ &rarr; ［**Sharing**］ をクリックしてください。

1. 共有のサブタブは、Facebook、OpenSocial Gadget、Netvibesの3つです。 選択したコンテキストに従って進みます。

```{note}
どのウェブサイトでも、トグルを切り替えてナビゲーションを許可してください。 提供されたJavaScriptのコードをコピーして、ウィジェットを追加したいWebサイトに貼り付けてください。 ユーザーが他のWebサイトにページをロードすると、コードは関連するウィジェットをサイトから取得して表示します。
```

<a name="facebook" />

## Facebook (Automatic Copy)

任意のウィジェットをFacebookアプリとして追加することができます。 そのためには、まず開発者キーを取得する必要があります。

1. 共有の［Facebook］タブに移動します。

1. Facebookから **API Key** <!--\[ include link -\]()--> と **Canvas Page URL** <!--\[ include link -\]()--> を取得します。 それらをコピーして［共有］タブに貼り付けます。

    ![ウィジェットの設定メニューにある共有タブでは、様々な方法でウィジェットを共有することができます。](./sharing-widgets-with-other-sites/images/02.png)

1. ［**保存**］ をクリックして、Liferay DXPの［Facebook］タブに戻ります。 **コールバックURL** が渡されるので、それをコピーしてFacebookに貼り付けます。 Facebookでアプリを開くと、正しいコールバックURLが使用されてアプリがレンダリングされます。

1. 必要に応じて、 **Allow users to add [application-name] to Facebook** を有効にしてください。 そしたら、アプリのオプションメニューに移動して、 ［**Add to Facebook**］ を選択します。

<a name="opensocial-gadget" />

## OpenSocial ガジェット

OpenSocialは、ソーシャルネットワーキングやその他のWebアプリケーションのためのコンテナとAPIセットで構成されています。 Liferay DXPは、OpenSocialに対応したページでOpenSocial Gadgetとして使用するアプリケーションを提供することができます。

1. OpenSocialプラットフォーム上でLiferayウィジェットを提供するには、提供されたガジェットURLをコピー＆ペーストし、使用しているOpenSocialプラットフォームの適切な設定ページに追加します。 Liferayインスタンスは、そのウィジェットをそのプラットフォームのページに直接提供します。

    提供される URL は、ウィジェットの特定のインスタンスに固有なので、同じウィジェットの複数のインスタンスを、異なる OpenSocial ガジェットとして提供することができます。

1. ［共有］タブで **OpenSocial ガジェット** に移動し、 ［**Allow users to add [application-name] to an OpenSocial platform**］ のセレクターを有効にします。

1. ［**保存**］ をクリックし、ウィジェットの ［**オプション**］ ボタンを再度クリックします。 ［**OpenSocialプラットフォームに追加する**］ という名前の新しいボタンが表示されます。 この新しいボタンを選択すると、ウィジェットをOpenSocialプラットフォームで共有するためのURLが提供されます。

<a name="netvibes" />

## Netvibes

Netvibesも、ユーザーがログインして独自のパーソナルダッシュボードを作成し、それにカスタマイズ可能なウィジェットを追加できる、という同様の環境を提供します。

［共有］タブで **Netvibes** に移動し、 ［**Allow users to add [application-name] to Netvibes pages**］ セレクターを有効にします。 次に、提供されたURLを使って、使用しているLiferayウィジェットのインスタンスに基づいたカスタムNetvibesウィジェットを作成することができます。

<a name="additional-information" />

## 追加情報

- [ウィジェット権限を設定する](./setting-widget-permissions.md)
