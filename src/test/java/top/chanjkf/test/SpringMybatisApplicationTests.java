package top.chanjkf.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import top.chanjkf.test.entity.User;
import top.chanjkf.test.mapper.UserMapper;
import top.chanjkf.test.service.IUserService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringMybatisApplicationTests {
	@Autowired
	IUserService userService;

	@Test

	public void contextLoads() {
		System.out.println("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>");
		System.out.println(findUserByName("李红"));
		System.out.println("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>");

	}
	@Transactional
	public int insertUser() {
		User user = new User();
		user.setName("lindc");
		user.setAge(11);
		user.setGender("男");
		int num = userService.insertUser(user);
		return num;
	}
	@Transactional
	public List<User> findAll() {
		List<User> users = userService.findAll();
		return users;
	}
	public User findUserById(int id) {
		return userService.findUserByIs(id);
	}
	public int updateUser() {
		User user = findUserById(5);
		user.setName("李红");
		user.setAge(20);
		user.setGender("女");
		int count = userService.updateUser(user);
		return count;
	}
	public List<User> findUserByName(String name) {
		List<User> users = userService.findUserByName(name);
		return users;
	}

}
