![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)

# Tio Benny's Clínica Veterinária

## Descrição do projeto

O sistema foi feito para o gerenciamento de uma clínica veterinária, que automatiza as seguintes funções:

- Cadastro de animais;
- Geração de relatórios veterinários;
- Armazenamento do histórico médico dos animais.

O projeto foi desenvolvido principalmente na linguagem Java para a matéria de Programação Orientada a Objetos, utilizando o Spring Framework.

## Instalação e execução

Primeiramente é preciso clonar o repositório em sua máquina. Para fazer isso, acesse a página principal do repositório e clique no botão "Code" e para exibir o link e copie-o.

Através da linha de comando ou usando um IDE, faça a clonagem. Usando o Intellij como exemplo, feche qualquer projeto que esteja aberto, clique em "Get from VCS", cole o link no campo URL, escolha a pasta em que deseja clonar e clique em "Clone" na parte inferior da tela.

Execute a classe "CareApplication" e acesse no navegador o endereço da aplicação, que é _localhost:8080_.

## Como usar

Na tela inicial você verá alguns gráficos e estatísticas dos atendimentos da clínica,
além dos botões para ir à aba "animais" e a barra de pesquisa.

![Tela inicial 1](./screenshots/10_tela_inicial_1.png)

![Tela inicial 2](./screenshots/11_tela_inicial_2.png)

![Tela inicial 3](./screenshots/12_tela_inicial_3.png)

Na aba "Animais", você verá uma lista dos animais cadastrados. Neste caso, ainda não há nenhum animal cadastrado.

![Lista de Animais](./screenshots/1_animais.png)

Ao clicar em "Novo animal", será apresentada a ficha de cadastro de animal. Podem ser cadastrados cães e gatos, e
cada um tem um atributo especial. Cães possuem "raça" e gatos possuem "tipo de pelagem".

![Cadastrar cão](./screenshots/2_cachorro.png)

![Cadastrar gato](./screenshots/3_gato.png)

Ao clicar em salvar, você será redirecionado à aba "Animal" novamente, onde agora há registros de animais.

![Lista de Animais com registros](./screenshots/4_animais_cadastrados.png)

Ao clicar em um animal é possível ver os dados de seu registro.

![Dados do Animal](./screenshots/5_dados_do_animal.png)

É possível fazer um relatório médico clicando em "Novo Relatório".

![Relatório não preenchido](./screenshots/6_relatorio_nao_preenchido.png)

![Relatório preenchido](./screenshots/7_Relatorio_preenchido.png)

Após cadastrar um relatório, ele aparecerá no "Feed Médico", com os relatórios mais recentes no topo.

![Feed Médico](./screenshots/8_Feed_medico.png)

Para excluir o registro de um animal, clique em "Deletar". Um pop-up aparecerá para que a decisão seja
confirmada.

![Excluir animal](./screenshots/9_Deletar_animal.png)

## Desenvolvido por:

- Guilherme Ferraz Ronha
- Matheus Biazotto
- Matheus Manzoli
- Raíne Felix
- Tiago Ferreira

Professor: Marcos Roberto de Morais
