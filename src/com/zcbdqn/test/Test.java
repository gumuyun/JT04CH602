package com.zcbdqn.test;

import com.zcbdqn.dao.UserMapper;
import com.zcbdqn.pojo.User;
import com.zcbdqn.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    private static Logger logger=Logger.getLogger(Test.class);


    public static void main(String[] args) {
        //getUsers();
        //getUsers1();

        //getUserByMap();

        //test();

        //add();

        //update();

        //updatePwd();

        //delete();
        //getUsers2();
     /*   Address address1=new Address();
        address1.setContact("张三");
        address1.setAddressDesc("广州增城区");

        List<Address> addressList =new ArrayList<>();
        addressList.add(address1);

        User user=new User();
        user.setUserName("张三");
        user.setAddressList(addressList);

        address1=null;
        addressList=null;
        //一个对象被一个生命周期未结束对象引用,那么该对象的生命不会结束
        //System.out.println(address1.getContact());
        for (Address address:user.getAddressList()){
            System.out.println(address);
        }*/

        getUsers2();
    }

    private static void getUsers2() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<User> users = sqlSession.getMapper(UserMapper.class).getUserByRoleId(3);
        for (User user:users){
            logger.debug(user);
            logger.debug(user.getRole());

        }
        sqlSession.clearCache();
        MybatisUtils.closeSqlSession();

        System.out.println("==================================================================");
        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        List<User> users1 = sqlSession1.getMapper(UserMapper.class).getUserByRoleId(3);
        for (User user:users1){
            logger.debug(user);
            logger.debug(user.getRole());

        }
        MybatisUtils.closeSqlSession();
    }

    private static void delete() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        int count = sqlSession.getMapper(UserMapper.class).delete(17);
        logger.debug(" delete :"+count);
        sqlSession.commit();
        MybatisUtils.closeSqlSession();
    }

    private static void updatePwd() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        int count = sqlSession.getMapper(UserMapper.class).updatePwd(17, "1234567");
        logger.debug(" update :"+count);
        sqlSession.commit();
        MybatisUtils.closeSqlSession();
    }

    private static void update() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        User user=new User();
        user.setUserCode("tom");
        user.setUserRole(3);
        user.setUserName("tom");
        user.setPhone("13412341234");
        user.setAddress("gz");
        user.setBirthday(new Date());
        user.setCreatedBy(1);
        user.setCreationDate(new Date());
        user.setUserPassword("tom");
        user.setGender(1);
        user.setId(17);
        int count = sqlSession.getMapper(UserMapper.class).update(user);
        logger.debug(" update :"+count);
        sqlSession.commit();
        MybatisUtils.closeSqlSession();
    }

    private static void add() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        User user=new User();
        user.setUserCode("tom");
        user.setUserRole(3);
        user.setUserName("tom");
        user.setPhone("13412341234");
        user.setAddress("gz");
        user.setBirthday(new Date());
        user.setCreatedBy(1);
        user.setCreationDate(new Date());
        user.setUserPassword("tom");
        user.setGender(1);
        int count = sqlSession.getMapper(UserMapper.class).add(user);
        logger.debug(" insert :"+count);
        sqlSession.commit();
        MybatisUtils.closeSqlSession();
    }

    private static void test() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //select * from smbms_user where userRole=1
        Map<String,Object> map=new HashMap<>();
        map.put("params","where userRole=1 and userName like concat('%','李','%')");
        List<User> users = sqlSession.getMapper(UserMapper.class).test(map);
        for (User user:users){
            logger.debug(user);
        }
        MybatisUtils.closeSqlSession();
    }

    private static void getUserByMap() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        Map<String,Object> map=new HashMap<>();
        map.put("username","'李'");
        map.put("userRole",2);

        List<User> users = sqlSession.getMapper(UserMapper.class).getUsersByMap(map);
        for (User user:users){
            logger.debug(user);
        }
        MybatisUtils.closeSqlSession();
    }

    private static void getUsers1() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        User _user=new User();
        _user.setUserName("李");
        _user.setUserRole(2);

        List<User> users = sqlSession.getMapper(UserMapper.class).getUsers(_user);
        for (User user:users){
            logger.debug(user);
        }
        MybatisUtils.closeSqlSession();
    }

    private static void getUsers() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<User> users = sqlSession.getMapper(UserMapper.class).getUsersLikeName("李");
        for (User user:users){
            logger.debug(user);
        }
        MybatisUtils.closeSqlSession();
    }
}
