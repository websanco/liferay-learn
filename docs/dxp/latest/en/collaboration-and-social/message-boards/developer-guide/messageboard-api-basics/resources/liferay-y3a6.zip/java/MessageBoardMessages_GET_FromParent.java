import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardMessage;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.MessageBoardMessageResource;

public class MessageBoardMessages_GET_FromParent {

	/**
	 * java -classpath .:* -DparentMessageBoardMessageId=1234 MessageBoardMessages_GET_FromParent
	 */
	public static void main(String[] args) throws Exception {
		MessageBoardMessageResource.Builder builder = MessageBoardMessageResource.builder();

		MessageBoardMessageResource messageBoardMessageResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		Page<MessageBoardMessage> page = messageBoardMessageResource.getMessageBoardMessageMessageBoardMessagesPage(
			Long.valueOf(System.getProperty("parentMessageBoardMessageId")), null, null, null, null,
			Pagination.of(1, 2), null);
		
		System.out.println(page);
	}

}