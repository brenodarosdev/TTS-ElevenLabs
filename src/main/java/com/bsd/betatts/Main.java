package com.bsd.betatts;

import java.net.http.HttpResponse;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String filePath = "C:/Users/bsd20/Music/AI-TTS/";
        String fileName;
        String text;

        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Digite o texto que será transformado em voz: ");
            text = scan.nextLine();

            System.out.println("\nDigite o nome do arquivo: ");
            fileName = scan.nextLine();
        }

        HttpResponse<byte[]> response = ApiRequest.request(text);

        if (response.statusCode() == 200) {
            FileSaver.MP3Saver(response.body(), filePath + fileName + ".mp3");
            System.out.println("Arquivo MP3 salvo com sucesso.");
        } else {
            System.err.println("Erro na solicitação: " + response.statusCode());
        }
    }

    // public static boolean fileExists(String filePath) {
    // Path path = Paths.get(filePath);
    // return Files.exists(path);
    // }
}  