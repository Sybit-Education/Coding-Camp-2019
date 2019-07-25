# Sybit Coding Camp 2019


## Entwicklung

### Voraussetzungen

- JDK 1.8
- Entwicklungsumgebung (Netbeans, IntelliJ, Eclipse, Visual Code, o.a.)
- Maven

### Projekt "bauen"

Der Quellcode muss kompiliert und mit den verschiedenen Dateien 
(Bibliotheken, HTML-Seiten, Konfigurations-Dateien, etc.) zusmmen gepackt werden.

Dazu verwenden wir das Werkzeug **Maven**. Diese kann über die Kommandozeile 
aufgerufen werden und integriert sich aber auch in die Entwicklungsumgebungen.

#### Maven Kommandos:

- Projekt kompilieren und testen:
  ``` bash
  > mvnw install
  ```

- Projekt starten:
  ``` bash
  > mvnw spring-boot:run
  ```
  Die Anwendung ist dann unter dem Link [http://localhost:8080] auf dem lokalen Computer im Browser aufrufbar.

## Installation

### Docker

### Erstellen des Docker-Containers

``` bash
> docker build -f ./docker/Dockerfile -t master-mind .
```

### Starten der Umgebung

Wir unterscheiden drei Systeme bei der Entwklckung des Projektes:

#### Lokal (Entwicklung)

Während der Entwicklung wird die Datenbank immer wieder beim Neustart neu im Speicher angelegt.
Somit hat man immer wieder den gleichen initialen Stand.

Das Projekt lässt sich direkt aus der Entwicklungsumgebung starten.
Alternativ kann es auch mit folgendem Kommando in der Konsole gestartet werden:

``` bash
> mvnw spring-boot:run
```

#### Test (Test-System)

Auf dem Test und Produktiv-System nutzen wir eine Virtualisierung der Anwendung mit **Docker**.

Testumgebung starten:

``` bash
> docker-compose -f .\docker\docker-compose-TEST.yml up
```
  
#### Production (Live-System)

Produktiv-System starten:

``` bash
> docker-compose -f .\docker\docker-compose-PRODUCTION.yml up
```
