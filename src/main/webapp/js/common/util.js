//获取URL参数
function getURLParameter(name) {
    let param = (new RegExp(name + '=(.+?)(&|$)').exec(location.search) || ['', null])[1];
    if (param) {
        return decodeURIComponent(param)
    } else {
        return null
    }
}

//初始化
Handlebars.registerHelper("if_type", function(type,  options) {
    if(type==="button") {
        //button
        return options.fn(this); // 满足添加继续执行
    } else {
        //url
        return options.inverse(this); // 不满足条件执行{{else}}部分
    }
});
