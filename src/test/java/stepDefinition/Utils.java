package stepDefinition;








import apiModels.Network;
import apiModels.Networks;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Utils {

    private static URL url;
    private static HttpURLConnection con;
    private static ObjectMapper objectMapper;
    private static Networks networks;
    private static Network network;
    public static boolean hitURL(){

        try {
            url = new URL("http://api.citybik.es/v2/networks");
            con = (HttpURLConnection) url.openConnection();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



    public static  int getResponse() {

        try {

            return con.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static  Networks getResponseData() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
               con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine())!=null){
                response.append(inputLine);
            }
            objectMapper = new ObjectMapper();
             networks= objectMapper.readValue(response.toString(),Networks.class);
            return networks;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean getStationInfo(String networkId) {
        boolean flag = false;
        for(Network network2 : networks.getNetworks()){
            network = network2;
            if(network.getId().equals(networkId)){
                    flag = true;
                    break;
              //  assertThat(network.getLocation().getCountry(),equalTo(data.get("country")));
               // assertThat(network.getLocation().getLatitude(),equalTo(Double.valueOf(data.get("latitude"))));
               // assertThat(network.getLocation().getLongitude(),equalTo(Double.valueOf(data.get("longitude"))));
            }

        }
        return flag;
    }

    public static Boolean getLocationInfo(String city, String country) {
        boolean flag = false;

            if(network.getLocation().getCity().equals(city)&&network.getLocation().getCountry().equals(country)){
                flag = true;

                //  assertThat(network.getLocation().getCountry(),equalTo(data.get("country")));
                // assertThat(network.getLocation().getLatitude(),equalTo(Double.valueOf(data.get("latitude"))));
                // assertThat(network.getLocation().getLongitude(),equalTo(Double.valueOf(data.get("longitude"))));
            }


        return flag;
    }

    public static boolean getLocationPosition(String lat, String lon) {
        boolean flag = false;

        if(network.getLocation().getLatitude().equals(Double.parseDouble(lat)) && network.getLocation().getLongitude().equals(Double.parseDouble(lon))){
            flag = true;

            //  assertThat(network.getLocation().getCountry(),equalTo(data.get("country")));
            // assertThat(network.getLocation().getLatitude(),equalTo(Double.valueOf(data.get("latitude"))));
            // assertThat(network.getLocation().getLongitude(),equalTo(Double.valueOf(data.get("longitude"))));
        }


        return flag;
    }

}
