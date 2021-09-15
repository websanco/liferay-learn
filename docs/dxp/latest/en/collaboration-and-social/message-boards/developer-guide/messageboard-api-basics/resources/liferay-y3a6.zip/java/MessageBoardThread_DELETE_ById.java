import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardThreadResource;

public class MessageBoardThread_DELETE_ById {

	/**
	 * java -classpath .:* -DmessageBoardThreadId=1234 MessageBoardThread_DELETE_ById
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardThreadResource.Builder builder =
			MessageBoardThreadResource.builder();

		MessageBoardThreadResource messageBoardThreadResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		messageBoardThreadResource.deleteMessageBoardThread(
			Long.valueOf(System.getProperty("messageBoardThreadId")));
	}

}