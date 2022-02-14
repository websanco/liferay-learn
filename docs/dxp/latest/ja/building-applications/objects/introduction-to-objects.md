# オブジェクトの紹介

Liferay Objectは、Liferayのコアフレームワーク上に構築され統合されたローコード開発機能を提供します。 これを使用すると、コードを記述したりモジュールをデプロイしたりすることなく、アプリケーションを簡単に構築して提供できます。 シームレスなユーザーエクスペリエンスを維持しながら、Liferay DXPをビジネスニーズに適応させる柔軟なソリューションを迅速に開発および管理します。

オブジェクトを作成するときに、カスタムデータ[フィールド](./creating-and-managing-objects/adding-fields-to-objects.md)を追加し、オブジェクトエントリー間の複雑な[関係](./creating-and-managing-objects/defining-object-relationships.md)を定義できます。 次に、カスタム[レイアウト](./creating-and-managing-objects/designing-object-layouts.md)を設計して、エントリを作成または編集するときにフィールドと関係がユーザーにどのように表示されるかを決定できます。 公開する前に、各オブジェクトのデータを会社またはサイトにスコープし、Liferay UIのどこに表示されるかを決定できます。 <!--TASK: Add in Views once implemented-->

作成時から、すべてのオブジェクトはLiferayの[コアフレームワーク](../../core-frameworks.md)と完全に統合されており、プラットフォーム全体で統一されたエクスペリエンスを提供し、Liferayのすべての機能を活用します。 これには、Liferayがすべてのオブジェクトに対してヘッドレスREST APIを自動的に生成することが含まれるため、定義されたエンドポイントを介してオブジェクトと対話できます。 これらのAPIを[Job Scheduler](../../core-frameworks/dispatch-framework/using-dispatch.md)と統合して使用することで、オブジェクトデータを外部システムと動的に同期するようにTalendジョブを構成することもできます。

すべてのオブジェクトはワークフローと統合されているため、定義されたレビューおよび承認プロセスを通じてオブジェクトエントリーの作成をガイドできます。 オブジェクトもフォームと統合されているため、オブジェクトフィールドにマップし、オブジェクトエントリーを作成するためのユーザー入力を受け取るフォームを設計できます。 エントリーが作成されると、Liferayの情報フレームワークを活用して、動的なユーザーエクスペリエンスを作成し、ページフラグメントと表示ページテンプレートを使用してオブジェクトエントリーを表示できます。

すべてのオブジェクトはLiferayの[権限設定フレームワーク](./understanding-object-integrations/permissions-framework-integration.md)と統合されているため、アプリケーションとリソースの権限を割り当てて、オブジェクトとそのエントリーへのユーザーアクセスを管理できます。 範囲指定と合わせ、ロールベースのアクセス制御を行うことで、データを保護し、適切なユーザーのみがデータにアクセスできるようにすることができます。

<!--TASK: Add the following text once more features are supported for system Objects, "The Objects application also provides a convenient way to extend and configure any system services registered with the Objects framework. This includes adding custom fields, defining relationships with other Objects, and designing layouts for Object entries." -->

カスタムアプリケーションの構築を開始するには[Creating Objects](./creating-and-managing-objects/creating-objects.md)を参照し、オブジェクトがLiferayのコアフレームワークをどのように活用するかについて確認するには[Understanding Object Integrations](./understanding-object-integrations.md)を参照してください。

## 追加情報

* [Creating Objects](./creating-and-managing-objects/creating-objects.md)
* [Understanding Object Integrations](./understanding-object-integrations.md)<!--TASK: * \[Objects UI Reference\](./objects-ui-reference.md) -->
