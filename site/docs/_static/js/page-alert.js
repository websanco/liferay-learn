/**
 * Manages the page alert by showing the alert unless it was already closed in
 * a previous session.
 */

const ALERT_ELEMENT_ID = 'pageAlert';

const ALERT_ELEMENT_HEIGHT = 56;

const ALERT_LOCAL_STORAGE_ID = 'pageAlertState';

function initPageAlert() {
	const pageAlertState = localStorage.getItem(ALERT_LOCAL_STORAGE_ID);

	if (pageAlertState !== 'closed') {
		const pageAlertElement = document.getElementById(ALERT_ELEMENT_ID);

		if (pageAlertElement) {
			pageAlertElement.classList.remove('page-alert-hidden');

			document.body.classList.add('page-alert-open');

			const pageAlertContainer = pageAlertElement.parentNode;

			if (pageAlertContainer) {
				window.addEventListener('scroll', () =>
					setTimeout(function () {
						if (window.scrollY <= ALERT_ELEMENT_HEIGHT) {
							pageAlertContainer.classList.remove('d-none');
						}
						else {
							if (
								!pageAlertContainer.classList.contains('d-none')
							) {
								pageAlertContainer.classList.add('d-none');
							}
						}
					}, 300)
				);
			}
		}

		$('#' + ALERT_ELEMENT_ID).on('closed.bs.alert', function () {
			localStorage.setItem(ALERT_LOCAL_STORAGE_ID, 'closed');

			document.body.classList.remove('page-alert-open');
		});
	}
}

// Initialize after DOM is ready

$(initPageAlert);
