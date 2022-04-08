---
description: 14 - Customize the Portal Search
title: Implement a Keyword Match Query
order: 14
---

<h2 class="exercise">Exercises</h2>

## Implement a Keywork Match Query

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Create a Liferay Module project using the service template</li>
		<li>Extend the BaseIndexerPostProcessor</li>
		<li>Set component properties</li>
		<li>Override the postProcessSearchQuery() method</li>
		<li>Deploy and test</li>
	</ul>
</div>

#### Create a Liferay Module Project

**Option 1: Use the Command Line Blade tools**

1. **Open** the _Command Line_ shell in your Liferay Workspace `modules` folder.
1. **Run** the command:
```bash
blade create -t service -p com.liferay.training.search -s com.liferay.portal.kernel.search.IndexerPostProcessor -c BoostingIndexerPostProcessor indexer-post-processor
```
1. **Run** Gradle refresh on the IDE.
	
**Option 2: Use Developer Studio Wizard**

1. **Launch** the *Liferay Module Project* wizard in Developer Studio.
1. **Use** the following information for the first step:
	* __Project Name__:  "indexer-post-processor"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.2
	* __Project Template__: service
1. **Click** *Next* and use the following information in the second step:
	* __Component Class Name__: "BoostingIndexerPostProcessor"
	* __Package Name__: "com.liferay.training.search"
	* __Service Name__: "com.liferay.portal.kernel.search.IndexerPostProcessor"
1. **Click** *Finish* to close the wizard.

#### Extend the BaseIndexerPostProcessor

Our generated class declaration implements the `IndexerPostProcessor` interface. Implementing that directly would require a lot of boilerplate code in the class. We'll change the class declaration so that it extends the `BaseIndexerPostProcessor`. That way, we only need to implement the methods we need.

1. **Open** the class `com.liferay.training.search.BoostingIndexerPostProcessor`.
1. **Change** the class declaration as follows:

```java
public class BoostingIndexerPostProcessor extends BaseIndexerPostProcessor
```

#### Set Component Properties

We have to configure the component properties with the property `indexer.class.name`, which defines what model types the processor should handle. We'll configure it only to apply to Journal Articles: 

1. **Open** the class `com.liferay.training.search.BoostingIndexerPostProcessor`.
1. **Implement** the `@Component` annotation:

```java
@Component(
	immediate = true,
	property = {
		"indexer.class.name=com.liferay.journal.model.JournalArticle",
	},
	service = IndexerPostProcessor.class
)
```

#### Override the `postProcessSearchQuery()` Method

1. **Open** the class `com.liferay.training.search.BoostingIndexerPostProcessor`.
1. **Right-click** somewhere on the class code.
1. **Select** *Source -> Override/Implement Methods*.
1. **Select** the `postProcessSearchQuery()` method.
1. **Implement** the method as follows:
```java
@Override
public void postProcessSearchQuery(
	BooleanQuery searchQuery, BooleanFilter booleanFilter,
	SearchContext searchContext)
	throws Exception {

	String keywords = searchContext.getKeywords();

	System.out.println("Keywords: " + keywords);

	// If keywords entered by the user contain word "spec", 
	// boost contents having a tag "specification".

	if (Validator.isNotNull(keywords)) {

		String[] searchTerms = keywords.toLowerCase().split(" ");

		for (String s : searchTerms) {
			if (s.equals("spec")) {
				TermQuery termQuery = new TermQueryImpl(
					Field.ASSET_TAG_NAMES, "specification");
				termQuery.setBoost(100);
				searchQuery.add(termQuery, BooleanClauseOccur.SHOULD);

			}
		}
	}
}
```
1. **Resolve** missing imports.

> Choose `com.liferay.portal.kernel.search.Field` and `com.liferay.portal.kernel.util.Validator` when prompted for the right import.

#### Final Code Review

1. **Check** that the `BoostingIndexerPostProcessor` class looks like this:

```java

package com.liferay.training.search;

import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author liferay
 */
@Component(
	immediate = true, 
	property = {
		"indexer.class.name=com.liferay.journal.model.JournalArticle"
	}, 
	service = IndexerPostProcessor.class
)
public class BoostingIndexerPostProcessor extends BaseIndexerPostProcessor {

	@Override
	public void postProcessSearchQuery(
		BooleanQuery searchQuery, BooleanFilter booleanFilter,
		SearchContext searchContext)
		throws Exception {

		String keywords = searchContext.getKeywords();

		System.out.println("Keywords: " + keywords);

		// If keywords entered by the user contain word "spec", 
		// boost contents having a tag "specification".

		if (Validator.isNotNull(keywords)) {

			String[] searchTerms = keywords.toLowerCase().split(" ");

			for (String s : searchTerms) {
				if (s.equals("spec")) {
					TermQuery termQuery = new TermQueryImpl(
						Field.ASSET_TAG_NAMES, "specification");
					termQuery.setBoost(100);
					searchQuery.add(termQuery, BooleanClauseOccur.SHOULD);

				}
			}
		}
	}
}
```

#### Deploy and Test

For testing, we need to create two test pieces of content. 

1. **Create** the first piece of web content with the following information:
	* __Title__: "Official Fox"
	* __Content__:
	```
	The quick brown fox jumps over the lazy dog
	```
1. **Tag** the content with the word "specification".
1. **Create** the second test web content with the following information:
	* __Title__: "Regular Fox"
	* __Content__:
	```
	The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog.
	```
	> This content has more occurrences of "fox" and should appear first on the results list.
1. **Open** the portal home page.
1. **Search** for "fox" using the search bar. 
	* The "Regular Fox" should appear first on the list because it has more occurrences of "fox":
	<img src="../images/fox.png" style="max-height:12%;" />
1. **Search** for "fox spec" using the search bar. 
	* The "Official Fox" should appear first on the list because of our customization:
	<img src="../images/fox-spec.png" style="max-height:18%;" />

#### Takeaways

We've demonstrated how to extend the querying functionality of the Liferay search framework. In the same way, IndexexPostProcessors can be used to intercept content indexing, for example, to enrich the index document data. Using these techniques, you should be able to customize the search for any of the built-in Liferay entities. In addition, developers should be able to use this same technique to extend the search functionality of custom applications as well.
