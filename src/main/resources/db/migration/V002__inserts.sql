-- insere usuário
insert into users
(nome, username, email, senha, nascido_em, telefone, whatsapp, permissao, avatar, ativo)
values('Demétrio Antonio de Santana', 'demetrio.santana@nossaclinica', 'dssid.dev@nossaclinica', '$2a$12$it8GqMWtD3JhyLwYpjLnGu4kZaCR/7I6avvPwiZvpaCRsqW4QKBkW', '1981-09-25', '(81) 99256-0214', true, 'SUPORTE', 'DEMETRIO_SANTANA', true);

--insere especialidades
insert into especialidades (descricao) values ('Acupuntura');
insert into especialidades (descricao) values ('Alergia e Imunologia');
insert into especialidades (descricao) values ('Angiologia');
insert into especialidades (descricao) values ('Cardiologia');
insert into especialidades (descricao) values ('Cirurgia Vascular');
insert into especialidades (descricao) values ('Clínica Médica');
insert into especialidades (descricao) values ('Coloproctologia');
insert into especialidades (descricao) values ('Dermatologia');
insert into especialidades (descricao) values ('Endocrinologia e Metabologia');
insert into especialidades (descricao) values ('Endoscopia');
insert into especialidades (descricao) values ('Fisoioterapia');
insert into especialidades (descricao) values ('Gastroenterologia');
insert into especialidades (descricao) values ('Geriatria');
insert into especialidades (descricao) values ('Ginecologia e Obstetrícia');
insert into especialidades (descricao) values ('Infectologia');
insert into especialidades (descricao) values ('Mastologia');
insert into especialidades (descricao) values ('Medicina do Trabalho');
insert into especialidades (descricao) values ('Medicina Esportiva');
insert into especialidades (descricao) values ('Nefrologia');
insert into especialidades (descricao) values ('Neurocirurgia');
insert into especialidades (descricao) values ('Neurologia');
insert into especialidades (descricao) values ('Nutrologia');
insert into especialidades (descricao) values ('Oftalmologia');
insert into especialidades (descricao) values ('Oncologia Clínica');
insert into especialidades (descricao) values ('Ortopedia e Traumatologia');
insert into especialidades (descricao) values ('Otorrinolaringologia');
insert into especialidades (descricao) values ('Patologia');
insert into especialidades (descricao) values ('Pediatria');
insert into especialidades (descricao) values ('Pneumologia');
insert into especialidades (descricao) values ('Psicologia');
insert into especialidades (descricao) values ('Psicopedagogia');
insert into especialidades (descricao) values ('Psiquiatria');
insert into especialidades (descricao) values ('Radiologia e Diagnóstico por Imagem');
insert into especialidades (descricao) values ('Radioterapia');
insert into especialidades (descricao) values ('Reumatologia');
insert into especialidades (descricao) values ('Urologia');

--insere servicos
insert into servicos (nome, descricao, orientacao) values('Consulta', 'Na consulta o especialista irá escutar suas queixas e o histórico da sua saúde, se necessário solicitará exames para lhe fornecer um diagnóstico mais preciso, te explicar sobre o que fora diagnosticado e lhe apresentará alternativas de tratamento, podendo inclusive prescrever medicações, ou encaminhamentos para outros profissionais ou procedimentos.', null);

insert into servicos (nome, descricao, orientacao) values('Volta', 'O retorno da consulta médica é um momento crucial seja para analisar resultados dos exames solicitados, confirmar ou refutar diagnóstico, avaliar a eficácia do tratamento em curso e identificar a necessidade de novos  procedimentos médicos.', 'Para ter direito à volta você precisa estar dentro do prazo de 15 dias, podendo ser prorrogado apenas pelo especialista, estar com os exames solicitados em mãos, não ter apresentado melhoras ou necessitar receita outra prescrição relacionada ao problema originário ou até mesmo solicitar a troca de medicação por não se dar com a prescrita anteriormente. Porém para todos os casos tem que estar dentro do prazo.');

insert into servicos (nome, descricao, orientacao) values('Sessão', null, null);

insert into servicos (nome, descricao, orientacao) values('ECG - Parecer Cariológico', 'Parecer cardiológico é um exame fundamental para identificar possíveis problemas cardíacos, avaliar riscos antes de cirurgias e orientar sobre o tratamento adequado.', null);

insert into servicos (nome, descricao, orientacao) values('Ecocardiograma', 'O ecocardiograma é um exame de imagem não invasivo que utiliza ondas sonoras de alta frequência para criar imagens em tempo real do coração. Esse exame é indicado para diagnosticar doenças cardíacas como: Insuficiência cardíaca, Doenças das válvulas cardíacas; Cardiomiopatias (doenças do músculo do cardíaco, null); Defeitos congênitos do coração; Tumores cardíacos; Coágulos sanguíneos no coração e etc.', null);

insert into servicos (nome, descricao, orientacao) values('USG das Carótidas', 'A USG das Carótidas é um exame que visa avaliar as artérias carótidas, grandes vasos sanguíneos localizados no pescoço. Esse exame é indicado para diagnosticar doenças como: Aterosclerose(Gorduras nas paredes das artérias carótidas, que podem obstruir o fluxo sanguíneo aumentando o risco de ocorrer um AVC - Acidente Vascular Cerebral, null); Estenoses, que são os estreitamentos nas artérias carótidas; Aneurismas, dilatações anormais nas paredes das artérias que podem causar hemorragias graves. O exame também é recomendado para monitoramento dos tratamentos ou procedimentos cirúrgicos.', null);

insert into servicos (nome, descricao, orientacao) values('C2C - Consulta, Citologia e Colposcopia', 'Uma consulta acompanhada com exames de citologia e colposcopia é um atendimento médico ginecológico completo destinado a avaliar a saúde do colo do útero. A Citologia (Papanicolau) é Um exame preventivo que coleta células do colo do útero para análise em laboratório, esse exame pode detecta alterações celulares que podem indicar o desenvolvimento de câncer ou lesões pré-cancerosas; A Colposcopia é um exame que utiliza um aparelho com lentes de aumento para visualizar o colo do útero em detalhes. Ele permite identificar áreas suspeitas e, se necessário, coletar pequenas amostras de tecido para análise (biópsia).', 'Estar no mínimo 3 dias sem relações sexuais ou sem ter realizado USG transvaginal.');

insert into servicos (nome, descricao, orientacao) values('Aplicação de DIU', 'A aplicação do DIU (Dispositivo Intrauterino) inserido dentro do útero. Ele é um método contraceptivo altamente eficaz e de longa duração.', null);

insert into servicos (nome, descricao, orientacao) values('Remoção de DIU', 'A remoção do DIU é um procedimento simples e geralmente rápido realizado por um profissional de saúde, como ginecologista, no consultório médico.', 'Está no período do ciclo menstrual');

insert into servicos (nome, descricao, orientacao) values('Remoção de Pólipo', 'A remoção do pólipo uterino é feita por meio de uma histeroscopia, um procedimento que permite visualizar o interior do útero para remover o pólipo. É importante remover um pólípo por duas razões, que são: Prevenção do câncer e Alívio de sintomas', null);

insert into servicos (nome, descricao, orientacao) values('Remoção de Sinal', 'A remoção de um sinal, muitas vezes chamada de pinta, pode ser indicada por diversos motivos, geralmente tomada após uma avaliação cuidadosa por um dermatologista. Principais moitvos são: Risco de câncer; Motivos estéticos; Irritação e Dúvida diagnóstica.', null);

insert into servicos (nome, descricao, orientacao) values('Procedimento Cirúrgico', 'A cirurgia pode ser realizada para remover tumores, reparar órgãos danificados, corrigir deformidades ou melhorar a qualidade de vida do paciente', null);

insert into servicos (nome, descricao, orientacao) values('Aplicação de Botox', 'O Botox é um procedimento estético popular utilizado para: Diminuir rugas; Retardar o envelhecimento e Proporcionar um aspecto mais jovem.', null);

insert into servicos (nome, descricao, orientacao) values('Preenchimento Labial', 'O preenchimento labial é um procedimento estético não cirúrgico que visa aumentar o volume dos lábios, definir o contorno ou corrigir assimetrias e Hidratar.', null);

insert into servicos (nome, descricao, orientacao) values('Penoscopia', 'A peniscopia é um exame médico que permite visualizar em detalhes a pele do pênis e das áreas adjacentes. É realizado com uma lupa especial ajuda a identificar lesões ou alterações que não são visíveis a olho nu. Esse exame é indicado para homens que se queixam de verrugas, feridas ou outras alterações na região genital; Homens com múltiplos parceiros sexuais podem ter maior risco de infecção pelo HPV; Homens com histórico de outras infecções sexualmente transmissíveis (ISTs) e Parceiros de pessoas com diagnóstico de HPV.', 'Estar no mínimo 3 dias sem ter relações sexuais.');

insert into servicos (nome, descricao, orientacao) values('Toque Retal', 'O toque retal é um exame médico que consiste na palpação da próstata através do reto para examinar a próstata, verificando seu tamanho, consistência e a presença de nódulos ou outras irregularidades. Esse exame é indicado para homens a partir de 40 à 45 anos, a depender do histórico famíliar ou sintomas e realizado para: Previnir Câncer de próstata; Hiperplasia prostática benigna (HPB, null); Prostatite e Outras alterações.', null);

insert into servicos (nome, descricao, orientacao) values('Urofluxometria', 'A urofluxometria é um exame simples e indolor que mede a velocidade e o volume do fluxo urinário. É indicado para homens que estão com: Dificuldade para iniciar a micção; Jato urinário fraco ou interrompido; Necessidade frequente de urinar; Urgência urinária; Incontinência urináriae e Sensação de esvaziamento incompleto da bexiga. Esse exame é indicado para Diagnosticar problemas urinários, Acompanhar o tratamento e Monitorar doenças', 'Estar com a bexiga cheia.');

insert into servicos (nome, descricao, orientacao) values('Avaliação Urodinâmica', 'A Avaliação Urodinâmica é um exame completo que avalia a função do trato urinário inferior, ou seja, como a bexiga armazena e elimina a urina. É indicado para pessoas com incontinência urinária, dificuldade para urinar, bexiga hiperativa, prolapso de órgão pélvico e para avaliação da função da bexiga para planejamento de procedimento cirúrgico.', 'Estar com a bexiga cheia.');

insert into servicos (nome, descricao, orientacao) values('EDA - Endoscopia Digistiva Alta', 'A endoscopia digestiva alta é um exame que permite visualizar o interior do esôfago, estômago e duodeno (primeira parte do intestino delgado). Esse exame é indicado para: Diagnosticar doenças, tais como: DRGE - Refluxo Gastroesofágico, Úlceras, Gastrite, Câncer, Disfagia e Sangue nas fezes ou vômitos com sangue; Avaliar lesões; Realizar biópsias e Tratar algumas condições', 'Estar em jejum de 8h, podendo tomar apenas água.');

insert into servicos (nome, descricao, orientacao) values('Colonoscopia', 'A colonoscopia é um exame que permite visualizar o interior do intestino grosso (cólon) e do reto através de um tubo fino e flexível chamado colonoscópio. Indicado para: Diagnosticar doenças: Identificar a causa de sintomas como sangramento retal, diarreia, constipação, dor abdominal e alterações no hábito intestinal; Detectar pólipos; Remover pólipos; Diagnosticar câncer colorretal e Avaliar outras condições como colite ulcerativa, doença de Crohn e diverticulite.', null);

insert into servicos (nome, descricao, orientacao) values('PEIM - Aplicação em Microvasos', 'PEIM é a sigla para Procedimento Estético Injetável para Microvasos. É um tratamento minimamente invasivo utilizado para eliminar aqueles vasinhos indesejados que aparecem nas pernas, principalmente.', null);

insert into servicos (nome, descricao, orientacao) values('Limpeza de Pele', 'A limpeza de pele é um procedimento estético que visa remover as impurezas acumuladas na pele, como células mortas, oleosidade excessiva e cravos. Indicado para: Redução de cravos e espinhas; Melhora da textura da pele; Diminuição da oleosidade; Prevenção de acne e Preparação da pele para outros tratamentos.', null);

insert into servicos (nome, descricao, orientacao) values('USG Abdomem Total', 'É um exame gera imagens detalhadas dos órgãos internos do abdômen. Esse exame permite visualizar órgãos como fígado, vesícula biliar, pâncreas, baço, rins e bexiga, além de vasos sanguíneos importantes como a aorta e a veia cava inferior. Esse exame é possível Identificar alterações nos órgãos abdominais, como tumores, cistos, cálculos biliares, inflamações e infecções, verificar se os órgãos estão com o tamanho e formato adequados, Avaliar a circulação sanguínea nos órgãos abdominais, Monitora a evolução de doenças crônicas, como cirrose hepática, doenças renais e pancreáticas e em alguns casos, pode indicar a necessidade de outros exames mais específicos, como a tomografia computadorizada ou a ressonância magnética.', 'Estar em jejum de 8h, tomar luftal para eliminar os gases, tomar bastante água.');

insert into servicos (nome, descricao, orientacao) values('USG Abdomem Superior', 'É um exame de imagens detalhadas dos órgãos localizados na parte superior da cavidade abdominal. Esses órgãos incluem o fígado, a vesícula biliar, as vias biliares, o pâncreas e o baço. Esse exame é fundamental para: Identificar alterações nesses órgãos, como tumores, cistos, cálculos biliares, inflamações e infecções; Monitorar a evolução de doenças crônicas, como cirrose hepática e pancreatite e Ajudar a guiar procedimentos como biópsias e drenagem de cistos.', null);

insert into servicos (nome, descricao, orientacao) values('USG Abdomem Inferior', 'É um exame de imagem que utiliza ondas sonoras de alta frequência para gerar imagens detalhadas dos órgãos localizados na parte inferior da cavidade abdominal. Esses órgãos incluem a bexiga, o útero, os ovários (nas mulheres), a próstata (nos homens) e os rins. Esse tem diversas aplicações, como: Identificar alterações nesses órgãos, como cistos, tumores, cálculos renais, inflamações e infecções. Avaliar o desenvolvimento do feto durante a gravidez, a quantidade de líquido amniótico e a placenta. Ajudar a identificar a causa de dores na região pélvica, como endometriose e cistos ovarianos e em alguns casos, o ultrassom pode indicar a necessidade de outros exames mais específicos.', 'Tomar bastante água.');

insert into servicos (nome, descricao, orientacao) values('USG da Parede Abdominal', 'É um exame que gera imagens detalhadas das estruturas que compõem a parede abdominal, como músculos, gordura, fáscia e pele.´É indicado para:  Identificar diferentes tipos de hérnias, como inguinal, umbilical e incisional, avaliando o tamanho e a extensão da protrusão; Identificar e caracterizar tumores ou massas na parede abdominal; Localizar e avaliar abscessos, que são coleções de pus; Identificar hematomas, que são acúmulos de sangue após traumas; Ajudar a guiar procedimentos como a aspiração de líquido e a biópsia e Avaliar a evolução de doenças e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG Obstétrico', 'É um exame que utiliza ondas sonoras de alta frequência para produzir imagens do bebê em desenvolvimento no útero materno. É um exame seguro, indolor e não invasivo, que permite acompanhar de perto o crescimento e desenvolvimento do bebê durante toda a gestação. Esse exame é fundamental para: Confirmar a gestação e estimar a idade gestacional; Acompanhar o crescimento e desenvolvimento do bebê, verificando se está dentro dos padrões de normalidade;  Identificar possíveis alterações no desenvolvimento do bebê, como problemas cardíacos, malformações craniofaciais e defeitos do tubo neural; Verificar a saúde da placenta, que é responsável pela nutrição do bebê, e a quantidade de líquido amniótico, que protege o bebê; Acompanhar o crescimento do bebê ao longo da gestação, identificando possíveis restrições de crescimento; Avalia a posição do bebê no útero e Permite identificar a presença de gêmeos ou mais bebês e acompanhar o desenvolvimento de cada um.', null);

insert into servicos (nome, descricao, orientacao) values('USG Obstétrico com Doppler', 'É um exame de imagem que, além de fornecer imagens detalhadas do bebê em desenvolvimento no útero, permite avaliar o fluxo sanguíneo entre a mãe e o feto. Essa avaliação é fundamental para verificar a saúde e o bem-estar do bebê durante a gestação.', null);

insert into servicos (nome, descricao, orientacao) values('USG Morforlógico 1º Trimestre', 'É um exame que  gera imagens detalhadas do bebê em desenvolvimento no útero materno. Realizado entre a 11ª e a 14ª semana de gestação, esse exame permite uma avaliação completa da anatomia fetal, que determina a idade gesttacional, avalia a quantidade de líquido amniótico e a placenta e identifica possíveis alterações cromossômicas e malformações congênitas.', null);

insert into servicos (nome, descricao, orientacao) values('USG Morforlógico 2º Trimestre', 'É um exame que  gera imagens detalhadas do bebê em desenvolvimento no útero materno. Realizado entre a 20ª e a 24ª semana de gestação, esse exame permite uma avaliação completa da anatomia fetal, identificando possíveis malformações congênitas e avaliando o crescimento e desenvolvimento do bebê, bem como avalia a placenta e o líquido amniótico, a posição do bebê e permite identificar a presença de gêmeos ou mais bebês e avaliar o desenvolvimento de cada um.', null);

insert into servicos (nome, descricao, orientacao) values('USG Morforlógico 3º Trimestre', 'É um exame que  gera imagens detalhadas do bebê em desenvolvimento no útero materno. Realizado entre a 28ª e a 32ª semana de gestação, esse exame permite uma avaliação completa do crescimento fetal, da placenta e do líquido amniótico, além de verificar a posição do bebê para o parto e identifica possíveis complicações', null);

insert into servicos (nome, descricao, orientacao) values('USG Transvaginal', 'é um exame que gera imagens detalhadas dos órgãos reprodutivos femininos, como útero, ovários, trompas de Falópio e colo do útero. Indicado para: Avaliar a fertilidade; Acompanhar a gravidez; Investigar dores pélvicas; Avaliar sangramentos anormais e Guiar procedimentos.', null);

insert into servicos (nome, descricao, orientacao) values('USG das Mamas', 'É um exame que gera imagens detalhadas do tecido mamário. É um exame indolor, não invasivo e que não utiliza radiação. Indicado para: Complementar a mamografia; Avaliar lesões; Guiar biópsias; Acompanhar o tratamento e Avaliar implantes mamários.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Tireóide', 'É um exame que gera imagens detalhadas da glândula tireoide, localizada na parte anterior do pescoço. É um exame indolor, não invasivo e que não utiliza radiação. Indicado para: Detectar nódulos; Avaliar o tamanho e a forma da tireoide; Avaliar a textura da tireoide; Guiar biópsias e Acompanhar o tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Tireóide com Doppler', 'A ultrassonografia da tireoide com Doppler é um exame de imagem que utiliza ondas sonoras de alta frequência para gerar imagens detalhadas da glândula tireoide e, além disso, avalia o fluxo sanguíneo nessa região. Essa combinação permite uma análise mais completa da saúde da tireóide. é indicado para:  Identificar a presença de nódulos na tireoide, que podem ser benignos ou malignos (câncer); Verificar se a tireoide está aumentada ou diminuída de tamanho e se apresenta alguma alteração em sua forma; Analisar a quantidade de sangue que chega à tireoide e como ele está circulando, o que ajuda a identificar inflamações, tumores e outras alterações; O ultrassom com Doppler pode ser utilizado para guiar a agulha durante a biópsia, garantindo que seja coletada uma amostra precisa da lesão e Permite acompanhar a evolução de nódulos e o efeito do tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG dos Musculoesquelético', 'É um exame que gera imagens detalhadas dos músculos, tendões, ligamentos, articulações e outras estruturas do sistema musculoesquelético. É um exame indolor, não invasivo e que não utiliza radiação. Indicado para: Identificar lesões como tendinite, bursite, rupturas musculares e ligamentares, além de avaliar a extensão e a gravidade dessas lesões; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG de Partes Moles', 'É um que gera imagens detalhadas das estruturas moles do corpo, como músculos, tendões, ligamentos, tecido adiposo e órgãos internos. Indicado para: Identificar lesões como tendinite, bursite, rupturas musculares e ligamentares, além de avaliar a extensão e a gravidade dessas lesões; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG Pélvica Transabidominal', 'É um exame que gera imagens detalhadas dos órgãos da região pélvica, como útero, ovários, bexiga e, em alguns casos, a parte inferior dos intestinos. indicado para: Identificar problemas como endometriose, miomas uterinos, obstrução das trompas e ovários policísticos que podem afetar a fertilidade; Detectar cistos ovarianos, tumores, inflamações e outras alterações nos órgãos reprodutivos femininos; Avaliar o desenvolvimento do feto, a posição da placenta e a quantidade de líquido amniótico, especialmente no início da gestação; Identificar a causa de dores na região pélvica, como endometriose e cistos ovarianos; Investigar a causa de sangramentos vaginais, como miomas, pólipos ou câncer e Avaliar o tamanho e a forma da bexiga, identificar cálculos renais e outras alterações.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Próstata Transabdominal', 'É um exame que gera imagens detalhadas da próstata, uma glândula localizada abaixo da bexiga nos homens. Esse exame é indolor e não invasivo, permitindo avaliar o tamanho, a forma e a textura da próstata, além de identificar possíveis alterações. É recomendado para:  Identificar nódulos, cistos, inflamações e tumores na próstata; Verificar se a próstata está aumentada, o que pode causar problemas urinários; Ajudar a guiar a agulha durante a biópsia da próstata, garantindo que seja coletada uma amostra precisa da lesão e Avaliar a eficácia do tratamento para doenças da próstata, como o câncer.', 'Segundo a recomendação no nosso especialista, o paciente deve estar à 5 dias sem relações sexuais, mastubar, andar de moto, bicicleta ou à cavalo e sem levantar peso durante esse período.');

insert into servicos (nome, descricao, orientacao) values('USG da Próstata Transabdominal com Doppler', 'A ultrassonografia da próstata com Doppler é um exame de imagens detalhadas da próstata, uma glândula localizada abaixo da bexiga nos homens. Além de fornecer imagens estáticas, o Doppler permite avaliar o fluxo sanguíneo na próstata, ou seja, a direção e a velocidade com que o sangue circula nessa região. É indicada para: Identificar nódulos, cistos, inflamações e tumores na próstata; Verificar se a próstata está aumentada, o que pode causar problemas urinários; Analisar a quantidade de sangue que chega à próstata e como ele está circulando, o que ajuda a identificar inflamações, tumores e outras alterações; O ultrassom com Doppler pode ser utilizado para guiar a agulha durante a biópsia, garantindo que seja coletada uma amostra precisa da lesão e Avaliar a eficácia do tratamento para diversas doenças da próstata, como o câncer.', 'Segundo a recomendação no nosso especialista, o paciente deve estar à 5 dias sem relações sexuais, mastubar, andar de moto, bicicleta ou à cavalo e sem levantar peso durante esse período.');

insert into servicos (nome, descricao, orientacao) values('USG da Bolsa Escrotal', 'É um exame que gera imagens detalhadas dos testículos, epidídimos e outras estruturas localizadas na bolsa escrotal. Esse exame é solicitado para: Identificar problemas como varicocele, hidrocele, torção testicular, tumores, inflamações e traumas; Avaliar o tamanho, a forma e a textura dos testículos, que são importantes para a produção de espermatozoides e Avaliar a eficácia do tratamento para diversas condições, como varicocele e tumores testiculares.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Bolsa Escrotal com Doppler', 'A ultrassonografia da bolsa escrotal com Doppler é um exame de imagens detalhadas dos testículos, epidídimos e cordão espermático. Além disso, o Doppler permite avaliar o fluxo sanguíneo nessas estruturas, ou seja, a direção e a velocidade com que o sangue circula. Essa combinação fornece informações importantes sobre a saúde dos testículos e do sistema reprodutor masculino. Incado para: Identificar problemas como varicocele (dilatação das veias do cordão espermático), hidrocele (acúmulo de líquido ao redor do testículo), torção testicular, tumores, inflamações e traumas; Avaliar o tamanho, a forma e o fluxo sanguíneo dos testículos, que são importantes para a produção de espermatozoides e Avaliar a eficácia do tratamento para diversas condições, como varicocele e tumores testiculares.', null);

insert into servicos (nome, descricao, orientacao) values('USG Peniana', 'É um exame que gera imagens detalhadas do pênis. Através desse exame, é possível avaliar a anatomia do pênis, identificar possíveis alterações e auxiliar no diagnóstico de diversas condições. É altamente indicado para homens com Disfunção erétil; Dor peniana durante ou após a ereção; Curvatura peniana durante a ereção; Massas ou nódulos no pênis e Trauma peniano.', null);

insert into servicos (nome, descricao, orientacao) values('USG Peniana com Doppler', 'A ultrassonografia peniana com Doppler é um exame de imagens detalhadas do pênis. Além disso, o Doppler permite avaliar o fluxo sanguíneo no pênis, ou seja, a direção e a velocidade com que o sangue circula nas artérias penianas. Essa combinação fornece informações importantes sobre a saúde sexual masculina. Indicado para: Avaliar o fluxo sanguíneo nos corpos cavernosos do pênis, identificando possíveis obstruções ou alterações que podem causar dificuldades na ereção; Ajudar a guiar procedimentos como a injeção de medicamentos para induzir a ereção e a biópsia de lesões e Avaliar a eficácia do tratamento para diversas condições, como a doença de Peyronie', null);

insert into servicos (nome, descricao, orientacao) values('USG da Vias Urinárias', 'É um exame que gera imagens detalhadas dos órgãos do sistema urinário, como rins, ureteres, bexiga e, nos homens, a próstata. É recomendado para: Identificar alterações como cálculos renais, cistos, tumores, infecções urinárias, obstruções e alterações no tamanho e forma dos órgãos;  Avaliar a eficácia do tratamento para diversas condições, como cálculos renais e tumores e Ajudar a guiar procedimentos como a biópsia renal e a drenagem de cistos', 'Estar com a bexiga cheia.');

insert into servicos (nome, descricao, orientacao) values('USG Vascular', 'É um exame que gera imagens detalhadas das artérias e veias do corpo. Esse exame permite avaliar o fluxo sanguíneo, identificar obstruções, dilatações, coágulos e outras alterações nas paredes dos vasos sanguíneos. é Indicado para:  Identificar doenças como varizes, trombose venosa profunda (TVP), doença arterial periférica, aneurismas e outras condições que afetam a circulação sanguínea; Verificar se o fluxo sanguíneo está adequado em diferentes partes do corpo, como pernas, braços e pescoço; Ajudar a guiar procedimentos como a angioplastia e a colocação de stents e Avaliar a eficácia do tratamento para diversas doenças vasculares', null);

insert into servicos (nome, descricao, orientacao) values('USG dos MMII', 'É um exame que gera imagens detalhadas das estruturas dos membros inferiores, como músculos, tendões, ligamentos, vasos sanguíneos (artérias e veias) e ossos. Esse exame é recomendado para: Identificar lesões como tendinite, bursite, rupturas musculares e ligamentares, além de avaliar a extensão e a gravidade dessas lesões; Identificar áreas de inflamação nas articulações e tecidos moles; Avaliar a circulação sanguínea nas artérias e veias, identificando problemas como varizes, trombose e obstruções arteriais; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG dos MMII com Doppler', 'É um exame que gera imagens detalhadas das artérias e veias das pernas. Além disso, o Doppler permite avaliar o fluxo sanguíneo nessas estruturas, ou seja, a direção e a velocidade com que o sangue circula. É fundamental para: Identificar problemas como varizes, trombose venosa profunda (TVP), doença arterial periférica (DAP), aneurismas e outras condições que afetam a circulação sanguínea; Verificar se o fluxo sanguíneo está adequado nas artérias e veias, identificando obstruções ou estreitamentos; Ajudar a guiar procedimentos como a angioplastia e a colocação de stents e Avaliar a eficácia do tratamento para diversas doenças vasculares.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Joelho', 'A ultrassonografia do joelho é um exame de imagens detalhadas das estruturas internas do joelho, como ossos, cartilagens, ligamentos, tendões e meniscos. É dicado para: Diagnosticar lesões: Identificar lesões como ruptura do ligamento cruzado anterior (LCA), lesões meniscais, tendinite patelar (joelho de saltador), bursite e outras; Identificar áreas de inflamação na articulação, como na sinovite; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido sinovial e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Joelho com Doppler', 'A ultrassonografia do joelho com Doppler é um exame de imagens detalhadas das estruturas internas do joelho, como ossos, cartilagens, ligamentos, tendões e meniscos. Além disso, o Doppler permite avaliar o fluxo sanguíneo nessas estruturas, ou seja, a direção e a velocidade com que o sangue circula. Essa combinação fornece informações mais precisas sobre a saúde do joelho e auxilia no diagnóstico de diversas condições. É indicado para: Identificar lesões como ruptura do ligamento cruzado anterior (LCA), lesões meniscais, tendinite patelar (joelho de saltador), bursite e outras. É solcitado para: Identificar áreas de inflamação na articulação, como na sinovite; Avaliar a circulação sanguínea na região do joelho, identificando possíveis problemas como trombose venosa profunda (TVP) ou obstruções arteriais; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido sinovial e Avaliar a evolução de lesões e a resposta ao tratamento', null);

insert into servicos (nome, descricao, orientacao) values('USG do Ombro', 'A ultrassonografia do ombro é um exame de imagens detalhadas das estruturas internas do ombro, como ossos, cartilagens, ligamentos, tendões, músculos e bursa. É um exame indolor, não invasivo e que não utiliza radiação. Indicado para: Identificar lesões como tendinite, bursite, rupturas do manguito rotador, luxações e instabilidades; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Ombro com Doppler', 'A ultrassonografia do ombro com Doppler é um exame de imagens detalhadas das estruturas internas do ombro, como ossos, cartilagens, ligamentos, tendões, músculos e bursa. Além disso, o Doppler permite avaliar o fluxo sanguíneo nessas estruturas, ou seja, a direção e a velocidade com que o sangue circula. Essa combinação fornece informações mais precisas sobre a saúde do ombro e auxilia no diagnóstico de diversas condições. Indicado para: Identificar lesões como tendinite, bursite, rupturas do manguito rotador, luxações e instabilidades; Identificar áreas de inflamação nas articulações e tecidos moles; Avaliar a circulação sanguíneo na região do ombro, identificando possíveis problemas como trombose ou obstruções arteriais e Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Cotovelo', 'A ultrassonografia do cotovelo é um exame de imagens detalhadas das estruturas internas do cotovelo, como ossos, cartilagens, ligamentos, tendões, músculos e bursa. É um exame indolor, não invasivo e que não utiliza radiação. Indicado para: Identificar lesões como tendinite, bursite, epicondilite (cotovelo de tenista), epitrocleíte (cotovelo de golfista) e rupturas ligamentares; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Cotovelo com Doppler', 'A ultrassonografia do ombro com Doppler é um exame de imagens detalhadas das estruturas internas do ombro, como ossos, cartilagens, ligamentos, tendões, músculos e bursa. Além disso, o Doppler permite avaliar o fluxo sanguíneo nessas estruturas, ou seja, a direção e a velocidade com que o sangue circula. Essa combinação fornece informações mais precisas sobre a saúde do ombro e auxilia no diagnóstico de diversas condições. Indicado para: Identificar lesões como tendinite, bursite, rupturas do manguito rotador, luxações e instabilidades; Identificar áreas de inflamação nas articulações e tecidos moles; Avaliar a circulação sanguíneo na região do ombro, identificando possíveis problemas como trombose ou obstruções arteriais e Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Mão', 'A ultrassonografia da mão é um exame de imagens detalhadas das estruturas internas da mão, como ossos, músculos, tendões, ligamentos e nervos. É indicado para: Identificar lesões como tendinite, síndrome do túnel do carpo, rupturas ligamentares, fraturas, cistos e tumores; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Mão com Doppler', 'A ultrassonografia da mão com Doppler é um exame de imagens detalhadas das estruturas internas da mão, como ossos, músculos, tendões, ligamentos e nervos. Além disso, o Doppler permite avaliar o fluxo sanguíneo nessas estruturas, ou seja, a direção e a velocidade com que o sangue circula. Essa combinação fornece informações mais precisas sobre a saúde da mão e auxilia no diagnóstico de diversas condições. Indicado para: Identificar lesões como tendinite, síndrome do túnel do carpo, rupturas ligamentares, fraturas, cistos e tumores; Identificar áreas de inflamação nas articulações e tecidos moles; Avaliar a circulação sanguínea na mão, identificando possíveis problemas como trombose ou obstruções arteriais; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Tornezelo', 'A ultrassonografia do tornozelo é um exame de imagens detalhadas das estruturas internas do tornozelo, como ossos, cartilagens, ligamentos, tendões e músculos. Indicado para: Identificar lesões como entorses, rupturas de ligamentos (como o ligamento deltóide), tendinite de Aquiles, fraturas por estresse e outras; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Tornezelo com Doppler', 'A ultrassonografia do tornozelo com Doppler é um exame de imagens detalhadas das estruturas internas do tornozelo, como ossos, cartilagens, ligamentos, tendões, músculos e vasos sanguíneos. Além disso, o Doppler permite avaliar o fluxo sanguíneo nessas estruturas, ou seja, a direção e a velocidade com que o sangue circula. Essa combinação fornece informações mais precisas sobre a saúde do tornozelo e auxilia no diagnóstico de diversas condições. É indicado para:  Identificar lesões como entorses, rupturas de ligamentos (como o ligamento deltóide), tendinite de Aquiles, fraturas por estresse e outras; Identificar áreas de inflamação nas articulações e tecidos moles; Avaliar a circulação sanguínea na região do tornozelo, identificando possíveis problemas como trombose venosa profunda (TVP) ou obstruções arteriais; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG Morforlógico com Medição do Últero', 'A ultrassonografia morfológica com medição do útero é um exame de imagens detalhadas do bebê em desenvolvimento no útero materno, além de avaliar o tamanho e a forma do útero. É um exame fundamental para acompanhar a gestação e identificar possíveis complicações. Indicado para:  Com histórico de complicações em gestações anteriores, diabetes gestacional, hipertensão ou outras condições que podem afetar a gravidez; Para avaliar a causa do sangramento e verificar a integridade do colo uterino; Para identificar possíveis causas de dor, como descolamento da placenta ou contrações uterinas e Para identificar precocemente possíveis malformações no feto.', null);

insert into servicos (nome, descricao, orientacao) values('USG de Translucência Nucal', 'A USG de Translucência Nucal é um exame de imagem realizado durante o primeiro trimestre da gestação, geralmente entre a 11ª e a 13ª semana de gestação. Esse exame mede a espessura de uma pequena região na nuca do feto. Ao analisar essa medida, os médicos podem estimar o risco do bebê ter algumas síndromes cromossômicas, como a Síndrome de Down. Indicado para: A medida da translucência nucal é um marcador importante para avaliar o risco de o feto ter síndromes como a Síndrome de Down, Síndrome de Edwards e Síndrome de Patau; Além das síndromes cromossômicas, o exame também pode identificar outros problemas, como malformações cardíacas e defeitos no tubo neural e Ao identificar precocemente possíveis problemas, os pais podem se preparar melhor para o futuro e tomar decisões sobre o acompanhamento da gestação.', null);

insert into servicos (nome, descricao, orientacao) values('USG Região Iguinal', 'A USG da região inguinal é um exame de imagem que utiliza ondas sonoras de alta frequência para gerar imagens detalhadas da região da virilha. É um exame indolor, não invasivo e que não utiliza radiação. Através desse exame, é possível visualizar estruturas como músculos, tendões, vasos sanguíneos, linfonodos e, principalmente, o canal inguinal, onde se localizam as hérnias. É recomendada para: É o principal uso deste exame. Permite identificar diferentes tipos de hérnias, como inguinal, femoral e epigástrica, avaliando o tamanho, a localização e o conteúdo da hérnia; Permite avaliar o tamanho, a forma e a textura dos linfonodos da região, auxiliando no diagnóstico de infecções e outras condições; Permite identificar a presença de massas ou tumores na região inguinal e Pode ser utilizada para guiar procedimentos como a punção de cistos e a biópsia de nódulos', null);

insert into servicos (nome, descricao, orientacao) values('USG da Região Cervical', 'A USG da região cervical é um exame de imagens detalhadas das estruturas do pescoço, como a tireoide, as glândulas salivares, os linfonodos (gânglios linfáticos) e os músculos. Indicado para:  Identificar nódulos, cistos, inflamações e outras alterações na tireoide.; Avaliar o tamanho, a forma e a textura dos linfonodos, auxiliando no diagnóstico de infecções e outras doenças; Identificar inflamações, cálculos e tumores nas glândulas salivares; Avaliar a presença de massas ou tumores em outras estruturas do pescoço e Ajudar a guiar procedimentos como a punção aspirativa por agulha fina (PAAF) para coleta de material para análise.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Braço', 'A USG do braço é um exame de imagens detalhadas das estruturas internas do braço, como músculos, tendões, ligamentos, vasos sanguíneos (artérias e veias), nervos e ossos. É indicado para:  Identificar lesões como tendinite, bursite, rupturas musculares e ligamentares, além de avaliar a extensão e a gravidade dessas lesões; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações; Avaliar a evolução de lesões e a resposta ao tratamento e Avaliar a circulação sanguínea nos vasos sanguíneos do braço, identificando possíveis problemas como trombose ou obstruções arteriais.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Antebraço', 'A USG do antebraço é um exame de imagens detalhadas das estruturas internas do antebraço, como músculos, tendões, ligamentos, vasos sanguíneos (artérias e veias) e ossos. É indicado para: Identificar lesões como tendinite, epicondilite (cotovelo de tenista), epitrocleíte (cotovelo de golfista), rupturas musculares e ligamentares; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações; Avaliar a evolução de lesões e a resposta ao tratamento e Avaliar a circulação sanguínea nas artérias e veias do antebraço, identificando possíveis problemas como trombose ou obstruções arteriais.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Quadril', 'A USG do quadril é um exame de imagem que utiliza ondas sonoras de alta frequência para gerar imagens detalhadas das estruturas internas do quadril, como ossos, cartilagens, ligamentos, tendões, músculos e bursa. É indicado para: Identificar lesões como bursite, tendinite do quadril, lábios acetabulares, impacto femoroacetabular, lesões musculares e tendíneas, além de avaliar a extensão e a gravidade dessas lesões; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações; Avaliar a evolução de lesões e a resposta ao tratamento e: É utilizado para avaliar a displasia do desenvolvimento do quadril (DDQ), uma condição em que a articulação do quadril não se desenvolve corretamente.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Coxa', 'A USG da coxa é um exame de imagem que utiliza ondas sonoras de alta frequência para gerar imagens detalhadas das estruturas internas da coxa, como músculos, tendões, ligamentos, vasos sanguíneos (artérias e veias) e ossos. É indicado para: Identificar lesões como distensões musculares, rupturas tendíneas, bursite, hematomas e trombose venosa profunda; Identificar áreas de inflamação nos músculos e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações; Avaliar a evolução de lesões e a resposta ao tratamentoe Avaliar a circulação sanguínea nas artérias e veias da coxa, identificando possíveis problemas como trombose ou obstruções arteriais.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Punho', 'do punho é um exame de imagem que utiliza ondas sonoras de alta frequência para gerar imagens detalhadas das estruturas internas do punho, como ossos, cartilagens, ligamentos, tendões, músculos e nervos. É indicado para: Identificar lesões como tendinite, síndrome do túnel do carpo, rupturas ligamentares, fraturas, cistos e tumores; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG do Pé', 'do pé é um exame de imagens detalhadas das estruturas internas do pé, como ossos, cartilagens, ligamentos, tendões, músculos e nervos. indicado para: Identificar lesões como tendinite, fascite plantar, fraturas por estresse, lesões ligamentares, neuroma de Morton e outras; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Região Lombar', 'USG da região lombar é um exame de imagens detalhadas das estruturas internas da região lombar, que compreende a parte inferior das costas. Indicado para: Identificar lesões como distensões musculares, rupturas tendíneas e inflamações Avaliar a presença de artrose, inflamação ou outras alterações nas articulações que conectam as vértebras; Embora seja mais difícil de visualizar do que em outros métodos de imagem, a ultrassonografia pode auxiliar no diagnóstico de hérnias de disco em alguns casos; Ajudar a guiar procedimentos como a infiltração de medicamentos em pontos específicos da coluna e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG Esternoclavicular', 'USG esternoclavicular é um exame de imagens detalhadas da articulação esternoclavicular, onde a clavícula se encontra com o esterno. Indicado para: Identificar lesões como luxações, fraturas, tendinite e outras condições que afetam a articulação; Identificar áreas de inflamação na articulação e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Panturrilha', 'USG da panturrilha é um exame de imagens detalhadas das estruturas internas da panturrilha, como músculos, tendões, vasos sanguíneos (artérias e veias) e ossos. É indicado para:  Identificar lesões como distensões musculares, rupturas do tendão de Aquiles, trombose venosa profunda (TVP), hematomas e outras condições; Identificar áreas de inflamação nos músculos e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Região Axilar', 'USG da região axilar é um exame de imagem que utiliza ondas sonoras de alta frequência para gerar imagens detalhadas das estruturas internas da axila, como os linfonodos (gânglios linfáticos), vasos sanguíneos e tecido adiposo. É solicitado para: Identificar aumento, alterações na forma e textura dos linfonodos, que podem indicar processos infecciosos, inflamatórios ou neoplásicos (como o câncer); É um exame complementar fundamental na avaliação de pacientes com câncer de mama, ajudando a identificar a presença de metástases nos linfonodos axilares; Permite acompanhar a resposta ao tratamento de doenças como o câncer, avaliando a redução do tamanho dos linfonodos e Pode ser utilizada para guiar procedimentos como a biópsia de linfonodos, permitindo a coleta de amostras para análise.', null);

insert into servicos (nome, descricao, orientacao) values('USG das Glândulas Salivares', 'USG das glândulas salivares é um exame de imagens detalhadas das glândulas salivares, que são responsáveis pela produção de saliva. É Identificar diversas doenças das glândulas salivares, como cálculos, cistos, tumores (benignos ou malignos), inflamações e infecções; Comparar o tamanho e a forma das glândulas com o padrão de normalidade, identificando possíveis alterações; Identificar alterações na textura do tecido, como endurecimento ou presença de nódulos; Ajudar a guiar procedimentos como a punção aspirativa por agulha fina (PAAF) para coleta de material para análise e Avaliar a evolução de doenças e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG das Vertebras', 'USG da coluna vertebral, ou ecografia da coluna, é um exame de imagens detalhadas das estruturas da coluna vertebral, como músculos, ligamentos, processos espinhosos e, em alguns casos, até mesmo as próprias vértebras. Esse exame serve para: Identificar lesões musculares, inflamações, pontos gatilho e outras alterações nos tecidos moles da coluna; Ajudar a guiar procedimentos como a infiltração de medicamentos em pontos específicos da coluna, como as articulações facetárias; Avaliar a evolução de lesões e a resposta ao tratamento e em alguns casos, pode ajudar a diagnosticar condições como hérnias de disco, especialmente em suas fases iniciais ou em locais específicos da coluna, como a região cervical.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Região Ulna', 'USG da região ulnar é um exame de imagens detalhadas da ulna, um dos ossos do antebraço, e das estruturas ao seu redor, como músculos, tendões, ligamentos e nervos. É solicitado para: Identificar lesões como tendinite, epicondilite medial (cotovelo de golfista), rupturas ligamentares, fraturas por estresse e outras; Identificar áreas de inflamação nas articulações e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Região Mediano', 'USG da região mediana, especificamente no contexto da mão e punho, é um exame de imagens detalhadas das estruturas internas dessa região, como o nervo mediano, tendões flexores e o túnel do carpo. Esse exame é solicitado para: Avaliar o tamanho e a forma do nervo, identificando se há compressão ou espessamento; Avaliar a dimensão do túnel do carpo e identificar qualquer estreitamento que possa estar comprimindo o nervo; Detectar outras causas de dor e sintomas na mão, como tendinite, cistos e tumores e Ajudar a guiar procedimentos como a infiltração de corticosteroides no túnel do carpo para aliviar a inflamação.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Região Fibolar', 'USG da região fibular é um exame de imagem que utiliza ondas sonoras de alta frequência para gerar imagens detalhadas das estruturas internas da região externa da perna, onde se localiza a fíbula, um dos ossos da perna. É solicitado para: Identificar lesões como tendinite do músculo fibular longo e curto, síndrome do compartimento lateral, fraturas por estresse da fíbula, além de avaliar a extensão e a gravidade dessas lesões; Identificar áreas de inflamação nos músculos e tecidos moles; Ajudar a guiar procedimentos como a infiltração de medicamentos e a aspiração de líquido nas articulações e Avaliar a evolução de lesões e a resposta ao tratamento.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Região Tibial Posterior', 'USG da região tibial posterior é um exame de imagem que utiliza ondas sonoras de alta frequência para gerar imagens detalhadas das estruturas internas da parte posterior da perna, com foco especial no tendão tibial posterior. É Solicitado para: Identificar inflamação e degeneração do tendão tibial posterior, uma condição comum que causa dor no tornozelo e no pé; Detectar rupturas parciais ou completas do tendão, que podem levar a instabilidade do tornozelo e deformidades no pé; Ajudar a guiar procedimentos como a infiltração de medicamentos e a fisioterapia e Avaliar a resposta do tendão ao tratamento e monitorar a recuperação.', null);

insert into servicos (nome, descricao, orientacao) values('USG da Próstata Transretal', 'USG da próstata transretal é um exame de imagem que utiliza ondas sonoras de alta frequência para gerar imagens detalhadas da próstata, uma glândula localizada abaixo da bexiga nos homens. A principal diferença deste exame para outros tipos de ultrassom da próstata é que o transdutor (a sonda que emite as ondas sonoras) é inserido no reto para obter imagens mais nítidas e detalhadas da glândula. Esse exame é indicado para: Detectar câncer de próstata, sendo o principal uso deste exame. Permite identificar áreas suspeitas na próstata que podem ser submetidas à biópsia para confirmação do diagnóstico; Permite medir o tamanho da próstata e identificar alterações em sua forma, como nódulos ou áreas de endurecimento; O transdutor pode ser utilizado para guiar a agulha durante a biópsia, garantindo que seja coletada uma amostra precisa da área suspeita e Permite avaliar a eficácia do tratamento para o câncer de próstata ou outras doenças da próstata.', null);

insert into servicos (nome, descricao, orientacao) values('USG Paratireóide', 'USG das paratireoides é um exame de imagens detalhadas das glândulas paratireoides. Essas pequenas glândulas localizadas no pescoço, próximas à tireoide, são responsáveis por regular os níveis de cálcio no organismo. Esse exame é indicado para: Diagnosticar hiperparatireoidismo, aumentando a precisão do diagnóstico e auxiliando na localização da glândula com adenoma para a cirurgia; A ultrassonografia pode guiar a agulha durante a biópsia ou a cirurgia para a remoção do adenoma e Avaliar a eficácia do tratamento após a cirurgia.', null);

insert into servicos (nome, descricao, orientacao) values('USG Cervical Uterina na gestação', 'USG cervical uterina é um exame de imagens detalhadas do colo do útero durante a gravidez. Esse exame permite avaliar o comprimento, a consistência e a posição do colo uterino, que são fatores importantes para prever o risco de parto prematuro. É solicitado para: Verificar se a placenta está inserida corretamente no útero e se há risco de placenta prévia; Detectar alterações como o funil cervical, que é um sinal de encurtamento e amolecimento do colo uterino e Avaliar o crescimento do bebê e a quantidade de líquido amniótico.', null);

insert into servicos (nome, descricao, orientacao) values('Exame Trabalhista', 'Realiza exames para fins trabalhistas, tais como: Exame admicional, Exame demicional, Exame de rotina', null);


-- insere dados da uri
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'Consulta'), 1, 'PRONTUARIO', '/documentos/gerar/prontuario/');
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'Consulta'), 2, 'PRONTUARIO', '/atendimentos/prontuario');

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'), 1, 'PREVENCAO', '/documentos/gerar/colposcopio/');
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'), 2, 'PREVENCAO', '/exames/colposcopio');

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'ECG - Parecer Cariológico'), 1, 'ECG', '/documentos/gerar/ecg/');
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'ECG - Parecer Cariológico'), 2, 'ECG', '/exames/ecc');

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'Ecocardiograma'), 1, 'ECO', '/documentos/gerar/eco/');
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'Ecocardiograma'), 2, 'ECO', '/exames/eco');

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'Exame Trabalhista'), 1, 'EXAME TRABALHISTA', '/documentos/gerar/trabalhista/');
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'Exame Trabalhista'), 2, 'EXAME TRABALHISTA', '/exames/trabalhista/');

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG das Carótidas'), 1, 'USG DAS CAROTIDAS', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG das Carótidas'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG das Carótidas'), 2, 'USG DAS CAROTIDAS', '/exames/usg/' || (select id_servico from servicos where nome = 'USG das Carótidas'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Transvaginal'), 1, 'USG TRANSVAGINAL', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Transvaginal'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Transvaginal'), 2, 'USG TRANSVAGINAL', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Transvaginal'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Pélvica Transabidominal'), 1, 'USG PELVICA TRASABIDOMINAL', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Pélvica Transabidominal'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Pélvica Transabidominal'), 2, 'USG PELVICA TRASABIDOMINAL', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Pélvica Transabidominal'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Vias Urinárias'), 1, 'USG VIAS URINÁRIAS', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Vias Urinárias'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Vias Urinárias'), 2, 'USG VIAS URINÁRIAS', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Vias Urinárias'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Abdomem Total'), 1, 'USG ABDOMEM TOTAL', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Abdomem Total'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Abdomem Total'), 2, 'USG ABDOMEM TOTAL', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Abdomem Total'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Abdomem Superior'), 1, 'USG ABDOMEM SUPERIOR', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Abdomem Superior'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Abdomem Superior'), 2, 'USG ABDOMEM SUPERIOR', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Abdomem Superior'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Abdomem Inferior'), 1, 'USG ABDOMEM INFERIOR', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Abdomem Inferior'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Abdomem Inferior'), 2, 'USG ABDOMEM INFERIOR', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Abdomem Inferior'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Parede Abdominal'), 1, 'USG DA PAREDE ABDOMINAL', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Parede Abdominal'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Parede Abdominal'), 2, 'USG DA PAREDE ABDOMINAL', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Parede Abdominal'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Obstétrico'), 1, 'USG OBSTÉTRICO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Obstétrico'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Obstétrico'), 2, 'USG OBSTÉTRICO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Obstétrico'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Obstétrico com Doppler'), 1, 'USG OBSTÉTRICO COM DOPPLER', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Obstétrico com Doppler'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Obstétrico com Doppler'), 2, 'USG OBSTÉTRICO COM DOPPLER', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Obstétrico com Doppler'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Morforlógico 1º Trimestre'), 1, 'USG MORFORLÓGICO 1º TRIMESTRE', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Morforlógico 1º Trimestre'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Morforlógico 1º Trimestre'), 2, 'USG MORFORLÓGICO 1º TRIMESTRE', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Morforlógico 1º Trimestre'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Morforlógico 2º Trimestre'), 1, 'USG MORFORLÓGICO 2º TRIMESTRE', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Morforlógico 2º Trimestre'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Morforlógico 2º Trimestre'), 2, 'USG MORFORLÓGICO 2º TRIMESTRE', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Morforlógico 2º Trimestre'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Morforlógico 3º Trimestre'), 1, 'USG MORFORLÓGICO 3º TRIMESTRE', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Morforlógico 3º Trimestre'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Morforlógico 3º Trimestre'), 2, 'USG MORFORLÓGICO 3º TRIMESTRE', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Morforlógico 3º Trimestre'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG das Mamas'), 1, 'USG DAS MAMAS', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG das Mamas'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG das Mamas'), 2, 'USG DAS MAMAS', '/exames/usg/' || (select id_servico from servicos where nome = 'USG das Mamas'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Tireóide'), 1, 'USG DA TIREÓIDE', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Tireóide'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Tireóide'), 2, 'USG DA TIREÓIDE', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Tireóide'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Tireóide com Doppler'), 1, 'USG DA TIREÓIDE COM DOPPLER', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Tireóide com Doppler'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Tireóide com Doppler'), 2, 'USG DA TIREÓIDE COM DOPPLER', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Tireóide com Doppler'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG dos Musculoesquelético'), 1, 'USG DOS MUSCULOESQUELÉTICO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG dos Musculoesquelético'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG dos Musculoesquelético'), 2, 'USG DOS MUSCULOESQUELÉTICO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG dos Musculoesquelético'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG de Partes Moles'), 1, 'USG DE PARTES MOLES', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG de Partes Moles'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG de Partes Moles'), 2, 'USG DE PARTES MOLES', '/exames/usg/' || (select id_servico from servicos where nome = 'USG de Partes Moles'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Próstata Transabidominal'), 1, 'USG DA PRÓSTATA TRANSABIDOMINAL', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Próstata Transabidominal'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Próstata Transabidominal'), 2, 'USG DA PRÓSTATA TRANSABIDOMINAL', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Próstata Transabidominal'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Próstata Transabdominal com Doppler'), 1, 'USG DA PRÓSTATA TRANSABDOMINAL COM DOPPLER', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Próstata Transabdominal com Doppler'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Próstata Transabdominal com Doppler'), 2, 'USG DA PRÓSTATA TRANSABDOMINAL COM DOPPLER', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Próstata Transabdominal com Doppler'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Bolsa Escrotal'), 1, 'USG DA BOLSA ESCROTAL', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Bolsa Escrotal'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Bolsa Escrotal'), 2, 'USG DA BOLSA ESCROTAL', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Bolsa Escrotal'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Bolsa Escrotal com Doppler'), 1, 'USG DA BOLSA ESCROTAL COM DOPPLER', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Bolsa Escrotal com Doppler'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Bolsa Escrotal com Doppler'), 2, 'USG DA BOLSA ESCROTAL COM DOPPLER', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Bolsa Escrotal com Doppler'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Peniana'), 1, 'USG PENIANA', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Peniana'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Peniana'), 2, 'USG PENIANA', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Peniana'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Peniana com Doppler'), 1, 'USG PENIANA COM DOPPLER', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Peniana com Doppler'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Peniana com Doppler'), 2, 'USG PENIANA COM DOPPLER', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Peniana com Doppler'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Vascular'), 1, 'USG VASCULAR', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Vascular'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Vascular'), 2, 'USG VASCULAR', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Vascular'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG dos MMII'), 1, 'USG DOS MMII', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG dos MMII'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG dos MMII'), 2, 'USG DOS MMII', '/exames/usg/' || (select id_servico from servicos where nome = 'USG dos MMII'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG dos MMII com Doppler'), 1, 'USG DOS MMII COM DOPPLER', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG dos MMII com Doppler'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG dos MMII com Doppler'), 2, 'USG DOS MMII COM DOPPLER', '/exames/usg/' || (select id_servico from servicos where nome = 'USG dos MMII com Doppler'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Joelho'), 1, 'USG DO JOELHO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Joelho'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Joelho'), 2, 'USG DO JOELHO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Joelho'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Joelho com Doppler'), 1, 'USG DO JOELHO COM DOPPLER', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Joelho com Doppler'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Joelho com Doppler'), 2, 'USG DO JOELHO COM DOPPLER', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Joelho com Doppler'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Ombro'), 1, 'USG DO OMBRO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Ombro'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Ombro'), 2, 'USG DO OMBRO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Ombro'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Ombro com Doppler'), 1, 'USG DO OMBRO COM DOPPLER', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Ombro com Doppler'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Ombro com Doppler'), 2, 'USG DO OMBRO COM DOPPLER', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Ombro com Doppler'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Cotovelo'), 1, 'USG DO COTOVELO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Cotovelo'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Cotovelo'), 2, 'USG DO COTOVELO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Cotovelo'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Cotovelo com Doppler'), 1, 'USG DO COTOVELO COM DOPPLER', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Cotovelo com Doppler'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Cotovelo com Doppler'), 2, 'USG DO COTOVELO COM DOPPLER', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Cotovelo com Doppler'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Mão'), 1, 'USG DA MÃO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Mão'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Mão'), 2, 'USG DA MÃO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Mão'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Mão com Doppler'), 1, 'USG DA MÃO COM DOPPLER', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Mão com Doppler'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Mão com Doppler'), 2, 'USG DA MÃO COM DOPPLER', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Mão com Doppler'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Tornezelo'), 1, 'USG DO TORNEZELO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Tornezelo'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Tornezelo'), 2, 'USG DO TORNEZELO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Tornezelo'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Tornezelo com Doppler'), 1, 'USG DO TORNEZELO COM DOPPLER', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Tornezelo com Doppler'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Tornezelo com Doppler'), 2, 'USG DO TORNEZELO COM DOPPLER', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Tornezelo com Doppler'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Morforlógico com Medição do Últero'), 1, 'USG MORFORLÓGICO COM MEDIÇÃO DO ÚLTERO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Morforlógico com Medição do Últero'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Morforlógico com Medição do Últero'), 2, 'USG MORFORLÓGICO COM MEDIÇÃO DO ÚLTERO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Morforlógico com Medição do Últero'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG de Translucência Nucal'), 1, 'USG DE TRANSLUCÊNCIA NUCAL', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG de Translucência Nucal'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG de Translucência Nucal'), 2, 'USG DE TRANSLUCÊNCIA NUCAL', '/exames/usg/' || (select id_servico from servicos where nome = 'USG de Translucência Nucal'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Inguinal'), 1, 'USG DA REGIÃO INGUINAL', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Região Inguinal'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Inguinal'), 2, 'USG DA REGIÃO INGUINAL', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Região Inguinal'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Cervical'), 1, 'USG DA REGIÃO CERVICAL', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Região Cervical'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Cervical'), 2, 'USG DA REGIÃO CERVICAL', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Região Cervical'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Braço'), 1, 'USG DO BRAÇO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Braço'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Braço'), 2, 'USG DO BRAÇO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Braço'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Antebraço'), 1, 'USG DO ANTEBRAÇO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Antebraço'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Antebraço'), 2, 'USG DO ANTEBRAÇO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Antebraço'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Quadril'), 1, 'USG DO QUADRIL', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Quadril'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Quadril'), 2, 'USG DO QUADRIL', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Quadril'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Coxa'), 1, 'USG DA COXA', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Coxa'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Coxa'), 2, 'USG DA COXA', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Coxa'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Punho'), 1, 'USG DO PUNHO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Punho'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Punho'), 2, 'USG DO PUNHO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Punho'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Pé'), 1, 'USG DO PÉ', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG do Pé'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG do Pé'), 2, 'USG DO PÉ', '/exames/usg/' || (select id_servico from servicos where nome = 'USG do Pé'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Lombar'), 1, 'USG DA REGIÃO LOMBAR', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Região Lombar'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Lombar'), 2, 'USG DA REGIÃO LOMBAR', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Região Lombar'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Esternoclavicular'), 1, 'USG ESTERNOCLAVICULAR', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Esternoclavicular'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Esternoclavicular'), 2, 'USG ESTERNOCLAVICULAR', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Esternoclavicular'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Panturrilha'), 1, 'USG DA PANTURRILHA', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Panturrilha'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Panturrilha'), 2, 'USG DA PANTURRILHA', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Panturrilha'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Axilar'), 1, 'USG DA REGIÃO AXILAR', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Região Axilar'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Axilar'), 2, 'USG DA REGIÃO AXILAR', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Região Axilar'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG das Glândulas Salivares'), 1, 'USG DAS GLÂNDULAS SALIVARES', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG das Glândulas Salivares'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG das Glândulas Salivares'), 2, 'USG DAS GLÂNDULAS SALIVARES', '/exames/usg/' || (select id_servico from servicos where nome = 'USG das Glândulas Salivares'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG das Vertebras'), 1, 'USG DAS VERTEBRAS', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG das Vertebras'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG das Vertebras'), 2, 'USG DAS VERTEBRAS', '/exames/usg/' || (select id_servico from servicos where nome = 'USG das Vertebras'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Ulna'), 1, 'USG DA REGIÃO ULNA', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Região Ulna'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Ulna'), 2, 'USG DA REGIÃO ULNA', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Região Ulna'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Mediano'), 1, 'USG DA REGIÃO MEDIANO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Região Mediano'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Mediano'), 2, 'USG DA REGIÃO MEDIANO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Região Mediano'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Fibolar'), 1, 'USG DA REGIÃO FIBOLAR', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Região Fibolar'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Fibolar'), 2, 'USG DA REGIÃO FIBOLAR', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Região Fibolar'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Tibial Posterior'), 1, 'USG DA REGIÃO TIBIAL POSTERIOR', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Região Tibial Posterior'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Região Tibial Posterior'), 2, 'USG DA REGIÃO TIBIAL POSTERIOR', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Região Tibial Posterior'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Próstata Transretal'), 1, 'USG da Próstata Transretal', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG da Próstata Transretal'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG da Próstata Transretal'), 2, 'USG da Próstata Transretal', '/exames/usg/' || (select id_servico from servicos where nome = 'USG da Próstata Transretal'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Paratireóide'), 1, 'USG PARATIREÓIDE', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Paratireóide'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Paratireóide'), 2, 'USG PARATIREÓIDE', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Paratireóide'));

insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Cervical Uterina na gestação'), 1, 'USG CERVICAL UTERINA NA GESTAÇÃO', '/documentos/gerar/usg/' || (select id_servico from servicos where nome = 'USG Cervical Uterina na gestação'));
insert into uris_geradores_pdf(id_servico, tipo_documento, descricao, uri) values ((select id_servico from servicos where nome = 'USG Cervical Uterina na gestação'), 2, 'USG CERVICAL UTERINA NA GESTAÇÃO', '/exames/usg/' || (select id_servico from servicos where nome = 'USG Cervical Uterina na gestação'));


-- insere dados do colo uterino
insert into analise_colposcipio(descricao, id_servico) values ('Colo Uterino', (select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Pequeno', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Médio', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Grande', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Epitelizado', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Parcialmente Epitelizado', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Ausente', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Parcialmente Visualizado', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Hiperemiado', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Apagado', (select max(id_analise_colposcopio) from analise_colposcipio));

-- insere dados de secreção
insert into analise_colposcipio(descricao, id_servico) values ('Secreção', (select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Mucóide', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Muco Esbranquiçada', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Muco Amarelada', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Muco Sanguinolenta', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Bolhosa', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Achocolatada', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Esbranquiçada', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Amarelada', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Sanguinolenta', (select max(id_analise_colposcopio) from analise_colposcipio));

-- insere dados de orificio cervical
insert into analise_colposcipio(descricao, id_servico) values ('Orifício Cervical', (select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Circular', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Em Fenda TransVersa', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Puntiforme', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Estenosado', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Não Visualizado', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Deformado', (select max(id_analise_colposcopio) from analise_colposcipio));


-- insere dados de mucosa
insert into analise_colposcipio(descricao, id_servico) values ('Mucosa', (select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Original', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Secundário', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Distófica', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Sentil', (select max(id_analise_colposcopio) from analise_colposcipio));


-- insere dados de zona de transoformação normal
insert into analise_colposcipio(descricao, id_servico) values ('Zona de Transformação Normal', (select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Com Cistos de Naboth', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Com Orifício Glandulares', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Com Ilhotas de Eptélio Cilindrico', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('', (select max(id_analise_colposcopio) from analise_colposcipio));


-- insere dados de ectopia
insert into analise_colposcipio(descricao, id_servico) values ('Ectopia', (select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Papilar', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Polipoide', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('', (select max(id_analise_colposcopio) from analise_colposcipio));


-- insere dados de junção escamo colunar
insert into analise_colposcipio(descricao, id_servico) values ('Junção Escamo Colunar', (select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Anterior', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Posterior', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Não Visualizada', (select max(id_analise_colposcopio) from analise_colposcipio));


-- insere dados de junção vacularização
insert into analise_colposcipio(descricao, id_servico) values ('Vascularização', (select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Típica', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Típica Aumentada', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Com Sufões Hemorrágicas', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Com Petéquias', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Atípica', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('', (select max(id_analise_colposcopio) from analise_colposcipio));


-- insere dados de Zona de Transformação Normal
insert into analise_colposcipio(descricao, id_servico) values ('Zona de Transformação Normal', (select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Leucoplasia', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Epitélio Branco', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Pontilhado', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Mosaico', (select max(id_analise_colposcopio) from analise_colposcipio));


-- insere dados de Outros Achados
insert into analise_colposcipio(descricao, id_servico) values ('Outros Achados', (select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Hipartrofia da parede do canal Endocervical', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Formação Polipoide', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Colpite Difusa', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Colpite Focal', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Colpite Micropapilar', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Cisto Hemático', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Lesão Condilomatosa', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Erosão', (select max(id_analise_colposcopio) from analise_colposcipio));


-- insere dados de Teste de Shiller
insert into analise_colposcipio(descricao, id_servico) values ('Teste de Shiller', (select id_servico from servicos where nome = 'C2C - Consulta, Citologia e Colposcopia'));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Iodo Positivo', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Iodo Claro', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Iodo Malhado', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Iodo Negativo', (select max(id_analise_colposcopio) from analise_colposcipio));

insert into achados_colposcipio (descricao, id_analise_colposcopio)
values ('Iodo Mudo', (select max(id_analise_colposcopio) from analise_colposcipio));


--insert parametros report
insert into parametros_reports
(id_parametro_report, papel, largura, altura, largura_da_logo, margem_top_pagina, margem_direita_pagina, margem_rodape_da_pagina, margem_esquerda_da_pagina, background_color_do_cabecalho, background_color_do_rodape, pixel_da_linha, cor_da_linha)
values(1, 'DOCUMENTO_A4', '210mm', '297mm', '100%', '0', '50px', '0', '50px', '#fff', '#fff', '2px', '#2e2646');
insert into parametros_reports
(id_parametro_report, papel, largura, altura, largura_da_logo, margem_top_pagina, margem_direita_pagina, margem_rodape_da_pagina, margem_esquerda_da_pagina, background_color_do_cabecalho, background_color_do_rodape, pixel_da_linha, cor_da_linha)
values(2, 'DOCUMENTO_A5', '148mm', '210mm', '80%', '0', '30px', '0', '30px', '#fff', '#fff', '2px', '#2e2646');