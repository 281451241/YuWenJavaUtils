package com.lyw.db.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Demo1 {
	public static void main(String[] args) {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * DataSource dataSource = new PooledDataSource("com.mysql.jdbc.Driver",
		 * "jdbc:mysql://localhost/dxsf?userUnicode=true&amp;characterEncoding=utf8"
		 * , "root", "123456"); TransactionFactory transactionFactory = new
		 * JdbcTransactionFactory(); Environment environment = new
		 * Environment("development", transactionFactory, dataSource);
		 * Configuration configuration = new Configuration(environment);
		 * configuration.addMapper(BlogMapper.class); SqlSessionFactory
		 * sqlSessionFactory = new
		 * SqlSessionFactoryBuilder().build(configuration);
		 */

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			// Blog blog = session.selectOne("model.BlogMapper.selectBlog", 1);

			BlogMapper mapper = session.getMapper(BlogMapper.class);
			Blog blog = mapper.selectBlog(1);
			System.out.println(blog + "\n" + blog.getId() + "  "
					+ blog.getTitle() + "   " + blog.getContent());
		} finally {
			session.close();
		}
	}
}

/*			
 */