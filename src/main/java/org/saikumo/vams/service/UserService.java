package org.saikumo.vams.service;

import lombok.extern.slf4j.Slf4j;
import org.saikumo.vams.dto.ApiResult;
import org.saikumo.vams.entity.User;
import org.saikumo.vams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public ApiResult changePassword(Authentication authentication, String oldPassword, String newPassword) {
		log.info("修改密码，用户名为：{}",authentication.getName());
		User user = userRepository.findUserByUsername(authentication.getName());
		if(user == null){
			return ApiResult.fail("数据库错误");
		}
		//旧密码不正确
		if(!passwordEncoder.matches(oldPassword,authentication.getCredentials().toString())){
			return ApiResult.fail("密码错误");
		}
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
		return ApiResult.ok();
	}
}
