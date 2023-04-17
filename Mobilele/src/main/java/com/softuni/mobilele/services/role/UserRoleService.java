package com.softuni.mobilele.services.role;

import java.util.List;

import com.softuni.mobilele.domain.dtos.model.UserRoleModel;
import com.softuni.mobilele.domain.dtos.view.UserRoleViewDto;
import com.softuni.mobilele.services.init.DataBaseInitService;

public interface UserRoleService extends DataBaseInitService {
	List<UserRoleViewDto> getAll();

	List<UserRoleModel> findAllRoles();

	UserRoleModel findRoleByName(String role);
}
