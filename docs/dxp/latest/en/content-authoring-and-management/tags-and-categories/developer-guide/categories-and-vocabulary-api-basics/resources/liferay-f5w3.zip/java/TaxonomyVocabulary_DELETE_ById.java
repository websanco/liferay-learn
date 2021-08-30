import com.liferay.headless.admin.taxonomy.client.resource.v1_0.TaxonomyVocabularyResource;

public class TaxonomyVocabulary_DELETE_ById {

	/**
	 * java -classpath .:* -DtaxonomyVocabularyId=1234 TaxonomyVocabulary_DELETE_ById
	 */
	public static void main(String[] args) throws Exception {
		TaxonomyVocabularyResource.Builder builder =
			TaxonomyVocabularyResource.builder();

		TaxonomyVocabularyResource taxonomyVocabularyResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		taxonomyVocabularyResource.deleteTaxonomyVocabulary(
			Long.valueOf(System.getProperty("taxonomyVocabularyId")));
	}

}