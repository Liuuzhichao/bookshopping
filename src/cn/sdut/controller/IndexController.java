package cn.sdut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sdut.model.PageBean;
import cn.sdut.model.Products;
import cn.sdut.service.ProductService;

@Controller
public class IndexController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping("showIndex")
	public String showIndex(Model model, Integer page) {
		return showMainIndex(model,page);
	}
	
	//设置访问的主页面,添加分页功能
	@RequestMapping("/")
	public String showMainIndex(Model model, Integer page) {
		//查询商品
		//List<Products> productsList = productService.findProductsList();
		
		//获取商品的总记录数
		Integer count = productService.findCount();
		
		//创建pageBean的对象,设置页面的一些信息(每页显示的记录数,显示的页码,总记录数)
		PageBean pageBean = new PageBean(8,page,count);
		//System.out.println(pageBean);
		//调用具有分页功能的查询所有商品的方法
		List<Products> productsList = productService.findProductsListPage(pageBean);
		//传参
		model.addAttribute("pageBean", pageBean);
		
		//将查询到的商品在首页面中进行展示
		model.addAttribute("productsList",productsList);
		
		return "index";
	}
	
	//后台管理界面
	@RequestMapping("showAdminIndex")
	public String showAdminIndex() {
		return "admin/index";
	}
	
	//查看详情
	@RequestMapping("showProductInformation")
	public String showProductInformation(String id, Model model) {
		Products product = productService.findProductById(id);
		model.addAttribute("product", product);
		return "productinfo";
	}
	
}
