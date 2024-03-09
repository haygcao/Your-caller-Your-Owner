public class FloatWindowActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 123;

    private WindowManager windowManager;
    private View floatWindowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_window);

        // Check if we have the permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, REQUEST_CODE_PERMISSION);
        } else {
            // We already have the permission, so show the window
            showCallFloatWindow();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, show the window
                showCallFloatWindow();
            } else {
                // Permission denied, do nothing
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showCallFloatWindow() {
        // Get the caller information
        String callerNumber = getIntent().getStringExtra("callerNumber");
        String callerName = getIntent().getStringExtra("callerName");
        String callerLocation = getIntent().getStringExtra("callerLocation");

        // Create the layout params for the floating window
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER;

        // Inflate the layout for the floating window
        floatWindowView = LayoutInflater.from(this).inflate(R.layout.layout_call_float_window, null);

        // Populate the text views with the caller information
        TextView textViewCallerNumber = (TextView) floatWindowView.findViewById(R.id.textViewCallerNumber);
        textViewCallerNumber.setText(callerNumber);
        TextView textViewCallerName = (TextView) floatWindowView.findViewById(R.id.textViewCallerName);
        textViewCallerName.setText(callerName);
        TextView textViewCallerLocation = (TextView) floatWindowView.findViewById(R.id.textViewCallerLocation);
        textViewCallerLocation.setText(callerLocation);

        // Add the floating window to the window manager
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(floatWindowView, layoutParams);
    }

    // Get the close button (assuming it's defined in layout_call_float_window.xml)
    Button buttonClose = (Button) floatWindowView.findViewById(R.id.buttonClose);

    // Set onclick listener for the close button
    buttonClose.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Close the floating window
        if (windowManager != null && floatWindowView != null) {
          windowManager.removeView(floatWindowView);
        }
        finish(); // Optionally finish the activity if needed
      }
    });
  
@Override
protected void onPause() {
    super.onPause();

    // Check if app is not in foreground
    if (!isApplicationVisible()) {
        // Remove the floating window
        if (windowManager != null && floatWindowView != null) {
            windowManager.removeView(floatWindowView);
        }
    }
}

private boolean isApplicationVisible() {
    ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
    List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(1);
    if (tasks != null && tasks.get(0).topActivity.getPackageName().equalsIgnoreCase(getPackageName())) {
        return true;
    }
    return false;
}
}
