import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class CrptApi {

    private final long delta;
    private long requestsLockedTo;

    private final CrtpHttpClient crtpHttpClient;

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        long millis = timeUnit.toMillis(1);
        delta = millis / requestLimit;
        requestsLockedTo = 0;
        crtpHttpClient = new CrtpHttpClient();
    }

    public synchronized void request(Document document) throws InterruptedException, IOException, URISyntaxException {
        long now = System.currentTimeMillis();

        if (now < requestsLockedTo) {
            Thread.sleep(requestsLockedTo - now);
        }

        now = System.currentTimeMillis();


        crtpHttpClient.sendRequest(document);

        System.out.println("Запрос отправлен");
        System.out.println(LocalDateTime.now());
        requestsLockedTo = now + delta;
    }



    // Внутренние классы для Document
    public class Document {
        public Description description;
        public String docId;
        public String docStatus;
        public String docType;
        public boolean importRequest;
        public String ownerInn;
        public String participantInn;
        public String producerInn;
        public String productionDate;
        public String productionType;
        public Product[] products;
        public String regDate;
        public String regNumber;

        public class Description {
            public String participantInn;
        }

        public class Product {
            public String certificateDocument;
            public String certificateDocumentDate;
            public String certificateDocumentNumber;
            public String ownerInn;
            public String producerInn;

            public String productionDate;
            public String tnvedCode;
            public String uitCode;
            public String uituCode;
        }
    }

    public class CrtpHttpClient {

        private final HttpClient httpClient;
        private final ObjectMapper objectMapper;
        private static final String URI_ADDRESS = "https://ismp.crpt.ru/api/v3/lk/documents/create";
        private static final String HEADER_NAME = "Content-Type";
        private static final String HEADER_VALUE = "application/json";


        public CrtpHttpClient() {
            httpClient = HttpClient.newHttpClient();
            objectMapper = new ObjectMapper();
        }

        public void sendRequest(Document document) throws IOException, InterruptedException, URISyntaxException {
            String jsonDocument = objectMapper.writeValueAsString(document);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URI_ADDRESS))
                    .header(HEADER_NAME, HEADER_VALUE) // Установка заголовка Content-Type
                    .POST(HttpRequest.BodyPublishers.ofString(jsonDocument))
                    .build();

            httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
        }
    }
}
