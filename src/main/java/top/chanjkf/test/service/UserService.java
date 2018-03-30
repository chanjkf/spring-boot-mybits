package top.chanjkf.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.chanjkf.test.entity.User;
import top.chanjkf.test.mapper.UserMapper;

import java.util.List;

/**
 * Created by yi on 2018/3/23.
 */
@Component
public class UserService implements IUserService {

    @Autowired
    UserMapper mapper;

    @Override
    public int insertUser(User user) {
        return mapper.insertUser(user);
    }

    @Override
    public List<User> findAll() {
        return mapper.findAll();
    }

    @Override
    public User findUserByIs(int id) {
        return mapper.findUserByIs(id);
    }

    @Override
    public int updateUser(User user) {
        return mapper.updateUser(user);
    }

    @Override
    public List<User> findUserByName(String name) {
        return mapper.findUserByName(name);
    }
}
