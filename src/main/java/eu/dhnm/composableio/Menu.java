package eu.dhnm.composableio;

/**
 * Class for composing Menus. Displays a menu title (message) and an ordered list of menu items.
 *
 * Menu items can be anything that extends {@link ComposableIO}.
 * When a menu item is selected, its init() method is called.
 *
 * Menu's init() method should be placed inside an infinite loop for a persistent main menu.
 * To exit the program, this can be one of the menu items:
 * <code>new Action("Exit program", () -> { System.exit(0) })</code>
 */
public class Menu extends ComposableIO {
    private final ComposableIO[] items;
    private int choiceIdx;

    /**
     * @param message title of the menu
     * @param items array of menu items
     */
    public Menu(String message, ComposableIO[] items) {
        super("\n" + message);
        this.items = items;
    }

    /**
     * Presents the menu with ordered list of menu items. User makes a selection by typing a number.
     * Input is handled by {@link InputInteger} and a custom validator ensures that the input is a valid choice.
     */
    @Override
    public Menu init() {
        super.init();

        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + " " + items[i].getMessage());
        }

        this.choiceIdx = new InputInteger(
                getMessage(),
                (Integer c) -> c > 0 && c <= items.length,
                "Invalid choice, please try again:"
        ).init().getValue() - 1;
        items[choiceIdx].init();

        return this;
    }

    /**
     * @return Index of menu item that user selected.
     */
    public int getChoiceIdx() {
        return choiceIdx;
    }
}
