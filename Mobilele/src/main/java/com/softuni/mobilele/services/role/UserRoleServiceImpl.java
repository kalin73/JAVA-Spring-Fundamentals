package com.softuni.mobilele.services.role;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.softuni.mobilele.domain.dtos.view.UserRoleViewDto;
import com.softuni.mobilele.domain.entities.UserRole;
import com.softuni.mobilele.domain.enums.Role;
import com.softuni.mobilele.repositories.UserRoleRepository;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	private final UserRoleRepository userRoleRepository;
	private final ModelMapper modelMapper;

	public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
		this.userRoleRepository = userRoleRepository;
		this.modelMapper = modelMapper;
		this.dbInit();
	}

	@Override
	public void dbInit() {
		if (!isDbInit()) {
			List<UserRole> roles = new ArrayList<>();
			roles.add(new UserRole().setRole(Role.USER));
			roles.add(new UserRole().setRole(Role.ADMIN));

			this.userRoleRepository.saveAllAndFlush(roles);
		}
	}

	@Override
	public boolean isDbInit() {
		return this.userRoleRepository.count() > 0;
	}

	@Override
	public List<UserRoleViewDto> getAll() {
		return this.userRoleRepository.findAll().stream().map(role -> modelMapper.map(role, UserRoleViewDto.class))
				.toList();
	}
}
