package com.bsd.betatts;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequest {

    public static HttpResponse<byte[]> request(String text) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.elevenlabs.io/v1/text-to-speech/gnH3TsnzBnedo6JlMdlv?output_format=mp3_44100_64"))
                .header("xi-api-key", "08cea0721d40cd50ef5e982936cb5123")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\n  \"model_id\": \"eleven_multilingual_v2\",\n  \"text\": \"" + text + "\",\n  \"voice_settings\": {\n    \"similarity_boost\": 0.75,\n    \"stability\": 0.5,\n    \"style\": 0.0,\n    \"use_speaker_boost\": true\n  }\n}"))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofByteArray());
    }
}