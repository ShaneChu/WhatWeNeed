package shane.factory;

import shane.dao.mysql.AbsMysqlDAO;
import shane.view.command.ICommand;

public interface IFactory {

	public AbsMysqlDAO getDAO(String name);
	public ICommand getCommand(String commName);
       

}

