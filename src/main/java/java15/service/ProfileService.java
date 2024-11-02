package java15.service;

import java15.entities.Profile;

public interface ProfileService {
    void save(Long userId,Profile profile);
    Profile findProfileById(Long userId);
    void deleteProfileByUserId(Long userId);
}
