_#Deployment
#setup
Om een microservice (i.e. REST based) in Kubernetes opgezet te krijgen, heb je drie configuratie bestanden nodig, zijnde een deployment config, 
een service config en een route config.

* Deployment config: bevat onder andere de docker image die gebruikt moet worden, de namespace en de resource reservations zoals geheugen en CPU.
* Service config: is de interne DNS binnen Kubernetes en laat applicaties to om andere applicaties via service naam aan te spreken.
* Route config: is een config om services extern beschikbaar te stellen. Dit houdt in dat je van buiten de Kubernetes cluster (e.g., web applicatie,
 laptop, ... aan je deployment kan)

#Kubernetes
Omdat de co-promotor vertrouwd is met Red Hat producten, hebben we geopteerd om gebruik te maken van de Red Hat developer (OpenShift) sandbox,
welke een gratis OpenShift omgeving aanbiedt voor 30 dagen. OpenShift is de Red Hat, enterprise ready, Kubernetes distributie. We noemen het hier 
een Kubernetes distributie, maar eigenlijk is OpenShift een container platform, niet alleen een orchestration tool zoals Kubernetes, maar voor de 
eenvoud, noemen we OpensShift in het vervolg van de tekst in dezelfde naam als Kubernetes.

#Process van opzetten
Kubernetes deployments zijn eenvoudig in een REST context, in een gRPC context bleek  het toch iets uitdagender te zijn. Reden: de harde 
dependency van gRPC op TLS/SSL. We moesten de Kubernetes/OpenShift routes gaan configureren om, in plaat van op de Ingress controllers, TLS 
verificatie over te laten aan de deployments (i.e., containers/PODs). Dit vereiste in een eerste stap dat TLS terminatie van de route 
geconfigureerd werd als "passthrough" in plaats van "edge".

Daarnaast moesten we ook aan gevalideerde certificaten geraken (i.e., gRPC laat geen manueel getekende certificaten toe). Hiervoor maakten we 
gebruik van Let's Encrypt om certificaten te genereren. Volgende moeilijkheid: hoe doen we dit binnen een Kubernetes/OpenShift omgeving? 
OpenShift/Kubernetes heeft het concept van operators: dit zijn componenten binnen de cluster die heel wat infrastructuur werk overnemen van jou. 
Bepaalde installaties (e.g., Kafka, cert manager, ...) worden hierdoor voor jou mogelijk binnen een paar kliks. We installeerden dan de 
cert-manager operator, configureerden een issuer en lieten dan een certificate request genereren. Dit request wordt opgeslagen in een Kubernetes
secret en geinjecteerd in een Kubernetes/OpenShift deployment. Op deze manier krijgt de applicatie direct de nieuwe certificaten toegewezen 
wanneer deze opnieuw gegenereerd worden.

Toen dit opgezet was, konden we beginnen aan de deployment, service en route wrap-up...
Het heeft wat zweet gekost, maar uiteindelijk is gRPC aan de praat gekregen op een online Kuberentes/OpenShift omgeving.
De verschillende resource files kunnen teruggevonden worden in application_server/configuration/metadata_

