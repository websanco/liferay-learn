import com.liferay.headless.admin.taxonomy.client.dto.v1_0.Keyword;
import com.liferay.headless.admin.taxonomy.client.pagination.Page;
import com.liferay.headless.admin.taxonomy.client.pagination.Pagination;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.KeywordResource;

public class Keywords_GET_FromSite {

	/**
	 * java -classpath .:* -DsiteId=1234 Keywords_GET_FromSite
	 */
	public static void main(String[] args) throws Exception {
		KeywordResource.Builder builder = KeywordResource.builder();

		KeywordResource keywordResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<Keyword> page = keywordResource.getSiteKeywordsPage(
			Long.valueOf(System.getProperty("siteId")), null, null,
			Pagination.of(1, 2), null);

		System.out.println(page);
	}

}