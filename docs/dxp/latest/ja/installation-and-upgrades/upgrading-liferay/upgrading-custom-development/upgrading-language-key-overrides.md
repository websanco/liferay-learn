# Language Key Overridesのアップグレード

Liferay DXP/Portal 7.4では、Liferayモジュールの言語キーがLiferay Portal言語モジュールに移動しました。 多くのキーの名前が変更されました。 名前を変更した言語キーをオーバーライドする場合は、モジュールのキー名を更新する必要があります。

```{note}
言語キーフックプラグインをアップグレードする場合は、まず [Upgrading Portlet Language Key Hooks](https://help.liferay.com/hc/en-us/articles/360029005272-Upgrading-Portlet-Language-Key-Hooks) に従ってください。
```

言語キーのオーバーライドをアップグレードする方法は次のとおりです。

1. 言語キーの名前が変更されていないか確認してください。 [言語キーの名称変更](../reference/renamed-language-keys.md) を参照してください。
1. 名前が変更された言語キーごとに、言語プロパティファイルで新しいキー名を使用します。

モジュールをデプロイする準備ができました。

<a name="additional-information" />

## 追加情報

* [グローバル言語キーのオーバーライド](../../../liferay-internals/extending-liferay/overriding-global-language-keys.md)
* [言語キーの名称変更](../reference/renamed-language-keys.md)
* [カスタム開発のアップグレード](../upgrading-custom-development.md)