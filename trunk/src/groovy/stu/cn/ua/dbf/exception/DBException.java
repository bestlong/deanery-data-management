package stu.cn.ua.dbf.exception;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 12.02.11
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public class DBException extends java.io.IOException {

	private static final long serialVersionUID = 1L;

	public DBException() {
		super();
	}

	public DBException(String msg) {
		super(msg);
	}

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause) {
        super(cause);
    }
}