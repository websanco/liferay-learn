# オブジェクト

```{toctree}
:maxdepth: 3

objects/creating-and-managing-objects.md
objects/building-solutions-with-objects.md
objects/using-forms-with-objects.md
objects/displaying-object-entries.md
objects/enabling-workflows-for-objects.md
objects/understanding-object-integrations.md
objects/using-picklists.md
objects/objects-application-permissions.md
```

Liferay Objectを使用すると、コードを記述したりモジュールをデプロイしたりすることなくアプリケーションを構築および配信できますが、これらのアプリケーションはLiferayのコアフレームワーク上に構築され、統合されています。 シームレスなユーザーエクスペリエンスを維持しながら、Liferay DXPをビジネスニーズに適応させる柔軟なソリューションを迅速に開発および管理します。

オブジェクトを作成するときに、データ[フィールド](./objects/creating-and-managing-objects/adding-fields-to-objects.md)を追加し、オブジェクトエントリー間の複雑な[関係](./objects/creating-and-managing-objects/defining-object-relationships.md)を定義できます。 次に、[レイアウト](./objects/creating-and-managing-objects/designing-object-layouts.md)を設計して、エントリーを作成または編集するときにフィールドと関係がどのように表示されるかを決定できます。 公開する前に、各オブジェクトのデータを会社またはサイトにスコープし、Liferay UIのどこに表示されるかを決定できます。 <!--TASK: Add in Views once implemented--> 作成時から、すべてのオブジェクトはLiferayの[コアフレームワーク](./core-frameworks.md)と完全に統合されており、プラットフォーム全体で統一されたエクスペリエンスを提供し、Liferayのすべての機能を活用します。 すべてのオブジェクトに対して[ヘッドレスREST API](./objects/understanding-object-integrations/headless-framework-integration.md)が自動的に生成される機能が含まれているため、定義されたエンドポイントを介してオブジェクトと対話できます。 これらのAPIを[Job Scheduler](./core-frameworks/dispatch-framework/using-dispatch.md)と統合して使用することで、オブジェクトデータを外部システムと動的に同期するようにTalendジョブを構成することもできます。

すべてのオブジェクトは[ワークフロー](./objects/enabling-workflows-for-objects.md)と統合されているため、定義されたレビューおよび承認プロセスを通じてオブジェクトエントリーの作成をガイドできます。  オブジェクト[もフォーム](objects/using-forms-with-objects.md)と統合されているため、オブジェクトフィールドにマップし、オブジェクトエントリーを作成するためのユーザー入力を受け取るフォームを設計できます。 エントリーが作成されると、Liferayの情報フレームワークを活用して、動的なユーザーエクスペリエンスを作成し、ページフラグメントと表示ページテンプレートを使用してオブジェクトエントリーを表示できます。

すべてのオブジェクトはLiferayの[権限設定フレームワーク](./objects/understanding-object-integrations/permissions-framework-integration.md)と統合されているため、アプリケーションとリソースの権限を割り当てて、オブジェクトとそのエントリーへのアクセスを管理できます。 範囲指定と合わせ、ロールベースのアクセス制御を行うことで、データを保護し、適切なユーザーのみがデータにアクセスできるようにすることができます。 <!--TASK: Add the following text once more features are supported for system Objects, "The Objects application also provides a convenient way to extend and configure any system services registered with the Objects framework. This includes adding custom fields, defining relationships with other Objects, and designing layouts for Object entries." --> カスタムアプリケーションの構築を開始するには[Creating Objects](./objects/creating-and-managing-objects/creating-objects.md)を参照し、オブジェクトがLiferayのコアフレームワークをどのように活用するかについて確認するには[Understanding Object Integrations](./objects/understanding-object-integrations.md)を参照してください。 <!--TASK: * \[Objects UI Reference\](./objects-ui-reference.md) --> ```{raw} html
:file: ../landingpage_template.html
```

```{raw} html
:file: objects/landing.html
```
