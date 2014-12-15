/**
 * Author: zk
 * Date: 2014-07-10
 */
define('util/time',function (require, exports, module) {
    var $ = require("lib/jquery");

    var weekday = new Array(7);
    weekday[0] = "星期一";
    weekday[1] = "星期二";
    weekday[2] = "星期三";
    weekday[3] = "星期四";
    weekday[4] = "星期五";
    weekday[5] = "星期六";
    weekday[6] = "星期日";
     function GetType(arg) {
        var today = new Date();
        var year = today.getFullYear();
        var month = today.getMonth() + 1;
        var td = today.getDate();
        var d = weekday[today.getDay() - 1];
        var h = today.getHours();
        var m = today.getMinutes();
        var s = today.getSeconds();
        switch (arg) {
            case 1:  //2013-09-09 09:31:56
                return year + "-" + month + "-" + td + "  " + h + ":" + m + ":" + s; break;
            case 2:  //2013-09-09 (星期一) 09:31:56
                return year + "-" + month + "-" + td + " (" + d + ") " + h + ":" + m + ":" + s; break;
            case 3:  //09-09-2013 09:31:56
                return month + "-" + td + "-" + year + "  " + h + ":" + m + ":" + s; break;
            case 4:  //09-09-2013 星期一 09:31:56
                return month + "-" + td + "-" + year + " (" + d + ") " + h + ":" + m + ":" + s; break;
            case 5:  //2013年09月09日 09时31分秒56
                return year + "年" + month + "月" + td + "日  " + h + "时" + m + "分" + s + "秒"; break;
            case 6:  //2013年09月09日 星期一 09时31分秒56
                return year + "年" + month + "月" + td + "日  (" + d + ")  " + h + "时" + m + "分" + s + "秒"; break;
        }
    };
    /*******************************************************
    /*函数名：GetTime
    /*参数：数值（包括整形浮点型都可以，浮点型会做四舍五入处理，如果不是数字，
    函数将采用默认的时间格式返回！时间样式有15【1-15是有效的时间样式
    超出或小于都将采用默认的样式 样式1】中）
    /*功能  获取当前的系统时间 可定制格式
    *******************************************************/
     function  GetTime(arg) {
        if (!isNaN(arg)) {
            var num = Math.round(arg);
            if (num < 7 && num > 0) {
                return GetType(num);
            }
            else {
                var str;
                var str2;
                switch (num) {
                    case 0: return GetType(1); break;
                    case 7: str = GetType(2); return str.replace(/星期/, ""); break;
                    case 8: str = GetType(1); return str.replace(/-/, "/").replace(/-/, "/"); break;
                    case 9: str = GetType(2); return str.replace(/-/, "/").replace(/-/, "/");
                    case 10: str = GetType(2); str2 = str.replace(/-/, "/").replace(/-/, "/"); return str2.replace(/星期/, ""); break;
                    case 11: str = GetType(4); return str.replace(/星期/, ""); break;
                    case 12: str = GetType(3); return str.replace(/-/, "/").replace(/-/, "/"); break;
                    case 13: str = GetType(4); return str.replace(/-/, "/").replace(/-/, "/");
                    case 14: str = GetType(4); str2 = str.replace(/-/, "/").replace(/-/, "/"); return str2.replace(/星期/, ""); break;
                    case 15: str = GetType(6); return str.replace(/星期/, "");
                    default: return GetType(1); break;
                }
            }
        }
        else {
            return GetType(1);
        }
    };

    /* 获取系统的当前年数*/
     function GetYear() {
        var today = new Date();
        return today.getFullYear();
    };

    /*获取系统的当前的月数*/
      function GetMonth() {
        var today = new Date();
        return today.getMonth() + 1; ;
    };
    /*获取系统的当前的天数*/
      function GetDay() {
        var today = new Date();
        return today.getDate(); ;
    };
    /*获取系统的当前的小时*/
   function GetHours() {
        var today = new Date();
        return today.getHours();
    };
    /*获取系统的当前的分钟*/
     function GetMinute() {
        var today = new Date();
        return today.getMinutes();
    };
    /*获取系统的当前的秒数*/
     function GetSecond() {
        var today = new Date();
        return today.getSeconds();
    };

    /************************************************************
    *函数名：TimeSubMillisecond
    *参数：endtime(结束时间) starttime(起始时间)
    *功能：获取两个时间的毫秒级的差值，必须写一个参数 第二个参数（起始时间）可以
    *不写默认是系统当前时间
    ************************************************************/
    function  TimeSubMillisecond(endtime, starttime) {
        var end = new Date(endtime).getTime();
        if (!endtime) {
            return -1;
        }
        if (!starttime) {
            start = new Date().getTime();
        }
        else {
            start = new Date(starttime).getTime();
        }
        if (start > end) {
            return -1;
        }
        else {
            return end - start;
        }
    };
    /************************************************************
    *函数名：TimeSubNormal
    *参数：endtime(结束时间) starttime(起始时间)
    *功能：获取两个时间的差值，必须写一个参数 第二个参数（起始时间）可以
    *不写默认是系统当前时间
    ************************************************************/
      function  TimeSubNormal(endtime, starttime) {
        var end = new Date(endtime).getTime();
        var start;
        if (!starttime) {
            start = new Date().getTime();
        }
        else {
            start = new Date(starttime).getTime();
        }
        if (start > end) {
            return -1;
        }
        else {
            var alltime = end - start;
            var seconds = alltime / 1000;
            var minutes = Math.floor(seconds / 60);
            var hours = Math.floor(minutes / 60);
            var days = Math.floor(hours / 24);
            var CDay = days;
            var CHour = hours % 24;
            var CMinute = minutes % 60;
            var CSecond = Math.floor(seconds % 60);
            var str = "";
            if (CDay > 0) {
                str += CDay + "天";
            }
            if (CHour > 0) {
                str += CHour + "小时";
            }
            if (CMinute > 0) {
                str += CMinute + "分钟";
            }
            if (CSecond > 0) {
                str += CSecond + "秒";
            }
            return str;
        }
    };

    exports.getTime = GetTime;
    exports.getYear = GetYear;
    exports.getMonth = GetMonth;
    exports.getDay = GetDay;
    exports.getHours = GetHours;
    exports.getMinute = GetMinute;
    exports.getSecond = GetSecond;
    exports.timeSubMillisecond = TimeSubMillisecond;
    exports.timeSubNormal = TimeSubNormal;
});