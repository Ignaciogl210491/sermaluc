package com.sermaluc.proy.app.services;

import com.sermaluc.proy.app.exceptions.UserException;
import com.sermaluc.proy.app.model.User;

public interface UserService {
    public User save(User user) throws UserException;
    public User update(User user) throws UserException;
}
