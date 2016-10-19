package ru.garf.aop.objects;

import java.util.Map;
import java.util.Set;

public interface Manager {

	Set<String> getExtensionList(String folder);

	Map<String, Integer> getExtensionCounter(String folder);

}
