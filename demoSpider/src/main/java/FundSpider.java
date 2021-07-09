import entity.FundInfo;
import entity.Worth;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.HttpClient;
import utils.RegularExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * 基金的辅助类
 */
public class FundSpider {

    /**
     * 获得基金最新信息
     * 表达式参考：https://www.cnblogs.com/zhangyinhua/p/8037599.html
     * 官网参考：https://jsoup.org/
     *
     * @return
     */
    public static FundInfo getInfo() {
        String urlStr = "http://finance.sina.com.cn/fund/quotes/001643/bc.shtml";
        String html = HttpClient.readHtml(urlStr);
        Document doc = Jsoup.parse(html);
        String name = doc.select("h1.fund_name").first().text();
        String funCode = doc.select("span.fund_code").first().text();
        funCode = RegularExpression.findByFirst(funCode, "\\((.*?)\\)");

        Element ele3 = doc.select("#fund_info_blk2").first();
        String worth = ele3.select("span.fund_data").get(0).text(); //单位净值
        String upAndDown = ele3.select("span.fund_data").get(1).text(); //涨跌幅
        String up_3month = ele3.select("span.fund_data").get(2).text(); //近3月涨幅
        String up_1year = ele3.select("span.fund_data").get(3).text(); //近1年涨幅
        String up_3year = ele3.select("span.fund_data").get(4).text(); //近3年涨幅
        String dataDate = doc.select("div.fund_data_date").first().text();//数据日期

//        System.out.println(name);
//        System.out.println(funCode);
//        System.out.println(worth);
//        System.out.println(upAndDown);
//        System.out.println(up_3month);
//        System.out.println(up_1year);
//        System.out.println(up_3year);
//        System.out.println(dataDate);

        FundInfo f = new FundInfo();
        f.name = name;
        f.fundCode = funCode;
        f.worth = Float.parseFloat(worth);
        f.upAndDown = upAndDown;
        f.up_3month = up_3month;
        f.up_1year = up_1year;
        f.up_3year = up_3year;
        f.dataDate = dataDate;

        return f;
    }

    /**
     * 获得历史净值
     */
    public static List<Worth> getHistoryWorth(String fundCode, String datefrom, String dateto, int pageIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://stock.finance.sina.com.cn/fundInfo/api/openapi.php/CaihuiFundInfoService.getNav?callback=jQuery11120453442464017479_1625757081906");
        sb.append(String.format("&symbol=%s", fundCode));
        sb.append(String.format("&datefrom=%s", datefrom));
        sb.append(String.format("&dateto=%s", dateto));
        sb.append(String.format("&page=%s", pageIndex));
        sb.append("&_=1625757081919");
        String content = HttpClient.readHtml(sb.toString());
        if (content != null) {
            content = content.substring(content.indexOf("(") + 1);
            content = content.substring(0, content.length() - 1);
            System.out.println(content);

            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONObject("data").getJSONArray("data");
            List<Worth> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                String fbrq = item.getString("fbrq");// 日期
                float jjjz = item.getFloat("jjjz");// 单位净值
                //String ljjz = item.getString("ljjz");// 累计净值
                Worth worth = new Worth(fbrq, jjjz);
                list.add(worth);
                System.out.println(fbrq);
            }
            return list;
        }
        return null;
    }


}
