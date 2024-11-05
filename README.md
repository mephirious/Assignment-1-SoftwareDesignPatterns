# Plants vs. Zombies Game: Design Patterns

This project is an end-term assignment for our Software Design Patterns course, where we implement a simplified version of the popular game "Plants vs. Zombies." The game utilizes various design patterns to enhance its structure, flexibility, and maintainability.

## Collaborators

- **Nursultan Nurgliyev**
- **Ilya Gussak**
- **Nursilam Sheri**
- **Class:** SE-2325

## File Structure

The project is organized as follows:

```
├───.idea
│   └───inspectionProfiles
├───out
│   └───production
│       └───Software-Design-Patterns
│           ├───images
│           ├───Model
│           ├───sounds
│           └───View
└───src
├───images
├───Model
├───sounds
└───View
```


- **.idea/**: Contains configuration files for the IDE.
- **out/**: Compiled production files, including resources like images and sounds.
- **src/**: The source files for the project, organized into subdirectories for images, model classes, sounds, and view components.


## Architecture

We have utilized the **MVVM (Model-View-ViewModel)** architectural pattern to separate the development concerns in our application:
- **Model**: Represents the data and business logic of the game.
- **View**: The UI components that display the game.
- **ViewModel**: Acts as a bridge between the Model and View, managing the data and UI state.


## Application of Structural Patterns 

1. Facade
2. Decorator
3. Composite
4. Flyweight

## Application of Creational Patterns 

1. Singleton
2. Builder
3. Factory Method
4. Prototype

## Application of Behavioral Patterns

1. Command
2. Observer
3. State
4. Strategy
5. Memento

## Conclusion

This project exemplifies the practical application of various software design patterns in creating a robust and scalable game architecture.
