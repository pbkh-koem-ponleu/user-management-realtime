package com.example.demo.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public class UserDao implements UserRepository {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> get() {
		String query = "SELECT * FROM \"user\" ORDER BY id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		return this.jdbcTemplate.query(query, params, this.rowMapper());
	}

	@Override
	public User create(User _user) {
		String query = "INSERT INTO \"user\" (name, gender, email, address) values(:name, :gender, :email, :address)"
				+ "RETURNING id, name, gender, email, address";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("name", _user.getName())
				.addValue("gender", _user.getGender())
				.addValue("email", _user.getEmail())
				.addValue("address", _user.getAddress());
		return this.jdbcTemplate.queryForObject(query, params, this.rowMapper());
	}

	@Override
	public User update(Long _id, User _user) {
		String query = "update \"user\" set name = :name, gender = :gender, email = :email, address = :address WHERE id = :id "
				+ "RETURNING id, name, gender, email, address";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("name", _user.getName())
				.addValue("gender", _user.getGender())
				.addValue("email", _user.getEmail())
				.addValue("address", _user.getAddress())
				.addValue("id", _id);
		return this.jdbcTemplate.queryForObject(query, params, this.rowMapper());
	}

	@Override
	public String delete(int _id) {
		String query = "DELETE FROM \"user\" WHERE id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", _id);
		this.jdbcTemplate.update(query, params);
		return String.format("remove %s", _id);
	}

	@Override
	public String deleteAll() {
		String query = "DELETE FROM \"user\"";
		MapSqlParameterSource params = new MapSqlParameterSource();
		this.jdbcTemplate.update(query, params);
		return "remove success";
	}

	private RowMapper<User> rowMapper()
	{
		return (ResultSet _resultSet, int _rowNum) ->
		{
			Long id = Long.parseLong(_resultSet.getString("id"));
			String name = _resultSet.getString("name");
			String gender = _resultSet.getString("gender");
			String email = _resultSet.getString("email");
			String address = _resultSet.getString("address");
			return new User(id, name, gender, email, address);
		};
	}
}
