package steps;

import br.com.ryoshino.entity.Cliente;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CriarClienteSteps {

    private Cliente cliente;

    @Dado("^que o cliente possui os atributos id (\\d+), nome \"(.*?)\", endereco \"(.*?)\", telefone (\\d+), email \"(.*?)\", cpf (\\d+)$")
    public void que_o_cliente_possui_os_atributos_id_nome_endereco_telefone_email_cpf(Long arg1, String arg2, String arg3, Integer arg4, String arg5, Integer arg6) {
        cliente = new Cliente(arg1,arg2,arg3,arg4,arg5, arg6, LocalDate.now());
    }

    @Quando("^o cliente for criado$")
    public void o_cliente_for_criado() {

    }

    @Entao("^o cliente devera ser criado$")
    public void o_cliente_devera_ser_criado() {
        assertNotNull(cliente);
    }

    @Entao("^o nome do cliente deve ser \"(.*?)\"$")
    public void o_nome_do_cliente_deve_ser(String arg1) {
        assertEquals(cliente.getNome(), "rafael");
    }

}
