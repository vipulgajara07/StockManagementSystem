package stock;

class Product {
	String name;
	float price;
	int stock;
}

interface ProductCRUD {
	void addProduct();
	void updateProduct();
	void viewProduct();
	void deleteProduct();
}
