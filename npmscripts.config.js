const CHECK_AND_FIX_GLOBS = ['/site/**/*.scss', '/site/**/*.js'];

module.exports = {
	check: CHECK_AND_FIX_GLOBS,
	fix: CHECK_AND_FIX_GLOBS,
	preset: '@liferay/npm-scripts/src/presets/standard',
};
