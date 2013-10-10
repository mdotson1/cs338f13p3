package com.cs388f13p2.database;

import java.util.Iterator;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cs388f13p2.model.person.ContactInformation;
import com.cs388f13p2.model.person.Student;

public class StudentDAO {
	
	public StudentDAO() {
		
	}
	
	public Student findStudentById(int id) {
		
		try {
			Statement st = DBHelper.getConnection().createStatement();
			
			String selectStudentQuery = "SELECT customerID, lname, fname FROM Customer WHERE customerID = '" + id + "'";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addStudent(Student obj) throws SQLException {
		
		Connection c = DBHelper.getConnection();
		Statement st = c.createStatement();
		
		DatabaseMetaData dbm = c.getMetaData();
		
		ResultSet tables = dbm.getTables(null, null, "Student", null);
		if (!tables.next()) {
			// Table does not exist
			String createTableStatement = "CREATE TABLE Student(" +
					"id INT NOT NULL," +
					"dateOfBirth VARCHAR(10)," +
					"homeAddress VARCHAR(200)," +
					"workAddress VARCHAR(200)," + 
					"lastName VARCHAR(30)," +
					"firstName VARCHAR(20)," +
					"workPhone CHAR(13)," +
					"homePhone CHAR(13)," +
					"cellPhone CHAR(13)," +
					"currentBalance VARCHAR(20)," +
					"PRIMARY KEY ( id ) " +
					");";
			st.execute(createTableStatement);
		}
		
		ContactInformation ci = obj.getContactInformation();
		
		String insertStudentStatement = "INSERT INTO Student(id, dateOfBirth, homeAddress, workAddress, " +
				"lastName, firstName, workPhone, homePhone, cellPhone, currentBalance) VALUES(" + (obj.getId()+5) +  ", '" + 
				obj.getDateOfBirth() + "', '" + ci.getHomeAddress() + "', '" + ci.getWorkAddress() + "', '" +
				ci.getFirstName() + "', '" + ci.getLastName() + "', '" + ci.getWorkPhone() + "', " + 
				ci.getHomePhone() + ", '" + ci.getCellPhone() + "', " + obj.getCurrentBalance() + ");";
		
		System.out.println(insertStudentStatement);
		st.execute(insertStudentStatement);
		
		st.close();
	}
	
	/*
	public Customer getCustomer(String customerId) {
	 	 
	    try { 		
	    	//Get Customer
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectCustomerQuery = "SELECT customerID, lname, fname FROM Customer WHERE customerID = '" + customerId + "'";

	    	ResultSet custRS = st.executeQuery(selectCustomerQuery);      
	    	System.out.println("CustomerDAO: *************** Query " + selectCustomerQuery);
	    	
	      //Get Customer
    	  Customer customer = new Customer();
	      while ( custRS.next() ) {
	    	  customer.setCustomerId(custRS.getString("customerID"));
	    	  customer.setLastName(custRS.getString("lname"));
	    	  customer.setFirstName(custRS.getString("fname"));
	      }
	      //close to manage resources
	      custRS.close();
	      	    		  
	      //Get Address
	      String selectAddressQuery = "SELECT addressID, street, unit, city, state, zip FROM Address WHERE customerID = '" + customerId + "'";
	      ResultSet addRS = st.executeQuery(selectAddressQuery);
    	  Address address = new Address();
    	  
    	  System.out.println("CustomerDAO: *************** Query " + selectAddressQuery);
    	  
	      while ( addRS.next() ) {
	    	  address.setAddressId(addRS.getString("addressid"));
	    	  address.setStreet(addRS.getString("street"));
	    	  address.setUnit(addRS.getString("unit"));
	    	  address.setCity(addRS.getString("city"));
	    	  address.setState(addRS.getString("state"));
	    	  address.setZip(addRS.getString("zip"));
	      }
	      
	      customer.setBillingAddress(address);
	      //close to manage resources
	      addRS.close();
	      st.close();
	      
	      return customer;
	    }	    
	    catch (SQLException se) {
	      System.err.println("CustomerDAO: Threw a SQLException retrieving the customer object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }
	
	public void addCustomer(Customer cust) {
		Connection con = DBHelper.getConnection();
        PreparedStatement custPst = null;
        PreparedStatement addPst = null;

        try {
        	//Insert the customer object
            String custStm = "INSERT INTO Customer(customerID, lname, fname) VALUES(?, ?, ?)";
            custPst = con.prepareStatement(custStm);
            custPst.setString(1, cust.getCustomerId());
            custPst.setString(2, cust.getLastName());       
            custPst.setString(3, cust.getFirstName()); 
            custPst.executeUpdate();

        	//Insert the customer address object
            String addStm = "INSERT INTO Address(customerID, addressID, street, unit, city, state, zip) VALUES(?, ?, ?, ?, ?, ?, ?)";
            addPst = con.prepareStatement(addStm);
            addPst.setString(1, cust.getCustomerId());
            addPst.setString(2, cust.getBillingAddress().getAddressId());  
            addPst.setString(3, cust.getBillingAddress().getStreet());       
            addPst.setString(4, cust.getBillingAddress().getUnit());  
            addPst.setString(5, cust.getBillingAddress().getCity());  
            addPst.setString(6, cust.getBillingAddress().getState());      
            addPst.setString(7, cust.getBillingAddress().getZip());  
            addPst.executeUpdate();
        } catch (SQLException ex) {

        } finally {

            try {
                if (addPst != null) {
                	addPst.close();
                	custPst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("CustomerDAO: Threw a SQLException saving the customer object.");
    	      System.err.println(ex.getMessage());
            }
        }
    }
    */

	public Iterator<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateStudent(int id, Student newObj) {
		// TODO Auto-generated method stub
		
	}

	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
