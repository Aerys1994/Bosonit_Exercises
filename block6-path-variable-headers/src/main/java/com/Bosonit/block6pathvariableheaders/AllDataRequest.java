package com.Bosonit.block6pathvariableheaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AllDataRequest {
    @PostMapping("/all")
    public ResponseEntity<AllDataResponse> allDataRequest(
            @RequestBody(required = false) String body,
            @RequestParam(required = false) List<String> requestParams,
            @RequestHeader(name = "h1", defaultValue = "") String h1,
            @RequestHeader(name = "h2", defaultValue = "") String h2
    ) {
        AllDataResponse response = new AllDataResponse();
        response.setBody(body);
        response.setHeaders(List.of(h1, h2));
        response.setRequestParams(requestParams);

        return ResponseEntity.ok(response);
    }

    public static class AllDataResponse {
        private String body;
        private List<String> headers;
        private List<String> requestParams;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<String> getHeaders() {
            return headers;
        }

        public void setHeaders(List<String> headers) {
            this.headers = headers;
        }

        public List<String> getRequestParams() {
            return requestParams;
        }

        public void setRequestParams(List<String> requestParams) {
            this.requestParams = requestParams;
        }
    }


}
