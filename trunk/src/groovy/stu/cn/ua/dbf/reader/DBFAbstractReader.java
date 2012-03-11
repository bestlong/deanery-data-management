package stu.cn.ua.dbf.reader;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import stu.cn.ua.dbf.exception.DBException;
import stu.cn.ua.dbf.utils.DBFUtils;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.List;

/**
* @author evgeniy
*/
public abstract class DBFAbstractReader<DOMEN> {

    protected abstract void add(DOMEN d);
    protected abstract DOMEN createDomain();
    private static final Log log = LogFactory.getLog(DBFAbstractReader.class);

    private DOMEN domen;

    public void read(InputStream is) throws DBException, DBFException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        DBFField[] fields = null;
        DBFReader reader = null;

        try {
            if (is != null) {
                reader = new DBFReader(is);
                reader.setCharactersetName("866");
                int fieldCount = reader.getFieldCount();
                fields = new DBFField[fieldCount];
                for (int i = 0; i < fieldCount; i++){
                    fields[i] = reader.getField(i);
                }
            }
        } catch (DBFException e) {
            throw new DBException(e.getMessage());
        }
        Object[] rowObjects;
        while ((rowObjects = reader.nextRecord()) != null) {
            domen = createDomain();
            for (int i = 0; i < rowObjects.length; i++) {
                if (rowObjects[i] == null)
                    continue;
                invokeSet(fields[i].getName(), rowObjects[i], rowObjects[i].getClass());
            }
            add(domen);
        }
    }

    private void invokeSet(String field, Object o, Class clazz) throws DBException {
        Method m = null;
        try {
            m = domen.getClass().getMethod(DBFUtils.convertToJavaName(field, "set"), clazz);
        } catch (Exception e){
            log.info(MessageFormat.format("Couldn't find setter for field: {0}", field), e);
        }
        if (m == null){
            log.info(MessageFormat.format("Couldn't find setter for field: {0}", field));
            return;
        }
        try {
            m.invoke(domen, new Object[]{o});//invoke setter
        } catch (Exception e){
            log.error(MessageFormat.format("Error while invoking setter for field: {0}, method name: {1}", field, DBFUtils.convertToJavaName(field, "set")), e);
            throw new DBException(e);
        }
    }

    public abstract List<DOMEN> validate();

    public abstract int save();
}
