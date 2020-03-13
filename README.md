# cardboadbox
Integração entre transportadoras


# Serviços

Acesso ao sistema:
http://localhost:8080/

Todas as Transportadoras
http://localhost:8080/transportadoras/all

Melhor Preço
http://localhost:8080/transportadoras/melhorPreco/<distancia>

Melhor Preco para Determinado Tipo
http://localhost:8080/transportadoras/melhorPreco/<distancia>/<id tipo>


Melhor Tempo de Entrega
http://localhost:8080/transportadoras/melhorTempo/<distancia>

Melhor Tempo de Entrega para Determinado Tipo
http://localhost:8080/transportadoras/melhorTempo/<distancia>/<id tipo>


Os serviços para de cada tranportadora foram simulados atraves de
http://localhost:8080/service/transportadora/<id transportadora>

## Estado Atual e Proximos Passos

Atualmente o sistema recebe as Transportadoras por diferentes meios e filtra elas com base no preço ou tempo de entreg. Para as proxímas versões Consumir um serviço que calcule a distância  dado os pontos de origem e destino.