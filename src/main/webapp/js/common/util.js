//获取URL参数
function getURLParameter(name) {
    let param = (new RegExp(name + '=(.+?)(&|$)').exec(location.search) || ['', null])[1];
    if (param) {
        return decodeURIComponent(param)
    } else {
        return null
    }
}