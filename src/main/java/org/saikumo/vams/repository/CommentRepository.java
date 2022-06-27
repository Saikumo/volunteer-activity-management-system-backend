package org.saikumo.vams.repository;

import org.saikumo.vams.entity.Comment;
import org.saikumo.vams.entity.JoinRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
