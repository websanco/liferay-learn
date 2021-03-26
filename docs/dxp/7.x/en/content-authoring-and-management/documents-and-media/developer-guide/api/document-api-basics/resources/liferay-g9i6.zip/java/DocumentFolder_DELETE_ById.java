import com.liferay.headless.delivery.client.resource.v1_0.DocumentFolderResource;

public class DocumentFolder_DELETE_ById {

	/**
	 * java -classpath .:* -DdocumentFolderId=1234 DocumentFolder_DELETE_ById
	 */
	public static void main(String[] args) throws Exception {
		DocumentFolderResource.Builder builder =
			DocumentFolderResource.builder();

		DocumentFolderResource documentFolderResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		documentFolderResource.deleteDocumentFolder(
			Long.valueOf(System.getProperty("documentFolderId")));
	}

}