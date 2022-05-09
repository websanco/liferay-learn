import com.liferay.headless.delivery.client.dto.v1_0.ContentField;
import com.liferay.headless.delivery.client.dto.v1_0.ContentFieldValue;
import com.liferay.headless.delivery.client.dto.v1_0.StructuredContent;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentResource;

public class StructuredContent_PUT_ById {

	/**
	 * java -classpath .:* -DcontentStructureId=1234 -DstructuredContentId=5678 StructuredContent_PUT_ById
	 */
	public static void main(String[] args) throws Exception {
		StructuredContentResource.Builder builder =
			StructuredContentResource.builder();

		StructuredContentResource structuredContentResource =
			builder.authentication(
				"test@liferay.com", "learn"
			).build();

		StructuredContent structuredContent =
			structuredContentResource.putStructuredContent(
				Long.valueOf(System.getProperty("structuredContentId")),
				new StructuredContent() {
					{
						contentFields = new ContentField[] {
							new ContentField() {
								{
									contentFieldValue =
										new ContentFieldValue() {
											{
												data = "<p>Goo</p>";
											}
										};
									name = "content";
								}
							}
						};
						contentStructureId = Long.valueOf(
							System.getProperty("contentStructureId"));
						title = "Dog Article";
					}
				});

		System.out.println(structuredContent);
	}

}