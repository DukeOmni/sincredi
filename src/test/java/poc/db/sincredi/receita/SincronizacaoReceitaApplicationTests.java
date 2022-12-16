package poc.db.sincredi.receita;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import poc.db.sincredi.receita.service.ReceitaService;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SincronizacaoReceitaApplicationTests {

    @Autowired
    ReceitaService receitaService;
    @Test
    void contextLoads() throws Exception{
        assertThat(receitaService).isNotNull();
    }
    @Test
    void check_file_reading_cabecario() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("D:\\projects\\intelij\\sincredi\\receita\\src\\main\\resources\\exemplo.csv"));
        String cabecalhoArquivo = br.readLine();
        assertThat(cabecalhoArquivo).isEqualTo("agencia;conta;saldo;status");
    }
    @Test
    void check_receita_service_formato_agencia() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("D:\\projects\\intelij\\sincredi\\receita\\src\\main\\resources\\exemplo.csv"));
        String cabecalhoArquivo = br.readLine();

        assertThat(receitaService.atualizarConta("0000","123456",Double.valueOf("100.00"),"A")).isTrue();

        assertThat(receitaService.atualizarConta("000","123456",Double.valueOf("100.00"),"A")).isFalse();
        assertThat(receitaService.atualizarConta("00","123456",Double.valueOf("100.00"),"A")).isFalse();
        assertThat(receitaService.atualizarConta("0","123456",Double.valueOf("100.00"),"A")).isFalse();
        assertThat(receitaService.atualizarConta(null,"123456",Double.valueOf("100.00"),"A")).isFalse();
    }
    @Test
    void check_receita_service_formato_conta() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("D:\\projects\\intelij\\sincredi\\receita\\src\\main\\resources\\exemplo.csv"));
        String cabecalhoArquivo = br.readLine();

        assertThat(receitaService.atualizarConta("0000","123456",Double.valueOf("100.00"),"A")).isTrue();

        assertThat(receitaService.atualizarConta("0000","12345",Double.valueOf("100.00"),"A")).isFalse();
        assertThat(receitaService.atualizarConta("0000","1234",Double.valueOf("100.00"),"A")).isFalse();
        assertThat(receitaService.atualizarConta("0000","123",Double.valueOf("100.00"),"A")).isFalse();
        assertThat(receitaService.atualizarConta("0000","12",Double.valueOf("100.00"),"A")).isFalse();
        assertThat(receitaService.atualizarConta("0000","1",Double.valueOf("100.00"),"A")).isFalse();
        assertThat(receitaService.atualizarConta("0000",null,Double.valueOf("100.00"),"A")).isFalse();
    }
    @Test
    void check_receita_service_formato_status() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("D:\\projects\\intelij\\sincredi\\receita\\src\\main\\resources\\exemplo.csv"));
        String cabecalhoArquivo = br.readLine();

        assertThat(receitaService.atualizarConta("0000","123456",Double.valueOf("100.00"),"A")).isTrue();
        assertThat(receitaService.atualizarConta("0000","123456",Double.valueOf("100.00"),"I")).isTrue();
        assertThat(receitaService.atualizarConta("0000","123456",Double.valueOf("100.00"),"B")).isTrue();
        assertThat(receitaService.atualizarConta("0000","123456",Double.valueOf("100.00"),"P")).isTrue();

    }

}
