package fr.epita.rest.web.data.services;

import fr.epita.datamodel.Role;
import fr.epita.rest.web.messages.RoleDTO;
import fr.epita.services.RoleJPADAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RoleDataService {
    private final RoleJPADAO roleJPADAO;
    private final SessionFactory sessionFactory;

    public RoleDataService(RoleJPADAO roleJPADAO, SessionFactory sessionFactory) {
        this.roleJPADAO = roleJPADAO;
        this.sessionFactory = sessionFactory;
    }

    public List<RoleDTO> getRoles() {
        List<Role> roles = roleJPADAO.findAll();
        return roles.stream().map(role -> {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setName(role.getName());
            return roleDTO;
        }).collect(Collectors.toList());
    }
}
