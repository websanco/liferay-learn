import com.liferay.headless.delivery.client.dto.v1_0.StructuredContent;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentResource;

public class StructuredContent_GET_ById {

	/**
	 * java -classpath .:* -DstructuredContentId=1234 StructuredContent_GET_ById
	 */
	public static void main(String[] args) throws Exception {
		StructuredContentResource.Builder builder =
			StructuredContentResource.builder();

		StructuredContentResource structuredContentResource =
			builder.authentication(
				"test@liferay.com", "learn"
			).build();

		StructuredContent structuredContent =
			structuredContentResource.getStructuredContent(
				Long.valueOf(System.getProperty("structuredContentId")));

		System.out.println(structuredContent);
	}

}