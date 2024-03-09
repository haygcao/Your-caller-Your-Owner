public class FloatWindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_window);

        // 获取来电信息
        String callerNumber = getIntent().getStringExtra("callerNumber");
        String callerName = getIntent().getStringExtra("callerName");
        String callerLocation = getIntent().getStringExtra("callerLocation");

        // 显示浮动窗口
        showCallFloatWindow(callerNumber, callerName, callerLocation);
    }

        private void showCallFloatWindow(String callerNumber, String callerName, String callerLocation) {
            // Implement logic to display the call float window using the retrieved information
            // You can use WindowManager to create a floating window and display the caller details

            WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.gravity = Gravity.CENTER;

            View view = LayoutInflater.from(this).inflate(R.layout.layout_call_float_window, null);
            TextView textViewCallerNumber = (TextView) view.findViewById(R.id.textViewCallerNumber);
            textViewCallerNumber.setText(callerNumber);
            TextView textViewCallerName = (TextView) view.findViewById(R.id.textViewCallerName);
            textViewCallerName.setText(callerName);
            TextView textViewCallerLocation = (TextView) view.findViewById(R.id.textViewCallerLocation);
            textViewCallerLocation.setText(callerLocation);

            windowManager.addView(view, layoutParams);
        }
}
