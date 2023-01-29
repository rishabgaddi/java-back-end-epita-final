package fr.epita.services;

import fr.epita.datamodel.Role;

public interface IRoleDAO extends IDAO<Role> {
    Role findByName(String name);
}
