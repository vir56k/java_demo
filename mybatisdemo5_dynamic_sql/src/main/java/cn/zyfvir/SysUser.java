package cn.zyfvir;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/26 14:11
 */
public class SysUser {
    public long id;
    public String userName;
    public String userPassword;
    public String userEmail;
    public String userInfo;

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userInfo='" + userInfo + '\'' +
                '}';
    }
}
