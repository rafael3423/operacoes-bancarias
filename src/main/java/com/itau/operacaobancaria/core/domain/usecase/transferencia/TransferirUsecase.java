package com.itau.operacaobancaria.core.domain.usecase.transferencia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.operacaobancaria.adapter.web.response.TransferenciaResponseDTO;
import com.itau.operacaobancaria.core.domain.mapper.BacenMapper;
import com.itau.operacaobancaria.core.domain.mapper.TransferenciaMapper;
import com.itau.operacaobancaria.core.domain.model.Cliente;
import com.itau.operacaobancaria.core.domain.model.ContaCorrente;
import com.itau.operacaobancaria.core.domain.model.Transferencia;
import com.itau.operacaobancaria.core.domain.port.in.TransferenciaPortIn;
import com.itau.operacaobancaria.core.domain.port.out.BuscaClientePorOut;
import com.itau.operacaobancaria.core.domain.port.out.ContaCorrentePortOut;
import com.itau.operacaobancaria.core.domain.port.out.NotificaBacenPortOut;
import com.itau.operacaobancaria.core.domain.port.out.TransferenciaCallBackPotOut;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.CallBackException;
import com.itau.operacaobancaria.core.domain.usecase.transferencia.exception.NotificacaoBacenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferirUsecase implements TransferenciaPortIn {

    private final ContaCorrentePortOut contaCorrentePortOut;
    private final BuscaClientePorOut buscaClientePorOut;
    private final NotificaBacenPortOut notificaBacenPortOut;
    private final TransferenciaMapper transferenciaMapper;
    private final BacenMapper bacenMapper;
    private final TransferenciaCallBackPotOut transferenciaCallBackPotOut;


    public TransferenciaResponseDTO execute(Transferencia transferencia) throws InterruptedException, JsonProcessingException {

        // Busca cliente
        Cliente cliente = buscaClientePorOut.buscaCliente(transferencia.getIdCliente());

        // consulta saldo do cliente, valida se valor da transferencia é maior que saldo e
        // que valor limite diário, redefine limite diário se a ultima transferencia for
        // um dia anterior a hoje e subtrai valor da transferencia do saldo.
        ContaCorrente contaCorrente = contaCorrentePortOut.buscarConta(transferencia.getIdConta());

        contaCorrente.validarContaInativaOuInexistente(transferencia.getIdConta());
        contaCorrente.redefinirLimiteDiario();
        contaCorrente.validarLimites(transferencia.getValorTransferencia(), transferencia);

        try {
            // Notifica Bacen sobre transferência e tenta 3x caso retorne 429
            notificaBacenPortOut.notificaBacen(bacenMapper.toAdapter(transferencia, cliente));
        } catch (NotificacaoBacenException e){
            transferenciaCallBackPotOut.sendMessageCallBack(new ObjectMapper().writeValueAsString(transferencia));
        }

        //Salva dados dabitados
        contaCorrentePortOut.salvarConta(contaCorrente);

        return transferenciaMapper.toSaida(transferencia, contaCorrente, cliente);
    }

}
