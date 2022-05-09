import com.liferay.headless.delivery.client.dto.v1_0.StructuredContentFolder;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentFolderResource;

public class StructuredContentFolders_GET_FromSite {

	/**
	 * java -classpath .:* -DsiteId=1234 StructuredContentFolders_GET_FromSite
	 */
	public static void main(String[] args) throws Exception {
		StructuredContentFolderResource.Builder builder =
			StructuredContentFolderResource.builder();

		StructuredContentFolderResource structuredContentFolderResource =
			builder.authentication(
				"test@liferay.com", "learn"
			).build();

		Page<StructuredContentFolder> page =
			structuredContentFolderResource.getSiteStructuredContentFoldersPage(
				Long.valueOf(System.getProperty("siteId")), null, null, null,
				null, Pagination.of(1, 2), null);

		System.out.println(page);
	}

}