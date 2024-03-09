public class SubscribeActivity extends AppCompatActivity {

    private SubscriptionManager subscriptionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        subscriptionManager = new SubscriptionManager(this);

        findViewById(R.id.button_add_whitelist).setOnClickListener(v -> {
            String url = ((EditText) findViewById(R.id.edit_text_url)).getText().toString();
            subscriptionManager.addWhitelistSubscription(url);
        });

        findViewById(R.id.button_add_blacklist).setOnClickListener(v -> {
            String url = ((EditText) findViewById(R.id.edit_text_url)).getText().toString();
            subscriptionManager.addBlacklistSubscription(url);
        });
    }

    private boolean isValidUrl(String url) {
    if (url == null || url.isEmpty()) {
        return false;
    }

    // 检查协议
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        return false;
    }

    // 检查域名和端口号
    try {
        URL u = new URL(url);
        u.getHost();
        u.getPort();
    } catch (MalformedURLException e) {
        return false;
    }

    // 检查路径
    if (url.getPath().isEmpty()) {
        return false;
    }

    return true;
}
