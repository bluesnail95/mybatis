package gdut.ff.dynamicSql;

import java.sql.JDBCType;
import java.util.Date;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;


public final class EmployerDynamicSqlSupport {

	public static final Employer employer = new Employer("employer");
	public static final SqlColumn<Integer> id = employer.id;
	public static final SqlColumn<String> firstName = employer.firstName;
	public static final SqlColumn<String> lastName = employer.lastName;
	public static final SqlColumn<Date> birthDate = employer.birthDate;
	public static final SqlColumn<String> employed = employer.employed;
	public static final SqlColumn<String> occupation = employer.occupation;
			
	public static final class Employer extends SqlTable{

		public final SqlColumn<Integer> id = column("id",JDBCType.INTEGER);
		public final SqlColumn<String> firstName = column("first_name",JDBCType.VARCHAR);
		public final SqlColumn<String> lastName = column("last_name",JDBCType.VARCHAR);
		public final SqlColumn<Date> birthDate = column("birth_date",JDBCType.DATE);
		public final SqlColumn<String> employed = column("employed",JDBCType.VARCHAR);
		public final SqlColumn<String> occupation = column("occupation",JDBCType.VARCHAR);
		
		protected Employer(String name) {
			super(name);
		}
		
		
	}
}
