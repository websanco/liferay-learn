import com.liferay.headless.admin.taxonomy.client.dto.v1_0.Keyword;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.KeywordResource;

public class Keyword_PUT_ById {

	/**
	 * java -classpath .:* -DkeywordId=1234 Keyword_PUT_ById
	 */
	public static void main(String[] args) throws Exception {
		KeywordResource.Builder builder = KeywordResource.builder();

		KeywordResource keywordResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Keyword keyword = keywordResource.putKeyword(
			Long.valueOf(System.getProperty("keywordId")),
			new Keyword() {
				{
					name = "Bar";
				}
			});

		System.out.println(keyword);
	}

}