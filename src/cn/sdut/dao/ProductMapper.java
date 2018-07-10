package cn.sdut.dao;

import java.util.List;

import cn.sdut.model.Products;

public interface ProductMapper {

	/**
	 * 添加商品
	 * @param 商品
	 * @return
	 */
	public int saveProduct(Products pro);
	
	/**
	 * 删除商品
	 * @param 商品ID
	 * @return
	 */
	public int delProduct(String id);
	
	
	/**
	 * 修改商品
	 * @param 商品
	 * @return
	 */
	public int updateProduct(Products pro);
	
	/**
	 * 根据ID查询商品
	 * @param 商品id
	 * @return
	 */
	public Products findProductById(String id);
	
	/**
	 * 查询所有商品
	 * @return
	 */
	public List<Products> findProductsList();
	
	/**
	 * 根据类别查询商品
	 * @return
	 */
	public List<Products> findProductsListByType(String type);
}
