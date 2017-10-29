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


function boke1_showContent(){
	$("#boke1_content").slideToggle("slow");
	$("#boke1_promot").slideToggle("slow");
}
function boke1_firstDo(){
	document.getElementById("boke1_content_1").innerHTML = "<button class='boke1_button1' onclick='boke1_showForm()'>想喷你个煞笔！</button>"
						+"<button class='boke1_button1' onclick='say()'>你该不会是个傻子吧？</button>";
}
function boke1_showForm(){
	$('#form').show(2000);
}
function boke1_hideForm(){
	$('#form').hide(2000);
}




function boke1_summary() {
	if (clickNum % 2 == 0) {
		$("#boke1_summary").animate({
			left:'25%',
		    opacity:'0.8',
		    height:'220px',
		    width:'40%',
		    paddingLeft:'8%',
		    paddingTop:'2%',
		},600);

		var temp = "";

		temp += "<a href='summary_1.html'>于微标科技观北大教授演讲总结</a><br/>";
		temp += "<a href='summary_2.html'>观金一南在华为演讲演讲总结</a><br/>";
		temp += "<a href='summary_3.html'>夏令营入门思想引导总结-2017</a><br/>";
		temp += "<a href='summary_4.html'>关于代码-代码规范</a><br/>";
		temp += "<a href='summary_5.html'>2017-夏令营培训-第一阶段&最后阶段-总结</a><br/>";

		document.getElementById('boke1_summary').innerHTML = temp;

	}
	else{
		$("#boke1_summary").animate({
			left:'',
			height:'120px',
		    width:'20px',
		    opacity:'1',
		    padding:'5px'
		},1200);

		document.getElementById('boke1_summary').innerHTML = "个人总结";
	}
	clickNum++;
}
