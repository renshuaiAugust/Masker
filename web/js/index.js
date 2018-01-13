function $(String) {
    return document.getElementById(String);
}


// 页面效果切换
$("register").onclick = function () {
    console.log($('login'));
    $('login').className = "login";
    $('register').className += " register1";
    $('download').className = "download";
    $('form1').style.display = "none";
    $('form3').style.display = "none";
    $('form2').style.display = "block";
}
$("login").onclick = function () {
    console.log($('login').style.backgroundColor);
    $('login').className += " login1";
    $('register').className = "register";
    $('download').className = "download";
    $('form3').style.display = "none";
    $('form1').style.display = "block";
    $('form2').style.display = "none";
}
$("download").onclick = function () {
    console.log($('login').style.backgroundColor);
    $('login').className = "login";
    $('register').className = "register";
    $('download').className += " download1";
    $('form3').style.display = "block";
    $('form1').style.display = "none";
    $('form2').style.display = "none";
}

// 登录界面校验
$('username1').onfocus = function () {
    $('worning1').innerHTML = "";
}
$('userpassword1').onfocus = function () {
    $('worning2').innerHTML = "";
}
$('username1').onblur = function () {
    var ptnn = /^[0-9]{11}$/;
    var pttn = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (this.value != "" && !pttn.test(this.value) && !ptnn.test(this.value)) {

        $('worning1').innerHTML = "用户名不合法";
    }
}
$('userpassword1').onblur = function () {
    if (this.value != "" && this.value.length > 12) {
        $('worning2').innerHTML = "密码不能超出12位";
    }
}
$('submit1').onclick = function () {
    if ($('worning1').innerHTML == "" && $('worning2').innerHTML == "") {
        if ($('username1').value == "") {
            $('worning1').innerHTML = "用户名不能为空";
        }
        else {
            if ($('userpassword1').value == "") {
                $('worning2').innerHTML = "密码不能为空";
            }
            else {
                var form = new FormData($('one'));


                var xhr = new XMLHttpRequest();
                xhr.open("post", "/ServletLogin");

                xhr.send(form);


                xhr.onreadystatechange = function () {

                    if (xhr.readyState == 4 && ( xhr.status == 200 || xhr.status == 304)) {
                        var worning = xhr.responseText;
                        console.log(worning);

                        if(worning=="用户名或密码错误") {
                            $('worning1').innerHTML = worning;
                        }else
                        {
                            window.location.href=worning;
                        }

                    }
                }

            }
        }
    }


// 注册界面校验
    $('username2').onfocus = function () {
        $('worning3').innerHTML = "";
    }
    $('userpassword2').onfocus = function () {
        $('worning4').innerHTML = "";
    }
    $('userpassword3').onfocus = function () {
        $('worning5').innerHTML = "";
    }
    $('username2').onblur = function () {
        var ptnn = /^[0-9]{11}$/;
        var pttn = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (this.value != "" && !pttn.test(this.value) && !ptnn.test(this.value)) {

            $('worning3').innerHTML = "用户名不合法";
        }
        ;
    }
    $('userpassword2').onblur = function () {
        if (this.value != "" && this.value.length > 12) {
            $('worning4').innerHTML = "密码不能超出12位";
        }
    }
    $('userpassword3').onblur = function () {
        if (this.value != "" && $('userpassword2').value != "") {
            if (this.value !== ($('userpassword2').value)) {
                $('worning5').innerHTML = "两次密码输入不一致";
            }
        }

    }
    $('submit2').onclick = function () {
        if ($('worning3').innerHTML == "" && $('worning4').innerHTML == "" && $('worning5').innerHTML == "") {
            if ($('username2').value == "") {
                $('worning3').innerHTML = "用户名不能为空";
            }
            else {
                if ($('userpassword2').value == "") {
                    $('worning4').innerHTML = "密码不能为空";
                }
                else {
                    if ($('userpassword3').value == "") {
                        $('worning5').innerHTML = "请确认密码";
                    }
                    else {
                        var form = new formDate($('two'));
                        var xhr = new XMLHttpRequest();
                        xhr.open("post", "#");
                        xhr.send(form);
                        xhr.onreadystatechange = function () {
                            if (xhr.readystate == 4 && ( xhr.status == 200 || xhr.status == 304)) {
                                var worning = xhr.responseText;
                                cosole.log(worning);
                                // if(worning=="用户名或密码错误") {
                                //     $('worning3').innerHTML = worning;
                                // }else
                                // {
                                //     // window.location.href=worning;
                                // }
                            }
                        }
                    }
                }
            }
        }
    }
}





