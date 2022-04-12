## Add CSS Resources

<div class="ahead">

#### Exercise Goals

- Add CSS resources
- Configure the portlet component to use the provided CSS resources
- Test the changes

</div>

The styling of the assignment list needs polishing. In the _Table_ view, the author column is not aligned and the links should be underlined.

<br />

<img src="images/table_css_before.png" style="max-width:100%;" />

Provide CSS resources for the Gradebook portlet to fix the issues.

Let's first create a CSS file to provide our custom styles for the *gradebook-web* module. 

#### Add CSS Resources
1. **Create** a folder file `resources/META-INF/resources/css`.
1. **Create** a file `resources/META-INF/resources/css/main.scss` and implement as follows:
	
```CSS
.gradebook-portlet {
	
	h1 {
		font-size: 1.7rem;
		margin: 20px 0 10px 0;
	}

	h2 {
		margin: 30px 0 10px 0;
	}
	
	.lfr-search-container-wrapper {
		a {
			text-decoration: underline;
		}
		
		.user-icon {
			float: left;
		}
		
		.user-details {
			vertical-align: sub;
			
			.user-name {
				color: inherit;
			}
		}
	}
	
	.submission-text {
		border: 1px solid #eee;
		border-radius: 5px;		
		padding: 20px;
	}
	
	.assignment-metadata,
	.submission-metadata {
		font-size: .9em;
	
		dt {
			margin-top: 15px;
		}
		
		dd {
		
		}				
	}
	
	.edit-assignment {
	
		.assignment-description {
			font-size: .875rem;
			font-weight: 600;
			
			.reference-mark {
				font-size: 6px;
			}
		}	
	}
}
```

The portlet component needs to know where to load the CSS resources from. Also, it's a good practice to encapsulate portlet styles by wrapping the portlet in a CSS class.

#### Configure the Portlet Component
1. **Open** the `GradebookPortlet.java` portlet class.
1. **Add** the following component properties:

```java
"com.liferay.portlet.css-class-wrapper=gradebook-portlet",
"com.liferay.portlet.header-portlet-css=/css/main.css",
```
	
#### Test the Changes
1. **Refresh** the browser to see the changes after the module has redeployed correctly. 
2. **Switch** the list to the *Table* view using the button on the left side of the search bar, if necessary. The Author column is now better aligned and the links have underlining:

<br />

<img src="images/table_css_after.png" style="max-width:100%;" />

<br />
