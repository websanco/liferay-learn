package com.acme.n4g6.internal.model.listener;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;

@Component(service = ModelListener.class)
public class N4G6ModelListener extends BaseModelListener<JournalArticle> {

	public void onAfterCreate(JournalArticle journalArticle)
		throws ModelListenerException {

		if (_log.isInfoEnabled()) {
			_log.info("Added journal article " + journalArticle.getId());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		N4G6ModelListener.class);

}