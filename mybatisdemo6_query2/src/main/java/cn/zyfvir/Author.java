package cn.zyfvir;

import java.util.List;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/26 14:11
 */
public class Author {
    public long id;
    public String userName;
    public String userInfo;
    public List<Blog> blogList;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Blog blog : blogList) {
            sb.append(blog).append(",");
        }
        sb.append("]");
        return "Author{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", blogList='" + sb.toString() + '\'' +
                '}';
    }
}
