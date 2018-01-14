package com.shan.netlibrary.net;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈俊山 on 2016/8/28.
 */

public class BaseBean implements Serializable {
    private static final long serialVersionUID = -7620435178023928252L;

    /**
     * IsSuccess : false
     * ErrorList : [{"Key":"UserCenter_User_ValidCodeError","Value":"验证码不正确"}]
     * ValidationList : [{"Key":"","Value":""}]
     */

    private boolean IsSuccess;
    private boolean IsOthersLogin;//是否有其他人登录
    private List<ErrorListBean> ErrorList;
    private List<ValidationListBean> ValidationList;

    public boolean isIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.IsSuccess = IsSuccess;
    }

    public boolean isOthersLogin() {
        return IsOthersLogin;
    }

    public void setOthersLogin(boolean othersLogin) {
        IsOthersLogin = othersLogin;
    }

    public List<ErrorListBean> getErrorList() {
        if (ErrorList == null) {
            ErrorList = new ArrayList<>();
        }
        return ErrorList;
    }

    public void setErrorList(List<ErrorListBean> ErrorList) {
        this.ErrorList = ErrorList;
    }

    public List<ValidationListBean> getValidationList() {
        if (ValidationList == null) {
            ValidationList = new ArrayList<>();
        }
        return ValidationList;
    }

    public void setValidationList(List<ValidationListBean> ValidationList) {
        this.ValidationList = ValidationList;
    }

    public static class ErrorListBean {
        /**
         * Key : UserCenter_User_ValidCodeError
         * Value : 验证码不正确
         */

        private String Key;
        private String Value;

        public String getKey() {
            return Key;
        }

        public void setKey(String Key) {
            this.Key = Key;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }
    }

    public static class ValidationListBean {
        /**
         * Key :
         * Value :
         */

        private String Key;
        private String Value;

        public String getKey() {
            return Key;
        }

        public void setKey(String Key) {
            this.Key = Key;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }
    }
}
