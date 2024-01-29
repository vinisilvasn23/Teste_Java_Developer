# Teste_Java_Developer

Este repositório contém uma aplicação Java desenvolvida com o framework Spring Boot. Para executar a aplicação, certifique-se de ter o Maven instalado em sua máquina.

Pré-requisitos:
Java (versão 8 ou superior)
Maven

A aplicação possui o envio de notificação ao realizar uma transferência. Será notificado pelo email, então é necessário configurar as variáveis de ambiente.
Está sendo ultilizado o banco de dados H2 database que opera em um modo "em memória", então as informações são deletadas após reiniciar a aplicação.

Iniciando...


1. Clonar o Repositório:

git clone https://github.com/vinisilvasn23/Teste_Java_Developer

cd Teste_Java_Developer

2. Renomeie o arquivo `.env.example` para `.env` e configure as variáveis de ambiente

3. Executar a Aplicação:
   ```mvn clean install```

    ```mvn spring-boot:run```

4. Acessar a Aplicação:

A aplicação estará disponível em http://localhost:8080.

## Endpoints

- **POST /users**

  - Criar um novo usuario como empresa.
  - Corpo da Requisição:
    ```json
    {
   "name": "Empresa",
	"password":"Empresa123",
	"cpfCnpj":"12345678915123",
	"email":"empresa@mail.com",
	"type": "company",
	"fee": 2
}
    ```
  - Resposta do Servidor:
    ```json
    {
	"id": 2,
	"name": "Empresa",
	"cpfCnpj": "12345678915123",
	"email": "empresa@mail.com",
	"type": "company",
	"balance": 0,
	"fee": 2
}```
 - Criar um novo usuário como cliente.
  - Corpo da Requisição:
    ```json
    {
   "name": "Client",
	"password":"client123",
	"cpfCnpj":"12345678912",
	"email":"client@mail.com",
	"type": "client"
}
    `
  - Resposta do Servidor:
    ```json
    {
	"id": 1,
	"name": "Client",
	"cpfCnpj": "12345678912",
	"email": "client@mail.com",
	"type": "client",
	"balance": 0,
	"fee": null
}
    ```

- **GET /users**

  - Lista todos os usuários
  - Resposta do Servidor (exemplo):
    ```json
    [
	{
		"id": 1,
		"name": "Client",
		"cpfCnpj": "12345678912",
		"email": "client@mail.com",
		"type": "client",
		"balance": 0.00,
		"fee": null
	},
	{
		"id": 2,
		"name": "Empresa",
		"cpfCnpj": "12345678915123",
		"email": "empresa@mail.com",
		"type": "company",
		"balance": 0.00,
		"fee": 2.00
	}
]
    ```

- **POST /users/:id/deposit**

  - Realizar um depósito ao usuário.
  - Corpo da Requisição:
    ```json
    {
  "value": 1000
  }
    ```
  - Resposta do Servidor (exemplo):
   ```json
    {
	"id": 2,
	"name": "Client",
	"cpfCnpj":"12345678912",
	"email":"client@mail.com",
	"type": "client"
	"balance": 1000.00,
	"fee": null
}
    ```

- **POST /transactions**

  - Realizar uma transação.
  - Corpo da Requisição:
    ```json
    {
	"clientId": 2,
	"companyId": 1,
	"type": "transfer",
	"amount": 5
}
    ```
  - Resposta do Servidor (exemplo):
   ```json
    {
	"id": 1,
	"client": {
		"id": 2,
		"name": "Client",
		"cpfCnpj": "12345678912",
		"email": "client@mail.com",
		"type": "client",
		"balance": 995.00,
		"fee": null
	},
	"company": {
		"id": 1,
		"name": "EmpresaX",
		"cpfCnpj": "12345678915123",
		"email": "empresa@mail.com",
		"type": "company",
		"balance": 4.90,
		"fee": 2.00
	},
	"amount": 5,
	"type": "transfer",
	"date": "2024-01-29T19:27:54.062+00:00"
}
    ```

- **GET /users/:id**

  - Busca um usuário pelo id.
  - Resposta do Servidor (exemplo):
    ```json
    {
	"id": 1,
	"name": "Client",
	"cpfCnpj": "12345678912",
	"email": "client@mail.com",
	"type": "client",
	"balance": 0.00,
	"fee": null
}
    ```

- **PATCH /users/:id**

  - Atualiza um usuário pelo id.
  - Corpo da Requisição (opcional):
    ```json
      {
   "name": "EmpresaX"
}
    ```
  - Resposta do Servidor:
    ```json
    {
	"id": 1,
	"name": "EmpresaX",
	"cpfCnpj": "12345678915123",
	"email": "empresa@mail.com",
	"type": "company",
	"balance": 0.00,
	"fee": 2.00
}
    ```

- **DELETE /users/:id**
  - Deleta um usuário pelo id.
  - Resposta do Servidor (status code):
    ```
      204 No Content
    ```
