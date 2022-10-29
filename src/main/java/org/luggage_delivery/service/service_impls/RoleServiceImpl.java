package org.luggage_delivery.service.service_impls;
/*
  User: admin
  Cur_date: 29.10.2022
  Cur_time: 11:13
*/

import org.luggage_delivery.dao.dao_interfaces.RoleDAO;
import org.luggage_delivery.entity.Role;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void addRole(Role role) throws DataBaseException {
        try {
            roleDAO.addNewRole(role);
        } catch (Exception e) {
            throw new DataBaseException("Failed to add role", e);
        }
    }

    @Override
    public List<Role> getRoles() throws DataBaseException {
        try {
            return roleDAO.getAllRoles();
        } catch (Exception e) {
            throw new DataBaseException("Failed to get all roles", e);
        }
    }

    @Override
    public Role getById(int id) throws DataBaseException {
        try {
            return roleDAO.getById(id);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get role by id", e);
        }
    }

    @Override
    public Role getByName(String roleName) throws DataBaseException {
        try {
            return roleDAO.getByName(roleName);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get role by name", e);
        }
    }

    @Override
    public void updateRole(int id, Role role) throws DataBaseException {
        try {
            roleDAO.updateRole(id, role);
        } catch (Exception e) {
            throw new DataBaseException("Failed to update role", e);
        }
    }

    @Override
    public void deleteRole(Role role) throws DataBaseException {
        try {
            roleDAO.deleteRole(role);
        } catch (Exception e) {
            throw new DataBaseException("Failed to delete role", e);
        }
    }
}
