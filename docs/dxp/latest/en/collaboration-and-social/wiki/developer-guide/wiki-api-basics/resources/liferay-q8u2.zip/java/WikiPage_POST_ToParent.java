import com.liferay.headless.delivery.client.dto.v1_0.WikiPage;
import com.liferay.headless.delivery.client.resource.v1_0.WikiPageResource;

public class WikiPage_POST_ToParent {

	/**
	 * java -classpath .:* -DparentWikiPageId=1234 WikiPage_POST_ToParent
	 */
	public static void main(String[] args) throws Exception {
		WikiPageResource.Builder builder = WikiPageResource.builder();

		WikiPageResource wikiPageResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		WikiPage wikiPage = wikiPageResource.postWikiPageWikiPage(
			Long.valueOf(System.getProperty("parentWikiPageId")),
			new WikiPage() {
				{
					content = "Foo";
					encodingFormat = "text/x-wiki";
					headline = "Fox Page";
				}
			});

		System.out.println(wikiPage);
	}

}