import com.liferay.headless.admin.taxonomy.client.dto.v1_0.Keyword;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.KeywordResource;

public class Keyword_POST_ToSite {

	/**
	 * java -classpath .:* -DsiteId=1234 Keyword_POST_ToSite
	 */
	public static void main(String[] args) throws Exception {
		KeywordResource.Builder builder = KeywordResource.builder();

		KeywordResource keywordResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Keyword keyword = keywordResource.postSiteKeyword(
			Long.valueOf(System.getProperty("siteId")),
			new Keyword() {
				{
					name = "Foo";
				}
			});

		System.out.println(keyword);
	}

}