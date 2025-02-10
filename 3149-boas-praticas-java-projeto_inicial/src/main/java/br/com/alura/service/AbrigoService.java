package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AbrigoService {

    ClientHttpConfiguration clientHttpConfiguration = new ClientHttpConfiguration();

    private void listarAbrigos() throws IOException, InterruptedException {
        HttpResponse<String> response = clientHttpConfiguration.dispararRequisiçãoGet("http://localhost:8080/abrigos");
        String responseBody = response.body();
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        System.out.println("Abrigos cadastrados:");
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            String nome = jsonObject.get("nome").getAsString();
            String telefone = jsonObject.get("telefone").getAsString();
            String email = jsonObject.get("email").getAsString();
            String endereco = jsonObject.get("endereco").getAsString();

            Abrigo abrigo = new Abrigo(nome, telefone, email, endereco);
            System.out.println(abrigo.getId() +" - " +nome);
        }
    }

    public void listarAbrigo() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = clientHttpConfiguration.dispararRequisiçãoGet( uri);
        String responseBody = response.body();
        Abrigo[] abrigos = new ObjectMapper().readValue(responseBody, Abrigo[].class);
        List<Abrigo> abrigoList = Arrays.stream(abrigos).toList();
        System.out.println("Abrigos cadastrados:");
        for (Abrigo abrigo : abrigoList) {

            long id = abrigo.getId();
            String nome = abrigo.getNome();
            System.out.println(id +" - " +nome);
        }
    }

    public void cadastrarAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();
        System.out.println("Digite o endereco do abrigo:");
        String endereco = new Scanner(System.in).nextLine();

        Abrigo abrigo = new Abrigo(nome,endereco, telefone, email);

        HttpClient client = HttpClient.newHttpClient();
        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = clientHttpConfiguration.dispararRequisiçãoPost(uri, abrigo);
        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }



}
