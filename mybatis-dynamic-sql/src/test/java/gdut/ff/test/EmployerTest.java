package gdut.ff.test;

import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static gdut.ff.dynamicSql.EmployerDynamicSqlSupport.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

import gdut.ff.domain.Employer;
import gdut.ff.mapper.EmployerMapper;

public class EmployerTest {
	
	private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/mybatis";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setup() throws Exception {
		Class.forName(JDBC_DRIVER);
		InputStream is = getClass().getResourceAsStream("/CreateEmployer.sql");
		
		try(Connection connection = DriverManager.getConnection(JDBC_URL, "root","19950821abc")){
			ScriptRunner runner = new ScriptRunner(connection);
			runner.setLogWriter(null);
			runner.runScript(new InputStreamReader(is));
		}
		
		UnpooledDataSource datasource = new UnpooledDataSource(JDBC_DRIVER,JDBC_URL, "root","19950821abc");
		Environment environment = new Environment("test",new JdbcTransactionFactory(),datasource);
		Configuration config = new Configuration(environment);
		config.addMapper(EmployerMapper.class);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
	}
	
	@Test
	public void testSelectAllRows() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		EmployerMapper mapper = sqlSession.getMapper(EmployerMapper.class);
		SelectStatementProvider selectStatement = select(id,firstName,lastName,birthDate,employed,occupation)
                .from(employer)
                .orderBy(id.descending())
                .build()
                .render(RenderingStrategy.MYBATIS3);
		List<Employer> rows = mapper.selectMany(selectStatement);
		System.out.println(rows);
		sqlSession.close();
	}
	

}
