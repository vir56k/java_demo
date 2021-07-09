package entity;

/**
 *
 */
public class Worth {
    public String date;
    public float worthValue;

    public Worth() {
    }

    public Worth(String date, float worthValue) {
        this.date = date;
        this.worthValue = worthValue;
    }

    @Override
    public String toString() {
        return "Worth{" +
                "date='" + date + '\'' +
                ", worthValue=" + worthValue +
                '}';
    }
}
