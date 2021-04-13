import com.liferay.headless.delivery.client.dto.v1_0.DocumentFolder;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentFolderResource;

public class DocumentFolder_PATCH_ById {

	/**
	 * java -classpath .:* -documentFolderId=1234 DocumentFolder_PATCH_ById
	 */
	public static void main(String[] args) throws Exception {
		DocumentFolderResource.Builder builder =
			DocumentFolderResource.builder();

		DocumentFolderResource documentFolderResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		DocumentFolder documentFolder =
			documentFolderResource.patchDocumentFolder(
				Long.valueOf(System.getProperty("documentFolderId")),
				new DocumentFolder() {
					{
						description = "Bar";
						name = "Bar";
					}
				});

		System.out.println(documentFolder);
	}

}