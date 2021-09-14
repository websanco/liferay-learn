import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardMessage;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardMessageResource;

public class MessageBoardMessage_PUT_ById { 

	/**
	 * java -classpath .:* -DmessageBoardMessageId=1234 MessageBoardMessage_PUT_ById
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardMessageResource.Builder builder = MessageBoardMessageResource.builder();

		MessageBoardMessageResource messageBoardMessageResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		MessageBoardMessage messageBoardMessage = messageBoardMessageResource.putMessageBoardMessage(
			Long.valueOf(System.getProperty("messageBoardMessageId")),
			new MessageBoardMessage() {
				{
					articleBody = "Goo";
					headline = "George Message";
				}
			});
		
		System.out.println(messageBoardMessage);
	}

}