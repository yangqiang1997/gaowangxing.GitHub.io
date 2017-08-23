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
		document.getElementById("see").style.visibility = "visible";
		document.getElementById("see2").value = students[index + ten].studentID;
		document.getElementById("see3").value = students[index + ten].name;
		document.getElementById("see4").value = students[index + ten].college;
		document.getElementById("see5").value = students[index + ten].major;
		document.getElementById("see6").value = students[index + ten].grade;
		document.getElementById("see7").value = students[index + ten].classes;
		document.getElementById("see8").value = students[index + ten].age;
}
function backFromSee(){
		document.getElementById("see").style.visibility = "hidden";
}



/**
*响应修改界面的函数
**/

function modify(index){
		document.getElementById("modify").style.visibility = "visible";
		document.getElementById("modify2").value = students[index + ten].studentID;
		document.getElementById("modify3").value = students[index + ten].name;
		document.getElementById("modify4").value = students[index + ten].college;
		document.getElementById("modify5").value = students[index + ten].major;
		document.getElementById("modify6").value = students[index + ten].grade;
		document.getElementById("modify7").value = students[index + ten].classes;
		document.getElementById("modify8").value = students[index + ten].age;

		backModifyIndex = index;
}
function backFromModify(){
		document.getElementById("modify").style.visibility = "hidden";
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

	
		document.getElementById("modify").style.visibility = "hidden";

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
function Repetition(str , index){

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
	ten = 0 ;


	for(var i = 0 ; i < n ; i++){
		if(students[i].remove == false){
			students[NO] = new student(students[i].studentID , students[i].name , students[i].college , students[i].major , students[i].grade , students[i].classes , students[i].age);
			NO++;
		}
	}
	Rwrite();
   }
}



/**
*响应增加界面的函数
**/

function addLine(){
	document.getElementById("add").style.visibility = "visible";
}

function backFromAdd(){
	document.getElementById("add").style.visibility = "hidden";
}

function sureFromAdd(){

	if(testAdd() == null){

		var index = allStudent;
		var studentID = document.getElementById("add2").value;
		var name = document.getElementById("add3").value;
		var college = document.getElementById("add4").value;
		var major = document.getElementById("add5").value;
		var grade = document.getElementById("add6").value;
		var classes = document.getElementById("add7").value;
		var age = document.getElementById("add8").value;

		students[index] = new student(studentID,name,college,major,grade,classes,age);
		Rwrite();

   	 	document.getElementById("add").style.visibility = "hidden";
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
	var div = document.getElementById("content");




	for(i = ten; i < index && i < allStudent; i++){

		if(num % 2 == 1)
			string += "<div id='div' class='divLine'>";
		else
			string += "<div id='div' class='divLine2'>";

		switch(i - ten){
			case 0:temp = "(0)'>";temp2="'0'";break;
			case 1:temp = "(1)'>";temp2="'1'";break;
			case 2:temp = "(2)'>";temp2="'2'";break;
			case 3:temp = "(3)'>";temp2="'3'";break;
			case 4:temp = "(4)'>";temp2="'4'";break;
			case 5:temp = "(5)'>";temp2="'5'";break;
			case 6:temp = "(6)'>";temp2="'6'";break;
			case 7:temp = "(7)'>";temp2="'7'";break;
			case 8:temp = "(8)'>";temp2="'8'";break;
			case 9:temp = "(9)'>";temp2="'9'";break;
		}

		string += "<input name='input' id="
		string += temp2 + "  type='checkbox' onclick='setCheckbox";
		string += temp;

		string +="<span class='psoition1'>" + students[i].serial + "</span>";
		string +="<span class='psoition2'>" + students[i].studentID + "</span>";
		string +="<span class='psoition3'>" + students[i].name + "</span>";
		string +="<span class='psoition4'>" + students[i].college + "</span>";
		string +="<span class='psoition5'>" + students[i].major + "</span>";
		string +="<span class='psoition6'>" + students[i].grade + "</span>";
		string +="<span class='psoition7'>" + students[i].classes + "</span>";
		string +="<span class='psoition8'>" + students[i].classes + "</span>";

		string += "<span class='span' onclick='modify"
		string += temp;
		string += " 修改 </span>";

		string += "<span class='span' onclick='see"
		string += temp;
		string += " 查看 </span>";
	
		string += "</div>";

		num = num + 1;
	}

	div.innerHTML = string;
	num = 0;


	var order = ten / 10 + 1;
	var chars = "第" + order + "页,共" + allStudent + "条(每页显示10条)"
	document.getElementById("span").innerHTML = chars;
}



function nextPage(){

	for (var i = ten ; i < ten+10 && i < allStudent ; i++) {
				students[i].remove = false;
		}

	if(ten + 10 < allStudent)
		ten += 10;
	Rwrite();

}
function beforPage(){

	for (var i = ten ; i < ten+10 && i < allStudent ; i++) {
				students[i].remove = false;
		}

	if(ten - 10 >= 0)
		ten -= 10;
	Rwrite();

}
