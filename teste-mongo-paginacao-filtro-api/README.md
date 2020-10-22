# Teste de configuração do query dsl no mongodb com kotlin     
  
Poc de API para paginação com mongodb e query dsl para realizar filtros, ordenação e paginação. 
  
### Versão  
- v0.0.1-SNAPSHOT

### Linguagem

- Kotlin na versão 1.3.72

### Problemas encontrados

A lib de query dsl exige que seja gerada uma classe pós processada (Java annotation post-processing)
Elas iniciam com o prefixo "Q". Tentei implementar o kapt no build.gradle mas só consegui gerar pasta vazia (O_O). 
Qualquer ajuda é bem vinda!
  
### Subindo o projeto localmente      
  
- Criar base de dados no mongodb localmente, recomendo usar **docker**
    O script que utilizei foi este: 
    ```
  docker run -it -v mongodata:/data/db -p 27017:27017 --name mongodb-docker -d mongo

- Não possui variavel de ambiente porém há prorpiedades definidas no application.properties 
    
```
    spring.data.mongodb.host=localhost
    spring.data.mongodb.port=27017
    spring.data.mongodb.database=teste
    
    spring.application.name=teste-mongo-paginacao-filtro-api
```
### Idea utilizada

- IntelliJ (Por favor use com moderação)
   
### Endpoints     
  
  Após subir seu projeto localmente todos os endpoints finalizados estão disponíveis no [Swagger](http://localhost:8080/swagger-ui.html#/)    

   - Não há autenticação nesses endpoints

    *GET* /users/ -> Busca todos os usuarios cadastrados no sistema sem paginação

    *GET* /users/by/name/{search}/age/{age} -> Busca todos os usuarios cadastrados no sistema com filtro, ordenação e paginação utilizando query dsl

    *GET* /users/{name}/name -> Busca todos os usuarios cadastrados no sistema sendo filtrado por nome ou id utilizando named query do Spring data repository retornando uma paginação

    *POST* /users/ -> Cria um novo usuário
