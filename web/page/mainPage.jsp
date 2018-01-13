<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div id="div">
fsdfsdf
</div>
</body>
<script>
    window.onload = function () {
        var xhr = new XMLHttpRequest();
        xhr.open("post", "/ServletMainPage");
        xhr.send();

        xhr.onreadystatechange = function () {

            var s = xhr.responseText;

            var sf = JSON.parse(s);

            document.write(sf[0].masker_topic_x1);


        }
    }


</script>
</html>
