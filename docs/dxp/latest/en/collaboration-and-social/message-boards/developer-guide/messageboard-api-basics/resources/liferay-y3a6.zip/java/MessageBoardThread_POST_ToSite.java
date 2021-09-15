import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardThread;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardThreadResource;

public class MessageBoardThread_POST_ToSite {

	/**
	 * java -classpath .:* -DsiteId=1234 MessageBoardThread_POST_ToSite
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardThreadResource.Builder builder =
			MessageBoardThreadResource.builder();

		MessageBoardThreadResource messageBoardThreadResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		MessageBoardThread messageBoardThread =
			messageBoardThreadResource.postSiteMessageBoardThread(
				Long.valueOf(System.getProperty("siteId")),
				new MessageBoardThread() {
					{
						articleBody = "Foo";
						headline = "Dog Thread";
					}
				});

		System.out.println(messageBoardThread);
	}

}