# Backend Java Test

## Endpoints

Os endpoints, juntamente com os parametros necessários para executa-los, estão no arquivo src/main/resources/restlet-client-services.json. É necessário instalar a extensão do chrome Restlet Client (https://chrome.google.com/webstore/detail/restlet-client-rest-api-t/aejoelaoggembcahagimdiliamlcdmfm) e importar o arquivo json para consulta-los.
**Os endpoints necessitam dos headers corretos para serem chamados.**

### Autenticacao
- Login: http://localhost:8446/rest/auth - POST

### Empresa
- inserir: http://localhost:8446/rest/empresa - POST
- alterar: http://localhost:8446/rest/empresa - PUT
- deletar: http://localhost:8446/rest/empresa?empnid= - DELETE
- listar todas: http://localhost:8446/rest/empresa/all - GET

### Veículo
- inserir: http://localhost:8446/rest/veiculo - POST
- alterar: http://localhost:8446/rest/veiculo - PUT
- deletar: http://localhost:8446/rest/veiculo?veinid= - DELETE
- listar todos: http://localhost:8446/rest/veiculo/all - GET

### Movimento
- entrada: http://localhost:8446/rest/movimento/entrada - POST
- saida: http://localhost:8446/rest/movimento/saida - POST
- sumario geral: http://localhost:8446/rest/movimento/sumario - GET
- sumario por hora: http://localhost:8446/rest/movimento/sumario-por-hora - GET

### Relatório
Pode executar pelo browser normalmente.
- sumario geral - http://localhost:8446/report/sumario/pdf?token=
- sumario por hora - http://localhost:8446/report/sumario-por-hora/pdf?token=

### H2
- console: http://localhost:8082/

## Tecnologias Utilizadoas
- Spring Boot
- H2
- Hibernate
- Jackson
- WebFlux
- JWT
- Guava
- Jaxb
- Jasper Reports

## Instalação e Execução
Sendo o diretorio corrente a pasta raiz do projeto, **mvn clean install** irá gerar um arquivo .jar dentro da pasta target.
Para executar, basta rodar **java -jar target/provajava-0.0.1-SNAPSHOT.jar**

