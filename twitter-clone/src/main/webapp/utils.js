function ajaxHandler(url, fn){ 

	var xhttp;
    xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var responseData = JSON.parse(xhttp.responseText);
         

         // responseData =[{"fromUserId":"Chedva","msg":"testing 123","date":"1/1/2016"},{"fromUserId":"Chedva","msg":"testing 456","date":"1/2/2016"}]
           fn(responseData);
           }
   };
    xhttp.open("GET", url, true);
   xhttp.send();


}