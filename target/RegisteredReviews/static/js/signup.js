$('document').ready(function(){

	$('#SignupForm').onSubmit(function(){
		
		//validate password
		var passRegex = '(?=^.{8,30}$)(?=(.*\\d){2})(?=(.*[A-Za-z]){2})(?=.*[!@#$%^&*?])(?!.*[\\s])^.*';
		if($('#password1').length > 2 && $('#password1').val()==$('#password2').val() && $('#password1').val().Match(passRegex)){
			return true;
		}return false;
	});

});