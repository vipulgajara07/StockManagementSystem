package stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class CRUDProduct implements ProductCRUD {

	Scanner sc = new Scanner(System.in);
	int choice = 0;
	String itemName = new String();
	Product p = new Product();
	DatabaseConnect dc = new DatabaseConnect();
	
	@Override
	public void addProduct() {

		while (choice != 2) {
			System.out.println("---------Add Product---------");
			System.out.println();
			System.out.println("Enter Product Name");
			p.name = sc.next();

			try {
				if (checkExistance()) {
					System.out.println("The Product Name Already Exists!");
				} else {
					System.out.println("Enter Product Price");
					p.price = sc.nextFloat();

					System.out.println("Enter Product Stock");
					p.stock = sc.nextInt();

					// product details inserted database
					PreparedStatement ps = dc.conn.prepareStatement("insert into products(name,stock,price) values(?,?,?)");
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

				askAgain();
				choice = sc.nextInt();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void updateProduct() {
		
		while (choice != 2) {
			System.out.println("---------Update Product---------");
			System.out.println();
			System.out.println("Enter Product Name");
			p.name = sc.next();

			// check whether product name already exists
			try {
				if (!checkExistance()) {
					System.out.println("The Product Doesn't Exists!");
				} else {
					System.out.println("Enter Product Price");
					p.price = sc.nextFloat();

					System.out.println("Enter Product Stock");
					p.stock = sc.nextInt();

					// product details updated in database
					PreparedStatement ps = dc.conn
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

				askAgain();
				choice = sc.nextInt();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void viewProduct() {

		while (choice != 2) {
			System.out.println("---------View Product---------");
			System.out.println();
			System.out.println("Enter Product Name");
			itemName = sc.next();

			// Fetch data for required product from database
			try {
				PreparedStatement ps1 = dc.conn.prepareStatement("select * from products where name = ?");
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

				askAgain();
				choice = sc.nextInt();

			} catch (Exception e) {
				System.out.println("Hello");
				System.out.println(e);
			}
		}
	}

	@Override
	public void deleteProduct() {

		while (choice != 2) {
			System.out.println("---------Delete Product---------");
			System.out.println();
			System.out.println("Enter Product Name");
			p.name = sc.next();

			// check whether product name already exists
			try {
				if (!checkExistance()) {
					System.out.println("The Product Doesn't Exists!");
				} else {

					// product details deleted from database
					PreparedStatement ps = dc.conn.prepareStatement("delete from products where name=?");
					ps.setString(1, p.name);
					int x = ps.executeUpdate();

					if (x > 0) {
						System.out.println("Product Deleted successfully!");
					} else {
						System.out.println("Product Deletion failed!");
					}
				}

				askAgain();
				choice = sc.nextInt();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	void askAgain() {
		System.out.println("Do you want to delete more product?");
		System.out.println("1) Yes");
		System.out.println("2) No");
	}
	
	boolean checkExistance() {
		try {
			PreparedStatement ps1 = dc.conn.prepareStatement("select * from products where name = ?");
			ps1.setString(1, p.name);
			ResultSet rs = ps1.executeQuery();
			
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
}
