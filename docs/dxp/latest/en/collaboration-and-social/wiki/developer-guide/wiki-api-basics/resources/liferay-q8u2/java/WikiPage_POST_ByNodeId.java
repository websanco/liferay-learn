import com.liferay.headless.delivery.client.dto.v1_0.WikiPage;
import com.liferay.headless.delivery.client.resource.v1_0.WikiPageResource;

public class WikiPage_POST_ByNodeId {

	/**
	 * java -classpath .:* -DwikiNodeId=1234 WikiPage_POST_ByNodeId
	 */
	public static void main(String[] args) throws Exception {
		WikiPageResource.Builder builder =
			WikiPageResource.builder();

		WikiPageResource wikiPageResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		WikiPage wikiPage =
			wikiPageResource.postWikiNodeWikiPage(
				Long.valueOf(System.getProperty("wikiNodeId")),
				new WikiPage() {
					{
						content = "This is a sample Wiki Page.";
						encodingFormat = "text/x-wiki";
						name = "Sample Wiki Page";
					}
				});

		System.out.println(wikiPage);
	}

}