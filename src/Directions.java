import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Directions {

    //final static Logger logger = Logger.getLogger(TestSysOutToLog4J.class.getName());

    public static void main(String[] args) {

        String[] cityCodes;
        List<String> cities = new ArrayList<String>();
   //     String username = "admin";
   //     String password = "admin";
       // String url = "http://va1*company*dweb105-nat.*company*ext.global:4502/libs/wcm/core/content/sites/createpagewizard/_jcr_content";
   //     String path = "/content/intercontinental/hotels/en_US/";
    //    String cityName = "porto";
    //    String hotelCode = "prtha";
   //     String endPart = "/hoteldetail&template=/apps/intercontinental/templates/hoteldetail/directions&template@Delete=&pageName=Directions&./jcr:title=Directions&./cq:tags%40TypeHint=String%5B%5D&./cq:tags%40Delete=&./pageTitle=&./navTitle=&./sling:alias=&:";
    //    String crxToken = "cq_csrf_token=eyJleHAiOjE1NjcxODM4NjUsImlhdCI6MTU2NzE4MzI2NX0.f3kIwGhiEagjKPAte1NkBquh6rnu3U8i3xeJcLQTefU";
        String parentPath =  "parentPath=/content/intercontinental/hotels/zh_TW/";//ar_AE,zh_CN,ja_JP//de_DE en_GB en_US// fr_FR in_ID it_IT // iw_IL ko_KR nl_NL // pt_PT ru_RU th_TH zh_CN zh_TW
        String template= "/hoteldetail&template=/apps/intercontinental/templates/hoteldetail/directions&template@Delete=&pageName=directions";


        String output = "";
        try {

            // URL url = new URL("https://www.*company*.com/guestapi/v1/crowneplaza/us/en/web/hoteldetails?hotelcode=DTTYZ");
            URL url = new URL("https://int-api.*company*.com/channels/direct/hotelsearch/v1/activehotels?brandCode=ic");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("x-*company*-api-key", "Az7F28mBx20mgFDY8pUa6A9LAXsQykJg");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            while ((output = br.readLine()) != null) {
                String city = output.substring(output.indexOf(":") + 3, output.length() - 2);
                cityCodes = city.split(",");
          //      System.out.println("cityCodes are  " + cityCodes.length);
               // cityCodes = new String[]{"DTTYZ"};   // uncomment to have all the cities
                for (int i = 0; i < cityCodes.length; i++) {
           //         System.out.println(cityCodes[i]);
                    cityCodes[i] = cityCodes[i].substring(1, cityCodes[i].length() - 1);
                    URL urlHotelCode = new URL("https://www.*company*.com/guestapi/v1/crowneplaza/us/en/web/hoteldetails?hotelcode=" + cityCodes[i]);
                    //     URL url = new URL("https://int-api.*company*.com/channels/direct/hotelsearch/v1/activehotels?brandCode=ic");
                    HttpURLConnection conn2 = (HttpURLConnection) urlHotelCode.openConnection();
                    conn2.setRequestMethod("GET");
                    conn2.setRequestProperty("Accept", "application/json");
                    //   conn.setRequestProperty("x-*company*-api-key", "Az7F28mBx20mgFDY8pUa6A9LAXsQykJg");
                    if (conn2.getResponseCode() != 200) {
                        System.out.println("-------!!! ISSUE------------- didn't receive response code for " + cityCodes[i]);
                        continue;
                        //throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                    }

                    BufferedReader br2 = new BufferedReader(new InputStreamReader(
                            (conn2.getInputStream())));

                    while ((output = br2.readLine()) != null) {
              //          System.out.println("output is   " + output);
                        int index = output.indexOf("\"", output.indexOf("seoCityName", 0));
                        int index2 = output.indexOf(",", index);
                        String hotelCityName = output.substring(index + 3, index2 - 1);
                //        System.out.println("Extracted City/HotelCode : " + hotelCityName  + "/" + cityCodes[i]); //":"cozumel" -> cozumel
                        executeCurlCommand(parentPath + hotelCityName + "/" + cityCodes[i].toLowerCase()  + template);
                        cities.add((output.substring(index + 3, index2 - 1)));
                    }
                }
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
     //   System.out.println(cities.size());
     //   cities.forEach(name -> System.out.println(name.toLowerCase()));
    //    executeCurlCommand(parentPath);
        //  curl -u admin:admin 'http://va1*company*dweb105-nat.*company*ext.global:4502/libs/wcm/core/content/sites/createpagewizard/_jcr_content' --data 'parentPath=/content/intercontinental/hotels/en_US/london/lonhb/hoteldetail&template=/apps/intercontinental/templates/hoteldetail/directions&template@Delete=&pageName=Directions&./jcr:title=Directions&./cq:tags%40TypeHint=String%5B%5D&./cq:tags%40Delete=&./pageTitle=&./navTitle=&./sling:alias=&:cq_csrf_token=eyJleHAiOjE1NjcxODM4NjUsImlhdCI6MTU2NzE4MzI2NX0.f3kIwGhiEagjKPAte1NkBquh6rnu3U8i3xeJcLQTefU' --compressed --insecure
    }

    static void executeCurlCommand(String parentPath){
        try{
            System.out.println("path-> " + parentPath );
            ProcessBuilder pb = new ProcessBuilder("curl", "-u", "admin:admin", "http://va1*company*dweb118-nat.*company*ext.global:4503/libs/wcm/core/content/sites/createpagewizard/_jcr_content", "--data", parentPath);
            pb.redirectErrorStream(true);
            Process p = pb.start();

        } catch (IOException e) {
            System.out.print("error IO Exception      ");
            e.printStackTrace();
        }
    }
}

