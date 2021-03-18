import com.liferay.headless.delivery.client.dto.v1_0.Document;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentResource;

import java.io.File;

import java.util.HashMap;
import java.util.Map;

public class Document_Add {

	public static void main(String[] args) throws Exception {
		String id = System.getProperty("site.id", "20121");

		DocumentResource.Builder builder = DocumentResource.builder();

		DocumentResource documentResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Map<String, File> multipartFiles = new HashMap<>();

		multipartFiles.put("file", new File("../resources/g9i6.txt"));

		Document response = documentResource.postSiteDocument(
			Long.valueOf(id), new Document(), multipartFiles);

		System.out.println(response);
	}

}