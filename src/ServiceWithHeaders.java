import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

public class ServiceWithHeaders {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url = "https://int-api.*company*.com/channels/direct/hotelsearchtmp/v1/activehotels?brandCode=va";
        String responseContent = null;
        System.out.println("testing now is fine ");
        HttpGet request = new HttpGet(url);
        request.setHeader("x-*company*-api-key","Az7F28mBx20mgFDY8pUa6A9LAXsQykJg");//x-*company*-api-key=Az7F28mBx20mgFDY8pUa6A9LAXsQykJg

       try{
           CloseableHttpResponse response = httpClient.execute(request);
           System.out.println("Response  is " + response);
           HttpEntity responseEntity = response.getEntity();
           responseContent = EntityUtils.toString(responseEntity, "UTF-8");
           System.out.println("Response  is " + responseContent);
       }
       catch(Exception e) {
        }
    }
}
