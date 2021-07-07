# ComposableIO

Utility classes for composing command-line input flows.

There are two main uses: building menus and handling general input. The inputs allow custom validator hooks and recursively ask for input until the validator is satisfied.

## Examples

Menu

```java
// Always present the Menu until the user chooses to exit
while (true) {

    new Menu("MENU", new ComposableIO[] {
        // Menu items
        new Action("Do this", MainIO::doThis),
        new Action("Hello World", () -> {
            System.out.println("Hello World!");
        }),
        new Branch("A Submenu", new Menu("Select one:", new ActionIO[] {
            new Action("Do that", MainIO::doThat)
        }),
        new Action("Exit program", () -> System.exit(0))
    }).init();

}
```

Input

```java
String name = new InputString("Enter your name:").init().getValue()
```

```java
this.choiceIdx = new InputInteger(
    getMessage(),
    (Integer c) -> c > 0 && c <= items.length,
    "Invalid choice, please try again:"
).init().getValue() - 1;
```

Custom

```java
Room rooms = // Array of Room objects

ComposableIO[] roomItems = ComposableIO.toItems(rooms);
Menu roomChoice = new Menu("Which room?", roomItems);
roomChoice.init(); // Present menu

int selectionIndex = roomChoice.getChoiceIdx();
Room selectedRoom = rooms[selectionIndex];
```