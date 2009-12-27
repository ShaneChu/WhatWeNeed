package shane.view.helper.impl;

import java.io.FileNotFoundException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shane.common.PropertiesManager;
import shane.factory.impl.CommandFactory;
import shane.view.command.ICommand;
import shane.view.helper.AbsHelper;


public class RequestHelperImpl extends AbsHelper {

	private String action;

	public RequestHelperImpl(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		action = request.getParameter("action");
	}
	
	private String getCommandName() {
		String className="";
		Properties properties;
		try {
			properties = PropertiesManager.getProperties("shane.properties.command");
			className=(String) properties.get(action);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return className;
		
	}

	public ICommand getCommand() {
		String className = this.getCommandName();
		CommandFactory commandFactory = new CommandFactory();
		ICommand cmd = commandFactory.getCommand(className);
		return cmd;
	}
}
