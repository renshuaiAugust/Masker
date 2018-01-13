// 页面加载添加动态

var xhr1 = new XMLHttpRequest();
xhr1.open('post', "#")
xhr1.send();
xhr1.onreadystatechange = function () {
    if (xhr1.readyState == 4 && (xhr1.status == 200 || xhr1.status == 200)) {
        var js1 = xhr1.responseText;
        var json1 = JSON.parse(js1);
        console.log(json1);
        var string = "<a href=''></a>";
        for (var i in json1) {
            string += '<div class="focusperson">';
            string += '<img src="' + json1[i].masker_topic_x2 + '" class="focusimage">';
            string += '<div class="focusname" id="focusname">' + json1[i].masker_topic_x3 + '</div>';
            string += '<div class="focusactiontitle">' + json1[i].masker_topic_x4 + '</div>';
            string += '<div class="focusactiondiscirb">' + json1[i].masker_topic_x5 + '</div>';
            if(json1[i].masker_topic_x6!=null) {
                string += '<img src="' + json1[i].masker_topic_x6 + '" class="focusactiondetil">';
            }
            string += '<div class="saysome" id="saysome">';
            string += '<h3 class="saysome1">删除</h3>';
            string += '</div>';
            string += '</div>';

        }
        document.getElementById("body").innerHTML = string;


    }
}
// 网页头点击事件
var moreflag = true;
$('.firstpage').on('click', function () {
    window.location.href = "http://localhost:8080/html/Personal.html"
})
$('.hotpage').on('click', function () {
    window.location.href = "http://localhost:8080/html/hotuser.html"
})
$('.morefirst').on('click', function () {
    if (moreflag) {
        $('.more').animate({'height': '156%'}, 200, 'linear');
        moreflag = false;
    }
    else {
        $('.more').animate({'height': '100%'}, 200, 'linear');
        moreflag = true;
    }


})
// 个人导航点击事件
$('.mineaction').on('click', function () {
    window.location.href = "http://localhost:8080/html/mineAction.html"
})
$('.minefocus').on('click', function () {
    window.location.href = "http://localhost:8080/html/myFocus.html"
})
$('.favourite').on('click', function () {
    window.location.href = "http://localhost:8080/html/favouritePacket.html"
})


//用户个人动态---------------------------------
var oUl = $('.wrapUl'),
    oLi = $('.wrapli');

function init() {
    oLi.css({'height': '40px'});
    oLi.find('.title').css({
        'display': 'block'
    });
    oLi.find('.decration').css({
        'display': 'none'
    });
    oLi.find('.picBox').css({
        'display': 'none'
    })

}

function bindEvent() {
    oLi.on('mouseover', function () {
        change($(this));
    });
    oLi.on('mouseleave', function () {
        $(this).css({
            'height': '40px'
        });

        $(this).find('.title').css({
            'display': 'block'
        });
        $(this).find('.picBox').css({
            'display': 'none'
        });
        $(this).find('.decration').css({
            'left': '-300px'
        });
        $(this).find('.decration').css({
            'display': 'none'
        });

    });
    oUl.on('mouseleave', function () {
        oLi.css({
            'height': '40px'
        });

        oLi.find('.title').css({
            'display': 'block'
        });
        oLi.find('.picBox').css({
            'display': 'none'
        });
        oLi.find('.decration').css({
            'display': 'none'
        });
        oLi.find('.decration').css({
            'left': '-300px'
        });
    });
}

function change(event) {
    event.animate({
        'height': '200px'
    }, 15, 'linear');

    event.find('.title').css({
        'display': 'none'
    });
    event.find('.picBox').css({
        'display': 'block'
    });
    event.find('.decration').css({
        'left': '0'
    })
    event.find('.decration').css({
        'display': 'block'
    })


}

init();
bindEvent();

//左侧铅笔---------------------------------

var flag = true;
$('.pencil').on('click', function () {
    if (flag) {
        // $('.pencil').animate({'border-radius':'0'},200,'linear');
        // $('.pencil').animate({'border-top-left-radius':'50%'},100,'linear');
        // $('.pencil').animate({'border-top-right-radius':'50%'},100,'linear');
        // $(this).animate({'border-bottom-left-radius':'0'},200,'linear');
        // $(this).animate({'border-bottom-right-radius':'0'},200,'linear');
        $(this).css({'border-bottom-left-radius': '0'});
        $(this).css({'border-bottom-right-radius': '0'});
        $('.actiondetil').animate({'height': '250px'}, 200, 'linear');
        flag = false;
    }
});
$('.action').on('mouseleave', function () {
    if (flag == false) {
        $('.actiondetil').animate({'height': '0'}, 200, 'linear');
        $('.pencil').animate({'border-radius': '10%'}, 200, 'linear');
        $('.pencil').animate({'border-radius': '50%'}, 200, 'linear');
        flag = true;
    }
});


//固定话题栏---------------------------------

var a = document.getElementsByClassName('titleof')
for (var i = 0; i < a.length; i++) {
    var num = Math.floor(Math.random() * 50);
    a[i].setAttribute('style', "left: " + num + "px !important;");
}
$('.titleof').on('mouseover', function () {
    $(this).css({'left': '60px'});
})
$('.titleof').on('mouseleave', function () {
    $(this).css({'left': '40px'});
})
$('.talkabout').on('mouseleave', function () {
    for (var i = 0; i < a.length; i++) {
        console.log(Math.floor(Math.random() * 1000));
        var num = Math.floor(Math.random() * 50);
        a[i].setAttribute('style', "left: " + num + "px !important;");
    }
});


//点击我的信息弹出个人信息页
$('.mineinfo').on('click', function () {
    $('.userinfo').css({'display': 'block'});
    var xhr1 = new XMLHttpRequest();
    xhr1.open('post', "/ServletPersonalInfo")
    xhr1.send();
    xhr1.onreadystatechange = function () {
        if (xhr1.readyState == 4 && (xhr1.status == 200 || xhr1.status == 200)) {
            var js1 = xhr1.responseText;
            var json1 = JSON.parse(js1);
            console.log(json1);


            var string = "<a href=''></a>";

            string += '<form  name="userinfo" id ="personbox" class="personbox"  action="#" method="post" enctype="multipart/form-data">';
            string += '<div class="exit fa fa-power-off fa-5x" ></div>';
            string += '<img src='+json1.masker_user_x4+' alt="头像"" class="userhead">';
            string += ' <input  class="upload" type="file" name="photo" hidden="hidden" onclick="">';
            string += '<div class="changehead">选择图片</div>';
            string += '<div class="user">';
            string += '<div>用户名:<input  type="text" value='+json1.masker_user_x2+' disabled="disabled" class="userinput username" name="nickname"></div>';
            string += ' <div >性别:';
            if(json1.masker_user_x5=="男")
            {   string += '<input class="userinput" type="radio" name="gender" value="男" disabled="disabled" checked="checked">男';
                string += '<input class="userinput" type="radio" name="gender" value="女" disabled="disabled">女';
            }else
            {
                string += '<input class="userinput" type="radio" name="gender" value="男" disabled="disabled" >男';
                string += '<input class="userinput" type="radio" name="gender" value="女" disabled="disabled" checked="checked">女';
            }
            string += '</div>';
            string += '<div>出生日期: <input name= "birthday"class="userinput" type="date" value='+json1.masker_user_x6+' class="userinput" disabled="disabled"></div>';
            string += '</div>';
            string += '<div>个人简介:</div>';
            string += '<div><textarea  name="userbrief" class="userinput userintr"  rows="10" cols="60" disabled="disabled">'+json1.masker_user_x9+'</textarea></div>';
            string += '<div class="save">保存</div>';
            string += '<div class="changeall">修改信息</div>';
            string += '</form>';


            document.getElementById("userinfo").innerHTML =string;


        }
    }


});

$(document).on('click','.exit', function () {
    $('.userinput').css({'border': '0px solid white'});
    $('.userinput').attr('disabled', true);
    $('.changehead').css({'display': 'none'});
    $('.save').css({'display': 'none'});
    $('.userinfo').css({'display': 'none'})
});
// 个人信息页操作

$(document).on('click','.changeall', function () {
    $('.userinput').css({'border': '1px solid grey'});
    $('.userinput').attr('disabled', false);
    $('.changehead').css({'display': 'block'});
    $('.save').css({'display': 'block'});
});
$(document).on('click','.changehead', function () {
    document.getElementsByClassName('upload')[0].click();
});
$(document).on('click','.save', function () {
    var xhr1 = new XMLHttpRequest();
    var form1 = document.getElementById('personbox');
    var form=new FormData(form1);
    xhr1.open('post', "/ServletEditPersonalInfo")
    xhr1.send(form);
    xhr1.onreadystatechange = function (){
        if (xhr1.readyState == 4 && (xhr1.status == 200 || xhr1.status == 200))
        {
            var res = xhr1.responseText;
            $('.userinfo').css({'display': 'none'});
            alert(res);
        }
    }

});
$(document).on('change','.upload', function () {
    var xhr1 = new XMLHttpRequest();
    var form1 = document.getElementById('personbox');
    var form=new FormData(form1);
    xhr1.open('post', "/ServletSendPhoto")
    xhr1.send(form);
    xhr1.onreadystatechange = function (){
        if (xhr1.readyState == 4 && (xhr1.status == 200 || xhr1.status == 200))
        {
            var res = xhr1.responseText;
            document.getElementsByClassName('userhead')[0].setAttribute('src', res);
        }
    }

});


// 点击相机弹出编辑动态页面
$('.camera').on('click', function () {
    $('.newaction').css({'display': 'block'});

    var xhr = new XMLHttpRequest();
    xhr.open("post", "/ServletEditDynamic");
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && ( xhr.status == 200 || xhr.status == 304)) {
            var r = xhr.responseText;
            var josn=JSON.parse(r);
            var img = document.getElementsByClassName('thishead');
            img[0].setAttribute('src',josn.thisimg);
            var name = document.getElementsByClassName('thisname');
            name[0].innerHTML=josn.thisname;

        }
    }

})

// 编辑动态页面操作
$('.add').on('click', function () {
    document.getElementById('actionimg').click();
});
$('.actionexit').on('click', function () {

    $('.newaction').css({'display': 'none'});
    document.getElementById('img1').setAttribute('src', '../images/add.jpg');
});
$('.cann').on('click', function () {
    ''
    $('.newaction').css({'display': 'none'});
    document.getElementById('img1').setAttribute('src', '../images/add.jpg');
});
//上传图片预览效果
$('#actionimg').on('change', function () {
    var form = document.getElementById('actionbox');
    var fd = new FormData(form);

    var xhr = new XMLHttpRequest();
    xhr.open("post", "/ServletPicturePreview");
    xhr.send(fd);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && ( xhr.status == 200 || xhr.status == 304)) {
            var r = xhr.responseText;

            document.getElementById('img1').setAttribute('src', r);
        }
    }

})

//点击comi发布动态
$('.comi').on('click', function () {
        var form = document.getElementById('actionbox');
        var fd = new FormData(form);
        var xhr = new XMLHttpRequest();
        xhr.open("post", "/ServletSendDynamic");

        xhr.send(fd);


        xhr.onreadystatechange = function () {

            if (xhr.readyState == 4 && ( xhr.status == 200 || xhr.status == 304)) {
                var s = xhr.responseText;
                if (s == "发布成功") {
                    $('.newaction').css({'display': 'none'});
                    alert("发布成功");
                }

            }
        }


    }
)


//注销
$('.zhuxiao').on('click',function(){
    window.location.href="http://localhost:8080/html/index.html";
})