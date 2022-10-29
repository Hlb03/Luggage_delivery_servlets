package org.luggage_delivery.dao.dao_implementations;
/*
  User: admin
  Cur_date: 15.10.2022
  Cur_time: 13:06
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_interfaces.RoleDAO;
import org.luggage_delivery.entity.Role;
import org.luggage_delivery.util.UpdateUtil;

import java.util.List;

public class RoleDAOImpl implements RoleDAO {

    private final Session session;

    public RoleDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public void addNewRole(Role role) {
        session.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return session.createQuery("SELECT a FROM Role a", Role.class).list();
    }

    @Override
    public Role getById(int id) {
        return session.get(Role.class, id);
    }

    @Override
    public Role getByName(String roleName) {
        return session.createQuery("SELECT r from Role r where r.roleName = :roleName", Role.class)
                        .setParameter("roleName", roleName)
                        .uniqueResult();
    }

    @Override
    public void updateRole(int id, Role role) {
        Role role1 = getById(id);
        UpdateUtil.updateRoleParams(role1, role);
        session.update(role1);
    }

    @Override
    public void deleteRole(Role role) {
        System.out.println(session.createQuery("delete from Role r where r.roleName = :roleName")
                .setParameter("roleName", role.getRoleName()).executeUpdate());
    }
}
