
var DOMAIN = (location.origin == "http://localhost:63342") ? "http://" : location.origin;
var liList = document.getElementById("bs-example-navbar-collapse-1").getElementsByTagName("ul")[0].getElementsByTagName("li");

window.onload = _load();

function _successInfo(data, targetURL) {
    alert(data.msg);
    if (data.success == "true") {
        var tmp = liList[2].getElementsByTagName("a")[0];
        tmp.href = "./mydocuments.html";
        tmp.innerText = "我的文档";
        tmp = liList[3].getElementsByTagName("a")[0];
        tmp.href = "./user/logout";
        tmp.innerText = "登出";
    }
}

function _load() {
    var json;
    json = {};
    _post(DOMAIN + "./user/info", json, _successInfo, _error, "#");
}

function _error(data) {
    alert(data.msg);
}