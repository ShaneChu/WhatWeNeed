package shane.view.helper;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shane.view.command.ICommand;


public abstract class AbsHelper implements IRequestHelper {

	private HttpServletResponse response;
	private HttpServletRequest request;

	protected Logger logger = Logger.getLogger(AbsHelper.class.getName());

	public AbsHelper(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public abstract ICommand getCommand();

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public HttpServletResponse getResponse() {
		return this.response;
	}
}
