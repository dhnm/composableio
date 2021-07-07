package eu.dhnm.composableio;

/**
 * Syntactic sugar for Action class, displays a provided submenu instead of calling an arbitrary callback function
 * @author Dinh Huy Nhat Minh
 */
public class Branch extends Action {
    /**
     * @param menu the sub-menu ({@link Menu}) that will be called on selection / on init()
     */
    public Branch(String message, Menu menu) {
        super(message, menu::init);
    }
}
