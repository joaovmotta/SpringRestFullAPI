package br.com.example.SpringBootH2;

import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.HttpStatusCode;
import org.springframework.http.HttpMethod;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class TestUtil {

    private static final String MOCK_SERVER_HOST = "localhost";
    private static final Integer MOCK_SERVER_PORT = 1080;

    private void createMockServer(
            HttpMethod requestMethod,
            String uri, String responseBody,
            HttpStatusCode responseStatus,
            MockServerClient mockServerClient
    ) {
        mockServerClient
                .when(
                        request()
                                .withMethod(requestMethod.name())
                                .withPath(uri)
                )
                .respond(
                        response()
                                .withStatusCode(responseStatus.code())
                                .withBody(responseBody)
                );
    }

    public class MockServer {
        private HttpMethod requestMethod;
        private HttpStatusCode responseStatus;
        private String uri;
        private String responseBody;
        private Boolean reset;

        public MockServer withRequestMethod(HttpMethod requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public MockServer withResponseStatus(HttpStatusCode responseStatus) {
            this.responseStatus = responseStatus;
            return this;
        }

        public MockServer withUri(String uri) {
            this.uri = uri;
            return this;
        }

        public MockServer withResponseBody(String responseBody) {
            this.responseBody = responseBody;
            return this;
        }

        public MockServer withReset(Boolean reset) {
            this.reset = reset;
            return this;
        }

        public void create() {
            if (reset) {
                createMockServer(requestMethod, uri, responseBody, responseStatus, new MockServerClient(MOCK_SERVER_HOST, MOCK_SERVER_PORT).reset());
            } else {
                createMockServer(requestMethod, uri, responseBody, responseStatus, new MockServerClient(MOCK_SERVER_HOST, MOCK_SERVER_PORT));
            }

        }
    }

    public MockServer createMockServer() {
        return new MockServer();
    }
}
