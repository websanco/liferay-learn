import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardSection;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardSectionResource;

public class MessageBoardSection_POST_ToSite {

	/**
	 * java -classpath .:* -DsiteId=1234 MessageBoardSection_POST_ToSite
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardSectionResource.Builder builder = MessageBoardSectionResource.builder();
	
		MessageBoardSectionResource messageBoardSectionResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		MessageBoardSection messageBoardSection = messageBoardSectionResource.postSiteMessageBoardSection(
			Long.valueOf(System.getProperty("siteId")),
			new MessageBoardSection() {
				{
					description = "Foo";
					title = "Charlie Section";
				}
			});
		
		System.out.println(messageBoardSection);
	}

}