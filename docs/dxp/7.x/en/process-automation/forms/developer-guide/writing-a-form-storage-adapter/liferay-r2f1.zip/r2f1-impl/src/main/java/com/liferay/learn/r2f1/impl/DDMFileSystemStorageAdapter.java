/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.learn.r2f1.impl;

import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializerDeserializeResponse;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializerTracker;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeResponse;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerTracker;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapter;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterDeleteRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterDeleteResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterGetRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterGetResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveResponse;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;
import java.io.IOException;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Russell Bohl
 */
@Component(
	immediate = true, property = "ddm.storage.adapter.type=file-system",
	service = DDMStorageAdapter.class
)
public class DDMFileSystemStorageAdapter implements DDMStorageAdapter {

	@Override
	public DDMStorageAdapterDeleteResponse delete(
			DDMStorageAdapterDeleteRequest ddmStorageAdapterDeleteRequest)
		throws StorageException {

		long fileId = ddmStorageAdapterDeleteRequest.getPrimaryKey();

		deleteFile(fileId);

		try {
			ddmContentLocalService.deleteDDMContent(
				ddmStorageAdapterDeleteRequest.getPrimaryKey());

			DDMStorageAdapterDeleteResponse.Builder builder =
				DDMStorageAdapterDeleteResponse.Builder.newBuilder();

			return builder.build();
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	public DDMStorageAdapterGetResponse get(
			DDMStorageAdapterGetRequest ddmStorageAdapterGetRequest)
		throws StorageException {

		try {
			long fileId = ddmStorageAdapterGetRequest.getPrimaryKey();

			getFile(fileId);

			DDMContent ddmContent = ddmContentLocalService.getContent(
				ddmStorageAdapterGetRequest.getPrimaryKey());

			DDMFormValues ddmFormValues = deserialize(
				ddmContent.getData(), ddmStorageAdapterGetRequest.getDDMForm());

			DDMStorageAdapterGetResponse.Builder builder =
				DDMStorageAdapterGetResponse.Builder.newBuilder(ddmFormValues);

			return builder.build();
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	@Override
	public DDMStorageAdapterSaveResponse save(
			DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
		throws StorageException {

		try {
			if (ddmStorageAdapterSaveRequest.isInsert()) {
				return insert(ddmStorageAdapterSaveRequest);
			}
		}
		catch (Exception e) {
		}

		return update(ddmStorageAdapterSaveRequest);
	}

	private void deleteFile(long fileId) {
		File file = new File(_PATHNAME + "/" + fileId);

		file.delete();
	}

	private DDMFormValues deserialize(String content, DDMForm ddmForm) {
		DDMFormValuesDeserializer ddmFormValuesDeserializer =
			ddmFormValuesDeserializerTracker.getDDMFormValuesDeserializer(
				"json");

		DDMFormValuesDeserializerDeserializeRequest.Builder builder =
			DDMFormValuesDeserializerDeserializeRequest.Builder.newBuilder(
				content, ddmForm);

		DDMFormValuesDeserializerDeserializeResponse
			ddmFormValuesDeserializerDeserializeResponse =
				ddmFormValuesDeserializer.deserialize(builder.build());

		return ddmFormValuesDeserializerDeserializeResponse.getDDMFormValues();
	}

	private void getFile(long fileId) throws IOException {
		try {
			System.out.println(
				"Reading the file named:" + fileId + "\n" +
					"The file contents: " +
						FileUtil.read(_PATHNAME + "/" + fileId));
		}
		catch (IOException e) {
			throw new IOException(e);
		}
	}

	private DDMStorageAdapterSaveResponse insert(
			DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
		throws PortalException, StorageException {

		DDMFormValues ddmFormValues =
			ddmStorageAdapterSaveRequest.getDDMFormValues();

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setScopeGroupId(
				ddmStorageAdapterSaveRequest.getScopeGroupId());
			serviceContext.setUserId(ddmStorageAdapterSaveRequest.getUserId());
			serviceContext.setUuid(ddmStorageAdapterSaveRequest.getUuid());

			DDMContent ddmContent = ddmContentLocalService.addContent(
				ddmStorageAdapterSaveRequest.getUserId(),
				ddmStorageAdapterSaveRequest.getScopeGroupId(),
				ddmStorageAdapterSaveRequest.getClassName(), null,
				serialize(ddmFormValues), serviceContext);

			DDMStorageAdapterSaveResponse.Builder builder =
				DDMStorageAdapterSaveResponse.Builder.newBuilder(
					ddmContent.getPrimaryKey());

			DDMStorageAdapterSaveResponse ddmStorageAdapterSaveResponse =
				builder.build();


			long fileId = ddmStorageAdapterSaveResponse.getPrimaryKey();

			saveFile(fileId, ddmFormValues);

			return ddmStorageAdapterSaveResponse;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	private void saveFile(long fileId, DDMFormValues formValues)
		throws IOException {

		try {
			String serializedDDMFormValues = serialize(formValues);

			File abstractFile = new File(String.valueOf(fileId));

			FileUtil.write(
				_PATHNAME, abstractFile.getName(), serializedDDMFormValues);
		}
		catch (IOException e) {
			throw new IOException(e);
		}
	}

	private String serialize(DDMFormValues ddmFormValues) {
		DDMFormValuesSerializer ddmFormValuesSerializer =
			ddmFormValuesSerializerTracker.getDDMFormValuesSerializer("json");

		DDMFormValuesSerializerSerializeRequest.Builder builder =
			DDMFormValuesSerializerSerializeRequest.Builder.newBuilder(
				ddmFormValues);

		DDMFormValuesSerializerSerializeResponse
			ddmFormValuesSerializerSerializeResponse =
				ddmFormValuesSerializer.serialize(builder.build());

		return ddmFormValuesSerializerSerializeResponse.getContent();
	}

	private DDMStorageAdapterSaveResponse update(
			DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
		throws StorageException {

		DDMFormValues ddmFormValues =
			ddmStorageAdapterSaveRequest.getDDMFormValues();

		try {
			DDMContent ddmContent = ddmContentLocalService.getContent(
				ddmStorageAdapterSaveRequest.getPrimaryKey());

			ddmContent.setModifiedDate(new Date());

			ddmContent.setData(
				serialize(ddmStorageAdapterSaveRequest.getDDMFormValues()));

			ddmContentLocalService.updateContent(
				ddmContent.getPrimaryKey(), ddmContent.getName(),
				ddmContent.getDescription(), ddmContent.getData(),
				new ServiceContext());

			DDMStorageAdapterSaveResponse.Builder builder =
				DDMStorageAdapterSaveResponse.Builder.newBuilder(
					ddmContent.getPrimaryKey());

			DDMStorageAdapterSaveResponse ddmStorageAdapterSaveResponse =
				builder.build();

			long fileId = ddmStorageAdapterSaveResponse.getPrimaryKey();

			saveFile(fileId, ddmFormValues);

			return ddmStorageAdapterSaveResponse;
		}
		catch (Exception e) {
			throw new StorageException(e);
		}
	}

	private static final String _PATHNAME = "/opt/liferay/form-records";

	@Reference
	private DDMContentLocalService ddmContentLocalService;

	@Reference
	private DDMFormValuesDeserializerTracker ddmFormValuesDeserializerTracker;

	@Reference
	private DDMFormValuesSerializerTracker ddmFormValuesSerializerTracker;

}