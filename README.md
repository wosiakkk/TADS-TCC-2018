# Mercadão do Aluno   

     Instruções para instalação do sistema                
                                                            


1. Instalar Java SE Development Kit (JDK) v8_131
- https://www.java.com/pt_BR/download/help/download_options.xml (acesso 26/11/2018)

2. Instalar Servidor WEB GlassFish v4.1.1
- https://javaee.github.io/glassfish/doc/5.0/installation-guide.pdf (acesso 26/11/2018)

3. Instalar Servidor de Banco de Dados MySQL v8.0.13
- https://dev.mysql.com/doc/refman/8.0/en/installing.html (acesso 26/11/2018)

4. Executar no MySQL o arquivo de inicialização da base de dados disponilizado.

5. Criar o arquivo DB.properties para conexão com banco de dados:
- Navegar pelos arquivos fontes do sistema até a pasta tccpool/utils;
- Criar um arquivo com o nome "DB" e a extensão "properties", ficando "DB.properties";
- Criar os seguintes atributos neste arquivo: db.driver=com.mysql.jdbc.Driver
                                              db.url=jdbc:mysql://<SERVIDOR>:<PORTA>/<SCHEMA>?autoReconnect=true
                                              db.user=<USUÁRIO>
                                              db.pwd=<SENHA>
- Substituir os valores entre chaves pelos valores que você configurou na instalação do MySQL.

6. Configure o servidor GlassFish
- Copiar os arquivos fontes para um diretório de sua preferência;
- Configurar o servidor indicando onde fica o diretório escolhido;
- Configurar o domínio e a porta do servidor.



###########################################################################################################################

# TADS-TCC
# TCC TADS 
# 2º Semestre de 2018, arquivo oficial para a finalização do projeto.
# Detalhar todos os commits com todas as alterações feitas para melhor controle.
# Bibliotecas, dependências e dumps de BD acessar o link: https://drive.google.com/open?id=1s9kJcwdoJdr7qQRuO_cKgRlIo0TQ9C6u
# Enviar para o Bruno todas as novas adições de bibliotecas ou novos dumps para manter o Google Drive atualizado.
# Utilizar os ícones desse link, que será dado os créditos nas referências para os devidos autores. https://www.flaticon.com/categories
