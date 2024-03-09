package dummydomain.yetanothercallblocker;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionManager {

    private static final String TAG = "SubscriptionManager";

    private static final String PREFS_NAME = "Subscriptions";
    private static final String PREFS_KEY_WHITELIST = "whitelist";
    private static final String PREFS_KEY_BLACKLIST = "blacklist";

    private Context context;

    public SubscriptionManager(Context context) {
        this.context = context;
    }

    public void addWhitelistSubscription(String url) {
        // 添加白名单订阅
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        List<String> whitelist = getWhitelistSubscriptions();
        if (!whitelist.contains(url)) {
            whitelist.add(url);
            editor.putStringSet(PREFS_KEY_WHITELIST, new HashSet<>(whitelist));
            editor.apply();

            Log.d(TAG, "Added whitelist subscription: " + url);
        } else {
            Log.d(TAG, "Whitelist subscription already exists: " + url);
        }
    }

    public void addBlacklistSubscription(String url) {
        // 添加黑名单订阅
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        List<String> blacklist = getBlacklistSubscriptions();
        if (!blacklist.contains(url)) {
            blacklist.add(url);
            editor.putStringSet(PREFS_KEY_BLACKLIST, new HashSet<>(blacklist));
            editor.apply();

            Log.d(TAG, "Added blacklist subscription: " + url);
        } else {
            Log.d(TAG, "Blacklist subscription already exists: " + url);
        }
    }

    public List<String> getWhitelistSubscriptions() {
        // 获取白名单订阅
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> whitelistSet = prefs.getStringSet(PREFS_KEY_WHITELIST, new HashSet<>());
        return new ArrayList<>(whitelistSet);
    }

    public List<String> getBlacklistSubscriptions() {
        // 获取黑名单订阅
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> blacklistSet = prefs.getStringSet(PREFS_KEY_BLACKLIST, new HashSet<>());
        return new ArrayList<>(blacklistSet);
    }
}
