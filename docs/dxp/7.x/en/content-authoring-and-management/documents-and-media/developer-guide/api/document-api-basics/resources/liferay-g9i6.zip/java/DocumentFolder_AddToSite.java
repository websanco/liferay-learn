import com.liferay.headless.delivery.client.dto.v1_0.DocumentFolder;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentFolderResource;

public class DocumentFolder_AddToSite {

	/**
	 * java -classpath .:* -DsiteId=1234 DocumentFolder_AddToSite
	 */
	public static void main(String[] args) throws Exception {
		DocumentFolderResource.Builder builder =
			DocumentFolderResource.builder();

		DocumentFolderResource documentFolderResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		DocumentFolder documentFolder =
			documentFolderResource.postSiteDocumentFolder(
				Long.valueOf(System.getProperty("siteId")),
				new DocumentFolder() {
					{
						name = "foo";
					}
				});

		System.out.println(documentFolder);
	}

}