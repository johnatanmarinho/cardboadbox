# cardboadbox
Integração entre transportadoras


#Sistema
http://localhost:8080/


#Servicos Filtragem

Lista todas as transportadoras
> http://localhost:8080/transportadoras/all


Para fazer A filtragem é necessario enviar um objeto "Rota" na requisão, com a origem, o destino e a distância
Melhor Preço
> http://localhost:8080/transportadoras/melhorPreco/

Melhor Tempo de Entrega
> http://localhost:8080/transportadoras/melhorTempo/


Os serviços para de cada tranportadora foram simulados atraves de
> http://localhost:8080/service/transportadora/<id transportadora>

## Estado Atual e Proximos Passos

Atualmente o sistema recebe as Transportadoras por diferentes meios e filtra elas com base no preço ou tempo de entreg. Para as proxímas versões Consumir um serviço que calcule a distância  dado os pontos de origem e destino.