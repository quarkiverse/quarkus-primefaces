package io.quarkiverse.primefaces.deployment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;

import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.logging.Log;

abstract class AbstractJandexProcessor {

    protected List<String> collectClassesInPackage(CombinedIndexBuildItem combinedIndex, String packageName) {
        final List<String> classes = new ArrayList<>();
        final List<DotName> packages = new ArrayList<>(combinedIndex.getIndex().getSubpackages(packageName));
        packages.add(DotName.createSimple(packageName));
        for (DotName aPackage : packages) {
            final List<String> packageClasses = combinedIndex.getIndex()
                    .getClassesInPackage(aPackage)
                    .stream()
                    .map(ClassInfo::toString)
                    .toList();
            classes.addAll(packageClasses);
        }
        Log.debugf("Package Classes: %s", classes);
        return classes;
    }

    protected List<String> collectInterfacesInPackage(CombinedIndexBuildItem combinedIndex, String packageName) {
        final List<String> classes = new ArrayList<>();
        final List<DotName> packages = new ArrayList<>(combinedIndex.getIndex().getSubpackages(packageName));
        packages.add(DotName.createSimple(packageName));
        for (DotName aPackage : packages) {
            final List<String> packageClasses = combinedIndex.getIndex()
                    .getClassesInPackage(aPackage)
                    .stream()
                    .filter(ClassInfo::isInterface) // Filter only interfaces
                    .map(ClassInfo::toString)
                    .toList();
            classes.addAll(packageClasses);
        }
        Log.debugf("Package Interfaces: %s", classes);
        return classes;
    }

    protected List<String> collectSubclasses(CombinedIndexBuildItem combinedIndex, String className) {
        List<String> classes = combinedIndex.getIndex()
                .getAllKnownSubclasses(DotName.createSimple(className))
                .stream()
                .map(ClassInfo::toString)
                .collect(Collectors.toList());
        classes.add(className);
        Log.debugf("Subclasses: %s", classes);
        return classes;
    }

    protected List<String> collectImplementors(CombinedIndexBuildItem combinedIndex, String className) {
        Set<String> classes = combinedIndex.getIndex()
                .getAllKnownImplementors(DotName.createSimple(className))
                .stream()
                .map(ClassInfo::toString)
                .collect(Collectors.toCollection(HashSet::new));
        classes.add(className);
        Set<String> subclasses = new HashSet<>();
        for (String implementationClass : classes) {
            subclasses.addAll(collectSubclasses(combinedIndex, implementationClass));
        }
        classes.addAll(subclasses);
        Log.debugf("Implementors: %s", classes);
        return new ArrayList<>(classes);
    }
}