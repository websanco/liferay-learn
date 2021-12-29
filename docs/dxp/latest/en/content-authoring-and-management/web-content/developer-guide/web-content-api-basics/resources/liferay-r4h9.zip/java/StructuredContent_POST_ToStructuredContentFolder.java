import com.liferay.headless.delivery.client.dto.v1_0.ContentField;
import com.liferay.headless.delivery.client.dto.v1_0.ContentFieldValue;
import com.liferay.headless.delivery.client.dto.v1_0.StructuredContent;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentResource;

import java.util.Collections;
import java.util.List;

public class StructuredContent_POST_ToStructuredContentFolder {

	/**
	 * java -classpath .:* -DcontentStructureId=1234 -DstructuredContentFolder=5678 StructuredContent_POST_ToStructuredContentFolder
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
							data = "<p>Foo</p>";
						}
					};
					name = "content";
				}
			});

		StructuredContent structuredContent =
			structuredContentResource.
				postStructuredContentFolderStructuredContent(
					Long.valueOf(System.getProperty("structuredContentFolder")),
					new StructuredContent() {
						{
							contentFields = contentList.toArray(
								new ContentField[0]);
							contentStructureId = Long.valueOf(
								System.getProperty("contentStructureId"));
							title = "Easy Article";
						}
					});

		System.out.println(structuredContent);
	}

}