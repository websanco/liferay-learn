import com.liferay.headless.delivery.client.dto.v1_0.StructuredContentFolder;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentFolderResource;

public class StructuredContentFolder_POST_ToSite {

	/**
	 * java -classpath .:* -DsiteId=1234 StructuredContentFolder_POST_ToSite
	 */
	public static void main(String[] args) throws Exception {
		StructuredContentFolderResource.Builder builder =
			StructuredContentFolderResource.builder();

		StructuredContentFolderResource structuredContentFolderResource =
			builder.authentication(
				"test@liferay.com", "learn"
			).build();

		StructuredContentFolder structuredContentFolder =
			structuredContentFolderResource.postSiteStructuredContentFolder(
				Long.valueOf(System.getProperty("siteId")),
				new StructuredContentFolder() {
					{
						description = "Foo";
						name = "Charlie Folder";
					}
				});

		System.out.println(structuredContentFolder);
	}

}