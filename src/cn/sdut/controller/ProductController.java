package cn.sdut.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import cn.sdut.model.Products;
import cn.sdut.service.ProductService;
import cn.sdut.utils.UUIDUtils;
import cn.sdut.utils.UploadUtils;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	//打开商品管理的页面
	@RequestMapping("showProductList")
	public String showProduct(Model model) {
		//查询所有的商品
		List<Products> productsList = service.findProductsList();
		
		//将查询到的商品传递到页面中去展示
		//model.addAttribute()相当于request.setAttribute()方法
		model.addAttribute("productsList",productsList);
		
		return "admin/product";
	}
	
	//打开添加商品的页面
	@RequestMapping("showAddProduct")
	public String showAddProduct() {
		return "admin/addProduct";
	}
	
	//添加商品
	@RequestMapping("saveProduct")
	//利用spring容器将jsp页面的表单里的数据与该方法参数进行自动绑定:1.表单的action的值需要与RequestMapping的值相同
	//2.该方法的参数值(类对象时,其中的每一个的属性名)需要与表单项的name值相同而不是id值
	public String saveProduct(Products pro, MultipartFile imgpic) throws IllegalStateException, IOException {
		//将上传的文件上传到服务器	
		String upload = UploadUtils.upload(imgpic);
		//获取上传文件的路径
		pro.setId(UUIDUtils.getUUID());
		pro.setImgurl(upload);
		pro.setState(0);//1表示上架,0表示下架
		//将路径保存到数据库当中
		service.saveProduct(pro);
		
		System.out.println(pro);
		System.out.println(imgpic.getOriginalFilename());
		return "redirect:showProductList";
	}
	
	//删除商品
	@RequestMapping("delProduct")
	public String delProduct(String id) {
		System.out.println("----");
		//调用service进行删除
		service.delProduct(id);
		
		return "redirect:showProductList";
	}
	
	//打开修改商品的页面
	@RequestMapping("showEditProduct")
	public String showEditProduct(String id, Model model) {
		//查询要修改的商品
		Products product = service.findProductById(id);
		//将商品信息传到编辑页面中进行展示
		model.addAttribute("product",product);
		//System.out.println(product);
		return "admin/editproduct";
	}
	
	//修改商品信息
	@RequestMapping("updateProduct")
	//MultipartFile的对象可以自动拿到文件对象
	public String updateProduct(Products product, MultipartFile imgpic) throws IllegalStateException, IOException {
		//System.out.println("update...");
		System.out.println(product);
		//获取文件的路径名,如果不为空(图片存在)就自动上传该路径
		if(!imgpic.getOriginalFilename().equals("") && 
				!(imgpic.getOriginalFilename()==null)) {
			String upload = UploadUtils.upload(imgpic);
			product.setImgurl(upload);
		}
		service.updateProduct(product);
		return "redirect:showProductList";
	}
	
	//打开商品展示的页面
	@RequestMapping("showProductKinds")
	public String showProductKinds(String type, Model model) {
		//System.out.println(type);
		//查询要显示的类型的商品
		List<Products> productsList = service.findProductsListByType(type);
		System.out.println(productsList);
		//将商品传递到页面中进行展示
		model.addAttribute("productsList",productsList);
		return "productkinds";
	}

}
