# 構成ファイルの使用

[システム設定](./system-settings.md) は、システムスコープの構成変更を行い、他の [スコープ](./understanding-configuration-scope.md)デフォルト構成を設定するためのユーザーインターフェイスを提供します。 UIの同じ構成は、構成ファイルを介して行うことができます。

バージョンが同一であれば、構成ファイルを使用して、構成を実動前のシステムから実動システムに、または他のLiferay DXPシステム間で転送できます。

構成ファイルは、 [Apache Felix構成管理フレームワーク](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html)によって定義された `.config` プロパティ値の形式を使用します。

``` note::
   The ``.cfg`` file format is common in OSGi environments, and it's a supported format, but ``.config`` files are preferred because you can both specify a property value's type, and allow multi-valued properties. For this reason, ``.config`` files are used, recommended, and documented as a best practice.
```

## 構成ファイルの作成

システム設定には、構成エントリを変更すると使用可能になる [*Export*](./system-settings.md#exporting-and-deploying-configurations) オプションが用意されています。 エクスポートは、 `.config` ファイルを作成する最良の方法です。エントリの設定を含む `.config` ファイルを `key = value` 形式でダウンロードし、必要に応じて変更または配布します。 変更された値が1つだけであっても、使用可能なすべての構成キーと値がエクスポートされます。

単一の構成エントリまたは変更された構成のセット全体をエクスポートできます。

構成ファイルは、バッキングJavaクラスにちなんで名付けられます。 たとえば、Webコンテンツ機能をサポートするジャーナルサービスエントリには、次のファイル名があります。

``` bash
com.liferay.journal.configuration.JournalServiceConfiguration.config
```

![Web Content System Settingsエントリには、バックエンドID com.liferay.journal.configurationがあります。JournalServiceConfiguration。](./using-configuration-files/images/01.png)

システムは構成ファイルの名前を強制します。 名前を編集すると、構成エントリへのリンクが壊れ、それ以上のカスタマイズは有効になりません。 唯一の例外は [ファクトリ構成](./using-factory-configuration.md)で、一意のサブネームを提供できる場合があります。

## キー/値の構文

`.config` ファイルのすべてのキーと値の構文は同じです。

``` properties
configurationName="value"
```

特殊文字を含まない単一値構成の場合、知っておくべきことはこれだけです。 複数の値と特定の文字の設定は少し異なります。

### 複数値の設定

構成エントリには、複数の値を受け入れるプロパティを含めることができます。 たとえば、サポートされているファイル拡張子を指定する構成プロパティには、複数の値が必要です。 `.config` ファイルに複数値設定を書き込む方法は次のとおりです。

``` properties
multiValueSetting=["Value 1","Value 2", ...]
```

値の間に（コンマの後の）スペース文字を使用すると、プロパティーは無視されます。

[コンテンツ]セクションの[システム設定]で[Webコンテンツ]カテゴリを開き、仮想インスタンスのスコープとして[ *Webコンテンツ* を選択します。 *Characters Blacklist*複数の単一値エントリが表示されます。

![Web Content System Settingsエントリには、多くの文字ブラックリストフィールドがあります。](./using-configuration-files/images/02.png)

構成ファイルでは、これはカンマ区切り値の配列を持つ単一のキーで表されます。

``` properties
charactersblacklist=["&","'","@","\\","]","}",":","\=",">","/","<","[","{","%","+","#","`","?","\"",";","*","~"]
```

### エスケープ文字

二重引用符（`"`）と等号（`=`）は、 `.config` ファイルで *エスケープ* する必要があります。 エスケープとは、別の文字を使用して、文字を通常の方法で使用してはならないことを示します。 二重引用符と等号はすでに `.config` ファイルで使用されているため、エスケープすると、システムは通常の方法では読み取らず、値の一部として渡すようにシステムに指示します。 `.config` ファイルの文字をエスケープするには、円記号を使用します。

``` properties
charactersblacklist=["&","\"","\="]
```

この設定は、通常のエスケープされていない文字（`&`）と2つのエスケープされた文字（`\ "` および `\ =`）を持つ複数値設定を示しています。

二重引用符と等号の必須のエスケープに加えて、問題を回避するために値内のスペースをエスケープすることは有益です。 この例では、各スペース文字の前にバックスラッシュを使用して、正しく読み取られて処理されるようにしています。

``` properties
blacklistBundleSymbolicNames=["Liferay\ Marketplace","Liferay\ Sharepoint\ Connector"]
```

自分でスペースをエスケープしない場合、システムは展開後にバックスラッシュを追加します。

## 型付き値

`.config` ファイル形式は、特殊なタイプマーカー文字を挿入することにより、構成値のタイプの指定をサポートします。 システムは各構成プロパティの正しいタイプをすでに認識しているため、タイプ文字は情報提供の目的でのみ役立ちます。 たとえば、ブール型の構成では、ブール型としてマークする値の直前に *B* あります。

``` properties
addDefaultStructures=B"true"
```

`.config` ファイルにタイプマーカーが表示される場合は、無視しても問題ありません。 上記の例は、タイプマーカーなしでも同じように機能します。

``` properties
addDefaultStructures="true"
```

## 構成ファイルのデプロイ

構成ファイルを作成したら、それをデプロイします。 それが登録され、ターゲットの構成値が自動的に更新されます。

`.config` ファイルを展開するには、 [Liferay Homeの](../../installation-and-upgrades/reference/liferay-home.md) `osgi/configs` フォルダーに配置します。 さらに構成を変更するには、 `.config` ファイルを直接編集するか、システム設定を使用します。

## 構成ファイルとクラスタリング

クラスター環境では、各ノードの各エントリーに同じ構成値が必要です。 たとえば、すべてのノードで同じブログ構成設定を使用する必要があります。 これを行うには、 `.config` ファイルをデプロイします。 透過性と保守性のために、 `.config` ファイルをクラスター内のすべてのノードにデプロイします。 ただし、構成ファイルが単一のノードにのみデプロイされている場合でも、内部システムはクラスター内のすべてのノードに変更を適用します。

``` important::
   If storing your Liferay DXP configuration (e.g., Liferay Home) in a source control system, make sure to include the OSGi configuration files (.config files).
```
