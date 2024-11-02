package java15.dao;

import java15.entities.Profile;

public interface ProfileDao {
    void saveProfile(Long userId, Profile profile);
    Profile findProfileById(Long userId);
    void deleteProfileByUserId(Long userId);
}
