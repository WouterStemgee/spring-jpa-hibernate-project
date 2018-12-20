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
- [x] De datalaag heeft ook een opvraging die gebruik maakt van parameters.
- [x] Met de datalaag kan je objecten aanpassen.
- [x] Schrijf voor elke functionaliteit een JUnit-test die het gebruik en de mogelijke speciale gevallen illustreert.

## Testen
### Overzicht geteste functionaliteit
#### addAndUpdateZoo()
- object toevoegen
- objecten aanpassen
- heeft value-object

#### addZooWithOwner()
- 1-1-relatie
- relatie zonder cascade
- heeft value-object

#### addZooWithDepartments()
- 1-n-relatie
- verzameling van objecten toevoegen
- niet "lazy" opvraging

#### addDepartmentWithAnimals()
- 1-n-relatie
- "lazy" opvraging
- opvraging die gebruik maakt van parameters
- overerving

#### addDepartmentsWithKeepers()
- n-n-relatie
- relatie met cascade

### Entities testen
- Entity testen door ze aan te maken
- De object-relaties checken: assertSame(expected, actual)
- Entities wegschrijven naar database
- Entities opvragen uit database
- Checken indien opgevraagde Entities dezelfde properties hebben als de oorspronkelijke Entities: assertEqual(expected, actual)

### TODO
- [x] Zoo toevoegen
- [x] Zoo met meerdere ZooDepartment's toevoegen
- [x] Zoo met ZooOwner toevoegen
- [x] meerdere ZooDepartment's met meerdere ZooKeeper's toevoegen
- [x] ZooDepartment met meerdere ZooAnimal's toevoegen
- [x] wijzigingen kunnen uitgevoerd worden op properties van een Entity
- [x] Test fetch = LAZY/EAGER
- [x] Test cascade = ALL bij ZooKeeper<->ZooDepartment

## UML Klassendiagram
![](https://i.imgur.com/U0eBWji.png)

## Database
![](https://i.imgur.com/AFxLNDk.png)

## Oplossing
### 1-1 relaties
- Zoo<->ZooOwner
### 1-n relaties
- Zoo<->ZooDepartment
- ZooDepartment<->ZooAnimal (cascade)
### n-n relaties
- ZooDepartment<->ZooKeeper (JoinTable = "zookeepers_per_departments")
### Overerving 
- ZooOwner en ZooKeeper gebruiken overerving van ZooWorker (hiërarchie vertaalt naar 1 tabel met discriminator)
- Mammal en Bird gebruiken overerving van ZooAnimal
### Value-objecten
- Address (Embeddable)