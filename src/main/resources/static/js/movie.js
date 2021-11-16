var movie = {
	exec: function() {
		$("#btn-movie-search").on("click", ()=> {
			this.search();
		});
	},
	
	search: function() {		
		var query = $("#query").val();
		
		$.ajax({
			url: "/recmv/api/movie/searchMovie",
			type: "GET",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {query: query}	
		}).done(function(resp){
			var ret = resp[0];
			console.log(ret);
			var loc = "./search?title=" + ret.title;
			location.replace(loc);			
		}).fail(function(err){
			alert(JSON.stringify(err));
		});				
	},
};

movie.exec();