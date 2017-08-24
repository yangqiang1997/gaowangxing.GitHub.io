/**
* student的构造函数
**/
function student(studentID,name,college,major,grade,classes,age)
{
	this.studentID = studentID;
	this.name = name;
	this.college = college;
	this.major = major;
	this.grade = grade;
	this.classes = classes;
	this.age = age;
	this.remove = false;

	allStudent++;
	this.serial = allStudent;
}

function test(Test){
		alert("哟。。。。美女" + Test);
}


/**
*响应界面勾选框的函数
**/
function checkAll(){

	var index = ten + 10;

	if(document.getElementById("check").checked == true){
		for (var i = ten ; i < index && i < allStudent ; i++) {
				students[i].remove = true;
		}
		for (var i = 0 ; i<allStudent-ten  && i<10; i++) {
			document.getElementById(ids[i]).checked = true;
		}
	}
	else{
		for (var i = ten ; i < index && i < allStudent ; i++) {
				students[i].remove = false;
		}
		for (var i = 0 ; i<allStudent-ten && i<10 ; i++) {
			document.getElementById(ids[i]).checked = false;
		}
	}
	
	
}

function setCheckbox(index){
		if(students[index + ten].remove == false)
			students[index + ten].remove = true;
		else
			students[index + ten].remove = false;
}



/**
*响应查看界面的函数
**/

function see(index){
		$(document).ready(function(){
   			$("#see").show(1000);
		});
		document.getElementById("see2").value = students[index + ten].studentID;
		document.getElementById("see3").value = students[index + ten].name;
		document.getElementById("see4").value = students[index + ten].college;
		document.getElementById("see5").value = students[index + ten].major;
		document.getElementById("see6").value = students[index + ten].grade;
		document.getElementById("see7").value = students[index + ten].classes;
		document.getElementById("see8").value = students[index + ten].age;
}
function backFromSee(){
		$(document).ready(function(){
   			$("#see").hide(1000);
		});
}



/**
*响应修改界面的函数
**/

function modify(index){
		$(document).ready(function(){
   			$("#modify").show(1000);
		});
		document.getElementById("modify2").value = students[index + ten].studentID;
		document.getElementById("modify3").value = students[index + ten].name;
		document.getElementById("modify4").value = students[index + ten].college;
		document.getElementById("modify5").value = students[index + ten].major;
		document.getElementById("modify6").value = students[index + ten].grade;
		document.getElementById("modify7").value = students[index + ten].classes;
		document.getElementById("modify8").value = students[index + ten].age;

		backModifyIndex = index + ten;
}
function backFromModify(){
		$(document).ready(function(){
   			$("#modify").hide(1000);
		});
}

function sureFromModify(){

	if(testModify() == null){
		var index = backModifyIndex;
		var studentID = document.getElementById("modify2").value;
		var name = document.getElementById("modify3").value;
		var college = document.getElementById("modify4").value;
		var major = document.getElementById("modify5").value;
		var grade = document.getElementById("modify6").value;
		var classes = document.getElementById("modify7").value;
		var age = document.getElementById("modify8").value;

		students[index].studentID = studentID;
		students[index].name = name;
		students[index].college = college;
		students[index].major = major;
		students[index].grade = grade;
		students[index].classes = classes;
		students[index].age = age;

	
		$(document).ready(function(){
   			$("#modify").hide(1000);
		});

		ten = 0;
		Rwrite();

	}
	else
		alert(testModify());
}

/**
*修改界面数据校验
**/

function testModify(){
	if(document.getElementById("modify2").value.length == 0)
		return "学号不能为空哦";
	if(document.getElementById("modify2").value.length > 11)
		return "学号不能超过11位哦";
	if(document.getElementById("modify2").value.length < 8)
		return "学号都小于八位了";
	if(Repetition(document.getElementById("modify2").value) == false)
		return "你怎么能跟别人有一样的学号呢";
	if(isNaN(document.getElementById("modify2").value) == true)
		return "哇！学号格式有点问题哦";

	if(document.getElementById("modify3").value.length == 0)
		return "名字怎么能不写呢";
	if(document.getElementById("modify3").value.length > 4)
		return "名字有点长";

	if(document.getElementById("modify4").value.length == 0)
		return "您是那个学院的";
	if(document.getElementById("modify4").value.length > 11)
		return "学院名好像有点长";

	if(document.getElementById("modify5").value.length == 0)
		return "专业都不写吗";
	if(document.getElementById("modify5").value.length > 10)
		return "没这个专业吧";

	if(document.getElementById("modify6").value.length == 0)
		return "您是那一级的";
	if(document.getElementById("modify6").value.length > 4)
		return "这级数有点大啊";

	if(document.getElementById("modify7").value.length == 0)
		return "唉！班级还没写";
	if(document.getElementById("modify7").value.length > 2)
		return "这么长的班号吗";
	if(isNaN(document.getElementById("modify7").value) == true)
		return "哇！你的班号居然不是数字";

	if(document.getElementById("modify8").value.length == 0)
		return "请问芳龄";
	if(document.getElementById("modify8").value.length > 3)
		return "我看你很年轻，没这么大的年纪吧";
	if(isNaN(document.getElementById("modify8").value) == true)
		return "哇！你的班号居然不是数字";

	return null;
}

/**
*检验修改学号是否重复
**/
function Repetition(str){

	for(var i = 0 ; i < allStudent ; i++){
		if(students[i].studentID == str && i != backModifyIndex)
				return false;
	}
	return true;
}


/**
*检验新增学号是否重复
**/
function testRepetition(str){
	for(var i = 0 ; i < allStudent ; i++){
		if(students[i].studentID == str)
				return false;
	}
	return true;
}



/**
*响应删除界面的函数
**/


function deleteLine(){

	if(confirm("真的要删除吗？") == true){
	var n = allStudent;
	var NO = 0;
	allStudent = 0;
	
	var str = new String;
	var tem = 0;

	for(var i = 0 ; i < n ; i++){
		if(students[i].remove == false){
			students[NO] = new student(students[i].studentID , students[i].name , students[i].college , students[i].major , students[i].grade , students[i].classes , students[i].age);
			NO++;
		}
	}

	ten = 0 ;
	Rwrite();
   }
}



/**
*响应增加界面的函数
**/

function addLine(){

	$(document).ready(function(){
   		$("#add").show(1000);
	});
}

function backFromAdd(){
	$(document).ready(function(){
   		$("#add").hide(1000);
	});
	recoverAdd();
}

function sureFromAdd(){

	if(testAdd() == null){

		var studentID = document.getElementById("add2").value;
		var name = document.getElementById("add3").value;
		var college = document.getElementById("add4").value;
		var major = document.getElementById("add5").value;
		var grade = document.getElementById("add6").value;
		var classes = document.getElementById("add7").value;
		var age = document.getElementById("add8").value;
	    var n = allStudent;

	    allStudent = 0;
		for(var i = n ; i > 0 ; i--){
			students[i] = new student(students[i-1].studentID , students[i-1].name , students[i-1].college , students[i-1].major , students[i-1].grade , students[i-1].classes , students[i-1].age);
		}
		students[0] = new student(studentID,name,college,major,grade,classes,age);
		ten = 0 ;
		Rwrite();

   	 	$(document).ready(function(){
   			$("#add").hide(1000);
		});
   	 	recoverAdd();
	}
	else
		alert(testAdd() + "  ! ! !");
}

/**
*恢复初始增加界面
**/
function recoverAdd(){
	document.getElementById("add2").value= "";
	document.getElementById("add3").value= "";
	document.getElementById("add4").value= "";
	document.getElementById("add5").value= "";
	document.getElementById("add6").value= "";
	document.getElementById("add7").value= "";
	document.getElementById("add8").value= "";
}



/**
*增加界面的数据校验
**/
function testAdd(){
	if(document.getElementById("add2").value.length == 0)
		return "学号不能为空哦";
	if(document.getElementById("add2").value.length > 11)
		return "学号不能超过11位哦";
	if(document.getElementById("add2").value.length < 8)
		return "学号都小于八位了";
	if(testRepetition(document.getElementById("add2").value) == false)
		return "你怎么能跟别人有一样的学号呢";
	if(isNaN(document.getElementById("add2").value) == true)
		return "哇！学号格式有点问题哦";

	if(document.getElementById("add3").value.length == 0)
		return "名字怎么能不写呢";
	if(document.getElementById("add3").value.length > 4)
		return "名字有点长";

	if(document.getElementById("add4").value.length == 0)
		return "您是那个学院的";
	if(document.getElementById("add4").value.length > 11)
		return "学院名好像有点长";

	if(document.getElementById("add5").value.length == 0)
		return "专业都不写吗";
	if(document.getElementById("add5").value.length > 10)
		return "没这个专业吧";

	if(document.getElementById("add6").value.length == 0)
		return "您是那一级的";
	if(document.getElementById("add6").value.length > 4)
		return "这级数有点大啊";

	if(document.getElementById("add7").value.length == 0)
		return "唉！班级还没写";
	if(document.getElementById("add7").value.length > 2)
		return "这么长的班号吗";
	if(isNaN(document.getElementById("add7").value) == true)
		return "哇！你的班号居然不是数字";

	if(document.getElementById("add8").value.length == 0)
		return "请问芳龄";
	if(document.getElementById("add8").value.length > 3)
		return "我看你很年轻，没这么大的年纪吧";
	if(isNaN(document.getElementById("add8").value) == true)
		return "哇！你这年龄好奇怪哦";

	return null;
}



function Rwrite(){

	var i;
	var index = ten + 10;
	var string = new String;
	var temp = new String;
	var temp2 = new String;
	var temp3 = new String;
	var div = document.getElementById("content");

	$(document).ready(function(){
    		$("#d1").hide(200);$("#d6").hide(200);
    		$("#d2").hide(200);$("#d7").hide(200);
    		$("#d3").hide(200);$("#d8").hide(200);
    		$("#d4").hide(200);$("#d9").hide(200);
    		$("#d5").hide(200);$("#d10").hide(200);
	 });

	for(i = ten; i < index && i < allStudent; i++){

		var test = i + 1;
		switch(i - ten){
			case 0:temp = "(0)'>";temp2="'0'";temp3="'d1'";break;
			case 1:temp = "(1)'>";temp2="'1'";temp3="'d2'";break;
			case 2:temp = "(2)'>";temp2="'2'";temp3="'d3'";break;
			case 3:temp = "(3)'>";temp2="'3'";temp3="'d4'";break;
			case 4:temp = "(4)'>";temp2="'4'";temp3="'d5'";break;
			case 5:temp = "(5)'>";temp2="'5'";temp3="'d6'";break;
			case 6:temp = "(6)'>";temp2="'6'";temp3="'d7'";break;
			case 7:temp = "(7)'>";temp2="'7'";temp3="'d8'";break;
			case 8:temp = "(8)'>";temp2="'8'";temp3="'d9'";break;
			case 9:temp = "(9)'>";temp2="'9'";temp3="'d10'";break;
		}
		if(num % 2 == 1)
			string += "<div id=" + temp3 + " class='divLine'>";
		else
			string += "<div id=" + temp3 + " class='divLine2'>";

		string += "<input name='input' id=" + temp2 + "  type='checkbox' onclick='setCheckbox" + temp;
		string +="<span class='psoition1'>" + test + "</span>";
		string +="<span class='psoition2'>" + students[i].studentID + "</span>";
		string +="<span class='psoition3'>" + students[i].name + "</span>";
		string +="<span class='psoition4'>" + students[i].college + "</span>";
		string +="<span class='psoition5'>" + students[i].major + "</span>";
		string +="<span class='psoition6'>" + students[i].grade + "</span>";
		string +="<span class='psoition7'>" + students[i].classes + "</span>";
		string +="<span class='psoition8'>" + students[i].classes + "</span>";
		string += "<span class='span' onclick='modify" + temp + " 修改 </span>";
		string += "<span class='span' onclick='see"  + temp + " 查看 </span>";
		string += "</div>";

		num = num + 1;
	}

	div.innerHTML = string;
	num = 0;

	$(document).ready(function(){
    		$("#d1").show(1000);$("#d6").show(1000);
    		$("#d2").show(1000);$("#d7").show(1000);
    		$("#d3").show(1000);$("#d8").show(1000);
    		$("#d4").show(1000);$("#d9").show(1000);
    		$("#d5").show(1000);$("#d10").show(1000);
	 });

	var order = ten / 10 + 1;
	var chars = "第" + order + "页,共" + allStudent + "条(每页显示10条)"
	document.getElementById("span").innerHTML = chars;
}



function nextPage(){

	if(ten + 11 < allStudent){
		for (var i = ten ; i < ten+10 && i < allStudent ; i++) {
				students[i].remove = false;
		}
		ten += 10;
		Rwrite();
	}
	else{
		alert("最后一页了哦。。。");
	}

}
function beforPage(){

	if(ten - 10 >= 0){
		for (var i = ten ; i < ten+10 && i < allStudent ; i++) {
				students[i].remove = false;
		}
		ten -= 10;
		Rwrite();
	}
	else{
		alert("已经是第一页了哦。。。")
	}

}
function Initialization(){

		for(var i=0 ; i < 10 ; i++){
			parameter[i] = i;
		}

		$(document).ready(function(){
			$("#add").hide();
			$("#see").hide();
			$("#modify").hide();


			document.getElementById("add").style.visibility = "visible";
			document.getElementById("see").style.visibility = "visible";
			document.getElementById("modify").style.visibility = "visible";
		});

		students[0] = new student("11603080423","杨强","计算机学院","软件工程","2016","4","19");
		students[1] = new student("11603080411","王桐","计算机学院","软件工程","2016","4","19");
		students[2] = new student("11603080233","王源栋","计算机学院","软件工程","2016","4","19");
		students[3] = new student("11603080412","胡倩","计算机学院","软件工程","2016","4","19");
		students[4] = new student("11603080414","王国瑜","计算机学院","软件工程","2016","4","19");
		students[5] = new student("11603080415","周流飞","计算机学院","软件工程","2016","4","19");
		students[6] = new student("11603080416","高王星","计算机学院","软件工程","2016","4","19");
		students[7] = new student("11603080417","康海燕","计算机学院","软件工程","2016","4","19");
		students[8] = new student("11603080418","冉彗","计算机学院","软件工程","2016","4","19");
		students[9] = new student("11603080419","胡浩天","计算机学院","软件工程","2016","4","19");
		students[10] = new student("11603080420","程若兰","计算机学院","软件工程","2016","4","19");
		students[11] = new student("11603080421","杨坚强","计算机学院","软件工程","2016","4","19");
		students[12] = new student("11603080444","孙金","计算机学院","软件工程","2016","4","19");
		students[13] = new student("11603080445","魏吉","计算机学院","软件工程","2016","4","19");
		students[14] = new student("11603080446","流氓","计算机学院","软件工程","2016","4","19");

}
