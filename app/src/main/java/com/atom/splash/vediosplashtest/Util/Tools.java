package com.atom.splash.vediosplashtest.Util;

/**
 * Created by atom on 2017/3/6.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 */
public class Tools {

    public static String strReplaceAll(String str){
        if(null != str && !"".equals(str)){
            return str.replaceAll("&nbsp;"," ").replaceAll("&amp","&").replaceAll("<br>","\n");
        }
        return null;
    }

    /**
     * 手机号验证
     * @param mobiles 手机号
     * @return (true:正确,false:错误)
     */
    public static boolean isMobileNo(String mobiles){
        Pattern pattern = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = pattern.matcher(mobiles);
        return m.matches();
    }

    /**
     * 密码设置验证
     * @param pwd  密码
     * @return (true:正确,false:错误)
     */
    public static boolean isRightPwd(String pwd){
        Pattern pattern = Pattern.compile("^(?![^a-zA-Z]+$)(?!\\D+$)[0-9a-zA-Z]{8,16}$");
        Matcher m = pattern.matcher(pwd);
        return m.matches();
    }

    /**
     * 邮箱格式验证
     * @param email 邮箱号
     * @return (true:正确,false:错误)
     */
    public static boolean isEmail(String email){
        if(null  == email || "".equals(email)){
            return false;
        }
        Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = pattern.matcher(email);
        return m.matches();
    }

    /**
     * 判断网址是否有效
     */
    public static boolean isLinkAvailable(String link) {
        Pattern pattern = Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(link);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
