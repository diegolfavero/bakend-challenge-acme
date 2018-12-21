# bakend-challenge-acme

Invillia Avaliation

# URL Git da Avaliação:

https://github.com/diegolfavero/bakend-challenge-acme.git

# Simulando uma possível V0 deste Challenge:

A tarefa foi dividida em 4 projetos, e visa mudar a arquitetura do sistema monolítico para uma arquitetura micro serviços da empresa ACME.
Procurei priorizar a implementação dos serviços, Testes utilizando as chamadas dos Endpoints com o Postman, Testes com JUnit, Segurança, Clean Code, Criação de Database e estabelecer um Padrão de Projeto para entregas futuras.

# "Nice to have features" implementadas:

* Database (Armazenamento dos Dados das informações e registros.)
	- Foi utilizado o MySql.
	
* Security (Prover autenticação, segurança e permissões de acesso.)
	- Foi utilizada a Basic Authentication do Spring Security.
	User: admin
	Password: 123456
	
* Clean Code (Código com clareza, de fácil entendimento e leitura.)

# "Nice to have features" não implementadas (não houve tempo até o término do prazo) e utilizaria em possíveis próximas entregas:

* Asynchronous processing (Dois ou mais Processos são realizados simultaneamente, sem necessidade do término de uma tarefa para iniciar outra.)
	- Utilizaria Processamento assíncrono com JMS, Filas, Messages, Queues para controle.
	
* Docker (Centralizador em Containers de ambientes e aplicações, auxiliando na criação e administração.) 

* AWS (Plataforma de serviços em nuvem para criação de produtos, soluções e aplicativos para empresas.)
	- Utilizaria para maior Escalabilidade e Resiliência
	
* Swagger (Ferramenta que auxilia na documentação de APIs.)
	- Utilizaria para melhorar a documentação e modelagem da aplicação. 

- Melhoria da Padronização para RNs e Tratamento de Exceções.

- Faria mais Testes JUnit nas demais camadas (Controller, Repository).

- Utilização de H2 Database Engine.