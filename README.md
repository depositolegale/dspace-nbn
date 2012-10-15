DSpace-NBN:IT
==========

Plugin per [DSpace](http://www.dspace.org) per la generazione di identificatori persistenti [urn:nbn](http://www.depositolegale.it/national-bibliography-number/)

Installazione
-------------
Copiare la directory __dspace__ nella relativa directory [[dspace-source]](https://github.com/DSpace/DSpace/tree/dspace-1_8_x)
e poi procedere con le procedure di default per l'aggiornamento di DSpace (mvn package , ant update)
Il plugin è stato sviluppato per la JSPUI di Dspace 1.8.x
ATTENZIONE: il plugin va a modificare anche una core-library: org.dspace.app.webui.servlet.MyDspaceServlet che potrebbe esser diversa nelle versioni precedenti alla 1.8.x

Credenziali
-----------
Le credenziali di autenticazione al webservice, [ottenute](http://www.depositolegale.it/nbn-flusso-di-lavoro/) in seguito all'adesione al servizio, vanno inserite nel file di configurazione
dspace/config/modules/identifier.cfg

