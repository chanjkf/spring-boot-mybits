package top.chanjkf.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import top.chanjkf.test.entity.User;

import java.util.List;

/**
 *
 * @author yi
 * @date 2018/3/23
 */
@Mapper
@Component
public interface UserMapper {
    /**
     * 插入user
     * @param user
     * @return
     */
    int insertUser(@Param("user")User user);

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id=#{id}")
    User findUserByIs(@Param("id")int id);

    int updateUser(@Param("user")User user);

    List<User> findUserByName(@Param("name") String name);
}
