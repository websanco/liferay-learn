import com.liferay.headless.delivery.client.dto.v1_0.WikiNode;
import com.liferay.headless.delivery.client.resource.v1_0.WikiNodeResource;

public class WikiNode_GET_ById {

	/**
	 * java -classpath .:* -DwikiNodeId=1234 WikiNode_GET_ById
	 */
	public static void main(String[] args) throws Exception {
		WikiNodeResource.Builder builder =
			WikiNodeResource.builder();

		WikiNodeResource wikiNodeResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		WikiNode wikiNode =
			wikiNodeResource.getWikiNode(
				Long.valueOf(System.getProperty("wikiNodeId")));

		System.out.println(wikiNode);
	}

}