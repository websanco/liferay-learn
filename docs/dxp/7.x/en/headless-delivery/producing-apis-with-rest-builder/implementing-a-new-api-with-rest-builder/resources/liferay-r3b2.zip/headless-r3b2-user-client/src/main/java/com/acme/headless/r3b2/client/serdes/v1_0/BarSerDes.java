package com.acme.headless.r3b2.client.serdes.v1_0;

import com.acme.headless.r3b2.client.dto.v1_0.Bar;
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
public class BarSerDes {

	public static Bar toDTO(String json) {
		BarJSONParser barJSONParser = new BarJSONParser();

		return barJSONParser.parseToDTO(json);
	}

	public static Bar[] toDTOs(String json) {
		BarJSONParser barJSONParser = new BarJSONParser();

		return barJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Bar bar) {
		if (bar == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (bar.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(bar.getDescription()));

			sb.append("\"");
		}

		if (bar.getFooId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fooId\": ");

			sb.append(bar.getFooId());
		}

		if (bar.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(bar.getId());
		}

		if (bar.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(bar.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		BarJSONParser barJSONParser = new BarJSONParser();

		return barJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Bar bar) {
		if (bar == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (bar.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put("description", String.valueOf(bar.getDescription()));
		}

		if (bar.getFooId() == null) {
			map.put("fooId", null);
		}
		else {
			map.put("fooId", String.valueOf(bar.getFooId()));
		}

		if (bar.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(bar.getId()));
		}

		if (bar.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(bar.getName()));
		}

		return map;
	}

	public static class BarJSONParser extends BaseJSONParser<Bar> {

		@Override
		protected Bar createDTO() {
			return new Bar();
		}

		@Override
		protected Bar[] createDTOArray(int size) {
			return new Bar[size];
		}

		@Override
		protected void setField(
			Bar bar, String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					bar.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fooId")) {
				if (jsonParserFieldValue != null) {
					bar.setFooId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					bar.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					bar.setName((String)jsonParserFieldValue);
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