package com.tomaskostadinov.openbeatz.news;

import com.tomaskostadinov.openbeatz.model.Message;

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
    public static final List<Message> ITEMS = new ArrayList<Message>();

    /**
     * A map of news items, by ID.
     */
    public static final Map<String, Message> ITEM_MAP = new HashMap<String, Message>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createNewsItem(i));
        }
    }

    private static void addItem(Message item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getType(), item);
    }

    private static Message createNewsItem(int position) {
        return new Message(String.valueOf(position), "Now", "Lol es geht", "default", "https://connect.nearstage.com/site/assets/files/2181/14195484_1071564426298517_3611941979064840177_o.600x400.jpg", 1841, "https://connect.nearstage.com/v1/openbeatz/posts/1841/");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}
