package com.acme.headless.r3b2.client.dto.v1_0;

import com.acme.headless.r3b2.client.function.UnsafeSupplier;
import com.acme.headless.r3b2.client.serdes.v1_0.BarSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Jonah the son of Amittai
 * @generated
 */
@Generated("")
public class Bar implements Cloneable, Serializable {

	public static Bar toDTO(String json) {
		return BarSerDes.toDTO(json);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String description;

	public Integer getFooId() {
		return fooId;
	}

	public void setFooId(Integer fooId) {
		this.fooId = fooId;
	}

	public void setFooId(
		UnsafeSupplier<Integer, Exception> fooIdUnsafeSupplier) {

		try {
			fooId = fooIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer fooId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Integer, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String name;

	@Override
	public Bar clone() throws CloneNotSupportedException {
		return (Bar)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Bar)) {
			return false;
		}

		Bar bar = (Bar)object;

		return Objects.equals(toString(), bar.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return BarSerDes.toJSON(this);
	}

}