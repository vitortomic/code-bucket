/* basic example
window.addEventListener('storage', storage_handler, false);
function storage_handler(evt)
{
    alert('The modified key was '+evt.key);
    alert('The original value was '+evt.oldValue);
    alert('The new value is '+evt.newValue);
    alert('The URL of the page that made the change was '+evt.url);
    alert('The window where the change was made was '+evt.source);
}
*/

/**
   Logout all other tabs on login
**/

//on application load
window.addEventListener('storage', storage_handler, false);
function storage_handler(event)
{	
	if(event.key == "logout_event"){
		authService.logout();
	}
}

//on login, using new date so the value is always changed and logout is performed
localStorage.setItem("logout_event", new Date());
