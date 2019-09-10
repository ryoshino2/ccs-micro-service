package br.com.ryoshino.service;

import br.com.ryoshino.configuration.ConfigurationKafka;
import br.com.ryoshino.entity.Cliente;
import br.com.ryoshino.repository.ClienteRepository;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@EnableScheduling
public class ClienteService {
    //288 clientes por dia
//    private final long GERAR_CLIENTES = (5000 * 60);
    private final ConfigurationKafka kafkaProperties = new ConfigurationKafka();

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public void salvarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

//    @Scheduled(fixedDelay = GERAR_CLIENTES)
    public void gerarCliente() {
        Random gerador = new Random();

        Cliente cliente = new Cliente();
        cliente.setDataAtualizacao(LocalDate.now());
        cliente.setCpf(gerador.nextInt(10000) + 1);
        cliente.setEmail("a@.com" + gerador.nextInt(100));
        cliente.setEndereco("endereco: " + gerador.nextInt(100));
        cliente.setNome("nome: " + gerador.nextInt(100));
        cliente.setTelefone(gerador.nextInt(10000) + 1);
        salvarCliente(cliente);

        System.out.println(cliente);

        KafkaProducer<String, Cliente> producer = new KafkaProducer<>(kafkaProperties.configurationKafka());
        ProducerRecord<String, Cliente> record;
        record = new ProducerRecord<>("cliente_ccs", cliente);
        producer.send(record);
        producer.flush();
        producer.close();

    }

    public void enviarParaOKafka() {
        // create the producer
        KafkaProducer<String, Cliente> producer = new KafkaProducer<>(kafkaProperties.configurationKafka());
        ProducerRecord<String, Cliente> record;

        List<Cliente> contaClienteList = clienteRepository.findAll();

        for (Cliente cliente : contaClienteList) {
            record = new ProducerRecord<>("cliente_ccs", cliente);
            producer.send(record);
        }

        // flush data
        producer.flush();
        // flush and close producer
        producer.close();
    }

}
