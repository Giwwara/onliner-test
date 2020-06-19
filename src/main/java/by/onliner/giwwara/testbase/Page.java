package by.onliner.giwwara.testbase;

import by.onliner.giwwara.context.DriverProvider;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.*;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Page {

    /**
     * Helper method to Initialize page object.
     *
     * @return page Object instance
     */
    public static <T> T on(Class<T> klass) {
        return new Page().get(klass);
    }

    /**
     * Initialize page object.
     *
     * @return page object instance
     */
    private <T> T get(Class<T> klass) {
        try {
            Constructor<T> constructor = klass.getConstructor();
            T page = constructor.newInstance();
            PageFactory.initElements(new ExtendedFieldDecorator(new DefaultElementLocatorFactory(DriverProvider.webDriver())), page);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class ExtendedFieldDecorator extends DefaultFieldDecorator {
        public ExtendedFieldDecorator(ElementLocatorFactory factory) {
            super(factory);
        }

        @Override
        public Object decorate(ClassLoader loader, Field field) {
            if (field.getType().equals(Select.class)) {
                ElementLocator locator = factory.createLocator(field);
                if (locator == null) {
                    return null;
                }
                return new Select(proxyForLocator(loader, locator));
            }
            return super.decorate(loader, field);
        }
    }
}

