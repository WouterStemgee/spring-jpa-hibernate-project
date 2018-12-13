# Project JPA
> groep-15

## Opdrachten
- [x] De datalaag bevat minstens één 1-1-relatie, 1-n-relatie en één n-n-relatie.
- [x] Voorzie een relatie met en zonder cascade.
- [x] De datalaag bevat overerving.
- [x] De datalaag heeft één of meerdere value-objecten.
- [x] Met de datalaag kan je één object toevoegen.
- [x] Met de datalaag kan je een verzameling van objecten toevoegen.
- [x] Met de datalaag kan je objecten opvragen. Voorzie zowel een "lazy" als niet "lazy" opvraging.
- [ ] De datalaag heeft ook een opvraging die gebruik maakt van parameters.
- [ ] Met de datalaag kan je objecten aanpassen.
- [ ] Schrijf voor elke functionaliteit een JUnit-test die het gebruik en de mogelijke speciale gevallen illustreert.

## Testen
### TODO
- [x] Zoo toevoegen
- [x] Zoo met meerdere ZooDepartment's toevoegen
- [ ] Zoo met ZooOwner toevoegen
- [ ] meerdere ZooDepartment's met meerdere ZooKeeper's toevoegen
- [x] ZooDepartment met meerdere ZooAnimal's toevoegen
- [ ] Test fetch = EAGER bij Zoo<->ZooDepartment
- [ ] Test cascade = REMOVE bij Zoo<->ZooDepartment
- [ ] Test fetch = LAZY bij ZooDeparments<->ZooAnimal
- [ ] Test cascade = DETACH bij ZooDeparments<->ZooAnimal
- [ ] Zoo toevoegen met alle mogelijke Entiteiten + alle opvragingen met parameters uitvoeren
- [ ] Zoo toevoegen met alle mogelijke Entiteiten + wijzigingen uitvoeren op alle properties

### Entities testen
1. Entity testen door ze aan te maken 
2. De object-relaties checken: assertSame(expected, actual)
3. Entities wegschrijven naar database
4. Entities opvragen uit database
5. Checken indien opgevraagde Entities dezelfde properties hebben als de oorspronkelijke Entities: assertEqual(expected, actual)

## UML Klassendiagram
![](https://i.imgur.com/cdlWxcT.png)

## Oplossing
### 1-1 relaties
- Zoo<->ZooOwner
### 1-n relaties
- Zoo<->ZooDepartment (fetch = EAGER, cascade = REMOVE)
- ZooDepartment<->ZooAnimal (fetch = LAZY, cascade = DETACH)
### n-n relaties
- ZooDepartment<->ZooKeeper (JoinTable = "zookeepers_per_departments")
### Overerving 
- ZooOwner en ZooKeeper gebruiken overerving van ZooWorker (hiërarchie vertaalt naar 1 tabel met discriminator)
### Value-objecten
- Address (Embeddable)

