package com.iot.testmybatis;

import com.iot.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    /*
    * 快速入门的测试
    * */
    @Test
    public void mybatisQuickStart() throws IOException {

        //1.加载核心的配资文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.获取SqlSession 会话的对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.执行sql 参数  statement ：namespace.id
        List<User> users = sqlSession.selectList("user.findAll");
        //遍历集合
        for (User user:
             users) {
            System.out.println(user);
        }

        //关闭资源
        sqlSession.close();
    }
    /*
    * 测试添加的操作
    * */
    @Test
    public void testSave() throws IOException {
        //1.加载核心的配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.获取sqlSessionFactory工场类的对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.获取SqlSession会话的对象
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //执行sql 参数   xxx.xxx
        //创建User对象
        User user=new User();
        user.setUsername("jack");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京");
        sqlSession.insert("user.save",user);
        //提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }
/*
* 测试修改操作
* */
    @Test
    public void testUpdata() throws IOException {
        //1.加载核心的配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.获取sqlSessionFactory工场类的对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.获取SqlSession会话的对象
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //执行sql 参数   xxx.xxx
        //创建User对象
        User user=new User();
        user.setId(4);
        user.setUsername("loiiicy");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("北京");
        sqlSession.update("user.updata",user);
        //提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }
/*
* 测试删除额的操作
* */
@Test
    public void testDelete() throws IOException {
        //1.加载核心的配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.获取sqlSessionFactory工场类的对象
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.获取SqlSession会话的对象
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //执行sql 参数   xxx.xxx
        //创建User对象
        sqlSession.update("user.delete",5);
        //提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }
}
