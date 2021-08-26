import com.liferay.headless.admin.taxonomy.client.dto.v1_0.TaxonomyVocabulary;
import com.liferay.headless.admin.taxonomy.client.pagination.Page;
import com.liferay.headless.admin.taxonomy.client.pagination.Pagination;
import com.liferay.headless.admin.taxonomy.client.resource.v1_0.TaxonomyVocabularyResource;

public class Vocabularies_GET_FromSite {

	/**
	 * java -classpath .:* -DsiteId=1234 Vocabularies_GET_FromSite
	 */
	public static void main(String[] args) throws Exception {
		TaxonomyVocabularyResource.Builder builder =
			TaxonomyVocabularyResource.builder();

		TaxonomyVocabularyResource taxonomyVocabularyResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<TaxonomyVocabulary> page =
			taxonomyVocabularyResource.getSiteTaxonomyVocabulariesPage(
				Long.valueOf(System.getProperty("siteId")), null,
				null, Pagination.of(1, 2), null);

		System.out.println(page);
	}

}