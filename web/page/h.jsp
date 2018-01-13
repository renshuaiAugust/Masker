<%--
  Created by IntelliJ IDEA.
  User: ttc
  Date: 17-12-27
  Time: 下午8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        window.onload = function () {
            document.getElementById("img1").onclick = function () {

                document.getElementById("img11").click();
            }
            //点击保存时触发；
            document.getElementById("in").onclick = function () {

                var f = document.getElementById("form1");
                var fd = new FormData(f);
                var xhr = new XMLHttpRequest();
                xhr.open("post", "/ServletSendDynamic");
                xhr.send(fd);
                xhr.onreadystatechange = function () {
                    var s = xhr.responseText;
                    if (s == "noall") {
                        document.getElementById("img1").src = "/images/tianjia.jpg";
                    }else if(s == "noimg")
                    {
                        document.getElementById("img1").style.display = "none";
                    }else {
                        document.getElementById("img1").src = s;

                    }
                }
            }
            //如果选择图片，并打开触发；
            document.getElementById("img11").onchange = function () {
                var f = document.getElementById("form1");
                var fd = new FormData(f);
                var xhr = new XMLHttpRequest();
                xhr.open("post", "/ServletSendDynamic");
                xhr.send(fd);
                xhr.onreadystatechange = function () {
                    var r = xhr.responseText;

                    if (r == "noall") {
                        document.getElementById("img1").src = "/images/tianjia.jpg";
                    }else if(r == "noimg")
                    {
                        document.getElementById("img1").src = "/images/tianjia.jpg";
                    }else {
                        document.getElementById("img1").src = r;

                    }
                }
            }
        }

    </script>


</head>
<body>
<div>

    <form action="/ServletSendDynamic" method="post" enctype="multipart/form-data" id="form1">
        <input type="file" name="file" accept="image/ipg" id="img11" style="display: none"/>
        <input type="text" name="text">
        <textarea name="textarea" id="textarea" cols="50px" rows="20px"></textarea>
        <input type="button" id="in" value="保存">
    </form>
    <img src="/images/tianjia.jpg" style="display: block" width="90px" height="90px" id="img1"/><br>

</div>
</body>
</html>
