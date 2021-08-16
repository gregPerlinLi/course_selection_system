/**
 * 获取url链接中的请求参数
 * @param name 提供需要查找的参数名
 * @returns {string|null} 返回该参数的值，若没有参数，则返回<code>null</code>
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

