/**
 * Add a user to Music Melody
 * Check For client side validations
 */
console.log("Loading Javascript");
var login = true;
var signup = false;
 
  
var signed = document.querySelector('.signUp');
var logged = document.querySelector('#login');

var signinBox = document.querySelector('.signinBox');
var signupBox = document.querySelector('.signupBox');
  
function loginScreen() {
 	if(!login){ 
 		login = true;
 		signup = false;
 		logged.style.textDecoration = "underline";
 		signed.style.textDecoration = "";
 		signupBox.style.visibility = "hidden";
 		signinBox.style.visibility = "visible";
 	} 
}

function signUp(){
 
 	if(!signup){
 		signup = true;
 		login = false;
 		signed.style.textDecoration = "underline";
 		logged.style.textDecoration = "";
 		signinBox.style.visibility = "hidden";
 		signinBox.style.height = "0px";
 		
 		signupBox.style.visibility = "visible";
 	}
}
 
 
function validateLogin(){
 	var email = document.forms["Signin"]["email"].value;
 	var pass = document.forms["Signin"]["pwd"].value;
	
  	if (email == ""){
    	alert("Email must be filled out");
    	return false;
  	}else if(pass == ""){
	    alert("Password must be filled out");
    	return false;
	}
}


function validateSignUp(){
 	var email = document.forms["Signup"]["newEmail"].value;
 	var fname = document.forms["Signup"]["fname"].value;
 	var lname = document.forms["Signup"]["lname"].value;
 	var pass = document.forms["Signup"]["newPassword"].value;

  	if (email == ""){
    	alert("Name must be filled out");
    	return false;
  	}else if(fname == ""){
	    alert("First Name must be filled out");
    return false;

	}else if(lname==""){
		alert("Last Name must be filled out");
    	return false;
	}else if(pass = ""){
		alert("Password must be filled out");
    	return false;
	}
}

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
  }