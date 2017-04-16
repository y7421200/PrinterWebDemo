
var _data;
var DOMAIN = (location.origin == "http://localhost:63342") ? "http://" : location.origin;

window.onload = _details();

function _success(data, targetURL) {
    alert(data.msg);
    if (data.success == "true") {
        window.location.href = targetURL;
    }
}

function _addElements(data) {
    var root = document.getElementById("cards-wrapper");

    for (var i in data) {
        var item = document.createElement("div");
        var itemInner = document.createElement("div");
        var iconHolder = document.createElement("div");
        var icon = document.createElement("span");
        var title = document.createElement("h3");
        var intro = document.createElement("p");

        root.appendChild(item);
        item.appendChild(itemInner);
        itemInner.appendChild(iconHolder);
        iconHolder.appendChild(icon);
        itemInner.appendChild(title);
        itemInner.appendChild(intro);

        item.id = i.id;
        item.className = "item item-green col-md-4 col-sm-6 col-xs-6";
        item.dataset.toggle = "modal";

        itemInner.className = "item-inner";
        iconHolder.className = "icon-holder";

        title.className = "title";
        title.innerText = i.filename;
        intro.className = "intro";

        if (i.filetype == "doc" || i.filetype == "docx") {
            intro.innerText = "Word文件";
        }
        else if (i.filetype == "xls" || i.filetype == "xlsx") {
            intro.innerText = "Excel文件";
        }
        else if (i.filetype == "ppt" || i.filetype == "pptx") {
            intro.innerText = "PowerPoint文件";
        }
        else if (i.filetype == "pdf") {
            intro.innerText = "PDF文件";
        }
        else {
            intro.innerText = "其他文件";
        }

        if (i.status < 0) {
            item.dataset.target = "readyToPay";
            intro.innerHTML += "</br>未付款"
        }
        else if (i.status > 0) {
            item.dataset.target = "completed";
            intro.innerHTML += "</br>已打印"
        }
        else {
            item.dataset.target = "readyToPrint";
            intro.innerHTML += "</br>已付款"
        }

    }
}

function _successDetails() {
    alert(data.msg);
    if (data.success == "true") {
        _data = data.data;
        _addElements(_data);
    }
}

function _error(data) {
    alert(data.msg);
}

function _details() {
    var json;
    json = {};
    _post(DOMAIN + "/document/details", json, _successDetails, _error, "#");
}

function _delete(id) {
    var json;
    json = {
        id: id
    };
    _post(DOMAIN + "/document/delete", json, _success, _error, "/mydocuments.html");
    return false;
}

function _paid(id) {
    var json;
    json = {
        id: id
    };
    _post(DOMAIN + "/status/paid", json, _success, _error, "/mydocuments.html");
    return false;
}

function utf16to8(str) {
    var result = "";
    var len = str.length;
    for(var i = 0; i < len; i++) {
        var ch = str.charCodeAt(i);
        if ((ch >= 0x0001) && (ch <= 0x007F)) {
            result += str.charAt(i);
        } else if (ch > 0x07FF) {
            result += String.fromCharCode(0xE0 | ((ch >> 12) & 0x0F));
            result += String.fromCharCode(0x80 | ((ch >>  6) & 0x3F));
            result += String.fromCharCode(0x80 | ((ch >>  0) & 0x3F));
        } else {
            result += String.fromCharCode(0xC0 | ((ch >>  6) & 0x1F));
            result += String.fromCharCode(0x80 | ((ch >>  0) & 0x3F));
        }
    }
    return result;
}

function _showCompleted(id) {
    var modal = document.getElementById("completed");
    modal.dataset.id = id;
    $('#completed').modal('show');
}

function _showReadyToPrint(id) {
    var canvas = document.getElementById("qrcodeCanvas").getElementsByTagName("canvas")[0];
    if (canvas != null) {
        canvas.parentNode.removeChild(canvas);
    }
    jQuery('#qrcodeCanvas').qrcode({
        text : utf16to8(id)
    });
    $('#readyToPrint').modal('show');
}

function _showReadyToPay(id) {
    var modal = document.getElementById("readyToPay");
    modal.dataset.id = id;
    $('#readyToPay').modal('show');
}

function _uploadFile() {

    
    var amount = document.getElementById("inputPrintAmount").value;
    var single = document.getElementById("single");
    var type = "single";
    if (single.checked == null) {
        type = "plural";
    }

    var json;
    json = {
        amount : amount,
        type : type
    };

    $.ajaxFileUpload
    (
        {
            url: '/action/uploadfile',
            type: 'POST',
            data: JSON.stringify(json),
            secureuri: false,
            fileElementId: 'inputUpload',
            dataType: 'JSON',
            success: function (data)
            {
                alert(data.msg);
                if (data.success == "true") {
                    window.location.href = "./mydocuments.html";
                }
            },
            error: function (data)
            {
                alert(data.msg);
            }
        }
    );
    
/*
    var form = new FormData(document.getElementById("inputForm"));
    $.ajax({
        async: false,
        contentType: false,
        processData: false,
        type: "POST",
        url: '/action/uploadfile',
        data: form,
        dataType : "json",
        error: function(data) {
            _error(data);
        },
        success: function(data) {
            _success(data, './mydocuments.html');
        },
        xhrFields: {
            withCredentials: true
        }
    });
    return false;
*/
}