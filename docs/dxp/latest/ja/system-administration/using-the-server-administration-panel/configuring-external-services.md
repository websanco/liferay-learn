# 外部サービスの構成

ユーザーエクスペリエンスを向上させるためにLiferay DXPインストールに追加できる外部サービスがいくつかあります。

## ドキュメントのプレビューを有効にする

Liferay DXPユーザーは、Documents and Mediaライブラリを介して、あらゆるタイプのファイルをアップロードおよび共有できます。これは、ファイルのカスタマイズ可能で権限が有効なオンラインリポジトリです（詳細については、 [公開と共有](./../../content-authoring-and-management/documents-and-media/publishing-and-sharing/README.md) を参照）。

[PDFBox](https://pdfbox.apache.org/) はデフォルトで、特定のファイルタイプ（主にPDF）のLiferay DXPの自動プレビューを生成します。 2つの追加ツールをインストールして、他のファイルタイプのプレビューを生成できます。


<!--
-   [**OpenOffice:**](https://www.openoffice.org/) or [**LibreOffice:**](https://www.libreoffice.org/)
    Convert and generate previews for many types of documents.
-->

  - [**ImageMagick：**](https://www.imagemagick.org/script/index.php) 多くのタイプの画像の高品質な画像プレビューを生成します。

  - [**Xuggler：**](http://www.xuggle.com/xuggler/) オーディオおよびビデオファイルのプレビューを変換および生成します。

![[外部サービス]タブからImageMagick、Ghostscript、およびXugglerを有効にします。](./configuring-external-services/images/01.png)

``` note::
   As of Liferay 7.1, OpenOffice/LibreOffice is configured in OSGi Configuration Admin instead of Server Administration or portal properties. To adjust these settings, go to System Settings and find the OpenOffice Integration entry. Alternatively, deploy a com.liferay.document.library.document.conversion.internal.configuration.OpenOfficeConfiguration.config file with the settings you need.
```

まずサーバーにImageMagickとXugglerをインストールしてから、サーバー管理アプリの[外部サービス]タブを使用して、Liferay DXPでの使用を構成します。 ご使用のオペレーティングシステムに対応するこれらのツールの正しいバージョンを選択してください。 古いバージョンはLiferay DXPで正しく動作しない可能性があるため、最新の安定バージョンをインストールしてください。 ImageMagickは手動でインストールする必要がありますが、コントロールパネルからXugglerをインストールできます。

``` tip:::
   Xuggler requires glibc version 2.6 or later on Linux.
```

### サーバー管理でのImageMagickの構成

ImageMagickを設定して画像とPDFプレビューを生成する前に、それとその依存関係であるGhostscriptをインストールします。 これはオペレーティングシステムによって異なります。Linuxでは、両方が既にインストールされている可能性があります。 Windowsにはインストールされない可能性がありますが、macOSにインストールされる可能性があります。

1.  [ImageMagick](https://www.imagemagick.org/script/index.php)ダウンロードしてインストールし

 。</p></li> 
   
   2  [Ghostscript](https://www.ghostscript.com/)ダウンロードしてインストールし 。</p></li> </ol> 
  
  インストールしたら、サーバー管理アプリの[外部サービス]タブまたは [ポータルプロパティ](./../../installation-and-upgrades/reference/portal-properties.md) ファイルでImageMagickを有効にします。 `portal-ext.properties`を使用する場合は、次の行を追加し、検索パスがImageMagickおよびGhostscript実行可能ファイルを含むディレクトリを指していることを確認します。 macOSまたはUnix環境でGhostscriptが使用するフォントのパスを構成する必要がある場合もあります。
  
  サーバー管理アプリの[外部サービス]タブからImageMagickを有効にするには、
  
  1.  *コントロールパネル*、[ *構成* →[ *サーバー管理*]に移動し、[ *外部サービス* ]タブをクリックします。

2.  ImageMagickおよびGhostscriptセクションを展開し、 *Enabled*を選択します。

3.  ImageMagickおよびGhostscript実行可能ファイルへのパスが正しいことを確認します。

4.  使用するリソース制限を設定します。



#### ポータルプロパティファイルでのImageMagickの構成

あるいは、 [ポータルプロパティ](./../../installation-and-upgrades/reference/portal-properties.md) ファイルでImageMagickを有効にできます。 `portal-ext.properties`を使用する場合は、次の行を追加し、検索パスがImageMagickおよびGhostscript実行可能ファイルを含むディレクトリを指していることを確認します。 macOSまたはUnix環境でGhostscriptが使用するフォントのパスを構成する必要がある場合もあります。



``` properties
imagemagick.enabled=true
imagemagick.global.search.path[apple]=/opt/local/bin:/opt/local/share/ghostscript/fonts:/opt/local/share/fonts/urw-fonts
imagemagick.global.search.path[unix]=/usr/local/bin:/usr/local/share/ghostscript/fonts:/usr/local/share/fonts/urw-fonts
imagemagick.global.search.path[windows]=C:\\Program Files\\ImageMagick
```




### サーバー管理でのXugglerの構成

Xugglerをインストールして構成するには、

1.  *コントロールパネル*、[ *構成* →[ *サーバー管理*]に移動し、[ *外部サービス* ]タブをクリックします。

2.  Xugglerセクションで、オペレーティングシステムに一致するXuggler `.jar` ファイルを選択します。 次に、[ *インストール*]をクリックします。

3.  アプリケーションサーバーをシャットダウンします。 サーバー管理ではなくポータルプロパティファイルでXugglerを有効にする場合は、「 [ポータルプロパティファイルでXugglerを有効にする](#enabling-xuggler-wtih-a-portal-properties-file) セクションに移動します。 それ以外の場合は、ここでアプリケーションサーバーを再起動してから、次の手順を実行します。

4.  Xugglerを有効にして終了します。サーバー管理の[外部サービス]タブに戻り、[ *有効*]を選択します。
   
   ![XugglerをインストールしてLiferay DXPを再起動すると、[有効]チェックボックスが表示されます。](./configuring-external-services/images/02.png)

5.  *[保存]* をクリックします。



### ポータルプロパティファイルでXugglerを有効にする

Xugglerは、 [Portal Properties](./../../installation-and-upgrades/reference/portal-properties.md) ファイルを使用して有効にすることもできます。 この行を `portal-ext.properties`に追加し`</p>

<pre><code class="properties">xuggler.enabled=true
`</pre> 

アプリケーションサーバーを再起動して、Xugglerの有効化を完了します。
