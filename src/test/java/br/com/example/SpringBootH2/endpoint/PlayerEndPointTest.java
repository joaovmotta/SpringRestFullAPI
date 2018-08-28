package br.com.example.SpringBootH2.endpoint;

import br.com.example.SpringBootH2.TestUtil;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpStatusCode.OK_200;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class PlayerEndPointTest {

    private static final String API_URI = "http://localhost:1080/api/player";

    private static final String LOCATION = "Location";

    private static ClientAndServer mockServer;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeClass
    public static void startServer() {
        mockServer = startClientAndServer(1080);
    }

    @AfterClass
    public static void stopServer() {
        mockServer.stop();
    }

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenGetAllPlayersShouldReturnStatusOK() throws Exception {


        new TestUtil().createMockServer().withReset(true)
                .withRequestMethod(GET)
                .withResponseStatus(OK_200)
                .withUri(API_URI)
                .create();

        mockMvc.perform(
                MockMvcRequestBuilders.get(API_URI)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetPlayerByIdShouldReturnStatusOK() throws Exception {
        new TestUtil().createMockServer().withReset(true)
                .withRequestMethod(GET)
                .withResponseStatus(OK_200)
                .withUri(API_URI)
                .create();

        mockMvc.perform(
                MockMvcRequestBuilders.get(API_URI.concat("/1"))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }
}