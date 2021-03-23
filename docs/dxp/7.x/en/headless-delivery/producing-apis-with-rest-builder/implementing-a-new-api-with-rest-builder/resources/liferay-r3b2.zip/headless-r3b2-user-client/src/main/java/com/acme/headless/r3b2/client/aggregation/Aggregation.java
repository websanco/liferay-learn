package com.acme.headless.r3b2.client.aggregation;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * @author Jonah the son of Amittai
 * @generated
 */
@Generated("")
public class Aggregation {

	public Map<String, String> getAggregationTerms() {
		return _aggregationTerms;
	}

	public void setAggregationTerms(Map<String, String> aggregationTerms) {
		_aggregationTerms = aggregationTerms;
	}

	private Map<String, String> _aggregationTerms = new HashMap<>();

}