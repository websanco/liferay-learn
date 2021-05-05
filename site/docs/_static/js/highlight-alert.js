/**
 * Adds an alert with the text being highlighted and provides a way to clear the
 * highlighted text.
 */

const HIGHLIGHT_ALERT_ID = 'highlightAlert';
const HIGHLIGHT_TEXT_MATCH_ID = 'highlightTextMatch';
const REMOVE_HIGHLIGHT_LINK_ID = 'removeHighlightLink';

const HIGHLIGHT_PARAM = 'highlight';

/**
 * Calls Sphinx utility to remove highlighted text.
 * https://github.com/sphinx-doc/sphinx/blob/9e1b4a8f1678e26670d34765e74edf3a3be3c62c/sphinx/themes/basic/static/doctools.js#L261-L267
 */
function hideSearchWords() {
	Documentation.hideSearchWords();
}

function initHighlightAlert() {
	const urlSearchParams = new URLSearchParams(window.location.search);

	if (urlSearchParams.has(HIGHLIGHT_PARAM)) {
		const highlightAlertElement = document.getElementById(
			HIGHLIGHT_ALERT_ID
		);

		if (highlightAlertElement) {
			// Add text being highlighted

			const textMatchElement = document.getElementById(
				HIGHLIGHT_TEXT_MATCH_ID
			);

			if (textMatchElement) {
				textMatchElement.textContent =
					' "' + urlSearchParams.get(HIGHLIGHT_PARAM) + '"';
			}

			// Setup remove highlight link to clear highlights and dismiss alert

			const removeHighlightLinkElement = document.getElementById(
				REMOVE_HIGHLIGHT_LINK_ID
			);

			if (removeHighlightLinkElement) {
				removeHighlightLinkElement.addEventListener(
					'click',
					function () {
						hideSearchWords();

						highlightAlertElement.remove();
					}
				);
			}

			// Show alert

			highlightAlertElement.classList.remove('alert-hidden');
		}
	}
}

// Initialize after DOM is ready

$(initHighlightAlert);
