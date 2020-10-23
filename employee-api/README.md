# Teste de configuração do ExampleMatcher do SpringData Query utilizando kotlin e mongo   
  
Poc de API para paginação com mongodb e query ExampleMatcher para realizar filtros, ordenação e paginação. 
  
### Versão  
- v0.0.1-SNAPSHOT

### Linguagem

- Kotlin na versão 1.3.72

### Contrás e problemas na minha visão

- ##### Contrás

     * A implementação do ExampleMatcher exige que seja criado um objeto de dominio de exemplo, este objeto é usado como exemplo para dar "match" em alguns atributos que se deseja
    Filtrar na query gerada pelas abstrações do spring data. É interessante frizar para as necessidades e possiveis implementações de uso. 
    Como temos parâmetros únicos sendo usados para cada atributo do objeto alvo, não foi possivel criar fazer com que o ExampleMatcher gerasse algo do tipo employee.id = :search or employee.name like '%search%',
     ou seja, não aceita clausula or e sim like e and separados. Também é confuso quando os parâmetros vêm nulos na requisição, sendo necessário criar um método de decisão para escolher qual findAll utilizar. 
        
     * Como dito não consegui, após pesquisar implementar algo que permita:
        * Buscar todos sem filtro. Se não for passado filtro ele coloca os parametros como nulo e não trás resultado nenhum. Repare no exemplo abaixo:
              
          ```
                private fun selectWhichSearchToUse(
                        search: String?,
                        position: String?,
                        example: Example<Employee>,
                        pages: Pageable
                ) =  if (search != null || position != null) {
                        employeeRepository.findAll(example, pages).map { EmployeeDto(it) }
                    } else {
                        employeeRepository.findAll(pages).map { EmployeeDto(it) }
                    }
          ``` 
     * Recomenda-se usar para poucos cenários e que os mesmos sejam bem simples;
     * Exige um conhecimento mais aprofundado do spring data; 
         
- ##### Problemas
     
     * Documentação não é boa
     * Dificil implementar suas abstrações, requer uma curva para pegar features mais avançadas;
     * Alguns bugs estão aberto já a bastante tempo e não houve fechamento desses bugs;
     * Não é recomendado para cenários complexos como os descritos acima, o que deixa a desejar;
         

- **PS**: Qualquer ajuda é bem vinda para um melhor entendimento dos problemas listados acima e que correspondem a minha primeira imprenssão!

### Prós na minha visão

- Interessante nos casos em que o front-end passe um DTO na requisição, vc pode converter o objeto para a sua entidade e assim usa-lá como exemplo matcher.
- Para casos simples de busca e paginação com poucos filtros, se torna 
- Semelhante ao Specification [Epecifications](https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/)
  
### Subindo o projeto localmente      
  
- Criar base de dados no mongodb localmente, recomendo usar **docker**
    O script que utilizei foi este: 
    ```
  docker run -it -v mongodata:/data/db -p 27017:27017 --name mongodb-docker -d mongo

- Não possui variavel de ambiente porém há prorpiedades definidas no application.properties 
    
    ```
        spring.data.mongodb.host=localhost
        spring.data.mongodb.port=27017
        spring.data.mongodb.database=employee_db
        
        spring.application.name=employee-api
    ```
### Idea utilizada

- IntelliJ (Por favor use com moderação)
   
### Endpoints     
  
  Após subir seu projeto localmente todos os endpoints finalizados estão disponíveis no [Swagger](http://localhost:8080/swagger-ui.html#/)    

   - Não há autenticação nesses endpoints

        *GET* /employees/ -> Busca todos os empregados cadastrados no sistema sem paginação
    
        *GET* /employees/by/name/{search}/position/{position} -> Busca todos os empregados cadastrados no sistema com filtro, ordenação e paginação
    
        *POST* /employees/ -> Cria um empregado
        
        *PUT* /employees/ -> Atualiza um empregado da base de dados
        
        *DELETE* /employees/ -> Remove um empregado da base de dados de forma fisica
