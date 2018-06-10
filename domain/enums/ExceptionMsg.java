package com.oee.pikachu.domain.enums;

/**
 * @Description:
 * @Auth: yangyanming
 * @Date: 2018/6/9
 * @Version: 1.0
 **/
public enum ExceptionMsg {
    SUCCESS("000000", "操作成功"),
    FAILED("999999","操作失败"),
    ParamError("000001", "参数错误！"),

    LoginNameOrPassWordError("000100", "该用户不存在！"),
    UserHasNoPoints("000101","该用户没有积分！"),
    UserNameUsed("000102","该登录名称已存在"),
    EmailNotRegister("000103","该邮箱地址未注册"),
    LinkOutdated("000104","该链接已过期，请重新请求"),
    PassWordError("000105","密码输入错误"),
    UserNameLengthLimit("000106","用户名长度超限"),
    LoginNameNotExists("000107","该用户未注册"),
    UserNameSame("000108","新用户名与原用户名一致"),

    FavoritesNameIsNull("000200","收藏夹名称不能为空"),
    FavoritesNameUsed("000201","收藏夹名称已被创建"),

    BindExist("000300","该工号已被绑定"),
    BindNotExist("000301","该工号尚未绑定"),
    BindExistButNotThisWxid("000302","该工号已经绑定另一个微信ID"),
    BindExistFobbidenMofify("000303","该工号已经绑定另一个微信ID,无权修改信息"),

    FileEmpty("000400","上传文件为空"),
    LimitPictureSize("000401","图片大小必须小于2M"),
    LimitPictureType("000402","图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'")
    ;
    private ExceptionMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
