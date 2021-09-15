import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardThread;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardThreadResource;

public class MessageBoardThread_POST_ToSection {

	/**
	 * java -classpath .:* -DmessageBoardSectionId=1234 MessageBoardThread_POST_ToSection
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardThreadResource.Builder builder =
			MessageBoardThreadResource.builder();

		MessageBoardThreadResource messageBoardThreadResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		MessageBoardThread messageBoardThread =
			messageBoardThreadResource.
				postMessageBoardSectionMessageBoardThread(
					Long.valueOf(System.getProperty("messageBoardSectionId")),
					new MessageBoardThread() {
						{
							articleBody = "Foo";
							headline = "Easy Thread";
						}
					});

		System.out.println(messageBoardThread);
	}

}