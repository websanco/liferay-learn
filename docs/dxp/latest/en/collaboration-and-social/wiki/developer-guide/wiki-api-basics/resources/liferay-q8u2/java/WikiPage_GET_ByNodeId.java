import com.liferay.headless.delivery.client.dto.v1_0.WikiPage;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.WikiPageResource;

public class WikiPage_GET_ByNodeId {

	/**
	 * java -classpath .:* -DwikiNodeId=1234 WikiPage_GET_ByNodeId
	 */
	public static void main(String[] args) throws Exception {
		WikiPageResource.Builder builder =
        WikiPageResource.builder();

		WikiPageResource wikiPageResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<WikiPage> page =
            WikiPageResource.getWikiNodeWikiPagesPage(
				Long.valueOf(System.getProperty("wikiNodeId")), null, null, null,
				null, Pagination.of(1, 2), null);

		System.out.println(page);
	}

}