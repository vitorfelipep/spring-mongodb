# Teste de configuração do mongoTemplate do springframework.data.mongodb.core utilizando kotlin e mongo   
  
Poc de API para paginação com mongodb e query utilizando mongo-template para realizar filtros, ordenação e paginação. 
  
### Versão  
- v0.0.1-SNAPSHOT

### Linguagem

- Kotlin na versão 1.3.72

### Resumo
 
Opção certa para quem quer simplicidade para um contrato padrão com o front-end e opções simples de criação de filtros customizavéis. 
Vale a pena ficar de olho e utilizar esta implementação. Por ser a mais rápida e com mais exemplos na web, caso tenha algum problema. 

- ##### Mongo-template sob alguns pontos de vista da minha parte

     * A implementação do mongoTemplate criando query com clausulas where que exigem buscas dinâmicas por filtros pede a implementação da classe ``org.springframework.data.mongodb.core.query``,  
       esta implementação foi a mais versátil e a que mais se aporxima de uma implementação fácil para paginação, filtro e ordenação para tabelas com milhares de registros. 
        
     * Neste exemplo podemos ver potencial em abstração e contrato utlilizando solid, apesar de ser uma POC, as boas praticas serão seguidas neste projeto. Aguardem!
     * Basicamente a implementação segue a function descrita abaixo:
              
      ```
        fun findAllByFiltering(
                search: String?,
                description: String?,
                page: Int,
                size: Int,
                sort: String,
                direction: String
        ): Page<ProjectDTO> {
            val pages: Pageable = PageRequest.of(page, size, Sort.Direction.valueOf(direction), sort)
            
            if (search != null || description != null) {
                val query = createQuery(search, description, pages)
                val projects = mongoTemplate.find(query, Project::class.java)
                val projectsDTO = projects.map { ProjectDTO(it) }
                
                return PageImpl<ProjectDTO>(projectsDTO, pages, count(query))
            }
            
            return projectRepository.findAll(pages).map { ProjectDTO(it) }
        }
      ``` 
      
     * query é criada na função createQuery;
                                             
     ```
        private fun createQuery(
                    search: String?,
                    description: String?,
                    pages: Pageable
            ): Query {
                val query = Query()
                
                search?.let {
                    val criteriaName = Criteria.where("name").regex(".*" + it.toLowerCase() + ".*", "i")
                    val criteriaId = Criteria.where("id").`is`(it)
                    query.addCriteria(Criteria().orOperator(criteriaName, criteriaId))
                }
                
                description?.let {
                    query.addCriteria(Criteria.where("info.description").regex(".*" + it.toLowerCase() + ".*", "i"))
                }
                
                query.with(pages)
                return query
            }
    
      ```   
     
 * Exige pouco de leitura sob a documentação mas nada muito complexo: [spring-mongodb-docs](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference)
         
### Contra na minha visão
     
- Necessário um bom entendimento e estudo no regex e a sintáxe criada pelo spring para cenários mais complexos
você vai precisar saber muito bem aonde esta mexendo hahahahhaa
         

### Prós na minha visão

- Após o entendimento do funcionamento e criação das querys, fica fácil a implementação.
- Boa leitura e bem orietado a orm, facilitando a leitura e criação das Criteria para busca e filtro dos dados
- Familiar pois se assemelha as tecnologias abaixo:
  
  * [Epecifications-Docs](https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/)
  * [Criteria-querys](https://www.baeldung.com/spring-data-criteria-queries)
  
### Subindo o projeto localmente      
  
- Criar base de dados no mongodb localmente, recomendo usar **docker**
    O script que utilizei foi este: 
    ```
  docker run -it -v mongodata:/data/db -p 27017:27017 --name mongodb-docker -d mongo

- Não possui variavel de ambiente porém há prorpiedades definidas no application.properties 
    
    ```
        spring.application.name=project-api
        
        spring.data.mongodb.host=localhost
        spring.data.mongodb.port=27017
        spring.data.mongodb.database=project_db
        
        server.port=8080
        
        logging.level.root = INFO
    ```
### Idea utilizada

- IntelliJ (Por favor use com moderação)
   
### Endpoints     
  
  Após subir seu projeto localmente todos os endpoints finalizados estão disponíveis no [Swagger](http://localhost:8080/swagger-ui.html#/)    

   - Não há autenticação nesses endpoints

        *GET* /v1/projects/ -> Busca todos os projetos cadastrados no sistema
    
        *GET* /search/{search}/description/{description} -> Busca todos os projetos cadastrados no sistema com filtros **serach (nome ou id do projeto), description(descrição do projeto), ordenação e paginação
    
        *POST* /v1/projects/ -> Cria um novo projeto
        
        