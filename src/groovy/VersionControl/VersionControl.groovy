package VersionControl

/**
 * Created by IntelliJ IDEA.
 * User: zim
 * Date: 05.07.11
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
class VersionControl
{
     static def index = {
	            response.contentType='text/plain'
	            def version = grailsApplication.metadata['app.version']
	            response.writer<<version;
	            response.writer.flush();
	    }
}
