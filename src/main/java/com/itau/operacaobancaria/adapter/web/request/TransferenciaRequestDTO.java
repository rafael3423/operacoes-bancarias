package com.itau.operacaobancaria.adapter.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.itau.operacaobancaria.adapter.web.utils.PadraoUtil.UUID_PATTERN;

@Data
public class TransferenciaRequestDTO {

   @NotBlank(message = "id_cliente não dever ser nulo/vazio.")
   @JsonProperty("id_cliente")
   @Pattern(regexp = UUID_PATTERN,
           message = "TokenFormatError")
   private String idCliente;

   @NotBlank(message = "codigo_chave_pix_destino não dever ser nulo/vazio.")
   @JsonProperty("codigo_chave_pix_destino")
   @Pattern(regexp = UUID_PATTERN,
           message = "TokenFormatError")
   private String codigoChavePixDestino;

   @NotBlank(message = "id_conta não dever ser nulo/vazio.")
   @JsonProperty("id_conta")
   @Pattern(regexp = UUID_PATTERN,
           message = "TokenFormatError")
   private String idConta;

   @NotBlank(message = "valor_transferencia não dever ser nulo/vazio.")
   @JsonProperty("valor_transferencia")
   @Digits(integer = 3, fraction = 2,
           message = "valor_transferencia máximo de 999 reais e centavos com duas casas decimais.")
   @Min(value = 1, message = "valor minimo de transferencia: 1 real")
   private String valorTransferencia;

}
