# TrabFinalPOO

- [x] Cadastrar novo porto (cadastra os dados de um porto. Se já houver portos
cadastrados, assume a distância de 100 milhas náuticas para cada um deles [Se já
houver porto com o identificador indicado, mostra uma mensagem de erro]. Os portos
são mantidos em ordem crescente de identificador).
- [x] Cadastrar novo navio (cadastra os dados de um navio. [Se já houver navio com o
nome indicado mostra uma mensagem de erro]. Os navios são mantidos em ordem
crescente de nome).
- [x] Cadastrar novo cliente (solicita os dados de um cliente [se o código ou e-mail já
existir, mostra uma mensagem de erro]. Os clientes são mantidos em ordem crescente
de código).
- [x] Cadastrar novo tipo de carga (solicita os dados de um tipo da carga, dependendo se
é perecível ou durável [se o número já existir, mostra uma mensagem de erro]. Os tipos
de carga são mantidos em ordem crescente de número).
- [x] Cadastrar nova carga (solicita os dados de uma carga [se o código já existir, mostra
uma mensagem de erro]; coloca a nova carga em uma fila de cargas pendentes; ao
final mostra na tela os dados da nova carga cadastrada, incluindo os dados da origem
e destino, cliente e tipo de carga. As cargas são mantidas em ordem crescente de
código).
- [ ] Consultar todas as cargas (mostra todas as cargas cadastradas: todos os dados das
cargas, incluindo os dados dos portos de origem e destino, do cliente, do tipo de carga
e da situação; se a carga possui um navio designado, mostra os dados do navio e o
valor final do frete [se não há cargas, mostra uma mensagem de erro]).
- [x] Alterar a situação de uma carga (solicita o código de uma carga; mostra os dados da
carga; solicita a nova situação [se não há carga com o código indicado, mostra uma
mensagem de erro; se a carga estiver na situação FINALIZADO, não pode ser alterado
e mostra uma mensagem de erro]).
- [ ] Carregar dados iniciais (solicita o nome do arquivo (sem extensão); carrega os dados
dos arquivos para o sistema; as cargas devem ser carregadas em uma fila de cargas
pendentes; ao final da carga de dados, mostra todos os dados de portos, navios,
clientes, cargas [se houver problemas na carga de dados, mostra uma mensagem de
erro]). Veja o Apêndice sobre os formatos dos arquivos de entrada.
- [x] Fretar cargas (a partir da fila de cargas pendentes; verifica se é possível designar
algum navio disponível para cada carga, e atualiza a sua situação. Se há algum navio
com capacidade de fazer o frete, mas já está designado para outra carga, a carga
retorna para a fila de fretes pendentes. Se não há nenhum navio com capacidade de 
fazer o frete a carga muda para a situação CANCELADO [se não há cargas na fila de
cargas pendentes, mostra uma mensagem de erro]).
- [ ] Salvar dados (solicita ao usuário um nome de arquivo (sem extensão) e salva todos
os dados cadastrados em um ou mais arquivos [se houver algum problema no
salvamento mostra uma mensagem de erro]).
- [ ] Carregar dados (solicita ao usuário um nome de arquivo (sem extensão) e carrega
todos os dados de um ou mais arquivos [se houver algum problema no carregamento
mostra uma mensagem de erro]).
- [ ] Finalizar sistema (termina a execução do sistema).