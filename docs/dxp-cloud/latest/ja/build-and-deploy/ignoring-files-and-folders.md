# ファイルとフォルダを無視する

DXP Cloudを使用すると、サブスクライバーは、サービスを展開する前に無視するファイルとフォルダーを決定できます。 これは、 `.lcpignore` ファイルを作成し、プロジェクトフォルダーの最上位レベル内に配置することで行われます（例： `/ etc/lfrlearn /` ここで、 `lfrlearn` はプロジェクト名です）。 ここにファイルを配置することで、 **ignore** ルールを他のユーザーと共有できます。

## `.lcpignore` ファイルの使用

1.  プロジェクトフォルダの場所に移動します。
2.  `.lcpignore`というファイルを作成します。
3.  それに応じてファイルを変更します。
4.  ターミナルで `lcp deploy` を実行して、新しい `.lcpignore` ルールでサービスをデプロイします。

## LCP Ignoreパターン

`.lcpignore` ファイルは、グロビングパターンを使用してファイル名と照合します。 （これらは `.gitignore` ファイルで使用されるものと同じパターンです。 ） 管理者は、さまざまなシンボルを使用してそのようなパターンを構築できます。

| パターン                                      | 一致の例                                                                                    | 説明\*                                                                                                 |
| ----------------------------------------- | --------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| `**/サービス`                                 | `service/file.txt`、または `service/monday/foo.bar`、または `build/service/file.txt`            | パターンの前にダブルアスタリスクを付けると、リポジトリ内の任意の場所のフォルダに一致します。                                                         |
| `**/service/file.txt`                     | `service/file.txt`; または `build/service/file.txt`であり、 `service/build/file.txt`ではない。      | ダブルアスタリスクを使用すると、ファイルの名前と親フォルダーの名前に基づいてファイルが照合されます。                                                     |
| `*.txt`                                  | `file.txt`、 `foo.txt`、`.txt`または `service/file.txt`                                      | アスタリスクは、0個以上の文字に一致するワイルドカードです。                                                                         |
| `* .txt` または `!important.txt`             | `file.txt`、`trace.txt`  。 ただし`important.txt`、 `service/important.txt`は不可。               | パターンの前にエクスクラメーション・マークを付けると、パターンが無効になります。 ファイルがパターンに一致するが、ファイルで後で定義された否定パターンにも一致する場合、それは無視されません。        |
| `*.txt`または `!important/*.txt`または`trace.*` | `file.txt`、 `important/trace.txt` 。 ただし`important/file.txt`は不可。                         | 否定パターンの後に定義されたパターンは、以前に否定されたファイルを無視します。                                                                |
| `/file.txt`                               | `file.txt`、ただし `service/file.txt`は不可。                                                   | 先頭にスラッシュを付けると、リポジトリルート内のファイルのみが一致します。                                                                  |
| `ファイルtxt`                                 | `file.txt`、または `service/file.txt`                                                       | デフォルトでは、パターンは任意のフォルダ内のファイルと一致します                                                                       |
| `ファイル?`                                   | `fileo.txt`、 `files.txt` なく `file10.txt`                                                | 疑問符を使用すると、正確に1文字と一致します。                                                                                |
| `ファイル[0-9].txt`                           | `file0.txt`、 `FILE1.TXT`。 ただし `file10.txt`は不可。                                          | 角括弧を使用すると、指定した範囲の1文字と一致します。                                                                            |
| `ファイル[01].txt`                            | `file0.txt`、`file1.txt`。 ただし `file2.txt` 、`file01.txt`は不可。                              | 角かっこを使用すると、指定したセットの1文字と一致します。                                                                          |
| `ファイル[!01].txt`                           | `FILE2.TXT`ではなく `file0.txt`、また `FILE1.TXT`、また `file01.txt`                              | エクスクラメーションマークを使用すると、指定したセットの文字以外のすべての文字に一致します。                                                         |
| `ファイル[a-z].txt`                           | `filea.txt`、`fileb.txt`。 ただし `file1.txt`は不可。                                            | 範囲は数値またはアルファベットにすることができます。                                                                             |
| `txts`                                    | `txts`、`txt/file.txt`、`txts/latest/foo.bar`、`build/txts`、`build/txts/file.txt`          | スラッシュがある場合は * not * 付加し、パターンがその名前のファイルとフォルダの内容の両方に一致します。 左の一致例では、 `txts` という名前のフォルダーとファイルの両方が無視されます。 |
| `緊張状態にある場合`                               | `txts/file.txt`、`txts/latest/foo.bar`、`build/txts/foo.bar`、`build/txts/latest/file.txt` | スラッシュを追加すると、パターンがフォルダーであることを示します。 その名前に一致するリポジトリ内のすべてのファイルとサブフォルダを含むフォルダの内容全体は無視されます。                  |
| `txts/**/file.txt`                        | `txts/file.txt`、`txts/monday/file.txt`または `txts/monday/pm/file.txt`                     | 二重アスタリスクは、0個以上のフォルダーと一致します。                                                                            |
| `txts/*day/file.txt`                      | `txts/monday/file.txt` または `txts/tuesday/file.txt`。                                     | ワイルドカードは、フォルダフォルダ名にも使用できます。                                                                            |
| `txts/file.txt`                           | `txts/file.txt`。 ただし `file.txt` 、`build/txts/file.txt`は不可                               | 特定のフォルダ内のファイルを指定するパターンは、リポジトリルートを基準にしています。 スラッシュを前に付けても効果がないことに注意してください。                               |

ベストプラクティスとして、 `.lcpignore` ファイルをプロジェクトの最上位フォルダに保持します。 リポジトリに`.lcpignore` ファイル（非推奨）が複数ある場合は、DXP Cloudは、統一された文書としてそれらを読み込みます。

``` note::
   ファイルやフォルダがデプロイされていて、管理者が後で無視したい場合、後続のルールがそのファイルやフォルダをインクルードしようとしても、DXP Cloudはそのファイルを無視しません。 むしろ、そのファイルやフォルダは更新されません。
```

## 追加情報

  - [Liferay DXPサービスの概要](../using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.md)
  - [Liferay DXPサービスの設定](../using-the-liferay-dxp-service/configuring-the-liferay-dxp-service.md)
