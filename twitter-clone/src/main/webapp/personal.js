
 var userName = "";

 //-----------------------------------------------------

  ajaxHandler("/rest/tweets/userId/personal/", function (resp) {

  		

  		var tweetDiv =document.getElementById('tweets');
		var dataStr="";
           for (var x in resp){
                dataStr+='<p class="firstln">' + resp[x].fromUserId ;
                dataStr += '</p> <p>' + resp[x].msg + '</p><p class="thirdln">' + resp[x].date + '</p><br>';
                

                if (userName===""){
                	userName=resp[x].fromUserId;
                }
                

                
            }
            tweetDiv.innerHTML=dataStr;


  });

//-----------------------------------------------------------------

  var user=document.getElementById('userName');
  user.innerHTML=userName;