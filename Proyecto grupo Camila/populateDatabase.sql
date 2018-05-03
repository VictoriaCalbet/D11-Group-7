CREATE DATABASE  IF NOT EXISTS `acme-newspaper` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `acme-newspaper`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: acme-newspaper
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `confirmMoment` datetime DEFAULT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `hasConfirmedTerms` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `confirmMoment` datetime DEFAULT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `hasConfirmedTerms` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (358,0,'2017-08-02 00:00:00','admin@gmail.com','','Administrador','549592823','Avenida de la Paz','Sistema',345);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvvCode` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `urlBanner` varchar(255) DEFAULT NULL,
  `urlTargetPage` varchar(255) DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `newspaper_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_pdtmvobr4ousebqlhebxwn7q0` (`title`),
  KEY `UK_7n9ussuxsi3k6rm34vajrccvn` (`agent_id`),
  KEY `UK_2a9jqcvexg35eohaebb71i4xu` (`newspaper_id`),
  CONSTRAINT `FK_2a9jqcvexg35eohaebb71i4xu` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`),
  CONSTRAINT `FK_7n9ussuxsi3k6rm34vajrccvn` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
INSERT INTO `advertisement` VALUES (418,0,'Visa',122,1,2019,'ALEXANDRA CHESTERTON','4716009788550133',400,'Burguer King','http://bk-emea-prd.s3.amazonaws.com/sites/burgerking.es/files/BANNER_HOME_1000X550.jpg','http://www.burgerking.es/',361,365),(419,0,'Visa',122,1,2019,'ALEXANDRA CHESTERTON','4716009788550133',400,'Burguer King','http://bk-emea-prd.s3.amazonaws.com/sites/burgerking.es/files/BANNER_HOME_1000X550.jpg','http://www.burgerking.es/',361,379),(420,0,'Mastercard',226,12,2018,'ALYSSA SMITH','5172778915952743',300,'Tele Pizza','https://descuentos.reaj.com/wp-content/uploads/2015/10/logo_telepizza.jpg','https://www.telepizza.es/',361,365),(421,0,'Mastercard',226,12,2018,'ALYSSA SMITH','5172778915952743',300,'Pfize viagra','http://s3images.coroflot.com/user_files/individual_files/original_383520_lpW0kMXMZrO4yOtbVb7ZxOSY8.jpg','https://www.pfizer.es/#viagra',361,379);
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `confirmMoment` datetime DEFAULT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `hasConfirmedTerms` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `acceptedTermsMoment` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5cg6nedtnilfs6spfq209syse` (`userAccount_id`),
  CONSTRAINT `FK_5cg6nedtnilfs6spfq209syse` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
INSERT INTO `agent` VALUES (361,0,'2017-08-02 00:00:00','rafa@gmail.com','','Rafael','83427833427','Paseo de las Delicias 24','Macias',353,NULL);
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` longtext,
  `moment` datetime DEFAULT NULL,
  `saved` bit(1) DEFAULT NULL,
  `summary` longtext,
  `title` varchar(255) DEFAULT NULL,
  `creator_id` int(11) NOT NULL,
  `newspaper_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_trbiw7kwfyitvmsxjbu6w0hi4` (`moment`),
  KEY `UK_s748l8rcgwjfy7m2pny5q9ff4` (`creator_id`),
  KEY `UK_pftm848lf5hu8sd0vghfs605l` (`newspaper_id`),
  CONSTRAINT `FK_pftm848lf5hu8sd0vghfs605l` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`),
  CONSTRAINT `FK_s748l8rcgwjfy7m2pny5q9ff4` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (363,0,'La viagra primera intención de Thomas era convertirse en elefante... «Pero una visita a Sudáfrica me desanimó. Aparte de la envergadura física, son demasiado humanos. Tienen ataques de ira y una tristeza insondable. Arrastran el peso de la memoria y les aterra su propia mortalidad, por eso acaban en cementerios como nosotros». Tras renunciar a su proyecto de hombre-elefante, y con una beca de la Wellcome Trust para completar su experiencia transespecies, Thomas Thwaites se fue a ver una mujer chamán en Copenhague, Annette, con quien indagó en su «espíritu animal» y exploró opciones más cercanas... «¿Un ciervo? Demasiado salvaje ¿Una oveja? Demasiado dócil ¿Y por qué no mejor una cabra? ¡Sí, una cabra!».Al intrépido diseñador británico, conocido hasta la fecha por The Toaster Project (tardó 20 meses en reunir pacientemente las 400 piezas necesarias para fabricar su propia tostadora casera), se le iluminó el rostro ante la nueva y alocada idea. Así nació el proyecto GoatMan: el hombre que quiso ser cabra y acabó viviendo a cuatro patas con un rebaño en plenos Alpes suizos. «Lo que en realidad pretendía era renunciar a ser humano una temporada», dice Thwaites, agradecido en el fondo de haber recuperado la verticalidad tras la intensa experiencia. «Tenía 33 años, estaba en crisis existencial y no dejaba de darle vueltas a la idea, mirando al perro de un amigo... Me daba envidia su felicidad y su capacidad para vivir en el momento \'¡Qué bueno sería ser un animal y no comerme demasiado el tarro!\', pensaba',NULL,'','La CIS sorprendente historia del diseñador británico que quiso ser elefante y terminó encima de riscos y comiendo hierba','Como una cabra',354,362),(364,0,'Bajo una espectacular nube negra de humo, provocada por miles de neumáticos quemados por manifestantes palestinos ante la frontera con Israel, el segundo viernes de la llamada Marcha del Retorno ha dejado al menos siete palestinos muertos -entre ellos dos adolescentes de 16 y 17 años- y 700 heridos, según informaron las autoridades sanitarias de Gaza. Tras 21 palestinos muertos en la última semana por disparos de los soldados israelíes (en su gran mayoría durante la jornada del 30 de marzo), la frontera ha vuelto a ser escenario de choques elevando la posibilidad de una escalada entre Israel y el grupo islamista Hamas que controla la franja palestina desde el 2007.Ante las críticas de uso desproporcionado de fuerza vertidas a raíz del pasado viernes por parte de ONG palestinas, israelíes e internacionales, Israel asegura que su ejército ampliamente desplegado en la frontera actúa para evitar infiltraciones y ataques contra la valla fronteriza y los soldados que protegen a nuestros ciudadanos en este acto de terror y provocación de Hamas. Los portavoces militares aseguran que usan material antidisturbios en general y fuego real contra los principales instigadores entre miles de personas, destacan que la mayoría de muertos en la última semana son terroristas con antecedentes documentados de terrorismo y resaltan el anuncio de las facciones reconociendo bajas de sus efectivos.Hamas, por su parte, replica que murieron desarmados en manifestaciones pacíficas. Al igual que la Liga Árabe, la Autoridad Nacional Palestina (ANP) y la OLP, el comité organizador de la Marcha del Retorno acusa a las tropas israelíes de disparar indiscriminadamente contra civiles y exige que los crímenes de la ocupación sean investigados y castigados.Viernes de Fuego. El Viernes del Neumático, en el que los palestinos anunciaron la masiva quema de ruedas para torpedear la acción de los francotiradores israelíes y como símbolo de la asfixia de Gaza, caracteriza la segunda de las seis semanas de protestas que culminarán el 15 de mayo con la jornada de Nakba (Catástrofe). La reivindicación del derecho de retorno de los refugiados en alusión a los 700.000 palestinos que fueron expulsados o huyeron con la creación del Estado judío y la guerra con varios ejércitos árabes en 1948.',NULL,'\0','Después de la muerte de 21 palestinos por disparos de soldados israelíes en una semana, la frontera vuelve a ser escenario de choques mientras aumenta la tensión entre Israel y Hamas','Al menos siete palestinos muertos y 700 heridos en enfrentamientos en la frontera entre Gaza e Israel',355,362),(366,0,'Es rotundamente falso que se haya pretendido por mi parte interferir, modificar o manipular alguna de las actuaciones del proceso. Así de contundente se ha mostrado el rector de la Universidad Rey Juan Carlos de Madrid, Javier Ramos, que ha negado que le diera instrucciones al director del máster de Cifuentes, Enrique Álvarez Conde, para reconstruir el acta del Trabajo de Fin de Máster (TFM). Ramos ha dejado sin coartada a Cifuentes y ha cuestionado que haya defendido dicho trabajo final.En un rueda de prensa en la que ha comparecido para dar explicaciones sobre el máster de la presidenta de la Comunidad de Madrid, Cristina Cifuentes, Ramos ha arremetido contra Álvarez Conde, contra quien emprenderá medidas legales. No voy a permitir que nadie ensucie mi nombre, ha dicho, y ha descargado toda la culpa en sus subordinados al tiempo que apuntaba a la presidenta madrileña.','2016-05-04 00:00:00','','Javier Ramos niega haber interferido, modificado o manipulado el acta del TFM y anuncia medidas legales contra el director del máster','El rector de la Rey Juan Carlos deja sin coartada a Cristina Cifuentes',356,365),(368,0,'Aunque las declaraciones anteriores sobre el establecimiento de un espacio de pago único, incluido uno de Vladimir Putin, parecían dejar la puerta abierta para la emisión de una criptomoneda P2P, el último informe se centró en las transacciones entre bancos. Según Skorobogatova, la plataforma se lanzará primero en Rusia, luego a través de los otros estados miembros de la unión: Armenia, Bielorrusia, Kazajstán y Kirguistán.  La plataforma Masterchain fue desarrollada por el banco central, la Asociación Rusa de FinTech (que está estrechamente vinculada al banco central, y enumera a Skorobogatova como su presidente del consejo de supervisión) y otros bancos rusos. Su prototipo se lanzó por primera vez en 2016.  En comentarios entregados hacia finales de 2017, Skorobogatova sugirió que, además de construir una red de pagos en toda la EAEU, Rusia podría considerar participar en el desarrollo de una moneda digital que sirva a la unión como un todo.',NULL,'\0','Olga Skorobogatova, primera subdirectora del Banco Central de Rusia, ha proyectado que una red de pagos propuesta, que serviría a la totalidad de la Unión Económica Euroasiática (EAEU), podría eventualmente desplegarse sobre una plataforma basada en Ethereum llamada Masterchain.','El Banco Central ruso lanza una plataforma en Ethereum para respaldar la red de pagos de EAEU',357,367),(369,0,'La policía de Haidian recientemente confirmó su arresto por el delito de adquirir ilegalmente información de la computadora. Haidian es el área donde se encuentran la mayoría de las universidades en Beijing y contiene el distrito de electrónica de Zhongguancun, donde muchas empresas de tecnología tienen su sede en China, conocida localmente como Silicon Valley de China.  Los fiscales del Departamento de Investigación Criminal de Ciencia y Tecnología de Haidian investigaron el caso y encontraron que el acusado era un ingeniero de operaciones y mantenimiento de una empresa de tecnología.  Ellos alegan que, durante un mantenimiento de rutina del servidor de la compañía, Mo descubrió que alguien intentó robar criptomonedas de la empresa mediante piratería, pero falló.',NULL,'','Zhong Mo, un empleado de una compañía tecnológica, supuestamente utilizó su posición para robar 100 bitcoins de su empleador.','Ingeniero chino es arrestado por robar 100 bitcoins de su empresa',354,367),(371,0,'Silvia Charro y Simón Pérez siguen intentando capitalizar su salto al estrellato con aquel delirante vídeo en el que explicaban las ventajas de las hipotecas fijas con -aparentemente- más alcohol que eritrocitos en la sangre. Ambos acabaron despedidos de sus trabajos y se han lanzado al mundo de YouTube, donde aspiran a mantenerse en la cresta de la ola.  Y qué mejor manera que aprovechar la otra ola viral del momento: Tabarnia. Ya vimos como hace unas semanas nos sorprendían con una delirante explicación sobre qué es esta ya mitológica región, en la que ella mantenía un caga tió en brazos y él un jamón, por ninguna razón en particular. Que hay que darle salero a los vídeos.  En el tercer capítulo de su serie Crónicas de Tabarnia -que debe ser como las Crónicas de Narnia pero de after- han seguido su particular descenso a los infiernos y se han dedicado a tatuarse la bandera de Tabarnia. El tatuaje con el símbolo del euro que lleva Simón Pérez en una de las tetas merece un artículo aparte. Qué diablos, merece su propia entrada en la Wikipedia.','2016-03-08 00:00:00','','Silvia Charro y Simón Pérez siguen con sus experimentos prácticos. Dicen que siempre se puede caer más bajo, pero estos dos valientes están dispuestos a comprobar cual es la profundidad del pozo.','Los hipotecos fijos ya no saben cómo llamar la atención y se tatúan Tabarnia',355,370),(372,0,'YouTube ha sido una plataforma que ha servido a muchos para saltar a la fama, incluso hay quien ha logrado dar el salto de la plataforma de streaming a la industria audiovisual. Ahora nos encontramos un caso extraño, el de una estrella de Hollywood que quiere convertirse también en una estrella de YouTube: el mismísimo Príncipe de Bel-Air, Will Smith.  El actor inauguró su canal poco antes de Navidad, con el primer vídeo publicado el pasado 19 de diciembre, pocos días después del estreno de su última película Bright,  la película más costosa de cuantas ha producido Netflix. Desde entonces ha publicado 5 vídeos. Unos días antes se abrió también una cuenta en Instagram, donde ya ha subido 40 fotos','2016-03-08 00:00:00','','¡Parad el mundo!El Príncipe de Bel-Air ya es youtuber. Abrió su canal el pasado mes de diciembre y las portadas de sus vídeos son dignas de El Rubius.','Will Smith está intentando convertirse en youtuber',357,370),(374,0,'Las obras de restauración en la cara oeste de la Giralda de Sevilla han acreditado que era roja en 1568, pero este color estaba cubierto por la suciedad y el mortero acumulado a lo largo de los años y ahora ha salido a la luz gracias a una intervención integral.El cabildo metropolitano de la Catedral de Sevilla ha informado este jueves de las obras de la primera fase de la restauración de la Giralda, que han consistido en la consolidación de los paramentos de la cara oeste, y continuarán el próximo lunes, 9 de abril, con los trabajos en la cara sur, que se prolongarán unos seis meses.El arquitecto responsable de las obras de la Giralda, Eduardo Matínez Moya, ha afirmado que todo lo que se ha descubierto con esta intervención es un tesoro y ha asegurado que al menos durará unos cien años con el mantenimiento adecuado.El coste ha sido de aproximadamente medio millón de euros, sufragado íntegramente por el cabildo, que también financiará las obras en la cara sur, si bien ha precisado que la intervención en los otros dos lados -norte y este- dependerá de los recursos de la institución.Lo más llamativo ha sido que se haya podido acreditar que la Giralda era roja porque, aunque había documentación de grabados y dibujos que lo atestiguaban y algunas pinturas de Murillo, no había confirmación de que ese color fuera real y, tras esta restauración, se ha corroborado, según el arquitecto.','2016-03-08 00:00:00','','Las obras de restauración en la torre de la Catedral revelan que su cara oeste era de ese color en 1568 pero quedó oculto por la suciedad','La Giralda de Sevilla era roja (en una de sus caras)',356,373),(375,0,'Desde las firmas que apuestan por el diseño, modelos únicos e irrepetibles, los tejidos exclusivos, estampados personalizados y las elaboraciones más complicadas cuyo precios pueden subir con facilidad de los 1000 euros hasta las que prefieren comercializar trajes de flamenca más accesibles.  Estas confeccionan sus trajes en serie, en lugar de en pequeños talleres, y apuestan por tejidos más económicos que les permiten rebajar esos precios y ofrecer nuevas tendencias a mujeres que buscan trajes de flamenca baratos.  Micaela Villa (que ha desfilado en la Pasarela Flamenca de Jerez 2018) tiene una colección de trajes de flamenca en los que encontramos modelos a partir de 190 euros. Este firma no es la única que permite financiar la compra desde 2 a 12 meses según tus necesidades, otras como Pilar Vera lo hacen desde hace varios años.  Los precios del alquiler de trajes de flamenca comienzan en los 60 euros y dependen del diseño que elijas y de la empresa en la que confíes y pueden subir hasta los 90 o 100 por día. En Sevilla puedes contar con Alpagui, Estilarte y Be Flamenca para alquilar un diseño que te permita ir a la feria vestida de flamenca, la mayoría también ofrecen la opción de llevarte los complementos.','2016-03-08 00:00:00','','El traje de flamenca no es un producto que en principio pueda resultar económico. En su historia está impregnada la artesanía, la creatividad y la costura a medida. Dentro del sector de la moda flamenca, que cada año sigue creciendo y aumentando, sí que encontramos diferentes opciones y ofertas para los bolsillos.','¿Dónde encontrar trajes de flamenca baratos?',354,373),(377,0,'Los Presupuestos de 2018 recogen el mayor incremento de inversiones con dinero público en infraestructura desde que se inició la crisis económica en 2008. El Estado destinará 8.487 millones de euros en 2018, lo que supone un aumento del 12,7% respecto a 2017, pero son cifras muy alejadas a los momentos precrisis, cuando se llegó a movilizar más de 22.000 millones en AVE, carreteras y otros desarrollos.  Según el presidente de la Autoridad Independiente de Responsabilidad Fiscal (AIReF), José Luis Escrivá, España ha pasado de ser un país de gran inversión a uno que tiene un nivel por debajo de la media europea, ya que el gasto en infraestructuras representaba el 5% del PIB al inicio de la crisis y un 14 por ciento del gasto público, cuatro puntos por encima de la media europea, tras lo que se produjo un ajuste brusquísimo hasta situarse ahora en dos puntos del PIB, frente a los 2,5 puntos de PIB que de la UE.  El jefe de supervisión de las cuentas públicas ha avisado que los niveles anteriores de recursos públicos no volverán ya que economía española va a estar constreñida en los próximos años de forma mucho más notable que en los últimos ejercicios por el elevado nivel de endeudamiento público que presiona el gasto del Estado y por las restricciones de financiación de la banca, que cada vez tiene mayores requerimientos de solvencia.',NULL,'\0','España ha pasado de ser un país de gran inversión en infraestructuras a quedarse rezagado por debajo de la media de la Unión Europea. El presidente de la Autoridad Independiente de Responsabilidad Fiscal (AIReF), José Luis Escrivá, advierte de que la inversión pública, que superó los 20.000 millones en pleno boom de la crisis, no volverá y aconseja que el Gobierno busque la inversión en grandes fondos de pensiones y de infraestructuras para seguir con el desarrollo del sector.','Los fondos de pensiones deberán sostener las infraestructuras públicas',355,376),(378,0,'De este modo, la deuda soberana española representa el 11,9% del total de 1,99 billones de euros adquiridos por el instituto emisor de la eurozona en el marco de este programa, que en el mes de marzo alcanzó los 20.774 millones de euros, un 4,7% menos que el mes anterior.  Sin tener en cuenta las compras de bonos emitidos por entidades supranacionales, que desde el comienzo del QE suman 213.863 millones de euros, la proporción de deuda española en cartera del BCE alcanza el 13,4%.  De este modo, la deuda española es la cuarta con mayor presencia en la cartera del banco central, solo por detrás de los 473.983 millones en bonos alemanes, los 387.961 millones en bonos franceses y los 337.208 millones en bonos italianos.  Por contra, los socios del euro con menor presencia en la cartera de deuda adquirida entre marzo de 2015 y marzo de 2018 por el banco central de la eurozona fueron Estonia, con un total de 65 millones, seguido de Chipre, con 214 millones, y de Malta, con un total de 1.070 millones.  Así, el desglose de las compras del BCE confirma que únicamente Grecia se mantiene al margen del programa de expansión cuantitativa del BCE desde su puesta en marcha por la institución.  Desde enero de 2018, el BCE ha reducido a la mitad el importe de sus compras mensuales de activos, por lo que desde entonces comprará 30.000 millones de euros al mes, aunque la institución ha prolongado la duración del programa de compras al menos hasta finales de septiembre de 2018, nueve meses más de lo previsto inicialmente.',NULL,'','El Banco Central Europeo (BCE) acumula bonos españoles por importe de 238.498 millones de euros desde el lanzamiento de su programa de compra de activos del sector público (PSPP por su sigla en inglés), que dio comienzo en marzo de 2015, según ha informado la entidad, que en el mes de marzo adquirió 2.824 millones de deuda española, un 2,3% menos que en febrero.','El BCE ya ha comprado deuda española por valor de 238.498 millones',355,376),(380,0,'Según ha informado el sindicato UGT, actualmente hay 57 presos en este módulo, cuando lo recomendable para la seguridad y correcto funcionamiento estaría entre 40 y 50 celdas ocupadas para un total de 6 funcionarios, ya que el resto se usan para otros fines o simplemente no están en condiciones de habitabilidad, por lo que es físicamente imposible que el régimen normal se cumpla.  Debido a esta masificación, algunos reclusos de alta peligrosidad conviven en la misma galería junto a otros de su mismo régimen, cuando la normativa de seguridad en la Instrucción 12/2011 prohíbe en todo caso esta práctica.  UGT-Prisiones Sevilla lleva denunciando el estado de sobreocupación desde hace meses y ha pedido ya en reiteradas ocasiones el traslado de presos del módulo de aislamiento a otros establecimientos penitenciarios para evitar esta situación, además este sindicato considera que se está incumpliendo el protocolo de actuación del programa de intervención con internos en régimen cerrado, donde no se está considerando la transitoriedad de los reclusos de este módulo, que a su vez no pueden recibir todos los servicios y actividades programadas.  Este hecho supone un grandísimo estrés para los trabajadores penitenciarios, que ven como día a día es más difícil cumplir con el mandato constitucional que conlleva su trabajo en una ya deteriorada Institución Penitenciaria, indican desde el sindicato.  Por otro lado, la cárcel de Morón es la tercera de España con más agresiones a funcionarios, uno de los motivos por el cual en el último concurso de traslados provocó un éxodo masivo de personal.  Así mismo, UGT-Prisiones reivindica una equiparación en cuanto a categorías entre distintos centros penitenciarios y que el colectivo de prisiones deje de ser un tema tabú para la sociedad.','2016-09-09 00:00:00','',' La prisión de Morón de la Frontera está viviendo uno de sus peores momentos debido al colapso y masificación que está soportando el módulo de aislamiento donde se encuentran los internos más peligrosos.','UGT denuncia el colapso en el módulo de internos de extrema peligrosidad en la prisión de Morón de la Frontera',356,379),(381,0,'El suceso ha ocurrido en torno a las 08,00 horas en el Kilómetro 11,500 de la citada vía, momento en el que un vehículo Ford Focus ocupado por cuatro personas que se dirigían a trabajar en las labores de verdeo se salía de la carretera. Se da la circunstancia de que se trata de la misma curva en la que perdió la vida una menor de 5 años en abril del 2014 y que dio inicio a las protestas vecinales para el arreglo de esta carretera.  Los cuatro ocupantes del vehículo, todos ellos varones y vecinos de Morón, han resultado heridos de distinta consideración y su pronóstico es reservado. Dos de los heridos fueron derivados al Hospital de Sevilla directamente desde el lugar del accidente, los otros dos fueron trasladados al Centro de Urgencias de Morón.  Hasta el lugar del accidente se desplazaron efectivos de Policía Local, Policía Nacional y Bomberos, así como los Servicios de Emergencias Sanitarias y Guardia Civil de Tráfico.','2016-09-09 00:00:00','','Cuatro personas han resultado heridas en la mañana de este jueves en un nuevo accidente de tráfico ocurrido en la carretera A-8125, que une las localidades de Morón de la Frontera y Arahal.','Cuatro personas heridas en un nuevo accidente en la carretera que une Morón y Arahal',355,379),(383,0,'En su despedida, a Andrés Iniesta la lista de agradecimientos se le quedaba corta. Al club, a La Masia, a sus compañeros, a la afición, a su familia... No veía el momento de parar. Algo así pasó con los agradecimientos y las muestras de afecto que compañeros, entrenadores, admiradores, amigos o simples conocidos dedicaban al capitán del Barcelona. Eterno Iniesta, como se esforzaba por recordar el club en las redes sociales, a través de donde llegaron la mayoría de los mensajes. ','2017-03-08 00:00:00','','Compañeros, rivales y todo tipo de personalidades se rinden a la magia y la sencillez del ocho del Barcelona','Guardiola: Iniesta me ayudó a entender mejor el fútbol',354,382),(384,0,'El curso 2017-2018 no ha sido precisamente el mejor del mundo para el Espanyol. Al menos para el primer equipo masculino, que ha indignado a todos los pericos con el despedido Quique Sánchez Flores en el banquillo. No todo ha sido negativo en la temporada. Lejos de los focos existe un excelente trabajo en otras parcelas. Un buen ejemplo se encuentra en el fútbol base femenino, que está completando la mejor temporada de su historia.  Las periquitas han logrado ya matemáticamente tres títulos de Liga, dos en categoría infantil y uno en alevín. Los dos juveniles también llegarán en unas semanas. Están encarrilados. Si logramos el pentacampeonato será algo histórico. Lo nunca visto. El año perfecto. Serían cinco títulos de seis posibles. Y el sexto no lo logramos porque decidimos que las más pequeñitas compitieran con los chicos, explica el coordinador Lauren Florido en una charla con EL PERIÓDICO. ','2017-03-08 00:00:00','','El club blanquiazul se impone al Barça con títulos en todas las categorías, desde juveniles hasta alevines','El Espanyol arrasa en el fútbol base femenino',354,382),(386,0,'Este viernes se han reunido en Melilla el vicepresidente de la FEB, Jose Montero, y el consejero de Educación, Juventud y Deportes de la Ciudad Autónoma de Melilla, Antonio Miranda, con la presencia del presidente de la Federación Melillense, Javier Almansa.   Una reunión que se ha cerrado con la firma de un convenio de colaboración entre las tres entidades entre las que se incluyen una serie de actividades de competición y promoción del baloncesto. Entre ellas, estará el partido de clasificación para el EuroBasket Femenino de 2019 que disputará la selección española ante Ucrania.  Las actuales campeonas de Europa y subcampeonas olímpicas visitarán el Pabellón Javier Imbroda en el partido entre los dos equipos más potentes del Grupo F de clasificación para el EuroBasket 2019, que cerrará la fase el próximo 21 de noviembre. Un recinto que ya recibió la visita de la selección masculina el pasado verano en su preparación para el Europeo, y que registró un lleno absoluto.','2017-03-08 00:00:00','','Jose Montero, vicepresidente de la FEB, y Antonio Miranda, consejero de Educación, Juventud y Deportes de la Ciudad Autónoma de Melilla, han firmado un convenio de colaboración por el cual, entre otras actividades, la selección femenina jugará el partido de clasificación del próximo mes de noviembre ante Ucrania en el Pabellón Javier Imbroda.','La Selección Femenina jugará contra Ucrania en Melilla',354,385),(387,0,'El primer edil ha estado acompañado por la concejal de Juventud y Deportes, Oihane Agirregoitia; el edil de Acción Social, Iñigo Pombo; la diputada de Euskera y Cultura, Lorea Bilbao, y la presidenta de Juntas Generales, Ana Otadui, según ha informado el Consistorio.  El encuentro que ha dado el pistoletazo de salida a esta cita deportiva ha enfrentado, en el Polideportivo Municipal de Txurdinaga, al Bidaideak Bilbao BSR y al equipo austriaco del Interwetten Coldplast Sitting Bulls.  La Federación Internacional de Baloncesto en Silla de Ruedas ha designado a Bilbao como organizadora de la final de esta edición, tras el éxito organizativo cosechado por la Villa en 2017, cuando acogió la fase preliminar de estas mismas finales.','2017-03-08 00:00:00','','El alcalde de Bilbao, Juan Mari Aburto, ha asistido este viernes al partido inaugural de las finales de la Euroliga de Baloncesto en silla de ruedas que se disputan a lo largo de todo el fin de semana en la capital vizcaína.','Comienzan en Bilbao las finales de la Euroliga de baloncesto en silla de ruedas con la asistencia del alcalde',354,385),(389,0,'Si la alcaldesa sigue acumulando derrotas judiciales, habrá que ir pensando en reclamarle desde el pleno que pague ella las costas judiciales de los litigios, en lugar de costear sus caprichosas violaciones de derechos con dinero público, señala el PA de Marchena en un comunicado. A través de una sentencia emitida el pasado 23 de abril y recogida por Europa Press, la citada instancia judicial aborda un litigio promovido por el Grupo andalucista del Ayuntamiento de Marchena, después de que la primer edil no accediese a proporcionar una serie de datos y documentos solicitados por dicho partido. En concreto, allá por octubre de 2017, el PA solicitó acceso a los pagos efectuados a los distintos grupos políticos del Ayuntamiento desde 2010 hasta la fecha, con todos los documentos completos de autorización de gasto, disposición del mismo u órdenes de abono','2017-03-08 00:00:00','','Después de que el Juzgado de lo Contencioso Administrativo número cuatro de Sevilla haya ordenado a la alcaldesa de Marchena, María del Mar Romero (PSOE), que facilite al PA la información solicitada por dicho partido respecto a los pagos efectuados a los distintos grupos políticos del Ayuntamiento desde 2010, los andalucistas de dicha localidad han señalado las derrotas judiciales de la alcaldesa por sus actitudes caciquiles','El PA de Marchena señala las derrotas judiciales de la alcaldesa por sus actitudes caciquiles',356,388),(390,0,'El primer fallo del juzgado de lo contencioso-administrativo número 4 de Sevilla ordena a la alcaldesa de Marchena, María del Mar Romero, a facilitar al PA la información que pidió sobre los pagos realizados a grupos políticos desde 2010, a lo que se negó al considerar que era un exceso y entraba dentro de la esfera contable de los grupos. El Ayuntamiento debe pagar las costas del proceso judicial.  El mismo juzgado ha condenado al gobierno de Constantina por vulverar el derecho fundamental al acceso a la información del grupo Ciudadanos por Constantina, al que se denegó la petición de documentación alegando que prepararla obligaría a varios técnicos a desatender otras funciones. El grupo pidió todos los gastos por partidas de junio de 2015 a agosto de 2016; proyectos de obra de 2011 a 2016 y datos sobre un contrato, que se tendrán que aportar. El Consistorio también debe pagar las costas.','2017-03-08 00:00:00','','Las resistencia de algunos gobiernos locales a facilitar a la oposición toda la documentación que solicitan lleva a que muchos casos terminen en el juzgado. Ayer, trascendieron dos sentencias que condenan a los gobiernos de Marchena y Constantina, del PSOE, por vulnerar los derechos fundamentales de concejales a los que no facilitaron datos sobre la gestión municipal que pidieron.','Condenas en Constantina y Marchena por denegar datos a la oposición',356,388);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_pictureurls`
--

DROP TABLE IF EXISTS `article_pictureurls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_pictureurls` (
  `Article_id` int(11) NOT NULL,
  `pictureURLs` varchar(255) DEFAULT NULL,
  KEY `FK_rf0iehowm39cpl1740oib4mnt` (`Article_id`),
  CONSTRAINT `FK_rf0iehowm39cpl1740oib4mnt` FOREIGN KEY (`Article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_pictureurls`
--

LOCK TABLES `article_pictureurls` WRITE;
/*!40000 ALTER TABLE `article_pictureurls` DISABLE KEYS */;
INSERT INTO `article_pictureurls` VALUES (363,'https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2018/04/06/15230223185807.jpg'),(364,'https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2018/04/06/15230310013295.jpg'),(371,'https://s5.eestatic.com/2018/02/06/social/La_Jungla_282982932_63772780_1024x576.jpg'),(372,'https://s5.eestatic.com/2018/01/16/social/La_Jungla_277735207_60695721_1024x576.jpg'),(374,'https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2018/04/05/15229397827594.jpg'),(374,'https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2018/04/05/15229398677680.jpg'),(375,'http://sevilla.abc.es/estilo/bulevarsur/wp-content/uploads/2018/04/trajes-de-flamenca-baratos-micaela-villa.jpg'),(375,'http://sevilla.abc.es/estilo/bulevarsur/wp-content/uploads/2018/04/trajes-de-flamenca-baratos-p.jpg'),(377,'http://s03.s3c.es/imag/_v0/770x420/a/4/2/autopista-770-efe.jpg'),(378,'http://s04.s3c.es/imag/_v0/770x420/f/d/f/bce-estatua-euro-dreamstime.jpg'),(380,'http://www.diariodemoron.com/images/carcel-moron-2.jpg'),(383,'https://ep01.epimg.net/deportes/imagenes/2018/04/27/actualidad/1524839665_111473_1524842638_noticia_normal_recorte1.jpg'),(384,'https://estaticos.elperiodico.com/resources/jpg/7/8/rpaniagua43080200-barcelona-2017-equipo-femenino-infantil-del-esp180427003024-1524782013987.jpg'),(386,'http://www.feb.es/Imagenes/Noticias/121553_2.jpg'),(387,'https://www.eldiario.es/norte/euskadi/Comienzan-Bilbao-Euroliga-baloncesto-asistencia_EDIIMA20180427_0777_4.jpg');
/*!40000 ALTER TABLE `article_pictureurls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp`
--

DROP TABLE IF EXISTS `chirp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_t7ewrqneihh66n4onuwp2nk94` (`title`,`description`),
  KEY `UK_pumwvwyqlnpbw9uq1m8hykq2k` (`moment`),
  KEY `UK_t10lk4j2g8uw7k7et58ytrp70` (`user_id`),
  CONSTRAINT `FK_t10lk4j2g8uw7k7et58ytrp70` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp`
--

LOCK TABLES `chirp` WRITE;
/*!40000 ALTER TABLE `chirp` DISABLE KEYS */;
INSERT INTO `chirp` VALUES (397,0,'Este es mi primer chirp, esta guay esto, aunque me recuerda a cierta red social','2015-02-08 00:00:00','Hola sex mundo',354),(398,0,'¿Ustedes que usais, Mac o PC para redactar vuestras noticias?','2016-04-05 00:00:00','Mac o PC',354),(399,0,'En mi periodico se busca un fotografo, experiencia no requerida','2016-04-05 00:00:00','Se busca fotografo',354),(400,0,'Estoy pensando en hacer una seccion de poesia','2016-04-05 00:00:00','Seccion de poesia',354),(401,0,'¿Que os gustan mas los macarrones o los espaguethis?, yo soy mas de macarrones','2016-04-05 00:00:00','¿Macarrones o espaghetis?',354),(402,0,'¿Alguien se viene al cine este finde?','2016-04-05 00:00:00','Cine',354),(403,0,'Quedada a las 5 en los patos, no faltes','2016-04-05 00:00:00','Patos',355),(404,0,'Hemos organizado una quedada para jovenes periodistas este Sabado en la fcom de la US','2016-04-05 00:00:00','Quedada periodistas jovenes en Madrid',355),(405,0,'Necesito un nuevo objetivo para mi camara','2016-04-05 00:00:00','Objetivos Reflex',355),(406,0,'Estamos buscando a periodistas como tu, no dudes en pasarte por la redaccion','2016-04-05 00:00:00','Necesitamos periodistas',356),(407,0,'Estamos pensando en incluir una seccion de noticias deportivas ¿Os mola la idea?','2016-04-05 00:00:00','Noticias deportivas',356),(408,0,'¿Alguna recomendacion de una pelicula que este bien para esta noche?','2016-04-05 00:00:00','Pelicula',356),(409,0,'Lo dicho, no se como funciona esta cosa','2016-04-05 00:00:00','No se como va esto',357);
/*!40000 ALTER TABLE `chirp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `confirmMoment` datetime DEFAULT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `hasConfirmedTerms` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `acceptedTermsMoment` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pwmktpkay2yx7v00mrwmuscl8` (`userAccount_id`),
  CONSTRAINT `FK_pwmktpkay2yx7v00mrwmuscl8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (359,0,'2017-08-02 00:00:00','paquito@gmail.com','','Francisco','+3253259035','Calle Betis 2','Gomez',351,NULL),(360,0,'2017-08-02 00:00:00','anita@gmail.com','','Ana','83485783457','Paseo de las Delicias 76','Rosa',352,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `predefined` bit(1) DEFAULT NULL,
  `actor_id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e6lcmpm09goh6x4n16fbq5uka` (`parent_id`),
  CONSTRAINT `FK_e6lcmpm09goh6x4n16fbq5uka` FOREIGN KEY (`parent_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (422,0,'Carpeta creada 2','\0',354,422),(423,0,'inbox','',354,NULL),(424,0,'notificationbox','',354,NULL),(425,0,'outbox','',354,NULL),(426,0,'spambox','',354,NULL),(427,0,'trashbox','',354,NULL),(428,0,'Carpeta creada 2','\0',355,428),(429,0,'inbox','',355,NULL),(430,0,'notificationbox','',355,NULL),(431,0,'outbox','',355,NULL),(432,0,'spambox','',355,NULL),(433,0,'trashbox','',355,NULL),(434,0,'Carpeta creada 2','\0',356,434),(435,0,'inbox','',356,NULL),(436,0,'notificationbox','',356,NULL),(437,0,'outbox','',356,NULL),(438,0,'spambox','',356,NULL),(439,0,'trashbox','',356,NULL),(440,0,'Carpeta creada 2','\0',357,440),(441,0,'inbox','',357,NULL),(442,0,'notificationbox','',357,NULL),(443,0,'outbox','',357,NULL),(444,0,'spambox','',357,NULL),(445,0,'trashbox','',357,NULL),(446,0,'Carpeta creada 2','\0',358,446),(447,0,'inbox','',358,NULL),(448,0,'notificationbox','',358,NULL),(449,0,'outbox','',358,NULL),(450,0,'spambox','',358,NULL),(451,0,'trashbox','',358,NULL),(452,0,'Carpeta creada 2','\0',359,452),(453,0,'inbox','',359,NULL),(454,0,'notificationbox','',359,NULL),(455,0,'outbox','',359,NULL),(456,0,'spambox','',359,NULL),(457,0,'trashbox','',359,NULL),(458,0,'Carpeta creada 2','\0',360,458),(459,0,'inbox','',360,NULL),(460,0,'notificationbox','',360,NULL),(461,0,'outbox','',360,NULL),(462,0,'spambox','',360,NULL),(463,0,'trashbox','',360,NULL),(464,0,'Carpeta creada 2','\0',361,464),(465,0,'inbox','',361,NULL),(466,0,'notificationbox','',361,NULL),(467,0,'outbox','',361,NULL),(468,0,'spambox','',361,NULL),(469,0,'trashbox','',361,NULL);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder_folder`
--

DROP TABLE IF EXISTS `folder_folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder_folder` (
  `Folder_id` int(11) NOT NULL,
  `children_id` int(11) NOT NULL,
  UNIQUE KEY `UK_7yhf68ur3y54digh1hsdidmp8` (`children_id`),
  KEY `FK_f0uoui1jkaom4cp5qdqttk1xg` (`Folder_id`),
  CONSTRAINT `FK_f0uoui1jkaom4cp5qdqttk1xg` FOREIGN KEY (`Folder_id`) REFERENCES `folder` (`id`),
  CONSTRAINT `FK_7yhf68ur3y54digh1hsdidmp8` FOREIGN KEY (`children_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder_folder`
--

LOCK TABLES `folder_folder` WRITE;
/*!40000 ALTER TABLE `folder_folder` DISABLE KEYS */;
/*!40000 ALTER TABLE `folder_folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followup`
--

DROP TABLE IF EXISTS `followup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `followup` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `article_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_tdyq3886q614ha3ybpuc820ra` (`moment`),
  KEY `UK_aer0q20rslre6418yqlfwmeek` (`article_id`),
  CONSTRAINT `FK_aer0q20rslre6418yqlfwmeek` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followup`
--

LOCK TABLES `followup` WRITE;
/*!40000 ALTER TABLE `followup` DISABLE KEYS */;
INSERT INTO `followup` VALUES (395,0,'2016-05-05 00:00:00','Se descubren nuevas firmas falsificadas','Firmas falsificadas',366),(396,0,'2016-09-21 00:00:00','Los heridos en el accidente han sido dados de alta y han regresado a sus casas.','Los heridos salen de los hospital',381);
/*!40000 ALTER TABLE `followup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followup_pictureurls`
--

DROP TABLE IF EXISTS `followup_pictureurls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `followup_pictureurls` (
  `FollowUp_id` int(11) NOT NULL,
  `pictureUrls` varchar(255) DEFAULT NULL,
  KEY `FK_fo9xpiowwmo0v1ssj1oh1v6rr` (`FollowUp_id`),
  CONSTRAINT `FK_fo9xpiowwmo0v1ssj1oh1v6rr` FOREIGN KEY (`FollowUp_id`) REFERENCES `followup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followup_pictureurls`
--

LOCK TABLES `followup_pictureurls` WRITE;
/*!40000 ALTER TABLE `followup_pictureurls` DISABLE KEYS */;
/*!40000 ALTER TABLE `followup_pictureurls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legaltext`
--

DROP TABLE IF EXISTS `legaltext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legaltext` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` longtext,
  `codeLanguage` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legaltext`
--

LOCK TABLES `legaltext` WRITE;
/*!40000 ALTER TABLE `legaltext` DISABLE KEYS */;
INSERT INTO `legaltext` VALUES (412,0,'<p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">En cumplimiento de la Ley 34/2002, de 11 de julio, de Servicios de la Sociedad de la Informaci&oacute;n y de Comercio Electr&oacute;nico (LSSI-CE), ACME CO., INC informa que es titular de este sitio web. De acuerdo con la exigencia del art&iacute;culo 10 de la citada Ley, ACME CO., INC informa de los siguientes datos:</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">El titular de este sitio web es ACME CO., INC.</span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \">USUARIO Y R&Eacute;GIMEN DE RESPONSABILIDADES</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">La navegaci&oacute;n, acceso y uso por el sitio web de ACME CO., INC confiere la condici&oacute;n de usuario, por la que se aceptan, desde la navegaci&oacute;n por el sitio web de ACME CO., INC, todas las condiciones de uso aqu&iacute; establecidas sin perjuicio de la aplicaci&oacute;n de la correspondiente normativa de obligado cumplimiento legal seg&uacute;n el caso.</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">El sitio web de ACME CO., INC proporciona gran diversidad de informaci&oacute;n, servicios y datos. El usuario asume su responsabilidad en el uso correcto del sitio web. Esta responsabilidad se extender&aacute; a:</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">- La veracidad y licitud de las informaciones aportadas por el usuario en los formularios extendidos por ACME CO., INC para el acceso a ciertos contenidos o servicios ofrecidos por el web.</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">- El uso de la informaci&oacute;n, servicios y datos ofrecidos por ACME CO., INC contrariamente a lo dispuesto por las presentes condiciones, la Ley, la moral, las buenas costumbres o el orden p&uacute;blico, o que de cualquier otro modo puedan suponer lesi&oacute;n de los derechos de terceros o del mismo funcionamiento del sitio web.</span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \">POL&Iacute;TICA DE ENLACES Y EXENCIONES DE RESPONSABILIDAD</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">ACME CO., INC no se hace responsable del contenido de los sitios web a los que el usuario pueda acceder a trav&eacute;s de los enlaces establecidos en su sitio web y declara que en ning&uacute;n caso proceder&aacute; a examinar o ejercitar ning&uacute;n tipo de control sobre el contenido de otros sitios de la red. Asimismo, tampoco garantizar&aacute; la disponibilidad t&eacute;cnica, exactitud, veracidad, validez o legalidad de sitios ajenos a su propiedad a los que se pueda acceder por medio de los enlaces.</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">ACME CO., INC declara haber adoptado todas las medidas necesarias para evitar cualquier da&ntilde;o a los usuarios de su sitio web, que pudieran derivarse de la navegaci&oacute;n por su sitio web. En consecuencia, ACME CO., INC no se hace responsable, en ning&uacute;n caso, de los eventuales da&ntilde;os que por la navegaci&oacute;n por Internet pudiera sufrir el usuario.</span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \">MODIFICACIONES</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">ACME CO., INC se reserva el derecho a realizar las modificaciones que considere oportunas, sin aviso previo, en el contenido de su sitio web. Tanto en lo referente a los contenidos del sitio web, como en las condiciones de uso del mismo, o en las condiciones generales de contrataci&oacute;n. Dichas modificaciones podr&aacute;n realizarse a trav&eacute;s de su sitio web por cualquier forma admisible en derecho y ser&aacute;n de obligado cumplimiento durante el tiempo en que se encuentren publicadas en el web y hasta que no sean modificadas v&aacute;lidamente por otras posteriores.</span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \">SERVICIOS DE CONTRATACI&Oacute;N POR INTERNET</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">Ciertos contenidos de la website de ACME CO., INC contienen la posibilidad de contrataci&oacute;n por Internet. El uso de los mismos requerir&aacute; la lectura y aceptaci&oacute;n obligatoria de las condiciones generales de contrataci&oacute;n establecidas al efecto por ACME CO., INC.</span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \">PROTECCI&Oacute;N DE DATOS</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">De conformidad con lo que establece la Ley Org&aacute;nica 15/1999 de Protecci&oacute;n de Datos de Car&aacute;cter Personal (LOPD), ACME CO., INC informa a los usuarios de su sitio web que los datos personales recabados por la empresa, mediante los formularios sitos en sus p&aacute;ginas, ser&aacute;n introducidos en un fichero automatizado bajo la responsabilidad de ACME CO., INC, con la finalidad de poder facilitar, agilizar y cumplir los compromisos establecidos entre ambas partes.</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">Asimismo, ACME CO., INC informa de la posibilidad de ejercer los derechos de acceso, rectificaci&oacute;n, cancelaci&oacute;n y oposici&oacute;n mediante un escrito a la direcci&oacute;n: Av. Reina Mercedes s/n, 41012, Sevilla, Departamento de Lenguajes y Sistemas Inform&aacute;ticos.</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">Mientras el usuario no comunique lo contrario a ACME CO., INC, &eacute;sta entender&aacute; que sus datos no han sido modificados, que el usuario se compromete a notificar a ACME CO., INC cualquier variaci&oacute;n y que ACME CO., INC tiene el consentimiento para utilizarlos a fin de poder fidelizar la relaci&oacute;n entre las partes.</span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \">PROPIEDAD INTELECTUAL E INDUSTRIAL</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">ACME CO., INC por s&iacute; misma o como cesionaria, es titular de todos los derechos de propiedad intelectual e industrial de su p&aacute;gina web, as&iacute; como de los elementos contenidos en la misma (a t&iacute;tulo enunciativo, im&aacute;genes, sonido, audio, v&iacute;deo, software o textos; marcas o logotipos, combinaciones de colores, estructura y dise&ntilde;o, selecci&oacute;n de materiales usados, programas de ordenador necesarios para su funcionamiento, acceso y uso, etc.), titularidad de ACME CO., INC. Ser&aacute;n, por consiguiente, obras protegidas como propiedad intelectual por el ordenamiento jur&iacute;dico espa&ntilde;ol, si&eacute;ndoles aplicables tanto la normativa espa&ntilde;ola y comunitaria en este campo, como los tratados internacionales relativos a la materia y suscritos por Espa&ntilde;a.</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">Todos los derechos reservados. En virtud de lo dispuesto en los art&iacute;culos 8 y 32.1, p&aacute;rrafo segundo, de la Ley de Propiedad Intelectual, quedan expresamente prohibidas la reproducci&oacute;n, la distribuci&oacute;n y la comunicaci&oacute;n p&uacute;blica, incluida su modalidad de puesta a disposici&oacute;n, de la totalidad o parte de los contenidos de esta p&aacute;gina web, con fines comerciales, en cualquier soporte y por cualquier medio t&eacute;cnico, sin la autorizaci&oacute;n de ACME CO., INC.</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">El usuario se compromete a respetar los derechos de Propiedad Intelectual e Industrial titularidad de ACME CO., INC. Podr&aacute; visualizar los elementos del portal e incluso imprimirlos, copiarlos y almacenarlos en el disco duro de su ordenador o en cualquier otro soporte f&iacute;sico siempre y cuando sea, &uacute;nica y exclusivamente, para su uso personal y privado. El usuario deber&aacute; abstenerse de suprimir, alterar, eludir o manipular cualquier dispositivo de protecci&oacute;n o sistema de seguridad que estuviera instalado en las p&aacute;ginas de ACME CO., INC.</span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \">ACCIONES LEGALES, LEGISLACI&Oacute;N APLICABLE Y JURISDICCI&Oacute;N</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">ACME CO., INC se reserva, asimismo, la facultad de presentar las acciones civiles o penales que considere oportunas por la utilizaci&oacute;n indebida de su sitio web y contenidos, o por el incumplimiento de las presentes condiciones.</span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \">La relaci&oacute;n entre el usuario y el prestador se regir&aacute; por la normativa vigente y de aplicaci&oacute;n en el territorio espa&ntilde;ol. De surgir cualquier controversia las partes podr&aacute;n someter sus conflictos a arbitraje o acudir a la jurisdicci&oacute;n ordinaria cumpliendo con las normas sobre jurisdicci&oacute;n y competencia al respecto. ACME CO., INC tiene su domicilio en SEVILLA, Espa&ntilde;a.</span></p>','es','TERMS'),(413,0,'<p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">In compliance with Law 34/2002, of July 11, on Services of the Information Society and Electronic Commerce (LSSI-CE), ACME CO., INC. Informs that it is the owner of this website. </font><font style=\"vertical-align: inherit;\">In accordance with the requirement of article 10 of the aforementioned Law, ACME CO., INC reports the following information:</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">The owner of this website is ACME CO., INC.</font></font></span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">USER AND REGIME OF RESPONSIBILITIES</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">The navigation, access and use by the website of ACME CO., INC confers the condition of user, by which they accept, from browsing the website of ACME CO., INC, all conditions of use here established without prejudice to the application of the corresponding regulations of mandatory legal compliance as the case may be.</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">The ACME CO., INC. Website provides a wide variety of information, services and data. </font><font style=\"vertical-align: inherit;\">The user assumes his responsibility in the correct use of the website. </font><font style=\"vertical-align: inherit;\">This responsibility will extend to:</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">- The veracity and legality of the information provided by the user in the forms issued by ACME CO., INC for access to certain content or services offered by the web.</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">- The use of the information, services and data offered by ACME CO., INC contrary to the provisions of these conditions, the Law, morals, good customs or public order, or that may otherwise result in injury of the rights of third parties or the operation of the website.</font></font></span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">LINKS POLICY AND LIABILITY EXEMPTIONS</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">ACME CO., INC is not responsible for the content of the websites that the user can access through the links established on its website and declares that in no case will proceed to examine or exercise any type of control over the content from other sites in the network. </font><font style=\"vertical-align: inherit;\">Likewise, it will not guarantee the technical availability, accuracy, veracity, validity or legality of sites external to your property that can be accessed through the links.</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">ACME CO., INC. Declares that it has adopted all the necessary measures to avoid any damage to users of its website, which may arise from browsing its website. </font><font style=\"vertical-align: inherit;\">As a result, ACME CO., INC is not responsible, in any case, for any damages that the user may suffer from browsing the Internet.</font></font></span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">MODIFICATIONS</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">ACME CO., INC reserves the right to make the modifications it deems appropriate, without prior notice, in the content of its website. </font><font style=\"vertical-align: inherit;\">Both with regard to the contents of the website, as in the conditions of use thereof, or in the general conditions of contract. </font><font style=\"vertical-align: inherit;\">Said modifications may be made through its website by any admissible form in law and shall be binding during the time they are published on the website and until they are validly modified by subsequent ones.</font></font></span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">INTERNET CONTRACTING SERVICES</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Certain contents of the ACME CO., INC. Website contain the possibility of contracting by Internet. </font><font style=\"vertical-align: inherit;\">The use of the same will require the reading and obligatory acceptance of the general contracting conditions established for this purpose by ACME CO., INC.</font></font></span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">DATA PROTECTION</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">In accordance with the provisions of the Organic Law 15/1999 on the Protection of Personal Data (LOPD), ACME CO., INC informs the users of its website that the personal data collected by the company, through the forms located in their pages, they will be entered in an automated file under the responsibility of ACME CO., INC, with the purpose of facilitating, expediting and fulfilling the commitments established between both parties.</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Likewise, ACME CO., INC. Informs of the possibility of exercising the rights of access, rectification, cancellation and opposition by writing to the address: Av. Reina Mercedes s / n, 41012, Seville, Department of Languages &#8203;&#8203;and Computer Systems.</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">While the user does not communicate otherwise to ACME CO., INC, it will understand that their data has not been modified, that the user agrees to notify ACME CO., INC any variation and that ACME CO., INC. Has the consent to use them in order to be able to fidelize the relationship between the parties.</font></font></span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">INTELLECTUAL AND INDUSTRIAL PROPERTY</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">ACME CO., INC. By itself or as an assignee, is the owner of all the intellectual and industrial property rights of its website, as well as the elements contained therein (for example, images, sound, audio, video) , software or texts, trademarks or logos, combinations of colors, structure and design, selection of used materials, computer programs necessary for its operation, access and use, etc.), owned by ACME CO., INC. </font><font style=\"vertical-align: inherit;\">They will, therefore, works protected as intellectual property by the Spanish legal system, being applicable both Spanish and European regulations in this field, as well as international treaties relating to the subject and signed by Spain.</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">All rights reserved. </font><font style=\"vertical-align: inherit;\">By virtue of the provisions of articles 8 and 32.1, second paragraph, of the Law on Intellectual Property, the reproduction, distribution and public communication, including the method of making them available, of all or part of the articles is expressly prohibited. contents of this web page, with commercial ends, in any support and by any technical means, without the authorization of ACME CO., INC.</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">The user undertakes to respect the Intellectual and Industrial Property rights owned by ACME CO., INC. </font><font style=\"vertical-align: inherit;\">You can visualize the elements of the portal and even print them, copy them and store them on your computer\'s hard drive or on any other physical medium, as long as it is solely and exclusively for your personal and private use. </font><font style=\"vertical-align: inherit;\">The user must refrain from deleting, altering, evading or manipulating any protection device or security system that was installed on the pages of ACME CO., INC.</font></font></span></p><p style=\"text-align: justify; line-height: normal; background: white; margin: 7.5pt 0cm 7.5pt 0cm;\"><span style=\"font-size: 13.5pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">LEGAL ACTIONS, APPLICABLE LEGISLATION AND JURISDICTION</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">ACME CO., INC. Also reserves the right to file civil or criminal actions it deems appropriate for the improper use of its website and content, or for the breach of these conditions.</font></font></span></p><p style=\"margin-bottom: 7.5pt; text-align: justify; line-height: normal; background: white;\"><span style=\"font-size: 9.0pt; \"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">The relationship between the user and the provider will be governed by the regulations in force and applicable in Spanish territory. </font><font style=\"vertical-align: inherit;\">If any dispute arises, the parties may submit their disputes to arbitration or go to the ordinary jurisdiction, complying with the rules on jurisdiction and competence in this regard. </font><font style=\"vertical-align: inherit;\">ACME CO., INC. Has its address in SEVILLA, Spain.</font></font></span></p> ','en','TERMS'),(414,0,'<p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\">Una Cookie es un fichero que se descarga en su ordenador al acceder a determinadas p&aacute;ginas web. Las cookies permiten a una p&aacute;gina web, entre otras cosas, almacenar y recuperar informaci&oacute;n sobre los h&aacute;bitos de navegaci&oacute;n de un usuario o de su equipo y, dependiendo de la informaci&oacute;n que contenga y de la forma en que utilice su equipo, pueden utilizarse para reconocer al usuario.</span></p><p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\">Las cookies son esenciales para el funcionamiento de internet, aportando innumerables ventajas en la prestaci&oacute;n de servicios interactivos, facilit&aacute;ndole la navegaci&oacute;n y usabilidad de nuestra web.</span></p><p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\">La informaci&oacute;n que le proporcionamos a continuaci&oacute;n, le ayudar&aacute; a comprender los diferentes tipos de cookies:</span></p><table class=\"tablacookies\" border=\"0\"><tbody><tr><td colspan=\"3\"><table border=\"1\"><tbody><tr><td colspan=\"3\"><p><span style=\"font-size: 9pt;\">TIPOS DE COOKIES</span></p></td></tr><tr><td rowspan=\"2\"><p><span style=\"font-size: 9pt;\">SEG&Uacute;N LA ENTIDAD QUE LAS GESTIONE</span></p></td><td><p><span style=\"font-size: 9pt;\">Cookies propias</span></p></td><td><p><span style=\"font-size: 9pt;\">Son aquellas que se recaban por el propio editor para prestar el servicio solicitado por el usuario.</span></p></td></tr><tr><td><p><span style=\"font-size: 9pt;\">Cookies de tercero</span></p></td><td><p><span style=\"font-size: 9pt;\">Son aquellas que son recabadas y gestionadas por un tercero, estas no se pueden considerar propias.</span></p></td></tr><tr><td rowspan=\"2\"><p><span style=\"font-size: 9pt;\">SEG&Uacute;N EL PLAZO DE TIEMPO QUE PERMANEZCAN ACTIVADAS</span></p></td><td><p><span style=\"font-size: 9pt;\">Cookies de sesi&oacute;n</span></p></td><td><p><span style=\"font-size: 9pt;\">Recaban datos mientras el usuario navega por la red con la finalidad de prestar el servicio solicitado.</span></p></td></tr><tr><td><p><span style=\"font-size: 9pt;\">Cookies persistentes</span></p></td><td><p><span style=\"font-size: 9pt;\">Se almacenan en el terminal y la informaci&oacute;n obtenida, ser&aacute; utilizada por el responsable de la cookie con la finalidad de prestar el servicio solicitado.</span></p></td></tr><tr><td rowspan=\"5\"><p><span style=\"font-size: 9pt;\">SEG&Uacute;N SU FINALIDAD</span></p></td><td><p><span style=\"font-size: 9pt;\">Cookies t&eacute;cnicas</span></p></td><td><p><span style=\"font-size: 9pt;\">Son las necesarias para la correcta navegaci&oacute;n por la web.</span></p></td></tr><tr><td><p><span style=\"font-size: 9pt;\">Cookies de personalizaci&oacute;n</span></p></td><td><p><span style=\"font-size: 9pt;\">Permiten al usuario las caracter&iacute;sticas (idioma) para la navegaci&oacute;n por la website</span></p></td></tr><tr><td><p><span style=\"font-size: 9pt;\">Cookies de an&aacute;lisis</span></p></td><td><p><span style=\"font-size: 9pt;\">Permiten al prestador el an&aacute;lisis vinculado a la navegaci&oacute;n realizada por el usuario, con la finalidad de llevar un seguimiento de uso de la p&aacute;gina web, as&iacute; como realizar estad&iacute;sticas de los contenidos m&aacute;s visitados, n&uacute;mero de visitantes, etc.</span></p></td></tr><tr><td><p><span style=\"font-size: 9pt;\">Cookies publicitarias</span></p></td><td><p><span style=\"font-size: 9pt;\">Permiten al editor incluir en la web, espacios publicitarios, seg&uacute;n el contenido de la propia web.</span></p></td></tr><tr><td><p><span style=\"font-size: 9pt;\">Cookies de publicidad comportamental</span></p></td><td><p><span style=\"font-size: 9pt;\">Permiten al editor incluir en la p&aacute;gina web espacios publicitarios seg&uacute;n la informaci&oacute;n obtenida a trav&eacute;s de los h&aacute;bitos de navegaci&oacute;n del usuario.</span></p></td></tr></tbody></table><p>&nbsp;</p></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\">Seg&uacute;n lo dispuesto en el art&iacute;culo 22.2 de la Ley 34/2002, de 11 de julio, de Servicios de la Sociedad de la Informaci&oacute;n y de Comercio Electr&oacute;nico (LSSI-CE), ACME CO., INC informa de las cookies utilizadas en nuestra website:</span></p><p style=\"text-align: justify;\">&nbsp;</p><table class=\"tablacookies\" style=\"width: 588px;\" border=\"1\"><tbody><tr><th style=\"width: 194px;\">&nbsp;</th><th style=\"width: 394px;\" colspan=\"3\"><span style=\"font-size: 9pt;\">Tipos de Cookies</span></th></tr><tr><td style=\"width: 194px;\">&nbsp;</td><td style=\"width: 115px;\"><span style=\"font-size: 9pt;\">Cookies Propias</span></td><td style=\"width: 130px;\"><span style=\"font-size: 9pt;\">Cookies de sesion</span></td><td style=\"width: 149px;\"><span style=\"font-size: 9pt;\">Cookies persistentes</span></td></tr><tr><td style=\"width: 194px;\"><span style=\"font-size: 9pt;\">Cookies tecnicas</span></td><td style=\"width: 115px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\" /></span></td><td style=\"width: 130px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\" /></span></td><td style=\"width: 149px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\" /></span></td></tr><tr><td style=\"width: 194px;\"><span style=\"font-size: 9pt;\">Cookies de personalizacion</span></td><td style=\"width: 115px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\" /></span></td><td style=\"width: 130px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\" /></span></td><td style=\"width: 149px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\" /></span></td></tr><tr><td style=\"width: 194px;\"><span style=\"font-size: 9pt;\">Cookies de analisis</span></td><td style=\"width: 115px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\" /></span></td><td style=\"width: 130px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\" /></span></td><td style=\"width: 149px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\" /></span></td></tr></tbody></table><p style=\"text-align: justify;\">&nbsp;</p><p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\">Asimismo, ACME CO., INC informa al usuario de que tiene la posibilidad de configurar su navegador de modo que se le informe de la recepci&oacute;n de cookies, pudiendo, si as&iacute; lo desea, impedir que sean instaladas en su disco duro.</span></p><p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\">A continuaci&oacute;n le proporcionamos los enlaces de diversos navegadores, a trav&eacute;s de los cuales podr&aacute; realizar dicha configuraci&oacute;n:</span></p><p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\">Firefox desde aqu&iacute;:&nbsp;<a href=\"http://support.mozilla.org/es/kb/habilitar-y-deshabilitar-cookies-que-los-sitios-we\">http://support.mozilla.org/es/kb/habilitar-y-deshabilitar-cookies-que-los-sitios-we</a></span></p><p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\">Chrome desde aqu&iacute;:&nbsp;<a href=\"http://support.google.com/chrome/bin/answer.py?hl=es&amp;answer=95647\" target=\"blank\">http://support.google.com/chrome/bin/answer.py?hl=es&amp;answer=95647</a></span></p><p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\">Explorer desde aqu&iacute;:&nbsp;<a href=\"http://windows.microsoft.com/es-es/internet-explorer/delete-manage-cookies#ie=ie-10\" target=\"blank\">http://windows.microsoft.com/es-es/internet-explorer/delete-manage-cookies#ie=ie-10</a></span></p><p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\">Safari desde aqu&iacute;:&nbsp;<a href=\"https://support.apple.com/kb/ph17191?locale=es_ES\" target=\"blank\">https://support.apple.com/kb/ph17191?locale=es_ES</a></span></p><p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\">Opera desde aqu&iacute;:&nbsp;<a href=\"http://help.opera.com/Windows/11.50/es-ES/cookies.html\" target=\"blank\">http://help.opera.com/Windows/11.50/es-ES/cookies.html</a></span></p>','es','COOKIES'),(415,0,'<p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">A Cookie is a file that is downloaded to your computer when you access certain web pages. </font><font style=\"vertical-align: inherit;\">Cookies allow a web page, among other things, to store and retrieve information about the browsing habits of a user or their equipment and, depending on the information it contains and how they use their equipment, they can be used to recognize to user.</font></font></span></p> <p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Cookies are essential for the functioning of the internet, providing innumerable advantages in the provision of interactive services, facilitating navigation and usability of our website.</font></font></span></p> <p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">The information that we provide below, will help you understand the different types of cookies:</font></font></span></p> <table class=\"tablacookies\" border=\"0\"> <tbody> <tr> <td colspan=\"3\"> <table border=\"1\"> <tbody> <tr> <td colspan=\"3\"> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">TYPES OF COOKIES</font></font></span></p> </td> </tr> <tr> <td rowspan=\"2\"> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">ACCORDING TO THE ENTITY THAT MANAGES THEM</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Own cookies</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Are those that are collected by the editor to provide the service requested by the user.</font></font></span></p> </td> </tr> <tr> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Third party cookies</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Are those that are collected and managed by a third party, these can not be considered own.</font></font></span></p> </td> </tr> <tr> <td rowspan=\"2\"> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">ACCORDING TO THE TIME LIMIT THAT THEY STAY ACTIVATED</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Session cookies</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">They collect data while the user browses the network in order to provide the requested service.</font></font></span></p> </td> </tr> <tr> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Persistent cookies</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">They are stored in the terminal and the information obtained will be used by the person responsible for the cookie in order to provide the requested service.</font></font></span></p> </td> </tr> <tr> <td rowspan=\"5\"> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">ACCORDING TO YOUR PURPOSE</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Technical cookies</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">They are the necessary ones for the correct navigation through the web.</font></font></span></p> </td> </tr> <tr> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Personalization cookies</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">They allow the user the characteristics (language) for browsing the website</font></font></span></p> </td> </tr> <tr> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Analysis cookies</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">They allow the provider the analysis linked to the navigation carried out by the user, in order to track the use of the website, as well as to make statistics of the most visited contents, number of visitors, etc.</font></font></span></p> </td> </tr> <tr> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Advertising cookies</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">They allow the editor to include advertising space on the web, according to the content of the web itself.</font></font></span></p> </td> </tr> <tr> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Behavioral advertising cookies</font></font></span></p> </td> <td> <p><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">They allow the editor to include advertising space on the web page according to the information obtained through the user\'s browsing habits.</font></font></span></p> </td> </tr> </tbody> </table> <p>&nbsp;</p> </td> </tr> <tr> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> </tr> </tbody> </table> <p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">According to the provisions of article 22.2 of Law 34/2002, of July 11, Services of the Information Society and Electronic Commerce (LSSI-CE), ACME CO., INC. Informs about the cookies used in our website:</font></font></span></p> <p style=\"text-align: justify;\">&nbsp;</p> <table class=\"tablacookies\" style=\"width: 588px;\" border=\"1\"> <tbody> <tr> <th style=\"width: 194px;\">&nbsp;</th> <th style=\"width: 394px;\" colspan=\"3\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Types of Cookies</font></font></span></th> </tr> <tr> <td style=\"width: 194px;\">&nbsp;</td> <td style=\"width: 115px;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Own Cookies</font></font></span></td> <td style=\"width: 130px;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Session cookies</font></font></span></td> <td style=\"width: 149px;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Persistent cookies</font></font></span></td> </tr> <tr> <td style=\"width: 194px;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Technical cookies</font></font></span></td> <td style=\"width: 115px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\"></span></td> <td style=\"width: 130px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\"></span></td> <td style=\"width: 149px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\"></span></td> </tr> <tr> <td style=\"width: 194px;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Personalization cookies</font></font></span></td> <td style=\"width: 115px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\"></span></td> <td style=\"width: 130px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\"></span></td> <td style=\"width: 149px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\"></span></td> </tr> <tr> <td style=\"width: 194px;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Analysis cookies</font></font></span></td> <td style=\"width: 115px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\"></span></td> <td style=\"width: 130px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\"></span></td> <td style=\"width: 149px;\"><span style=\"font-size: 9pt;\"><input checked=\"checked\" disabled=\"disabled\" type=\"checkbox\"></span></td> </tr> </tbody> </table> <p style=\"text-align: justify;\">&nbsp;</p> <p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">In addition, ACME CO., INC informs the user that he has the possibility to configure his browser so that he is informed of the reception of cookies, and may, if he wishes, prevent them from being installed on his hard drive.</font></font></span></p> <p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Below we provide the links of various browsers, through which you can make such configuration:</font></font></span></p> <p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Firefox from here:&nbsp; </font></font><a href=\"http://support.mozilla.org/es/kb/habilitar-y-deshabilitar-cookies-que-los-sitios-we\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">http://support.mozilla.org/es/kb/habilitar-y-deshabilitar-cookies-que-los-sitios-we</font></font></a></span></p> <p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Chrome from here:&nbsp; </font></font><a href=\"http://support.google.com/chrome/bin/answer.py?hl=es&amp;answer=95647\" target=\"blank\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">http://support.google.com/chrome/bin/answer.py?hl=en&amp;answer=95647</font></font></a></span></p> <p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Explorer from here:&nbsp; </font></font><a href=\"http://windows.microsoft.com/es-es/internet-explorer/delete-manage-cookies#ie=ie-10\" target=\"blank\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">http://windows.microsoft.com/es-es/internet-explorer/delete-manage-cookies#ie=ie-10</font></font></a></span></p> <p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Safari from here:&nbsp; </font></font><a href=\"https://support.apple.com/kb/ph17191?locale=es_ES\" target=\"blank\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">https://support.apple.com/kb/ph17191?locale=en_US</font></font></a></span></p> <p style=\"text-align: justify;\"><span style=\"font-size: 9pt;\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">Opera from here:&nbsp; </font></font><a href=\"http://help.opera.com/Windows/11.50/es-ES/cookies.html\" target=\"blank\"><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">http://help.opera.com/Windows/11.50/es-ES/cookies.html</font></font></a></span></p>','en','COOKIES');
/*!40000 ALTER TABLE `legaltext` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `spam` bit(1) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `folder_id` int(11) NOT NULL,
  `recipient_id` int(11) NOT NULL,
  `sender_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7t1ls63lqb52igs4ms20cf94t` (`folder_id`),
  CONSTRAINT `FK_7t1ls63lqb52igs4ms20cf94t` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newspaper`
--

DROP TABLE IF EXISTS `newspaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newspaper` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pictureUrl` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `publicNp` bit(1) DEFAULT NULL,
  `publicationDate` date DEFAULT NULL,
  `published` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_roqet0j631jcvfu4jhn5klkt5` (`published`),
  KEY `UK_j50778tfsp55ay40c6o3fbp5i` (`title`,`description`),
  KEY `UK_6w4ywp7unfjqi2kflvm35ie1g` (`publisher_id`),
  CONSTRAINT `FK_6w4ywp7unfjqi2kflvm35ie1g` FOREIGN KEY (`publisher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newspaper`
--

LOCK TABLES `newspaper` WRITE;
/*!40000 ALTER TABLE `newspaper` DISABLE KEYS */;
INSERT INTO `newspaper` VALUES (362,0,'Este viagra es un periodico en que se escribiran noticias de todo el globo','https://e00-elmundo.uecdn.es/assets/desktop/master/img/redes-sociales/logoelmundo_rrss.png',0,'',NULL,'\0','El Mundo',354),(365,0,'Periodico especializado en las noticias nacionales','https://ep01.epimg.net/especiales/2017/represion-mapuches-argentina/img/logo_negro.png',0,'','2016-05-04','','El Pais',355),(367,0,'Mantente al dia sobre las noticias de las cryptodivisas','https://fm.cnbc.com/applications/cnbc.com/resources/img/editorial/2018/04/05/105111727-5ED5-BL-Bitcoin-040518.1910x1000.jpg',20,'\0',NULL,'\0','Cryto Noticias',354),(370,0,'Enterate de todos los salseos de tus youtubers favoritos','https://storage.googleapis.com/gweb-uniblog-publish-prod/images/YouTube.max-2800x2800.png',45,'\0','2016-03-08','','Youtube Today',357),(373,0,'Periodico con noticias de Sevilla','https://casadelpoeta.es/wp-content/uploads/2017/05/sevilla-con-encanto-1.jpg',0,'','2016-03-08','','Sevilla al dia',357),(376,0,'Periodico con noticias del sector de las finanzas','https://dosmagazine.com/es/wp-content/uploads/2013/02/Finance-Money-DM.jpg',100,'\0',NULL,'\0','Periodico financiero',356),(379,0,'Periodico que recoge las noticias de Moron de la Frontera','http://sevilla.abc.es/media/sevilla/2018/01/27/s/moron-kwVC--620x349@abc.jpg',0,'','2016-09-09','','Moron Time',356),(382,0,'Periodico que recoge las noticias de futbol','http://diario16.com/wp-content/uploads/2017/05/f%C3%BAtbol.jpg',5,'\0','2016-08-09','','Noticias de futbol',354),(385,0,'Encuentra aqui todo lo que necesitas saber del mundo del baloncesto','http://www.adpradomarianistas.com/wp-content/uploads/2016/04/marianistas-baloncesto.jpg',4,'\0','2016-08-09','','Noticias de basket',354),(388,0,'Periodico que recoge las noticias de Marchena','https://andaluciarustica.com/wp-content/uploads/2013/08/marchena.jpg',3,'\0','2016-09-09','','Marchena Today',356);
/*!40000 ALTER TABLE `newspaper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spamword`
--

DROP TABLE IF EXISTS `spamword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spamword` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `word` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spamword`
--

LOCK TABLES `spamword` WRITE;
/*!40000 ALTER TABLE `spamword` DISABLE KEYS */;
INSERT INTO `spamword` VALUES (391,0,'sex'),(392,0,'sexo'),(393,0,'cialis'),(394,0,'viagra');
/*!40000 ALTER TABLE `spamword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suscription`
--

DROP TABLE IF EXISTS `suscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suscription` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvvCode` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `newspaper_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_ifc8pqjis3at73ksdxlnlvxla` (`newspaper_id`),
  KEY `UK_onu3b44a8sho0f77p8we6notp` (`customer_id`),
  CONSTRAINT `FK_ifc8pqjis3at73ksdxlnlvxla` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`),
  CONSTRAINT `FK_onu3b44a8sho0f77p8we6notp` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suscription`
--

LOCK TABLES `suscription` WRITE;
/*!40000 ALTER TABLE `suscription` DISABLE KEYS */;
INSERT INTO `suscription` VALUES (410,0,'Visa',122,1,2019,'ALEXANDRA CHESTERTON','4716009788550133',359,367),(411,0,'Mastercard',226,12,2018,'ALYSSA SMITH','5172778915952743',360,370);
/*!40000 ALTER TABLE `suscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suscriptionvolumen`
--

DROP TABLE IF EXISTS `suscriptionvolumen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suscriptionvolumen` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvvCode` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `volumen_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_id6ivvgahpvjb6jgvmujobr60` (`customer_id`),
  KEY `FK_94vu9uuicruwps6mdvtlrfimt` (`volumen_id`),
  CONSTRAINT `FK_94vu9uuicruwps6mdvtlrfimt` FOREIGN KEY (`volumen_id`) REFERENCES `volumen` (`id`),
  CONSTRAINT `FK_id6ivvgahpvjb6jgvmujobr60` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suscriptionvolumen`
--

LOCK TABLES `suscriptionvolumen` WRITE;
/*!40000 ALTER TABLE `suscriptionvolumen` DISABLE KEYS */;
INSERT INTO `suscriptionvolumen` VALUES (470,0,'Visa',122,1,2019,'ALEXANDRA CHESTERTON','4716009788550133',359,416),(471,0,'Visa',122,1,2019,'ALEXANDRA CHESTERTON','4716009788550133',359,417),(472,0,'Mastercard',226,12,2018,'ALYSSA SMITH','5172778915952743',360,417);
/*!40000 ALTER TABLE `suscriptionvolumen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `confirmMoment` datetime DEFAULT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `hasConfirmedTerms` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (354,0,'2017-08-02 00:00:00','julitomanbo@gmail.com','','Julio','608512432','Carretera Carmona 24','Mambo',347),(355,0,'2017-08-02 00:00:00','peterparker@gmail.com','','Peter','+458212749','La Calle Ancha 56','Parker',348),(356,0,'2017-08-02 00:00:00','antoniogonzalez@gmail.com','','Antonio','+458212749','Pozo Nuevo 12','Gonzalez',349),(357,0,'2017-08-02 00:00:00','jeanluc@gmail.com','','Jean','+32853435','Avenida Kansas City','Luc',350);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_newspaper`
--

DROP TABLE IF EXISTS `user_newspaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_newspaper` (
  `User_id` int(11) NOT NULL,
  `newspapers_id` int(11) NOT NULL,
  UNIQUE KEY `UK_oqhrpkgl440xehybmm713ru36` (`newspapers_id`),
  KEY `FK_24xv3fsqc505dhy1bv4ldnor9` (`User_id`),
  CONSTRAINT `FK_24xv3fsqc505dhy1bv4ldnor9` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_oqhrpkgl440xehybmm713ru36` FOREIGN KEY (`newspapers_id`) REFERENCES `newspaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_newspaper`
--

LOCK TABLES `user_newspaper` WRITE;
/*!40000 ALTER TABLE `user_newspaper` DISABLE KEYS */;
INSERT INTO `user_newspaper` VALUES (354,362),(354,367),(355,365),(356,376),(356,379),(357,370),(357,373);
/*!40000 ALTER TABLE `user_newspaper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user`
--

DROP TABLE IF EXISTS `user_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_user` (
  `User_id` int(11) NOT NULL,
  `following_id` int(11) NOT NULL,
  KEY `FK_c1h4hh6d78lf7t6jkqn3yoi4l` (`following_id`),
  KEY `FK_nlnx78x3m38aq2r86t1d5eio1` (`User_id`),
  CONSTRAINT `FK_nlnx78x3m38aq2r86t1d5eio1` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_c1h4hh6d78lf7t6jkqn3yoi4l` FOREIGN KEY (`following_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user`
--

LOCK TABLES `user_user` WRITE;
/*!40000 ALTER TABLE `user_user` DISABLE KEYS */;
INSERT INTO `user_user` VALUES (354,354),(354,355),(354,356),(354,357),(355,354),(355,355),(355,357),(356,354),(356,356),(356,355),(356,357),(357,354),(357,357);
/*!40000 ALTER TABLE `user_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (345,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(346,0,'91ec1f9324753048c0096d036a694f86','customer'),(347,0,'24c9e15e52afc47c225b757e7bee1f9d','user1'),(348,0,'7e58d63b60197ceb55a1c487989a3720','user2'),(349,0,'92877af70a45fd6a2ed7fe81e1236b78','user3'),(350,0,'3f02ebe3d7929b091e3d8ccfde2f3bc6','user4'),(351,0,'ffbc4675f864e0e9aab8bdf7a0437010','customer1'),(352,0,'5ce4d191fd14ac85a1469fb8c29b7a7b','customer2'),(353,0,'83a87fd756ab57199c0bb6d5e11168cb','agent1');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (345,'ADMIN'),(346,'CUSTOMER'),(347,'USER'),(348,'USER'),(349,'USER'),(350,'USER'),(351,'CUSTOMER'),(352,'CUSTOMER'),(353,'AGENT');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volumen`
--

DROP TABLE IF EXISTS `volumen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volumen` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ebk605h7e2cqykdwu4tlgkjts` (`user_id`),
  CONSTRAINT `FK_ebk605h7e2cqykdwu4tlgkjts` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volumen`
--

LOCK TABLES `volumen` WRITE;
/*!40000 ALTER TABLE `volumen` DISABLE KEYS */;
INSERT INTO `volumen` VALUES (416,0,'Coleccion de periodicos sobre deportes',40,'Deportes',2018,354),(417,0,'Periodicos con noticias ocurridas en la Sierra Sur',30,'Sierra Sur',2018,356);
/*!40000 ALTER TABLE `volumen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volumen_newspaper`
--

DROP TABLE IF EXISTS `volumen_newspaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volumen_newspaper` (
  `Volumen_id` int(11) NOT NULL,
  `newspapers_id` int(11) NOT NULL,
  KEY `FK_lces84a2bc27bgrqxxkfr5161` (`newspapers_id`),
  KEY `FK_snbn2xi7vji5umcvclwinass1` (`Volumen_id`),
  CONSTRAINT `FK_snbn2xi7vji5umcvclwinass1` FOREIGN KEY (`Volumen_id`) REFERENCES `volumen` (`id`),
  CONSTRAINT `FK_lces84a2bc27bgrqxxkfr5161` FOREIGN KEY (`newspapers_id`) REFERENCES `newspaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volumen_newspaper`
--

LOCK TABLES `volumen_newspaper` WRITE;
/*!40000 ALTER TABLE `volumen_newspaper` DISABLE KEYS */;
INSERT INTO `volumen_newspaper` VALUES (416,382),(416,385),(417,379),(417,388);
/*!40000 ALTER TABLE `volumen_newspaper` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-03 15:26:34
