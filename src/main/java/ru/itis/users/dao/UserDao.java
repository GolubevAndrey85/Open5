package ru.itis.users.dao;

import ru.itis.users.models.User;

public interface UserDao {

	User findByUserName(String username);

}