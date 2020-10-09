package db;

import java.sql.Connection;

import model.dao.impl.DepartmentDaoJDBC;

public class DaoFactory {
	
	public static DepartmentDaoJDBC createDepartmentDao(Connection conn) {
		return new DepartmentDaoJDBC(conn);
	}

}
