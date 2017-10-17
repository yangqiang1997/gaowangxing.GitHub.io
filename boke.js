function login() {
	if (clickNum % 2 == 0) {
		$("#login").animate({
			left:'40%',
		    opacity:'0.8',
		    height:'250px',
		    width:'250px'
		},600);

		var temp = "";

		temp += "愿意留下大名吗? 有的名字才可访问内部网页哦...</br>"
		temp += "<button class='button b1'>好的</button><a href='boke1.html'><button class='button'>不要，滚！</button></a>";
		document.getElementById('login').innerHTML = temp;

	}
	else{
		$("#login").animate({
			left:'',
			height:'40px',
		    width:'40px',
		    opacity:'1'
		},1200);

		document.getElementById('login').innerHTML = " 进入";
	}
	clickNum++;
}
function show(){
	$("#show").show(1000);
}
function backShow(){
	$("#show").hide(1000);
}

