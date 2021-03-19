package com.acme.r3b2.foo.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Liferay
 * @generated
 */
@Generated("")
@GraphQLName("Foo")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Foo")
public class Foo implements Serializable {

	public static Foo toDTO(String json) {
		return ObjectMapperUtil.readValue(Foo.class, json);
	}

	@Schema(description = "A field called bar, because this has no meaning.")
	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	@JsonIgnore
	public void setBar(UnsafeSupplier<String, Exception> barUnsafeSupplier) {
		try {
			bar = barUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "A field called bar, because this has no meaning."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String bar;

	@Schema(description = "A field called baz to go with bar.")
	public String getBaz() {
		return baz;
	}

	public void setBaz(String baz) {
		this.baz = baz;
	}

	@JsonIgnore
	public void setBaz(UnsafeSupplier<String, Exception> bazUnsafeSupplier) {
		try {
			baz = bazUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "A field called baz to go with bar.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String baz;

	@Schema(description = "The Foo's ID")
	public Integer getFooId() {
		return fooId;
	}

	public void setFooId(Integer fooId) {
		this.fooId = fooId;
	}

	@JsonIgnore
	public void setFooId(
		UnsafeSupplier<Integer, Exception> fooIdUnsafeSupplier) {

		try {
			fooId = fooIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The Foo's ID")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer fooId;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Foo)) {
			return false;
		}

		Foo foo = (Foo)object;

		return Objects.equals(toString(), foo.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (bar != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"bar\": ");

			sb.append("\"");

			sb.append(_escape(bar));

			sb.append("\"");
		}

		if (baz != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"baz\": ");

			sb.append("\"");

			sb.append(_escape(baz));

			sb.append("\"");
		}

		if (fooId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fooId\": ");

			sb.append(fooId);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		defaultValue = "com.acme.r3b2.foo.dto.v1_0.Foo", name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
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

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(value);
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}