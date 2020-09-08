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

package com.acme.r2f1.internal.dynamic.data.mapping.storage;

import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapter;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterDeleteRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterDeleteResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterGetRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterGetResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveResponse;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Russell Bohl
 */
@Component(
	immediate = true,
	property = "ddm.storage.adapter.type=r2f1-ddm-storage-adapter",
	service = DDMStorageAdapter.class
)
public class R2F1DDMStorageAdapter implements DDMStorageAdapter {

	@Override
	public DDMStorageAdapterDeleteResponse delete(
			DDMStorageAdapterDeleteRequest ddmStorageAdapterDeleteRequest)
		throws StorageException {

		try {
			if (_log.isWarnEnabled()) {
				_log.warn("Acme storage adapter's delete method was invoked");
			}

			return _jsonStorageAdapter.delete(ddmStorageAdapterDeleteRequest);
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	@Override
	public DDMStorageAdapterGetResponse get(
			DDMStorageAdapterGetRequest ddmStorageAdapterGetRequest)
		throws StorageException {

		try {
			if (_log.isWarnEnabled()) {
				_log.warn("Acme storage adapter's get method was invoked");
			}

			return _jsonStorageAdapter.get(ddmStorageAdapterGetRequest);
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	@Override
	public DDMStorageAdapterSaveResponse save(
			DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
		throws StorageException {

		try {
			if (_log.isWarnEnabled()) {
				_log.warn("Acme storage adapter's save method was invoked");
			}

			return _jsonStorageAdapter.save(ddmStorageAdapterSaveRequest);
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		R2F1DDMStorageAdapter.class);

	@Reference(target = "(ddm.storage.adapter.type=json)")
	private DDMStorageAdapter _jsonStorageAdapter;

}