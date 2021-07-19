import com.liferay.headless.delivery.client.dto.v1_0.BlogPosting;
import com.liferay.headless.delivery.client.resource.v1_0.BlogPostingResource;

public class BlogPosting_PUT_ById {

	/**
	 * java -classpath .:* -DblogPostingId=1234 BlogPosting_PUT_ById
	 */
	public static void main(String[] args) throws Exception {
		BlogPostingResource.Builder builder =
			BlogPostingResource.builder();

		BlogPostingResource blogPostingResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		BlogPosting blogPosting =
			blogPostingResource.putBlogPosting(
				Long.valueOf(System.getProperty("BlogPostingId")),
				new BlogPosting() {
					{
						headline = "Baker";
						articleBody = "Goo"
					}
				});

		System.out.println(blogPosting);
	}

}