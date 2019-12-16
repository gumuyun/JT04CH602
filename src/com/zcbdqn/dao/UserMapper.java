package com.zcbdqn.dao;

import com.zcbdqn.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 使用用户名模糊查询
     * @param username 用户名
     * @return 用户集合
     */
    List<User> getUsersLikeName(String username);

    /**
     * 使用User,查user集合
     * @param user
     * @return
     */
    List<User> getUsers(User user);

    /**
     *
     * @param map
     * @return
     */
    List<User> getUsersByMap(Map<String,Object> map);


    List<User> test(Map<String,Object> map);

    int add(User user);

    int update(User user);

    int updatePwd(@Param("id") Integer id, @Param("newPassword") String newPassword);

    int delete(Integer id);

    /**
     * 使用角色编号查询用户集合,
     * @param roleId
     * @return
     */
    List<User> getUserByRoleId(Integer roleId);

    /**
     * 使用用户id查询用户对象,并且查出用户的所有的地址
     * @param id
     * @return
     */
    User getUserById(Integer id);
}
