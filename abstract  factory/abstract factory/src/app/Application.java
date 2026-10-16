package app;

import factory.UIFactory;
import product.Button;
import product.CheckBox;

public class Application {
    private final Button button;
    private final CheckBox checkbox;

    public Application(UIFactory factory) {       // family chosen once, here
        this.button = factory.createButton();
        this.checkbox = factory.createCheckBox();
    }

    public void render() {
        button.render();
        checkbox.render();
    }
}
