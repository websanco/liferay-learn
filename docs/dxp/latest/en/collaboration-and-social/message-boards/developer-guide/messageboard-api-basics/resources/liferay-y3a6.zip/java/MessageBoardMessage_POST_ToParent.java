import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardMessage;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardMessageResource;

public class MessageBoardMessage_POST_ToParent {

	/**
	 * java -classpath .:* -DmessageBoardMessageId=1234 MessageBoardMessage_POST_ToParent
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardMessageResource.Builder builder = MessageBoardMessageResource.builder();

		MessageBoardMessageResource messageBoardMessageResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		MessageBoardMessage messageBoardMessage = messageBoardMessageResource.postMessageBoardMessageMessageBoardMessage(
			Long.valueOf(System.getProperty("messageBoardMessageId")),
			new MessageBoardMessage() {
				{
					articleBody = "Foo";
					headline = "Fox Message";
				}
			});
		
		System.out.println(messageBoardMessage);
	}

}