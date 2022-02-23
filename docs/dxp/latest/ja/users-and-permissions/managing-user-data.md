# ユーザーデータの管理（GDPR）

```{toctree}
:maxdepth: 3

managing-user-data/exporting-user-data.md
managing-user-data/sanitizing-user-data.md
managing-user-data/configuring-the-anonymous-user.md
```

Liferayのユーザー関連データ（UAD）フレームワークは、一般データ保護規則（GDPR）の技術的に厳しい2つの要件を満たすのに役立ちます。

- データポータビリティの権利
- [「忘れられる権利」](./managing-user-data/sanitizing-user-data.md)

以下のLiferayアプリケーションには、UADフレームワークが適用されています。

| Liferayアプリケーション     | Liferay 7.2 (DXP & CE) | Liferay 7.3 (DXP & CE) |
| ------------------- | ---------------------- | ---------------------- |
| お知らせ                | はい                     | はい                     |
| ブログ                 | はい                     | はい                     |
| ブックマーク（非推奨）         | はい                     | はい                     |
| コンタクトセンター           | はい                     | はい                     |
| Documents and Media | はい                     | はい                     |
| フォーム                | いいえ                    | はい                     |
| 掲示板                 | はい                     | はい                     |
| Webコンテンツ            | はい                     | はい                     |
| Wiki                | はい                     | はい                     |

```{important}
Liferay DXPのユーザー管理機能を通じて、Webサイトのユーザーの個人データを処理する企業は、GDPRの要件に対応できるようになります。 ただし、GDPR要件に直接対処することを目的としたツールを含め、本ページおよびドキュメント内の他の場所で説明されているツールは、GDPRの法的要件への準拠を保証するものではありません。 Webサイトでユーザーの個人データを取り扱っていて、GDPRの管轄下にある各企業または個人は、GDPRに完全に準拠するために必要な正確な手順を慎重に判断する必要があります。
```