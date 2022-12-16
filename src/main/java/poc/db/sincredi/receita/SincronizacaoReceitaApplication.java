/*
Cenário de Negócio:
Todo dia útil por volta das 6 horas da manhã um colaborador da retaguarda do Sicredi recebe e organiza as informações de 
contas para enviar ao Banco Central. Todas agencias e cooperativas enviam arquivos Excel à Retaguarda. Hoje o Sicredi 
já possiu mais de 4 milhões de contas ativas.
Esse usuário da retaguarda exporta manualmente os dados em um arquivo CSV para ser enviada para a Receita Federal, 
antes as 10:00 da manhã na abertura das agências.

Requisito:
Usar o "serviço da receita" (fake) para processamento automático do arquivo.

Funcionalidade:
0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma 
nova coluna.


Formato CSV:
agencia;conta;saldo;status
0101;12225-6;100,00;A
0101;12226-8;3200,50;A
3202;40011-1;-35,12;I
3202;54001-2;0,00;P
3202;00321-2;34500,00;B
...

*/
package poc.db.sincredi.receita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import poc.db.sincredi.receita.service.ReceitaService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

    //@Value("${csv.config.parser:';'}")
    private static String DELIMITER=";";
    public static void main(String[] args) {
        // Exemplo como chamar o "serviço" do Banco Central.
        // ReceitaService receitaService = new ReceitaService();
        // receitaService.atualizarConta("0101", "123456", 100.50, "A");

        SpringApplication.run(SincronizacaoReceitaApplication.class, args);
        ReceitaService receitaService = new ReceitaService();
        try {
            if (args.length>0 && args[0].endsWith(".csv")){
                String cabecalhoArquivo;
                String tuplasArquivo;

                BufferedReader br = new BufferedReader(new FileReader(args[0]));
                cabecalhoArquivo = br.readLine();
                while ((tuplasArquivo = br.readLine()) != null) {
                    String[] atributoTupla = tuplasArquivo.split(DELIMITER);
                    receitaService.atualizarConta(atributoTupla[0],atributoTupla[1],Double.valueOf(atributoTupla[2].replaceAll(",",".")),atributoTupla[3]);
                }
            }else{
                throw new Exception("Arquivo incompreensível");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
}
