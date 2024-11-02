package java15.service.impl;

import java15.dao.ProfileDao;
import java15.dao.impl.ProfileDaoImpl;
import java15.entities.Profile;
import java15.service.ProfileService;

public class ProfileServiceImpl implements ProfileService {
   private final ProfileDao profileDao = new ProfileDaoImpl();

    @Override
    public void save(Long userId,Profile profile) {
        profileDao.saveProfile(userId, profile);

    }

    @Override
    public Profile findProfileById(Long userId) {
        return profileDao.findProfileById(userId);
    }

    @Override
    public void deleteProfileByUserId(Long userId) {
        profileDao.deleteProfileByUserId(userId);
    }
}
