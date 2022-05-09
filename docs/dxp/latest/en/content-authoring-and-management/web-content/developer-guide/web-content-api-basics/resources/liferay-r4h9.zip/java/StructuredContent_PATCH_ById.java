import com.liferay.headless.delivery.client.dto.v1_0.ContentField;
import com.liferay.headless.delivery.client.dto.v1_0.ContentFieldValue;
import com.liferay.headless.delivery.client.dto.v1_0.StructuredContent;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentResource;

public class StructuredContent_PATCH_ById {

	/**
	 * java -classpath .:* -DcontentStructureId=1234 -DstructuredContentId=5678 StructuredContent_PATCH_ById
	 */
	public static void main(String[] args) throws Exception {
		StructuredContentResource.Builder builder =
			StructuredContentResource.builder();

		StructuredContentResource structuredContentResource =
			builder.authentication(
				"test@liferay.com", "learn"
			).build();

		StructuredContent structuredContent =
			structuredContentResource.patchStructuredContent(
				Long.valueOf(System.getProperty("structuredContentId")),
				new StructuredContent() {
					{
						contentFields = new ContentField[] {
							new ContentField() {
								{
									contentFieldValue =
										new ContentFieldValue() {
											{
												data = "<p>Bar</p>";
											}
										};
									name = "content";
								}
							}
						};
						contentStructureId = Long.valueOf(
							System.getProperty("contentStructureId"));
					}
				});

		System.out.println(structuredContent);
	}

}