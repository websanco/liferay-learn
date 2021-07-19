import com.liferay.headless.delivery.client.dto.v1_0.BlogPosting;
import com.liferay.headless.delivery.client.resource.v1_0.BlogPostingResource;

public class xBlogPosting_POST_ToSite {

	/**
	 * java -classpath .:* -DsiteId=1234 BlogPosting_POST_ToSite
	 */
	public static void main(String[] args) throws Exception {
		BlogPostingResource.Builder builder =
			BlogPostingResource.builder();

		BlogPostingResource blogPostingResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		BlogPosting blogPosting =
			BlogPostingResource.postSiteBlogPosting(
				Long.valueOf(System.getProperty("siteId")),
				new BlogPosting() {
					{
						headline = "Baker";
						articleBody = "Foo"
					}
				});

		System.out.println(blogPosting);
	}

}