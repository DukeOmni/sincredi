# Prova técnica DB Informática

O projeto faz parte do esforço para demonstrar o nível técnico ao intrevistador DB Server

## Cenário de Negócio
Todo dia útil por volta das 6 horas da manhã um colaborador da retaguarda do Sicredi recebe e organiza as informações de
contas para enviar ao Banco Central. Todas agencias e cooperativas enviam arquivos Excel à Retaguarda. Hoje o Sicredi
já possiu mais de 4 milhões de contas ativas.
Esse usuário da retaguarda exporta manualmente os dados em um arquivo CSV para ser enviada para a Receita Federal,
antes as 10:00 da manhã na abertura das agências.

## Requisito
Usar o "serviço da receita" (fake) para processamento automático do arquivo.

Funcionalidade:
* Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
* Processa um arquivo CSV de entrada com o formato abaixo.
* Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
* Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma
   nova coluna.

## Notas de Implementação

* Foi criado uma aplicação Springboot standalone, empacotada através do maven, como pode ser verificado através dos arquivos na raiz do repositório
* A aplicação espera um argumento de linha de comando, esse argumento é um arquivo com a extensão csv. A ausência desse parâmetro, bem como qualquer arquivo que não seja .csv retornará o erro "Arquivo incomprerensível"

## Modo de Usar
* Faça o download do repositório
* Utilize o wrapper do maven através do comando ./mvnw.
* Além de utilizar o wrapper para criar o Jar. Também é possível cria-lo através da IDE
* Após gerar o .jar utilize o comando java -jar SincronizacaoReceita-<VERSÃO>-<SNAPSHOT> <input-file>
* Será gerado um arquivo com o nome 'result' no mesmo diretório onde o jar foi executado

## Testes
* Para avaliar os testes, basta entrar no arquivo SincronizacaoReceitaApplicationTests
* Para rodar os testes construa a build com o entry point a classe acima em vez da classe com sufixo Application

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0/maven-plugin/reference/html/)

