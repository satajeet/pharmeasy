package org.pharmacy.pharmeasy.dao;

import java.util.List;

import org.pharmacy.pharmeasy.model.User;

public interface UserDao {

	public User createUser(User user);

	public User updateUser(User user);

	public User retrieveUser(Integer userId);

	public User retrieveUserByDetails(String userName, String password);

	public List<User> retrieveUserByType(String userType);

}
