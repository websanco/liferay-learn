import com.liferay.headless.delivery.client.dto.v1_0.WikiNode;
import com.liferay.headless.delivery.client.resource.v1_0.WikiNodeResource;

public class WikiNode_POST_ToSite {

	/**
	 * java -classpath .:* -DsiteId=1234 WikiNode_POST_ToSite
	 */
	public static void main(String[] args) throws Exception {
		WikiNodeResource.Builder builder = WikiNodeResource.builder();

		WikiNodeResource wikiNodeResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		WikiNode wikiNode = wikiNodeResource.postSiteWikiNode(
			Long.valueOf(System.getProperty("siteId")),
			new WikiNode() {
				{
					description = "Foo";
					name = "Dog Node";
				}
			});

		System.out.println(wikiNode);
	}

}