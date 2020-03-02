/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Models.Developers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import jdk.nashorn.internal.parser.JSONParser;

/**
 * Jersey REST client generated for REST resource:DevelopersFacadeREST
 * [developers]<br>
 * USAGE:
 * <pre>
 *        DevRestClient client = new DevRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Chris
 */
public class DevRestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/DevRest/webresources";

    public DevRestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("developers");
    }

    public Response edit(Object requestEntity, String userId) throws javax.ws.rs.ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{userId})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public Developers find(Object requestEntity) throws javax.ws.rs.ClientErrorException {
        System.out.println("DevRestClient:find(byName?)");
        return (Developers) webTarget.path("validate").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class).getEntity();
    }

    public Response create(Object requestEntity) throws javax.ws.rs.ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public List<Developers> findAll() throws javax.ws.rs.ClientErrorException {
        System.out.println("DevRestClient:FindAll()");
        Response res = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
        int status = res.getStatus();
        HttpURLConnection entity = (HttpURLConnection) res.getEntity();

        System.out.println("got object");
        // List<Developers> listRes = (List<Developers>) res.getEntity();
        // System.out.println("Findall res:" + listRes.get(0));
        // return listRes;
        return null;
    }

    public List<Developers> findAllDevs() {

        try {
            // Get connection to REST service
            HttpURLConnection connection = (HttpURLConnection) new URL(BASE_URI + "/developers").openConnection();

            // Check status code
            int responseCode = connection.getResponseCode();
            InputStream inputStream;
            // If valid response, get response body, else error body
            if (200 <= responseCode && responseCode <= 299) {
                inputStream = connection.getInputStream();
            } else {
                inputStream = connection.getErrorStream();
            }

            // Build string from response body
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                response.append(currentLine);
            }
            in.close();
            String json = response.toString();
            
            // Map JSON to object
            List<Developers> dev = new ObjectMapper().readValue(json, new TypeReference<List<Developers>>(){});
            
            return dev;

        } catch (MalformedURLException ex) {
            Logger.getLogger(DevRestClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(DevRestClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Response remove(String userId) throws javax.ws.rs.ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{userId})).request().delete(Response.class);
    }

    public void close() {
        client.close();
    }

}
