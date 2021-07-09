import entity.FundInfo;

/**
 *
 */
public class MainClass {
    public static void main(String[] args) {
        printf("starting...");
//        List<Worth> historyWorth = FundHelper.getHistoryWorth("000025", "2021-03-01", "2021-07-08", 1);
//        PrintUtil.printf2(historyWorth);
        //        HttpClient httpClient = new HttpClient()

        FundInfo info = FundSpider.getInfo();
        printf(info.toString());
    }

    private static void printf(String str) {
        System.out.println(str);
    }
}
