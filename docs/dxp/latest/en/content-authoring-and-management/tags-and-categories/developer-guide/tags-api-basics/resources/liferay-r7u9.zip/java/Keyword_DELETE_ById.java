import com.liferay.headless.admin.taxonomy.client.resource.v1_0.KeywordResource;

public class Keyword_DELETE_ById {

	/**
	 * java -classpath .:* -DkeywordId=1234 Keyword_DELETE_ById
	 */
	public static void main(String[] args) throws Exception {
		KeywordResource.Builder builder = KeywordResource.builder();

		KeywordResource keywordResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		keywordResource.deleteKeyword(
			Long.valueOf(System.getProperty("keywordId")));
	}

}