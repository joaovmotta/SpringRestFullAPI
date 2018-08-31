package br.com.example.SpringBootH2.endpoint;

import br.com.example.SpringBootH2.util.FileUtil;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import static br.com.example.SpringBootH2.representation.DefaultErrorMessages.*;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PlayerEndPointTest {

    private static final String API_URI = "http://localhost:1080/api/player";

    private static final String LOCATION = "Location";

    private static ClientAndServer mockServer;

    private static Integer SERVER_PORT = 1080;

    private static final String JSON_FILE_DIRECTORY = "/json/createPlayerRequest.json";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeClass
    public static void startServer() {
        mockServer = startClientAndServer(SERVER_PORT);
    }

    @AfterClass
    public static void stopServer() {
        mockServer.stop();
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenGetAllPlayersShouldReturnArrayAndStatusOK() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get(API_URI)
        )
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void whenGetPlayerByIdShouldReturnOnlyOnePlayerAndStatusOK() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get(UriComponentsBuilder.fromHttpUrl(API_URI).pathSegment("3").toUriString())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void whenCreatePlayerShouldReturnCreated() throws Exception {

        String jsonFileRequest = FileUtil.fetchScript(JSON_FILE_DIRECTORY);

        JSONObject jsonObject = new JSONObject(jsonFileRequest);

        mockMvc.perform(
                MockMvcRequestBuilders.post(API_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString())
        )
                .andExpect(header().exists(LOCATION))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenCreatePlayerWithPresentOrFutureDateShouldReturnBadRequest() throws Exception {

        String jsonFileRequest = FileUtil.fetchScript(JSON_FILE_DIRECTORY);

        JSONObject jsonObject = new JSONObject(jsonFileRequest);
        jsonObject.remove("birthday");
        jsonObject.put("birthday", "10/10/2019");

        mockMvc.perform(
                MockMvcRequestBuilders.post(API_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString())
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(FUTURE_OR_PRESENT_DATE));
    }

    @Test
    public void whenCreatePlayerWithHeightLessThanOneHundredFiftyShouldReturnBadRequest() throws Exception {

        String jsonFileRequest = FileUtil.fetchScript(JSON_FILE_DIRECTORY);

        JSONObject jsonObject = new JSONObject(jsonFileRequest);
        jsonObject.remove("height");
        jsonObject.put("height", "145");

        mockMvc.perform(
                MockMvcRequestBuilders.post(API_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString())
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(HEIGHT_LESS_THAN_ONE_HUNDRED_AND_FIFITY));
    }

    @Test
    public void whenCreatePlayerWithWeightLessThanFiftyShouldReturnBadRequest() throws Exception {

        String jsonFileRequest = FileUtil.fetchScript(JSON_FILE_DIRECTORY);

        JSONObject jsonObject = new JSONObject(jsonFileRequest);
        jsonObject.remove("weight");
        jsonObject.put("weight", "49");

        mockMvc.perform(
                MockMvcRequestBuilders.post(API_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString())
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(WEIGHT_LESS_THAN_FIFITY));
    }

    @Test
    public void whenCreatePlayerWithSomeNullValueShouldReturnBadRequest() throws Exception {

        String jsonFileRequest = FileUtil.fetchScript(JSON_FILE_DIRECTORY);

        JSONObject jsonObject = new JSONObject(jsonFileRequest);
        jsonObject.remove("name");

        mockMvc.perform(
                MockMvcRequestBuilders.post(API_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString())
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(REQUIRED_FIELD_IS_NULL));
    }

    @Test
    public void whenGetPlayerByNonexistentIdShouldReturnNotFound() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get(UriComponentsBuilder.fromHttpUrl(API_URI).pathSegment("20").toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(RESOURCE_NOT_FOUND));
    }

    @Test
    public void whenDeletePlayerByIdShouldReturnStatusOK() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.delete(UriComponentsBuilder.fromHttpUrl(API_URI).pathSegment("1").toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeletePlayerByNonexistentIdShouldReturnNotFound() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.delete(UriComponentsBuilder.fromHttpUrl(API_URI).pathSegment("20").toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(RESOURCE_NOT_FOUND));
    }

    @Test
    public void whenUpdatePlayerByNonexistentIdShouldReturnNotFound() throws Exception {

        String jsonFileRequest = FileUtil.fetchScript(JSON_FILE_DIRECTORY);

        JSONObject jsonObject = new JSONObject(jsonFileRequest);
        mockMvc.perform(
                MockMvcRequestBuilders.put(UriComponentsBuilder.fromHttpUrl(API_URI).pathSegment("20").toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString())
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(RESOURCE_NOT_FOUND));
    }

    @Test
    public void whenUpdatePlayerByIdShouldReturnStatusOk() throws Exception {

        String jsonFileRequest = FileUtil.fetchScript(JSON_FILE_DIRECTORY);

        JSONObject jsonObject = new JSONObject(jsonFileRequest);
        mockMvc.perform(
                MockMvcRequestBuilders.put(UriComponentsBuilder.fromHttpUrl(API_URI).pathSegment("2").toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString())
        )
                .andExpect(status().isOk())
                .andExpect(header().exists(LOCATION));
    }
}