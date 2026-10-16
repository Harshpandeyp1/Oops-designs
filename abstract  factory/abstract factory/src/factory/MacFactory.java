package factory;

import product.Button;
import product.CheckBox;
import product.MacButton;
import product.MacCheckbox;

public class MacFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacCheckbox();
    }
}
