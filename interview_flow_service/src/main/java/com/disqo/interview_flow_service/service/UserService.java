package com.disqo.interview_flow_service.service;

import com.disqo.interview_flow_service.excaption.TalentNotFoundException;
import com.disqo.interview_flow_service.excaption.UserNotFoundException;
import com.disqo.interview_flow_service.persistance.entity.talent.Talent;
import com.disqo.interview_flow_service.persistance.entity.user.User;

public interface UserService {

    User findById(Long id) throws UserNotFoundException;

}
