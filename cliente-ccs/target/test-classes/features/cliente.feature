# language:pt

@CriarClienteTeste
Funcionalidade: Cadastrar Cliente PagSeguro com sucesso.

Esquema do Cenario: Cadastro de cliente com sucesso.
Dado que o cliente possui os atributos id <idCliente>, nome "<nome>", endereco "<endereco>", telefone <telefone>, email "<email>", cpf <cpf>
Quando o cliente for criado
Entao o cliente devera ser criado
E o nome do cliente deve ser "<nome>"

Exemplos:
      | idCliente   | nome	    | endereco		    | telefone	| email			    | cpf	    	| dataAtualizacao	|
      | 1           | rafael	| rua dos cravos	| 123		| rafael@rafael.com	| 11122233344   | 2019-08-01		|
