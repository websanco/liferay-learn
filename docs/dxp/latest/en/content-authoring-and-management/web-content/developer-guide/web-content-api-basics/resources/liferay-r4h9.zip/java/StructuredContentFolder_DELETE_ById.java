import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentFolderResource;

public class StructuredContentFolder_DELETE_ById {

	/**
	 * java -classpath .:* -DstructuredContentFolderId=1234 StructuredContentFolder_DELETE_ById
	 */
	public static void main(String[] args) throws Exception {
		StructuredContentFolderResource.Builder builder =
			StructuredContentFolderResource.builder();

		StructuredContentFolderResource structuredContentFolderResource =
			builder.authentication(
				"test@liferay.com", "learn"
			).build();

		structuredContentFolderResource.deleteStructuredContentFolder(
			Long.valueOf(System.getProperty("structuredContentFolderId")));
	}

}