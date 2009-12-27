package shane.view.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shane.view.command.ICommand;

/**
 * ��ͼ���ֽӿڣ�����������ҳ��Ķ�����з�װ
 * @author shane
 *
 */

public interface IRequestHelper {

	public HttpServletRequest getRequest();

	public HttpServletResponse getResponse();


	public ICommand getCommand();
}

