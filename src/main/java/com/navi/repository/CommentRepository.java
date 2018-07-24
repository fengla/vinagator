package com.navi.repository;

import com.navi.dto.CommentDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentDTO, Long>{

    public List<CommentDTO> findCommentDTOByActivityId(Long activityId);

    public CommentDTO save(CommentDTO commentDTO);

}
