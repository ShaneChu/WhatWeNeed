package shane.factory;

import java.io.FileNotFoundException;
import java.util.Properties;

import shane.common.PropertiesManager;
import shane.dao.mysql.AbsMysqlDAO;
import shane.view.command.ICommand;


public class AbsFactory implements IFactory {

	public ICommand getCommand(String commName) {
		return null;
	}

	public AbsMysqlDAO getDAO(String name) {
		return null;
	}
	/**
	 * 根据资源文件的名称，获得该文件解析完之后的键值对的集合 Properties类型 的对象 
	 * @param fileName
	 * @return
	 */
	public Properties getPropertiesByName(String fileName){
		Properties properties = null;
		try {
			properties = PropertiesManager.getProperties(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		return properties;
		
		
	}

}
