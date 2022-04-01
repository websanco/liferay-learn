---
description: 8 - Customize the User Interface
title: Create a Media Gallery Widget Template
order: 1
---

<h2 class="exercise">Exercises</h2>

## Create a Media Gallery Widget Template

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Add the Media Gallery widget to the page</li>
		<li>Upload images</li>
		<li>Create a custom Widget Template</li>
		<li>Verify your custom carousel</li>
	</ul>
</div>

## Add the Media Gallery

1. **Open** your browser to http://localhost:8080 and sign in.
1. **Add** the *Media Gallery* widget onto the page.
1. **Click** the *Add* icon on the top left corner of the Media Gallery portlet.  
1. **Choose** *Multiple Files Upload*.
1. **Click** *Select* and browse to the folder `exercise-files/08-customize-the-user-interface` provided in the materials.
1. **Upload** and publish all the files.

<img src="../images/upload-to-gallery.png" style="max-height:30%;"/>

#### Create a Custom Widget Display Template

1. **Click** the *Options* icon in the top left corner of the Media Gallery.
* **Choose** *Configuration*.
* **Click** *Manage Templates* under the *Display Template* section.
* **Click** the plus button to add a new template.
* **Enter** "My Custom Carousel" as the *Name*.
* **Implement** the template as follows:
```xml
	<style>
		#<@portlet.namespace />carousel {
			position: relative;
		}

		#<@portlet.namespace />carousel .slides {
			width: 100%;
		}

		#<@portlet.namespace />carousel h1 {
			color: white;
			font-size: 50px;
			position: absolute; 
			text-align: center;
			top: 100px;
			width: 100%;
		}
	</style>

	<#if entries?has_content>
		<div id="<@portlet.namespace />carousel" name="<@portlet.namespace />carousel">
			<#assign imageMimeTypes = propsUtil.getArray("dl.file.entry.preview.image.mime.types") />

			<#list entries as entry>
				<#if imageMimeTypes?seq_contains(entry.getMimeType())>
					<img class="slides" src="${dlUtil.getPreviewURL(entry, entry.getFileVersion(), themeDisplay, "")}">
				</#if>
			</#list>
			<h1>My Custom Carousel</h1>
		</div>

		<script>
			var slideIndex = 0;
			carousel();
			
			function carousel() {
				var i;
				var x = document.getElementsByClassName("slides");
				for (i = 0; i < x.length; i++) {
					x[i].style.display = "none"; 
				}
				slideIndex++;
				if (slideIndex > x.length) {slideIndex = 1} 
				x[slideIndex-1].style.display = "block"; 
				setTimeout(carousel, 3000); // Change image every 3 seconds
			}
		</script>
	</#if>
```
7. **Save** and close the dialog.
8. **Choose** *My Custom Carousel* as the *Display Template* from the drop-down menu.
	- You may have to refresh the page to see the new option in the drop-down menu.
9. **Save** and close the configuration dialog.

<img src="../images/carousel-example.png" style="max-height:40%;" />