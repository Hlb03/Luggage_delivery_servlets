package org.luggage_delivery.service;

import org.luggage_delivery.entity.Role;
import org.luggage_delivery.exceptions.DataBaseException;

import java.util.List;

public interface RoleService {
    void addRole(Role role) throws DataBaseException;
    List<Role> getRoles() throws DataBaseException;
    Role getById(int id) throws DataBaseException;
    Role getByName(String roleName) throws DataBaseException;
    void updateRole(int id, Role role) throws DataBaseException;
    void deleteRole(Role role) throws DataBaseException;
}
