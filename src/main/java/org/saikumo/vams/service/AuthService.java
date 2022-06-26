package org.saikumo.vams.service;

import lombok.extern.slf4j.Slf4j;
import org.saikumo.vams.constant.RoleName;
import org.saikumo.vams.dto.AccessToken;
import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.entity.Role;
import org.saikumo.vams.entity.User;
import org.saikumo.vams.repository.RoleRepository;
import org.saikumo.vams.repository.UserRepository;
import org.saikumo.vams.security.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthService {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;

	public ApiResult login(String loginAccount, String password) {

		log.info("用户登录，用户名为: {}", loginAccount);
		// 1 创建UsernamePasswordAuthenticationToken
		UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(loginAccount, password);
		// 2 认证
		Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication);
		// 3 保存认证信息
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// 4 生成自定义token
		AccessToken accessToken = jwtProvider.createToken((UserDetails) authentication.getPrincipal());

		User userDetail = (User) authentication.getPrincipal();

		accessToken.setAuthorities(userDetail.getAuthorities());

		return ApiResult.ok(accessToken);
	}

	public ApiResult register(String loginAccount, String password, String roleName) {
		User user = userRepository.findUserByUsername(loginAccount);
		//用户名存在
		if (!ObjectUtils.isEmpty(user)) {
			return ApiResult.fail("用户名已存在");
		}
		//注册
		user = new User();
		user.setAccountNonExpired(true);
		user.setEnabled(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setUsername(loginAccount);
		user.setNickname(loginAccount);
		user.setPassword(passwordEncoder.encode(password));
		List<Role> roleList = new ArrayList<>();
		Role role = new Role();
		role.setName(roleName);
		//判断角色
		if (roleName.equals(RoleName.ACTIVITY_ORGANIZER.getRoleName())) {
			role.setId(RoleName.ACTIVITY_ORGANIZER.getId());
		} else if (roleName.equals(RoleName.MANAGER.getRoleName())) {
			role.setId(RoleName.MANAGER.getId());
		} else if (roleName.equals(RoleName.VOLUNTEER.getRoleName())) {
			role.setId(RoleName.VOLUNTEER.getId());
		} else {
			return ApiResult.fail("角色错误");
		}
		roleList.add(role);
		user.setRoles(roleList);
		userRepository.save(user);
		return ApiResult.ok();
	}
}
