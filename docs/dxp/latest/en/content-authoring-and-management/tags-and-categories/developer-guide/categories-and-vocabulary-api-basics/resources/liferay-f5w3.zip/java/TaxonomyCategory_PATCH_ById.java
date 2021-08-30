import com.liferay.headless.admin.taxonomy.client.dto.v1_0.TaxonomyCategory;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.TaxonomyCategoryResource;

public class TaxonomyCategory_PATCH_ById {

	/**
	 * java -classpath .:* -DtaxonomyCategoryId=1234 TaxonomyCategory_PATCH_ById
	 */
	public static void main(String[] args) throws Exception {
		TaxonomyCategoryResource.Builder builder =
			TaxonomyCategoryResource.builder();

		TaxonomyCategoryResource taxonomyCategoryResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		TaxonomyCategory taxonomyCategory =
			taxonomyCategoryResource.patchTaxonomyCategory(
				String.valueOf(System.getProperty("taxonomyCategoryId")),
				new TaxonomyCategory() {
					{
						description = "Bar";
						name = "Baker";
					}
				});

		System.out.println(taxonomyCategory);
	}

}