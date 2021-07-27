import com.liferay.headless.delivery.client.dto.v1_0.WikiPage;
import com.liferay.headless.delivery.client.resource.v1_0.WikiPageResource;

public class WikiPage_POST_ByParentId {

	/**
	 * java -classpath .:* -DwikiNodeId=1234 WikiPage_POST_ByParentId
	 */
	public static void main(String[] args) throws Exception {
		WikiPageResource.Builder builder =
			WikiPageResource.builder();

		WikiPageResource wikiPageResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		WikiPage wikiPage =
			wikiPageResource.postWikiPageWikiPage(
				Long.valueOf(System.getProperty("wikiNodeId")),
				new WikiPage() {
					{
						content = "This is a sample child page.";
						encodingFormat = "text/x-wiki";
						name = "Sample Child Page";
					}
				});

		System.out.println(wikiPage);
	}

}