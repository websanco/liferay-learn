# 翻訳のエクスポートとインポート

WebコンテンツをXLIFF形式にエクスポートすることで、Liferayでの手動翻訳の代わりになります。 XLIFF（1.2または2.0）は、専用の翻訳ソフトウェアに使用される主要な交換フォーマットです。 エクスポートされた翻訳は、プロの翻訳者に送信してインポートし、コンテンツの翻訳を提供できます。

```{note}
翻訳用にWebコンテンツをエクスポートしたり、コンテンツの翻訳をインポートしたりするには、選択したコンテンツの*表示*および*更新*権限が必要です。 [Managing Permissions for Translation](./manually-translating-web-content.md#managing-permissions-for-translation) を参照してください。
```

## 翻訳用のコンテンツのエクスポート

1.  プロダクトメニューを開き、*[Content & Data]* → *[Webコンテンツ]* に移動します。

2.  翻訳するWebコンテンツの*アクション*メニュー（![Actions button](../../../images/icon-actions.png)）をクリックし、*[翻訳用にエクスポート]* を選択します。 エクスポートの設定に使用できるモーダルウィンドウが開きます。

    ![翻訳するWebコンテンツのアクションメニューをクリックし、[翻訳用にエクスポート]を選択します。](./exporting-and-importing-translations/images/01.png)

3.  エクスポート形式（XLIFF1.2またはXLIFF2.0）を選択します

4.  Webコンテンツの元の言語を指定します。 これは、翻訳の参照として使用されます。

5.  チェックボックスを使用して、翻訳する言語を選択します。

    ![Webコンテンツを翻訳する言語を選択します。](./exporting-and-importing-translations/images/02.png)

6.  完了したら、 *[エクスポート]* をクリックして、翻訳用にエクスポートを開始します。

Liferayは、翻訳用に選択した言語と同じ数のXLIFFファイルを含むダウンロード用のZipアーカイブを生成します。 これらのファイルを互換性のある翻訳ソフトウェアで使用して、Webコンテンツを翻訳できます。

## コンテンツ翻訳のインポート

翻訳の準備ができたら、次の手順に従ってWebコンテンツにインポートします。

1.  プロダクトメニューを開き、*[Content & Data]* → *[Webコンテンツ]* に移動します。

2.  翻訳されたWebコンテンツの*アクション*メニュー（![Actions button](../../../images/icon-actions.png)）をクリックし、*[翻訳のインポート]* を選択します。

    ![翻訳されたWebコンテンツのアクションメニューをクリックし、[翻訳のインポート]を選択します。](./exporting-and-importing-translations/images/03.png)

3.  インポートする翻訳ファイルを選択してから、変更を公開します。

    ![インポートする翻訳ファイルを選択します。](./exporting-and-importing-translations/images/04.png)

インポートが完了すると、XLIFFファイルに含まれているすべての変更内容でWebコンテンツが更新されます。 ワークフローが有効になっている場合、この変更は他のコンテンツの更新と同様に承認が必要になります。

```{note}
LiferayはXLIFF1.2と2.0の両方をサポートしていますが、これらのフォーマットが提供するすべての機能をサポートしているわけではありません。 また、Liferayは*翻訳用にエクスポート*アクションを使用して作成されたXLIFFファイルのインポートをサポートしていますが、他の方法で生成されたファイルの正常なインポートは保証できません。
```

## 追加情報

  - [Manually Translating Web Content](./manually-translating-web-content.md)
  - [Adding a Basic Web Content Article](../web-content-articles/adding-a-basic-web-content-article.md)
