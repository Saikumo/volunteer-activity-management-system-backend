package org.saikumo.vams.init;

import org.junit.jupiter.api.Test;
import org.saikumo.vams.constant.RoleName;
import org.saikumo.vams.entity.Role;
import org.saikumo.vams.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class initRole {
	@Autowired
	RoleRepository roleRepository;

	@Test
	public void initRole(){
		List<Role> roleList = new ArrayList<>();
		for(int i = 0;i < 3;i++){
			roleList.add(new Role());
		}
		roleList.get(0).setName(RoleName.VOLUNTEER.getRoleName());
		roleList.get(0).setId(RoleName.VOLUNTEER.getId());
		roleList.get(1).setName(RoleName.MANAGER.getRoleName());
		roleList.get(1).setId(RoleName.MANAGER.getId());
		roleList.get(2).setName(RoleName.ACTIVITY_ORGANIZER.getRoleName());
		roleList.get(2).setId(RoleName.ACTIVITY_ORGANIZER.getId());

		roleRepository.saveAllAndFlush(roleList);
	}

}
