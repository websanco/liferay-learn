import com.liferay.headless.delivery.client.dto.v1_0.Document;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentResource;

import java.util.Base64;

public class Document_GET_ById_ContentValue {

	/**
	 * java -classpath .:* -DdocumentId=1234 Document_GET_ById_ContentValue
	 */
	public static void main(String[] args) throws Exception {
		DocumentResource.Builder builder = DocumentResource.builder();

		builder.parameter("nestedFields", "contentValue");

		DocumentResource documentResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Document document = documentResource.getDocument(
			Long.valueOf(System.getProperty("documentId")));

		Base64.Decoder decoder = Base64.getDecoder();

		System.out.println(
			new String(decoder.decode(document.getContentValue())));
	}

}