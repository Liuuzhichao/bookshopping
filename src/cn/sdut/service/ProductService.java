package cn.sdut.service;

import java.util.List;

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
	public List<Products> findProductsListByType(String type);

}
