package com.bsd.betatts;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String nomeArquivo;
        String texto;
        HttpClient client = HttpClient.newHttpClient();

        try (Scanner scan = new Scanner(System.in)) {
            System.out.print("Texto: ");
            texto = scan.nextLine();

            System.out.print("Nome do arquivo: ");
            nomeArquivo = scan.nextLine();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.elevenlabs.io/v1/text-to-speech/E22eJe3xCK5ZLq113k7l?output_format=mp3_44100_64"))
                .header("xi-api-key", "08cea0721d40cd50ef5e982936cb5123")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\n  \"model_id\": \"eleven_multilingual_v2\",\n  \"text\": \"" + texto + "\",\n  \"voice_settings\": {\n    \"similarity_boost\": 0.85,\n    \"stability\": 0.4,\n    \"style\": 0.7,\n    \"use_speaker_boost\": true\n  }\n}"))
                .build();

        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

        // Verifique se a chamada foi bem-sucedida (status code 200)
        if (response.statusCode() == 200) {
            // Salva o conteúdo do MP3 em um arquivo
            saveMP3ToFile(response.body(), "C:/Users/bsd20/OneDrive/Arquivos/Programação/projetos/" + nomeArquivo + ".mp3");
            System.out.println("Arquivo MP3 salvo com sucesso.");

            // Aqui você pode reproduzir o arquivo salvo ou realizar outras operações conforme necessário.
        } else {
            System.err.println("Erro na solicitação: " + response.statusCode());
        }
    }

    public static void saveMP3ToFile(byte[] mp3Content, String filePath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(mp3Content);
        }
    }
}
