package ru.garf.springDB.dao.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import ru.garf.springDB.dao.interfaces.MP3DAO;
import ru.garf.springDB.dao.objects.MP3;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3DAO {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public int insert(MP3 mp3) {
		String sql = "INSERT INTO mp3 (name, author) VALUES (:name, :author)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", mp3.getName());
		params.addValue("author", mp3.getAuthor());
		jdbcTemplate.update(sql, params, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public void insertWithJDBC(MP3 mp3) {
		Connection conn = null;

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:db/SpringDB.db";
			conn = DriverManager.getConnection(url, "", "");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "INSERT INTO mp3 (name, author) VALUES (?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mp3.getName());
			ps.setString(2, mp3.getAuthor());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM mp3 WHERE id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		jdbcTemplate.update(sql, params);
	}

	@Override
	public void delete(MP3 mp3) {
		delete(mp3.getId());
	}

	@Override
	public MP3 getMP3ById(int id) {
		String sql = "SELECT * FROM mp3 WHERE id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper());

	}

	@Override
	public List<MP3> getMP3ListByName(String name) {
		String sql = "SELECT * FROM mp3 WHERE UPPER(name) LIKE :name";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "%" + name.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	@Override
	public List<MP3> getMP3ListByAuthor(String author) {
		String sql = "SELECT * FROM mp3 WHERE UPPER(author) LIKE :author";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("author", "%" + author.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	@Override
	public int getMP3Count() {
		String sql = "SELECT COUNT(*) FROM mp3";
		return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
	}

	@Override
	public List<MP3> getAllMP3() {
		String sql = "SELECT * FROM mp3";
		return jdbcTemplate.query(sql, new MP3RowMapper());
	}

	@Override
	public Map<String, Integer> getStat() {
		String sql = "SELECT AUTHOR, COUNT(*) AS COUNT FROM MP3 GROUP BY AUTHOR";

		return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {

			public Map<String, Integer> extractData(ResultSet rs) throws SQLException {
				Map<String, Integer> map = new TreeMap();
				while (rs.next()) {
					String author = rs.getString("author");
					int count = rs.getInt("count");
					map.put(author, count);
				}

				return map;
			};
		});
	}

	private static final class MP3RowMapper implements RowMapper<MP3> {

		@Override
		public MP3 mapRow(ResultSet rs, int rowNum) throws SQLException {
			MP3 mp3 = new MP3();
			mp3.setId(rs.getInt("id"));
			mp3.setName(rs.getString("name"));
			mp3.setAuthor(rs.getString("author"));

			return mp3;
		}

	}
}
