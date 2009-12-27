package shane.factory;

import shane.dao.mysql.AbsMysqlDAO;

public abstract class AbsMyDAOFactory extends AbsFactory{

	public   AbsMysqlDAO getDAO(String name) {
		return null;
	}
	
}

