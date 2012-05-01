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
        return inp.replaceAll("'", "’").replaceAll("\"", "’").trim();
    }

    public static def wordToCSV(def buf) {
        String buf1;
        if (buf == null) {buf1 = new String("\"\";");}
        else {

            buf1 = buf.toString().trim();
            buf1 = buf1.replace("\"", "\"\"");
            buf1 = "\"${buf1}\";";
        }
        return buf1;

    }


}
