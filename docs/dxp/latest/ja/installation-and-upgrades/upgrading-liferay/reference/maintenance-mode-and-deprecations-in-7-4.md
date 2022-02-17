# 7.4のメンテナンスモードと非推奨

新しいDXPのリリースごとに、機能の継続的な拡張が保証されない場合や、廃止される可能性があります。 機能の拡張を停止すると、その機能は **メンテナンスモード** に入ります。

**メンテナンスモード：** この機能はサポートされていますが、拡張は行われません。

廃止された機能は最終的に削除されるか、より新しい、より優れた機能に置き換えられます。 廃止された機能のサポートを終了する前に、Liferayはそれを **廃止予定** とマークします。

**廃止予定：** この機能は、次のマイナーバージョンのリリースでサポートされなくなります。 たとえば、7.4で廃止予定になった機能のサポートは、早ければ7.5で削除される可能性があります。

```{important}
廃止予定の機能のサポートは、早ければ次のマイナーリリースで終了する可能性があるため、機能の使用を停止することを計画してください。
```

```{important}
詳細は、 [Maintenance Mode and Deprecation Policies](https://help.liferay.com/hc/en-us/articles/360015767952-Maintenance-Mode-and-Deprecation) を参照してください。
```

<a name="廃止予定の機能の利用可能性" />

## 廃止予定の機能の利用可能性

廃止予定の機能には、さまざまな利用可能性があります。

**バンドル：** この機能はLiferay製品に含まれています。

**マーケットプレイス：** この機能はマーケットプレイスアプリの最終バージョンに含まれています。

**最終版：** このLiferayバージョン用にマーケットプレイスアプリの最終版がリリースされています。

**アーカイブ：** この機能は利用できませんが、そのコードはLiferayのリポジトリ（ <https://repository.liferay.com/nexus/index.html#welcome>）のソースアーティファクトで利用できます。

**削除：** この機能とそのコードは使用できません。

<a name="74で廃止予定の機能" />

## 7.4で廃止予定の機能

7.4で廃止予定の機能は次のとおりです。

| 機能            | 利用可能性  | メモ                                                                             |
|:------------- |:------ |:------------------------------------------------------------------------------ |
| EXTプラグインのサポート | 削除しました | 構成設定と標準の [拡張ポイント](../../../liferay-internals/extending_liferay.html) に置き換えられました。 |
| アプリビルダー       | 削除     | Liferay 7.4でLiferay Objectとして再開されます。                                           |

以前のLiferayバージョンからアップグレードする場合は、以前のバージョンで廃止になった機能も考慮する必要があります。

* [7.3の非推奨事項とメンテナンスモードに移行した機能](./maintenance-mode-and-deprecations-in-7-3.md)
* [7.2の非推奨事項とメンテナンスモードに移行した機能](./maintenance-mode-and-deprecations-in-7-2.md)
* [7.1の非推奨事項](https://help.liferay.com/hc/en-us/articles/360018403151-Digital-Experience-Platform-7-1-Deprecated-and-Removed-Items)
* [7.0の非推奨事項](https://help.liferay.com/hc/en-us/articles/360018123832-Digital-Experience-Platform-7-0-Deprecated-and-Removed-Items)

<a name="74でメンテナンスモードに移行された機能" />

## 7.4でメンテナンスモードに移行された機能

7.4でメンテナンスモードに移行した機能は次のとおりです。

* TBD

<a name="追加情報" />

## 追加情報

* [7.4破壊的な変更](../../../liferay-internals/reference/7-4-breaking-changes.md)
* [7.4 Default Setting Changes](./default-setting-changes-in-7-4.md)
