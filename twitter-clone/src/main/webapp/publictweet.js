function getPulicTweet() {

	ajaxHandler("Rest/tweets/", function (tweets) {

		var imagesURL = ["http://ichef.bbci.co.uk/news/624/cpsprodpb/10806/production/_90709576_034421612-1.jpg",
						"http://ichef.bbci.co.uk/news/624/cpsprodpb/AF65/production/_90710944_034464971-1.jpg",
						"https://static01.nyt.com/images/2016/08/08/us/08whletter/08whletter-articleLarge.jpg"];
		var publictweet = document.getElementById("publictweet");
		var htmlStr = "";
		for(var i = 0; i < tweets.length; i++) {
			htmlStr += "<div class='tweetItem'>";
			htmlStr += "<div class='floatDiv'><a href='personal.html'><img src='avater/" + tweets[i]["fromUserId"] + ".jpg' width='50'></a></div>";
			htmlStr += "<div class='floatDiv'><div><a href='personal.html'><span class='tweetUserID'>" + tweets[i]["fromUserId"] + "</span></a><span class='tweetDate'>" + tweets[i]["date"] + "</span></div>";
			htmlStr += "<div class='tweetMsg'>" + tweets[i]["msg"] + "</div>";
			htmlStr += "<div><img src='" + imagesURL[i] + "' width='500'></div></div>";
			htmlStr += "</div>";
		}
		publictweet.innerHTML = htmlStr;
		
	});
}