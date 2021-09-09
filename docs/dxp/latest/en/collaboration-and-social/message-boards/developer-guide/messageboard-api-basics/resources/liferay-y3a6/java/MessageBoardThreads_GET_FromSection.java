import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardThread;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardThreadResource;

public class MessageBoardThreads_GET_FromSection {

	/**
	 * java -classpath .:* -DmessageBoardSectionId=1234 MessageBoardThreads_GET_FromSection
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardThreadResource.Builder builder = MessageBoardThreadResource.builder();

		MessageBoardThreadResource messageBoardThreadResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<MessageBoardThread> page = messageBoardThreadResource.getMessageBoardSectionMessageBoardThreadsPage(
			Long.valueOf(System.getProperty("messageBoardSectionId")), null, null, null,
			Pagination.of(1, 2), null);

		System.out.println(page);
	}

}