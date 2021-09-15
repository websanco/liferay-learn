import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardSection;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardSectionResource;

public class MessageBoardSection_PUT_ById {

	/**
	 * java -classpath .:* -DmessageBoardSectionId=1234 MessageBoardSection_PUT_ById
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardSectionResource.Builder builder = MessageBoardSectionResource.builder();
	
		MessageBoardSectionResource messageBoardSectionResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		MessageBoardSection messageBoardSection = messageBoardSectionResource.putMessageBoardSection(
			Long.valueOf(System.getProperty("messageBoardSectionId")),
			new MessageBoardSection() {
				{
					description = "Goo";
					title = "Dog Section";
				}
			});
		
		System.out.println(messageBoardSection);
	}

}