package com.meetu.repository;

import com.meetu.dto.CommentDTO;
import com.meetu.dto.UserDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentDTO, Long>{

    public List<CommentDTO> findCommentDTOByActivityId(Long activityId);

    public CommentDTO save(CommentDTO commentDTO);

}
