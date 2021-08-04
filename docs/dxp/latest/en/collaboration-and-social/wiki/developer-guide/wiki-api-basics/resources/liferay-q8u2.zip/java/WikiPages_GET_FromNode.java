import com.liferay.headless.delivery.client.dto.v1_0.WikiPage;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.WikiPageResource;

public class WikiPages_GET_FromNode {

	/**
	 * java -classpath .:* -DwikiNodeId=1234 WikiPages_GET_FromNode
	 */
	public static void main(String[] args) throws Exception {
		WikiPageResource.Builder builder = WikiPageResource.builder();

		WikiPageResource wikiPageResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<WikiPage> page = wikiPageResource.getWikiNodeWikiPagesPage(
			Long.valueOf(System.getProperty("wikiNodeId")), null, null, null,
			Pagination.of(1, 2), null);

		System.out.println(page);
	}

}