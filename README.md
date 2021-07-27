# provabackendjava
 prova de backend java
 
 Usando o Postman:
 
Cadastrando um Produto/Serviço:

POST http://localhost:8080/produtoservico/salva

Resposta:
{
    "id": "1c0b50c0-dcf0-4d7b-8821-cb9b55868d17",
    "nome": "Notbebok",
    "preco": 2000.0,
    "tipo": "0",
    "ativo": true
}


tipo: indicação para diferenciar um produto de um serviço

·  0: PRODUTO
·  1: SERVIÇO
Observação: Substituir pelo nome e porta do seu servidor: http://localhost:8080


Listando um Produto/Serviço específico:

GET http://localhost:8080/produtoservico/1c0b50c0-dcf0-4d7b-8821-cb9b55868d17

Resposta:
{
    "id": "1c0b50c0-dcf0-4d7b-8821-cb9b55868d17",
    "nome": "Notbebok",
    "preco": 2000.0,
    "tipo": "PRODUTO",
    "ativo": true
}

Listando todos os Produtos/Serviços

GET http://localhost:8080/produtoservico/listatodos

[
    {
        "id": "9010beef-bb54-4cf5-b201-4b363393ac4c",
        "nome": "The Lord of the Rings",
        "preco": 90.5,
        "tipo": "PRODUTO",
        "ativo": true
    },
    {
        "id": "0a191a62-9a8c-4249-99bf-a621a25f855e",
        "nome": "Smart TV",
        "preco": 2190.0,
        "tipo": "PRODUTO",
        "ativo": true
    },
    {
        "id": "5a166f2d-2480-406d-bbb0-4d1870dc5962",
        "nome": "Estacionamento",
        "preco": 8.0,
        "tipo": "SERVIÇO",
        "ativo": true
    },
     {
        "id": "2e5a2ce0-a8d1-4013-a89f-5c8beb6e46cc",
        "nome": "Geladeira",
        "preco": 2000.0,
        "tipo": "PRODUTO",
        "ativo": true
    },
    {
        "id": "1c0b50c0-dcf0-4d7b-8821-cb9b55868d17",
        "nome": "Notbebok",
        "preco": 2000.0,
        "tipo": "PRODUTO",
        "ativo": true
},
...
]


Apaga um Produto/Serviço:

DELETE http://localhost:8080/produtoservico/0a191a62-9a8c-4249-99bf-a621a25f855e

Resposta:
204 No Content


Atualiza um Produto/Serviço:

PUT http://localhost:8080/produtoservico/ff37ae12-a153-4d99-bbdd-56df855e235e

Resposta:
{
    "id": "ff37ae12-a153-4d99-bbdd-56df855e235e",
    "nome": "Estacionamento",
    "preco": 6.0,
    "tipo": "SERVIÇO",
    "ativo": true
}


Cadastrando um Pedido:
POST http://localhost:8080/pedido/salva

Resposta:
{
    "id": "235e38d0-285f-430e-91d0-46a0891eedc5",
    "momento": "2021-07-27T15:16:49Z",
    "status": "ABERTO",
    "items": [],
    "pagamento": null,
    "desconto": 10.0,
    "total": 0.0
}

status: indicação para diferenciar um pedido aberto de um fechado

·  0: ABERTO
·  1: FECHADO

Listando um Pedido específico:
GET http://localhost:8080/pedido/347fe3f0-cab4-4988-8eca-757c1344e477
Resposta:
{
    "id": "347fe3f0-cab4-4988-8eca-757c1344e477",
    "momento": "2021-07-27T15:16:49Z",
    "status": "ABERTO",
    "items": [
        {
            "quantidade": 1,
            "preco": 90.5,
            "prodServ": {
                "id": "9010beef-bb54-4cf5-b201-4b363393ac4c",
                "nome": "The Lord of the Rings",
                "preco": 90.5,
                "tipo": "PRODUTO",
                "ativo": true
            },
            "subTotal": 90.5
        }
    ],
    "pagamento": null,
    "desconto": 10.0,
    "total": 81.45
}


Listando todos os Pedidos:
GET http://localhost:8080/pedido/listatodos

Resposta:
[
    {
        "id": "e713017f-d04d-400a-a41e-b765dad729f4",
        "momento": "2021-07-27T15:16:49Z",
        "status": "FECHADO",
        "items": [],
        "pagamento": null,
        "desconto": 10.0,
        "total": 0.0
    },
    {
        "id": "347fe3f0-cab4-4988-8eca-757c1344e477",
        "momento": "2021-07-27T15:18:36Z",
        "status": "ABERTO",
        "items": [
            {
                "quantidade": 1,
                "preco": 90.5,
                "prodServ": {
                    "id": "9010beef-bb54-4cf5-b201-4b363393ac4c",
                    "nome": "The Lord of the Rings",
                    "preco": 90.5,
                    "tipo": "PRODUTO",
                    "ativo": true
                },
                "subTotal": 90.5
            }
        ],
        "pagamento": null,
        "desconto": 10.0,
        "total": 81.45
},
...
]

items: Lista todos os itens de pedido


Apaga um Pedido:

DELETE http://localhost:8080/pedido/cd5f95ee-a322-4ea0-bb45-d8dc11af3d46

Resposta:
204 No Content

Atualiza um Pedido:

PUT http://localhost:8080/pedido/e713017f-d04d-400a-a41e-b765dad729f4

Resposta:
{
    "id": "e713017f-d04d-400a-a41e-b765dad729f4",
    "momento": "2021-07-27T15:37:09Z",
    "status": "FECHADO",
    "items": [],
    "pagamento": null,
    "desconto": 10.0,
    "total": 0.0
}


Listando todos os Itens de pedidos:
GET http://localhost:8080/pedidoitem/listatodos

Resposta:
[
    {
        "quantidade": 1,
        "preco": 90.5,
        "prodServ": {
            "id": "9010beef-bb54-4cf5-b201-4b363393ac4c",
            "nome": "The Lord of the Rings",
            "preco": 90.5,
            "tipo": "PRODUTO",
            "ativo": true
        },
        "subTotal": 90.5
},
...
]


