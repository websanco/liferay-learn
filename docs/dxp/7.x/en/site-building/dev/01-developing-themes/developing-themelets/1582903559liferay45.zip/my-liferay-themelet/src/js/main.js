AUI().ready(
	function() {
    if(Liferay.Browser.isMobile !== 'true'){
      alert("You're viewing this site on a desktop device.");
    }
    else if(Liferay.Browser.isMobile == 'true'){
      alert("You're viewing this site on a mobile device.");
    }
  }
);