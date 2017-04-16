/**
 * Created by xvhuanlin on 16/10/5.
 */

function _post(URL, json, _success, _error, targetURL) {
    if(!URL.endsWith('/')) URL += '/';
    $.ajax({
        async:false,
        contentType: "application/json; charset=utf-8",
        type: "POST",
        url: URL,
        data: JSON.stringify(json),
        dataType : "json",
        error: function(data) {
            _error(data);
        },
        success: function(data) {
            _success(data, targetURL);
        },
        xhrFields: {
            withCredentials: true
        }
    });

}
