# Project JPA
> groep-15

## Opdrachten
- [x] De datalaag bevat minstens één 1-1-relatie, 1-n-relatie en één n-n-relatie.
- [x] Voorzie een relatie met en zonder cascade.
- [x] De datalaag bevat overerving.
- [x] De datalaag heeft één of meerdere value-objecten.
- [x] Met de datalaag kan je één object toevoegen.
- [x] Met de datalaag kan je een verzameling van objecten toevoegen.
- [ ] Met de datalaag kan je objecten opvragen. Voorzie zowel een "lazy" als niet "lazy" opvraging.
- [ ] De datalaag heeft ook een opvraging die gebruik maakt van parameters.
- [ ] Met de datalaag kan je objecten aanpassen.
- [ ] Schrijf voor elke functionaliteit een JUnit-test die het gebruik en de mogelijke speciale gevallen illustreert.

## Oplossing
- 1-1-relaties: Zoo-ZooOwner (bidirectioneel, zonder cascade)
- 1-n-relaties: Zoo-ZooDepartment (bidirectioneel), ZooDepartment-ZooAnimal (bidirectioneel, lazy opvraging, met cascade)
- n-n-relaties: Zoo-ZooDepartment-ZooKeeper (bidirectioneel)
- overerving: ZooOwner en ZooKeeper gebruiken overerving van ZooWorker (volledige hiërarchie vertaalt naar 1 tabel met discriminator)
- value-objecten: Address

## UML Klassendiagram
![](UML/img.png)
