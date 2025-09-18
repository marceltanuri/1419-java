# E-Commerce API

A Ada tech pretendo realizar a venda de produtos através de um E-Commerce e, para isso, nos contratou com a finalidade de desenvolvermos todo o fluxo necessário. Nesse E-Commerce temos algumas necessidades que serão descritas abaixo.

- Cadastrar, listar, atualizar os clientes da base. Não é necessário a ação de excluir clientes, pois esses devem permanecer na base como histórico.
- Cadastrar, listar, atualizar os produtos da base. Não é necessário a ação de excluir produtos, pois esses devem permanecer na base como histórico.
- Criar uma venda para um cliente. Nessa venda deve ser possível: Adicionar item (produto) com quantidade e preço, remover item (produto), alterar quantidade do item (produto), realizar o pagamento e a entrega.

Regras importantes que foram levantadas junto ao nosso cliente.

- Todo cliente cadastrado precisa ter o documento de identificação.
- Um pedido sempre deve ter a data em que foi criado.
- Um pedido sempre deve iniciar com o status igual a aberto.
- Pedidos com status igual a aberto podem receber itens (produto), alterar quantidade e remover item.
- Os produtos adicionados ao pedido devem ter um valor de venda informado, pois esse valor pode ser diferente do valor do produto.
- Para que o cliente possa realizar a ação de finalizar o pedido, o pedido deve ter ao menos um item e o valor deve ser maior que zero. Também deve-se alterar o status do pagamento para: "Aguardando pagamento" e notificar o cliente via e-mail.
- A ação de pagar deve acontecer apenas sobre vendas que estejam com o status igual a "Aguardando pagamento" e, após o pagamento acontecer com sucesso, deve-se alterar o status do pagamento para "Pago" e, também, notificar o cliente.
- Após o pagamento realizado o pedido pode ser entregue ao cliente e o status alterado para "Finalizado". - Não esqueça de notificar o cliente sobre a entrega.

## Funcionalidades

- **Clientes**
    - Cadastrar novos clientes (com documento de identificação obrigatório)
    - Listar clientes cadastrados
    - Atualizar dados dos clientes
    - Exclusão de clientes não permitida (mantidos como histórico)

- **Produtos**
    - Cadastrar novos produtos
    - Listar produtos cadastrados
    - Atualizar dados dos produtos
    - Exclusão de produtos não permitida (mantidos como histórico)

- **Pedidos**
    - Criar pedidos para clientes
    - Adicionar itens (produtos) ao pedido, informando quantidade e preço
    - Remover itens do pedido
    - Alterar quantidade dos itens
    - Realizar pagamento do pedido
    - Realizar entrega do pedido

- **Cupom de desconto (Adicionado após o módulo POOII)**
    - Criar cupons de desconto
    - Aplicar cupons de desconto aos pedidos
    - Listar cupons de desconto disponíveis
    - Atualizar cupons de desconto
    - Expirar cupons de desconto

- **Regras de desconto (Adicionado após o módulo POOII)**
    - Criar regras de desconto simples e compostas.

## Regras de Negócio

- Todo cliente deve possuir documento de identificação
- Pedido deve registrar data de criação e iniciar com status **Aberto**
- Pedidos **Abertos** podem receber itens, alterar quantidade e remover itens
- Valor de venda do produto pode ser diferente do valor cadastrado
- Para finalizar pedido:
    - Deve conter ao menos um item
    - Valor total deve ser maior que zero
    - Status do pagamento alterado para **Aguardando pagamento**
    - Cliente deve ser notificado por e-mail
- Pagamento só pode ser realizado em pedidos com status **Aguardando pagamento**
    - Após pagamento, status alterado para **Pago**
    - Cliente deve ser notificado
- Após pagamento, pedido pode ser entregue e status alterado para **Finalizado**
    - Cliente deve ser notificado sobre a entrega
- Cupons de desconto devem ser validados antes de serem aplicados, incluindo (Adicionado após o módulo POOII):
    - Verificação de validade (data de expiração)
    - Verificação de uso (se já foi utilizado)
    - Aplicação correta do desconto no valor total do pedido
- Regras de desconto (Adicionado após o módulo POOII):
    - Regras simples: Aplicação direta de um desconto fixo ou percentual
    - Regras compostas: Combinação de múltiplas regras, como descontos progressivos ou condicionais

## Notificações (Existente desde módulo POOII, mas será aprimorado nesse módulo)

- Notificação por e-mail ao cliente nas etapas de:
    - Finalização do pedido
    - Pagamento realizado
    - Entrega do pedido