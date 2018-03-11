package org.pharmacy.pharmeasy.dao;

import java.util.ArrayList;

import org.pharmacy.pharmeasy.model.User;

public interface UserDao {

	public User createUser(User user);

	public User updateUser(User user);

	public User retrieveUser(Integer userId);

	public User retrieveUserByDetails(String userName, String password);

	public ArrayList<User> retrieveUserByType(String userType);

}
