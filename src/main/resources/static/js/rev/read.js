$(document).ready(function() {
	var form = $("form");
	var id = $("#id").val();
	var userId = $("#userId").val();
	
	$("#upRev").click(function(){
		if(id !== userId){
			alert("수정권한이 없습니다.");
			return false;	
		}
	
		form.attr("action", "./update");
		form.attr("method", "get");
		form.submit();
	});
	
	$("#deRev").click(function(){
		if(id !== userId){
			alert("삭제권한이 없습니다.");
			return false;	
		}
			
		form.attr("action", "./delete");
		form.attr("method", "get");
		form.submit();
	});
		
});