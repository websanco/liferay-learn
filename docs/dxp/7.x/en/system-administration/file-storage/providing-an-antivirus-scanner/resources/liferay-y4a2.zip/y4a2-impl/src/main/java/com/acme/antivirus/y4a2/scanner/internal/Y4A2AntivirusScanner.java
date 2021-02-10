package com.acme.antivirus.y4a2.scanner.internal;

import com.liferay.document.library.kernel.antivirus.AntivirusScanner;
import com.liferay.document.library.kernel.antivirus.AntivirusScannerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.File;

import org.osgi.service.component.annotations.Component;

@Component(service = AntivirusScanner.class)
public class Y4A2AntivirusScanner implements AntivirusScanner {

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public void scan(byte[] bytes) throws AntivirusScannerException {
		if (_log.isWarnEnabled()) {
			_log.warn("Y4A2AntivirusScanner's scan(byte[]) method was invoked");
		}
	}

	@Override
	public void scan(File file) throws AntivirusScannerException {
		if (_log.isWarnEnabled()) {
			_log.warn("Y4A2AntivirusScanner's scan(File) method was invoked");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		Y4A2AntivirusScanner.class);

}