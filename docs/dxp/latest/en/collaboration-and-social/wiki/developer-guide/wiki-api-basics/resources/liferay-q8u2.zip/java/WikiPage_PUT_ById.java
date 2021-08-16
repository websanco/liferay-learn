import com.liferay.headless.delivery.client.dto.v1_0.WikiPage;
import com.liferay.headless.delivery.client.resource.v1_0.WikiPageResource;

public class WikiPage_PUT_ById {

	/**
	 * java -classpath .:* -DwikiPageId=1234 WikiPage_PUT_ById
	 */
	public static void main(String[] args) throws Exception {
		WikiPageResource.Builder builder = WikiPageResource.builder();

		WikiPageResource wikiPageResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		WikiPage wikiPage = wikiPageResource.putWikiPage(
			Long.valueOf(System.getProperty("wikiPageId")),
			new WikiPage() {
				{
					content = "Bar";
					encodingFormat = "text/x-wiki";
					headline = "Easy Page";
				}
			});

		System.out.println(wikiPage);
	}

}