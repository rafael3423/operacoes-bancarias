operacoes bancarias

A solução para transferência foi pensada em um provisionamento na AWS, onde um API Gateway gerência as requisições de entrada e as redireciona para um network load balancer, que por sua vez distrubuí a carga para um cluster ECS. Este cluster contendo um serviço de fargate adicionado realiza o auto gerênciamento de escalonamento de tasks caso o serviço seja muito requisitado e é provisionado com tasks em duas AZs para garantir 
a alta disponibilidade. As operações nos serviços são processadas e um pré registro é resgatado de um dynamoDB (escolhido para atender o alto desempenho necessário para muitos usuários simultâneos, com uma latência realtivamente baixa).
Os dados da conta corrente são comparados com os dados da transferência, e é validado se os limites não são ultrapassados e se a conta se encontra válida para realizar a transferência. Com a realização da transferência necessita-se a notificação ao banco central, que tem a possibilidade de não receber a integração por estar com muitos acessos recorrentes naquele momento. Para este cenário de não notificação, foi adicionado uma fila sqs para que retente o processamento de transferência caso o bacen retorne por tres vezes consecultivas o códdigo de retorno 429. Para a falha de dependência em quaisquer outras integrações, foi utilizado o padrão circuitBreaker para que monitore a disponibilidade das API's e não onere o cliente no momento da transferência. 
As métricas e registros podem ser consultados no serviço cloudWatch e um monitoramento pode ser customizado na plataforma grafana integrado.
![image](https://github.com/rafael3423/operacoes-bancarias/assets/67655762/73ff6b5d-e77c-43af-9e30-e0032c610aaa)







A API foi configurada para um realizar um teste de transferência com um cliente que possuí um saldo inicial de 1500 reais, um limite diário de 200, um valor de limite ajustado de 1000, e uma data de ultima transferência que retrocede a data atual (dessa forma o limite diário assume o valor ajustado na conta)

curl para requisição:



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
