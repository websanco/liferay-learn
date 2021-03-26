import com.liferay.headless.delivery.client.dto.v1_0.DocumentFolder;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentFolderResource;

public class Folder_GetById {

	/**
	 * java -classpath .:* -DdocumentFolderId=1234 Folder_GetById
	 */
	public static void main(String[] args) throws Exception {
		DocumentFolderResource.Builder builder =
			DocumentFolderResource.builder();

		DocumentFolderResource documentFolderResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		DocumentFolder documentFolder =
			documentFolderResource.getDocumentFolder(
				Long.valueOf(System.getProperty("documentFolderId")));

		System.out.println(documentFolder);
	}

}