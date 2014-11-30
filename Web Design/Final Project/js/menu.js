$(document).ready(function(){
	//Click on drinks updates menu
	$('#left-item').click(function(){
		$('#title').text("Drinks");
		$('#menu').html("<div id=\"left-menu\" class=\"menu-box\">\n\t<h3>Wine</h3>\n<ul>\n\t<li>Red</li>\n\t<li>White</li>\n\t<li>Pink</li>\n</ul>\n</div>\n<div id=\"right-menu\" class=\"menu-box\">\n\t<h3>Soda</h3>\n\t<ul>\n\t<li>Coca-Cola</li>\n\t	<li>Sprite</li>\n\t<li>Dr. Pepper</li>\n\t<li>Fanta</li>\n\t<li>Root Beer</li>\n</ul>\n</div>\n");
	});

	//Click on appetizers updates menu
	$('#appetizers').click(function(){
		$('#title').text("Appetizers");
		$('#menu').html("There are so many appetizers here.");
	});

	//Click on salads updates menu
	$('#salads').click(function(){
		$('#title').text("Salads");
		$('#menu').html("There are so many salads here.");
	});

	//Click on entrees updates menu
	$('#entrees').click(function(){
		$('#title').text("Entrees");
		$('#menu').html("There are so many entrees here.");
	});

	//Click on desserts updatesmenu
	$('#right-item').click(function(){
		$('#title').text("Desserts");
		$('#menu').html("There are so many desserts here.");
	});
})