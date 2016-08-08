// alert (GetCookie());
// if (GetCookie()===  ""){
//   //no user so send to sign on page
//   window.location="signon.html";
// }

function ajaxHandler(url, fn){ 

	var xhttp;
    xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var responseData = JSON.parse(xhttp.responseText);
               fn(responseData);
           }
   };
   
 
    xhttp.open("GET", url, true);
    xhttp.send();
  

}


//---------------------------------------------------

function SetCookie(userId){

  document.cookie = "userid=" + userId + ";"; 
}

//----------------------------------------------------

function GetCookie(){
    return document.cookie.split(";")[0].split("=")[1];
}





