# product ms
[![Build Status](https://travis-ci.com/elysantos/product-ms.svg?branch=master)](https://travis-ci.com/elysantos/product-ms)

Tabela de conteúdos
=================
<!--ts-->
* [Instalação](#instalao)
* [Como usar](#executando-o-projeto)
    * [Executar local](#execuo-local)
    * [Com Docker](#execuo-com-docker)
* [Documentação de Api](#documentao-de-api)
* [Testes](#testes)
* [Tecnologias](#tecnologias)
<!--te-->
## Instalação
Para instalar o projeto, basta clonar o repositório:

```
git clone https://github.com/elysantos/product-ms.git
```

## Executando o projeto

### Execução Local
Clonando o projeto e executando em ide mantendo o perfil ativo dev para uso do banco em memória

### Execução com docker
Após clonar o projeto, executar o seguinte comando para criar as imagens e os containers do docker

```
docker compose up
```

## Documentação de API
A documentação de api foi criada com o Open Api que pode ser 
acessada após a execução do projeto no link: 
```
http://localhost:9999/swagger-ui.html
```

## Testes
Criado teste de integração com recursos do JUnit e Mockito para verificação dos
endpoints e das respostas esperadas.

## Tecnologias
* Mybatis - Facilita o ORM ao possibilitar a escrita de querys e resultmaps,
  controlando as requisições ao banco de dados
* H2 Database - Banco em memória para testes e desenvolvimento
* Open Api - Geração de documentação dos endpoints com ui
* Docker - Criar conteiner com ambiente controlado para execução da aplicação