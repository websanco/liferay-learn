import com.liferay.headless.delivery.client.dto.v1_0.ContentStructure;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.ContentStructureResource;

public class ContentStructures_GET_FromSite {

	/**
	 * java -classpath .:* -DsiteId=1234 ContentStructures_GET_FromSite
	 */
	public static void main(String[] args) throws Exception {
		ContentStructureResource.Builder builder =
			ContentStructureResource.builder();

		ContentStructureResource contentStructureResource =
			builder.authentication(
				"test@liferay.com", "learn"
			).build();

		Page<ContentStructure> page =
			contentStructureResource.getSiteContentStructuresPage(
				Long.valueOf(System.getProperty("siteId")), null, null, null,
				Pagination.of(1, 2), null);

		System.out.println(page);
	}

}