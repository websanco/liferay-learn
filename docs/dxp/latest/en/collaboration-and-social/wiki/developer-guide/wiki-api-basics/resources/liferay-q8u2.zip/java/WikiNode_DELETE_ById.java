import com.liferay.headless.delivery.client.resource.v1_0.WikiNodeResource;

public class WikiNode_DELETE_ById {

	/**
	 * java -classpath .:* -DwikiNodeId=1234 WikiNode_DELETE_ById
	 */
	public static void main(String[] args) throws Exception {
		WikiNodeResource.Builder builder = WikiNodeResource.builder();

		WikiNodeResource wikiNodeResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		wikiNodeResource.deleteWikiNode(
			Long.valueOf(System.getProperty("wikiNodeId")));
	}

}