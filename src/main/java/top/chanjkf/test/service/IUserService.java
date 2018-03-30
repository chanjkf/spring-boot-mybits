package top.chanjkf.test.service;

import top.chanjkf.test.entity.User;

import java.util.List;

/**
 *
 * @author yi
 * @date 2018/3/23
 */
public interface IUserService {
    int insertUser(User user);

    List<User> findAll();

    User findUserByIs(int id);

    int updateUser(User user);

    List<User> findUserByName(String name);
}
