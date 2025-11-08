AWS Certified Cloud Practitioner (CLF-C02) ->  https://aws.amazon.com/certification/certified-cloud-practitioner/

Glosario ->   https://docs.aws.amazon.com/es_es/glossary/latest/reference/glos-chap.html

Overview of Amazon Web Services -> https://d0.awsstatic.com/whitepapers/aws-overview.pdf

	Fundamentos de la nube de AWS para profesionales (Español de España) | AWS Cloud Practitioner Essentials (Spanish from Spain)
	
	What is cloud computing?
	
Cloud computing is the on-demand delivery of IT resources over the Internet with pay-as-you-go pricing. Instead of buying, owning, and maintaining physical data centers and servers, you can access technology services, such as computing power, storage, and databases, on an as-needed basis from a cloud provider like Amazon Web Services (AWS).

	Benefits of cloud computing
	--Agility
	--Elasticity
	--Cost savings
	--Deploy globally in minutes
	
	Types of cloud computing

The three main types of cloud computing include Infrastructure as a Service, Platform as a Service, and Software as a Service. Each type of cloud computing provides different levels of control, flexibility, and management so that you can select the right set of services for your needs.

	Infrastructure as a Service (IaaS): contains the basic building blocks for cloud IT. It typically provides access to networking features, computers (virtual or on dedicated hardware), and data storage space. IaaS gives you the highest level of flexibility and management control over your IT resources. It is most similar to the existing IT resources with which many IT departments and developers are familiar. 

	Platform as a Service (PaaS): removes the need for you to manage underlying infrastructure (usually hardware and operating systems), and allows you to focus on the deployment and management of your applications. This helps you be more efficient as you don’t need to worry about resource procurement, capacity planning, software maintenance, patching, or any of the other undifferentiated heavy lifting involved in running your application.
	
	Software as a Service (SaaS): provides you with a complete product that is run and managed by the service provider. In most cases, people referring to SaaS are referring to end-user applications (such as web-based email). With a SaaS offering, you don’t have to think about how the service is maintained or how the underlying infrastructure is managed. You only need to think about how you will use that particular software.
	
	Infraestructura como código (IaaC)

Serverless
	"Serverless" es un nuevo paradigma en el que los desarrolladores ya no tienen que gestionar servidores,
	El término "sin servidor" significa que el código se ejecuta en servidores, pero no es necesario aprovisionar ni administrar dichos servidores. 
		
Como interactuar con AWS
	1. La consola de administración de AWS es una interfaz basada en web para acceder a los servicios de AWS y administrarlos.
	2. AWS CLI -> AWS Command Line Interface. AWS CLI te permite controlar varios servicios de AWS directamente desde la línea de comandos en una herramienta.
	3. AWS SDK -> kits de desarrollo de software. Los SDK facilitan el uso de los servicios de AWS a través de una API diseñada para tu lenguaje de programación o tu plataforma.

Amazon EC2 -> Amazon Elastic Compute Cloud = IaaS     https://aws.amazon.com/ec2/faqs/
	Is a web service that provides resizable compute capacity in the cloud. It is designed to make web-scale computing easier for developers.
	Con una instancia de Amazon EC2 puedes utilizar un servidor virtual para ejecutar aplicaciones en la nube de AWS.
	
	Consiste en:
		-Alquilar máquinas virtuales
		-Almacenar datos en unidades virtuales EBS y EFS
		-Datribuir la carga entre máquinas
		-Escalar los servicios mediante Auto Scaling Group (ASG)
		
	Al crear EC2 se necesita crear par de clave privada. RSA por ejemplo

	Instance types   https://aws.amazon.com/ec2/instance-types/
		On Demand ->
		--General Purpose instances -> Instancias de propósito general
		--Compute Optimized instances -> Instancias de computación optimizada
		--Memory optimized instances -> Instancias optimizadas de memoria
		--Accelerated computing instances -> Instancias de computación acelerada
		--Storage optimized instances -> Instancias optimizadas para el almacenamiento
		--High performance computing (HPC) Optimized instances
		--Burstable Instances
		--Flex instances
		--High Memory instances
		--Previous Generation instances
		
	Opciones de Compras de instancias
		On-demand -> bajo demanda, pago por lo que usa
			Costo más elevado, pero no se paga por elevado
		Reserve -> Reservadas, 72% de descuento, reservada de atributos específicos 
			Períodos de reserva -> 1 año + o 3 años ++
			Opciones de pago -> Sin pago inicial +, pago inicial parcial ++, pago total +++
			Recomendado para app de uso constante (ejemplo BD)
			Reservada convertible -> cambiar tipo de instancia, familia instancia, SO, etc. 66% de descuento
		Saving Plans -> Planes de ahorro
			Compute Savings Plans provide the most flexibility and help to reduce your costs by up to 66%.
			EC2 Instance Savings Plans provides the lowest prices, offering savings up to 72%
		Spot Instances
			descuento hasta 90% comparado con on-demand
			Instancias más rentables
			Util para las cargas de trabajo que son resistentes a los fallos
			No es adecuado para trabajos críticos o base de datos
		Dedicated Host -> Hots dedicados
			Servidos físico totalmente dedicado a su uso
			Opción más cara
			Para empresas que tienen fuertes necesidades de regulación o cumplimiento
		Capacity Reservations -> Reservas de capacidad
			Se reserva la capacidad de las instancias bajo demanda
			Sin compromiso de tiempo
		Reserved Instance Marketplace
		
Amazon EBS -> Almacenes de instancias y Amazon Elastic Block Store

	Almacenes de instancias (Temporal)
		Los volúmenes de almacenamiento en el nivel de bloque se comportan como discos duros físicos, proporciona almacenamiento temporal en el nivel de bloque para una instancia de Amazon EC2. Son unidades de red con un rendimiento bueno pero "limitado" pierden su almacenamiento si se detienen
		
	Amazon Elastic Block Store (Amazon EBS)
		Es un servicio que proporciona volúmenes de almacenamiento en el nivel de bloque que puedes usar con instancias de Amazon EC2.
		Un volumen EBS, Es una unidad de red que puede adjuntar instancias mientras se ejecutan
		Permite que las instancias persistan los datos, incluso después de su finalización
		Un volumen de Amazon EBS almacena datos en una única zona de disponibilidad. Para trasladar un volumen primero hay que hacer un snapshot del mismo
		Poseen atributo "Delete on Termination" -> "Borrar al terminar" por default habilitado para que se borre.		
	EBS Multi-Attach.
		Los volúmenes EBS no pueden adjuntarse a varias instancias, esto no es cierto para los tipos de volumen io1 e io2: esto se llama la función EBS Multi-Attach.
		
	Snapshot / Instantáneas de EBS
		Copia de seguridad (snapshot) de volumen EBS en un momento dado
		-Archivo de Snapshots de EBS
			Mover un snapshot a un "nivel de archivo" que es más barato
			La restauración del archivo tarda entre 24 y 72 horas
		-Papelera de reciclaje para Snapshots EBS
			Configura reglas para retener los snaphost eliminados para poder recuperarlos después de un borrado accidental
			Se puede especificar la retención por tiempo, de 1 día a 1 año
			
Amazon Machine Image -> AMI
	Las AMI son una personalización de una instancia EC2
		-Se añade tu propio software, configufación, sistema operativo, monitorización, etc
		-Tiempo de arranque/configuración más rápido porque todo el software está preempaquetado
		
	Las AMI se contribuyente para un región en específico y pueden copiarse entre regiones
	Se puede lanzar instancias EC2 desde:
		-Una AMI pública, proporcionada por AWS
		-Tu propia AMI: la creas y la mantienees tú mismo
		-Una AMI de AWS Marketplace: Una AMI de AWS Marketplace: Una AMi hecha por otro persona (y potencialmente vendida)
		
	Proceso crear una AMI
		-Iniciar una instancia EC2 y personalizarla
		-Detener la instancia, para integridad de datos
		-Construir una AMI - esto también crearé instantáneas de EBS
		-Lanzar instancias desde otras AMIs
		
	
EC2 Image Builder
	Se utiliza para automatizar la creación de máquinas virtuales o imágenes de contenedores
	Automatizar la creación, mantener, validar y probar las AMIs de EC2
	
		
EC2 Instance Store
	Si se necesita un disco de hardware de alto rendimiento, se utiliza EC2 Instance Store
	-Mejor rendimiento de E/S
	-Los almacenes de instancias EC2 pierden su almacenamiento si se detienen (son efímeros)
	-Bueno para el buffer / cache / datos de memoria virtual / contenido temporal
	-Riesgo de pérdida de datos de el hardware falla
	-Las copias de seguridad y la replicación son responsabilidad de uno
					
Amazon EFS -> Amazon Elastic File System
	Es un sistema de archivos escalable que se usa con los servicios en la nube de AWS y los recursos en las instalaciones. 
	
	Amazon EFS es un servicio regional. Almacena datos transversalmente en varias zonas de disponibilidad. 
	
	EBS vrs EFS
		Con EBS se puede utilizar en otra zona de disponibilidad creando una EBS Snapshot y luego restaurarla y con EFS se puede usar en varias zonas de disponibilidad a la vez, utilizando EFS Mount Target
		
	EFS Infrecuent Access (EFS-IA)
		-Clase de almacenamiento con costes optimizados para los archivos a los que no se accede a diario
		-Hasta un 92% menos de coste en comparación con EFS Standard
		-EFS moverá automáticamente tus archivos a EFS-IA basándose en la última vez que se accedió a ellos
		
3 tipos de almacenamientos
	-EBS
	-Almacenamiento de instancias EC2
	-EFS
	
Amazon FSx
	Lanzar sistemas de archivos de alto rendimiento de terceros en AWS
	Servicio totalmente gestionado
	
	-Amazon FSx para Windows File Server
		Un sistema de archivos compartido nativo de Windows totalmente gestionado, altamente fiable y escalable
		Contruido sobre Windows File Server
	-Amazon FSx Lustre
		Un almacenamiento de archivos totalmente gestionado, de alto rendimiento y escalable para High Performance Computing (HPC)
		Nombre de Lustre se deriva de Linux y Cluster
			
Escalabilidad, Elasticidad y alta disponibilidad
	Escalabilidad significa que una aplicación/sistema puede manejar mayores cargar adaptándose.
	Dos tipos  vertical (aumentar tamaño de instancia) scale up y horizontal (elasticidad) aumentar número de instancias scale out
	
	Escalado horizontal
		Auto Scaling Group
		Load Balancer
		
	Alta disponibilidad
		Va de la mano de escabilidad horizontal, sistemas en al menos 2 zonas de disponibilidad
		Auto Scaling Group multi AZ
		Load Balancer multi AZ
		
	Elasticidad
		"Autoescalado" en función de la carga. Esto es amigable con el Cloud: Pago por uso, adecuación a la demanda, optimización de costes
		
	Agilidad
		Poder tener los nuevos recursos de IT a un clic de distancia	
		
Amazon ELB -> Elastic Load Balancing (Equilibrador de carga): balanceador de carga que direcciona tráfico  https://aws.amazon.com/es/elasticloadbalancing/
	es el servicio de AWS que distribuye automáticamente el tráfico entrante de la aplicación entre varios recursos como, por ejemplo, instancias de Amazon EC2. 
	
	Load Balancers  son servidores que reenvían el tráfico de Internet a múltiples servidores (Instancias EC2) en sentido descendente
		Distribuye carga entre múltiples instancias descendentes
		Exponer un único punto de acceso DNS
		Manejar sin problemas los fallos de las instancias descendentes
		Proporcionar terminación SSL (HTTPS) para tus sitios web
		Alta disponibilidad entre zonas
	
	Contribuye a agregar una arquitectura desacoplada
	
Arquitectura de acoplamiento fuerte
	Si un componente falla, falla todo
Arquitectura de acoplamiento debil
	Si un componente falla, se desacopla y sistema sigue funcionando sin problema
	
	4 tipos ELB:
	Classic (Antiguo)
	
	Application Load Balancer
		Protocolos HTTP / HTTPS / gRPC (Capa 7)
		Funciones de enrutamiento HTTP
		DNS estático (URL)
		
	Network Load Balancer
		Protocolos TCP / UDP
		Alto rendimiento
		IP estática a través de IP elástica
		
	Gateway Load Balancer
		Protocolo GENEVE en paquetes IP (Capa 3)
		Enrutar el tráfico a los firewalls
		Detección de intrusos
		
	ASG -> Auto Scaling Group, en función de demanda y se integra con ELB  https://docs.aws.amazon.com/autoscaling/ec2/userguide/auto-scaling-groups.html
		Objetivo
			-Reducir (añadir instancias de EC2) para adaptarse a un aumento de la carga
			-Aumentar (eliminar instancias de EC2) para coincida con una disminución de la carga
			-Asegurar que tenemos un número mínimo y máximo de máquinas en funcionamiento
			-Registrar nuevas instancias en un Load Balancer
			-Reemplazar las instancias en mal estado
		Ahorro de Costes
		Estrategias de escalado
			Escalado manual
			Escalado dinámico
		Escalado de seguimiento de objetivos
		Escalado programado
		Escalado predictivo: Utiliza machine Learning para predecir trafico
			
	
	
Amazon SQS -> Amazon Simple Queue Service (SQS)   https://aws.amazon.com/sqs/
	SQS es un servicio de cola de mensajes. Permite enviar, almacenar y recibir mensajes entre componentes de software a cualquier volumen.
	Escala desde 1 mensaje por segundo hasta 10000 por segundo
	No hay límite en el número de mensajes que puede haber en la cola
	Los mensajes se eliminan después de ser leidos
	Tipos de colas
		Standard
		FIFO

Amazon SNS -> Amazon Simple Notification Service (SNS)		https://aws.amazon.com/sns/
	Es un servicio de mensajería pub/sub de alta disponibilidad, duradero, seguro y totalmente gestionado que permite desaclopar servicios, sistemas distribuidos y aplicaciiones sin servidor. Utiliza un sistema basado en push.
	Es similar a Amazon SQS porque se utiliza para enviar mensajes a los servicios, pero también puede enviar notificaciones a usuarios finales.
		
Amanzon Kinesis		https://aws.amazon.com/kinesis/
	Streaming de big data en tiempo real
	Servicio gestionado para recopilar, procesar y analizar datos de streaming en tiempo real a cualquier  escala
	Kinesis Data Streams: streaming de baja latencia para ingerir datos  a escala desde cientos de miles de fuentes
	Kinesis Data FireHouse: carga flujos en S3, Redshift, ElasticSearch, etc.
	Kinesis Data Analystics: realiza análisis en tiempo real de los flujos mediante SQL
	Kinesis Data Streams: supervisa los flujos de vídeo en tiempo real para la analítica o el ML
	
Amazon MQ
	Servicio gestionado de intermediación/broker de mensajes para RabbitMQ, ActiveMQ
	Tiene la función tanto de SQS como la de tema de SNS

Docker		https://aws.amazon.com/docker/
	Se puede definir como una plataforma de desarrollo de software para desplegar aplicaciones.
	Las aplicaciones con Docker se empaquetan en lo que se llaman contenedores de software que pueden ejecutarse.
	Funciona en cualquier lenguaje, sistema operativo o tecnología
	Docker se puede entender como una especie de tecnología de virtualización, pero no exactamente.

Amazon ECS -> Amazon Elastic Container Service	https://aws.amazon.com/ecs/
	Es un sistema de administración de contenedores altamente escalable y de alto rendimiento que permite ejecutar y escalar aplicaciones en contenedores en AWS. Amazon ECS admite y permite lanzar contenedores de Docker.
	AWS se encarga de parar o iniciar los contenedores
	Tiene integraciones con el Application Load Balancer
	
	
Amazon EKS -> Amazon Elastic Kubernetes Service		https://aws.amazon.com/eks/
	Es un servicio completamente administrado que se puede usar para ejecutar Kubernetes en AWS. 
	Start, run, and scale Kubernetes without thinking about cluster management
		--Accelerate time to production
		--Run Kubernetes in any environment
		--Improve performance, availability, and resiliency
		--Enhance security of your systems
	
AWS Fargate		https://aws.amazon.com/fargate/
	Es un motor de computación sin servidor para contenedores. Funciona tanto con Amazon ECS como con Amazon EKS. 
	¿Pero cuál es la diferencia?
		Con Fargate, tú no aprovisionas la infraestructura, no hay instancias EC2 que gestionar. Es mucho, mucho más sencillo. Es una oferta sin servidor.
		Aquí es donde vas a almacenar tus imágenes de Docker para que luego puedan ser ejecutadas por ECS o Fargate.
		
Amazon ECR -> Amazon Elastic Container Registry		https://aws.amazon.com/ecr/
	Easily store, share, and deploy your container software anywhere.
	Es un registro privado de Docker en AWS. ¿Por qué? Existe un registro público donde podemos encontrar imágenes de Docker, imágenes como Node, Java, Reddit y muchas otras, que es Docker Hub. No obstante, ese es público.	

	Con Fargate, AWS simplemente ejecutará los contenedores por ti en función de la CPU y RAM que necesites.
	
AWS Lambda	https://aws.amazon.com/lambda/
	Es un servicio que permite ejecutar código sin necesidad de aprovisionar ni administrar servidores. 
	Se paga por las solicitudes que se realizan de ejecución y por el tiempo de computación.
	Integrado con todo el conjunto de servicios de AWS
	Fácil monitorización con AWS CloudWatch
	se paga por llamadas, los primeros. El primer millón de solicitudes es totalmente gratuito. A partir de ese momento vas a pagar por las llamadas que hagas y cero punto 0,20 $ por cada millón de solicitudes. O también lo que sería igual a 3.200.000 segundos si la función es de 128 megabytes de RAM y después un dólar por 600.000 por segundo, no son precios exageradamente altos.
	También se paga por duración
		--Increase developer agility
		--Boost application performance
	
AWS API Gateway		https://aws.amazon.com/api-gateway/
	con API Gateway crear APIS que nos permite hacer llamadas al servicio de Amazon Web Services AWS Lambda
	Es un servicio totalmente gestionado para que los desarrolladores puedan crear, publicar, mantener, supervisar y asegurar fácilmente las APIs de servicios, y también es escalable
	
AWS Batch	https://aws.amazon.com/batch/
	El servicio que nos permite realizar procesamiento por lotes, es un servicio totalmente gestionado en cualquier escala con AWS Batch. 
	Podemos ejecutar eficientemente 100.000 trabajos de computación por lotes en AWS. 
	Un trabajo por lotes, digámoslo así, es un trabajo con un inicio y un final, en contraposición a uno continuo. Batch. 
	Lo que hará será lanzar dinámicamente instancias EC22 o instancias spot a AWS Batch proporciona.
	
AWS Batch vrs Lambda
	Lambda
		-Límite de tiempo
		-Tiempos de ejecución limitado
		-Espacio de disco temporal limitado
		-Serverless
	Batch
		-Sin límite de tiempo
		-Depende de EBS / almacén de instancias para el espacio en disco
		-Depende de EC2 (puede ser gestionado por AWS)
		
Amazon Lightsail
	Nos puede servir para servidores virtuales, almacenamiento, bases de datos y redes, entre otras cosas.
	Para personas con poca experiencia en cloud.
		--Create a website fast
		--Scale easily
		--Security and reliability
	
AWS Amplify 	https://docs.aws.amazon.com/amplify/
	Use AWS Amplify to develop and deploy cloud-powered mobile and web applications. Amplify provides frontend libraries, UI components, and backend building for fullstack applications on AWS. Amplify Hosting provides a continuous delivery and hosting service for fullstack cloud applications.
	
	https://main.dld2wjdmztsz3.amplifyapp.com/
	
Regions
	--AWS has the concept of a Region, which is a physical location around the world where we cluster data centers. We call each group of logical data centers an Availability Zone. Each AWS Region consists of a minimum of three, isolated, and physically separate AZs within a geographic area.
	--Cada región posee varias AZ separadas y fisícamente aisladas dentro de una región geográfica
	--Todos los servicios de AWS poseen alta disponibilidad
	
Availability Zones AZ
	Availability Zone (AZ) is one or more discrete data centers with redundant power, networking, and connectivity in an AWS Region. 
	AWS crea zonas de disponibilidad AZ para tener alta disponibilidad
	Se debe de elegir zona según 4 puntos:
		1. Conformidad con los requisitos y gobernanza de datos, datos deben de estar alojados en ubicación específica?
		2. Próximidad a los clientes, lo más cercano posible
		3. Servicios - Características disponibles en cada región
		4. Precios, según región puede ser más caro un servicio que en otra región

Content Delivery Network CDN
	--A content delivery network (CDN) is a network of interconnected servers that speeds up webpage loading for data-heavy applications
	--Una red de entrega de contenido (CDN) es una red de servidores interconectados que acelera la carga de las páginas web para las aplicaciones que tienen un uso intensivo de datos. CDN puede significar red de entrega de contenido o red de distribución de contenido.	
	--Ubicaciones periféricas
	--Why is a CDN important? The primary purpose of a content delivery network (CDN) is to reduce latency, or reduce the delay in communication created by a network's design.
	--What are the benefits of CDNs?
		--Reduce page load time
		--Reduce bandwidth costs
		--Increase content availability
		--Improve website security
		
AWS CloudFront	https://aws.amazon.com/cloudfront/
	--Amazon CloudFront is a content delivery network (CDN) service built for high performance, security, and developer convenience.
		--Reduce latency
		--Improve security
		--Cut costs
		--Customize the code
	Es un servicio de entrega de contenido.
	Red de entrega de contenido (CDN)
	Mejora el rendimiento de lectura, el contenido se almacena en caché en edge location
	Da protección DDoS, integración con Shield, AWS Web Application Firewall WAF
	
	Origenes (es donde se almacena la información)
		Bucket S3
			Para distribuir archivos y almacenarlos en caché en el borde
			Seguridad mejorada con CloudFront (Origin Access Control OAC)
		Origen personalizado (HTTP)
			Application Load Balancer
			Sitio web S3 (primero se debe habilitar el bucket S3 como sitio web S3 estático)
			Cualquier backend HTTP que se desee
			
	CloudFront vrs S3 Cross Region Replication CRR
		CloudFront
			Red Global Edge
			Archivos se almacenan en caché duración un TTL
			Es ideal para contenidos estáticos que deben estar disponibles en todas partes
			
		S3 Cross Region Replication CRR
			Debe configurarse para cada región en la que se produzca la replicación
			Archivos se actualizan en tiempo real
			Sólo lectura
			Ideal para contenidos dinámicos que deben estar disponibles con baja latencia en pocas regiones
	
Herramientas de administración - 

AWS CloudFormation -> IaaC	https://aws.amazon.com/cloudformation/
	--Speed up cloud provisioning with infrastructure as code
	Con AWS CloudFormation, puedes tratar tu infraestructura como código. Esto significa que puedes crear un entorno escribiendo líneas de código en lugar de usar la consola de administración de AWS para aprovisionar recursos individualmente
	Con CloudFormation tenemos una forma declarativa de esbozar, de dibujar una infraestructura de AWS para cualquier recurso.
	podemos desplegar una infraestructura como código IaaC
	Productividad, es posible distruir y volver a crear una infraestructura en el cloud sobre la marcha
		--Scale your infrastructure
		--Extend and manage your infrastructure
		--Automate resource management
	
Amazon Cloud Development Kit -> CDK		https://aws.amazon.com/cdk/
	Permite definir una infraestructura en la nube utilizando un lenguaje conocido.
	Se conecta con AWS CloudFormation 

AWS Elastic Beanstalk -> PaaS	https://aws.amazon.com/elasticbeanstalk/
	--Easily migrate, deploy, and scale full-stack applications on AWS
	Con AWS Elastic Beanstalk, el usuario proporciona ajustes de código y configuración, y Elastic Beanstalk implementa los recursos necesarios para realizar tareas:
		-Es una visión centrada en el desarrollador de la implementación de una app en AWS
		-Utiliza todos los componentes que hemos visto antes: EC2, ASG, ELB, RDS, etc.
		-Se tiene control total sobre la configuración
		-Es gratuito, pero se paga por las instancias subyacentes
		-Servicio gestionado, todo es manejado por Beanstalk, sólo el código de la app es responsabilidad del desarrollador
	Existen tres modelos de arquitectura:
		1. Despliegue de una única instancia.
		2. LB + ASG:  lo que sería el balanceador con el grupo de escalado automático, que es ideal para aplicaciones web de producción o preproducción y
		3. Sólo ASG grupo de escalado automático, ideal para app no web en producción (para trabajadores, etc)
	Tiene soporte para muchas plataformas o lenguajes
	Con Beanstalk, el desarrollador solo se preocupa por el código
	
AWS CodeDeploy	https://aws.amazon.com/codedeploy/
	Automate code deployment to maintain application uptime
	Desplegar apps automáticamente
	Funciona con instancias EC2
	Funciona con servidores locales
	Servicio híbridos
	Los servidores deber ser aprivisionados y configurados previamente con el agente de CodeDeploy
	
AWS CodeCommit	https://aws.amazon.com/codecommit/
	--Securely host highly scalable private Git repositories and collaborate on code
	Antes de enviar el código de la app a los servidores, es necesario almacenario en algún lugar
	Para control de versiones, regularmente se utiliza GitHub
	Utilizar tecnololía GitHub
	Los cambios en el código se versionan automáticamente
	Ventajas:
		Totalmente gestionado
		Escalable y de alta disponibilidad
		Privado, seguro, integrado con AWS
	
AWS CodeBuild		https://aws.amazon.com/codebuild/
	--Build and test code with automatic scaling
	Servicio de construcción de código en el Cloud
	Compila el código fuente, ejecuta las pruebas y produce paquetes que están listos para ser desplegados por CodeDeploy, por ejemplo
	Contruye el código y proporciona un artefacto listo para desplegar
	Totalmente gestionado, sin servidor
	Sólo se paga por el tiempo de compilación
	
AWS CodePipeline (Capa de orquestación)		https://aws.amazon.com/codepipeline/
	--Automate continuous delivery pipelines for fast and reliable updates
	Orquesta los diferentes pasos para que el código sea empujado automáticamente  a producción
	Código -> Construir -> Probar -> Aprovionar -> Desplegar
	Base de CICD (Integración continua y entrega continua)
	Ventajas
		Totalmente gestionado, compatible con CodeCommit, CodeBuild, CodeDeploy, Elastic Beanstalk, CloudFormation, GitHub y plugins
		
AWS CodeArtifact	https://aws.amazon.com/codeartifact
	--Secure, scalable, and cost-effective package management for software development
	Es una gestión de artefactos segura, escalable y rentable para el desarrollo de software
	Paquetes de software dependen unos de otros, esto se le llama dependencia de Software
	Almacenar y recuperar estas dependencias se llama Gestión de artefactos
	Funciona con herramientas comunes de gestión de dependencias como Maven, Gradle, npm, yarn, twine, pip y Nuget
	
AWS CodeStar	https://aws.amazon.com/es/codestar/
	Interfaz de usuario unificada para gestionar fácilmente las actividades de desarrollo de software en un solo lugar
	Es un forma rápida de empezar a configurar correctamente CodeCommit, CodePipeline, CodeBuild, CodeDeploy, Elastic  Beanstalk, EC2, etc

AWS Cloud9	https://aws.amazon.com/cloud9
	Es un IDE en la nube para escribir, ejecutar y depurar código
	Permite colaboración de código en tiempo real
	
AWS System Manager (SSM)	https://aws.amazon.com/systems-manager/
	--Manage nodes at scale on AWS and in multicloud and hybrid environments
	Ayuda a gestionar tus sistemas EC2 y On-Premises a escala
	Otro servicio híbrido de AWS
	Carácteristicas importantes
		Automatización de parches para mejorar la normativa
		Ejecuta comandos en toda una flota de servidores
		Almacena la configuración de los parámetros con el almacén de parámetros SSM
	Cómo funciona
		Se necesita instalar el agente SSM en los sistemas que se controlan
		Con el agente, es posible ejecutar comandos, parchear y configurar nuestros servidores
		
AWS OpsWorks	https://aws.amazon.com/documentation-overview/opsworks/
	--AWS OpsWorks is a configuration management service that provides managed instances of Chef and Puppet. Chef and Puppet are automation platforms that allow you to use code to automate the configurations of your servers. 
	Chef y Puppet ayudan a realizar la configuración del servidor de forma automática, o acciones repetitivas
	Funciona con AC2 y VM On-Premises
	Es una alternativa a AWS SSM		
	
Servicios Globales

Amazon Route 53		https://aws.amazon.com/route53/
	--A reliable and cost-effective way to route end users to Internet applications
	Es un DNS gestionado (Sistemas de nombres de dominio)
	DNS es una colección de reglas y registros que ayuda a los clientes a entender cómo llegar a un servidor a través de las URL
	
	Políticas enrutamiento
		Simple Routing Policy, no hay controles de salud
		Weighted Routing Policy
		Latency Routing Policy
		Failover Routing Policy, Recuperación de desastres, tiene control de salud
					
S3 Transfer Acceleration
	Aumenta la velocidad de transferencia transfiriendo el archivo a una ubicación edge de AWS que reenviará los datos al bucket de S3 en la región de destino
	
AWS Global Accelerator
	Mejora la disponibilidad y el rendimiento global de la aplicación utilizando la red global de AWS
	Aprovecha la red interna de AWS para optimizar la ruta hacia tu aplicación (60% de mejora)
	
	CloudFront vrs AWS Global Accelerator
		Ambos utilizan la red global de AWS y sus ubicaciones de borde en todo el mundo
		Ambos servicios se integran con AWS Shield para la protección DDoS
		
		CloudFront CDN
			Mejora el rendimiento de contenido almacenable en caché
			El contenido se entrega en edge location
		AWS Global Accelerator
			Sin almacenamiento en caché
			Mejora el rendimiento de una amplia gama de aplicaciones sobre TCP y UDP
			Bueno para casos de uso de HTTP que requieren direcciones IP estáticas y que requieran conmutación por error regional dterminista y rápida
			
AWS Outposts	https://aws.amazon.com/outposts
	--Run AWS infrastructure and services on premises for a truly consistent hybrid experience
	Ampliar la infraestructura y los servicios de AWS a diferentes ubicaciones, incluido tu centro de datos en las instalaciones.
	Cloud híbrido: empresas que mantienen una infraestructura local junto a una infraestructura en la nube
	Los Outposts de AWS son racks de servidores que ofrecen la misma infraestructura, servicios, API y herramientas de AWS para crear tus propias aplicaciiones en las instalaciones al igual que en el Cloud
	Ventajas
		Acceso de baja latencia a los sistemas locales
		Procesamiento local de datos
		Residencia de datos
		Migración más fácil de onpremises a el Cloud
		
AWS WaveLenght	https://aws.amazon.com/wavelength/
	--Run applications using AWS Infrastructure and services in AWS telco partners’ data centers to meet your low latency, data residency, and resiliency needs.
	WaveLenght Zones son despliegues de infraestructura incrustados en los centros de datos de los proveedores de telecomunicaciones de las redes 5G
	Lleva los servicios de AWS al límite de las redes 5G
	El tráfico no sale de la red del proveedor de servicios
	
AWS Local Zones	https://aws.amazon.com/about-aws/global-infrastructure/localzones/
	--Run applications on AWS infrastructure closer to your end users and workloads
	Coloca la informática, el almacenamiento, la base de datos y otros servicios de AWS más cerca de los usuarios finales para ejecutar aplicaciones sensibles a latencia	

Redes:

Amazon VPC -> Amazon Virtual Private Cloud 
	--Define and launch AWS resources in a logically isolated virtual network
	Amazon VPC permite aprovisionar una sección aislada de AWS Cloud. En esta sección aislada, puedes iniciar recursos en una red virtual definida. Dentro de una nube virtual privada (VPC), puedes organizar los recursos en subredes. Una subred es una sección de una VPC que puede contener recursos como las instancias de Amazon EC2
	
	Dirección IP
		IPv4 pública, se utiliza en internet
			En EC2 si se detiene o inicia obtiene una nueva (por defecto)
		IPv4 privada, se utiliza en redes privadas
			En EC2 si se detiene o inicia es fija
		IP Elástica, permite adjuntar un dirección IPv4 pública fija a la instancia EC2
		IPv6
	VPC Virtual Private Cloud, red privada para desplegar recursos
		Se tiene subredes pública y privada, para definir el acceso a internet y entre subredes se utiliza Tablas de Ruta
	
	Gateway - Puerta de enlace de internet
		 Es una conexión entre una VPC e Internet.
	Puerta de enlace privada virtual
	
	Puerta de enlace pública
	Puerta de enlace privada
	
	Gateways NAT (gestionadas por AWS) y las instancias NAT (autogestionadas) permiten que tus instancias a internet sin dejar de ser privadas
	
		
	ACL Lista de control de acceso a la red (No tiene estado)
		Una ACL es un firewall virtual que controla el tráfico entrante y saliente a nivel de subred.
		
	NACL (ACL de red)
		Un firewall que controla el tráfico desde y hacia la subred
		Puede tener reglas ALLOW y DENY
		Se adjuntan a nivel de subred
		Las reglas sólo incluyen direcciones IP
		Sin estado, las reglas deben permitir de forma explícita el tráfico de retorno
		Se aplica a todas las instancias de las subredes
	Grupos de seguridad
		Un firewall que controla el tráfico hacia y desde una instancia EC2
		Puede tener sólo reglas ALLOW
		Las reglas sólo incluyen direcciones IP y otros grupos de seguridad
		Con estado, el tráfico de retorno se admite automáticamente, independientemente de las reglas
		Se aplica auna instancia únicamente
		
	Logs de flujos de la VPC
		Captura información sobre el tráfico IP que entra en tus interfaces, VPC, Subred, Interfaz de Red Elástica
		
	VPC Peering
		Conectar dos VPC, de forma privada, utilizando la red de AWS
		
	VPC Endpoint
		permiten conectar a los servicios de AWS utilizando una red privada en lugar de la red www pública
		Proporciona mayor seguridad y menos latencia para acceder a los servicios de AWS
	
	VPC Endpoint Gateway: S3 y DinamoDB
	VPC Endpoint Interface: el resto
	
	AWS PrivateLink (Servicios VPC Endpoint)
		Forma más segura y escalable de exponer un servicio a miles de VPCs
		No requiere peering de VPC, gatewat de Internet, NAT, tablas de rutas, etc
		Requier un Network Load Balancer (VPC de servicio) y un ENI (VPC de cliente)
		
	AWS Site to Site
		Conecta una VPN on-premise a AWS
		Conección se encripta automáticamente
		Para por Internet público
		En instalaciones se debe utilizar un Customer Gateway (CGW)
		AWS debe utilizar un Virtual Private Gateway (VGW)
		
	Direct Connect (DX)
		Establece una conección física entre las instalaciones y AWS
		Conección es privada, segura y rápida
		Pasa por red privada
		Tarda al menos un mes en establecerse
		
	Cliente VPN
		Conectar desde tu ordenador mediante OpenVPN a tu red privada en AWS y en las instalaciones
		Permite conectarse a tus instancias EC2 a través de una IP privada
		Pasa por Internet pública
		
	Transit Gateway
		Servicios para simplificar topologías que se complican
		Para tener peering transitivo entre miles de VPC y locales, conección hub-and-spoke (estrella)
		Un único Gateway para proporcionar esta funcionalidad
		Funciona con el Gateway de Direct Connect y las conecciones VPN
		
	Subredes
		Una subred es una sección de una VPC en la que se pueden agrupar recursos en función de las necesidades operativas o de seguridad. Las subredes pueden ser públicas o privadas.
		
		Las subredes públicas contienen recursos a los que el público debe tener acceso, como el sitio web de una tienda en línea.

		Las subredes privadas contienen recursos a los que solo se debe tener acceso a través de la red privada, como una base de datos que contiene la información personal de los clientes y los historiales de pedidos. 
	
	Amazon Direct Connect
		Es un servicio que permite establecer una conexión privada dedicada entre un centro de datos y una VPC.  
		Crear conección para poder ingresar a diferentes recursos
		
	Tráfico de red en una VPC
		Cuando un cliente solicita datos de una aplicación alojada en la nube de AWS, esta solicitud se envía como un paquete. Un paquete es una unidad de datos enviada por Internet o a través de una red. 
		
	Grupos de seguridad (Tiene estado)
		Un grupo de seguridad es un firewall virtual que controla el tráfico entrante y saliente de una instancia de Amazon EC2.

Amazon Route 53 
	Es un servicio web DNS
	Ofrece a los desarrolladores y las empresas una forma fiable de enrutar a los usuarios finales a aplicaciones de Internet alojadas en AWS.
	Otra característica de Route 53 es la capacidad de administrar los registros DNS de los nombres de dominio. 
	
	Sistema de nombres de dominio DNS
	
Almacenamiento y base de datos:
	
Amazon S3 -> Amazon Simple Storage Service.  https://aws.amazon.com/s3/
	--Object storage built to retrieve any amount of data from anywhere
	Ventaja Sin servidor 
	Es un servicio que proporciona almacenamiento en el nivel de objeto. Amazon S3 almacena datos como objetos en buckets.
	Permite almacenar objetos (archivos) en buckets (directorios)
	Buckets se definen a nivel de región
	S3 es un servicio global pero los buckets se crean en una región
	Convención de nombres
		Sin mayúsculas, sin guión bajo
		De 3 a 63 carácteres
		No es una IP
		Debe empezar por letra minúscula o número
		No debe empezar por el prefijo xn--
		No debe terminar con el sufijo -s3alias
		
	--Scalability
	--Durability and availability
	--Security and data protection
	--Lowest price and highest performance
	
	Clases de almacenamiento de Amazon S3
		-Amazon S3 Standard proporciona alta disponibilidad para los objetos.
		-Amazon S3 Standard-Infrequent Access es ideal para los datos a los que se accede con poca frecuencia
		-Amazon S3 One Zone-Infrequent Access
		-Amazon S3 Intelligent-Tiering. Amazon S3 supervisa los patrones de acceso de los objetos.
		-Amazon S3 Glacier Instant Retrieval, Se pueden recuperar objetos en pocos milisegundos
		-Amazon S3 Glacier Flexible Retrieval es una clase de almacenamiento de bajo precio ideal para archivar datos.
		-Amazon S3 Glacier Deep Archive admite la conservación a largo plazo y la preservación digital de datos a los que se puede acceder una o dos veces al año. La clase de almacenamiento de objetos más barata, ideal para archivar
		-Amazon S3 Outposts, Ofrece almacenamiento de objetos a su entorno local de AWS Outposts.
	
Amazon RDS -> Amazon Relational Database Service   https://aws.amazon.com/rds/
	--Easy to manage relational databases optimized for total cost of ownership
	Es un servicio que permite ejecutar bases de datos relacionales en AWS Cloud.
	
	Motores de base de datos de Amazon RDS
		Amazon RDS está disponible en seis motores de base de datos, que optimizan la memoria, el rendimiento o la entrada/salida (E/S). Los motores de base de datos compatibles incluyen:
			*Amazon Aurora ->   database-dev     UserAdmin: admin  admin123$   FirstDB
			-PostgreSQL
			-MySQL
			-MariaDB
			-Oracle Database
			-Microsoft SQL Server
			
Amazon DynamoDB - https://aws.amazon.com/dynamodb/
	--Serverless, fully managed, distributed NoSQL database with single-digit millisecond performance at any scale
	Es un servicio de base de datos clave-valor. Ofrece un rendimiento de milisegundos de un solo dígito a cualquier escala
	Bases de datos no relacionales, en una base de datos no relacional, se crean tablas. Una tabla es un lugar en el que se pueden almacenar y consultar datos
	
Amazon Redshift - https://aws.amazon.com/redshift/
	--Deliver unmatched price-performance at scale with SQL for your data lakehouse
	Es un servicio de almacenamiento de datos que puedes usar para analizar big data - Datawarehouse
	
Amazon DMS -> AWS Database Migration Service - https://aws.amazon.com/dms/
	Permite migrar bases de datos relacionales, bases de datos no relacionales y otros tipos de almacenes de datos.
	
Storage:
AWS Backup - https://aws.amazon.com/es/backup/
	--Administre y automatice la protección de los datos de manera centralizada
	
AWS Elastic Disaster Recovery
	--
	
Servicios de bases de datos adicionales
	Amazon DocumentDB Es un servicio de base de datos de documentos compatible con cargas de trabajo de MongoDB. (MongoDB es un programa de base de datos de documentos).
	Amazon Neptune Es un servicio de base de datos de grafos.
	Amazon QLDB -> Amazon Quantum Ledger Database es un servicio de base de datos de libro mayor. 
	Amazon Managed Blockchain es un servicio que puedes usar para crear y administrar redes de cadenas de bloques con marcos de trabajo de código abierto
	Amazon ElastiCache es un servicio que añade capas de almacenamiento sobre las bases de datos para ayudar a mejorar los tiempos de lectura de las solicitudes comunes. Es compatible con dos tipos de almacenes de datos: Redis y Memcached
	Amazon DAX -> Amazon DynamoDB Accelerator es una caché en memoria para DynamoDB
	
Seguridad:
	Modelo de responsabilidad compartida de AWS  https://aws.amazon.com/compliance/shared-responsibility-model/
		AWS es responsable de algunas piezas del entorno y tú (el cliente) de otras. Este concepto se conoce como modelo de responsabilidad compartida.
	
	AWS: Seguridad de la nube
		AWS es responsable de la seguridad de la nube.
		
	Clientes: Seguridad en la nube
		Los clientes son responsables de la seguridad de todo lo que crean y ponen en la nube de AWS.
		
Amazon IAM -> AWS Identity and Access Management   https://aws.amazon.com/iam/
	Permite administrar el acceso a los recursos y servicios de AWS de manera segura. 
	
	Funciones de IAM, que se analizan detalladamente en esta lección:
	  -Usuarios, grupos y roles de IAM
	  -Políticas de IAM
	  -Autenticación multifactor
	  
	Usuario raíz de la cuenta de AWS
		Cuando crea por primera vez una cuenta en AWS, comienza con una identidad conocida como usuario raíz.
		
	Usuarios de IAM
		Un usuario de IAM es una identidad que creas en AWS. Representa a la persona o aplicación que interactúa con los servicios y recursos de AWS. Consiste en un nombre y credenciales.
		
	Políticas de IAM
		Una política de IAM es un documento que concede o deniega permisos a los servicios y recursos de AWS. 
		Práctica recomendada: Sigue el principio de seguridad de mínimo privilegio al conceder permisos. 
		
	Grupos de IAM
		Un grupo de IAM es un conjunto de usuarios de IAM. Cuando se asigna una política de IAM a un grupo, a todos los usuarios del grupo se les conceden los permisos especificados por la política.
		
	Roles de IAM
		Es una identidad que se puede adoptar para obtener acceso temporal a los permisos.
		Práctica recomendada: Los roles de IAM son ideales para situaciones en las que el acceso a servicios o recursos debe otorgarse temporalmente, en lugar de a largo plazo.

	MFA -> Autenticación multifactor
		En IAM, la autenticación multifactor proporciona una capa adicional de seguridad para tu cuenta de AWS.
		
	2 Herramientas de seguridad de Servicio IAM
		IAM Credentials Report / Informe de credenciales de IAM
			Un informe que enumera todos los usuarios de tu cuenta  y el estado de tus diversas credenciales
			
		IAM Access Advidor / Asesor de IAM (nivel de usuario)
			Muestra los permisos de servicio concedidos a un usuario y cuando se accedió a esos  servicios por última vez
			
		
AWS Organizations
	Para unificar y administrar varias cuentas de AWS en una ubicación central.
	
	Ventajas:
		-Administración Centralizada
		-Facturación unificada
		-Agrupaciones jerárquicas de cuentas
		-Control de acceso a las acciones de API y servicios de AWS
	
	Unidades organizativas UO
		En AWS Organizations, puedes agrupar las cuentas en unidades organizativas para facilitar la administración de cuentas con requisitos empresariales o de seguridad similares.		
	
Conformidad
	Centro de Confirmidad de AWS
		Contiene recursos que te pueden ayudar a obtener más información sobre la conformidad de AWS. 
		
	AWS Artifact 
		es un servicio que proporciona acceso bajo demanda a los informes de seguridad y conformidad de AWS y a determinados acuerdos en línea. 
		Según el sector de tu empresa, es posible que debas respetar estándares específicos. Una auditoría o inspección garantizará que la empresa haya cumplido esos estándares.
		
		consta de dos secciones principales:
			-AWS Artifact Agreements: proporciona acceso a los documentos de seguridad y conformidad de AWS, como las certificaciones ISO de AWS, los informes del sector de pagos con tarjeta (PCI) y los informes de control de organización de servicios (SOC).   y
			-AWS Artifact Reports: AWS Artifact Reports proporciona informes de conformidad de auditores de terceros. Estos auditores han probado y verificado que AWS cumple una serie de estándares y regulaciones de seguridad globales, regionales y específicas del sector.
		
	AWS Shield
		Es un servicio que protege las aplicaciones contra ataques DDoS Distributed Denial-of-Service (ataque de denegación de servicio) 
		Ofrece dos niveles de protección: 
			-Estándar protege automáticamente a todos los clientes de AWS sin coste alguno y
			-Avanzado es un servicio de pago que proporciona diagnósticos de ataques detallados y ofrece la capacidad de detectar y mitigar ataques DDoS sofisticados.
		
		AWS Shield Standard: protege a tu sitio web y app, para todos los clientes sin coste adicional
		AWS Shield Advanced: protección DDoS premium 24-7 ($3000 al mes por organización)
		CloudFront y Route 54:
			Protección de la disponibilidad mediante una red de borde global
			Combinado con AWS Shield, proporciona mitigación de ataques en el borde
	
	AWS Web Application Firewall WAF
		Es un firewall de aplicaciones web que permite supervisar las solicitudes de red que llegan a tus aplicaciones web.
		AWS WAF trabaja junto con Amazon CloudFront y una instancia de Application Load Balancer.
		AWS WAF funciona de forma similar a ACL para bloquear o permitir el tráfico. No obstante, lo hace mediante una lista de control de acceso (ACL) web para proteger tus recursos de AWS. 
		Protege tus aplicaciones web de las vulnerabilidades web más comunes (capa 7)
		Filtra solicitudes específicas basadas en reglas 
		Despliega en el Application Load Balancer, API Gateway, CloudFront
		Define la ACL
		Protege de los ataques más comunes: inyección SQL y Cross-Site Scripting XSS
		
	AWS Network Firewall
		Protege toda la VPC
		Protección de capa 3 a 7
		
	AWS KMS -> AWS Key Management Service
		permite realizar operaciones de cifrado mediante el uso de claves criptográficas
		AWS gestiona las claves de cifrado por nosotros
		Está activado automáticamente para los siguientes servicios
			Logs de CloudTrail
			S3 Glacier
			Storage Gateway
		Para demás servicios de debe de activar manual
		
	AWS CloudHSM
		Provee hardware de encriptación
		HSM = Módulo de Seguridad de Hardware
		Tipos de Customer Master Keys: CMK
		
	AWS Certificate Manager ACM
		Te permite aprovisionar, gestionar y desplegar fácilmente los certificados SSL/TLS
		Se utilizan para proporcionar encriptación en vuelo para los sitios web (HTTPS)
		
	AWS Secrets Manager
		Capacidad para forzar la rotación de secretos cada X días
		Integración con Amazon RDS
		los Secretos se incriptan mediante KMS
		Pensado principalmente para la integración con RDS
		
	Amazon GuardDuty
		Es un servicio que proporciona detección inteligente de amenazas para tu infraestructura y recursos de AWS.
		Utiliza algoritmos de Machine Learning, detección de anomalías y datos de terceros
		Incluye
			Logs de eventos de gestión de CloudTrail
			Logs de flujo de la VPC
			Logs de DNS
		Se puede configurar reglas de EventBridge de CloudWatch
		Puede proteger contra ataques de criptomonedas
		
	Amazon Inspector
		lleva a cabo evaluaciones de seguridad automatizadas.
		Para Instancias EC2, aprovechando el agente AWS Agent Manager SSM
		Para imágenes de contenedor enviadas a Amazon ECR
		Para funciones Lambda
		Que evalúa Amazon Inspector?
			Escaneo continuo de la infraestructura, sólo cuando sea necesario
		
	AWS Config
		Ayuda a auditar y registrar la normativa de tus recursos de AWS
		Ayuda a registrar las configuraciones y los cambios a lo largo del tiempo
		Posibilidad de almacenar los datos de configuración en S3 (analizados por Athena)
		Preguntas que se pueden resolver con AWS Config
			Hay acceso SSH sin restricciones a mis grupos de seguridad?
			Mis buckets tienen acceso público?
			Cómo ha cmabiado la configuración de mi ALB  con el tiempo?
		Es un servicio por región
		
	AWS Macie
		Es un servicio de seguridad y privacidad de datos totalmente gestionado que utiliza Machine learning y la concordancia de patrones para descubrir y proteger nuestros datos sensibles en AWS
		Ayuda a identificar y alertar sobre datos sensibles, como por ejemplo la información personal identificable PII.
		
	AWS Segurity Hub
		Es una herramienta de seguridad central para gestionar la seguridad en varias cuentas de Amazon y de esta forma automatizar las comprobaciones de seguridad.
		
	AWS Detective
		Analiza, investiga y también identifica de forma rápida la causa raíz de los problemas de seguridad o de las actividades sospechosas mediante machine learning y el uso de grafos.		
		Recoge y procesa automáticamente los eventos de logs de flujo de la VPC de CloudTrail de GuardDuty y crea una vista totalmente unificada.
		
	AWS Abuse
		Cada vez que tú sospeches de que los recursos de AWS se utilicen con fines abusivos o ilegales, debes informar de esa sospecha y ahora entenderás por qué. 
		En primer lugar, hay que entender que son aquellos fines abusivos o prohibidos:
			Spam desde una dirección propiedad de AWS
			Escaneo de puertos
			Ataques DoS o DDoS direcciones de AWS que intente sobrecargar tus servidores/software
			Intentos de intrusión
			Intentar alojar contenido censurable o con derechos de autor en servicios de AWS
			Distribuir malware
	
	Privilegios del usuario root del usuario raíz.
		Usuario propietario de la cuenta
		Acceso completo a todos los servicios de las cuentas
		Las acciones que solo puede realizar el usuario root son las siguientes:
			En primer lugar, cambiar la configuración de la cuenta, por ejemplo, el nombre de la cuenta, la dirección de correo electrónico, la contraseña del usuario root, las claves de acceso del usuario root, ver ciertas facturas de impuestos, cerrar la cuenta de Amazon Web Services.
			Ver facturas
			Cerrar cuenta
			Restaurar permisos usuario IAM
			Configurar un bucket de Amazon S3 para habilitar lo que es el MFA
			Tiene la posibilidad de editar o eliminar una política de bucket de Amazon S3 que incluya lo que es un ID de VPC o un ID de inputs de VPC no válido.
			
	AWS IAM Access Analyzer -> Analizador de Acceso
		Es una característica de IAM que ayuda a los administradores y propietarios de recursos a descubrir y revisar los permisos de sus recursos en AWS.
		Es un servicio que averigua qué recursos se comparten externamente. Por ejemplo, buckets, S3, roles, IAM, claves, kms, funciones Lambda y capas, colas, SQS o incluso Secrets Manager.
		Define zona de confianza: Cuenta de AWS o AWS Organization
		Acceso fuera de zona de confianza = Hallazgos
		
Machine Learning
	
	AWS Rekognition
		Este servicio nos permite encontrar y detectar objetos, personas, textos y escenas en imágenes y videos mediante machine learning, lo cual permite realizar análisis facial y también búsqueda facial para verificar la identidad de usuarios y hacer un recuento de personas.
		Casos de Uso
			Etiquetado
			Moderación de textos
			Detección y análisis de rostros
			Búsqueda y verificación de famosos
			Trayectoria
			
	AWS Transcribe
		Servicio que convierte automáticamente el habla en texto
		Utiliza un proceso de deep learning llamado reconocimiento automático del habla (ASR) para convertir el habla en texto de forma rápida y precisa
		Elimina automáticamente la información de Identificación Personal PII
		
	Amazon Polly
		El cual nos permite hacer lo contrario de Amazon Transcribe.
		Nos permite convertir el texto en voz real utilizando Deep Learning o aprendizaje profundo en español, permitiendo así crear aplicaciones que hablan muy fácilmente
		
	AWS Translate
		Traducción natural y precisa de idiomas
		
	Amazon Lex y Connect
		Lex tiene la misma tecnología que impulsa a Alexa
			Reconocimiento Automático del habla ASR para convertir habla en texto
			Comprensión del lenguaje Natural para reconocer la intención del texto, de las personas que llaman
		Connect
			Recibe llamadas, crea flujos de contacto, centro de contacto visual bastado en la nube
			Puede integrarse con otros sistemas CRM o AWS
			
	Amazon Comprehend
		Sirve para el Procesamiento del Lenguaje Natural, también conocido como Natural Languaje Processing NLP.
		Utiliza el Machine learning para encontrar ideas y relaciones en el texto
		
	AWS SageMaker 
		es un servicio de machine learning muy reconocido mundialmente de AWS.
		Es un servicio totalmente gestionado que permite a los desarrolladores o científicos de datos construir modelos de machine learning.
			
	Amazon Forecast
		Servicio para hacer predicciones.
		Es un servicio totalmente gestionado que utiliza el machine learning para ofrecer predicciones muy precisas.
		
	Amazon Kendra
		Es un servicio de búsqueda de documentos totalmente gestionado y potenciado por el machine learning.

		Extrae respuestas de un documento, como un documento de texto, un PDF, un HTML, un PowerPoint, un Word, o incluso la página de preguntas frecuentes donde tenemos una fuente de datos.
		
	Amazon Personalize
		Servicio que utiliza Machine Learning y es totalmente gestionado. Utiliza machine learning para crear aplicaciones con recomendaciones personalizadas en tiempo real, por ejemplo, recomendaciones o una reclasificación de productos personalizados.
	
	Amazon Textract
	el servicio de extracción de texto, el cual nos permite extraer automáticamente el texto, la escritura y los datos de cualquier documento, únicamente escaneando todo esto utilizando la inteligencia artificial y el machine learning.
			
		
		
Supervisión y Análisis, monitorización
	
	Amazon CloudWatch
		Es un servicio web que permite supervisar y administrar diversas métricas y configurar acciones de alarma basadas en los datos de esas métricas.
		
		Con CloudWatch, puedes crear alarmas que efectúen acciones automáticamente si el valor de tu métrica ha superado un umbral predefinido o queda por debajo de él. 
		
	AWS CloudTrail
		Es un servicio que permite llevar a cabo auditorías de gobernanza, de conformidad, operativas y de riesgo en su cuenta de AWS.
		Registra las llamadas a la API de tu cuenta. La información registrada incluye la identidad de la persona que llama a la API, la hora de la llamada a la API, la dirección IP de origen de la persona que llama a la API y mucho más.
		
	AWS Trusted Advisor
		Es un servicio web que inspecciona tu entorno de AWS y proporciona recomendaciones en tiempo real de acuerdo con las prácticas recomendadas de AWS.
		Trusted Advisor compara sus resultados con las prácticas recomendadas de AWS en cinco categorías:
			-Optimización de costes
			-Rendimiento
			-Seguridad
			-Tolerancia a errores
			-límites de los servicios
			
	AWS Health Dashboard
		muestra estado general de los servicios de AWS
		Anteriormente llamado AWS Personal Health Dashboard (PHD)
		AWS Account Health Dashboard proporciona una visión personalizada del rendimiento y la disponibilidad de los servicios de AWS subyacentes a tus recursos de AWS
		Puede agregar datos de toda una AWS Organizations
		
	AWS X-Ray
		Ayuda a los desarrolladores a analizar y depurar aplicaciones distribuidas de producción, como las constituidas con una arquitectura de microservicios.
	
	AWS CodeGuru
		Revisiones de código automatizadas y recomendaciones sobre el rendimiento de las aplicaciones
		
		Se muestra toda la información relevante
			
Precios y soporte
	
	El nivel gratuito de AWS te permite comenzar a usar determinados servicios sin tener que preocuparte por incurrir en costes durante el periodo especificado. 
	
	Calculadora de precios de AWS
		Te permite analizar los servicios de AWS y crear una estimación del precio de tus casos prácticos en AWS. 
		
	Panel Gestión de facturación y costes de AWS -> Billing and Cost Management home
		Para pagar la factura de AWS, supervisar el uso y analizar y controlar los costes.
		
	Características de AWS Billing and Cost Management
		-Facturación y pagos
		-Análisis de costos
		-Organización de costos
		-Elaboración de presupuestos y planificación
		-Ahorros y compromisos
	
	Facturación unificada
		Un servicio que te permite administrar varias cuentas de AWS desde una ubicación central.
	
	AWS Organizations también ofrece la opción de facturación unificada.
		Helps you centrally manage and govern your environment as you grow and scale your AWS resources. Using AWS Organizations, you can create accounts and allocate resources, group accounts to organize your workflows, apply policies for governance, and simplify billing by using a single payment method for all of your accounts. AWS Organizations is integrated with other AWS services so you can define central configurations, security mechanisms, audit requirements, and resource sharing across accounts in your organization. AWS Organizations is available to all AWS customers at no additional charge.
		
	AWS Control Tower
		Servicio nos proporciona una forma fácil de configurar y también gobernar un entorno AWS multicuenta, seguro y conforme a las mejores prácticas.
		Se ejecuta sobre las organizaciones de AWS e implementa las SCP (Políticas de Control de Servicios)
		
	AWS Resource Access Manager RAM
		Servicio que nos va a permitir compartir recursos de AWS que nosotros poseamos con otras cuentas de Amazon.
		Comparte con cualquier cuenta o dentro de tu organización
		Evita la duplicación de recursos
		
	AWS Service Catalog
		Servicio que permite a las empresas organizar y gestionar catálogos de servicios aprobados para que se utilicen en toda la empresa en concreto.
		
	AWS Budgets
		Con AWS Budgets, establezca presupuestos personalizados para hacer un seguimiento de los costos y el uso, y responda rápidamente a las alertas que recibe mediante correo electrónico o notificaciones de SNS cuando supera un límite.
		
	AWS Cost Explorer
		Es una herramienta que te permite visualizar, comprender y administrar los costes y el uso de AWS a lo largo del tiempo.
		
		AWS Cost Explorer incluye un informe predeterminado de los costes y el uso de los cinco servicios principales de AWS con costes acumulados.
		
	AWS Support
		AWS ofrece cuatro planes de soporte diferentes para ayudarte a solucionar problemas, reducir los costes y usar de forma eficiente los servicios de AWS. 
		
		Se puede elegir entre los siguientes planes de soporte para satisfacer las necesidades de tu empresa:
			-Basic Support: 
				es gratuito para todos los clientes de AWS. Incluye acceso a documentos técnicos, documentación y comunidades de soporte. Con Basic Support, también puedes ponerte en contacto con AWS si tienes preguntas sobre facturación y aumentos del límite de servicio.
			-Developer Support
			-Business Support
			-Enterprise On-Ramp
			-Enterprise
		
		Planes de Soporte
			Basic, Servicio de atención al cliente 24-7 y comunidades, Trusted Advisor limitado, Personal Health Dashboard
			Developer, Basic+, acceso por correo en horario laboral
			Business, 24-7, Destinado a cargas de trabajo de producción, Trusted Advisor ilimitado
			Enterprise On-Ramp, Acceso a un grupo de Gestores Técnicos de Cuentas TAM
			Enterprice, cargas de trabajo para misión crítica, un TAM asignado
			
		Administrador técnico de cuenta (TAM)
			Los planes Enterprise On-Ramp y Enterprise Support incluyen acceso a un administrador técnico de cuenta (TAM).
	
	AWS Marketplace
		Es un catálogo digital que incluye miles de listas de software de proveedores de software independientes. Puedes usar AWS Marketplace para buscar, probar y comprar software que se ejecute en AWS. 
		
	Modelos precios en AWS
		Paga por lo que usas
		Ahorra cuando reserves
		Paga menos usando más
		Paga menos al crecer AWS
		
	Herramientas de verificación de Costos y Gastos
		Dashboard de facturación
		Tags -> Etiquetas de asignacion de costes e informes, para organizar recursos
		Cost and Usage Reports (AWS CUR), contiene el conjunto más completo de datos de costes y uso de AWS disponible. Enumera el uso de AWS para cada categoría de servicio utilizada por una cuenta y sus usuarios de IAM
		Cost Explorer, visualiza, entiende y gestiona costes y uso de AWS a lo largo del tiempo
		Alarmas de facturación y presupuestos
		AWS Budgets, crea presupuesto y envía alarmas cuando los costes superen el presupuesto
			4 tipos de presupuestos: Uso, Coste, Reserva, Planes de ahorro
		Cost Anomaly Detection, monitorización continua de tus costes y uso mediante ML para detectar gastos inusuales, envía alertas e indica origen de alerta
		AWS Services Quotas, Avisa cuando se está cerca de un umbral de valor de cuota de servicio
		AWS Trusted Advisor, no se debe de instalar nada, analiza cuentas de AWS y proporciona recomendaciones en 5 categorías, optimización de costes, Rendimiento, Seguridad, Tolerancia a fallos y límites del servicio
			
		
		
		
Migración e Innovación

	AWS CAF -> AWS Cloud Adoption Framework	 https://aws.amazon.com/cloud-adoption-framework/
		--The AWS Cloud Adoption Framework (AWS CAF) leverages AWS experience and best practices to help you digitally transform and accelerate your business outcomes through innovative use of AWS. AWS CAF identifies specific organizational capabilities that underpin successful cloud transformations. These capabilities provide best practice guidance that helps you improve your cloud readiness. AWS CAF groups its capabilities in six perspectives: Business, People, Governance, Platform, Security, and Operations. Each perspective comprises a set of capabilities that functionally related stakeholders own or manage in the cloud transformation journey. Use the AWS CAF to identify and prioritize transformation opportunities, evaluate and improve your cloud readiness, and iteratively evolve your transformation roadmap.
		Ofrece directrices sobre seis áreas de interés, denominadas perspectivas.
		
	Perspectiva de comercial-business
		Garantiza que las TI estén en línea con las necesidades empresariales y que las inversiones en TI se vinculen a los resultados empresariales clave.
		Utiliza la perspectiva de negocio para crear un caso empresarial sólido de adopción de la nube y priorizar las iniciativas de adopción. Asegúrate de que tus estrategias y objetivos empresariales estén en línea con tus estrategias y objetivos de TI.
		Los roles comunes en la perspectiva de negocio incluyen:
			-Administradores de negocio
			-Administradores de finanzas
			-Responsables de presupuestos
			-Inversores estratégicos
			
	Perspectiva de personal-People
		Apoya el desarrollo de una estrategia de administración de cambio en toda la organización para adoptar la nube con éxito.
		Los roles comunes en la perspectiva de personal incluyen:
			-Recursos humanos
			-Plantilla
			-Administradores de personal
	
	Perspectiva de gobernanza-Governance
		Se centra en las habilidades y procesos para alinear la estrategia de las TI con la estrategia empresarial. Esto garantiza que se maximice el valor empresarial y se minimicen los riesgos.
		Los roles comunes en la perspectiva de gobernanza incluyen:
			-Director de información (CIO)
			-Administradores de programas
			-Arquitectos de empresas
			-Analistas empresariales
			-Administradores de cartera
			
	La perspectiva de plataforma-Security
		Incluye principios y patrones para implementar nuevas soluciones en la nube y migrar cargas de trabajo de las instalaciones a la nube.
		Los roles comunes en la perspectiva de plataforma incluyen:
			-Director de tecnología
			-Administradores de TI
			-Arquitectos de soluciones
			
	Perspectiva de seguridad-Operations
		Garantiza que la organización cumpla los objetivos de seguridad en materia de visibilidad, auditabilidad, control y agilidad. 
		Los roles comunes en la perspectiva de seguridad incluyen:
			-Director de seguridad de la información (CISO)
			-Administradores de seguridad de TI
			-Analistas de seguridad de TI
			
	Perspectiva de operaciones
		Ayuda a habilitar, ejecutar, utilizar, operar y recuperar cargas de trabajo de TI al nivel acordado con los inversores de la empresa.
		Los roles habituales en la perspectiva de operaciones incluyen:
			-Administradores de operaciones de TI
			-Administradores de soporte de TI
			
	Beneficios
		-Reduzca los riesgos comerciales
		-Mejore el rendimiento ambiental, social y de gobernanza
		-Aumente los ingresos
		-Mejore la eficiencia operativa
			
Estrategias de Migración
	-Volver a alojar-Rehosting  "lift-and-shift" implica mover aplicaciones sin realizar cambios. 
	-Redefinir la plataforma-Replatforming  "lift, tinker and shift" implica realizar algunas optimizaciones en la nube para obtener un beneficio tangible. La optimización se logra sin cambiar la arquitectura principal de la aplicación.
	-Refactorizar-Rearquitectura-Refactoring / Re-architecting   implica volver a imaginar cómo se diseña y se desarrolla una aplicación mediante el uso de funciones nativas en la nube. 
	-Volver a comprar-Repurchasing  implica pasar de una licencia tradicional a un modelo de software como servicio. 
	-Retener-Retain  consiste en mantener las aplicaciones que son esenciales para el negocio en el entorno fuente. Esto puede incluir aplicaciones que requieren una refactorización importante antes de que se puedan migrar o trabajos que se pueden posponer para más adelante.
	-Retirar-Retire  es el proceso de eliminar aplicaciones que ya no son necesarias.
	
Familia de productos AWS Snow
	Es un conjunto de dispositivos físicos que ayudan a transportar físicamente hasta exabytes de datos tanto dentro como fuera de AWS. 
	
	AWS Snow Family está compuesta por
		-AWS Snowcone: es un dispositivo de transferencia de datos y computación periférica pequeño, robusto y seguro
		-AWS Snowball
			--Snowball Edge Storage Optimized, adecuado para migraciones de datos a gran escala y flujos de trabajo de transferencias recurrentes, además de la computación local con mayores necesidades de capacidad
			--Snowball Edge Compute Optimized, que proporciona potentes recursos de computación para casos prácticos como machine learning, análisis de vídeo de movimiento completo, análisis y pilas de computación locales			
		-AWS Snowmobile: es un servicio de transferencia de datos a escala de exabytes que se utiliza para transferir grandes cantidades de datos, hasta 100 petabytes, a AWS.
		
Amazon CodeWhisperer-Amazon Q Developer
	Es un complemento de programación basado en inteligencia artificial. Analiza el código y los comentarios del desarrollador según programa en su entorno de desarrollo integrado. Utiliza el procesamiento de lenguaje natural para comprender los comentarios que hace el desarrollador y así completar el código. Al entender los comentarios en inglés, CodeWhisperer genera funciones completas y bloques de código que se adaptan a las descripciones del desarrollador. Además, CodeWhisperer analiza el resto del código y se asegura de que el generado coincide con el estilo y las convenciones de nomenclatura que ha usado el desarrollador.
	
Traspaso a la nube
	AWS Well-Architected Framework
		Ayuda arquitectos-clientes a comprender cómo diseñar y operar sistemas fiables, seguros, eficientes y rentables en la nube de AWS. 
		
	Well-Architected Framework se basa en seis pilares:
		-Excelencia operativa-Operational Excellence
			es la capacidad de ejecutar y supervisar sistemas para ofrecer valor empresarial y mejorar continuamente los procesos y procedimientos de soporte.
			--Operations as Code
			--Frequent, small, reversible changes
			--Anticipate failure
			--learn from all mistakes
		-Seguridad-Security
			es la capacidad de proteger la información, los sistemas y los activos y, al mismo tiempo, ofrecer valor empresarial mediante evaluaciones de riesgos y estrategias de mitigación.
			--Use a foundational identity
			--Security at all layers
			--Encription in transit y at rest
		-Fiabilidad-Reliability
			es la capacidad de un sistema para hacer lo siguiente:
				-Recuperarse de las interrupciones de la infraestructura o del servicio
				-Adquirir recursos de computación de forma dinámica para satisfacer la demanda
				-Mitigar interrupciones como configuraciones erróneas o problemas transitorios de red
			--Automatic Failure recovery
			--Scale horizontally
			--Stop guessin capacity
		-Eficiencia de rendimiento-Performance Efficiency
			es la capacidad de utilizar los recursos de computación de forma eficiente para satisfacer los requisitos del sistema y mantener esa eficiencia a medida que la demanda cambia y evoluciona la tecnología.
			--Pick the right tool for de the job
			--Use servelees
			--Go global in minutus
		-Optimización de costes-Cost Optimization
			es la capacidad de ejecutar sistemas para ofrecer valor empresarial al precio más bajo.
			--Cloud financial management
			--Measure efficiciency
			--Analyze and attribute expenditures
		-Sostenibilidad-Sustainability
			es la capacidad de mejorar continuamente los impactos de la sostenibilidad mediante la reducción del consumo de energía y el aumento de la eficiencia en todos los componentes de una carga de trabajo, maximizando los beneficios de los recursos proporcionados y minimizando los recursos totales requeridos.
			--Aspire for maximum utilization
			--Adopt more efficiente hardware
			--Used managed services
			
	Seis ventajas de la computación en la nube:
		-Pasar de gasto inicial a gasto variable.
			Los gastos iniciales incluyen centros de datos, servidores físicos y otros recursos en los que tendrías que invertir antes de utilizar los recursos de computación. 
		-Aprovechar las economías de escala masiva.
			Con la computación en la nube, puedes lograr un coste variable menor al que puedes obtener por tu cuenta. 
		-Dejar de adivinar la capacidad.
			Con la computación en la nube, no tienes que predecir cuánta capacidad de infraestructura necesitarás antes de desplegar una aplicación.
		-Aumentar la velocidad y la agilidad.
			La flexibilidad de la computación en la nube facilita el desarrollo y la implementación de aplicaciones.
		-Dejar de gastar dinero en funcionamiento y mantenimiento de los centros de datos.
			La computación en la nube en los centros de datos suele requerir que gastes más dinero y tiempo en administrar la infraestructura y los servidores.
		-Lograr un alcance global en cuestión de minutos.
			La presencia global de AWS Cloud permite desplegar aplicaciones rápidamente para clientes de todo el mundo, a la vez que les proporcionas una baja latencia.
			
El examen AWS Certified Cloud Practitioner incluye cuatro dominios:
	1. Conceptos de la nube
	2. Seguridad y conformidad
	3. Tecnología
	4. Facturación y precios
	
1- AWS Cost Explorer  
2- Amazon S3 
3- AWS Auto Scaling 
4- The Customer 
5- AWS Lambda
6- Site-to-Site VPN
7- 12-month trial with access to specific services under usage limits. 
8- Provide compliance reports and certifications
9- AWS Shield 
10-  AWS CloudTrail  X

11- AWS Fargate 
12- On-Demand Instances  X
13- Amazon Athena 
14- AWS CloudTrail   X
15- Managing billing and applying policies across multiple AWS accounts 
16- Attach an IAM role to the EC2 instance
17- Enterprise
18- Amazon EBS   X
19- Object-based storage X
20-  AWS IAM 
	
21- AWS WAF   X
22- AWS CloudFormation
23- Amazon Route 53 
24- Cache content at edge locations to reduce latency
25- Enforce permission boundaries across accounts 
26- Amazon DynamoDB 
27- Amazon CloudWatch
28- Scalable data warehouse 
29- Application Load Balancer 
30- AWS Systems Manager X

31- Amazon Aurora 
32- Setting permissions and access control for AWS services 
33- Reduced need for operational overhead
34- AWS Budgets  x
35- AWS Glue   x
36- Business
37- Route Table x
38- Amazon Inspector x
39- Amazon Route 53 x
40- AWS Lambda 

41- Provide a dedicated private network connection between AWS and on-premises data centers 
42- Amazon S3 Glacier Deep Archive 
43- Amazon Neptune  x
44- AWS Trusted Advisor 
45- Amazon EventBridge
46- ViewBilling
47- AWS Secrets Manager 
48- Amazon WorkSpaces
49- AWS Pricing Calculator 
50- Amazon EKS

51- AWS CloudTrail  x
52- Amazon S3
53- Add a second layer of login security
54- Amazon EC2  x  
55- Private IP 
56- Amazon S3 
57- Amazon SageMaker 
58- Amazon Inspector  x
59- S3 Access Analyzer x
60-  Amazon Route 53

61- Amazon Lex 
62- Amazon ElastiCache 
63- Amazon Certificate Manager (ACM) 
64-  AWS Cost Explorer 
65- AWS Batch 
66- AWS CloudFormation
67- AWS Transit Gateway   x
68- AWS Skill Builder  
69- KMS rotation   x
70- Enterprise

71- Amazon EFS
72- AWS Shield   x
73- AWS IAM x
74-  Reserved for long-term use with predictable pricing  x
75- AWS Systems Manager  x
76- AWS IoT Core 
77- Monitor and store log data from applications and AWS resources
78-  Private and blocked from public access
79- AWS CodeCommit
80- Service Control Policies (SCPs) 

81- Amazon Kinesis
82- Server-Side Encryption
83-  S3 Standard 
84- Amazon API Gateway
85- Automatic scaling of compute resources based on demand
86- AWS Fargate   x
87- AWS CloudTrail  x
88-  Amazon Neptune
89-  VPC Peering   x
90- AWS X-Ray

91- Amazon VPC
92- Optimizes costs by moving data between access tiers based on usage 
93- AWS Snowball 
94- AWS WAF  X
95-  AWS Elastic Beanstalk
96- Amazon ECS with EC2   X
97- AWS CodePipeline
98- AWS Budgets 
99-  Generate a pre-signed URL 
100- AWS Shield




