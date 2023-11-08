package com.mycompany.a3teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SistemaDePessoas {

    public static void main(String[] args) {
        //Cria uma lista vazia de grupos [Necessário aprofundamento para explicar]
        Map<String, Set<String>> grupos = new HashMap<>();
        
        //Função mais robusta comparada ao Scanner para leitura do arquivo [Necessário aprofundamento para explicar]
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Alunos\\Documents\\NetBeansProjects\\a3Teste\\src\\main\\java\\com\\mycompany\\a3teste\\entrada1.txt"))) {
            //Variável que vai armazenada todo o conteúdo de cada linha do arquivo
            String linha;
            //While que será executado enquanto a última linha não chegar, semelhante ao feito pelo professor. 
            while ((linha = reader.readLine()) != null) {
                //Cria um vetor onde serão armazenados os valores de cada linha. O split quebra a string completa em elementos
                String[] tokens = linha.split(" ");
                //O comando sempre será o primeiro elemento do vetor. Portanto, o seu conteúdo é armazenado para decisão do que será executado.
                String comando = tokens[0];

                //Verifica se o primeiro elemento do vetor corresponde a grupo, indicando que um novo grupo de pessoas deve ser criado
                if ("grupo:".equals(comando)) {
                    // Comando para criar um grupo de pessoas
                    String nomeGrupo = tokens[1].replace(":", "");
                    //Cria uma lista do tipo Hash par armazenar as pessoas do grupo [Necessário aprofundamento para explicar]
                    Set<String> pessoas = new HashSet<>();
                    //Percorre todo o vetor de tokens e armazena cada elemento na lista pessoas criada anteriormente
                    for (int i = 0; i < tokens.length; i++) {
                        pessoas.add(tokens[i]);
                    }
                    //Insere a lista criada na variável global 'grupos'
                    grupos.put(nomeGrupo, pessoas);
                } 
                //Verifica se o primeiro elemento do vetor corresponde a existe, indicando que será realizada uma busca para verificar a exisência da pessoa nos grupos
                else if ("existe:".equals(comando)) {
                    // Atribui o nome da pessoa a variável 'nomePessoa'
                    String nomePessoa = tokens[1];
                    // Comando para verificar se uma pessoa existe, através do método 'pessoaExiste' passando os grupos e o nome da pessoa
                    if (pessoaExiste(grupos, nomePessoa)) {
                        System.out.println("[" + nomePessoa + "] existe!");
                    } else {
                        System.out.println("[" + nomePessoa + "] NÃO existe!");
                    }
                } 
                //Verifica se o primeiro elemento do vetor corresponde a conhece, indicando que será realizada uma comparação para verificar se as pessoas se conhecem (elas se conhecem caso pertençam ao mesmo grupo)
                else if ("conhece:".equals(comando)) {
                    //Atribui os nomes a variáveis
                    String nome1 = tokens[1];
                    String nome2 = tokens[2];
                   // Comando para verificar se uma pessoa existe, através do método 'pessoaExiste' passando os grupos e os nomes das pessoas
                    if (pessoasSeConhecem(grupos, nome1, nome2)) {
                        System.out.println("[" + nome1 + "] conhece [" + nome2 + "]");
                    } else {
                        System.out.println("[" + nome1 + "] NÃO conhece [" + nome2 + "]");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private static boolean pessoaExiste(Map<String, Set<String>> grupos, String nomePessoa) {
        //Faz uma busca sequencial para verificar se a pessoa existe ou não
        for (Set<String> pessoas : grupos.values()) {
            if (pessoas.contains(nomePessoa)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean pessoasSeConhecem(Map<String, Set<String>> grupos, String nome1, String nome2) {
        //Faz uma busca sequencial para verificar se as pessoas se conhecem ou não
        for (Set<String> pessoas : grupos.values()) {
            if (pessoas.contains(nome1) && pessoas.contains(nome2)) {
                return true;
            }
        }
        return false;
    }
}
