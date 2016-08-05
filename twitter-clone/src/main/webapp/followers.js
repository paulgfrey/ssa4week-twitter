function getFollowers() {
	var jsonText = [{userID:'ABC'},{userID:'QWE'},{userID:'DFG'},{userID:'zxcvb'},{userID:'r64saa'}];
	
	//var publictweetArr = JSON.parse(jsonText);

	var publictweet = document.getElementById("followerlist");
	var htmlStr = "";
	for(var i in jsonText) {
		htmlStr += "<div><span class='tweetUserID'>" + jsonText[i]["userID"] + "</span>" + "</div>";
	}
	publictweet.innerHTML = htmlStr;
	
}