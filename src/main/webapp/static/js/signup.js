/**
 * Сценарій валідації даних у формі реєстрації користувачів
 */

 $(document).ready(()=>{
	
	// 0.
	//---
	let validate1 = false; // - login
	let validate2 = false; // - password
	let validate3 = false; // - password2
	let validate4 = true; // - email
	
	// 1.
	//---
	$("#login").blur(()=>{
		let loginX = $("#login").val();
		let loginReg = /^[a-zA-Z][a-zA-Z0-9_\-]{5,15}$/;
		if (loginReg.test(loginX)) {
			// ->
			$.ajax({
				url: 'Auth?page=ajax_signup',
				data: "login="+loginX,
				success: (response) => {
					console.log("ajax OK!");
					console.log(response);
					if (response === "YES") {
						validate1 = true;
						$("#login_error").text('');
					} else {
						validate1 = false;
						$("#login_error").text('Login is already taken!');
					}
				}
			});
		} else {
			$("#login_error").text('Login does not match the requirments!');
		}
	});
	
	// 2.
	//---
	$("#password").blur(()=>{
		let passwordX = $("#password").val();
		let passwordReg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9_\-]{8,}$/;
		if (passwordReg.test(passwordX)) {
			validate2 = true;
			$("#password_error").text('');
		} else {
			validate2 = false;
			$("#password_error").text('Password does not match the requirments!');
		}
	});
	// 3.
	//---
	$("#password2").blur(()=>{
		let passwordX = $("#password").val();
		let passwordY = $("#password2").val();
		if (passwordX === passwordY) {
			validate3 = true;
			$("#password2_error").text('');
		} else {
			validate3 = false;
			$("#password2_error").text('Password does not match!');
		}
	});
	
	// 4.
	//---
	
	
	// 5.
	//---
	$("#_submit").click(()=>{
		if (validate1 && validate2 && validate3 && validate4) {
			console.log("validation TRUE");
			$("#signupForm").attr('onSubmit', "return true");
		} else {
			console.log("validation FALSE");
			$("#signupForm").attr('onSubmit', "return false");
			alert("Incorrect data!\nUnable to continue!");
		}
	});
	
 });