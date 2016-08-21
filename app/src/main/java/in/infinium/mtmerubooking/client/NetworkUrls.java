package in.infinium.mtmerubooking.client;

/**
 * Created by admin on 8/20/2016.
 */
public class NetworkUrls {

    public static String BaseUrl = "http://122.169.111.90:90/api/MTGASApi/";
    public static String loginUrl = BaseUrl + "Login?";
    public static String countryUrl = BaseUrl + "GetCountryList";
    public static String cityUrl = BaseUrl + "GetCityList?Id=";
    public static String registerUrl = BaseUrl + "Regester";
    public static String registerListUrl = BaseUrl + "GetRegesterList";
}
