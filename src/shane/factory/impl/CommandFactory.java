package shane.factory.impl;

import java.util.Properties;

import shane.dao.mysql.AbsMysqlDAO;
import shane.factory.AbsFactory;
import shane.factory.IFactory;
import shane.view.command.ICommand;


public class CommandFactory extends AbsFactory {
	
	final String FILE_NAME = "shane.properties.command";
	private static CommandFactory commandFactory=null;

	public static CommandFactory newInstance() {
		if(commandFactory==null)
			commandFactory=new CommandFactory();
		return commandFactory;
	}

	public AbsMysqlDAO getDAO(String name) {
		return null;
	}

	public ICommand getCommand(String commName) {
		
		String className =commName;
	    ICommand command=null;
	    try {
	    	command = (ICommand) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return command;
	}

}
