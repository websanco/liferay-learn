import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardSectionResource;

public class MessageBoardSection_DELETE_ById {

	/**
	 * java -classpath .:* -DmessageBoardSectionId=1234 MessageBoardSection_DELETE_ById
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardSectionResource.Builder builder = MessageBoardSectionResource.builder();

		MessageBoardSectionResource messageBoardSectionResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		messageBoardSectionResource.deleteMessageBoardSection(
			Long.valueOf(System.getProperty("messageBoardSectionId")));
	}

}