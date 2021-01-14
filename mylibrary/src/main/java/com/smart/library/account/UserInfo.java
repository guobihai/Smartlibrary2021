package com.smart.library.account;

import com.google.gson.annotations.SerializedName;

/**
 * @Author guobihai
 * @Created 5/9/19
 * @Editor zrh
 * @Edited 5/9/19
 * @Type
 * @Layout
 * @Api
 */
public class UserInfo {
    // 公司名称
    @SerializedName(value = "branchName")
    private String companyName;
    // 公司ID
    @SerializedName(value = "branchId")
    private String companyId;
    // 城市编码
    private String cityCode;
    // 城市名称
    private String cityName;
    // 性别
    private String sex;
    // 职位
    @SerializedName(value = "positionName")
    private String position;
    // 用户名
    private String userName;
    // 真实名
    private String realName;
    // 头像
    private String avatar;
    // 电话
    private String mobile;
    // 品牌公司
    private String brandName;
    private String brandId;

    private String employeesId;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(String employeesId) {
        this.employeesId = employeesId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
