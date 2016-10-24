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
import ru.garf.springDB.dao.objects.Author;
import ru.garf.springDB.dao.objects.MP3;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3DAO {

	private static final String mp3Table = "mp3";
	private static final String mp3View = "mp3_view";

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	// @Transactional(propagation = Propagation.REQUIRED, isolation
	// =Isolation.SERIALIZABLE)
	public int insert(MP3 mp3) {

		String sqlMp3 = "INSERT INTO mp3 (name, author_id) VALUES (:name, :author_id)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		int authorId = getAuthorId(mp3.getAuthor());

		params = new MapSqlParameterSource();
		params.addValue("name", mp3.getName());
		params.addValue("author_id", authorId);

		return jdbcTemplate.update(sqlMp3, params);

	}

	@Override
	// @Transactional(propagation = Propagation.REQUIRED)
	public int getAuthorId(Author author) {

		// System.out.println(TransactionSynchronizationManager.isActualTransactionActive());

		String sql = "SELECT * FROM author WHERE name=:name LIMIT 1;";
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("name", author.getName());

		try {

			return jdbcTemplate.queryForObject(sql, params, new AuthorRowMapper()).getId();

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("SetNewAuthor - " + author.getName());

			String sqlAuthor = "INSERT INTO author (name) VALUES (:authorName)";
			KeyHolder key = new GeneratedKeyHolder();

			params.addValue("authorName", author.getName());

			jdbcTemplate.update(sqlAuthor, params, key);

			return key.getKey().intValue();
		}

	}

	@Override
	public int[] insertList(List<MP3> listMP3) {

		int[] i = new int[listMP3.size()];
		for (MP3 mp3 : listMP3) {
			insert(mp3);
		}

		return i;

		// String sql = "INSERT INTO MP3 (name, author) VALUES (:name,
		// :author)";
		// SqlParameterSource[] batch =
		// SqlParameterSourceUtils.createBatch(listMP3.toArray());
		// int[] updateCounts = jdbcTemplate.batchUpdate(sql, batch);
		// return updateCounts;
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
			// ps.setString(2, mp3.getAuthor());
			ps.setInt(2, mp3.getAuthor().getId());
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
		String sql = "DELETE FROM " + mp3Table + " WHERE mp3_id=:id";
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
		String sql = "SELECT * FROM " + mp3View + " WHERE mp3_id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper());

	}

	@Override
	public List<MP3> getMP3ListByName(String name) {
		String sql = "SELECT * FROM " + mp3View + " WHERE UPPER(mp3_name) LIKE :name";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "%" + name.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	@Override
	public List<MP3> getMP3ListByAuthor(String author) {
		String sql = "SELECT * FROM " + mp3View + " WHERE UPPER(author_name) LIKE :author_name";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("author_name", "%" + author.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	@Override
	public int getMP3Count() {
		String sql = "SELECT COUNT(*) FROM" + mp3Table;
		return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
	}

	@Override
	public List<MP3> getAllMP3() {
		String sql = "SELECT * FROM " + mp3View;
		return jdbcTemplate.query(sql, new MP3RowMapper());
	}

	@Override
	public Map<String, Integer> getStat() {
		String sql = "SELECT AUTHOR_NAME, COUNT(*) AS COUNT FROM " + mp3View + " GROUP BY AUTHOR_NAME";

		return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {

			public Map<String, Integer> extractData(ResultSet rs) throws SQLException {
				Map<String, Integer> map = new TreeMap();
				while (rs.next()) {
					String author = rs.getString("author_name");
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
			Author author = new Author();
			author.setId(rs.getInt("author_id"));
			author.setName(rs.getString("author_name"));

			MP3 mp3 = new MP3();
			mp3.setId(rs.getInt("mp3_id"));
			mp3.setName(rs.getString("mp3_name"));
			mp3.setAuthor(author);

			return mp3;
		}

	}

	private static final class AuthorRowMapper implements RowMapper<Author> {

		@Override
		public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
			Author author = new Author();
			author.setId(rs.getInt("id"));
			author.setName(rs.getString("name"));

			return author;
		}

	}
}
