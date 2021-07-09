package entity;

/**
 * 基金基本信息
 */
public class FundInfo {
    public String name;
    public String fundCode;
    public float worth;
    public String upAndDown;
    public String up_3month;
    public String up_1year;
    public String up_3year;
    public String dataDate;

    @Override
    public String toString() {
        return "FundInfo{" +
                "name='" + name + '\'' +
                ", fundCode='" + fundCode + '\'' +
                ", worth=" + worth +
                ", upAndDown='" + upAndDown + '\'' +
                ", up_3month='" + up_3month + '\'' +
                ", up_1year='" + up_1year + '\'' +
                ", up_3year='" + up_3year + '\'' +
                ", dataDate='" + dataDate + '\'' +
                '}';
    }
}
