package io.kontakt.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CategoryService {

	public static CategoryService INSTANCE = new CategoryService();

	private Map<String, Set<Object>> DB = new HashMap<>();

	public String createCategory(String category) {
		LinkedHashSet<Object> objects = new LinkedHashSet<>();
		DB.put(category, objects);

		for (int i = 0; i < new Random().nextInt(100) + 1; i++) {
			objects.add(category + "_" + i);
		}

		return category;
	}

	public void addToCategory(String category, Object element) {
		DB.get(category).add(element);
	}

	public void removeFromCategory(String category, Object element) {
		DB.get(category).remove(element);
	}

	public List<Object> getCategoryObjects(String category) {
		return new ArrayList<>(DB.get(category));
	}

}
