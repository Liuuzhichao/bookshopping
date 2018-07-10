package cn.sdut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sdut.model.Products;
import cn.sdut.service.ProductService;

@Controller
public class IndexController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping("showIndex")
	public String showIndex(Model model) {
		//查询商品
		List<Products> productsList = productService.findProductsList();
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
