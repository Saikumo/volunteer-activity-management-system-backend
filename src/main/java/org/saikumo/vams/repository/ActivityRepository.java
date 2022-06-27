package org.saikumo.vams.repository;

import org.saikumo.vams.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    public Activity findById(Long id);

    @Modifying
    @Transactional
    public void deleteById(Long id);

    public List<Activity> findAllByStatus(String status);

}
