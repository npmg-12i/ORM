package edu.npmg.accessdb.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TestAccess {

	public static void main(String[] args) {
		
		/*DBAccessQueryProvider provider = 
				new DBAccessQueryProvider("C:\\Users\\Toncata\\Downloads\\HospitalDB_12i_14.mdb");
		ResultSet result = provider.selectQuery("SELECT Doctors.ID AS ID, FirstName, LastName, HeadOfDepartment, Name "
				+ "FROM Doctors LEFT JOIN Specialities ON Doctors.Specialty_ID = Specialities.ID");
		
		List<Doctor> doctors = new ArrayList<>();

		
		try {
			while(result.next())
			{
				int id = result.getInt("ID");
				String firstName = result.getString("FirstName");
				String lastName = result.getString("LastName");
				boolean isHeadOfDepartment = result.getBoolean("HeadOfDepartment");
				String specialityName = result.getString("Name");
				
				doctors.add(new Doctor(id, firstName, lastName, 
						isHeadOfDepartment, specialityName));*/
				/*System.out.printf("name: %s %s head of department: %b speciality: %s\n",
						firstName, lastName, isHeadOfDepartment, specialityName);*/
		/*	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFrame mainWindow = new JFrame("Hospital");
		mainWindow.setBounds(0, 0, 860, 640);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.setLayout(null);
		
		JList<Doctor> lstDoctors = 
			new JList<>();
		DefaultListModel<Doctor> model = new DefaultListModel<>();
		doctors.forEach(d -> model.addElement(d));
		
		lstDoctors.setModel(model);
		/*lstDoctors.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				Doctor selected = lstDoctors.getSelectedValue();
				System.out.println(selected);
			}
			
		});
		*/
		/*JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(550, 20, 75, 30);
		btnDelete.setVisible(true);
		btnDelete.addActionListener(e->{
			int selected = lstDoctors.getSelectedIndex();
			Doctor selectedDoctor = lstDoctors.getSelectedValue();
			((DefaultListModel<Doctor>)lstDoctors.getModel()).remove(selected);
			provider.tableChangeQuery("DELETE FROM Doctors WHERE ID=?", ""+selectedDoctor.getId());
		});
		//lstDoctors.setBounds(20, 20, 500, 300);
		JScrollPane scroll = new JScrollPane(lstDoctors);
		scroll.setBounds(20, 20, 500, 300);
		mainWindow.add(scroll);
		mainWindow.add(btnDelete);
		*/
		
		
		//doctors.forEach(System.out::println);
	}

}
