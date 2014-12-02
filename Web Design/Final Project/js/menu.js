$(document).ready(function(){
	//Click on drinks updates menu
	$('#left-item').click(function(){
		$('#title').text("Drinks");
		$('#menu').html("<div id=\"left-menu\" class=\"menu-box\">\n\t<h3>Wine</h3>\n<ul>\n\t<li>Merlot</li>\n\t<li>Cabernet</li>\n\t<li>Sauvignon Blanc</li>\n</ul>\n</div>\n<div id=\"right-menu\" class=\"menu-box\">\n\t<h3>Soda</h3>\n\t<ul>\n\t<li>Coca-Cola</li>\n\t	<li>Sprite</li>\n\t<li>Dr. Pepper</li>\n\t<li>Fanta</li>\n\t<li>Root Beer</li>\n</ul>\n</div>\n");
	});

	//Click on appetizers updates menu
	$('#appetizers').click(function(){
		$('#title').text("Appetizers");
		$('#menu').html("<div id=\"left-menu\" class=\"menu-box\">\n\t<h3>For Two</h3>\n<ul>\n\t<li>Mozzarella Sticks</li>\n\t<li>Calamari</li>\n\t<li>Cheese Platter</li>\n\t<li>Starter Pizza</li>\n</ul>\n</div>\n<div id=\"right-menu\" class=\"menu-box\">\n\t<h3>For Groups</h3>\n<ul>\n\t<li>Starter Salad</li>\n\t<li>Appetizer Spaghetti</li>\n\t<li>Grande Appetizer</li>\n\t<li>Sample Platter</li>\n</ul>\n</div>\n");
	});

	//Click on salads updates menu
	$('#salads').click(function(){
		$('#title').text("Salads");
		$('#menu').html("<div id=\"left-menu\" class=\"menu-box\">\n\t<h3>Salads</h3>\n<ul>\n\t<li>Caesar</li>\n\t<li>Antipasto</li>\n\t<li>Caprese</li>\n</ul>\n</div>\n<div id=\"right-menu\" class=\"menu-box\">\n\t<h3>Dressings</h3>\n<ul>\n\t<li>Ranch</li>\n\t<li>Italian</li>\n\t<li>Caesar</li>\n\t<li>Balsamic Vinaigrette</li>\n</ul>\n</div>\n");
	});

	//Click on entrees updates menu
	$('#entrees').click(function(){
		$('#title').text("Entrees");
		$('#menu').html("<div id=\"left-menu\" class=\"menu-box\">\n\t<h3>Pasta</h3>\n<ul>\n\t<li>Spaghetti</li>\n\t<li>Ravioli</li>\n\t<li>Linguine</li>\n</ul>\n</div>\n<div id=\"right-menu\" class=\"menu-box\">\n\t<h3>Pizza</h3>\n<ul>\n\t<li>Margherita</li>\n\t<li>Meatball Delight</li>\n\t<li>Vegan Extravaganza</li>\n\t<li>Bellion Bell Pepper</li>\n</ul>\n</div>\n");
	});

	//Click on desserts updatesmenu
	$('#right-item').click(function(){
		$('#title').text("Desserts");
		$('#menu').html("<div id=\"left-menu\" class=\"menu-box\">\n\t<h3>Cheesecakes</h3>\n<ul>\n\t<li>Peanut Butter Cheesecake</li>\n\t<li>Cheesy Cheesecake</li>\n\t<li>Raspberry Cheesecake</li>\n</ul>\n</div>\n<div id=\"right-menu\" class=\"menu-box\">\n\t<h3>Gellato</h3>\n<ul>\n\t<li>Chocolate</li>\n\t<li>Vanilla</li>\n\t<li>Strawberry Delight</li>\n\t<li>Pistachio</li>\n</ul>\n</div>\n");
	});
})