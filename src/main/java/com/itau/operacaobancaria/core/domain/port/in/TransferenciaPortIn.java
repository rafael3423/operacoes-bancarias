package com.itau.operacaobancaria.core.domain.port.in;

import com.itau.operacaobancaria.adapter.web.response.TransferenciaResponseDTO;
import com.itau.operacaobancaria.core.domain.model.Transferencia;

public interface TransferenciaPortIn {
    TransferenciaResponseDTO execute(Transferencia transferencia) throws Exception;

}
