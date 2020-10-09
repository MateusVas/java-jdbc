package application;

import java.sql.Connection;
import java.util.List;
import db.DB;
import db.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = DB.getConnection();
		DepartmentDao dep = DaoFactory.createDepartmentDao(conn);
		
		System.out.println("\n===INSERT_DEPARTMENT====");
		Department dep6 = new Department(6, "Food");
		Department dep7 = new Department(7,"Electronics");
		dep.insert(dep6);
		dep.insert(dep7);
		
		System.out.println("\n===UPDATE_DEPARTMENT===");
		dep6.setName("Books");
		dep.update(dep6);
		
		System.out.println("\n===DELETE_DEPARTMENT===");
		dep.deletByID(dep7.getId());
		
		System.out.println("\n==FINDBYID===");
		Department depFind = dep.findById(6);
		System.out.println(depFind);
		
		System.out.println("\n==FINDBYALL===");
		List<Department> list = dep.findAll();
		list.forEach(System.out::println);
		
		
	}

}
