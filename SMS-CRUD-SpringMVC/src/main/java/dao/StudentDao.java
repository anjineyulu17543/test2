package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import model.Student;

public class StudentDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(Student std) {
		String q = "INSERT INTO student(name, email, password) VALUES('" + std.getName() + "'," + std.getEmail() + ",'"
				+ std.getPassword() + "')";
		return template.update(q);
	}

	public int update(Student std) {
		String q = "UPDATE student SET name='" + std.getName() + "', email=" + std.getEmail() + ",password='"
				+ std.getPassword() + "' where id=" + std.getId() + "";
		return template.update(q);
	}

	public int delete(int id) {
		String q = "DELETE FROM student WHERE id=" + id + "";
		return template.update(q);
	}

	public Student getEmpById(int id) {
		String q = "SELECT * FROM student WHERE id=?";
		return template.queryForObject(q, new Object[] { id }, new BeanPropertyRowMapper<Student>(Student.class));
	}

	public List<Student> getAllStudents() {
		return template.query("SELECT * FROM student", new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int row) throws SQLException {
				Student std = new Student();

				std.setId(rs.getInt(1));
				std.setName(rs.getString(2));
				std.setEmail(rs.getString(3));
				std.setPassword(rs.getString(4));
				return std;
			}
		});
	}
}
