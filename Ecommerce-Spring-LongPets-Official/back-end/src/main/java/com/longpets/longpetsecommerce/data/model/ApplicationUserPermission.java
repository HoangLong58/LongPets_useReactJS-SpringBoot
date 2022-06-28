package com.longpets.longpetsecommerce.data.model;

public enum ApplicationUserPermission {

    ADMIN_LOG_READ("admin_log:read"),
    ADMIN_LOG_WRITE("admin_log:write"),

    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),

    CITY_READ("city:read"),
    CITY_WRITE("city:write"),

    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),

    DISTRICT_READ("district:read"),
    DISTRICT_WRITE("district:write"),

    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),

    IMAGE_READ("image:read"),
    IMAGE_WRITE("image:write"),

    ORDER_READ("order:read"),
    ORDER_WRITE("order:write"),

    ORDER_DETAIL_READ("order_detail:read"),
    ORDER_DETAIL_WRITE("order_detail:write"),

    ORDER_STATUS_READ("order_status:read"),
    ORDER_STATUS_WRITE("order_status:write"),

    PET_READ("pet:read"),
    PET_WRITE("pet:write"),

    POSITION_READ("position:read"),
    POSITION_WRITE("position:write"),

    WARD_READ("ward:read"),
    WARD_WRITE("ward:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
