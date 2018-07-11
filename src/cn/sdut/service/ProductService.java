package cn.sdut.service;

import java.util.List;

import cn.sdut.model.PageBean;
import cn.sdut.model.Products;

public interface ProductService {
	
	public int saveProduct(Products pro);
	
	public int delProduct(String id);
	
	public int updateProduct(Products pro);
	
	public Products findProductById(String id);
	
	public List<Products> findProductsList();
	
	/**
	 * 根据类别查询商品
	 * @return
	 */
	public List<Products> findProductsListByType(String type, PageBean pageBean);

	/**
	 * 获取商品的总记录数
	 * @return
	 */
	public Integer findCount();
	
	/**
	 * 进行商品分页的查询方法
	 * @param page
	 * @return
	 */
	public List<Products> findProductsListPage(PageBean page);

	
	
	/**
	 * 查询某一类别商品的总数量
	 */
	public int findCountByType(String type);
}
