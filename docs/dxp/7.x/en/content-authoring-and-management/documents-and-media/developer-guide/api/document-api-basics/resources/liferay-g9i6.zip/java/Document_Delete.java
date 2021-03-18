import com.liferay.headless.delivery.client.dto.v1_0.Document;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentResource;

public class Document_Delete {

	public static void main(String[] args) throws Exception {
		String id = System.getProperty("document.id", "");

		if (id.isEmpty()) {
			StringBuilder sb = new StringBuilder(4);

			sb.append("Usage: java -classpath ");
			sb.append(".:com.liferay.headless.delivery.client.jar ");
			sb.append("-Ddocument.id=[id] ");
			sb.append(Document_Delete.class.getName());

			System.err.println(sb.toString());

			System.exit(1);
		}

		DocumentResource.Builder builder = DocumentResource.builder();

		DocumentResource documentResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		documentResource.deleteDocument(Long.valueOf(id));
	}

}