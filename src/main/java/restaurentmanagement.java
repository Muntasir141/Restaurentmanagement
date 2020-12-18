//Restaurentmanagement//
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;



public class restaurentmanagement{
public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scn = new Scanner(System.in);
        int choice = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try( Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant?", "root", "")) 
            {
           
               do {
                    System.out.println("Restaurant Management Server");
                    System.out.println("   ====================");
                    System.out.println("Enter a Number from One to Five for this Databse");
                    System.out.println("_____________________________________");
                    System.out.println("Enter '1' For Adding new Iteam");
                    System.out.println("");
                    System.out.println("Enter '2' For Show The Menu");
                    System.out.println("");
                    System.out.println("Enter '3' For Updating Menu");
                    System.out.println("");
                    System.out.println("Enter '4' For Deleting Any Iteam");
                    System.out.println("");
                    System.out.println("Enter '5' To Exit.");
                    System.out.println("");
                    System.out.print("Enter a Number Here: ");
                    choice = scn.nextInt();
                    switch (choice) {
                        case 1:
                            try {
                            System.out.println("Enter Menu Code : ");
                            int Code = scn.nextInt();
                            System.out.println("Enter The Menu Name : ");
                            String Menu_Name = scn.next();
                            System.out.println("Enter The Menu Catagory : ");
                            String Catagory_Name = scn.next();
                            System.out.println("Enter The Price : ");
                            int Price = scn.nextInt();
                            String sql = "insert into info values (?,?,?,?);";
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setInt(1, Code);
                            stmt.setString(2, Menu_Name);
                            stmt.setString(3,Catagory_Name);
                            stmt.setInt(4,Price);
                            stmt.execute();
                            System.out.println("New Menu Added Successfully!");
                            System.out.println("");
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 2:
                            try {
                            System.out.println("Enter Your Menu Code to see the Data : ");
                            Scanner input = new Scanner(System.in);
                            int Code = input.nextInt();
                            String sql = "select * from info where Code=?;";
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setInt(1, Code);
                            ResultSet rs = stmt.executeQuery();
                            while (rs.next()) {

                                System.out.println("Menu Code No = " + rs.getInt(1));
                                System.out.println("Menu Name is = " + rs.getString(2));
                                System.out.println("Menu Catagory is = " + rs.getString(3));
                                System.out.println("Price = " + rs.getInt(4));
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 3:
                            try {
                            System.out.println("Enter Name To Update : ");
                            String Menu_Name = scn.next();
                            System.out.println("Enter New Catagory : ");
                            String Catagory_Name = scn.next();
                            String sql = "update info set Catagory_Name = ? where Menu_Name = ?";
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setString(1, Catagory_Name);
                            statement.setString(2, Menu_Name);
                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Record Updated Successfully!");
                            } else {
                                System.out.println("There is no Such Record Founded in This Database, Try Again!");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                        case 4:
                            try {
                            System.out.println("Enter Menu Name for Deleting the Data : ");
                            String Menu_Name = scn.next();
                            String sql = "delete from info where Menu_Name = ?;";
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setString(1,Menu_Name);
                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Data Deleted Successfully!");
                            } else {
                                System.out.println("There is no Such Record Founded in This Database, Try Again!");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 5:
                            System.out.println("");
                            break;
                        default:
                            System.out.println("Please Enter '1-5' Option!");
                            break;
                    }

                } while (choice != 5);
                System.out.println("Thanks, For Using The Database!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
    }
}