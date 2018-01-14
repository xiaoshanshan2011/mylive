package com.shan.netlibrary.bean;


import com.shan.netlibrary.net.BaseBean;

/**
 * Created by chenjunshan on 17-3-21.
 */

public class LoginBean extends BaseBean {
    /**
     * ConfirmPassword : null
     * Name : shan1234
     * UserName : 昵称iukw4762
     * UserType : 1
     * UserTypeDID : null
     * PrivRoleID : null
     * PrivRoleName : null
     * NickName : 昵称iukw4762
     * Account : 13422058465
     * ThemeColor : null
     * Password :
     * SafetyPassword :
     * Email : null
     * QQ : null
     * WeChat : null
     * MobileNumber : 13422058465
     * PhoneNumber : null
     * VisitNum : 0
     * FansNum : 0
     * FollowNum : 0
     * Point : 0
     * Credit : 100
     * FirstName : null
     * MiddleName : null
     * LastName : null
     * HeadPicUrl : http://usercenterresource.oss-cn-shenzhen.aliyuncs.com/UserHeaderImages/DefaultHeaderImage/pic75x75.jpg
     * SexType : 0
     * SelfIintroduction : null
     * SelectedAppList : null
     * SelectedModuleList : null
     * UserAccountSelfRelateID : null
     * JoinInWorkAt : 1979-12-31T00:00:00
     * IsEnterpriseUser : false
     * ProjectTypeList : null
     * SkillsTypeList : null
     * IndustryTypeList : null
     * ProjectTypeNameList : null
     * SkillsTypeNameList : null
     * IndustryTypeNameList : null
     * UserTypeName : null
     * UserAccountSelfRelateName : null
     * PlatformType : 1
     * FID : 9bed94f1-bc33-4738-9760-31186749a7ac
     * CreatedBy : 00000000-0000-0000-0000-000000000000
     * CreatedByUserName : null
     * CreatedAt : 2017-03-21T16:07:07
     * UpdatedBy : null
     * UpdatedByUserName : null
     * UpdatedAt : 2017-03-21T16:07:07
     * IsAdd : false
     * Remark : null
     * Status : 1
     * IsConvertToNullGUID : true
     * Token : ebb634cd-f1f0-4e96-81dd-83c68ef3faf1
     * TimeStamp : 1490112443
     * Signature : nAihBCgQ%2fRLI%2bqPPKy5ItA%3d%3d
     * UserID : 9bed94f1-bc33-4738-9760-31186749a7ac
     * Tag : null
     * IsFromCached : false
     * IsAuth : false
     * IsMustAuth : false
     * ErrorList : []
     * ValidationList : []
     */

    private Object ConfirmPassword;
    private String Name;
    private String UserName;
    private int UserType;
    private String UserTypeDID;
    private Object PrivRoleID;
    private Object PrivRoleName;
    private String NickName;
    private String Account;
    private Object ThemeColor;
    private String Password;
    private String SafetyPassword;
    private Object Email;
    private Object QQ;
    private Object WeChat;
    private String MobileNumber;
    private Object PhoneNumber;
    private int VisitNum;
    private int FansNum;
    private int FollowNum;
    private int Point;
    private int Credit;
    private Object FirstName;
    private Object MiddleName;
    private Object LastName;
    private String HeadPicUrl;
    private int SexType;
    private Object SelfIintroduction;
    private Object SelectedAppList;
    private Object SelectedModuleList;
    private Object UserAccountSelfRelateID;
    private String JoinInWorkAt;
    private boolean IsEnterpriseUser;
    private Object ProjectTypeList;
    private Object SkillsTypeList;
    private Object IndustryTypeList;
    private Object ProjectTypeNameList;
    private Object SkillsTypeNameList;
    private Object IndustryTypeNameList;
    private Object UserTypeName;
    private Object UserAccountSelfRelateName;
    private int PlatformType;
    private String FID;
    private String CreatedBy;
    private Object CreatedByUserName;
    private String CreatedAt;
    private Object UpdatedBy;
    private Object UpdatedByUserName;
    private String UpdatedAt;
    private boolean IsAdd;
    private Object Remark;
    private int Status;
    private boolean IsConvertToNullGUID;
    private String Token;
    private String TimeStamp;
    private String Signature;
    private String UserID;
    private String Tag;//1,未认证；2认证中；3已通过；4不通过
    private boolean IsFromCached;
    private boolean IsAuth;
    private boolean IsMustAuth;


    public Object getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(Object ConfirmPassword) {
        this.ConfirmPassword = ConfirmPassword;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUserName() {
        if (UserName == null)
            return "";
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int UserType) {
        this.UserType = UserType;
    }

    public String getUserTypeDID() {
        return UserTypeDID;
    }

    public void setUserTypeDID(String UserTypeDID) {
        this.UserTypeDID = UserTypeDID;
    }

    public Object getPrivRoleID() {
        return PrivRoleID;
    }

    public void setPrivRoleID(Object PrivRoleID) {
        this.PrivRoleID = PrivRoleID;
    }

    public Object getPrivRoleName() {
        return PrivRoleName;
    }

    public void setPrivRoleName(Object PrivRoleName) {
        this.PrivRoleName = PrivRoleName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String Account) {
        this.Account = Account;
    }

    public Object getThemeColor() {
        return ThemeColor;
    }

    public void setThemeColor(Object ThemeColor) {
        this.ThemeColor = ThemeColor;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getSafetyPassword() {
        return SafetyPassword;
    }

    public void setSafetyPassword(String SafetyPassword) {
        this.SafetyPassword = SafetyPassword;
    }

    public Object getEmail() {
        return Email;
    }

    public void setEmail(Object Email) {
        this.Email = Email;
    }

    public Object getQQ() {
        return QQ;
    }

    public void setQQ(Object QQ) {
        this.QQ = QQ;
    }

    public Object getWeChat() {
        return WeChat;
    }

    public void setWeChat(Object WeChat) {
        this.WeChat = WeChat;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String MobileNumber) {
        this.MobileNumber = MobileNumber;
    }

    public Object getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(Object PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public int getVisitNum() {
        return VisitNum;
    }

    public void setVisitNum(int VisitNum) {
        this.VisitNum = VisitNum;
    }

    public int getFansNum() {
        return FansNum;
    }

    public void setFansNum(int FansNum) {
        this.FansNum = FansNum;
    }

    public int getFollowNum() {
        return FollowNum;
    }

    public void setFollowNum(int FollowNum) {
        this.FollowNum = FollowNum;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int Point) {
        this.Point = Point;
    }

    public int getCredit() {
        return Credit;
    }

    public void setCredit(int Credit) {
        this.Credit = Credit;
    }

    public Object getFirstName() {
        return FirstName;
    }

    public void setFirstName(Object FirstName) {
        this.FirstName = FirstName;
    }

    public Object getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(Object MiddleName) {
        this.MiddleName = MiddleName;
    }

    public Object getLastName() {
        return LastName;
    }

    public void setLastName(Object LastName) {
        this.LastName = LastName;
    }

    public String getHeadPicUrl() {
        return HeadPicUrl;
    }

    public void setHeadPicUrl(String HeadPicUrl) {
        this.HeadPicUrl = HeadPicUrl;
    }

    public int getSexType() {
        return SexType;
    }

    public void setSexType(int SexType) {
        this.SexType = SexType;
    }

    public Object getSelfIintroduction() {
        return SelfIintroduction;
    }

    public void setSelfIintroduction(Object SelfIintroduction) {
        this.SelfIintroduction = SelfIintroduction;
    }

    public Object getSelectedAppList() {
        return SelectedAppList;
    }

    public void setSelectedAppList(Object SelectedAppList) {
        this.SelectedAppList = SelectedAppList;
    }

    public Object getSelectedModuleList() {
        return SelectedModuleList;
    }

    public void setSelectedModuleList(Object SelectedModuleList) {
        this.SelectedModuleList = SelectedModuleList;
    }

    public Object getUserAccountSelfRelateID() {
        return UserAccountSelfRelateID;
    }

    public void setUserAccountSelfRelateID(Object UserAccountSelfRelateID) {
        this.UserAccountSelfRelateID = UserAccountSelfRelateID;
    }

    public String getJoinInWorkAt() {
        return JoinInWorkAt;
    }

    public void setJoinInWorkAt(String JoinInWorkAt) {
        this.JoinInWorkAt = JoinInWorkAt;
    }

    public boolean isIsEnterpriseUser() {
        return IsEnterpriseUser;
    }

    public void setIsEnterpriseUser(boolean IsEnterpriseUser) {
        this.IsEnterpriseUser = IsEnterpriseUser;
    }

    public Object getProjectTypeList() {
        return ProjectTypeList;
    }

    public void setProjectTypeList(Object ProjectTypeList) {
        this.ProjectTypeList = ProjectTypeList;
    }

    public Object getSkillsTypeList() {
        return SkillsTypeList;
    }

    public void setSkillsTypeList(Object SkillsTypeList) {
        this.SkillsTypeList = SkillsTypeList;
    }

    public Object getIndustryTypeList() {
        return IndustryTypeList;
    }

    public void setIndustryTypeList(Object IndustryTypeList) {
        this.IndustryTypeList = IndustryTypeList;
    }

    public Object getProjectTypeNameList() {
        return ProjectTypeNameList;
    }

    public void setProjectTypeNameList(Object ProjectTypeNameList) {
        this.ProjectTypeNameList = ProjectTypeNameList;
    }

    public Object getSkillsTypeNameList() {
        return SkillsTypeNameList;
    }

    public void setSkillsTypeNameList(Object SkillsTypeNameList) {
        this.SkillsTypeNameList = SkillsTypeNameList;
    }

    public Object getIndustryTypeNameList() {
        return IndustryTypeNameList;
    }

    public void setIndustryTypeNameList(Object IndustryTypeNameList) {
        this.IndustryTypeNameList = IndustryTypeNameList;
    }

    public Object getUserTypeName() {
        return UserTypeName;
    }

    public void setUserTypeName(Object UserTypeName) {
        this.UserTypeName = UserTypeName;
    }

    public Object getUserAccountSelfRelateName() {
        return UserAccountSelfRelateName;
    }

    public void setUserAccountSelfRelateName(Object UserAccountSelfRelateName) {
        this.UserAccountSelfRelateName = UserAccountSelfRelateName;
    }

    public int getPlatformType() {
        return PlatformType;
    }

    public void setPlatformType(int PlatformType) {
        this.PlatformType = PlatformType;
    }

    public String getFID() {
        return FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public Object getCreatedByUserName() {
        return CreatedByUserName;
    }

    public void setCreatedByUserName(Object CreatedByUserName) {
        this.CreatedByUserName = CreatedByUserName;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public Object getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(Object UpdatedBy) {
        this.UpdatedBy = UpdatedBy;
    }

    public Object getUpdatedByUserName() {
        return UpdatedByUserName;
    }

    public void setUpdatedByUserName(Object UpdatedByUserName) {
        this.UpdatedByUserName = UpdatedByUserName;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }

    public boolean isIsAdd() {
        return IsAdd;
    }

    public void setIsAdd(boolean IsAdd) {
        this.IsAdd = IsAdd;
    }

    public Object getRemark() {
        return Remark;
    }

    public void setRemark(Object Remark) {
        this.Remark = Remark;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public boolean isIsConvertToNullGUID() {
        return IsConvertToNullGUID;
    }

    public void setIsConvertToNullGUID(boolean IsConvertToNullGUID) {
        this.IsConvertToNullGUID = IsConvertToNullGUID;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String Signature) {
        this.Signature = Signature;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public boolean isIsFromCached() {
        return IsFromCached;
    }

    public void setIsFromCached(boolean IsFromCached) {
        this.IsFromCached = IsFromCached;
    }

    public boolean isIsAuth() {
        return IsAuth;
    }

    public void setIsAuth(boolean IsAuth) {
        this.IsAuth = IsAuth;
    }

    public boolean isIsMustAuth() {
        return IsMustAuth;
    }

    public void setIsMustAuth(boolean IsMustAuth) {
        this.IsMustAuth = IsMustAuth;
    }


}
