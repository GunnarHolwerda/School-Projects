$(document).ready(function(){
	$('#left-item').click(function(){
		$('#title').text("Drinks");
		$('#menu').html("<b>There are so many drinks here.</b>");
	});

	$('#appetizers').click(function(){
		$('#title').text("Appetizers");
		$('#menu').html("<b>There are so many appetizers here.</b>");
	});

	$('#salads').click(function(){
		$('#title').text("Salads");
		$('#menu').html("<b>There are so many salads here.</b>");
	});

	$('#entrees').click(function(){
		$('#title').text("Entrees");
		$('#menu').html("<b>There are so many entrees here.</b>");
	});

	$('#right-item').click(function(){
		$('#title').text("Desserts");
		$('#menu').html("<b>There are so many desserts here.</b>");
	});
})