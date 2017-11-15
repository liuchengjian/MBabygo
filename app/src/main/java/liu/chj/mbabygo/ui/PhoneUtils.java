package liu.chj.mbabygo.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：柳成建
 * 日期：2016/12/27 - 15:39
 * 注释：判断电话号码是否符合格式.密码格式
 */
public class PhoneUtils {
    public static boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }
    public static boolean isPassword(String inputText) {
        Pattern pa = Pattern.compile("^[0-9A-Za-z]{6,20}$");//字母或数字
//        Pattern pa = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$");//字母和数字
        Matcher ma = pa.matcher(inputText);
        return ma.matches();
    }
}
