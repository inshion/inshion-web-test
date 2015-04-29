package mini.ext;

import org.jfaster.mango.annotation.DB;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ash
 */
public class MangoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private List<String> locationPatterns;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory dlbf = (DefaultListableBeanFactory) beanFactory;
        for (Class<?> daoClass : findMangoDaoClass()) {
            GenericBeanDefinition bf = new GenericBeanDefinition();
            bf.setBeanClassName(daoClass.getName());
            MutablePropertyValues pvs = bf.getPropertyValues();
            pvs.addPropertyValue("daoClass", daoClass);
            bf.setBeanClass(MangoFactoryBean.class);
            bf.setPropertyValues(pvs);
            bf.setLazyInit(false);
            dlbf.registerBeanDefinition(daoClass.getName(), bf);
        }
    }

    private List<Class<?>> findMangoDaoClass() {
        try {
            List<Class<?>> daos = new ArrayList<Class<?>>();
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            for (String locationPattern : locationPatterns) {
                Resource[] rs = resourcePatternResolver.getResources(locationPattern);
                MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
                for (Resource r : rs) {
                    MetadataReader reader = metadataReaderFactory.getMetadataReader(r);
                    AnnotationMetadata annotationMD = reader.getAnnotationMetadata();
                    if (annotationMD.hasAnnotation(DB.class.getName())) {
                        ClassMetadata clazzMD = reader.getClassMetadata();
                        daos.add(Class.forName(clazzMD.getClassName()));
                    }
                }
            }
            return daos;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void setLocationPatterns(List<String> locationPatterns) {
        this.locationPatterns = locationPatterns;
    }

}
