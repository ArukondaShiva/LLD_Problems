package org.example._07_atm_machine.repository.impl;

import org.example._07_atm_machine.domain.AdminUser;
import org.example._07_atm_machine.repository.AdminUserRepository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AdminUserRepositoryImpl implements AdminUserRepository {

    private Map<String, AdminUser> adminStore = new ConcurrentHashMap<>();


    @Override
    public AdminUser save(AdminUser adminUser) {
        adminStore.put(adminUser.getId(),adminUser);
        return adminUser;
    }

    @Override
    public Optional<AdminUser> findById(String adminId) {
        return Optional.ofNullable(adminStore.get(adminId));
    }

    @Override
    public boolean validateAdminCredentials(String adminId, String pinHash) {

        Optional<AdminUser> adminOpt = findById(adminId);
        if(adminOpt.isPresent()){
            AdminUser admin = adminOpt.get();
            return admin.isActive() && admin.getPinHash().equals(pinHash);
        }

        return false;
    }

}
