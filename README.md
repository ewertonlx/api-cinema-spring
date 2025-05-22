## Api Cinema com Spring ☕️
Esse projeto tem como objetivo em adquirir conhecimento na utilização do Spring para criar Apis REST.

## Principais funcionalidades 🔧
1. Listar filmes disponíveis no cinema.
2. Filtrar algum filme pelo ID ou por Categoria.
3. Adicionar novos filmes.
4. Deletar algum filme.
5. Fazer alterações nas informações de um filme.
6. Criar novas avaliações para determinado filme.
7. Listar avaliações de algum filme.
8. Fazer alterações nas informações de uma avaliação.

## Rotas 🚏

🔹 **[GET]** -> /filmes/all

🛰️ Status: 200 | Response:
```
[
  {
    "id": 1,
    "name": "Filme legal 1",
    "description": "Um filme muito legal",
    "year": 2025,
    "director": "Diretor legal",
    "categories": [
    "ACAO",
    "COMEDIA"
    ],
    "feedbacks": [
      {
        "id": 1,
        "username": "Tio do Zap",
        "comment": "Comentario legal",
        "rating": 5
      },
      {
        "id": 2,
        "username": "Avestruz que te seduz",
        "comment": "Filme daora galera!",
        "rating": 5
      }
    ]
  }
]
```
 
🔹 **[GET]** -> /filmes/:id

🛰️ Status: 200 | Response:
```
{
  "id": 3,
  "name": "Sistemas de Informação",
  "description": "Curso de sistemas",
  "year": 2025,
  "director": "UNIFACOL",
  "categories": [
    "DOCUMENTARIO"
  ],
  "feedbacks": []
}
```

🔹 **[GET]** -> /filmes/categoria/:categoria

🛰️ Status: 200 | Response:
```
[
  {
    "id": 2,
    "name": "Filme divertido",
    "description": "Se divirta assistindo",
    "year": 2000,
    "director": "Diretor divertido",
    "categories": [
      "DRAMA",
      "FANTASIA"
    ],
    "feedbacks": [
      {
        "id": 3,
        "username": "So Vejo Dublado",
        "comment": "Muito emocionante.",
        "rating": 4
        }
    ]
  }
]
```

🔹 **[GET]** -> /feedbacks/filme/:id

🛰️ Status: 200 | Response:
```
[
  {
    "id": 3,
    "username": "So Vejo Dublado",
    "comment": "Muito emocionante.",
    "rating": 4
  } 
]
```
 
❇️ **[POST]** -> /filmes

🛰️ Status: 201 | Body:
  ```
{
	"name": "Novo filme",
	"description": "Descrição do filme",
	"year": 2025,
	"director": "ewertonlx",
	"categories": [
		"DOCUMENTARIO" // Enum: ACAO, COMEDIA, DRAMA, FANTASIA, MISTERY, ROMANCE, TERROR, DOCUMENTARIO
	],
	"feedbacks": [] // Vazio ou um objeto com os dados: username, comment e rating
}
```

❇️ **[POST]** -> /feedbacks/filme/:id

🛰️ Status: 201 | Body:
```
{
  "username": "ewertonlx",
  "comment": "Filme legal!",
  "rating": 5 // 0 à 5
}
```

🔸 **[PUT]** -> /filmes/:id

⚠️ Todas as propriedades são opcionais, ou seja, podendo alterar cada uma individualmente.

🛰️ Status: 204 | Body:
```
{
  "name": "Novo nome do filme",
  "description": "Descrição alterada do filme",
  "year": 2024,
  "director": "ewertonlx alterado",
  "categories": [
  "DOCUMENTARIO" // Enum: ACAO, COMEDIA, DRAMA, FANTASIA, MISTERY, ROMANCE, TERROR, DOCUMENTARIO
  ]
}
```

🔸 **[PUT]** -> /feedbacks/:id

⚠️ Neste caso, apenas as duas propriedades **comment** e **rating** são obrigatórias.

🛰️ Status: 204 | Body:
```
{
  "comment": "Comentário atualizado",
  "rating": 5 // 0 à 5
}
```

🔻 **[DELETE]** -> /filmes/:id

🛰️ Status: 204

🔻 **[DELETE]** -> /feedbacks/:id

🛰️ Status: 204
