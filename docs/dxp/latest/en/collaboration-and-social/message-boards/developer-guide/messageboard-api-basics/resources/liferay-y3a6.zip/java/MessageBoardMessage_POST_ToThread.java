import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardMessage;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardMessageResource;

public class MessageBoardMessage_POST_ToThread {

	/**
	 * java -classpath .:* -DmessageBoardThreadId=1234 MessageBoardMessage_POST_ToThread
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardMessageResource.Builder builder =
			MessageBoardMessageResource.builder();

		MessageBoardMessageResource messageBoardMessageResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		MessageBoardMessage messageBoardMessage =
			messageBoardMessageResource.
				postMessageBoardThreadMessageBoardMessage(
					Long.valueOf(System.getProperty("messageBoardThreadId")),
					new MessageBoardMessage() {
						{
							articleBody = "Foo";
							headline = "Dog Message";
						}
					});

		System.out.println(messageBoardMessage);
	}

}