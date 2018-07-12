package cn.sdut.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sdut.dao.ProductMapper;
import cn.sdut.model.PageBean;
import cn.sdut.model.Products;
import cn.sdut.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper mapper;
	@Override
	public int saveProduct(Products pro) {
		// TODO Auto-generated method stub
		return mapper.saveProduct(pro);
	}

	@Override
	public int delProduct(String id) {
		// TODO Auto-generated method stub
		return mapper.delProduct(id);
	}

	@Override
	public int updateProduct(Products pro) {
		// TODO Auto-generated method stub
		return mapper.updateProduct(pro);
	}

	@Override
	public Products findProductById(String id) {
		// TODO Auto-generated method stub
		return mapper.findProductById(id);
	}

	@Override
	public List<Products> findProductsList() {
		// TODO Auto-generated method stub
		return mapper.findProductsList();
	}

	@Override
	public List<Products> findProductsListByType(String type, PageBean pageBean) {
		// TODO Auto-generated method stub
		return mapper.findProductsListByType(type,pageBean);
	}

	@Override
	public Integer findCount() {
		// TODO Auto-generated method stub
		return mapper.findCount();
	}

	@Override
	public List<Products> findProductsListPage(PageBean page) {
		// TODO Auto-generated method stub
		return mapper.findProductsListPage(page);
	}

	@Override
	public int findCountByType(String type) {
		// TODO Auto-generated method stub
		return mapper.findCountByType(type);
	}

}
