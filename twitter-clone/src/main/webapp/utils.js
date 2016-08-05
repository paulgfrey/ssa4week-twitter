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
  document.cookie ="userId=" + userId +";";
}

//----------------------------------------------------

function GetCookie(){

//console.log(document.cookie);
  var useName;
  var arr;
  arr=document.cookie.split("=");
  userName=arr[1];

  return userName;
}

