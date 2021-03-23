import com.liferay.headless.delivery.client.dto.v1_0.Document;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentResource;

public class Document_Get {

	/**
	 * java -classpath ".:*" -DdocumentId=1234 Document_Get
	 */
	public static void main(String[] args) throws Exception {
		DocumentResource.Builder builder = DocumentResource.builder();

		DocumentResource documentResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Document document = documentResource.getDocument(
			Long.valueOf(System.getProperty("documentId")));

		System.out.println(document);
	}

}