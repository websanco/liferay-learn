package com.acme.headless.r3b2.client.serdes.v1_0;

import com.acme.headless.r3b2.client.dto.v1_0.Foo;
import com.acme.headless.r3b2.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Jonah the son of Amittai
 * @generated
 */
@Generated("")
public class FooSerDes {

	public static Foo toDTO(String json) {
		FooJSONParser fooJSONParser = new FooJSONParser();

		return fooJSONParser.parseToDTO(json);
	}

	public static Foo[] toDTOs(String json) {
		FooJSONParser fooJSONParser = new FooJSONParser();

		return fooJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Foo foo) {
		if (foo == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (foo.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(foo.getDescription()));

			sb.append("\"");
		}

		if (foo.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(foo.getId());
		}

		if (foo.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(foo.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		FooJSONParser fooJSONParser = new FooJSONParser();

		return fooJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Foo foo) {
		if (foo == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (foo.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put("description", String.valueOf(foo.getDescription()));
		}

		if (foo.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(foo.getId()));
		}

		if (foo.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(foo.getName()));
		}

		return map;
	}

	public static class FooJSONParser extends BaseJSONParser<Foo> {

		@Override
		protected Foo createDTO() {
			return new Foo();
		}

		@Override
		protected Foo[] createDTOArray(int size) {
			return new Foo[size];
		}

		@Override
		protected void setField(
			Foo foo, String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					foo.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					foo.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					foo.setName((String)jsonParserFieldValue);
				}
			}
			else if (jsonParserFieldName.equals("status")) {
				throw new IllegalArgumentException();
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}