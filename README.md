# Documentação Técnica: Sistema de Gerenciamento e Alocação de Equipes de Software
Trabalho final para a disciplina de Padrões e Projetos de Software.

Tema Escolhido
O tema desenvolvido é um Sistema de Gerenciamento e Alocação de Equipes de Software. O objetivo principal da aplicação é simular o fluxo de combinações e gerenciamento de requisitos de engenharia de software, recebendo demandas de projeto e alocando-as de forma inteligente a desenvolvedores com perfis específicos (como especialistas em Backend utilizando Java ou PHP, ou especialistas Mobile utilizando Dart/Flutter).

Arquitetura Geral
A aplicação foi construída em Java puro, seguindo os princípios da Programação Orientada a Objetos (POO). A arquitetura é modular e dividida em pacotes por responsabilidade:

manager: Centraliza o estado global da aplicação.

model: Contém as entidades de domínio e suas interfaces.

factory: Encapsula a complexidade de instanciação das entidades.

strategy: Isola as regras de negócio e algoritmos de tomada de decisão.

Esta separação garante baixo acoplamento e alta coesão, permitindo que as lógicas de criação de objetos não interfiram nas lógicas de regras de negócio.

Padrões Aplicados: Onde, Como e Por Quê
1. Singleton
Onde: Classe RequirementManager no pacote manager.

Como: A classe possui um construtor privado e expõe um método estático getInstance() que retorna sempre a mesma instância alocada na memória.

Por quê: Em um sistema de gerenciamento de requisitos, é fundamental que exista uma única fonte. Se múltiplas partes do sistema criassem instâncias diferentes do gerenciador, os dados ficariam dessincronizados. O Singleton garante um ponto global de acesso seguro aos requisitos cadastrados.

2. Factory Method
Onde: Classe DeveloperFactory no pacote factory, associada à interface Developer.

Como: Foi criada uma classe fábrica com um método estático que recebe uma String (o tipo do desenvolvedor) e retorna a implementação concreta apropriada (BackendDeveloper ou MobileDeveloper), ocultando o uso do operador new.

Por quê: Desacopla a classe principal do sistema das classes concretas dos desenvolvedores. Se no futuro for necessário adicionar um novo perfil, basta criar a classe e atualizar a fábrica, respeitando o Princípio do Aberto/Fechado (Open/Closed Principle) do SOLID.

3. Strategy
Onde: Interface AllocationStrategy e suas implementações (SkillBasedAllocation, FirstAvailableAllocation) no pacote strategy, utilizadas pelo contexto ProjectAllocator.

Como: O algoritmo de alocação de tarefas foi extraído para uma interface. O ProjectAllocator mantém uma referência para essa interface e delega a ação de alocar. A estratégia pode ser alterada em tempo de execução através do método setStrategy().

Por quê: O processo de "matching" entre requisitos e desenvolvedores pode variar muito (por habilidade, carga horária, primeiro disponível, etc.). O padrão Strategy evita o uso de múltiplos blocos if/else no código principal, permitindo que novos algoritmos de alocação sejam introduzidos sem quebrar o sistema existente.

Diagrama de Classes (UML Simplificado)
``` mermaid
classDiagram
    %% Singleton
    class RequirementManager {
        -static RequirementManager instance
        -List~String~ requirements
        -RequirementManager()
        +static getInstance() RequirementManager
        +addRequirement(req: String) void
        +getRequirements() List~String~
    }


    %% Factory Method
    class Developer {
        <<interface>>
        +assignTask(task: String) void
        +getSkill() String
    }
    class BackendDeveloper {
        +assignTask(task: String) void
        +getSkill() String
    }
    class MobileDeveloper {
        +assignTask(task: String) void
        +getSkill() String
    }
    class DeveloperFactory {
        +static createDeveloper(type: String) Developer
    }

    Developer <|.. BackendDeveloper
    Developer <|.. MobileDeveloper
    DeveloperFactory ..> Developer : cria

    %% Strategy
    class AllocationStrategy {
        <<interface>>
        +allocate(team: List~Developer~, requirementType: String) Developer
    }
    class SkillBasedAllocation {
        +allocate(team: List~Developer~, requirementType: String) Developer
    }
    class FirstAvailableAllocation {
        +allocate(team: List~Developer~, requirementType: String) Developer
    }
    class ProjectAllocator {
        -AllocationStrategy strategy
        +setStrategy(strategy: AllocationStrategy) void
        +allocateTask(team: List~Developer~, task: String, requiredSkill: String) void
    }

    AllocationStrategy <|.. SkillBasedAllocation
    AllocationStrategy <|.. FirstAvailableAllocation
    ProjectAllocator o-- AllocationStrategy : utiliza
```

Considerações Finais
A aplicação prática dos padrões Singleton, Factory Method e Strategy demonstrou como problemas clássicos de design de software podem ser resolvidos de forma elegante. A estrutura resultante não apenas atende aos requisitos funcionais de simular um sistema de matching para projetos e desenvolvedores, mas também garante atributos de qualidade vitais, como manutenibilidade e extensibilidade. O código está preparado para crescer de forma sustentável, comprovando o valor do design orientado a objetos aplicado de forma estratégica.
