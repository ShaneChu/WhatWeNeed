package shane.view.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shane.view.helper.IRequestHelper;


public interface ICommand {
	public String execute(HttpServletRequest request,HttpServletResponse response);

	public String execute(IRequestHelper helper);

}
