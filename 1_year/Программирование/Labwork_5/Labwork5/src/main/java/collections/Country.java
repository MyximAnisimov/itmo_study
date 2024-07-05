package collections;

/**
 * Перечисление национальностей
 */
public enum Country {
    UNITED_KINGDOM,
    INDIA,
    VATICAN,
    ITALY,
    JAPAN;
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (Country country : values()) {
            nameList.append(country.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
