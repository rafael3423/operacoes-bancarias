#language: pt
#encoding: utf-8
#Author: Rafael Marcos

Funcionalidade: Permite realizar uma transferencia

  Cenario: Transferir valor informando dados válidos
    Dado um id de um cliente "71b27651-3907-4a70-b5a6-9b43efecef18"
    E a chave pix destino  "ca36b376-b222-40ef-896c-436e0ab740a9"
    E o id conta "6ebcf820-f499-4a5a-82e5-2422270f56d7"
    E o valor da transferencia "100.00"
    Quando realizo a chamada para transferencia
    Entao deve ser retornado código http 204
