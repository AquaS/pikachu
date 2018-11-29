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
    DataBaseError("000002", "数据库错误！"),

    LoginNameOrPassWordError("000100", "该用户不存在！"),
    UserHasNoPoints("000101","该用户没有积分！"),
    QuestionNotExist("000102","问题不存在"),

    BindExist("000300","该工号已被绑定"),
    BindNotExist("000301","该工号尚未绑定"),
    BindExistButNotThisWxid("000302","该工号已经绑定另一个微信ID"),
    BindExistForbiddenModify("000303","该工号已经绑定另一个微信ID,无权修改信息"),
    BindNotExistOrNoBranch("000303","该工号尚未绑定或者不在任何党支部内"),

    FileEmpty("000400","上传文件为空"),
    LimitPictureSize("000401","图片大小必须小于2M"),
    LimitPictureType("000402","图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'"),
    BranchIsNotExist("000403","该用户党支部不存在"),
    NewsModuleNotExist("000404","新闻模块不存在"),
    NewsNotExist("000405","新闻不存在"),
    VoteNotExist("000406","投票列表为空"),
    VoteDetailNotExist("000407","投票对象为空"),
    UserHasVoted("000408","该用户已投票"),
    UserHasSurveyed("000409","该用户已参与过该调研"),
    CookieIsNull("000410","cookie为空"),
    StudyModuleIsNull("000411", "当前不存在学习模块"),
    StudyNotExist("00412", "当前模块不存在考试"),
    NoQuestionInExam("00413", "当前考试不存在题目"),
    NoActivity("00414", "当前没有活动"),
    BeyondActivityLimit("00415", "超出活动最大人数，无法报名"),
    HasEnrolled("00416", "用户已经报名过"),
    HasLikedTopic("00417", "用户已点赞");

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
