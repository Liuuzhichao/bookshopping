package cn.sdut.dao;

import java.util.List;

import cn.sdut.model.Users;

public interface UsersMapper {
	
	/**
	 * 保存方法
	 * @param user 要保存的用户对象
	 * @return 如果返回1,保存成功;否则返回0
	 */
	public int saveUsers(Users user);
	
	/**
	 * 删除用户方法
	 * @param id 要删除的用户的id
	 * @return 如果返回1,删除成功;否则返回0
	 */
	public int delUsers(int id);
	
	/**
	 * 修改用户方法
	 * @param users 要修改的对象
	 * @return 如果返回1,修改成功;否则返回0
	 */
	public int updateUsers(Users user);
	
	/**
	 * 根据ID查询用户
	 * @param id 要查询用的ID
	 * @return 查询的用户对象
	 */
	public Users findUserById(int id);
	
	/**
	 * 查询所有的用户
	 * @return 所有用户组成的集合
	 */
	public List<Users> findUsersList();
	
	/**
	 * 用户登录方法
	 * @param user 对象当中存放用户输入的用户名和密码
	 * @return 登录成功,返回Users对象;否则返回NULL.
	 */
	public Users login(Users user);

	/**
	 * 根据激活码查找用户
	 * @param code 激活码
	 * @return
	 */
	public Users findUserByCode(String code);
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	public Users findUserByName(String username);
	
}
