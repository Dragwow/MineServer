package com.pet_project.backend_server.repository.userProfile;

import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.repository.BaseRepository;
import com.pet_project.backend_server.repository.data.UserProfileSearchDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserProfileRepository extends BaseRepository<UserProfile> {

 Optional<UserProfile> findByUser(User user);

 @Query(value = "select distinct new com.pet_project.backend_server.repository.data.UserProfileSearchDto(pv, pv.user) from UserProfile pv")
 List<UserProfileSearchDto> findAllUserProfileSearchDto();

}
