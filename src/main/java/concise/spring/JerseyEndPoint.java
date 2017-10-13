package concise.spring;

import concise.database.DatabaseConnection;
import concise.database.CustomData;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

@Component
@Path("/data")
public class JerseyEndPoint {

    @POST
    @Consumes("application/json")
    public String saveCustomDataIntoDatabase(CustomData customData) {
        String data = customData.getData();
        String SQL = "INSERT INTO concise.data (data) VALUES ( '" + data + "' )";
        DatabaseConnection dbc = new DatabaseConnection();
        dbc.performDatabaseInsert(SQL);

        return "You inserted: " + data;
    }

    @GET
    @Produces("application/json")
    public JSONArray getSearchResultsFromDatabase(@QueryParam("search") String search) {
        String SQL = "SELECT * FROM concise.data WHERE data LIKE '%" + search + "%'";
        DatabaseConnection dbc = new DatabaseConnection();
        List<String> searchResults = dbc.performDatabaseSelect(SQL);
        JSONArray jsonResultsArray = new JSONArray();

        for (String result : searchResults) {
            jsonResultsArray.put(result);
        }

        return jsonResultsArray;
    }

}
