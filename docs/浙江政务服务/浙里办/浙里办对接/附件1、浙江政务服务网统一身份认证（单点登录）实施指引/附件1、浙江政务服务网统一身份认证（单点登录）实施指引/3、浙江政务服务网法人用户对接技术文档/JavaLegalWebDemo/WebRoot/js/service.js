function callService(url, data, callback) {
    showLoading();
    $.ajax({
        type : "POST",
        url : url,
        data : data,
        success : callback,
        error : function(){
            hideLoading();
            alertError('服务异常');
        }
    });
}

function showLoading() {
    $('.bg').show();
}

function hideLoading() {
    $('.bg').hide();
}

function alertError(msg) {
    $('#errorMsg').text(msg);
    $('#errorModal').modal('show');
}