import com.liferay.headless.delivery.client.dto.v1_0.Document;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentResource;

import java.io.File;

import java.util.HashMap;

public class Document_PUT_ToSite {

	/**
	 * java -classpath .:* -DsiteId=1234 Document_PUT_ToSite
	 */
	public static void main(String[] args) throws Exception {
		DocumentResource.Builder builder = DocumentResource.builder();

		DocumentResource documentResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Document document = documentResource.postSiteDocument(
			Long.valueOf(System.getProperty("siteId")), new Document(),
			new HashMap<String, File>() {
				{
					put("file", new File("Document_PUT_ToSite.java"));
				}
			});

		System.out.println(document);
	}

}