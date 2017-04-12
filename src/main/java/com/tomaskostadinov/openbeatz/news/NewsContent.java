package com.tomaskostadinov.openbeatz.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class NewsContent {

    /**
     * An array of news items.
     */
    public static final List<NewsItem> ITEMS = new ArrayList<NewsItem>();

    /**
     * A map of news items, by ID.
     */
    public static final Map<String, NewsItem> ITEM_MAP = new HashMap<String, NewsItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createNewsItem(i));
        }
    }

    private static void addItem(NewsItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static NewsItem createNewsItem(int position) {
        //return new NewsItem(String.valueOf(position), "Item " + position, makeDetails(position));
        return new NewsItem(String.valueOf(position), "Item " + position, "openbeatz.de", "text", "now");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class NewsItem {
        public final String id;
        public final String content;
        public final String url;
        public final String type;
        public final String datetime;

        public NewsItem(String id, String content, String url, String type, String datetime) {
            this.id = id;
            this.content = content;
            this.url = url;
            this.type = type;
            this.datetime = datetime;
        }



        @Override
        public String toString() {
            return content;
        }
    }
}
