package org.saikumo.vams.repository;

import org.saikumo.vams.entity.Activity;
import org.saikumo.vams.entity.JoinRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinRecordRepository extends JpaRepository<JoinRecord,Integer> {

	public List<JoinRecord> findAllByUserId(Long userId);

}
