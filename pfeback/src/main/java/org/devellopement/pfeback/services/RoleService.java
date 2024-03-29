package org.devellopement.pfeback.services;


import org.devellopement.pfeback.entities.Role;

import java.util.List;

public interface RoleService {
    Role AddRole(Role role);
    public void DeleteRole(Long id);
    List<Role> RetreiveAllRole ();
    Role updateRole(Role role, Long id);

}
