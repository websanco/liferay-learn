import com.liferay.headless.delivery.client.dto.v1_0.ContentField;
import com.liferay.headless.delivery.client.dto.v1_0.ContentFieldValue;
import com.liferay.headless.delivery.client.dto.v1_0.StructuredContent;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentResource;

import java.util.Collections;
import java.util.List;

public class StructuredContent_PATCH_ById {

	/**
	 * java -classpath .:* -DstructuredContentId=1234 StructuredContent_PATCH_ById
	 */
	public static void main(String[] args) throws Exception {
		StructuredContentResource.Builder builder =
			StructuredContentResource.builder();

		StructuredContentResource structuredContentResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		List<ContentField> contentList = Collections.singletonList(
			new ContentField() {
				{
					contentFieldValue = new ContentFieldValue() {
						{
							data = "<p>Bar</p>";
						}
					};
					name = "content";
				}
			});

		StructuredContent structuredContent =
			structuredContentResource.patchStructuredContent(
				Long.valueOf(System.getProperty("structuredContentId")),
				new StructuredContent() {
					{
						contentFields = contentList.toArray(
							new ContentField[0]);
					}
				});

		System.out.println(structuredContent);
	}

}