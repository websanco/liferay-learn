import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardThread;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardThreadResource;

public class MessageBoardThread_GET_ById {

	/**
	 * java -classpath .:* -DmessageBoardThreadId=1234 MessageBoardThread_GET_ById
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardThreadResource.Builder builder = MessageBoardThreadResource.builder();

		MessageBoardThreadResource messageBoardThreadResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		MessageBoardThread messageBoardThread = messageBoardThreadResource.getMessageBoardThread(
			Long.valueOf(System.getProperty("messageBoardThreadId")));

		System.out.println(messageBoardThread);
	}

}