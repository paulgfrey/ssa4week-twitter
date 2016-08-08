
 
  ajaxHandler("/rest/tweets/" + GetCookie() + "/personal/", function (resp) {

  		

  		var tweetDiv =document.getElementById('tweets');
		var dataStr="";
           for (var x in resp){
                dataStr+='<p class="firstln">' + resp[x].fromUserId ;
                dataStr += '</p> <p>' + resp[x].msg + '</p><p class="thirdln">' + resp[x].date + '</p><br>';
                
            }
            tweetDiv.innerHTML=dataStr;


  });

//-----------------------------------------------------------------s
console.log('cookie:', document.cookie);
  var user=document.getElementById('userName');
  user.innerHTML=GetCookie(); 
