$(document).ready(function() {

    $( document ).ajaxSend(function( event, jqxhr, settings ) {
        $('#dimmed').removeClass( 'un-dimmed' );
        $('#dimmed').addClass( 'dimmed' );
    });

    $( document ).ajaxComplete(function( event, xhr, settings ) {
        $('#dimmed').addClass( 'un-dimmed' );
        $('#dimmed').removeClass( 'dimmed' );
    });

    $.ajaxSetup({
        headers: { "SIGNUP_STEP_UUID": getCookie('SIGNUP_STEP_UUID') }
    });

    function getCookie(cname) {
        let name = cname + "=";
        let ca = document.cookie.split(';');
        for(let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) === 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }
});