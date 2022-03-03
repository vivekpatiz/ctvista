//root pacakage where spring boot resides
package com.danaherdigital.che_hx.asset_management.exceptionhandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class EntityNotFoundException.
 */
public class EntityNotFoundException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new entity not found exception.
	 *
	 * @param clazz the clazz
	 * @param searchParamsMap the search params map
	 */
	public EntityNotFoundException(Class<?> clazz, String... searchParamsMap) {
		super(EntityNotFoundException.generateMessage(clazz.getSimpleName(),
				toMap(String.class, String.class, (Object[])searchParamsMap)));
	}

	/**
	 * Generate message.
	 *
	 * @param entity the entity
	 * @param searchParams the search params
	 * @return the string
	 */
	private static String generateMessage(String entity, Map<String, String> searchParams) {
		return StringUtils.capitalize(entity) + " was not found for parameters " + searchParams;
	}

	/**
	 * To map.
	 *
	 * @param <K> the key type
	 * @param <V> the value type
	 * @param keyType the key type
	 * @param valueType the value type
	 * @param entries the entries
	 * @return the map
	 */
	private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
		if (entries.length % 2 == 1)
			throw new IllegalArgumentException("Invalid entries");
		return IntStream.range(0, entries.length / 2).map(i -> i * 2).collect(HashMap::new,
				(m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])), Map::putAll);
	}

}
