package cn.sdut.model;
/**
 * 分页的实体类
 * @author Administrator
 *
 */

public class PageBean {

	//每一页中显示的记录数
	private Integer pageSize;
	//显示的页数
	private Integer page;
	//总记录数
	private Integer count;
	//总页数
	private Integer pageCount;
	//偏移量
	private Integer pageNum;
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		if(page==null || page<1) {
			this.page = 1;
		} else if (page>pageCount) {
			this.page = pageCount;
		} else {
			this.page = page;
		}
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	
	//总页数=总记录数/每页显示的记录数
	public void setPageCount() {
		if(count%pageSize==0) {
			pageCount = count/pageSize;
		} else {
			pageCount = count/pageSize+1;
		}
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum() {
		pageNum = (page-1)*pageSize;
	}
	
	
	@Override
	public String toString() {
		return "PageBean [pageSize=" + pageSize + ", page=" + page + ", count=" + count + ", pageCount=" + pageCount
				+ ", pageNum=" + pageNum + "]";
	}
	
	public PageBean() {
	}
	
	/**
	 * 
	 * @param pageSize  每页显示的记录数
	 * @param page		显示的页码
	 * @param count		总记录数
	 */
	public PageBean(Integer pageSize, Integer page, Integer count) {
		this.pageSize = pageSize;
		this.count = count;
		setPageCount();
		setPage(page);
		setPageNum();
	}
	
	
	
}
