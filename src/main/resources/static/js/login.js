
var DOMAIN = (location.origin == "http://localhost:63342") ? "http://" : location.origin;

function _success(data, targetURL) {
    alert(data.msg);
    if (data.success == "true") {
        window.location.href = targetURL;
    }
}

function _error(data) {
    alert(data.msg);
}

function _login() {
    var username = document.getElementById("inputUsername").value;
    var password = document.getElementById("inputPassword").value;

    if (username == "") {
        alert("用户名不能为空哟");
        return false;
    }

    if (password == "") {
        alert("密码不能为空哦");
        return false;
    }

    var json = {
        username : username,
        password : password
    };

    _post(DOMAIN + "/user/login/", json, _success, _error, "/mydocuments.html");

    return false;
}