package cn.sdut.utils;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传的工具类
 * @author Administrator
 *
 */
public class UploadUtils {
	
	public static String upload(MultipartFile file) throws IllegalStateException, IOException {
		
		//定义访问路径
		String fwPath = "http://localhost:8080/bookshopping/pic/";
		
		//定义文件上传的位置,服务器中的某一位置,不可能是项目路径
		String path = "f:/upload/";
		File filePath = new File(path);
		//判断上传位置否则存在,如果该位置不存在,那么就创建一个
		if(!filePath.exists()) {
			//只能创建当前目录,不能创建父目录
			filePath.mkdir();
			//创建父目录和当前目录
			//filePath.mkdirs();
		}
		
		//获取上传文件的真实名称
		String filename = file.getOriginalFilename();
		
		//获取上传文件的后缀名
		filename = filename.substring(filename.lastIndexOf('.'));
		
		//上传文件的新名称
		filename = UUIDUtils.getUUID()+filename;
		
		path = path + filename;
		
		//上传文件
		file.transferTo(new File(path));
		//返回的是文件访问的路径
		return fwPath+filename;
	}

}
