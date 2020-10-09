package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department department) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO Department (Id, Name) VALUES (?, ?)");

			st.setInt(1, department.getId());
			st.setString(2, department.getName());

			int rows = st.executeUpdate();

			if (rows > 0) {
				System.out.println("Sucess!");
			} 
			else {
				throw new DbException("Failed!");
			}

		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Department department) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Department SET Name = ? WHERE Id = ?");

			st.setString(1, department.getName());
			st.setInt(2, department.getId());

			int rows = st.executeUpdate();

			if (rows > 0) {
				System.out.println("Sucess!");
			} 
			else {
				throw new DbException("Failed!");
			}

		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deletByID(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM Department WHERE Id = ?");

			st.setInt(1, id);

			int rows = st.executeUpdate();

			if (rows > 0) {
				System.out.println("Sucess!");
			} 
			else {
				throw new DbException("Failed!");
			}

		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM Department WHERE Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
				return dep;
			} 
			else {
				throw new DbException("Failed");
			}

		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		List<Department> list = new ArrayList<>();

		try {
			st = conn.prepareStatement("SELECT * FROM Department");
			rs = st.executeQuery();

			while (rs.next()) {
				Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
				list.add(dep);
			}

			return list;
			
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
