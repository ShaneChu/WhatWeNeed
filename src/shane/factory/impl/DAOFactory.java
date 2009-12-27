package shane.factory.impl;

import java.io.FileNotFoundException;
import java.util.Properties;

import shane.common.PropertiesManager;
import shane.dao.mysql.AbsMysqlDAO;
import shane.factory.AbsMyDAOFactory;
import shane.view.command.ICommand;


public class DAOFactory extends AbsMyDAOFactory {
	
	final String FILE_NAME = "shane.properties.dao_classes";
	
	public  AbsMysqlDAO getDAO(String name) {
		AbsMysqlDAO dao = null;
		try {
			Properties properties=null;
    	
			properties=	this.getPropertiesByName(FILE_NAME);
    		//根据参数获得相应DAO的实现类的名称
    		String className = properties.getProperty(name);
			dao = (AbsMysqlDAO) Class.forName(className).newInstance();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		}
	return dao;
	}
	
	public ICommand getCommand(String commName) {
		return null;
	}

}
