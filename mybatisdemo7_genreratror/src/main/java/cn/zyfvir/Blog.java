package cn.zyfvir;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/28 17:23
 */
public class Blog {
    public long id;
    public String blogTitle;
    public String blogContent;
    public String authorId;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}
