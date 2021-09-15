import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardSection;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardSectionResource;

public class MessageBoardSection_PATCH_ById {

	/**
	 * java -classpath .:* -DmessageBoardSectionId=1234 MessageBoardSection_PATCH_ById
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardSectionResource.Builder builder =
			MessageBoardSectionResource.builder();

		MessageBoardSectionResource messageBoardSectionResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		MessageBoardSection messageBoardSection =
			messageBoardSectionResource.patchMessageBoardSection(
				Long.valueOf(System.getProperty("messageBoardSectionId")),
				new MessageBoardSection() {
					{
						description = "Bar";
					}
				});

		System.out.println(messageBoardSection);
	}

}