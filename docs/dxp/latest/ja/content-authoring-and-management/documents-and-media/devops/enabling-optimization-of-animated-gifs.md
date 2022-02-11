# アニメーションGIFの最適化を有効にする

アニメーションGIFをスケーリングするために、アダプティブメディアは[Gifsicle](https://www.lcdf.org/gifsicle/)と呼ばれる外部ツールを使用します。 Gifsicleがインストールされておらず、`image/gif`がサポートされている MIME種別として含まれている場合、アダプティブメディアはGIFの1フレームのみをスケーリングし、静的GIFを作成します。

アダプティブメディアを使用してアニメーションGIFを最適化するには、3つの手順が必要です。DXPが実行されているローカルサーバーにGifsicleをインストールし、`PATH`環境変数を設定してから、DXPの_システム設定_でGifsicleを有効にします。

1. [Gifsicle](https://www.lcdf.org/gifsicle/)をインストールし、サーバー環境のパスに追加します。

1. _［グローバルメニュー］_ &rarr; _［コントロールパネル］_に移動します。

    ![コントロールパネルに移動します。](./enabling-optimization-of-animated-gifs/images/01.png)

1. _［システム設定］_をクリックします。
1. _［アダプティブメディア］_をクリックします。

   ![アダプティブメディアのシステム設定にアクセスします。](./enabling-optimization-of-animated-gifs/images/02.png)

1. _［システムスコープ］_の下の左側のナビゲーションにある_［Images］_をクリックします。
1. 下にスクロールして、_［Gifsicleが有効］_の横にあるボックスをクリックします。

    ![Gifsicleを有効にします。](./enabling-optimization-of-animated-gifs/images/03.png)

1. 完了したら、_［保存］_をクリックします。

## 追加情報

* [アダプティブメディアの仕組み](../publishing-and-sharing/serving-device-and-screen-optimized-media/how-adaptive-media-works.md)
* [サイトコンテンツでの適応画像の使用](../publishing-and-sharing/serving-device-and-screen-optimized-media/using-adapted-images-in-site-content.md)
