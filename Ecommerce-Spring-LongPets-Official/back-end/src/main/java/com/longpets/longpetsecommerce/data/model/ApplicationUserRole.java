package com.longpets.longpetsecommerce.data.model;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {
    CUSTOMER(Sets.newHashSet()),       //nguoi mua
    ADMIN(Sets.newHashSet()),          //admin
    SALER(Sets.newHashSet()),          //nhan vien ban hang
    COUNSELOR(Sets.newHashSet()),      //nhan vien tu van
    ACCOUNTANT(Sets.newHashSet()),     //nhan vien ke toan
    WAREHOUSE(Sets.newHashSet());      //nhan vien kho va van chuyen

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
