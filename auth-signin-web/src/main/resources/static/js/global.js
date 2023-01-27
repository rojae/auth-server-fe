$(document).ready(function() {

    $( document ).ajaxSend(function( event, jqxhr, settings ) {
        $('#dimmed').removeClass( 'un-dimmed' );
        $('#dimmed').addClass( 'dimmed' );
    });

    $( document ).ajaxComplete(function( event, xhr, settings ) {
        $('#dimmed').addClass( 'un-dimmed' );
        $('#dimmed').removeClass( 'dimmed' );
    });

});