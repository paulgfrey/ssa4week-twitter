function getPulicTweet() {

	ajaxHandler("Rest/userinfo/", function (jsonText) {

		//var jsonText = [{formUserID:'ABC',toUserID:'BCD',msg:'Test form ABC to BCD',date:'2016-08-05 12:00:01'},{formUserID:'ASD',toUserID:'ZXC',msg:'Test form ASD to ZXC',date:'2016-08-05 12:00:02'}];
		
		//var publictweetArr = JSON.parse(tweets);

		var publictweet = document.getElementById("publictweet");
		var htmlStr = "";
		for(var i in jsonText) {
			htmlStr += "<div class='tweetItem'>";
			htmlStr += "<div><span class='tweetUserID'>" + jsonText[i]["fromUserId"] + "</span><span class='tweetDate'>" + jsonText[i]["date"] + "</span></div>";
			htmlStr += "<div class='tweetMsg'>" + jsonText[i]["msg"] + "</div>";
			htmlStr += "</div>";
		}
		publictweet.innerHTML = htmlStr;
	});
}