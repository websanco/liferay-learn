import com.liferay.headless.delivery.client.dto.v1_0.StructuredContentFolder;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentFolderResource;

public class StructuredContentFolder_PUT_ById {

	/**
	 * java -classpath .:* -DstructuredContentFolderId=1234 StructuredContentFolder_PUT_ById
	 */
	public static void main(String[] args) throws Exception {
		StructuredContentFolderResource.Builder builder =
			StructuredContentFolderResource.builder();

		StructuredContentFolderResource structuredContentFolderResource =
			builder.authentication(
				"test@liferay.com", "learn"
			).build();

		StructuredContentFolder structuredContentFolder =
			structuredContentFolderResource.putStructuredContentFolder(
				Long.valueOf(System.getProperty("structuredContentFolderId")),
				new StructuredContentFolder() {
					{
						description = "Goo";
						name = "Dog Folder";
					}
				});

		System.out.println(structuredContentFolder);
	}

}