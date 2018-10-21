package microservice;



public class Order {
	
	
    private Long id;
	
	
	
    private Long userId;
	
	
	
    private String productName;
	

	
    private int price;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long userId, String productName, int price) {
		super();
		this.userId = userId;
		this.productName = productName;
		this.price = price;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
	
	

}
