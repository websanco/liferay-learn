import com.liferay.headless.delivery.client.dto.v1_0.BlogPosting;
import com.liferay.headless.delivery.client.resource.v1_0.BlogPostingResource;

public class BlogPosting_GET_ById {

	/**
	 * java -classpath .:* -DblogPostingId=1234 BlogPosting_GET_ById
	 */
	public static void main(String[] args) throws Exception {
		BlogPostingResource.Builder builder = BlogPostingResource.builder();

		BlogPostingResource blogPostingResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		BlogPosting blogPosting = blogPostingResource.getBlogPosting(
			Long.valueOf(System.getProperty("blogPostingId")));

		System.out.println(blogPosting);
	}

}