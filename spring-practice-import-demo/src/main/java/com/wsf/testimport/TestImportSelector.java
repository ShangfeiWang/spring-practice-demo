package com.wsf.testimport;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

public class TestImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        boolean b = importingClassMetadata.hasAnnotation("org.springframework.stereotype.Component");
        System.out.println("importingClassMetadata：=" + importingClassMetadata);
        System.out.println("是否包含了 org.springframework.stereotype.Component注解 : " + b);
        return new String[]{"com.wsf.testimport.Blue", "com.wsf.testimport.Red"};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }
}
