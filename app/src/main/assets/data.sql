DROP TABLE IF EXISTS "android_metadata";
CREATE TABLE android_metadata (locale TEXT);
INSERT INTO "android_metadata" VALUES('pt_BR');
DROP TABLE IF EXISTS "hints";
CREATE TABLE "hints" ("_id" INTEGER PRIMARY KEY, "title" TEXT NOT NULL, "description" TEXT NOT NULL, "password" TEXT NOT NULL);
INSERT INTO "hints" VALUES(1, 'Instruções iniciais', 'Isabela, você já é uma especialista quando se trata de caça ao tesouro, por isso você não terá vida fácil por aqui! Hahaha. Mas não se preocupe, eu sei que você consegue. :) Lhe serão propostos vários desafios, que podem estar em locais não tão próximos, portanto, podem levar algumas boas horas desvendando os segredos... Cada pista contém uma enigma que dará acesso a uma palavra-chave que abre a próxima dica. Quando você descobrir a resposta, clique no botão acima e digite a resposta. Está pronta? A próxima pista está em um local em que você se encontra com a beleza em pessoa TODOS os dias.', 'asdf');
INSERT INTO "hints" VALUES(2, 'Varanda', 'Varanda', 'asdf');
INSERT INTO "hints" VALUES(3, 'No escurinho do cinema', 'Comédia, romance, suspense ou ação? Independente do gênero, qualquer filme se torna perfeito se eu estiver com a melhor companhia: você. Então corra e escolha um filme que a sessão vai começar!', 'asdf');
INSERT INTO "hints" VALUES(4, 'Gordice', 'Assisitir um filminho assim em casa, um doce cairia bem... Pegue os ingredientes e faça aquele brigadeiro gostoso antes que o filme comece!', 'asdf');
INSERT INTO "hints" VALUES(5, 'Próximo nível', 'Parabéns, você está indo muito bem! Você já está pegando o jeito. Mas sei que você deve estar achando tudo isso muito fácil. Então está na hora de começar nossa aventura pela cidade! Não sei por quanto tempo isso durará, então se prepare e leve o que você achar necessário para sua sobrevivência. Não tenha medo, seja corajosa e tudo dará certo! A próxima dica está à sua espera em um lugar onde as crianças gostam de brincar.', 'asdf');
INSERT INTO "hints" VALUES(6, 'Hora do passeio', 'Vamos começar dando um passeio pelo parque?', 'asdf');
INSERT INTO "hints" VALUES(7, 'Aventuras no parque', 'Sim, existem várias pistas espalhadas pelo parque e seu objetivo é encontrar todas elas. Use seu GPS para se guiar e procure por mapas em pontos específicos do parque para te ajudar. Investigue, pergunte e procure. Você pode passar por alguns desafios no caminho, por tanto seja corajosa! Pista: em um banco preciso me sentar para observar os patinhos a nadar.', 'asdf');
INSERT INTO "hints" VALUES(8, 'Plantas da academia', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'asdf');
INSERT INTO "hints" VALUES(9, 'Hora de relaxar', 'Que tal assistir a uma partida de vôlei de praia?', 'asdf');
INSERT INTO "hints" VALUES(10, 'Bambuzal', 'Foto.', 'asdf');
INSERT INTO "hints" VALUES(11, 'Metrô', 'Metrô.', 'asdf');
INSERT INTO "hints" VALUES(12, 'Igreja', 'Igreja.', 'asdf');
INSERT INTO "hints" VALUES(13, 'Carona', 'Pegue uma carona.', 'asdf');
INSERT INTO "hints" VALUES(14, 'Pontão', 'Coloque a venda', 'asdf');
