$(function(){
	
	// Checking for CSS 3D transformation support
	$.support.css3d = supportsCSS3D();
	
	var w3ls_form = $('#w3ls_form');
	
	// Listening for clicks on the ribbon links
	$('.flipLink').click(function(e){
		
		// Flipping the forms
		w3ls_form.toggleClass('flipped');
		
		// If there is no CSS3 3D support, simply
		// hide the sign in form (exposing the signup one)
		if(!$.support.css3d){
			$('#signin').toggle();
		}
		e.preventDefault();
	});
	 
	
	// A helper function that checks for the 
	// support of the 3D CSS3 transformations.
	function supportsCSS3D() {
		var props = [
			'perspectiveProperty', 'WebkitPerspective', 'MozPerspective'
		], testDom = document.createElement('a');
		  
		for(var i=0; i<props.length; i++){
			if(props[i] in testDom.style){
				return true;
			}
		}
		
		return false;
	}
});
