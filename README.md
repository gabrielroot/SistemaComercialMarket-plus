# Sistema Comercial Market-plus

###Introdução
O desenvolvimento desse software busca resolver alguns problemas relacionados aos processos de negócio de uma empresa fictícia (Market+). Os problemas são:  controle de estoque, fornecedores, vendas, compras, pessoas e emissão de relatórios.
###Tecnologias
- Java 16
		- Java Swing
		- JPA
- Mysql

###Criando o Ambiente de Desenvolvimento
Será necessário ter as tecnologias instaladas mencionadas a cima.

Opcionalmente, poderá criar o banco de dados com Docker. No arquivo `docker-compose.yml` está configurado o mysql e phpmyadmin, e para subir os containers será necessário ter o [docker](https://www.docker.com/) e [docker-compose](https://docs.docker.com/compose/) instalados. O arquivo mencionado, referencia o arquivo `.env` que por padrão, não é comitado. Mas existe uma cópia de exemplo `.env.sample` que pode ser usada como parâmetro para a criação do seu próprio .env. Com tudo configurado, execute `docker-compose up -d` para inicializar os containers.

###Execução
