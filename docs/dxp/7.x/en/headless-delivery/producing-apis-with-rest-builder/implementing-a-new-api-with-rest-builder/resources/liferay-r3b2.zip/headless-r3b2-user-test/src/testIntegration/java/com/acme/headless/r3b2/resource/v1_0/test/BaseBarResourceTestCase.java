package com.acme.headless.r3b2.resource.v1_0.test;

import com.acme.headless.r3b2.client.dto.v1_0.Bar;
import com.acme.headless.r3b2.client.http.HttpInvoker;
import com.acme.headless.r3b2.client.pagination.Page;
import com.acme.headless.r3b2.client.resource.v1_0.BarResource;
import com.acme.headless.r3b2.client.serdes.v1_0.BarSerDes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.log.CaptureAppender;
import com.liferay.portal.test.log.Log4JLoggerTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Level;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Jonah the son of Amittai
 * @generated
 */
@Generated("")
public abstract class BaseBarResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_barResource.setContextCompany(testCompany);

		BarResource.Builder builder = BarResource.builder();

		barResource = builder.authentication(
			"test@liferay.com", "test"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Bar bar1 = randomBar();

		String json = objectMapper.writeValueAsString(bar1);

		Bar bar2 = BarSerDes.toDTO(json);

		Assert.assertTrue(equals(bar1, bar2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Bar bar = randomBar();

		String json1 = objectMapper.writeValueAsString(bar);
		String json2 = BarSerDes.toJSON(bar);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Bar bar = randomBar();

		bar.setDescription(regex);
		bar.setName(regex);

		String json = BarSerDes.toJSON(bar);

		Assert.assertFalse(json.contains(regex));

		bar = BarSerDes.toDTO(json);

		Assert.assertEquals(regex, bar.getDescription());
		Assert.assertEquals(regex, bar.getName());
	}

	@Test
	public void testDeleteBar() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		Bar bar = testDeleteBar_addBar();

		assertHttpResponseStatusCode(
			204, barResource.deleteBarHttpResponse(bar.getId()));

		assertHttpResponseStatusCode(
			404, barResource.getBarHttpResponse(bar.getId()));

		assertHttpResponseStatusCode(404, barResource.getBarHttpResponse(0L));
	}

	protected Bar testDeleteBar_addBar() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteBar() throws Exception {
		Bar bar = testGraphQLBar_addBar();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteBar",
						new HashMap<String, Object>() {
							{
								put("barId", bar.getId());
							}
						})),
				"JSONObject/data", "Object/deleteBar"));

		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					"graphql.execution.SimpleDataFetcherExceptionHandler",
					Level.WARN)) {

			JSONArray errorsJSONArray = JSONUtil.getValueAsJSONArray(
				invokeGraphQLQuery(
					new GraphQLField(
						"bar",
						new HashMap<String, Object>() {
							{
								put("barId", bar.getId());
							}
						},
						new GraphQLField("id"))),
				"JSONArray/errors");

			Assert.assertTrue(errorsJSONArray.length() > 0);
		}
	}

	@Test
	public void testGetBar() throws Exception {
		Bar postBar = testGetBar_addBar();

		Bar getBar = barResource.getBar(postBar.getId());

		assertEquals(postBar, getBar);
		assertValid(getBar);
	}

	protected Bar testGetBar_addBar() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetBar() throws Exception {
		Bar bar = testGraphQLBar_addBar();

		Assert.assertTrue(
			equals(
				bar,
				BarSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"bar",
								new HashMap<String, Object>() {
									{
										put("barId", bar.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/bar"))));
	}

	@Test
	public void testGraphQLGetBarNotFound() throws Exception {
		Long irrelevantBarId = RandomTestUtil.randomLong();

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"bar",
						new HashMap<String, Object>() {
							{
								put("barId", irrelevantBarId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	@Test
	public void testPatchBar() throws Exception {
		Bar postBar = testPatchBar_addBar();

		Bar randomPatchBar = randomPatchBar();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Bar patchBar = barResource.patchBar(postBar.getId(), randomPatchBar);

		Bar expectedPatchBar = postBar.clone();

		_beanUtilsBean.copyProperties(expectedPatchBar, randomPatchBar);

		Bar getBar = barResource.getBar(patchBar.getId());

		assertEquals(expectedPatchBar, getBar);
		assertValid(getBar);
	}

	protected Bar testPatchBar_addBar() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutBar() throws Exception {
		Bar postBar = testPutBar_addBar();

		Bar randomBar = randomBar();

		Bar putBar = barResource.putBar(postBar.getId(), randomBar);

		assertEquals(randomBar, putBar);
		assertValid(putBar);

		Bar getBar = barResource.getBar(putBar.getId());

		assertEquals(randomBar, getBar);
		assertValid(getBar);
	}

	protected Bar testPutBar_addBar() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetFooBarsPage() throws Exception {
		Page<Bar> page = barResource.getFooBarsPage(
			testGetFooBarsPage_getFooId());

		Assert.assertEquals(0, page.getTotalCount());

		Long fooId = testGetFooBarsPage_getFooId();
		Long irrelevantFooId = testGetFooBarsPage_getIrrelevantFooId();

		if ((irrelevantFooId != null)) {
			Bar irrelevantBar = testGetFooBarsPage_addBar(
				irrelevantFooId, randomIrrelevantBar());

			page = barResource.getFooBarsPage(irrelevantFooId);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantBar), (List<Bar>)page.getItems());
			assertValid(page);
		}

		Bar bar1 = testGetFooBarsPage_addBar(fooId, randomBar());

		Bar bar2 = testGetFooBarsPage_addBar(fooId, randomBar());

		page = barResource.getFooBarsPage(fooId);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(bar1, bar2), (List<Bar>)page.getItems());
		assertValid(page);

		barResource.deleteBar(bar1.getId());

		barResource.deleteBar(bar2.getId());
	}

	protected Bar testGetFooBarsPage_addBar(Long fooId, Bar bar)
		throws Exception {

		return barResource.postFooBar(fooId, bar);
	}

	protected Long testGetFooBarsPage_getFooId() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetFooBarsPage_getIrrelevantFooId() throws Exception {
		return null;
	}

	@Test
	public void testPostFooBar() throws Exception {
		Bar randomBar = randomBar();

		Bar postBar = testPostFooBar_addBar(randomBar);

		assertEquals(randomBar, postBar);
		assertValid(postBar);
	}

	protected Bar testPostFooBar_addBar(Bar bar) throws Exception {
		return barResource.postFooBar(testGetFooBarsPage_getFooId(), bar);
	}

	protected Bar testGraphQLBar_addBar() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Bar bar1, Bar bar2) {
		Assert.assertTrue(bar1 + " does not equal " + bar2, equals(bar1, bar2));
	}

	protected void assertEquals(List<Bar> bars1, List<Bar> bars2) {
		Assert.assertEquals(bars1.size(), bars2.size());

		for (int i = 0; i < bars1.size(); i++) {
			Bar bar1 = bars1.get(i);
			Bar bar2 = bars2.get(i);

			assertEquals(bar1, bar2);
		}
	}

	protected void assertEqualsIgnoringOrder(List<Bar> bars1, List<Bar> bars2) {
		Assert.assertEquals(bars1.size(), bars2.size());

		for (Bar bar1 : bars1) {
			boolean contains = false;

			for (Bar bar2 : bars2) {
				if (equals(bar1, bar2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(bars2 + " does not contain " + bar1, contains);
		}
	}

	protected void assertValid(Bar bar) throws Exception {
		boolean valid = true;

		if (bar.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (bar.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("fooId", additionalAssertFieldName)) {
				if (bar.getFooId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (bar.getName() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<Bar> page) {
		boolean valid = false;

		java.util.Collection<Bar> bars = page.getItems();

		int size = bars.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (Field field :
				ReflectionUtil.getDeclaredFields(
					com.acme.headless.r3b2.dto.v1_0.Bar.class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					ReflectionUtil.getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(Bar bar1, Bar bar2) {
		if (bar1 == bar2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						bar1.getDescription(), bar2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("fooId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(bar1.getFooId(), bar2.getFooId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(bar1.getId(), bar2.getId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(bar1.getName(), bar2.getName())) {
					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_barResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_barResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, Bar bar) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("description")) {
			sb.append("'");
			sb.append(String.valueOf(bar.getDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("fooId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(bar.getName()));
			sb.append("'");

			return sb.toString();
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected Bar randomBar() throws Exception {
		return new Bar() {
			{
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				fooId = RandomTestUtil.randomLong();
				id = RandomTestUtil.randomLong();
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected Bar randomIrrelevantBar() throws Exception {
		Bar randomIrrelevantBar = randomBar();

		return randomIrrelevantBar;
	}

	protected Bar randomPatchBar() throws Exception {
		return randomBar();
	}

	protected BarResource barResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(":");
					sb.append(entry.getValue());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseBarResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.acme.headless.r3b2.resource.v1_0.BarResource _barResource;

}