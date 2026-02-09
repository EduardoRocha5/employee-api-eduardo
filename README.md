## Objetivo do Projeto
Este projeto tem como foco a prática de **testes unitários em Java**, utilizando JUnit e Mockito, conforme apresentado no material "Como funciona um teste unitário na prática".

## O que deve ser implementado

- Criação de testes unitários utilizando JUnit
- Uso do padrão AAA 
- Testes de fluxo de sucesso
- Testes de exceções
- Uso de mocks com Mockito 
- Validação de interações com verify()
- Foco em regras de negócio 

## Estrutura das Rotas da API

| Verbo | Rota | Descrição |
|-----|-----|----------|
| POST | `/api/employees` | Cria um novo funcionário |
| GET | `/api/employees` | Lista todos os funcionários |
| GET | `/api/employees/{id}` | Busca um funcionário por ID |
| PUT | `/api/employees/{id}` | Atualiza os dados de um funcionário |
| DELETE | `/api/employees/{id}` | Remove um funcionário |

## Contributing / Entrega do Trabalho

Siga os passos abaixo para realizar a entrega:

1. Faça um fork do projeto  
2. Clone o repositório forkado:
   ```bash
   git clone https://github.com/SEU_USUARIO/SEU_PROJETO.git
   git checkout -b minha-solucao
   git commit -am "feat: Implementado testes unitários..."
   git push origin minha-solucao
3. Abra um Pull Request para o repositório original
