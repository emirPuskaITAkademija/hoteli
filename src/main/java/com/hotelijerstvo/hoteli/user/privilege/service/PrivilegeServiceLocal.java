package com.hotelijerstvo.hoteli.user.privilege.service;

import com.hotelijerstvo.hoteli.user.privilege.Privilege;

import java.util.List;

public interface PrivilegeServiceLocal {

    PrivilegeServiceLocal SERVICE = new PrivilegeService();

    List<Privilege> findAll();
}
