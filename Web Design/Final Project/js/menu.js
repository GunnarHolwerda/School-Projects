$(document).ready(function(){
	//Click on drinks updates menu
	$('#left-item').click(function(){
		$('#title').text("Drinks");
		$('#menu').html("<b>There are so many drinks here.</b>");
	});

	//Click on appetizers updates menu
	$('#appetizers').click(function(){
		$('#title').text("Appetizers");
		$('#menu').html("<b>There are so many appetizers here.</b>");
	});

	//Click on salads updates menu
	$('#salads').click(function(){
		$('#title').text("Salads");
		$('#menu').html("<b>There are so many salads here.</b>");
	});

	//Click on entrees updates menu
	$('#entrees').click(function(){
		$('#title').text("Entrees");
		$('#menu').html("<b>There are so many entrees here.</b>");
	});

	//Click on desserts updatesmenu
	$('#right-item').click(function(){
		$('#title').text("Desserts");
		$('#menu').html("<b>There are so many desserts here.</b>");
	});
})