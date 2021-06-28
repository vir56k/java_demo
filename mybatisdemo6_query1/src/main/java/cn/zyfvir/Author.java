package cn.zyfvir;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/26 14:11
 */
public class Author {
    public long id;
    public String userName;
    public String userInfo;


    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userInfo='" + userInfo + '\'' +
                '}';
    }
}
