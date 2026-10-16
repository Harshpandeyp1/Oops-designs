package factory;

import product.Button;
import product.CheckBox;
import product.WindowsButton;
import product.WindowsCheckbox;

public class WindowsFactory implements UIFactory {
    @Override
    public Button createButton(){
        return new WindowsButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new WindowsCheckbox();
    }
}
