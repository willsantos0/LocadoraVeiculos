$(function() {
	$('.js-toggle').bind('click', function(event) {
		$('.js-sidebar, .js-content').toggleClass('is-toggled');
		event.preventDefault();
	});	
	
    $(".up").click(function (){
        $('html, body').animate({
            scrollTop: $(".aw-content").offset().top
        }, 500);
    });
	
	
});
