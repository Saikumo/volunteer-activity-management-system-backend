package org.saikumo.vams.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityRepository {

	@Query(value = "insert into activity_users values (?1,?2)", nativeQuery = true)
	public void createUserActivitity(Long activityId, Long userId);
}
