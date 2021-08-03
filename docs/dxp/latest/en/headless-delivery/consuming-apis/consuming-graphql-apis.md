# Consuming GraphQL APIs

Liferay DXP contains [GraphQL](https://graphql.org) APIs for most of its applications. Here, you'll learn how to consume them. This takes only three steps:

1. Identify the API you wish to consume.
1. Identify the site containing the data you need.
1. Make the API call using credentials that have access to the data.

## Identify the Service to Consume

You need a running Liferay DXP to call its GraphQL APIs. To obtain one using Docker, run this command:

```bash
docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
```

After Liferay DXP initializes, you can find the service you need.

1. Go to `http://localhost:8080` and sign in using the default credentials:
    **User Name:** `test@liferay.com`
    **Password:** `test`

1. Go to this URL:

        http://localhost:8080/o/api

1. At the top-left of the screen that appears, pick *headless-delivery* from the selector. This is the category that contains the Blog posting API.

1. Click the *Show GraphQL* button at the top-right of the screen to open Liferay's [GraphiQL](https://github.com/graphql/graphiql) browser.

1. Click the *Docs* link below the button you just clicked. Now you can browse the API.

1. GraphQL separates read and write operations by calling the first a *query* and the second a *mutation*. Since the first thing you want to do is post a blog entry, click *mutation*.

1. A list appears of the entire API use the search at the top or scroll down and find the call to `createSiteBlogPosting`:

    ```graphql
    createSiteBlogPosting(
      blogPosting: InputBlogPosting
      siteKey: String!
      ): BlogPosting
    ```

```note::
   You can also discover your local installation's APIs by requesting the schema directly:

   ``curl 'http://localhost:8080/o/graphql'  -H 'Content-Type: application/json' --data '{"query":"query{ __schema{ queryType{ name fields{ name args{ name } description } } } }","variables":{}}'``


   This URL does not require authentication, but it's quite cumbersome to manage the returned schema. For this reason, it's better to use the included GraphQL client.
   ```

![The included GraphQL client has a schema documentation browser.](./consuming-graphql-apis/images/01.png)

Since the API requires you to know the Site containing the Blog where the entry should be posted, you must find the Site ID first.

## Identify the Site Containing the Data

Visit your site with your browser at `http://localhost:8080`.

Now you must find the default Site ID:

1. Sign in using the default credentials:
   **User Name:** `test@liferay.com`
   **Password:** `test`
1. Go to Control Panel &rarr; Sites &rarr; Sites.
1. Click the Actions button next to the Liferay Site and choose *Go to Site Settings*.

The Site ID appears at the top of the Details section. It's an Integer, like `20122`.

## Make the Service Call using Credentials with Access to the Data

Now you have everything you need to make the call. All web services must be accessed using credentials that have access to the data you're requesting. The included GraphQL client authenticates using your browser. If your plan is to write a standalone client, you should authorize users via [OAuth2](../using-oauth2/using-oauth2.md).

During development, it's much easier to use Basic Auth, which passes credential data in the URL. Since this is insecure, *never use this method for production.*

### Calling a GraphQL API Using Basic Auth (During Development Only)

To call a service using Basic Auth, provide the credentials in the URL:

```bash
curl --request POST --url http://localhost:8080/o/graphql \ -u test@liferay.com:test  --header 'content-type: application/json' --data '{"query":"query {blogPostings(filter: \"\", page: 1, pageSize: 10, search: \"\", siteKey: \"20122\", sort: \"\"){ page  items{ id articleBody headline  creator{ name }}}}"}'
```

### Calling a Service Using OAuth2

For production, create an [OAuth2 application](../using-oauth2/creating-oauth2-applications.md) and use the OAuth2 process to get an authorization token. Once you have the token, provide it in the HTTP header:

```bash
curl --request POST --url http://localhost:8080/o/graphql -H "Authorization: Bearer d5571ff781dc555415c478872f0755c773fa159" --header 'content-type: application/json' --data '{"query":"query {blogPostings(filter: \"\", page: 1, pageSize: 10, search: \"\", siteKey: \"20122\", sort: \"\"){ page  items{ id articleBody headline  creator{ name }}}}"}'
```

## Getting and Posting Data

In the top left window of the GraphQL client, place this code, which retrieves all blog entries:

```graphql
query {blogPostings(filter:"",page:1,pageSize:10,search:"",siteKey:"20122",sort:"")
	{page
     items {
        id
  		articleBody
  		headline
  		creator
  			{name
            }
  }
  }
}
```

Click the play button to run it, and you'll see there aren't any blog entries:

```json
{"data":{"blogPostings":{"page":1,"items":[]}}}
```

Now you'll post a blog entry.

### Posting a Blog Entry

The GraphQL schema revealed the call that must be made to post a blog entry.

1. Construct a JSON document containing the entry you wish to publish:

   ```json
   {
     "blog": {
         "articleBody": "This Blog entry was created by calling the GraphQL service!",
         "headline": "GraphQL Blog Entry"
     }
   }
    ```

1. Construct the GraphQL query based on the schema documentation:

   ```
   mutation CreateBlog($blog: InputBlogPosting){
     createSiteBlogPosting(blogPosting: $blog, siteKey: "20122" ) {
       headline
       articleBody
       id
       friendlyUrlPath
     }

    }
    ```

1. Make the request by visiting `http://localhost:8080/o/api` again. Click the *Show GraphQL* button.

1. Paste your JSON document into the Query Variables box at the lower left.

1. Paste your mutation in the unlabeled box at the top right.

1. Run your query by clicking the play button at the top.

The blog entry you added now appears in the GraphQL client's right pane:

![The GraphQL client makes it easy to call GraphQL services directly from your browser.](./consuming-graphql-apis/images/02.png)

Liferay DXP returns a JSON representation of your blog entry that contains the fields you requested in the mutation:

```json
{
  "data": {
    "createSiteBlogPosting": {
      "headline": "GraphQL Blog Entry",
      "articleBody": "This Blog entry was created by calling the GraphQL service!",
      "id": 35541,
      "friendlyUrlPath": "graphql-blog-entry"
    }
  }
}
```

```note::
You can make these requests with any web client, such as cURL:

   ``curl --request POST --url http://localhost:8080/o/graphql -u test@liferay.com:test --header 'content-type: application/json' --data '{"query":"mutation CreateBlog($blog: InputBlogPosting){   createSiteBlogPosting(blogPosting: $blog, siteKey: \"20122\" ) {    headline    articleBody    id    friendlyUrlPath  }    } ","variables":{"blog":{"articleBody":"This Blog entry was created by using cURL to call the GraphQL service!","headline":"cURL GraphQL Blog Entry"}},"operationName":"CreateBlog"}'``
```

### Getting All Blog Entries

Now you can repeat the first query you did:

```graphql
query {blogPostings(filter:"",page:1,pageSize:10,search:"",siteKey:"20122",sort:"")
	{page
     items {
        id
  		articleBody
  		headline
  		creator
  			{name
            }
  }
  }
}
```

Liferay DXP returns JSON containing the blog entry you posted:

```json
{
  "data": {
    "blogPostings": {
      "page": 1,
      "items": [
        {
          "id": 35541,
          "articleBody": "This Blog entry was created by calling the GraphQL service!",
          "headline": "GraphQL Blog Entry",
          "creator": {
            "name": "Test Test"
          }
        }
      ]
    }
  }
}
```

### Getting a Single Blog Entry

The API call from the GraphQL schema for getting a single Blog entry has only one parameter:

```
blogPosting(
   blogPostingId: Long
): BlogPosting
```

Since the query above revealed your Blog post's ID, you can retrieve just the post you want:

```graphql
query {blogPosting(blogPostingId: 35541)
  { id
    headline
    articleBody}
}
```

Paste this into the top left window of the client and click the *Play* button. It returns the same blog entry:

```json
{
  "data": {
    "blogPosting": {
      "id": 35541,
      "headline": "GraphQL Blog Entry",
      "articleBody": "This Blog entry was created by calling the GraphQL service!"
    }
  }
}
```

### Deleting a Blog Entry

Deleting a blog entry, like creating one, is a mutation. Its call is almost the same as getting a single blog entry:

```
deleteBlogPosting(
  blogPostingId: Long
): Boolean
```

Using the client, you can make the call like this:

```graphql
mutation {
  deleteBlogPosting(blogPostingId: 35541)
}
```

This call returns a Boolean in a JSON document denoting success or failure:

```json
{
  "data": {
    "deleteBlogPosting": true
  }
}
```

Congratulations! You've now learned how to call Liferay DXP's GraphQL services. Remember that the examples above use Basic Auth: for production, use OAuth2 to call services in a secure way.
