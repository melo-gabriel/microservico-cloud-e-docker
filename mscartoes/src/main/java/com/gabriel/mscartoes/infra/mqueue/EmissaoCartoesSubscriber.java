package com.gabriel.mscartoes.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.mscartoes.model.Cartoes;
import com.gabriel.mscartoes.model.ClienteCartao;
import com.gabriel.mscartoes.model.DadosSolicitacaoEmissaoCartao;
import com.gabriel.mscartoes.repositories.CartoesRepository;
import com.gabriel.mscartoes.repositories.ClienteCartaoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmissaoCartoesSubscriber {

    private final CartoesRepository cartaoRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload){
        try {
            var mapper = new ObjectMapper();

            DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartoes cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();

            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCpf());
            clienteCartao.setLimite(dados.getLimiteLiberado());

            clienteCartaoRepository.save(clienteCartao);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}