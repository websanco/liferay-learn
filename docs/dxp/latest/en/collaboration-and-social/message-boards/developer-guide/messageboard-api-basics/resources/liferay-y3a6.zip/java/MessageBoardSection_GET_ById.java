import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardSection;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardSectionResource;

public class MessageBoardSection_GET_ById {

	/**
	 * java -classpath .:* -DmessageBoardSectionId=1234 MessageBoardSection_GET_ById
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardSectionResource.Builder builder = MessageBoardSectionResource.builder();
	
		MessageBoardSectionResource messageBoardSectionResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		MessageBoardSection messageBoardSection = messageBoardSectionResource.getMessageBoardSection(
			Long.valueOf(System.getProperty("messageBoardSectionId")));

		System.out.println(messageBoardSection);
	}

}