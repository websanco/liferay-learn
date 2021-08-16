import com.liferay.headless.delivery.client.dto.v1_0.WikiNode;
import com.liferay.headless.delivery.client.resource.v1_0.WikiNodeResource;

public class WikiNode_PUT_ById {

	/**
	 * java -classpath .:* -DwikiNodeId=1234 WikiNode_PUT_ById
	 */
	public static void main(String[] args) throws Exception {
		WikiNodeResource.Builder builder = WikiNodeResource.builder();

		WikiNodeResource wikiNodeResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		WikiNode wikiNode = wikiNodeResource.putWikiNode(
			Long.valueOf(System.getProperty("wikiNodeId")),
			new WikiNode() {
				{
					description = "Bar";
					name = "Easy Node";
				}
			});

		System.out.println(wikiNode);
	}

}