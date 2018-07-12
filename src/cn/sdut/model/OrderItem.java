package cn.sdut.model;

public class OrderItem {
	
	private String orderId;
	private String productId;
	private Integer buynum;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getBuynum() {
		return buynum;
	}
	public void setBuynum(Integer buynum) {
		this.buynum = buynum;
	}
	
	@Override
	public String toString() {
		return "OrderItem [orderId=" + orderId + ", productId=" + productId + ", buynum=" + buynum + "]";
	}
	
	
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderItem(String orderId, String productId, Integer buynum) {
		this.orderId = orderId;
		this.productId = productId;
		this.buynum = buynum;
	}
	
	
	
	

}
