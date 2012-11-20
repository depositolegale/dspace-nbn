DSpace-NBN:IT
==========

Plugin per [DSpace](http://www.dspace.org) per la generazione di identificatori persistenti [urn:nbn](http://www.depositolegale.it/national-bibliography-number/)

Installazione
-------------
Copiare la directory __dspace__ nella relativa directory [[dspace-source]](https://github.com/DSpace/DSpace/tree/dspace-1_8_x)
e poi procedere con le procedure di default per l'aggiornamento di DSpace (mvn package , ant update)

Il plugin è stato sviluppato per la JSPUI di Dspace 1.8.x

Configurazione
--------------
Definire nel Registro dei Metadati di Dspace il nuovo metadato dc.identifier.nbn.


Credenziali
-----------
Le credenziali di autenticazione al webservice, [ottenute](http://www.depositolegale.it/nbn-flusso-di-lavoro/) in seguito all'adesione al servizio, vanno inserite nel file di configurazione
dspace/config/modules/identifier.cfg

Licenza
-------
Il plugin è sviluppato dai [Servizi informativi-Centracon](http://www.centracon.units.it/SIE) dell'[Università di Trieste](http://www.units.it) ed è rilasciato sotto [Licenza Dspace](http://www.dspace.org/license/)
