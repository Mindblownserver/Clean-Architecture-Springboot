# Core package contents
This includes all the **business layer**. From entities to the logic and interactions between these entities.

## Domain package
This holds the domain layer. It's basically pure java classes that explains relationship between domain entities, in our case `User` and `Role`.

## Use Cases package
This explains what we can do with these entities. They are extracted from our **use case diagram**.
### Our Use Cases
We want the following:
- Add user
- Update User (as well as his role)
- Add Role
- Update Role
- sign in
- archive user

Each usecase is a defined **interactor** and implemented within.
Each interactor uses boundaries: *Input boundaries* and *Output boundaries* ~~, and *Models*~~

Note that since we can group use cases and domain into one layer (business layer), we can assume the objects that are transmitted between them are that of the **domain**.
### Input Boundary
It's a component (typically an interface) that exposes our usecase to outer layer
```
output layer -(uses)-> input boundary -(communicates with)-> interactor
```

### Output Boundary
It's a component (also an interface) that makes use of outer layers, so the opposite of its cousin input boundary.
```
interactor -(uses)-> output boundary -(talks with)-> outer layer -(returns data back to)-> interactor
```
