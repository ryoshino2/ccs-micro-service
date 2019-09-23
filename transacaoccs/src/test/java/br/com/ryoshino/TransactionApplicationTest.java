package br.com.ryoshino;

import br.com.ryoshino.service.TransacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author ryoshino
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionApplicationTest extends TransacaoService {
    @Test
    public void contextLoads() {
    }
}