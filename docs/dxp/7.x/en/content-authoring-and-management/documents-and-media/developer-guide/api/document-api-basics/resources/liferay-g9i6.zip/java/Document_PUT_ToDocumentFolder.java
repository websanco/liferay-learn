import com.liferay.headless.delivery.client.dto.v1_0.Document;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentResource;

import java.io.File;

import java.util.HashMap;

public class Document_PUT_ToDocumentFolder {

	/**
	 * java -classpath .:* -DdocumentFolderId=1234 Document_PUT_ToDocumentFolder
	 */
	public static void main(String[] args) throws Exception {
		DocumentResource.Builder builder = DocumentResource.builder();

		DocumentResource documentResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Document document = documentResource.postDocumentFolderDocument(
			Long.valueOf(System.getProperty("documentFolderId")),
			new Document(),
			new HashMap<String, File>() {
				{
					put("file", new File("Document_PUT_ToDocumentFolder.java"));
				}
			});

		System.out.println(document);
	}

}