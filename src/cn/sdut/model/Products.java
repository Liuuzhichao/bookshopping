package cn.sdut.model;

public class Products {

	private String id;
	private String name;
	private long price;//以分为单位,double会精度缺失
	private String category;//商品种类
	private Integer pnum; //商品数量
	private String imgurl;//图片路径
	private String description;//商品描述
	private Integer state; //商品状态
	private String cbtime;//出版日期
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCbtime() {
		return cbtime;
	}
	public void setCbtime(String cbtime) {
		this.cbtime = cbtime;
	}
	
	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", pnum="
				+ pnum + ", imgurl=" + imgurl + ", description=" + description + ", state=" + state + ", cbtime="
				+ cbtime + "]";
	}
	
	
}
