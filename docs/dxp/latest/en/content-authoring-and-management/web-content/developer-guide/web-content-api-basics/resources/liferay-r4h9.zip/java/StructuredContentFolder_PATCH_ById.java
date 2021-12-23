import com.liferay.headless.delivery.client.dto.v1_0.StructuredContentFolder;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentFolderResource;

public class StructuredContentFolder_PATCH_ById {

	/**
	 * java -classpath .:* -DstructuredContentFolderId=1234 StructuredContentFolder_PATCH_ById
	 */
	public static void main(String[] args) throws Exception {
		StructuredContentFolderResource.Builder builder =
			StructuredContentFolderResource.builder();

		StructuredContentFolderResource structuredContentFolderResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		StructuredContentFolder structuredContentFolder =
			structuredContentFolderResource.patchStructuredContentFolder(
				Long.valueOf(System.getProperty("structuredContentFolderId")),
				new StructuredContentFolder() {
					{
						description = "Bar";
					}
				});

		System.out.println(structuredContentFolder);
	}

}