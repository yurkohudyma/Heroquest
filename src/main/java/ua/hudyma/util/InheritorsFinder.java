package ua.hudyma.util;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InheritorsFinder {

    /**
     * Code by ChatGPT
     */

    public static <T> List<T> retrieveAllClassInstances (Class<T> parentClass, String packageName ) throws ClassNotFoundException {
        var classList = findInheritors(parentClass, packageName);
        return mapToEntity(classList, parentClass);
    }

    public static <T> List<T> retrieveAllClassInstances (Class<T> parentClass) throws ClassNotFoundException {
        String packageName = parentClass.getPackageName();
        var classList = findInheritors(parentClass, packageName);
        return mapToEntity(classList, parentClass);
    }
    public static List<Class<?>> findInheritors(Class<?> parentClass, String packageName) throws ClassNotFoundException {
        List<Class<?>> inheritors = new ArrayList<>();
        // Get the path to the package
        String path = packageName.replace('.', '/');
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        if (url == null) {
            return inheritors; // No classes in the package
        }
        // Get all files in the package directory
        File directory = new File(url.getFile());
        if (directory.exists()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".class"));
            if (files != null) {
                for (File file : files) {
                    String className = file.getName().substring(0, file.getName().length() - 6);
                    Class<?> clazz = Class.forName(packageName + "." + className);
                    // Check if the class is a subclass of parentClass (excluding the parent itself)
                    if (parentClass.isAssignableFrom(clazz) && !clazz.equals(parentClass) && !Modifier.isAbstract(clazz.getModifiers())) {
                        inheritors.add(clazz);
                    }
                }
            }
        }
        return inheritors;
    }

    public static <T> List<T> mapToEntity(List<Class<?>> classList, Class<T> entityType) {
        List<T> entities = new ArrayList<>();
        for (Class<?> clazz : classList) {
            try {
                // Ensure the class is an instance of entityType
                if (entityType.isAssignableFrom(clazz)) {
                    // Instantiate the class via reflection
                    Constructor<?> constructor = clazz.getConstructor();
                    T entity = entityType.cast(constructor.newInstance()); // Cast to the desired type
                    entities.add(entity);
                }
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception properly, such as logging
            }
        }
        return entities;
    }
}
