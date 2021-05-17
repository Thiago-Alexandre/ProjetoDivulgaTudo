# ProjetoDivulgaTudo

## O problema

A agência Divulga Tudo precisa de um programa para gerenciar os seus anúncios online. O objetivo dos anúncios faz parte de uma campanha nas redes sociais. O sistema de gerenciamento permitirá a gestão do anúncio e o rastreio dos resultados da campanha.

### 1ª Parte

Considerando os seguintes critérios fictícios para o desenvolvemento da calculadora de alcance de anúncio:

Baseados em dados de análise de anúncios anteriores, a agência tem os seguintes dados:
* a cada 100 pessoas que visualizam o anúncio 12 clicam nele.
* a cada 20 pessoas que clicam no anúncio 3 compartilham nas redes sociais.
* cada compartilhamento nas redes sociais gera 40 novas visualizações.
* 30 pessoas visualizam o anúncio original (não compartilhado) a cada R$ 1,00 investido.
* o mesmo anúncio é compartilhado no máximo 4 vezes em sequência

(1ª pessoa -> compartilha -> 2ª pessoa -> compartilha - > 3ª pessoa -> compartilha -> 4ªpessoa)

Dessa forma, criou-se uma classe Java com um método de Cálculo de Projeção de Visualizações, que recebe o valor investido em reais e a quantidade de níveis de compartilhamento (nesta situação, 3 níveis), retornando uma projeção aproximada da quantidade máxima de pessoas que visualizarão o mesmo anúncio (considerando o anúncio original + os compartilhamentos). Utilizou-se do recurso de recursividade para calcular cada nível, subtraindo a cada chamada do método.

### 2ª Parte

Considerando os critérios fictícios, foi desenvolvido um sistema que permita o cadastro de anúncios. O anúncio irá conter os seguintes dados:

* nome do anúncio
* cliente
* data de início
* data de término
* investimento por dia

O sistema fornecerá os relatórios de cada anúncio contendo:

* valor total investido
* quantidade máxima de visualizações
* quantidade máxima de cliques
* quantidade máxima de compartilhamentos

Os relatórios poderão ser filtrados por intervalo de tempo e cliente.

Utilizou-se Spring Web e Spring Data Mongo para desenvolver uma API Rest que possa persistir e retornar os dados, realizando os cáculos de projeção de visualizações através do Projeto de Calculadora da 1ª etapa. O frontend foi desenvolvido com Bootstrap e JQuery.

## Orientações de uso:

Foi criado o docker-compose do projeto, podendo ser gerado em qualquer computador que contenha o Docker instalado. Para isso, é necessário gerar as imagens do Mongo e do Projeto. Para criar a imagem do Mongo, usa-se o comando:

```docker pull mongo```

Para criar a imagem do Projeto, deve-se chamar o seguinte comando na pasta onde encontra-se o dockerfile do projeto (ProjetoDivulgaTudo\Segunda Parte\API), ou passar todo o caminho até o arquivo:

```docker build -t projeto-divulga-tudo .```

Com isso, basta executar o comando ```docker compose up``` na pasta onde encontra-se o arquivo docker-compose.yml (ProjetoDivulgaTudo\Segunda Parte\API\src\main\resources) e chamar a página index.html (ProjetoDivulgaTudo\Segunda Parte\FrontEnd). O sistema estará pronto para ser usado.

## Observações:

Neste projeto, considera-se a data de início do anúncio como um dia ativo, ou seja, caso o anúncio for cadastrado com a data de início 16/05/2021 e data de término também 16/052021, o anúncio estará ativo por 1 dia.

Quanto as pesquisas por período, todos os anúncios que estiverem pelo menos 1 dia ativos dentro do período pesquisado serão contados no relatório, não importando se começaram ou terminarão fora do período pesquisado.