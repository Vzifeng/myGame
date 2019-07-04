package com.example.game.po;

public class Permission {
    private Integer id;

    private String permissionName;

    private String permissionDescrip;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionDescrip() {
        return permissionDescrip;
    }

    public void setPermissionDescrip(String permissionDescrip) {
        this.permissionDescrip = permissionDescrip == null ? null : permissionDescrip.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}