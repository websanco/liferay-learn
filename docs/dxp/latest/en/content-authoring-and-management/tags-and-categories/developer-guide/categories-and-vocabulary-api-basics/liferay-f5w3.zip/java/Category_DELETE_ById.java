import com.liferay.headless.admin.taxonomy.client.resource.v1_0.TaxonomyCategoryResource;

public class Category_DELETE_ById {

	/**
	 * java -classpath .:* -DtaxonomyCategoryId=1234 Category_DELETE_ById
	 */
	public static void main(String[] args) throws Exception {
		TaxonomyCategoryResource.Builder builder =
			TaxonomyCategoryResource.builder();

		TaxonomyCategoryResource taxonomyCategoryResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		taxonomyCategoryResource.deleteTaxonomyCategory(
			String.valueOf(System.getProperty("taxonomyCategoryId")));
	}

}