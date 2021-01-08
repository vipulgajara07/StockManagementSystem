package stock;

import java.sql.*;
import java.util.Scanner;

class Product {
	String name;
	float price;
	int stock;
}

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int choice = 0;

		while (choice != 4) {
			System.out.println("---------STOCK MANAGEMENT SYSTEM---------");
			System.out.println();
			System.out.println("1) Products");
			System.out.println("2) Purchase");
			System.out.println("3) Sale");
			System.out.println("4) Exit");
			System.out.println("Enter your choice :-");

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				products();
				break;
			case 2:
				purchase();
				break;
			case 3:
				sale();
				break;
			case 4:
				System.out.println("Thanks you for using System!");
				break;
			default:
				System.out.println("Please enter valid choice!");
			}
		}
	}

	static void products() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;

		while (choice != 5) {
			System.out.println("---------Products---------");
			System.out.println();
			System.out.println("1) Add");
			System.out.println("2) Update");
			System.out.println("3) View");
			System.out.println("4) Delete");
			System.out.println("5) Exit");
			System.out.println("Enter your choice :-");

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				addProduct();
				break;
			case 2:
				updateProduct();
				break;
			case 3:
				viewProduct();
				break;
			case 4:
				deleteProduct();
				break;
			case 5:
				break;
			default:
				System.out.println("Please enter valid choice!");
			}
		}
	}

	static void purchase() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		String itemName = new String();

		while (choice != 2) {
			System.out.println("---------Purchase Product---------");
			System.out.println();
			System.out.println("Enter Product Name");
			itemName = sc.next();
			int stock;

			// check whether product exists in database
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagementsystem",
						"root", "");
				PreparedStatement ps1 = conn.prepareStatement("select * from products where name = ?");
				ps1.setString(1, itemName);
				ResultSet rs = ps1.executeQuery();

				if (rs.next()) {
					System.out.println("How many quantity you purchased?");
					stock = sc.nextInt();

					// product stock updated in database
					PreparedStatement ps = conn
							.prepareStatement("update products set stock = stock + ? where name = ?");
					ps.setString(2, itemName);
					ps.setInt(1, stock);
					int x = ps.executeUpdate();

					if (x > 0) {
						System.out.println("Product Purchased successfully!");
					} else {
						System.out.println("Product Purchase failed!");
					}
				} else {
					System.out.println("The Product Doesn't Exists!");
				}

				System.out.println("Do you want to purchase more product?");
				System.out.println("1) Yes");
				System.out.println("2) No");

				choice = sc.nextInt();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	static void sale() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		String itemName = new String();

		while (choice != 2) {
			System.out.println("---------Sale Product---------");
			System.out.println();
			System.out.println("Enter Product Name");
			itemName = sc.next();
			int stock;

			// check whether product exists in database
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagementsystem",
						"root", "");
				PreparedStatement ps1 = conn.prepareStatement("select * from products where name = ?");
				ps1.setString(1, itemName);
				ResultSet rs = ps1.executeQuery();

				if (rs.next()) {
					System.out.println("How many quantity you want to sell?");
					stock = sc.nextInt();

					// product stock updated in database
					PreparedStatement ps = conn
							.prepareStatement("update products set stock = stock - ? where name = ?");
					ps.setString(2, itemName);
					ps.setInt(1, stock);
					int x = ps.executeUpdate();

					if (x > 0) {
						System.out.println("Product Sold successfully!");
					} else {
						System.out.println("Product Selling failed!");
					}
				} else {
					System.out.println("The Product Doesn't Exists!");
				}

				System.out.println("Do you want to sell more product?");
				System.out.println("1) Yes");
				System.out.println("2) No");

				choice = sc.nextInt();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	static void addProduct() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		Product p = new Product();

		while (choice != 2) {
			System.out.println("---------Add Product---------");
			System.out.println();
			System.out.println("Enter Product Name");
			p.name = sc.next();

			// check whether product name already exists
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagementsystem",
						"root", "");
				PreparedStatement ps1 = conn.prepareStatement("select * from products where name = ?");
				ps1.setString(1, p.name);
				ResultSet rs = ps1.executeQuery();

				if (rs.next()) {
					System.out.println("The Product Name Already Exists!");
				} else {
					System.out.println("Enter Product Price");
					p.price = sc.nextFloat();

					System.out.println("Enter Product Stock");
					p.stock = sc.nextInt();

					// product details inserted database
					PreparedStatement ps = conn
							.prepareStatement("insert into products(name,stock,price) values(?,?,?)");
					ps.setString(1, p.name);
					ps.setInt(2, p.stock);
					ps.setFloat(3, p.price);
					int x = ps.executeUpdate();

					if (x > 0) {
						System.out.println("Product Entered successfully!");
					} else {
						System.out.println("Product Entry failed!");
					}
				}

				System.out.println("Do you want to add more product?");
				System.out.println("1) Yes");
				System.out.println("2) No");

				choice = sc.nextInt();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	static void updateProduct() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		Product p = new Product();

		while (choice != 2) {
			System.out.println("---------Update Product---------");
			System.out.println();
			System.out.println("Enter Product Name");
			p.name = sc.next();

			// check whether product name already exists
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagementsystem",
						"root", "");
				PreparedStatement ps1 = conn.prepareStatement("select * from products where name = ?");
				ps1.setString(1, p.name);
				ResultSet rs = ps1.executeQuery();

				if (!rs.next()) {
					System.out.println("The Product Doesn't Exists!");
				} else {
					System.out.println("Enter Product Price");
					p.price = sc.nextFloat();

					System.out.println("Enter Product Stock");
					p.stock = sc.nextInt();

					// product details updated in database
					PreparedStatement ps = conn
							.prepareStatement("update products set price = ?, stock = ? where name = ?");
					ps.setString(3, p.name);
					ps.setInt(2, p.stock);
					ps.setFloat(1, p.price);
					int x = ps.executeUpdate();

					if (x > 0) {
						System.out.println("Product Updated successfully!");
					} else {
						System.out.println("Product Updation failed!");
					}
				}

				System.out.println("Do you want to add more product?");
				System.out.println("1) Yes");
				System.out.println("2) No");

				choice = sc.nextInt();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	static void viewProduct() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		String itemName = new String();

		while (choice != 2) {
			System.out.println("---------View Product---------");
			System.out.println();
			System.out.println("Enter Product Name");
			itemName = sc.next();
			Product p = new Product();

			// Fetch data for required product from database
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagementsystem",
						"root", "");
				PreparedStatement ps1 = conn.prepareStatement("select * from products where name = ?");
				ps1.setString(1, itemName);
				ResultSet rs = ps1.executeQuery();

				if (rs.next()) {
					p.name = itemName;
					p.price = rs.getFloat("price");
					p.stock = rs.getInt("stock");

					System.out.println("Product details :- ");
					System.out.println("Name - " + p.name);
					System.out.println("Price - " + p.price);
					System.out.println("Stock - " + p.stock);
				} else {
					System.out.println("The Product Doesn't Exists!");
				}

				System.out.println("Do you want to view more product?");
				System.out.println("1) Yes");
				System.out.println("2) No");

				choice = sc.nextInt();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	static void deleteProduct() {

		Scanner sc = new Scanner(System.in);
		int choice = 0;
		String itemName = new String();

		while (choice != 2) {
			System.out.println("---------Delete Product---------");
			System.out.println();
			System.out.println("Enter Product Name");
			itemName = sc.next();

			// check whether product name already exists
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagementsystem",
						"root", "");
				PreparedStatement ps1 = conn.prepareStatement("select * from products where name = ?");
				ps1.setString(1, itemName);
				ResultSet rs = ps1.executeQuery();

				if (!rs.next()) {
					System.out.println("The Product Doesn't Exists!");
				} else {

					// product details deleted from database
					PreparedStatement ps = conn.prepareStatement("delete from products where name=?");
					ps.setString(1, itemName);
					int x = ps.executeUpdate();

					if (x > 0) {
						System.out.println("Product Deleted successfully!");
					} else {
						System.out.println("Product Deletion failed!");
					}
				}

				System.out.println("Do you want to delete more product?");
				System.out.println("1) Yes");
				System.out.println("2) No");

				choice = sc.nextInt();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}