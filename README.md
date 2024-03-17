operacoes bancarias

![image](https://github.com/rafael3423/operacoes-bancarias/assets/67655762/73ff6b5d-e77c-43af-9e30-e0032c610aaa)

saldo 1500
limite 1000



curl --request POST \
  --url http://localhost:8080/v1/transferencia/transferir \
  --header 'Content-Type: application/json' \
  --data '
{
	"id_cliente": "71b27651-3907-4a70-b5a6-9b43efecef18",
	"codigo_chave_pix_destino": "ca36b376-b222-40ef-896c-436e0ab740a9",
	"id_conta": "6ebcf820-f499-4a5a-82e5-2422270f56d7",
	"valor_transferencia": "500.00"
}
'
