package org.luggage_delivery.dao.dao_interfaces;

import org.luggage_delivery.entity.Role;

import java.util.List;

public interface RoleDAO {
    void addNewRole(Role role);
    List<Role> getAllRoles();
    Role getById(int id);
    Role getByName(String roleName);
    void updateRole(int id, Role role);
    void deleteRole(Role role);
}
