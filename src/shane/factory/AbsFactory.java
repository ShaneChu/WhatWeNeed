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
	 * ������Դ�ļ������ƣ���ø��ļ�������֮��ļ�ֵ�Եļ��� Properties���� �Ķ��� 
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
