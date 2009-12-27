package shane.view.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shane.view.command.ICommand;

/**
 * 视图助手接口，用来帮助将页面的对象进行封装
 * @author shane
 *
 */

public interface IRequestHelper {

	public HttpServletRequest getRequest();

	public HttpServletResponse getResponse();


	public ICommand getCommand();
}

