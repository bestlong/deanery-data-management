package stu.cn.ua

/**
 * Created by IntelliJ IDEA.
 * User: evgeniy
 * Date: 20.08.11
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
class CommonUtils {

    public static String prepareString(String inp){
        return inp.replaceAll("'", "’").replaceAll("\"", "’");
    }
}
