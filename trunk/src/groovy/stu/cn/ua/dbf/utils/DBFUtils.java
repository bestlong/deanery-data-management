package stu.cn.ua.dbf.utils;

import org.apache.log4j.Logger;
import stu.cn.ua.dbf.exception.DBException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author evgeniy
 */
public class DBFUtils {
    private static Logger log = Logger.getLogger(DBFUtils.class);

    public static String convertToJavaName(String fieldName, String prefix) {
        String s = convertToJavaName(fieldName);
        if (prefix != null) {
            s = prefix + s.substring(0, 1).toUpperCase() + s.substring(1);
        }
        return s;
    }

    public static String convertToJavaName(String fieldName) {
        String buffer;
        int index;
        buffer = fieldName.toLowerCase();
        index = buffer.indexOf("_");
        while (index != -1) {
            if (index == buffer.length()-1){
                buffer = buffer.replaceAll("_", "");
                break;
            }
            Character c = buffer.charAt(index + 1);
            String arr[] = buffer.split("_" + c);
            buffer = arr[0] + c.toString().toUpperCase();
            if (arr.length > 1){
                buffer += arr[1];
                for (int i = 2; i < arr.length; i++) {
                    buffer += c.toString().toUpperCase() + arr[i];
                }
            }
            index = buffer.indexOf("_");
        }
        return buffer;

    }


}
