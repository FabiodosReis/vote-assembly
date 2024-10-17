#Objetivo

Na cooperativa, cada associado possui um voto e as decicoes
sao tomadas em assembleias, por votacao.

Voce precisa criar uma solucao back-end para gerenciar essas
sessooes de votacao.

A solucao deve ser executada na nunvem e prover as seguintes
funcionalidades:

* Cadastrar uma nova pauta.
* Abrir uma sessao de votacao em uma pauta, onde a sessao
  de votacao deve ficar aberta por um tempo determinado ou 1
  minuto por default.
* Receber votos dos associados em pauta, onde os votos sao apenas
  SIM ou NAO, cada associado sera identificado por um ID e pode votar
  apenas uma vez por pauta.
* Contabilizar os votos e da o resultado da votacao da Pauta.
* Importante que as pautas e os votos sejam persistidos e nao
  sejam perdidos com o restarte da aplicacao.

#Bonus
* Verificar o CPF do associado se ele pode votar.
  caso o CPF seja invalido, retornar um erro 400.
  caso o CPF seja valido setar o status do associado
  para "ABLE_TO_VOTE", caso nao seja valido setar o
  status para "UNABLE_TO_VOTE".
* Mensageria e fila
  A votacao precisa ser informada para o restante
  da plataforma isso deve ser feito atravez de 
  mensageria.
* Performace
  Imaginar que a aplicacao possa ser usada em cenario
  de milhares de votos, ela deve ser comportar de maneira
  performatica, teste de performace seria uma boa maneira de
  observar a performace(Jmiter)
* Versionamento da api
  Como fazer o versionamento da aplicacao ?
* Qual estrategia usar para encerrar a pauta da votacao ?
  A aplicacao estara sendo executada em cenario de nuvem.
* Clean arquiteture.

