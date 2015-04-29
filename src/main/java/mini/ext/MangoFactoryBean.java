package mini.ext;

import org.jfaster.mango.operator.Mango;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author ash
 */
public class MangoFactoryBean implements FactoryBean<Object>, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Class<?> daoClass;

    @Override
    public Object getObject() throws Exception {
        Mango mango = applicationContext.getBean(Mango.class);
        return mango.create(daoClass);
    }

    @Override
    public Class<?> getObjectType() {
        return daoClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setDaoClass(Class<?> daoClass) {
        this.daoClass = daoClass;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
